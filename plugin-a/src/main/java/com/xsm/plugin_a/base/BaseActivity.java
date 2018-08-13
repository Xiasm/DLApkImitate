package com.xsm.plugin_a.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.xsm.dlapkimitate.dl_plugin_standard.IStandardActivity;

public class BaseActivity extends Activity implements IStandardActivity {
    protected Activity mProxy;

    @Override
    public void attach(Activity proxyActivity) {
        mProxy = proxyActivity;
    }

    @Override
    public void setContentView(View view) {
        if (mProxy != null) {
            mProxy.setContentView(view);
            return;
        }
        super.setContentView(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (mProxy != null) {
            mProxy.setContentView(layoutResID);
            return;
        }
        super.setContentView(layoutResID);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if (mProxy != null) {
            return mProxy.findViewById(id);
        }
        return super.findViewById(id);
    }

    @Override
    public Intent getIntent() {
        if (mProxy != null) {
            return mProxy.getIntent();
        }
        return super.getIntent();
    }

    @Override
    public ClassLoader getClassLoader() {
        if (mProxy != null) {
            return mProxy.getClassLoader();
        }
        return super.getClassLoader();
    }

    @Override
    public void startActivity(Intent intent) {
        if (mProxy != null) {
            Intent proxyIntent = new Intent();
            proxyIntent.putExtra("className", intent.getComponent().getClassName());
            mProxy.startActivity(proxyIntent);
            return;
        }
        super.startActivity(intent);
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        if (mProxy != null) {
            return mProxy.getLayoutInflater();
        }
        return super.getLayoutInflater();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        if (mProxy != null) {
            return mProxy.getApplicationInfo();
        }
        return super.getApplicationInfo();
    }

    @Override
    public Window getWindow() {
        if (mProxy != null) {
            return mProxy.getWindow();
        }
        return super.getWindow();
    }

    @Override
    public WindowManager getWindowManager() {
        if (mProxy != null) {
            return mProxy.getWindowManager();
        }
        return super.getWindowManager();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (mProxy == null) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onStart() {
        if (mProxy == null) {
            super.onStart();
        }
    }

    @Override
    public void onResume() {
        if (mProxy == null) {
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if (mProxy == null) {
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if (mProxy == null) {
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (mProxy == null) {
            super.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mProxy == null) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onBackPressed() {
        if (mProxy != null) {
            mProxy.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
}
