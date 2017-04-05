package com.example.ttb.regisn.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ttb.regisn.R;
import com.example.ttb.regisn.util.FunctionHelper;
import com.example.ttb.regisn.util.JsonUtil;
import com.example.ttb.regisn.util.Utils;
import com.gc.materialdesign.views.ButtonRectangle;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.CookieStore;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends Activity implements View.OnClickListener{

    private Button login;
    private EditText uname,upwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getSupportActionBar().hide();
//        getActionBar().hide();
        login = (Button)findViewById(R.id.login);
        uname = (EditText)findViewById(R.id.uname);
        upwd = (EditText)findViewById(R.id.pswd) ;

        login.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                Object[] session = {uname.getText().toString(),upwd.getText().toString()};
                try {
//                    boolean result = (boolean)new LoginTask().execute(session).get();
                    boolean result = (boolean)new LoginTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,session).get();
                    if(result) {
                        Intent intent = new Intent(LoginActivity.this, SuccessActivity.class);
                        FunctionHelper.isModify = true;
                        FunctionHelper.stuCardNo = uname.getText().toString();
                        FunctionHelper.stuPWD = upwd.getText().toString();
                        startActivity(intent);
                    }else{
                        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        Utils.showDialog(LoginActivity.this,"登录失败！\n"+"请检查用户名和密码");

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
class LoginTask extends AsyncTask{
    boolean bl = false;
    @Override
    protected Object doInBackground(Object[] objects) {
        HttpClient hc = new DefaultHttpClient();
        HttpPost hp = new HttpPost(FunctionHelper.URL_CS +
                "?action=Login&IDCard="+objects[0]+"&pwd="+objects[1]);

        try {
            HttpResponse hr = hc.execute(hp);

            String result = null;
            //获取报文
            if(hr.getStatusLine().getStatusCode() == 200){

                result = EntityUtils.toString(hr.getEntity());

                JSONObject jo = new JSONObject(result);
                String a = jo.getString("StuID");
                FunctionHelper.stuID = a;

                bl = true;
            }
            //关闭连接
            if(hc != null){
                hc.getConnectionManager().shutdown();
            }
            return bl;
        }catch (Exception e){
            e.printStackTrace();
            return bl;
        }
    }
}

//获取stumodel的线程,并赋值到inmap中
class AT1 extends AsyncTask{
    boolean bl = false;
    @Override
    protected Object doInBackground(Object[] objects) {
        HttpClient hc = new DefaultHttpClient();

        try {

            HttpGet httpGet = new HttpGet(FunctionHelper.URL_CS + "?action=GetStuModel&StuID="+FunctionHelper.stuID);

            HttpResponse hr = hc.execute(httpGet);

            String result = null;
            //获取报文
            if(hr.getStatusLine().getStatusCode() == 200){
                result = EntityUtils.toString(hr.getEntity());
                Log.i("result = ====",result);
                JSONObject jo = new JSONObject(result);
                String a = jo.getString("HouseholdType");
                if(a.equalsIgnoreCase("户籍"))
                    FunctionHelper.isHjchild = true;
                else
                    FunctionHelper.isHjchild = false;
                if(result != null){
                    Utils.setString2InMap(result);
                    bl = true;
                }else{
                    bl = false;
                }
            }
            //关闭连接
            if(hc != null){
                hc.getConnectionManager().shutdown();
            }
            return bl;
        }catch (Exception e){
            e.printStackTrace();
            return bl;
        }
    }
}
