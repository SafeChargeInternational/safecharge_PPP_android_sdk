package com.safecharge.android.util;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

public class OkHttpDefaultClientBuilder {
    public static OkHttpClient getDefaultHttpClient() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = builder
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build();

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
