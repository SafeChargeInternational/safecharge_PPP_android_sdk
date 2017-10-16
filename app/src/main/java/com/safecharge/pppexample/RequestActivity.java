package com.safecharge.pppexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.safecharge.android.request.PPRequest;
import com.safecharge.android.request.PPRequestBuilder;
import com.safecharge.android.request.PPWithdrawalRequestBuilder;
import com.safecharge.android.request.models.PPItemDetails;
import com.safecharge.android.util.Constants.UserToken;

public class RequestActivity extends Activity {
	private Button loadURLButton;
	private Button generatePP;
	private Button generateWD;
	
	private EditText urlEdit;
	private CheckBox redirectCheck;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.request_activity);
		super.onCreate(savedInstanceState);

		loadURLButton = (Button) findViewById(R.id.request_activity_redirect_load_button);
		generatePP = (Button) findViewById(R.id.request_activity_default_pp_button);
		generateWD = (Button) findViewById(R.id.request_activity_default_wd_button);

		urlEdit = (EditText) findViewById(R.id.request_activity_url_edit);
		redirectCheck = (CheckBox) findViewById(R.id.request_activity_redirect_check);
		
		Intent intent = getIntent();
	    String action = intent.getAction();
	    String type = intent.getType();

	    if (Intent.ACTION_SEND.equals(action) && type != null) {
	    	String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
	    	urlEdit.setText(sharedText);
	    }
	   
	    generatePP.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 PPRequestBuilder builder  = new PPRequestBuilder();
				 builder.setMerchantId(8931191912814068630l).setMerchantSiteId(128728).setUserToken(UserToken.AUTO).
				 setUserTokenId("Test_1133123826").setItemsDetails(new PPItemDetails("TestItem", 1.9)).
				 setTotalAmount(1.9).setCurrency("RUB").
				 
				 setSecretKey("sVSiBVq4iPvjZxwadiuaYxloP3cRua7hMkCuCnh4aIJUx4X01BUHqG6HLC1wpv8J");
				    
				 PPRequest request = builder.buildRequest();
				 
				 urlEdit.setText(request.getUrl() + request.toUrlEncodedString());
			}
		});
	    

	    generateWD.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 PPWithdrawalRequestBuilder builder  = new PPWithdrawalRequestBuilder();
				 builder.setMerchantId(8931191912814068630l).setMerchantSiteId(128728).setUserToken(UserToken.AUTO).setWithdrawalAmount(1.1).
				 setWithdrawalCurrency("RUB").setWithdrawalMinAmount(1).setWithdrawalMaxAmount(2).setUserTokenId("Test_1133123826").
				 setSecretKey("sVSiBVq4iPvjZxwadiuaYxloP3cRua7hMkCuCnh4aIJUx4X01BUHqG6HLC1wpv8J");
				    
				 PPRequest request = builder.buildRequest();
				 
				 urlEdit.setText(request.getUrl() + request.toUrlEncodedString());
			}
		});
	    
	    
		loadURLButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (urlEdit.getText() != null &&  urlEdit.getText().length() > 0 ) {
					String url = urlEdit.getText().toString();
					Intent i = new Intent(RequestActivity.this, SampleActivity.class);
					i.putExtra("redirect", redirectCheck.isChecked());
					i.putExtra("url", url);
					
					startActivityForResult(i, 1);
					
				} else {
					Toast.makeText(getApplicationContext(), "Please enter URl", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (data != null) {
			Toast.makeText(getApplicationContext(), "PPP status: " + data.getStringExtra("ppp_status"), Toast.LENGTH_LONG).show();
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
}
