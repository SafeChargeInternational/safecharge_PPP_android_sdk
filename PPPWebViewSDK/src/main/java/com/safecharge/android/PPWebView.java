package com.safecharge.android;

import java.io.IOException;
import java.lang.annotation.Target;
import java.util.Map;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.os.*;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.safecharge.android.request.PPRequest;
import com.safecharge.android.request.models.PPResult;
import com.safecharge.android.util.Constants;
import com.safecharge.android.util.OkHttpUnsafeClientBuilder;
import com.safecharge.android.util.OkHttpDefaultClientBuilder;
import com.safecharge.android.util.Util;

@SuppressLint("SetJavaScriptEnabled")
/**
 * Configured webview for loading payment page, accepting callabacks for start, stop, progress change and result from ppp. 
 * @author Bozhidar
 *
 */
public class PPWebView extends WebView {
	private static final String UTF_8 = "UTF-8";
	private static final String PURCHASE_DO = "purchase.do";
	private static final String REVIEW_DO = "review.do";
	private static final String OVERRIDE_WIN_OPEN_JS = buildOverrideWindowOpenJS();
	private static boolean ignoreSecurity;

	private IPageFinishCallback pageFinishCallback;
	private IErrorCallback errorCalback;
	private IProgressCallback progressCallback;
	private IPageStartCallback pageStartCallback;
	private IPPPResultCallback resultCallback;
	
	private boolean interceptPurchaseRequest;
	private boolean isReviewExecuted;



	
	public PPWebView(Context context) {
		super(context);
		init();
	}

	public PPWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public PPWebView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	
	public void loadJS(String js) {
		super.loadUrl("javascript:" +js);
	}
	
	/**
	 * Will not take effect
	 */
	@Override
	@Deprecated
	public void postUrl(String url, byte[] postData) {
	}
	
	/**
	 * Will not take effect
	 */
	@Override
	@Deprecated
	public void setWebChromeClient(WebChromeClient client) {
	}
	
	/**
	 * Will not take effect
	 */
	@Override
	@Deprecated
	public void setWebViewClient(WebViewClient client) {
	}
	
	/**
	 * Load the payment page
	 * @param request
	 */
	public void load(PPRequest request) {
		if (request == null) {
			throw new IllegalStateException("You must provide PPPRequest !");
		}
		
		
		String pppRequestString = request.toUrlEncodedString();
		Log.i(PPWebView.class.getCanonicalName() , request.getUrl() + pppRequestString);
		
		super.loadUrl(request.getUrl() + pppRequestString);
	}
	
	public void setPageFinishCallback(IPageFinishCallback pageFinishCallback) {
		this.pageFinishCallback = pageFinishCallback;
	}
	
	public void setErrorCalback(IErrorCallback errorCalback) {
		this.errorCalback = errorCalback;
	}

	public void setProgressCallback(IProgressCallback progressCallback) {
		this.progressCallback = progressCallback;
	}
	
	public void setStartPageCallback(IPageStartCallback callback) {
		this.pageStartCallback = callback;
	}

	public void setResultCallback(IPPPResultCallback resultCallback) {
		this.resultCallback = resultCallback;
	}

	///////////////////////////////////////////////////////////////
	public static void setIgnoreSecurity(boolean ignoreSecurity) {
		PPWebView.ignoreSecurity = ignoreSecurity;
	}

	public static boolean getIgnoreSecurity() {
		return PPWebView.ignoreSecurity;
	}

