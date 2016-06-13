package com.example.ttb.regisn.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.ttb.regisn.R;
import com.example.ttb.regisn.bean.InfoBean;
import com.example.ttb.regisn.util.FunctionHelper;
import com.example.ttb.regisn.util.OutPut;
import com.example.ttb.regisn.util.Utils;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.List;

public class HomeOneActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout yyzz,sbkh,gzxz;
    ButtonRectangle next;
    private View m_Masker;
    private RadioButton wg,gt;
    private OptionsPickerView m_OptionMenu;
    private TextView m_Yuertongguanxi,m_Sex,m_Guobie,m_Wenhuachengdu,m_Zhengzhimianmao,m_Shifoujianhuaren;
    RadioGroup m_Gongzuoxingzhi;
    private EditText m_Etxingming,m_Etsfzhaoma,m_Etlianxidizhi,m_Etlianxidianhua,m_Etyoubian,m_Etzhiwu,m_Gonguodanwei;
    private String m_Selyuertonguanxi,m_Selsex,m_Selguobie,m_Selwenhuachengdu,m_Selzhengzhimianmao,m_Selgongzuodanwei,m_Selshifoujianhuaren,m_Selgongzuoxingzhi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_one_info);

        initView();

        if(FunctionHelper.isModify){
//            Utils.setString2InMap(FunctionHelper.testStr);
            View view1 = this.getWindow().getDecorView();
            //获取所有的tag控件 并赋值
            Utils.setValue2TagKJInParentView(view1);
            wg = (RadioButton)findViewById(R.id.wg1);
            gt = (RadioButton)findViewById(R.id.gt1);
            if(FunctionHelper.isHjchild){
                sbkh.setVisibility(View.GONE);
                yyzz.setVisibility(View.GONE);
            }else{
                if(FunctionHelper.isShowWG1){
                    wg.setChecked(true);
                    sbkh.setVisibility(View.VISIBLE);
                    yyzz.setVisibility(View.GONE);
                }else{
                    gt.setChecked(true);
                    sbkh.setVisibility(View.GONE);
                    yyzz.setVisibility(View.VISIBLE);
                }
            }

        }else {
            //加载tempmap中的数据
            if(FunctionHelper.tempMap.size() > 0) {
                View view2 = this.getWindow().getDecorView();
                Utils.setValue2TagKJInParentViewByTempMap(view2);
            }
        }

        FunctionHelper.gongzuoxingzhi.add(new InfoBean("0","务工",""));
        FunctionHelper.gongzuoxingzhi.add(new InfoBean("1","个体",""));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setCustomView(R.layout.layout_actionbar);
        TextView tvTitle = (TextView) actionBar.getCustomView().findViewById(R.id.title);
        tvTitle.setText("家庭成员一");




        setListner();

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
            case R.id.yuertongguanxi1:
                m_Selyuertonguanxi = Utils.initPickViewData(m_Yuertongguanxi,"请选择民族", m_OptionMenu, FunctionHelper.yuertongguanxi,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.sex1:
                m_Selsex = Utils.initPickViewData(m_Sex,"请选择民族", m_OptionMenu, FunctionHelper.sexList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.guobie1:
                m_Selyuertonguanxi = Utils.initPickViewData(m_Guobie,"请选择民族", m_OptionMenu, FunctionHelper.guobie,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.wenhuachengdu1:
                m_Selyuertonguanxi = Utils.initPickViewData(m_Wenhuachengdu,"请选择民族", m_OptionMenu, FunctionHelper.wenhuachengdu,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.zhengzhimianmao1:
                m_Selyuertonguanxi = Utils.initPickViewData(m_Zhengzhimianmao,"请选择民族", m_OptionMenu, FunctionHelper.zhengzhimianmao,m_Masker);
                m_OptionMenu.show();
                break;
//            case R.id.gongzuodanwei1:
//                m_Selyuertonguanxi = Utils.initPickViewData(m_Gonguodanwei,"请选择民族", m_OptionMenu, FunctionHelper.gongzuoxingzhi,m_Masker);
//                m_OptionMenu.show();
//                break;
            case R.id.shifoujianhuren1:
                m_Selshifoujianhuaren = Utils.initPickViewData(m_Shifoujianhuaren,"请选择民族", m_OptionMenu, FunctionHelper.shifoujianhuaren,m_Masker);
                m_OptionMenu.show();
                break;
//            case R.id.gongzuoxingzhi1:
//                m_Selgongzuoxingzhi = Utils.initPickViewData(m_Gongzuoxingzhi,"请选择民族", m_OptionMenu, FunctionHelper.gongzuoxingzhi,m_Masker);
//                m_OptionMenu.show();
//                break;
        }
    }
    private void initView(){
        next = (ButtonRectangle)findViewById(R.id.next);
        m_OptionMenu = new OptionsPickerView(this);
        m_Masker = (View)findViewById(R.id.vMasker);

        m_Yuertongguanxi = (TextView)findViewById(R.id.yuertongguanxi1);
        m_Sex=(TextView)findViewById(R.id.sex1);
        m_Guobie=(TextView)findViewById(R.id.guobie1);
        m_Wenhuachengdu=(TextView)findViewById(R.id.wenhuachengdu1);
        m_Zhengzhimianmao=(TextView)findViewById(R.id.zhengzhimianmao1);
        m_Gonguodanwei=(EditText)findViewById(R.id.gongzuodanwei1);
//        m_Gonguodanwei.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(b){
//                    if(m_Gongzuoxingzhi.getText().toString().equalsIgnoreCase("个体")){
//                        sbkh.setVisibility(View.GONE);
//                        yyzz.setVisibility(View.VISIBLE);
//                    }else{
//                        sbkh.setVisibility(View.VISIBLE);
//                        yyzz.setVisibility(View.GONE);
//                    }
//                }else{
//                    if(m_Gongzuoxingzhi.getText().toString().equalsIgnoreCase("个体")){
//                        sbkh.setVisibility(View.GONE);
//                        yyzz.setVisibility(View.VISIBLE);
//                    }else{
//                        sbkh.setVisibility(View.VISIBLE);
//                        yyzz.setVisibility(View.GONE);
//                    }
//                }
//            }
//        });
        m_Shifoujianhuaren =(TextView)findViewById(R.id.shifoujianhuren1);
        m_Shifoujianhuaren.setText("是-y");
//        m_Gonguodanwei.setText("务工");
        m_Gongzuoxingzhi = (RadioGroup)findViewById(R.id.gongzuoxingzhi1);
        m_Gongzuoxingzhi.setOnClickListener(this);
        m_Gongzuoxingzhi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)HomeOneActivity.this.findViewById(radioButtonId);
                if(rb.getText().toString().equalsIgnoreCase("务工")){
                    FunctionHelper.isTAG1 = "务工";
                    sbkh.setVisibility(View.VISIBLE);
                    yyzz.setVisibility(View.GONE);
                }else{
                    FunctionHelper.isTAG1 = "个体";
                    sbkh.setVisibility(View.GONE);
                    yyzz.setVisibility(View.VISIBLE);
                }

            }
        });

