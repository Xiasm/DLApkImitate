package com.xsm.dlapkimitate.plugin.proxy;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.xsm.dlapkimitate.R;
import com.xsm.dlapkimitate.dl_plugin_standard.IStandardActivity;
import com.xsm.dlapkimitate.plugin.DLManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DLProxyActivity extends Activity {

    private String className;
    private IStandardActivity standardActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        className = getIntent().getStringExtra("className");

        try {
            Class<?> activityClass = getClassLoader().loadClass(className);
            Constructor<?> activityClassConstructor = activityClass.getConstructor(new Class[]{});
            Object instance = activityClassConstructor.newInstance(new Object[]{});
            standardActivity = (IStandardActivity) instance;
            standardActivity.attach(this);
            Bundle bundle = new Bundle();
            standardActivity.onCreate(bundle);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        standardActivity.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        standardActivity.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        standardActivity.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        standardActivity.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        standardActivity.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        standardActivity.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        standardActivity.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");
        Intent proxyIntent = new Intent(this, DLProxyActivity.class);
        proxyIntent.putExtra("className", className);
        super.startActivity(proxyIntent);
    }

    @Override
    public ClassLoader getClassLoader() {
        return DLManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return DLManager.getInstance().getResources();
    }


}