	private void init() {
		getSettings().setJavaScriptEnabled(true);
		getSettings().setSupportZoom(true);
		getSettings().setSupportMultipleWindows(true);
		
		super.setWebChromeClient(new WebChromeClient() {
			
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if (progressCallback != null) {
					progressCallback.onProgressChanged(newProgress);
				}
				super.onProgressChanged(view, newProgress);
			}
			
			@Override
			public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
				result.confirm();
				return true;
			}
			
			@Override
			public void onCloseWindow(WebView window) {
				closePopUp(window);
				super.onCloseWindow(window);
			}

			@Override
			public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
				PPWebView newWebView = new PPWebView(getContext());
				
				view.scrollTo(0, 0);
				
				view.addView(newWebView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
				
				WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
		        transport.setWebView(newWebView);
		        resultMsg.sendToTarget();
				
		        return true;
			}
		});
		
		super.setWebViewClient(new WebViewClient() {

			@Override
			public void onPageFinished(WebView view, String url) {
				if (pageFinishCallback != null) {
					pageFinishCallback.onFinish(url);
				}
				
				if (url.contains(Constants.ResultParams.PPP_STATUS)) {
					interceptPurchaseRequest = false;
					isReviewExecuted = false;
					
					if (resultCallback != null) {
						Map<String, String> urlParams = Util.getParamsMap(url);
						PPResult result = null;
						result = Util.toPPPResult(urlParams);
						
						resultCallback.onResult(result);
					}
				}
				
				loadJS(OVERRIDE_WIN_OPEN_JS);

				super.onPageFinished(view, url);
			}
			
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				
				if (pageStartCallback != null) {
					pageStartCallback.onPageStart(url);
				}
				
				
				super.onPageStarted(view, url, favicon);
			}
			
			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				if (errorCalback != null) {
					errorCalback.onReceivedError(errorCode, description, failingUrl);
				}
				super.onReceivedError(view, errorCode, description, failingUrl);
			}

			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			@Deprecated
			@Override
			public WebResourceResponse shouldInterceptRequest(WebView view,
															  String url) {

				if (url.contains(REVIEW_DO)) {
					isReviewExecuted = true;
				} else if (!isReviewExecuted && url.contains(PURCHASE_DO)) {

					if (!interceptPurchaseRequest) {
						interceptPurchaseRequest = true;
						return super.shouldInterceptRequest(view, url);
					} else {
						Request okHttpRequest = new Request.Builder().url(url).build();
						try {
							OkHttpClient okHttp = PPWebView.getIgnoreSecurity() ? OkHttpUnsafeClientBuilder.getUnsafeOkHttpClient()
									: OkHttpDefaultClientBuilder.getDefaultHttpClient();

							Response response = okHttp.newCall(okHttpRequest).execute();
							return new WebResourceResponse(response.header("Content-Type", "text/html;charset=utf-8"),
									response.header("Content-Encoding", "deflate"),
									response.body().byteStream());

						} catch (IOException e) {
							e.printStackTrace();
						}
						return null;
					}
				}
				return super.shouldInterceptRequest(view, url);
			}


			@TargetApi(Build.VERSION_CODES.LOLLIPOP)
			@Override
			public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request ) {
				String url = request.getUrl().toString();
				if (url.contains(REVIEW_DO)) {
					isReviewExecuted = true;
				} else if (!isReviewExecuted && url.contains(PURCHASE_DO)) {
					if (!interceptPurchaseRequest) {
						interceptPurchaseRequest = true;
						return null;
					} else {
						Request okHttpRequest = new Request.Builder().url(url).build();
						try {
							OkHttpClient okHttp = PPWebView.getIgnoreSecurity() ? OkHttpUnsafeClientBuilder.getUnsafeOkHttpClient()
																				: OkHttpDefaultClientBuilder.getDefaultHttpClient() ;

							Response response = okHttp.newCall(okHttpRequest).execute();
							return new WebResourceResponse("text/html;charset=utf-8","deflate",response.body().byteStream());
						} catch (IOException e) {
							e.printStackTrace();
						}
						return null;
					}
				}
				return null;
			}

		});
	}
	
	private void closePopUp(WebView window) {
		ViewParent viewParent = window.getParent();
		
		if (viewParent != null && viewParent instanceof PPWebView) {
			PPWebView parent = (PPWebView) viewParent;
			parent.loadJS("_popupWindow.closed=true;");
			parent.setActivated(true);
			parent.removeView(window);
		}
	}
	
	private static String buildOverrideWindowOpenJS() {
		StringBuilder builder = new StringBuilder();
		builder.append("if (!window.openAndCloseAreOverriden) {");
		builder.append("var _open = window.open;");
		builder.append(" window.open = function(url, name, properties)");
		builder.append( "{ _open(url, name, properties);");
		builder.append("_popupWindow = {  closed : false, focus : function() { },  close : function() { } };");
		builder.append("return _popupWindow; };");
		builder.append("window.openAndCloseAreOverriden = true; };");
		
		return builder.toString();
	}
	
	@Override
	public void goBack() {
		PPWebView lastWebView = findLastWebView(this);
		if (lastWebView != null && !lastWebView.equals(this)) {
			closePopUp(lastWebView);
		}
		super.goBack();
	}
	
	private PPWebView findLastWebView(View root) {
		if (root == null) {
			return null;
		}
		
		if (root instanceof ViewGroup) {
			ViewGroup rootGroup = (ViewGroup) root;
		
			for(int i = rootGroup.getChildCount() -1; i >= 0; i--) {
				PPWebView result = findLastWebView(rootGroup.getChildAt(i));
				if (result != null) {
					return result;
				}
			}
				
			if (root instanceof PPWebView) {
				return (PPWebView) root;
			} else {
				return null;
			}
		}
		
		return null;
	}
	
	@Override
	public boolean canGoBack() {
		PPWebView lastWebView = findLastWebView(this);
		if (lastWebView != null && !lastWebView.equals(this)) {
			return true;
		}
		return super.canGoBack();
	}
	
	public static interface IPageFinishCallback {
		void onFinish(String url);
	}
	
	public static interface IPPPResultCallback{
		void onResult(PPResult result);
	}

	public static interface IErrorCallback {
		void onReceivedError(int errorCode, String description, String failingUrl);
	}

	public static interface IProgressCallback {
		void onProgressChanged(int newProgress);
	}
	
	public static interface IPageStartCallback {
		void onPageStart(String url);
	}
	
}
