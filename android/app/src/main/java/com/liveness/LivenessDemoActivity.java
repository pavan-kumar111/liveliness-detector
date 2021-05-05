package com.liveness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.liveness.databinding.ActivityLivenessDemoBinding;
import com.liveness.klivenesslibrary.KLiveResult;
import com.liveness.klivenesslibrary.KLiveStatus;
import com.liveness.klivenesslibrary.callback.KLivenessCallbacks;
import com.liveness.klivenesslibrary.liveness.util.FaceStatus;
import com.liveness.klivenesslibrary.view.KLivenessView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class LivenessDemoActivity extends ReactActivity implements  KLivenessCallbacks, EasyPermissions.PermissionCallbacks  {

    AlertDialog.Builder builder = null;
    Dialog dialog = null;
    private Promise promise;

    private ActivityLivenessDemoBinding binding;
    private static final int PER_REQUEST_CODE = 201;
    private static final String karzaToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHBpcnlfdGltZSI6IjI4LTA0LTIwMjFUMTA6MDc6NDYiLCJ1c2VyX2lkIjozOTgxNzMsImNsaWVudF9pZCI6IkthcnphX1RlY2hfdFZqSXVaIiwiZW52IjoidGVzdCIsInJlcXVlc3RfaWQiOiIwNTM0NjJiNy1jMjU2LTQyMDEtYTMxZS00ZDdkM2Y5MDI3YTYiLCJzY29wZSI6WyJsaXZlbmVzcyJdLCJ1c2VyX3R5cGUiOiJvcGVuIiwic3RhZ2UiOiJ0ZXN0In0.N6TxLjRBoP07aWgClPYRpXbGpQiRxs7JDUbpFjzpBm4";

//    CustomModule customModule = new CustomModule(getApplicationContext());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_liveness_demo);



//    Intent intent = new Intent(getApplicationContext(), Leegality.class);
////    intent.putExtra("url", "signing-url-here");
//    intent.putExtra("url", "https://karzatechnologies.sandbox.leegality.com/sign/ad33ded8-46ea-4833-8907-311a92a66602");
//    startActivityForResult(intent, 121);

        binding.kLiveView.initialize(karzaToken
                ,getSupportFragmentManager(),LivenessDemoActivity.this,"test");

        // Initializes the Bridge
//    this.init(savedInstanceState, new ArrayList<Class<? extends Plugin>>() {{
//      // Additional plugins you've installed go here
//      // Ex: add(TotallyAwesomePlugin.class);
//
//      binding.kLiveView.initialize("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHBpcnlfdGltZSI6IjI3LTA0LTIwMjFUMTI6MDk6MTciLCJ1c2VyX2lkIjozOTgxNzMsImNsaWVudF9pZCI6IkthcnphX1RlY2hfdFZqSXVaIiwiZW52IjoidGVzdCIsInJlcXVlc3RfaWQiOiI5N2VhODhmZS02OTVhLTRkOGUtYWE4NS1jYWY2YzRiNGI1YmEiLCJzY29wZSI6WyJsaXZlbmVzcyJdLCJ1c2VyX3R5cGUiOiJvcGVuIiwic3RhZ2UiOiJ0ZXN0In0.gei4cyZbUraW2ijYPRB3O2wpQEQfXoBVcPuDFMesog0"
//        ,getSupportFragmentManager(),MainActivity.this,"test");
//    }});
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public void needPermissions(@NonNull String... perms) {
        EasyPermissions.requestPermissions(this, "This app needs access to your camera so you can take pictures.",
                PER_REQUEST_CODE, perms);
    }

    @Override
    public void showLoader() {


    }

    @Override
    public void hideLoader() {


    }

    @Override
    public void onReceiveKLiveResult(KLiveStatus kLiveStatus, @Nullable KLiveResult kLiveResult) {


//        JSONObject jsonObject = new JSONObject();
        try {
//            jsonObject.put("status", kLiveStatus.status);
//            jsonObject.put("statusCode", kLiveStatus.statusCode);
//            jsonObject.put("livenessScore", kLiveResult.getLivenessScore());
//            jsonObject.put("imageB64String", kLiveResult.getImageB64String());
//            jsonObject.put("requestId", kLiveResult.getRequestId());


            WritableMap map = Arguments.createMap();
            map.putString("status", kLiveStatus.status);
            map.putInt("statusCode", kLiveStatus.statusCode);
            map.putDouble("livenessScore", kLiveResult.getLivenessScore());
            map.putString("imageB64String", kLiveResult.getImageB64String());
            map.putString("requestId", kLiveResult.getRequestId());

            try {
                getReactInstanceManager().getCurrentReactContext()
                        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                        .emit("onDataAvailable", map);

            } catch (Exception e){
                Log.e("ReactNative", "Caught Exception: " + e.getMessage());
            }

//            callback.invoke(jsonObject);
            finish();
        }
        catch (Exception ex ){
            ex.printStackTrace();
        }
    }

    @Override
    public void onError(String message) {

//        JSONObject jsonObject = new JSONObject();
        try {
//            jsonObject.put("message", message);

            WritableMap map = Arguments.createMap();
            map.putString("message",message);

            try {
                getReactInstanceManager().getCurrentReactContext()
                        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                        .emit("onDataAvailable", map);

            } catch (Exception e){
                Log.e("ReactNative", "Caught Exception: " + e.getMessage());
            }


//            callback.invoke(jsonObject);
            finish();
        }
        catch (Exception ex ){
            ex.printStackTrace();
        }

    }

    @Override
    public void faceStatus(FaceStatus faceStatus) {

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if(perms.size()>=2){
            binding.kLiveView.initialize(karzaToken,getSupportFragmentManager(),LivenessDemoActivity.this,"test");
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}

