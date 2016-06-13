package com.example.ttb.regisn.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.example.ttb.regisn.R;
import com.example.ttb.regisn.util.FunctionHelper;
import com.example.ttb.regisn.util.OutPut;
import com.example.ttb.regisn.util.Utils;
import com.gc.materialdesign.views.ButtonRectangle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HouseRelationActivity extends AppCompatActivity implements View.OnClickListener{

    private ButtonRectangle next;
    private View m_Masker;
    private OptionsPickerView m_OptionMenu;
    private TimePickerView m_TimePicker;
    private TextView m_Time,m_Suoyou2et;
    private EditText m_Etnum,m_Etchanquanren,m_Etsfnum, m_Etchuan2huzhu;

    private String m_Seltime,m_Selsuoyou2et;

    private void initTimeData(){
        m_TimePicker = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
//        m_TimePicker.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));
        m_TimePicker.setTime(new Date());
        m_TimePicker.setCyclic(false);
        m_TimePicker.setCancelable(true);
        //时间选择后回调
        m_TimePicker.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                m_Time.setText(getTime(date));
            }
        });

    }
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_relation);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setCustomView(R.layout.layout_actionbar);
        TextView tvTitle = (TextView) actionBar.getCustomView().findViewById(R.id.title);
        tvTitle.setText("房屋关系");

        if(FunctionHelper.isModify){
//            Utils.setString2InMap(FunctionHelper.testStr);
            View view1 = this.getWindow().getDecorView();
            //获取所有的tag控件 并赋值
            Utils.setValue2TagKJInParentView(view1);
        }else {
            //加载tempmap中的数据
            if(FunctionHelper.tempMap.size() > 0) {
                View view2 = this.getWindow().getDecorView();
                Utils.setValue2TagKJInParentViewByTempMap(view2);
            }
        }

        initView();

        setListner();
        initTimeData();



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
            case R.id.hrtime:
                m_TimePicker.show();
                break;
            case R.id.suoyou2ertong:
                m_Selsuoyou2et = Utils.initPickViewData(m_Suoyou2et,"请选择民族", m_OptionMenu, FunctionHelper.fangchanowner2childList,m_Masker);
                m_OptionMenu.show();
                break;
        }
    }
    private void initView(){
        m_OptionMenu = new OptionsPickerView(this);
        m_Masker = (View)findViewById(R.id.vMasker);
        next = (ButtonRectangle)findViewById(R.id.next);

        m_Time=(TextView)findViewById(R.id.hrtime);
        m_Time.setText("2007-01-01");
        m_Suoyou2et=(TextView)findViewById(R.id.suoyou2ertong);

        m_Etnum=(EditText)findViewById(R.id.bianhao);
        m_Etchanquanren=(EditText)findViewById(R.id.chanquanren);
        m_Etchuan2huzhu =(EditText)findViewById(R.id.suoyou2huzhu);
        m_Etsfnum=(EditText)findViewById(R.id.chanquanrennum);
    }
    private void setListner(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = HouseRelationActivity.this.getWindow().getDecorView();
                List<View> list = OutPut.setOutMap(OutPut.getAllChildViews(view1));
                int i= setValue();
                if(i<0)
                    return;
                Intent intent = new Intent(HouseRelationActivity.this,HomeOneActivity.class);
                startActivity(intent);
            }
        });

//        m_Time.setOnClickListener(this);
        m_Suoyou2et.setOnClickListener(this);
    }
    private int setValue(){
        
        StringBuffer tmpsb = new StringBuffer();

//        if(m_Etnum.getText().length()<=0||m_Etnum.getText().equals("－请选择－")) {
//            Toast.makeText(HouseRelationActivity.this,"请填写房产证编号",Toast.LENGTH_SHORT).show();
//            return -1;
//        }
        tmpsb.append("tbxCqrFangChanZhenHao="+m_Etnum.getText()+"&");
//        String[] as = m_Time.getText().toString().split("-");
//        if(as.length<=1)
//            return -1;
//        tmpsb.append("tbxCqrFangChanZhenTime_Y="+as[0]+"&");
//        tmpsb.append("ddlCqrFangChanZhenTime_M="+as[1]+"&");
//        tmpsb.append("ddlCqrFangChanZhenTime_D="+as[2]+"&");

//        if(m_Etchanquanren.getText().length()<=0||m_Etchanquanren.getText().equals("－请选择－")) {
//            Toast.makeText(HouseRelationActivity.this,"请填写产权人/承租人",Toast.LENGTH_SHORT).show();
//            return -1;
//        }
        tmpsb.append("tbxCqrName="+m_Etchanquanren.getText()+"&");
//        if(m_Etsfnum.getText().length()<=0||m_Etsfnum.getText().equals("－请选择－")) {
//            Toast.makeText(HouseRelationActivity.this,"请填写产权人身份证号",Toast.LENGTH_SHORT).show();
//            return -1;
//        }
        tmpsb.append("tbxCqrIDCard="+m_Etsfnum.getText()+"&");
//        if(m_Etchuan2huzhu.getText().length()<=0||m_Etchuan2huzhu.getText().equals("－请选择－")) {
//            Toast.makeText(HouseRelationActivity.this,"请填写所有人与户主关系",Toast.LENGTH_SHORT).show();
//            return -1;
//        }
        tmpsb.append("tbxCqrHuZhuGuanXi="+ m_Etchuan2huzhu.getText()+"&");

//        if(m_Suoyou2et.getText().length()<=0||m_Suoyou2et.getText().equals("－请选择－")) {
//            Toast.makeText(HouseRelationActivity.this,"请选择所有人与儿童关系",Toast.LENGTH_SHORT).show();
//            return -1;
//        }
        tmpsb.append("ddlCqrStuGuanxi="+m_Suoyou2et.getText()+"&");
        m_Selsuoyou2et = Utils.getCode(m_Suoyou2et.getText().toString(),FunctionHelper.fangchanowner2childList);
        tmpsb.append("ddlCqrStuGuanxiCode="+m_Selsuoyou2et+"&");

        FunctionHelper.sendSB.append(tmpsb);
        return 0 ;
    }
}
