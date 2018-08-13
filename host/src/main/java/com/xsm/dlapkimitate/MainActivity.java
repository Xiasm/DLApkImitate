package com.xsm.dlapkimitate;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xsm.dlapkimitate.plugin.DLManager;
import com.xsm.dlapkimitate.plugin.proxy.DLProxyActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();
        DLManager.getInstance().setContext(this);
        initView();
    }



    private void initView() {
        findViewById(R.id.btnLoadPlugin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DLManager.getInstance().loadPlugin(MainActivity.this, "plugin.apk", new DLManager.LoadPluginCallback() {
                    @Override
                    public void loadSuccess() {
                        Toast.makeText(MainActivity.this, "加载插件成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void loadError(String errorMsg) {
                        Toast.makeText(MainActivity.this, "加载插件失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        findViewById(R.id.btnToPluginA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DLProxyActivity.class);
                intent.putExtra("className", DLManager.getInstance().getPackageInfo().activities[0].name);
                startActivity(intent);
            }
        });
    }

    public void requestPermission() {
        //判断是否已经赋予权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true。
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {//这里可以写个对话框之类的项向用户解释为什么要申请权限，并在对话框的确认键后续再次申请权限
            } else {
                //申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }


}
