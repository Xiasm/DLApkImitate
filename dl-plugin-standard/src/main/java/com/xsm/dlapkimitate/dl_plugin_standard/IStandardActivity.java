package com.xsm.dlapkimitate.dl_plugin_standard;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * Author: 夏胜明
 * Date: 2018/8/13 0013
 * Email: xiasem@163.com
 * Description:
 */
public interface IStandardActivity {

    public void attach(Activity proxyActivity);
    public void onCreate(Bundle savedInstanceState);
    public void onStart();
    public void onResume();
    public void onPause();
    public void onStop();
    public void onDestroy();
    public void onSaveInstanceState(Bundle outState);
    public boolean onTouchEvent(MotionEvent event);
    public void onBackPressed();

}
