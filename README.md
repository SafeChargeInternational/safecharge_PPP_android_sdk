# safecharge_PPP_android_sdk

# Introduction
The Cashier SDK provides developers an easy and straightforward way to implement Cashier in your Android and iOS applications. The Cashier SDK generates Request URLs to the Cashier payment page from which your customers can make deposits or the withdrawal page from which your customers can generate withdrawal requests within the customer's mobile device. From these pages, your customers can complete their transaction using all supported payment methods. This guide provides code samples of the Cashier SDK and an explanation of how to implement the SDK into your application.


# # PPRequestBuilder: The Request Builder exposes properties that correspond to the parameters which can be passed to the payment page listed in the Cashier Integration guide.
The main role of the PPRequestBuilder is to create a PPRequest with the correct set of parameters and will ensure that all of the required parameters are provided. The PPRequest is then passed to PPWebView, which displays the payment page.
# # PPWithdrawalRequestBuilder: Like the PPRequestBuilder, the Withdrawal Request Builder exposes properties that correspond to the parameters which can be passed to the withdrawal page listed in the Cashier Withdrawal API guide.
The main role of the PPWithdrawalRequestBuilder is to create a PPRequest with the correct set of parameters and will ensure that all of the required parameters are provided. The PPRequest is then passed to PPWebView, which displays the withdrawal page.
# # PPRequest: The PPRequest wraps the platform-specific native URL request to the payment page. This is the output of the PPRequestBuilder and must be passed to PPWebVIew to display the payment page in the mobile device.
# # PPItem: The PPItem represents a single item to be passed to the payment page. The PPItem includes Item-related parameters listed in the Cashier Integration guide. The item related parameters (like item_name_N, item_amount_N, etc.) are wrapped in PPItem. PPItems can be added to the Request Builder to create the item list.
If the customer wants to purchase several items, you can add several PPItems to the Request Builder.
# # PPWebView: PPWebVeiew requires the PPRequest, which is the output of the PPRequestBuilder as input to load the payment page.
After the payment flow is complet, the PPWebView triggers an event with the result of the transaction (PPResult). The event negates the requirement to configure the success_url and error_url. If however, you want to configure these URLs, then PPWebView will not trigger the PPResult.
# # PPResult – the result of the Cashier Payment Page. It wraps all output parameters listed in the Cashier Integration Guide.

public class DemoActivity extends Activity {
  private static final String SECRET_KEY = "SecretKey";
  private PPPWebView webView;
  ...
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.demo_activity); super.onCreate(savedInstanceState);
    webView = (PPPWebView) findViewById(R.id.webview); PPRequest request;
    
    // you can choose between building withdraw request or payment request
    if(buildWithdrawalRequest) { 
            //Build Withdrawal request
            //Create builder
            PPWithdrawalRequestBuilder builder = new PPWithdrawalRequestBuilder();
            //set request 
            parameters builder.setMerchantId(merchantId)
            .setMerchantSiteId(128728)
            .setUserToken(UserToken.AUTO)
            .setWithdrawalAmount(1.1)
            .setWithdrawalCurrency("RUB") 
            .setWithdrawalMinAmount(1) 
            .setWithdrawalMaxAmount(2) 
            .setUserTokenId("Test_1133123826") 
            .setSecretKey(SECRET_KEY);
            request = builder.buildRequest(); 
       } else { // or build payment request
             //Build payment page request
             //Create builder
            PPPRequestBuilder builder = new PPPRequestBuilder();
            //set request parameters 
            builder.setMerchantId(merchantId) 
            .setMerchantSiteId(128728) 
            .setSecretKey(SECRET_KEY)
            .setItemsDetails(new RequestItemDetails("item1",1.23), new RequestItemDetails("item2", 1.23))
            .setTotalAmount(1.23)
            .setCurrency("RUB") 
            .setUserTokenId("Test_1133123826") 
            .setUserToken("auto") 
            .setItemOpenAmount1(true) 
            .setItemMinAmount1(1)
            .setItemMaxAmount1(100) 
            .setCountry("RU")
            .setPaymentMethod("cc_card"); 
            request = builder.buildRequest();
       }
       
      webView.load(request);
      
      // in order to observe the status of the payment or the whitdraw operation  
      // you will need ot set the following callbacks
      webView.setResultCallback(new IPPPResultCallback() {
        @Override
        public void onResult(PPPResult result) {
             //Handle pp result
        }
       }
       
      webView.setErrorCalback(new IErrorCallback() {
       @Override
       public void onReceivedError(int errorCode, String description, String failingUrl) {
                 //Handle error events
            }
      }
      
      
});
       
       
});
      
   }
}
