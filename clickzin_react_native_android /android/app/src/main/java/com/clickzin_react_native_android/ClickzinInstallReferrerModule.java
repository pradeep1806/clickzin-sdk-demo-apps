package com.clickzin_react_native_android;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import android.util.Log;

public class ClickzinInstallReferrerModule extends ReactContextBaseJavaModule {

    private static final String TAG = "ClickzinInstallReferrerModule";
    private InstallReferrerClient referrerClient;

    public ClickzinInstallReferrerModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ClickzinInstallReferrerModule";
    }

    @ReactMethod
    public void getInstallReferrer(final Callback callback) {
        Log.d("ClickzinInstallReferrerModule"," getInstallReferrer ");

        referrerClient = InstallReferrerClient.newBuilder(getReactApplicationContext()).build();
        referrerClient.startConnection(new InstallReferrerStateListener() {
            @Override
            public void onInstallReferrerSetupFinished(int responseCode) {
                switch (responseCode) {
                    case InstallReferrerClient.InstallReferrerResponse.OK:
                        try {
                            ReferrerDetails response = referrerClient.getInstallReferrer();
                            //String referrerUrl = response.getInstallReferrer();
                            // Mocking: 
                            String referrerUrl="utm_source=clickzin&utm_medium=test&utm_term=testing&utm_content=content&utm_campaign=12&utm_uid=asdfasdfisadf&utm_network=231&utm_refer=asdfasdf";

                            Log.d("Clickzin"," InstallReferrerModule referrerUrl " + referrerUrl);
                            double clickTimestamp = (double) response.getReferrerClickTimestampSeconds();
                            double installTimestamp = (double) response.getInstallBeginTimestampSeconds();
                            Log.d("Clickzin"," InstallReferrerModule clickTimestamp " + clickTimestamp);
                            Log.d("Clickzin"," InstallReferrerModule installTimestamp " + installTimestamp);

                            callback.invoke(null, referrerUrl, clickTimestamp, installTimestamp);
                        } catch (Exception e) {
                            Log.d("Clickzin"," InstallReferrerModule Exception " + e);
                            callback.invoke(e.getMessage(),null,null,null);
                        }
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                        callback.invoke("FEATURE_NOT_SUPPORTED");
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                        callback.invoke("SERVICE_UNAVAILABLE");
                        break;
                    default:
                        callback.invoke("Unknown response code: " + responseCode);
                        break;
                }
                referrerClient.endConnection();
            }

            @Override
            public void onInstallReferrerServiceDisconnected() {
                callback.invoke("Service disconnected");
            }
        });
    }
}