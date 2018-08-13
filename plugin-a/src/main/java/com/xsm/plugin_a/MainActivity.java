package com.xsm.plugin_a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xsm.plugin_a.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
