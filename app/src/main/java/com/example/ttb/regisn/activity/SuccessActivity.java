package com.example.ttb.regisn.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ttb.regisn.R;
import com.example.ttb.regisn.util.FHJInfoShowAsynTask;
import com.example.ttb.regisn.util.FunctionHelper;
import com.example.ttb.regisn.util.HJInfoShowAsynTask;
import com.example.ttb.regisn.util.ServerAsynTask;
import com.example.ttb.regisn.util.Utils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SuccessActivity extends AppCompatActivity {
    private TextView a1,name,info,title;
    private Button modify;
    boolean result;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    showInfo();
                    break;
                case 2:
                    Toast.makeText(SuccessActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setCustomView(R.layout.layout_actionbar);
        TextView tvTitle = (TextView) actionBar.getCustomView().findViewById(R.id.title);
        tvTitle.setText("个人信息");

        new AThread().start();
       /* try {
            result = (boolean)new AT1().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
            if (result) {
                if (FunctionHelper.isHjchild) {
                    //如果是户籍儿童调用HJInfoShowAsynTask
                    result = (boolean) new HJInfoShowAsynTask().execute().get();

                } else {
                    //如果是非户籍调用FHJInfoShowAsynTask
                    result = (boolean) new FHJInfoShowAsynTask().execute().get();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/


        initView();

        //showInfo();

    }

    private void initView(){
        a1 = (TextView)findViewById(R.id.a1);
        name = (TextView)findViewById(R.id.name);
        modify = (Button)findViewById(R.id.modify);
        info = (TextView)findViewById(R.id.infosus);
        title = (TextView)findViewById(R.id.title);

        ////
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent ;
                if(FunctionHelper.isHjchild)
                    intent= new Intent(SuccessActivity.this, BaseInfoHJActivity.class);
                else
                    intent= new Intent(SuccessActivity.this, BaseInfoFHJActivity.class);
                FunctionHelper.isModify = true;
//                new ServerAsynTask().execute();
                startActivity(intent);
            }
        });
    }

    private void showInfo(){
        if(FunctionHelper.isHjchild){
            title.setText("2017年市北区户籍适龄入学儿童小学入学报名提示单");
        }else{
            title.setText("2017年市北区非户籍适龄入学儿童小学入学报名提示单");
        }


        String b = "信息采集系统登陆账号(适龄入学儿童身份证号): "+FunctionHelper.stuCardNo+"\n"+
                "登录密码:"+FunctionHelper.stuPWD;
        info.setText(b);

        String c = "报名时间地点提示：请携带以下材料（原件、复印件）于"+FunctionHelper.stuTime+"到 "+FunctionHelper.stuSchool+" 验证材料";
        name.setText(c);


        String a = "报名所需材料提示：\n" +
                "1.劳动和社会保障局提供的劳动合同正规文本、青岛市就业登记表及社保卡，或者是工商营业执照及缴税证明。\n" +
                "2.青岛市居住证。\n" +
                "3.大青岛范围持户口薄。\n" +
                "4.房产证或青岛市房屋租赁登记备案证明。\n" +
                "5.学生及其父母在原籍户口簿。\n" +
                "6.适龄儿童父母身份证。\n" +
                "7.出生证。\n" +
                "8.预防接种证。\n" +
                "9.健康证明。\n" +
                "10.幼儿园评估手册。\n" +
                "友情提示：如果您需修改资料，请在2017年4月28日至2017年5月27日，登陆本系统首页“修改资料”模块，使用登录帐号（适龄入学儿童身份证号码）、登录密码（系统自动生成的或您修改的），进行修改。请确保您填写的信息真实合法有效，不实信息不作为报名依据。";

        a1.setText(a);
    }

    class AThread extends Thread{
        OkHttpClient client = new OkHttpClient.Builder().build();
        @Override
        public void run() {
            Request request = new Request.Builder()
                    .url(FunctionHelper.URL_CS + "?action=GetStuModel&StuID="+FunctionHelper.stuID)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    mHandler.sendEmptyMessage(2);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result = response.body().string();
                    try {
                        JSONObject jo = new JSONObject(result);
                        String a = jo.getString("HouseholdType");
                        if(a.equalsIgnoreCase("户籍")){
                            Request request1 = new Request.Builder()
                                    .url(FunctionHelper.URL_CS+"?action=GetHuJiStuSort&StuID="
                                            +FunctionHelper.stuID)
                                    .build();
                            Response response1 = client.newCall(request1).execute();

                            JSONObject jo1 = new JSONObject(response1.body().string());
                            FunctionHelper.stuSchool = jo1.getString("SchoolName");
                            FunctionHelper.stuTime = jo1.getString("STime");
                            FunctionHelper.isHjchild = true;
                        } else {
                            Request request2 = new Request.Builder()
                                    .url(FunctionHelper.URL_CS+"?action=GetFeiHuJiStuSort&StuID="
                                            +FunctionHelper.stuID)
                                    .build();
                            Response response2 = client.newCall(request2).execute();

                            JSONObject jo2 = new JSONObject(response2.body().string());
                            FunctionHelper.stuSchool = jo2.getString("SchoolName");
                            FunctionHelper.stuTime = jo2.getString("STime");
                            FunctionHelper.isHjchild = false;
                        }

                        mHandler.sendEmptyMessage(1);

                    } catch (Exception e) {
                        e.printStackTrace();
                        mHandler.sendEmptyMessage(2);
                    }

                    if(result!=null){
                        Utils.setString2InMap(result);
                    }

                }
            });
        }
    }
}