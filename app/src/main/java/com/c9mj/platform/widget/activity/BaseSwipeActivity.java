package com.c9mj.platform.widget.activity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.c9mj.platform.R;
import com.c9mj.platform.util.ToastUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * Created by Administrator on 2016/11/15.
 */

public class BaseSwipeActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getWindow();
//            // Translucent status bar
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }

        //6.0权限申请
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions
                    .request(
                            Manifest.permission.CAMERA,
                            Manifest.permission.INTERNET,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(granted -> {
                        if (granted) {
                            //所有权限被允许
                        } else {
                            //至少一个权限被拒绝
                            ToastUtil.show(getString(R.string.error_grant));
                        }
                    });
        }

    }
}
