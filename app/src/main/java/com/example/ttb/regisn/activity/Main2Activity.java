package com.example.ttb.regisn.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.example.ttb.regisn.R;
import com.example.ttb.regisn.util.ServerAsynTask;

public class Main2Activity extends AppCompatActivity {
    private long m_dwSplashTime=3000;
    private boolean m_bPaused=false;
    private boolean m_bSplashActive=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashy);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new ServerAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                Intent intent = new Intent(Main2Activity.this,WebViewActivity.class/*MainActivity.class*/);  //从启动动画ui跳转到主ui
                startActivity(intent);
                Main2Activity.this.finish();    // 结束启动动画界面
            }
        }, 3000);    //启动动画持续3秒钟

        getSupportActionBar().hide();
    }
    @Override
    protected void onPause() {
        super.onPause();
        m_bPaused=true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        m_bPaused=false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        switch(keyCode){
            case KeyEvent.KEYCODE_MENU:
                m_bSplashActive=false;
                break;
            case KeyEvent.KEYCODE_BACK:
                /*两种退出方法*/
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
            default:
                break;
        }
        return true;
    }
}
