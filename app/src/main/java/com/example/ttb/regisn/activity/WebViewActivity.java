package com.example.ttb.regisn.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ttb.regisn.R;
import com.example.ttb.regisn.bean.BaseInfo;
import com.example.ttb.regisn.bean.InfoBean;
import com.example.ttb.regisn.util.FunctionHelper;
import com.example.ttb.regisn.util.IDReflect;
import com.example.ttb.regisn.util.JsonUtil;
import com.example.ttb.regisn.util.MyServerAsynTask;
import com.example.ttb.regisn.util.PCAAsynTask;
import com.example.ttb.regisn.util.ServerAsynTask;
import com.example.ttb.regisn.util.ServerCitiesAsynTask;
import com.example.ttb.regisn.util.ServerJiedaoAsynTask;
import com.example.ttb.regisn.util.ServerProvinceAsynTask;
import com.example.ttb.regisn.util.ServerQDCountiesAsynTask;
import com.example.ttb.regisn.util.Utils;
import com.gc.materialdesign.views.ButtonRectangle;

import org.json.JSONObject;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WebViewActivity extends AppCompatActivity {

    private TextView webView;
    private Button in,out;

    private ServiceConnection sc = new MyServiceConnection();
    private BackService.MyBinder mBinder ;
    private BackService mService;
    private boolean mBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        getSupportActionBar().hide();

        new ServerAsynTask().execute(WebViewActivity.this);

        Intent service = new Intent(WebViewActivity.this,BackService.class);
        bindService(service,sc, Context.BIND_AUTO_CREATE);

        webView = (TextView)findViewById(R.id.webview);

        Spanned text = Html.fromHtml(FunctionHelper.WEB_STR);
        webView.setText(text);

        in = (Button)findViewById(R.id.in);
        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WebViewActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        out = (Button)findViewById(R.id.out);
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FunctionHelper.tempMap.clear();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        FunctionHelper.tempMap.clear();
        super.onBackPressed();
    }

    private class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinder = (BackService.MyBinder)iBinder;
            mService = mBinder.getService();

            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    }
}