//        m_Gongzuoxingzhi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(!b){
//                    if(m_Gongzuoxingzhi.getText().toString().equalsIgnoreCase("务工")) {
//                        yyzz.setVisibility(View.GONE);
//                        sbkh.setVisibility(View.VISIBLE);
//                    }
//                    else{
//                        yyzz.setVisibility(View.VISIBLE);
//                        sbkh.setVisibility(View.GONE);
//                    }
//                }
//            }
//        });


        m_Etxingming=(EditText)findViewById(R.id.name1);
        m_Etsfzhaoma=(EditText)findViewById(R.id.shengfenzhenghaoma1);
        m_Etlianxidizhi=(EditText)findViewById(R.id.lianxidizhi1);
        m_Etlianxidianhua=(EditText)findViewById(R.id.lianxidianhu1);
        m_Etyoubian=(EditText)findViewById(R.id.youzhengbianma1);
        m_Etzhiwu=(EditText)findViewById(R.id.zhiwu1);

        yyzz = (LinearLayout)findViewById(R.id.yyzz1);
        sbkh = (LinearLayout)findViewById(R.id.sbkh1);
        gzxz = (LinearLayout)findViewById(R.id.gzxz1);
        //if(FunctionHelper.isHjchild){

          //  gzxz.setVisibility(View.GONE);

       // }
        if(FunctionHelper.isHjchild)
            gzxz.setVisibility(View.GONE);
        sbkh.setVisibility(View.GONE);
        yyzz.setVisibility(View.GONE);

    }
    private void setListner(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = HomeOneActivity.this.getWindow().getDecorView();
                List<View> list = OutPut.setOutMap(OutPut.getAllChildViews(view1));
                int i=setValue();
                if(i<0)
                    return;
                Intent intent = new Intent(HomeOneActivity.this,HomeTwoActivity.class);
                startActivity(intent);
            }
        });

        m_Yuertongguanxi.setOnClickListener(this);
        m_Sex.setOnClickListener(this);
        m_Guobie.setOnClickListener(this);
        m_Wenhuachengdu.setOnClickListener(this);
        m_Zhengzhimianmao.setOnClickListener(this);
        m_Gonguodanwei.setOnClickListener(this);
        m_Shifoujianhuaren.setOnClickListener(this);
        m_Gongzuoxingzhi.setOnClickListener(this);
    }
    private int setValue(){
        StringBuffer tmpsb = new StringBuffer();
        if(m_Yuertongguanxi.getText().length()<=0||m_Yuertongguanxi.getText().equals("－请选择－")) {
            Toast.makeText(HomeOneActivity.this,"请选择与儿童关系",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("ddlRelation1="+m_Yuertongguanxi.getText()+"&");
        m_Selyuertonguanxi = Utils.getCode(m_Yuertongguanxi.getText().toString(),FunctionHelper.yuertongguanxi);
        tmpsb.append("ddlRelation1Code="+m_Selyuertonguanxi+"&");

        if(m_Etxingming.getText().length()<=0||m_Etxingming.getText().equals("－请选择－")) {
            Toast.makeText(HomeOneActivity.this,"请填写姓名",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("tbxName1="+m_Etxingming.getText()+"&");


        if(m_Sex.getText().length()<=0||m_Sex.getText().equals("－请选择－")) {
            Toast.makeText(HomeOneActivity.this,"请选择性别",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("ddlSex1="+m_Sex.getText()+"&");
        m_Selsex = Utils.getCode(m_Sex.getText().toString(),FunctionHelper.sexList);
        tmpsb.append("ddlSex1Code="+m_Selsex+"&");

        tmpsb.append("ddlNation1="+m_Guobie.getText()+"&");
        m_Selguobie = Utils.getCode(m_Guobie.getText().toString(),FunctionHelper.guobie);
        tmpsb.append("ddlNation1Code="+m_Selguobie+"&");

        if(m_Shifoujianhuaren.getText().length()<=0||m_Shifoujianhuaren.getText().equals("－请选择－")) {
            Toast.makeText(HomeOneActivity.this,"请选择是否监护人",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("ddlIfGuardian1="+m_Shifoujianhuaren.getText()+"&");
        m_Selshifoujianhuaren = Utils.getCode(m_Shifoujianhuaren.getText().toString(),FunctionHelper.shifoujianhuaren);
        tmpsb.append("ddlIfGuardian1Code="+m_Selshifoujianhuaren+"&");

        tmpsb.append("tbxIDCard1="+m_Etsfzhaoma.getText()+"&");

        tmpsb.append("tbxAddress1="+m_Etlianxidizhi.getText()+"&");

        tmpsb.append("ddlStudy1="+m_Wenhuachengdu.getText()+"&");
        m_Selwenhuachengdu = Utils.getCode(m_Wenhuachengdu.getText().toString(),FunctionHelper.wenhuachengdu);
        tmpsb.append("ddlStudy1Code="+m_Selwenhuachengdu+"&");

        tmpsb.append("ddlPolitical1="+m_Zhengzhimianmao.getText()+"&");
        m_Selzhengzhimianmao = Utils.getCode(m_Zhengzhimianmao.getText().toString(),FunctionHelper.zhengzhimianmao);
        tmpsb.append("ddlPolitical1Code="+m_Selzhengzhimianmao+"&");

        tmpsb.append("tbxPhone1="+m_Etlianxidianhua.getText()+"&");
        tmpsb.append("tbxZipCode1="+m_Etyoubian.getText()+"&");

        tmpsb.append("ddlJobCategory1="+m_Gonguodanwei.getText()+"&");
        m_Selgongzuodanwei = Utils.getCode(m_Gonguodanwei.getText().toString(),FunctionHelper.gongzuoxingzhi);
        tmpsb.append("ddlJobCategory1Code="+m_Selgongzuodanwei+"&");

        FunctionHelper.sendSB.append(tmpsb);
        return 0;
    }
}
