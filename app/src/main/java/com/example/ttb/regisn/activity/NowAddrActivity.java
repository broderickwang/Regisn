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

public class NowAddrActivity extends AppCompatActivity implements View.OnClickListener {

    private ButtonRectangle next;
    private String type;
    private LinearLayout houseFrom;
    private View m_Masker;
    private OptionsPickerView m_OptionMenu;
    private TextView m_Sheng,m_Shi,m_Xian,m_Jiedao,m_Fuhao,m_Fangwulaiyuan,m_Nowteshu;
    private EditText m_Etmenpaihao,m_Etlouhao,m_Etdanyuan,m_Ethuhaom;
    private String m_Selhjdisnow,m_Selsheng,m_Selshi,m_Selxian,m_Seljiedoa,m_Selfuhao,m_Selfangwulaiyuan;
    private LinearLayout teshulinear,putonglinear;
    private RadioGroup teshurg;
    private RadioButton nshi,nfou;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowadress);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setCustomView(R.layout.layout_actionbar);
        TextView tvTitle = (TextView) actionBar.getCustomView().findViewById(R.id.title);
        tvTitle.setText("现住址");

        initView();

        if(FunctionHelper.isModify){
            View view1 = this.getWindow().getDecorView();
            //获取所有的tag控件 并赋值
            Utils.setValue2TagKJInParentView(view1);

            if(!FunctionHelper.isTeshu){
                //if not teshuadddr
                nfou.setChecked(true);
                putonglinear.setVisibility(View.VISIBLE);
                teshulinear.setVisibility(View.GONE);
            }else{
                //if teshuaddr
                nshi.setChecked(true);
                putonglinear.setVisibility(View.GONE);
                teshulinear.setVisibility(View.VISIBLE);
            }
        }else {
            //加载tempmap中的数据
            if(FunctionHelper.tempMap.size() > 0) {
                View view2 = this.getWindow().getDecorView();
                Utils.setValue2TagKJInParentViewByTempMap(view2);
            }
            m_Sheng.setText("山东省");
            m_Shi.setText("青岛市");
            m_Xian.setText("市北区");
            m_Fuhao.setText("无");
        }


        Intent getIntent = getIntent();
        type = getIntent.getStringExtra("type");



        setListner();
        if(type!=null && type.equalsIgnoreCase("FHJ")){
            houseFrom.setVisibility(View.VISIBLE);
        }else{
            houseFrom.setVisibility(View.GONE);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nowsheng:
//                m_Selsheng = Utils.initPickViewData(m_Sheng,"", m_OptionMenu, FunctionHelper.provinces,m_Masker);
//                m_OptionMenu.show();
                break;
            case R.id.nowshi:
//                m_Selshi = Utils.initPickViewData(m_Shi,"", m_OptionMenu, FunctionHelper.city1,m_Masker);
//                m_OptionMenu.show();
                break;
            case R.id.nowxian:
//                m_Selshi = Utils.initPickViewData(m_Xian,"", m_OptionMenu, FunctionHelper.country_QD,m_Masker);
//                m_OptionMenu.show();
                break;
            case R.id.nowjiedao:
                m_Seljiedoa = Utils.initPickViewData(m_Jiedao,"", m_OptionMenu, FunctionHelper.road,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.nowfuhao:
                m_Selfuhao = Utils.initPickViewData(m_Fuhao,"", m_OptionMenu, FunctionHelper.fuhao,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.fangwulaiyuan:
                m_Selfangwulaiyuan = Utils.initPickViewData(m_Fangwulaiyuan,"", m_OptionMenu, FunctionHelper.fangwulaiyuan,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.nowteshu:
                m_Selfangwulaiyuan = Utils.initPickViewData(m_Nowteshu,"", m_OptionMenu, FunctionHelper.teshuaddrlist,m_Masker);
                m_OptionMenu.show();
                break;
        }
    }
    private void initView(){
        m_OptionMenu = new OptionsPickerView(this);
        m_Masker = (View)findViewById(R.id.vMasker);
        next = (ButtonRectangle) findViewById(R.id.next);
        houseFrom = (LinearLayout)findViewById(R.id.housefrom);

        m_Sheng = (TextView)findViewById(R.id.nowsheng);
        m_Sheng.setText("山东省");
        m_Shi = (TextView)findViewById(R.id.nowshi);
        m_Shi.setText("青岛市");
        m_Xian = (TextView)findViewById(R.id.nowxian);
        m_Xian.setText("市北区");
        m_Jiedao = (TextView)findViewById(R.id.nowjiedao);
        m_Fuhao = (TextView)findViewById(R.id.nowfuhao);
        m_Fuhao.setText("无");
        m_Fangwulaiyuan = (TextView)findViewById(R.id.fangwulaiyuan);
        m_Nowteshu = (TextView)findViewById(R.id.nowteshu);

        m_Etmenpaihao = (EditText)findViewById(R.id.nowmenpaihao);
        m_Etlouhao = (EditText)findViewById(R.id.nowlouhao);
        m_Etdanyuan = (EditText)findViewById(R.id.nowdanyuan);
        m_Ethuhaom = (EditText)findViewById(R.id.nowhuhao);

        teshulinear = (LinearLayout)findViewById(R.id.teshulinear);
        putonglinear = (LinearLayout)findViewById(R.id.putonglinear);

        nshi = (RadioButton)findViewById(R.id.nshi);
        nfou = (RadioButton)findViewById(R.id.nfou);

        teshurg = (RadioGroup)findViewById(R.id.nowteshurg);
        teshurg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)NowAddrActivity.this.findViewById(radioButtonId);
                if(rb.getText().toString().equalsIgnoreCase("是")){
                    teshulinear.setVisibility(View.VISIBLE);
                    putonglinear.setVisibility(View.GONE);
                    FunctionHelper.teshuaddr = "1";
                }else{
                    teshulinear.setVisibility(View.GONE);
                    putonglinear.setVisibility(View.VISIBLE);
                    FunctionHelper.teshuaddr = "0";
                }
            }
        });

    }
    private void setListner(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = NowAddrActivity.this.getWindow().getDecorView();
                List<View> list = OutPut.setOutMap(OutPut.getAllChildViews(view1));
                int i= setValue();
                if(i<0)
                    return;
                switch (teshurg.getId()){
                    case R.id.nshi:
                        break;
                    case R.id.nfou:
                        FunctionHelper.teshuaddr = "0";
                        m_Nowteshu.setText("");
                        break;
                }
                if(type!=null && type.equalsIgnoreCase("FHJ")){
                    intent = new Intent(NowAddrActivity.this,HomeOneActivity.class);
                }else{
                    intent = new Intent(NowAddrActivity.this,HouseRelationActivity.class);
                }
                startActivity(intent);
            }
        });

        m_Sheng.setOnClickListener(this);
        m_Shi.setOnClickListener(this);
        m_Xian.setOnClickListener(this);
        m_Jiedao.setOnClickListener(this);
        m_Fuhao.setOnClickListener(this);
        m_Fangwulaiyuan.setOnClickListener(this);
        m_Nowteshu.setOnClickListener(this);
    }
    private int setValue(){
        StringBuffer tmpsb = new StringBuffer();

        if(houseFrom.getVisibility() == View.VISIBLE){
            tmpsb.append("ddlFangWuLaiYuan＝"+m_Fangwulaiyuan.getText()+"&");
            m_Selfangwulaiyuan = Utils.getCode(m_Fangwulaiyuan.getText().toString(),FunctionHelper.fangwulaiyuan);
            tmpsb.append("ddlFangWuLaiYuanCode="+m_Selfangwulaiyuan+"&");
        }

        tmpsb.append("ddlStuAddressP="+m_Sheng.getText()+"&");
        m_Selsheng = Utils.getCode(m_Sheng.getText().toString(),FunctionHelper.provinces);
        tmpsb.append("ddlStuAddressPCode="+m_Selsheng+"&");

        tmpsb.append("ddlStuAddressC="+m_Shi.getText()+"&");
        m_Selshi = Utils.getCode(m_Shi.getText().toString(),FunctionHelper.city1);
        tmpsb.append("ddlStuAddressCCode="+m_Selshi+"&");

        if(m_Xian.getText().length()<=0||m_Xian.getText().equals("－请选择－")) {
            Toast.makeText(NowAddrActivity.this,"请选择县/区",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("ddlStuAddressA="+m_Xian.getText()+"&");
        tmpsb.append("ddlStuAddressACode="+m_Selxian+"&");

        tmpsb.append("ddlRoadName="+m_Jiedao.getText()+"&");
        // TODO: 16/4/20
        tmpsb.append("ddlRoadNameCode="+m_Seljiedoa+"&");

        tmpsb.append("nbxDoorplateNum="+m_Etmenpaihao.getText()+"&");

        tmpsb.append("ddlDoorplateMarkNum="+m_Fuhao.getText()+"&");
        tmpsb.append("ddlDoorplateMarkNumCode="+m_Selfuhao+"&");

        tmpsb.append("nbxTowerNum="+m_Etlouhao.getText()+"&");

        tmpsb.append("nbxDanYuanNum="+m_Etdanyuan.getText()+"&");

        tmpsb.append("nbxHuNum="+m_Ethuhaom.getText()+"&");

        FunctionHelper.sendSB.append(tmpsb);
        return 0 ;
    }
}
