package com.example.ttb.regisn.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

    private Button next;
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
        next = (Button)findViewById(R.id.next);

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
                Intent intent = new Intent(HouseRelationActivity.this,HomeOneActivity.class);
                startActivity(intent);
            }
        });

//        m_Time.setOnClickListener(this);
        m_Suoyou2et.setOnClickListener(this);
    }
}
