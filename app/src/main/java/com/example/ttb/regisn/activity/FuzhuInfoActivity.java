package com.example.ttb.regisn.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.ttb.regisn.R;
import com.example.ttb.regisn.bean.BaseInfo;
import com.example.ttb.regisn.bean.InfoBean;
import com.example.ttb.regisn.util.FunctionHelper;
import com.example.ttb.regisn.util.OutPut;
import com.example.ttb.regisn.util.Utils;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.List;

public class FuzhuInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ButtonRectangle next;
    String type = null;
    Intent intent;
    private TextView m_Dushegnzinv,m_Foreign,m_Shangxiaxue,m_Sfsgxqjy;
    private View m_Masker;
    private OptionsPickerView  m_OptionMenu;
    private String m_Seldsznzk,m_Selxqjy,m_Selwy,m_Selsxx;
    private EditText m_Etphone,m_Etaddr,m_Etyb,m_Etbz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuzhu_info);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setCustomView(R.layout.layout_actionbar);
        TextView tvTitle = (TextView) actionBar.getCustomView().findViewById(R.id.title);
        tvTitle.setText("辅助信息");

        // TODO: 16/4/22 test
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

        type = getIntent().getStringExtra("type");

        BaseInfo baseInfo = (BaseInfo)getIntent().getSerializableExtra("baseinfo");

        // TODO: 16/4/17 delete
//        Toast.makeText(FuzhuInfoActivity.this,baseInfo.toString(),Toast.LENGTH_SHORT).show();



        initView();

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

    private void initView(){
        m_OptionMenu = new OptionsPickerView(this);
        m_Masker = (View)findViewById(R.id.vMasker);

        next = (ButtonRectangle)findViewById(R.id.next);
        m_Dushegnzinv = (TextView)findViewById(R.id.dushengzinv);
        m_Foreign = (TextView)findViewById(R.id.foreign);
        m_Shangxiaxue = (TextView)findViewById(R.id.shangxiaxue);
        m_Sfsgxqjy = (TextView)findViewById(R.id.shifoushouguoxqjy);

        m_Etphone = (EditText)findViewById(R.id.phone);
        m_Etaddr = (EditText)findViewById(R.id.addr);
        m_Etyb = (EditText)findViewById(R.id.yb);
        m_Etbz = (EditText)findViewById(R.id.bz);
    }
    private void setListner(){

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = FuzhuInfoActivity.this.getWindow().getDecorView();
                List<View> list = OutPut.setOutMap(OutPut.getAllChildViews(view1));
//                setSendMap();
                int i = setValue();
                if(i < 0)
                    return;
                if(type!=null &&type.equalsIgnoreCase("HJ")){
                    intent = new Intent(FuzhuInfoActivity.this,HujiRelationActivity.class);
                }else {
                    FunctionHelper.isHjchild = false;
                    intent = new Intent(FuzhuInfoActivity.this, HujidiActivity.class);
                }
                startActivity(intent);
            }
        });

        m_Dushegnzinv.setOnClickListener(this);
        m_Foreign.setOnClickListener(this);
        m_Shangxiaxue.setOnClickListener(this);
        m_Sfsgxqjy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dushengzinv:
                m_Seldsznzk =Utils.initPickViewData(m_Dushegnzinv,"请选择独生子女状况", m_OptionMenu, FunctionHelper.dushengzinvList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.foreign:
                m_Selwy = Utils.initPickViewData(m_Foreign,"请选择外语语种", m_OptionMenu, FunctionHelper.foreignList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.shangxiaxue:
                m_Selsxx = Utils.initPickViewData(m_Shangxiaxue,"请选择上下学方式", m_OptionMenu, FunctionHelper.shangxiaxueList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.shifoushouguoxqjy:
                m_Selxqjy = Utils.initPickViewData(m_Sfsgxqjy,"", m_OptionMenu, FunctionHelper.shifoushouguoxqjy,m_Masker);
                m_OptionMenu.show();
                break;
        }
    }
    private int setValue(){
        StringBuffer tmpsb = new StringBuffer();
        String a = m_Dushegnzinv.getText().toString();
        if(m_Dushegnzinv.getText().length()<=0||m_Dushegnzinv.getText().equals("－请选择－")) {
            Toast.makeText(FuzhuInfoActivity.this,"请选择独生子女状况",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("ddlOnlyChildType＝"+m_Dushegnzinv.getText()+"&");
        m_Seldsznzk = Utils.getCode(m_Dushegnzinv.getText().toString(),FunctionHelper.dushengzinvList);
        tmpsb.append("ddlOnlyChildTypeCode="+m_Seldsznzk+"&");

        if(m_Sfsgxqjy.getText().length()<=0||m_Sfsgxqjy.getText().equals("－请选择－")) {
            Toast.makeText(FuzhuInfoActivity.this,"请选择是否受过学前教育",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("ddlIfPreStudy＝"+m_Sfsgxqjy.getText()+"&");
        m_Selxqjy = Utils.getCode(m_Sfsgxqjy.getText().toString(),FunctionHelper.shifoushouguoxqjy);
        tmpsb.append("ddlIfPreStudyCode="+m_Selxqjy+"&");

        if(m_Foreign.getText().length()<=0||m_Foreign.getText().equals("－请选择－")) {
            Toast.makeText(FuzhuInfoActivity.this,"请选择外语语种",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("ddlForeignLanguage="+m_Foreign.getText()+"&");
        m_Selwy = Utils.getCode(m_Foreign.getText().toString(),FunctionHelper.foreignList);
        tmpsb.append("ddlForeignLanguageCode="+m_Selwy+"&");

        if(m_Shangxiaxue.getText().length()<=0||m_Shangxiaxue.getText().equals("－请选择－")) {
            Toast.makeText(FuzhuInfoActivity.this,"请选择上下学方式",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("ddlTransType＝"+m_Shangxiaxue.getText()+"&");
        m_Selsxx = Utils.getCode(m_Shangxiaxue.getText().toString(),FunctionHelper.shangxiaxueList);
        tmpsb.append("ddlTransTypeCode="+m_Selsxx+"&");

        tmpsb.append("tbxStuPhone＝"+m_Etphone.getText()+"&");
        tmpsb.append("tbxCommAddress="+m_Etaddr.getText()+"&");
        tmpsb.append("tbxZipCode="+m_Etyb.getText()+"&");
        tmpsb.append("taRemark="+m_Etbz.getText()+"&");
        FunctionHelper.sendSB.append(tmpsb);
        return 0;
    }
    private void setSendMap(){
        ViewGroup layout = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_fuzhu_info, null);
        Utils.getKeyValueByView(layout);
    }
}
