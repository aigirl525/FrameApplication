package com.example.sqliteapplication.Util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

public class PermissionsUtil {

    public interface PermissionResult {
        void hasAllPermissions();
        void notHasAllPermissions();
    }

    public static void requestPermissions(Activity activity, int code, PermissionResult permissionResult) {
        String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if(PermissionsUtil.permissionsGranted(permissions, activity)) {
            if(permissionResult != null) {
                permissionResult.hasAllPermissions();
            }
        } else {
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    activity, Manifest.permission.RECORD_AUDIO)) {
                if(permissionResult != null) {
                    permissionResult.notHasAllPermissions();
                }
            }
            else {
                ActivityCompat.requestPermissions(activity,
                        permissions,
                        code);
            }
        }
    }

    private static boolean permissionsGranted(String[] permissions, Context context) {
        for(String permission : permissions) {
            if(!selfPermissionGranted(permission, context)) {
                return false;
            }
        }
        return true;
    }

    private static boolean selfPermissionGranted (String permission, Context context) {
        // For Android < Android M, self permissions are always granted.
        boolean result = true;
        int targetSdkVersion = Build.VERSION_CODES.N;
        try {
            final PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            targetSdkVersion = info.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (targetSdkVersion >= Build.VERSION_CODES.M) {
                // targetSdkVersion >= Android M, we can
                // use Context#checkSelfPermission
                result = context.checkSelfPermission(permission)
                        == PackageManager.PERMISSION_GRANTED;
            } else {
                // targetSdkVersion < Android M, we have to use PermissionChecker
                result = PermissionChecker.checkSelfPermission(context, permission)
                        == PermissionChecker.PERMISSION_GRANTED;
            }
        }
        return result;
    }
}
