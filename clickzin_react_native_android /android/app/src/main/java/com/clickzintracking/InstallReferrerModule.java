package com.clickzintracking;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class InstallReferrerModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "InstallReferrer";
    private InstallReferrerClient mReferrerClient;

    public InstallReferrerModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void getInstallReferrer(final Promise promise) {
        mReferrerClient = InstallReferrerClient.newBuilder(getReactApplicationContext()).build();
        mReferrerClient.startConnection(new InstallReferrerStateListener() {
            @Override
            public void onInstallReferrerSetupFinished(int responseCode) {
                switch (responseCode) {
                    case InstallReferrerClient.InstallReferrerResponse.OK:
                        try {
                            ReferrerDetails response = mReferrerClient.getInstallReferrer();
                            String installReferrer = response.getInstallReferrer();
                            // Mocking: 
                            //String installReferrer="utm_source=clickzin&utm_medium=test&utm_term=testing&utm_content=content&utm_campaign=12&utm_uid=asdfasdfisadf&utm_network=231&utm_refer=asdfasdf";
                            long referrerClickTimestamp = response.getReferrerClickTimestampSeconds();
                            long installBeginTimestamp = response.getInstallBeginTimestampSeconds();
                            mReferrerClient.endConnection();

                            promise.resolve(installReferrer + "," + referrerClickTimestamp + "," + installBeginTimestamp);
                        } catch (RemoteException e) {
                            promise.reject("FAILED", e.getMessage());
                        }
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                        promise.reject("FEATURE_NOT_SUPPORTED", "API not available on the current device.");
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                        promise.reject("SERVICE_UNAVAILABLE", "Connection could not be established.");
                        break;
                }
            }

            @Override
            public void onInstallReferrerServiceDisconnected() {
                // Handle the error
            }
        });
    }
}
