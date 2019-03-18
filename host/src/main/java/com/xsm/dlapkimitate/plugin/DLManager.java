package com.xsm.dlapkimitate.plugin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

import static android.content.ContentValues.TAG;

/**
 * Author: 夏胜明
 * Date: 2018/8/13 0013
 * Email: xiasem@163.com
 * Description:
 */
public class DLManager {
    private static final String TAG = "DLManager";
    private static DLManager instance;
    private PackageInfo packageInfo;
    private DexClassLoader dexClassLoader;
    private Resources resources;

    public static DLManager getInstance() {
        if (instance == null) {
            synchronized (DLManager.class) {
                if (instance == null) {
                    instance = new DLManager();
                }
            }
        }
        return instance;
    }

    private DLManager () {

    }

    public void loadPlugin(Context context, String pluginName) {
        loadPlugin(context, pluginName, null);
    }

    public void loadPlugin(Context context, String pluginName, LoadPluginCallback callback) {
        reloadPath(context, pluginName);

        File dir = context.getDir("plugin", Context.MODE_PRIVATE);
        String path = new File(dir, pluginName).getAbsolutePath();
        // get packageInfo
        PackageManager packageManager = context.getPackageManager();
        packageInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        // get dexClassLoader
        File pluginDex = context.getDir("dex", Context.MODE_PRIVATE);
        dexClassLoader = new DexClassLoader(path, pluginDex.getAbsolutePath(), null, context.getClassLoader());

        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath=AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, path);
            resources = new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
            Log.d(TAG, "loadPlugin: 加载插件" + pluginName + "成功！");
            if (callback != null) {
                callback.loadSuccess();
            }
        } catch (InstantiationException e) {
            if (callback != null) {
                callback.loadError(e.getMessage());
            }
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            if (callback != null) {
                callback.loadError(e.getMessage());
            }
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            if (callback != null) {
                callback.loadError("可能权限不存在或没有这个文件！");
            }
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            if (callback != null) {
                callback.loadError(e.getMessage());
            }
            e.printStackTrace();
        }
    }
    /**
     * 将手机内存卡中的apk复制到app目录
     * @param context
     * @param pluginName apk name
     */
    private void reloadPath(Context context, String pluginName) {
        File filesDir = context.getDir("plugin", Context.MODE_PRIVATE);
        String filePath = new File(filesDir, pluginName).getAbsolutePath();
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        InputStream is = null;
        FileOutputStream os = null;
        try {
            is = new FileInputStream(new File(Environment.getExternalStorageDirectory(), pluginName));
            os = new FileOutputStream(filePath);
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            File f = new File(filePath);
            if (f.exists()) {
                Log.d(TAG, "拷贝成功");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public Resources getResources() {
        return resources;
    }

    public interface LoadPluginCallback {
        void loadSuccess();
        void loadError(String errorMsg);
    }
}
