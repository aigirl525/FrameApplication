package com.example.sqliteapplication.mvp.view.base;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqliteapplication.Util.PermissionsUtil;


public abstract class BaseActivity extends AppCompatActivity implements PermissionsUtil.PermissionResult{

    protected static final int REQUEST_PERMISSION_CODE = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionsUtil.requestPermissions(this, REQUEST_PERMISSION_CODE, this);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void hasAllPermissions() {

    }

    @Override
    public void notHasAllPermissions() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_PERMISSION_CODE) {
            if(grantResults.length > 0) {
                boolean hasAllPermissions = true;
                for(int result : grantResults) {
                    if(result != PackageManager.PERMISSION_GRANTED) {
                        hasAllPermissions = false;
                        break;
                    }
                }
                if(!hasAllPermissions) {
                }
            } else {
            }
        }
    }
}
