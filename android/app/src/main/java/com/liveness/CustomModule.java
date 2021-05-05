package com.liveness;

import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import org.json.JSONObject;

public class CustomModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;

    CustomModule(ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }

    @ReactMethod
    public  void loadSdk() {
        ReactApplicationContext context = getReactApplicationContext();
        Intent intent = new Intent(context, LivenessDemoActivity.class);
        context.startActivity(intent);
    }

    @NonNull
    @Override
    public String getName() {
        return "CustomModule";
    }
}