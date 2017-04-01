package com.example.ttb.regisn.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.ttb.regisn.R;
import com.example.ttb.regisn.bean.InfoBean;
import com.example.ttb.regisn.util.FunctionHelper;
import com.example.ttb.regisn.util.OutPut;
import com.example.ttb.regisn.util.ServerCountiesAsynTask;
import com.example.ttb.regisn.util.Utils;
import com.gc.materialdesign.views.Button;
import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.widgets.Dialog;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;

import java.util.List;

public class HujiAddrActivity extends AppCompatActivity implements View.OnClickListener{

    private android.widget.Button next;
    private View m_Masker;
    private OptionsPickerView m_OptionMenu;

    /** 全局屏幕的高和宽 */
    private static int SCREEN_WIDTH = 0 ;
    private static int SCREEN_HEIGHT = 0;

    /**气泡view*/
    private View bubbleView = null;
    /**气泡dialog*/
    private Dialog bubbleAlert = null;

    private  TextView m_Hjdisnow,m_Sheng,m_Xian,m_Jiedao,m_Shi,m_Fuhao,m_Regteshuaddr;
    private EditText m_Etmenpaihao,m_Etlouhao,m_Etdanyuan,m_Ethuhao;
    private String m_Selhjdisnow,m_Selsheng,m_Selshi,m_Selxian,m_Seljiedoa,m_Selfuhao;
    private RadioGroup regaddrrg;
    private RadioButton regshi,regfou;
    private LinearLayout putonglinear,teshulinear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hujiadress_huji);



        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setCustomView(R.layout.layout_actionbar);
        TextView tvTitle = (TextView) actionBar.getCustomView().findViewById(R.id.title);
        tvTitle.setText("户籍住址");

        initView();

        if(FunctionHelper.isModify){
//            Utils.setString2InMap(FunctionHelper.testStr);
            View view1 = this.getWindow().getDecorView();
            //获取所有的tag控件 并赋值
            Utils.setValue2TagKJInParentView(view1);

            if(!FunctionHelper.isTeshuReg){
                //if not teshuadddr
                regfou.setChecked(true);
                putonglinear.setVisibility(View.VISIBLE);
                teshulinear.setVisibility(View.GONE);
            }else{
                //if teshuaddr
                regshi.setChecked(true);
                putonglinear.setVisibility(View.GONE);
                teshulinear.setVisibility(View.VISIBLE);
            }
        }else {
            //加载tempmap中的数据
            if(FunctionHelper.tempMap.size() > 0) {
                View view2 = this.getWindow().getDecorView();
                Utils.setValue2TagKJInParentViewByTempMap(view2);
            }
        }




        setListner();


    }

    private void initView(){
        m_OptionMenu = new OptionsPickerView(this);
        m_Masker = (View)findViewById(R.id.vMasker);
        next = (android.widget.Button) findViewById(R.id.next);

        m_Hjdisnow = (TextView)findViewById(R.id.hjdisnow);
        m_Hjdisnow.setText("是-y");

        m_Sheng = (TextView)findViewById(R.id.sheng);
        m_Sheng.setText("山东省");
        m_Shi = (TextView)findViewById(R.id.shi);
        m_Shi.setText("青岛市");
        m_Xian = (TextView)findViewById(R.id.xian);
        m_Xian.setText("市北区");
        m_Jiedao = (TextView)findViewById(R.id.jiedao);
        m_Fuhao = (TextView)findViewById(R.id.fuhao);
        m_Fuhao.setText("无");
        m_Regteshuaddr = (TextView)findViewById(R.id.regteshudizhi);
        regaddrrg = (RadioGroup)findViewById(R.id.regteshu);
        regshi = (RadioButton)findViewById(R.id.regshi);
        regfou = (RadioButton)findViewById(R.id.regfou);

        m_Etmenpaihao = (EditText)findViewById(R.id.menpaihao);
        m_Etlouhao = (EditText)findViewById(R.id.louhao);
        m_Etdanyuan = (EditText)findViewById(R.id.danyuan);
        m_Ethuhao = (EditText)findViewById(R.id.huhao);

        putonglinear = (LinearLayout)findViewById(R.id.regputonglinear);
        teshulinear = (LinearLayout)findViewById(R.id.regteshulinear);

        regaddrrg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)HujiAddrActivity.this.findViewById(radioButtonId);
                if(rb.getText().toString().equalsIgnoreCase("是")){
                    teshulinear.setVisibility(View.VISIBLE);
                    putonglinear.setVisibility(View.GONE);
                    FunctionHelper.teshuregaddr = "1";
                }else{
                    teshulinear.setVisibility(View.GONE);
                    putonglinear.setVisibility(View.VISIBLE);
                    FunctionHelper.teshuregaddr = "0";
                }
            }
        });

    }
    private void setListner(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View viewtmp = HujiAddrActivity.this.getWindow().getDecorView();
                List<View> list = OutPut.setOutMap(OutPut.getAllChildViews(viewtmp));
                switch (regaddrrg.getId()){
                    case R.id.regshi:
                        break;
                    case R.id.regfou:
                        FunctionHelper.teshuregaddr = "0";
                        m_Regteshuaddr.setText("");

                        break;
                }
                int i= setValue();
                if(i<0)
                    return;
                Intent intent = new Intent(HujiAddrActivity.this,NowAddrActivity.class);
                startActivity(intent);
            }
        });
        m_Hjdisnow.setOnClickListener(this);
        m_Sheng.setOnClickListener(this);
        m_Shi.setOnClickListener(this);
        m_Xian.setOnClickListener(this);
        m_Jiedao.setOnClickListener(this);
        m_Fuhao.setOnClickListener(this);
        m_Regteshuaddr.setOnClickListener(this);
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
            case R.id.hjdisnow:
                m_Selhjdisnow = Utils.initPickViewData(m_Hjdisnow,"", m_OptionMenu, FunctionHelper.hujidiisnow,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.sheng:
//                m_Selsheng = Utils.initPickViewData(m_Sheng,"", m_OptionMenu, FunctionHelper.provinces,m_Masker);
//                m_OptionMenu.show();
                break;
            case R.id.shi:
//                if(m_Sheng.getText().equals("山东省")){
//                    Utils.initPickViewData(m_Shi,"请选择身份证类型",m_OptionMenu, FunctionHelper.city1,m_Masker);
//                    m_OptionMenu.show();
//                }else{
//                    m_Shi.setText("-");
//                }
                break;
            case R.id.xian:
//                if(m_Shi.getText().length() > 0 && !m_Shi.getText().equals("－请选择－") && !m_Shi.getText().equals("-")){
//                    new ServerCountiesAsynTask().execute(m_Shi.getText());
//                    Utils.initPickViewData(m_Xian,"请选择身份证类型",m_OptionMenu, FunctionHelper.country,m_Masker);
//                    m_OptionMenu.show();
//                }else{
//                    m_Xian.setText("-");
//                }
                break;
            case R.id.jiedao:
                if(m_Xian.getText().toString().equalsIgnoreCase("市北区") ) {
                    m_Seljiedoa = Utils.initPickViewData(m_Jiedao, "", m_OptionMenu, FunctionHelper.road, m_Masker);
                    m_OptionMenu.show();
                }
                break;
            case R.id.fuhao:
                m_Selfuhao = Utils.initPickViewData(m_Fuhao,"", m_OptionMenu, FunctionHelper.fuhao,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.regteshudizhi:
                m_Selfuhao = Utils.initPickViewData(m_Regteshuaddr,"", m_OptionMenu, FunctionHelper.teshuaddrlist,m_Masker);
                m_OptionMenu.show();
                break;
        }
    }
    private int setValue(){
        if(m_Hjdisnow.getText().length()<=0||m_Hjdisnow.getText().equals("－请选择－")) {
            Toast.makeText(HujiAddrActivity.this,"请选择户籍地是否实际居住",Toast.LENGTH_SHORT).show();
            return -1;
        }

        if(m_Sheng.getText().length()<=0||m_Sheng.getText().equals("－请选择－")) {
            Toast.makeText(HujiAddrActivity.this,"请选择省",Toast.LENGTH_SHORT).show();
            return -1;
        }
        return 0;
    }
}
