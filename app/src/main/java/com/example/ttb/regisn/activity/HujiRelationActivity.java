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
import com.example.ttb.regisn.bean.InfoBean;
import com.example.ttb.regisn.util.FunctionHelper;
import com.example.ttb.regisn.util.OutPut;
import com.example.ttb.regisn.util.Utils;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.List;

public class HujiRelationActivity extends AppCompatActivity implements View.OnClickListener {

    private ButtonRectangle next;
    private View m_Masker;
    private OptionsPickerView m_OptionMenu;
    private TimePickerView m_TimePicker;
    private TextView m_Hukouxingzhi,m_Liudongrenkou,m_Hujiditype,m_Hujiintime,m_Owner2child;
    private String m_Selhkxz,m_Selldrk,m_Selert2hz,m_Selcqlx;
    private EditText m_Ethzxm,m_Etqt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huji_relation);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setCustomView(R.layout.layout_actionbar);
        TextView tvTitle = (TextView) actionBar.getCustomView().findViewById(R.id.title);
        tvTitle.setText("户籍关系");

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

    }

    private void initView(){
        m_OptionMenu = new OptionsPickerView(this);
        m_Masker = (View)findViewById(R.id.vMasker);

        next = (ButtonRectangle) findViewById(R.id.next);

        m_Hukouxingzhi = (TextView)findViewById(R.id.hjgxhukouxingzhi);

        m_Liudongrenkou = (TextView)findViewById(R.id.hjgxliudongrenkou);

        m_Hujiditype = (TextView)findViewById(R.id.hjgxhujiditype);

        m_Hujiintime = (TextView)findViewById(R.id.hjgxhujiintime);
        m_Hujiintime.setText("2007-01-01");

        m_Owner2child = (TextView)findViewById(R.id.hjgxowner2child);

        m_Ethzxm = (EditText)findViewById(R.id.hjgxhzxm);

        m_Etqt = (EditText)findViewById(R.id.hjgxqt);

    }
    private void setListner(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = HujiRelationActivity.this.getWindow().getDecorView();
                List<View> list = OutPut.setOutMap(OutPut.getAllChildViews(view1));
                int i = setValue();
                if(i<0)
                    return;
                Intent intent = new Intent(HujiRelationActivity.this,HujiAddrActivity.class);
                startActivity(intent);
            }
        });

        m_Hukouxingzhi.setOnClickListener(this);
        m_Liudongrenkou.setOnClickListener(this);
        m_Hujiditype.setOnClickListener(this);
        m_Hujiintime.setOnClickListener(this);
        m_Owner2child.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.hjgxhukouxingzhi:
                m_Selhkxz = Utils.initPickViewData(m_Hukouxingzhi,"请选择户口性质", m_OptionMenu, FunctionHelper.hukouxingzhiList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.hjgxliudongrenkou:
                m_Selldrk = Utils.initPickViewData(m_Liudongrenkou,"请选择流动人口状况", m_OptionMenu, FunctionHelper.liudongrenkouList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.hjgxhujiditype:
                m_Selcqlx = Utils.initPickViewData(m_Hujiditype,"请选择户籍地产权类型", m_OptionMenu, FunctionHelper.hujidichanquantypeList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.hjgxhujiintime:
                m_TimePicker = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
                Utils.initTimePickViewData(m_TimePicker,m_Hujiintime);
                m_TimePicker.show();
                break;
            case R.id.hjgxowner2child:
                m_Selert2hz = Utils.initPickViewData(m_Owner2child,"请选择关系", m_OptionMenu, FunctionHelper.ertong2huzhu,m_Masker);
                m_OptionMenu.show();
                break;
        }
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

    private int setValue(){
        
        StringBuffer tmpsb = new StringBuffer();
        
        if(m_Hukouxingzhi.getText().length()<=0||m_Hukouxingzhi.getText().equals("－请选择－")) {
            Toast.makeText(HujiRelationActivity.this,"请选择户口性质",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("ddlRegPlaceType="+m_Hukouxingzhi.getText()+"&");
        m_Selhkxz = Utils.getCode(m_Hukouxingzhi.getText().toString(),FunctionHelper.hukouxingzhiList);
        tmpsb.append("ddlRegPlaceTypeCode"+m_Selhkxz+"&");

        if(m_Liudongrenkou.getText().length()<=0||m_Liudongrenkou.getText().equals("－请选择－")) {
            Toast.makeText(HujiRelationActivity.this,"请选择流动人口状况",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("ddlPeopleType="+m_Liudongrenkou.getText()+"&");
        m_Selldrk = Utils.getCode(m_Liudongrenkou.getText().toString(),FunctionHelper.liudongrenkouList);
        tmpsb.append("ddlPeopleTypeCode"+m_Selldrk+"&");

        if(m_Ethzxm.getText().length()<=0||m_Ethzxm.getText().equals("－请选择－")) {
            Toast.makeText(HujiRelationActivity.this,"请填写户主姓名",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("tbxHuZhuName="+m_Ethzxm.getText()+"&");

        if(m_Etqt.getText().equals(""))
            tmpsb.append("ddlHuZhuGuanXi="+m_Owner2child.getText()+"&");
        else
            tmpsb.append("ddlHuZhuGuanXi="+m_Etqt.getText()+"&");

        String[] times = m_Hujiintime.getText().toString().split("-");

        tmpsb.append("tbxHuJiQianRuTime_Y"+times[0]+"&");
        tmpsb.append("ddlHuJiQianRuTime_M"+times[1]+"&");
        tmpsb.append("ddlHuJiQianRuTime_D"+times[2]+"&");

        if(m_Hujiditype.getText().length()<=0||m_Hujiditype.getText().equals("－请选择－")) {
            Toast.makeText(HujiRelationActivity.this,"请选择户籍地产权类型",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("ddlHuJiDiChanQuanType="+m_Hujiditype.getText()+"&");
        m_Selcqlx = Utils.getCode(m_Hujiditype.getText().toString(),FunctionHelper.hujidichanquantypeList);
        tmpsb.append("ddlHuJiDiChanQuanTypeCode="+m_Selcqlx+"&");

        FunctionHelper.sendSB.append(tmpsb);

        return 0;
    }
}
