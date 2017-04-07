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
import com.example.ttb.regisn.R;
import com.example.ttb.regisn.bean.InfoBean;
import com.example.ttb.regisn.util.FunctionHelper;
import com.example.ttb.regisn.util.JsonUtil;
import com.example.ttb.regisn.util.ServerAsynTask;
import com.example.ttb.regisn.util.ServerCitiesAsynTask;
import com.example.ttb.regisn.util.ServerJiedaoAsynTask;
import com.example.ttb.regisn.util.ServerProvinceAsynTask;
import com.example.ttb.regisn.util.ServerQDCountiesAsynTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.security.PublicKey;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebViewActivity extends AppCompatActivity {

    private TextView webView;
    private Button in,out;
    public static String getMsg = "";

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

        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(FunctionHelper.URL_CS + "?action=GetDictionaryAll")
                .build();
        mOkHttpClient.newCall(request).enqueue(
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        getMsg = response.body().string();
                        new ABC(getMsg).run();
                    }
                }
        );



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

    class  ABC extends Thread{
        private String str;

        public ABC(String str) {
            this.str = str;
        }

        @Override
        public void run() {
            super.run();
            JSONArray ja1 = null;
            try {
                ja1 = new JSONArray(getMsg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonUtil.JsonInfoParser(ja1);
        }
    }
}
