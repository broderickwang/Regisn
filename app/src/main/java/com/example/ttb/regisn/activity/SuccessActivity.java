package com.example.ttb.regisn.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ttb.regisn.R;
import com.example.ttb.regisn.util.FHJInfoShowAsynTask;
import com.example.ttb.regisn.util.FunctionHelper;
import com.example.ttb.regisn.util.HJInfoShowAsynTask;
import com.example.ttb.regisn.util.ServerAsynTask;

import java.util.concurrent.ExecutionException;

public class SuccessActivity extends AppCompatActivity {
    private TextView a1,name,info,title;
    private Button modify;
    boolean result;

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

        try {
//            result = (boolean)new AT1().execute().get();
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
        }






        initView();

        showInfo();

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
                new ServerAsynTask().execute();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_modify:
                try {
                    boolean result = (boolean)new AT1().execute().get();
                    //if(result){
                        Intent intent = new Intent(SuccessActivity.this, BaseInfoHJActivity.class);
                        FunctionHelper.isModify = true;
                        new ServerAsynTask().execute();
                        startActivity(intent);
//                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
