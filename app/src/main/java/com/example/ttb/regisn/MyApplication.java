package com.example.ttb.regisn;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;

import com.example.ttb.regisn.activity.BackService;
import com.example.ttb.regisn.activity.WebViewActivity;
import com.example.ttb.regisn.util.PCAAsynTask;

/**
 * Created by Broderick on 2017/4/1.
 */

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		new PCAAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}
}
