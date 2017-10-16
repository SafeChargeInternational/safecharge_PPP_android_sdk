package com.safecharge.pppexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.safecharge.android.PPWebView;
import com.safecharge.android.PPWebView.IErrorCallback;
import com.safecharge.android.PPWebView.IPPPResultCallback;
import com.safecharge.android.request.models.PPResult;


public class SampleActivity extends AppCompatActivity {

    private PPWebView  webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);

        webView = (PPWebView) findViewById(R.id.webview);

        webView.clearCache(true);
        webView.clearHistory();
        //useful in development, do not use in production!!
        webView.setIgnoreSecurity(false);

        final boolean redirect = getIntent().getBooleanExtra("redirect", true);
        String url = getIntent().getStringExtra("url");

        webView.loadUrl(url);

        webView.setResultCallback(new IPPPResultCallback() {

            @Override
            public void onResult(PPResult result) {
                Intent data = new Intent();
                data.putExtra("ppp_status", result.getPppStatus());

                if (!redirect) {
                    webView.stopLoading();
                    setResult(Activity.RESULT_OK, data);
                    finish();
                }
            }
        });

        webView.setErrorCalback(new IErrorCallback() {

            @Override
            public void onReceivedError(int errorCode, String description, String failingUrl) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
