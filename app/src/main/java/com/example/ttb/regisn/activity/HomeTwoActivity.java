package com.example.ttb.regisn.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.ttb.regisn.R;
import com.example.ttb.regisn.util.FunctionHelper;
import com.example.ttb.regisn.util.OutPut;
import com.example.ttb.regisn.util.ServerInsertAsynTask;
import com.example.ttb.regisn.util.UpdateAsynTask;
import com.example.ttb.regisn.util.Utils;
import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.widgets.ProgressDialog;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeTwoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button commit,jump;
    private LinearLayout yyzz,sbkh,gzxz;
    private View m_Masker;
    private RadioButton wg,gt;
    private OptionsPickerView m_OptionMenu;
    private TextView m_Yuertongguanxi,m_Sex,m_Guobie,m_Wenhuachengdu,m_Zhengzhimianmao,m_Shifoujianhuaren;
    private EditText m_Etxingming,m_Etsfzhaoma,m_Etlianxidizhi,m_Etlianxidianhua,m_Etyoubian,m_Etzhiwu,m_Gonguodanwei;
    private String m_Selyuertonguanxi,m_Selsex,m_Selguobie,m_Selwenhuachengdu,m_Selzhengzhimianmao,m_Selgongzuodanwei,m_Selshifoujianhuaren;
    private boolean result = false;
    RadioGroup m_Gongzuoxingzhi;

    private Intent intent;
    private KProgressHUD mKProgressHUD;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mKProgressHUD.dismiss();
            switch (msg.what){
                case 1:
                    Toast.makeText(HomeTwoActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                    intent = new Intent(HomeTwoActivity.this,SuccessActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    Utils.showDialog(HomeTwoActivity.this,FunctionHelper.errorMsg);
                    Toast.makeText(HomeTwoActivity.this,"修改失败！",Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(HomeTwoActivity.this, "信息采集成功！", Toast.LENGTH_SHORT).show();
                    intent = new Intent(HomeTwoActivity.this,SuccessActivity.class);
                    startActivity(intent);
                    break;
                case 4:
                    Utils.showDialog(HomeTwoActivity.this,FunctionHelper.errorMsg);
                    Toast.makeText(HomeTwoActivity.this,"信息采集失败！",Toast.LENGTH_SHORT).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_two_info);

        initView();

        if(FunctionHelper.isModify){
//            Utils.setString2InMap(FunctionHelper.testStr);
            View view1 = this.getWindow().getDecorView();
            //获取所有的tag控件 并赋值
            Utils.setValue2TagKJInParentView(view1);

            wg = (RadioButton)findViewById(R.id.wg2);
            gt = (RadioButton)findViewById(R.id.gt2);
            if(FunctionHelper.isHjchild){
                sbkh.setVisibility(View.GONE);
                yyzz.setVisibility(View.GONE);
            }else{
                if(!m_Gonguodanwei.getText().equals("")) {
                    if (FunctionHelper.isShowWG2) {
                        wg.setChecked(true);
                        sbkh.setVisibility(View.VISIBLE);
                        yyzz.setVisibility(View.GONE);
                    } else {
                        gt.setChecked(true);
                        sbkh.setVisibility(View.GONE);
                        yyzz.setVisibility(View.VISIBLE);
                    }
                }
            }
        }else {
            //加载tempmap中的数据
            if(FunctionHelper.tempMap.size() > 0) {
                View view2 = this.getWindow().getDecorView();
                Utils.setValue2TagKJInParentViewByTempMap(view2);
            }
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setCustomView(R.layout.layout_actionbar);
        TextView tvTitle = (TextView) actionBar.getCustomView().findViewById(R.id.title);
        tvTitle.setText("家庭成员二");



        setListner();

    }
    private void initView(){
        commit = (Button)findViewById(R.id.commit);
        jump = (Button)findViewById(R.id.jump);

        m_OptionMenu = new OptionsPickerView(this);
        m_Masker = (View)findViewById(R.id.vMasker);

        m_Yuertongguanxi = (TextView)findViewById(R.id.yuertongguanxi2);
        m_Sex=(TextView)findViewById(R.id.sex2);
        m_Guobie=(TextView)findViewById(R.id.guobie2);
        m_Wenhuachengdu=(TextView)findViewById(R.id.wenhuachengdu2);
        m_Zhengzhimianmao=(TextView)findViewById(R.id.zhengzhimianmao2);
        m_Gonguodanwei=(EditText)findViewById(R.id.gongzuodanwei2);
        m_Shifoujianhuaren =(TextView)findViewById(R.id.shifoujianhuren2);


        m_Etxingming=(EditText)findViewById(R.id.name2);
        m_Etsfzhaoma=(EditText)findViewById(R.id.shengfenzhenghaoma2);
        m_Etlianxidizhi=(EditText)findViewById(R.id.lianxidizhi2);
        m_Etlianxidianhua=(EditText)findViewById(R.id.lianxidianhu2);
        m_Etyoubian=(EditText)findViewById(R.id.youzhengbianma2);
        m_Etzhiwu=(EditText)findViewById(R.id.zhiwu2);

        m_Gongzuoxingzhi = (RadioGroup)findViewById(R.id.gongzuoxingzhi2);
        m_Gongzuoxingzhi.setOnClickListener(this);
        m_Gongzuoxingzhi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)HomeTwoActivity.this.findViewById(radioButtonId);
                if(rb.getText().toString().equalsIgnoreCase("务工")){
                    FunctionHelper.isTAG2 = "务工";
                    sbkh.setVisibility(View.VISIBLE);
                    yyzz.setVisibility(View.GONE);
                }else{
                    FunctionHelper.isTAG2 = "个体";
                    sbkh.setVisibility(View.GONE);
                    yyzz.setVisibility(View.VISIBLE);
                }

            }
        });

        yyzz = (LinearLayout)findViewById(R.id.yyzz2);
        sbkh = (LinearLayout)findViewById(R.id.sbkh2);
        gzxz = (LinearLayout)findViewById(R.id.gzxz2);
        if(FunctionHelper.isHjchild){

          gzxz.setVisibility(View.GONE);

         }
        sbkh.setVisibility(View.GONE);
        yyzz.setVisibility(View.GONE);

        mKProgressHUD = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("请稍候")
                .setDetailsLabel("正在提交数据...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);
    }
    private void setListner(){
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mKProgressHUD.show();
                try {
                    View view1 = HomeTwoActivity.this.getWindow().getDecorView();
                    List<View> list = OutPut.setOutMap(OutPut.getAllChildViews(view1));
                    new CommitThread().start();
                    /*if (FunctionHelper.isModify){
                        result = (boolean)new UpdateAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
                        if(result) {
                            mKProgressHUD.dismiss();
                            Toast.makeText(HomeTwoActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                            intent = new Intent(HomeTwoActivity.this,SuccessActivity.class);
                            startActivity(intent);
                        }else{
                            mKProgressHUD.dismiss();
                            Utils.showDialog(HomeTwoActivity.this,FunctionHelper.errorMsg);
                            Toast.makeText(HomeTwoActivity.this,"修改失败！",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        result = (boolean)new ServerInsertAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,HomeTwoActivity.this).get();
                        if(result){
                            mKProgressHUD.dismiss();
                            Toast.makeText(HomeTwoActivity.this, "信息采集成功！", Toast.LENGTH_SHORT).show();
                            intent = new Intent(HomeTwoActivity.this,SuccessActivity.class);
                            startActivity(intent);
                        }else{
                            mKProgressHUD.dismiss();
                            Utils.showDialog(HomeTwoActivity.this,FunctionHelper.errorMsg);
                            Toast.makeText(HomeTwoActivity.this,"信息采集失败！",Toast.LENGTH_SHORT).show();
                        }
                    }*/
                }catch (Exception e){
                    Log.e("error---->",e.getMessage());
                }
            }
        });
        jump.setOnClickListener(this);

        m_Yuertongguanxi.setOnClickListener(this);
        m_Sex.setOnClickListener(this);
        m_Guobie.setOnClickListener(this);
        m_Wenhuachengdu.setOnClickListener(this);
        m_Zhengzhimianmao.setOnClickListener(this);
        m_Gonguodanwei.setOnClickListener(this);
        m_Shifoujianhuaren.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                View viewtemp = this.getWindow().getDecorView();
                OutPut.setTempMap(OutPut.getAllChildViews(viewtemp));
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        View viewtemp = this.getWindow().getDecorView();
        OutPut.setTempMap(OutPut.getAllChildViews(viewtemp));
        OutPut.setInMap(OutPut.getAllChildViews(viewtemp));
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.yuertongguanxi2:
                m_Selyuertonguanxi = Utils.initPickViewData(m_Yuertongguanxi,"请选择民族", m_OptionMenu, FunctionHelper.yuertongguanxi,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.sex2:
                m_Selsex = Utils.initPickViewData(m_Sex,"请选择民族", m_OptionMenu, FunctionHelper.sexList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.guobie2:
                m_Selyuertonguanxi = Utils.initPickViewData(m_Guobie,"请选择民族", m_OptionMenu, FunctionHelper.guobie,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.wenhuachengdu2:
                m_Selyuertonguanxi = Utils.initPickViewData(m_Wenhuachengdu,"请选择民族", m_OptionMenu, FunctionHelper.wenhuachengdu,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.zhengzhimianmao2:
                m_Selyuertonguanxi = Utils.initPickViewData(m_Zhengzhimianmao,"请选择民族", m_OptionMenu, FunctionHelper.zhengzhimianmao,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.gongzuodanwei2:
                m_Selyuertonguanxi = Utils.initPickViewData(m_Gonguodanwei,"请选择民族", m_OptionMenu, FunctionHelper.gongzuoxingzhi,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.shifoujianhuren2:
                m_Selshifoujianhuaren = Utils.initPickViewData(m_Shifoujianhuaren,"请选择民族", m_OptionMenu, FunctionHelper.shifoujianhuaren,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.jump:
                try {
                    View view1 = this.getWindow().getDecorView();
                    List<View> list = OutPut.setOutMap(OutPut.getAllChildViews(view1));
                    if (FunctionHelper.isModify){
                        result = (boolean)new UpdateAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
                        if(result) {
                            Toast.makeText(HomeTwoActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                            intent = new Intent(HomeTwoActivity.this,SuccessActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Utils.showDialog(HomeTwoActivity.this,FunctionHelper.errorMsg);
                            Toast.makeText(HomeTwoActivity.this,"修改失败！",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        result = (boolean)new ServerInsertAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,HomeTwoActivity.this).get();
                        if(result){
                            Toast.makeText(HomeTwoActivity.this, "信息采集成功！", Toast.LENGTH_SHORT).show();
                            intent = new Intent(HomeTwoActivity.this,SuccessActivity.class);
                            startActivity(intent);
                        }else{
                            Utils.showDialog(HomeTwoActivity.this,FunctionHelper.errorMsg);
                            Toast.makeText(HomeTwoActivity.this,"信息采集失败！",Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception e){
                    Log.e("error---->",e.getMessage());
                }
                break;
        }
    }

    class CommitThread extends Thread{
        OkHttpClient client = new OkHttpClient.Builder().build();
        String url;

        @Override
        public void run() {
            super.run();
            /*try{
                if (FunctionHelper.isModify){
                    if(FunctionHelper.isHjchild){
                        url = FunctionHelper.URL_CS+"?action=UpdateHuJiStu";
                    }else{
                        url = FunctionHelper.URL_CS+"?action=UpdateFeiHuJiStu";
                    }
                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            mHandler.sendEmptyMessage(2);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            mHandler.sendEmptyMessage(1);
                        }
                    });
                }
                else {
                    if(FunctionHelper.isHjchild){
                        url = FunctionHelper.URL_CS+"?action=InsertHuJiStu";

                    }else{
                        url = FunctionHelper.URL_CS+"?action=InsertFeiHuJiStu";
                    }
                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            mHandler.sendEmptyMessage(4);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            mHandler.sendEmptyMessage(3);
                        }
                    });
                }
            }catch (Exception e){

            }*/
            try{
                if (FunctionHelper.isModify){
                    result = (boolean)new UpdateAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
                    if(result) {
                        mHandler.sendEmptyMessage(1);
                    }else{
                        mHandler.sendEmptyMessage(2);
                    }
                }
                else {
                    result = (boolean)new ServerInsertAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,HomeTwoActivity.this).get();
                    if(result){
                        mHandler.sendEmptyMessage(3);
                    }else{
                        mHandler.sendEmptyMessage(4);
                    }
                }
            }catch (Exception e){

            }


        }
    }
    class ModifyThread extends Thread{
        @Override
        public void run() {
            super.run();
        }
    }
}
