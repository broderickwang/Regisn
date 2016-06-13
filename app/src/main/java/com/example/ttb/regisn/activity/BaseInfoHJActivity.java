package com.example.ttb.regisn.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.example.ttb.regisn.R;
import com.example.ttb.regisn.bean.BaseInfo;
import com.example.ttb.regisn.bean.ProvinceBean;
import com.example.ttb.regisn.util.FunctionHelper;
import com.example.ttb.regisn.util.IDCard;
import com.example.ttb.regisn.util.OutPut;
import com.example.ttb.regisn.util.Utils;
import com.gc.materialdesign.views.ButtonRectangle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseInfoHJActivity extends AppCompatActivity implements View.OnClickListener {
    private ButtonRectangle m_next;
    private View m_Masker;
    private TextView m_Jiguan,m_Birthday,m_Minzu,m_SFZType,m_Sex,m_Language,m_Health,m_Chushengdi;
    private OptionsPickerView m_OptionJiGuan, m_OptionMenu;
    private TimePickerView m_TimePicker;

    private ArrayList<ProvinceBean> options1Items = new ArrayList<ProvinceBean>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<ArrayList<ArrayList<String>>>();

    private EditText m_EtName,m_EtCym,m_EtZjh,m_Etzj,m_Etpwd;

    private String m_Selsfzlx,m_Selxb,m_Selmz,m_Seljkzk,m_Selmzyy;

    private boolean isSFZ=false;

//    private View view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_info_hj);

        // TODO: 16/4/22 test
        if(FunctionHelper.isModify){
            View view2 = this.getWindow().getDecorView();
            //获取所有的tag控件
            Utils.setValue2TagKJInParentView(view2);
        }else {
            //加载tempmap中的数据
            if(FunctionHelper.tempMap.size() > 0) {
                View view2 = this.getWindow().getDecorView();
                Utils.setValue2TagKJInParentViewByTempMap(view2);
            }
        }

        FunctionHelper.isHjchild = true;

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setCustomView(R.layout.layout_actionbar);
        TextView tvTitle = (TextView) actionBar.getCustomView().findViewById(R.id.title);
        tvTitle.setText("基本信息");

        initView();


        initTimeData();

        setListner();

    }

    private void initView(){
        m_next = (ButtonRectangle) findViewById(R.id.next);
        m_Jiguan = (TextView)findViewById(R.id.jiguan);
        m_Birthday = (TextView)findViewById(R.id.birthday);
        m_Minzu = (TextView)findViewById(R.id.minzu);
        m_SFZType = (TextView)findViewById(R.id.sfztype);
        m_Sex = (TextView)findViewById(R.id.sex);
        m_Language = (TextView)findViewById(R.id.language);
        m_Health = (TextView)findViewById(R.id.health);


        m_Chushengdi = (TextView)findViewById(R.id.chushengdi);

        m_EtName = (EditText)findViewById(R.id.et_etname) ;
        m_EtCym = (EditText)findViewById(R.id.et_cym);
        m_EtZjh = (EditText)findViewById(R.id.et_zjh);
        m_EtZjh.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    if(m_SFZType.getText().toString().equalsIgnoreCase("身份证-I"))
                        isSFZ = true;
                }else{
                    if(isSFZ)
                        m_Birthday.setText(Utils.getBirthdayByCardNum(m_EtZjh.getText().toString()));
                }
            }
        });
        m_Etzj = (EditText)findViewById(R.id.et_zj);
        m_Etpwd = (EditText)findViewById(R.id.et_pwd);


        m_OptionJiGuan = new OptionsPickerView(this);
        m_OptionMenu = new OptionsPickerView(this);
        m_Masker = (View)findViewById(R.id.vMasker);
    }
    private void setListner(){
        m_Jiguan.setOnClickListener(this);
        m_Birthday.setOnClickListener(this);
        m_Minzu.setOnClickListener(this);
        m_SFZType.setOnClickListener(this);
        m_Sex.setOnClickListener(this);
        m_Language.setOnClickListener(this);
        m_Health.setOnClickListener(this);
        m_Chushengdi.setOnClickListener(this);


        m_next.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        View view3 = BaseInfoHJActivity.this.getWindow().getDecorView();
        List<View> list = OutPut.setTempMap(OutPut.getAllChildViews(view3));
        OutPut.setInMap(OutPut.getAllChildViews(view3));
        super.onBackPressed();
    }

    private void initAddresData3(final TextView tv){
        m_OptionJiGuan.setPicker(FunctionHelper.province_3j,FunctionHelper.cities_3j,FunctionHelper.contries_3j,true);
        m_OptionJiGuan.setCancelable(true);

        m_OptionJiGuan.setCyclic(false);


        m_OptionJiGuan.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String tx = FunctionHelper.province_3j.get(options1)+"-"+
                        FunctionHelper.cities_3j.get(options1).get(option2)+"-"+
                        FunctionHelper.contries_3j.get(options1).get(option2).get(options3);
                tv.setText(tx);
            }
        });
    }
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
                m_Birthday.setText(getTime(date));
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //把数据放到tempmap中
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.jiguan:
                initAddresData3(m_Jiguan);
                m_OptionJiGuan.show();
                break;
            case R.id.chushengdi:
                initAddresData3(m_Chushengdi);
                m_OptionJiGuan.show();
                break;
            case R.id.birthday:
                if(m_SFZType.getText().equals("身份证-I")) {
                    Toast.makeText(BaseInfoHJActivity.this, "根据身份证号码，自动生成！", Toast.LENGTH_SHORT).show();
                    m_Birthday.setText(Utils.getBirthdayByCardNum(m_EtZjh.getText().toString()));
                }
                else
                    m_TimePicker.show();
//                if(isSFZ)
//                    Toast.makeText(BaseInfoHJActivity.this,"根据身份证号码，自动生成！",Toast.LENGTH_SHORT).show();
//                else
//                    m_TimePicker.show();
                break;
            case R.id.minzu:
                m_Selmz = Utils.initPickViewData(m_Minzu,"请选择民族", m_OptionMenu,FunctionHelper.minzuList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.sfztype:
                Utils.initPickViewData(m_SFZType,"请选择身份证类型",m_OptionMenu,FunctionHelper.shenfenzhengList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.sex:
                if(isSFZ)
                    m_Birthday.setText(Utils.getBirthdayByCardNum(m_EtZjh.getText().toString()));
                m_Selxb = Utils.initPickViewData(m_Sex,"请选择性别",m_OptionMenu,FunctionHelper.sexList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.language:
                m_Selmzyy = Utils.initPickViewData(m_Language,"请选择民族语言",m_OptionMenu,FunctionHelper.languageList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.health:
                m_Seljkzk = Utils.initPickViewData(m_Health,"请选择健康状况",m_OptionMenu,FunctionHelper.healthList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.next:
                try {
                    if(isSFZ){
                        String rt = IDCard.IDCardValidate(m_EtZjh.getText().toString());
                        if(!rt.equalsIgnoreCase("")){
                            Toast.makeText(BaseInfoHJActivity.this,"请填写正确的身份证号码",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                View view1 = this.getWindow().getDecorView();
                List<View> list = OutPut.setOutMap(OutPut.getAllChildViews(view1));
                //setOut();
                setSendMap();
                int i = setValue();
                if(i<0)
                    return;
                if(!FunctionHelper.isModify){
                    FunctionHelper.stuCardNo = m_EtZjh.getText().toString();
                    FunctionHelper.stuPWD = m_Etpwd.getText().toString();
                }
                Intent intent = new Intent(BaseInfoHJActivity.this,FuzhuInfoActivity.class);
                intent.putExtra("type","HJ");

                startActivity(intent);
                break;
        }
    }
    private int setValue(){
        StringBuffer tmpsb = new StringBuffer();
        //ertongxingming
        if(m_EtName.getText().length()<=0||m_EtName.getText().equals("－请选择－")) {
            Toast.makeText(BaseInfoHJActivity.this,"请正确填写儿童姓名",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("tbxStuName="+m_EtName.getText()+"&");
        FunctionHelper.sendTable.put(m_EtName.getTag(),m_EtName.getText());

        //shengfenzhengleixing
        if(m_SFZType.getText().length()<=0||m_SFZType.getText().equals("－请选择－")) {
            Toast.makeText(BaseInfoHJActivity.this,"请正确填写身份证类型",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("ddlStuCardType＝"+m_SFZType.getText()+"&");
        m_Selsfzlx = Utils.getCode(m_SFZType.getText().toString(),FunctionHelper.shenfenzhengList);
        tmpsb.append("ddlStuCardTypeCode="+m_Selsfzlx+"&");
//        new ServerInsertAsynTask().execute();
        //chushengdi
        String a = m_Chushengdi.getText().toString();
        String[] addrs = a.split("-");
        if(addrs.length<1){
            Toast.makeText(BaseInfoHJActivity.this,"请正确填写出生地",Toast.LENGTH_SHORT).show();
        }else if(addrs.length == 3) {
            tmpsb.append("ddlBirthPlaceP=" + addrs[0] + "&" +
                    "ddlBirthPlaceC=" + addrs[1] + "&" + "ddlBirthPlaceA=" + "" + "&");
            tmpsb.append("ddlBirthPlacePCode=" + addrs[0] + "&" +
                    "ddlBirthPlaceCCode=" + addrs[1] + "&" + "ddlBirthPlaceACode=" + "" + "&");
        }
        //jiguan
        String b = m_Jiguan.getText().toString();
        String[] ads=b.split("-");
        if(ads.length < 1){
            Toast.makeText(BaseInfoHJActivity.this,"请正确填写籍贯",Toast.LENGTH_SHORT).show();
            return -1;
        }
        if(ads.length == 3) {
            tmpsb.append("ddlNativePlaceP=" + ads[0] + "&" +
                    "ddlNativePlaceC=" + ads[1] + "&" + "ddlNativePlaceA=" + "" + "&");
            tmpsb.append("ddlNativePlacePCode=" + ads[0] + "&" +
                    "ddlNativePlaceCCode=" + ads[1] + "&" + "ddlNativePlaceACode=" + "" + "&");
        }
        //cengyongming
        tmpsb.append("tbxOldName＝"+m_EtCym.getText()+"&");

        if(m_EtZjh.getText().length()<=0||m_EtZjh.getText().equals("－请选择－")) {
            Toast.makeText(BaseInfoHJActivity.this,"请正确填写证件号",Toast.LENGTH_SHORT).show();
            return -1;
        }
        //zhengjianhao
        tmpsb.append("tbxStuCardNo＝"+m_EtZjh.getText()+"&");
        //xingbie
        if(m_Sex.getText().length()<=0||m_Sex.getText().equals("－请选择－")) {
            Toast.makeText(BaseInfoHJActivity.this,"请正确填写性别",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("ddlStuSex＝"+m_Sex.getText()+"&");
        m_Selxb = Utils.getCode(m_Sex.getText().toString(),FunctionHelper.sexList);
        tmpsb.append("ddlStuSexCode="+m_Selxb+"&");
        //chushengriqi
        String[] rz = m_Birthday.getText().toString().split("-");
        if(rz.length <= 1){
            Toast.makeText(BaseInfoHJActivity.this,"请正确填写出生日期",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("ddlBirthDayY="+rz[0]+"&");
        tmpsb.append("ddlBirthDayM="+rz[1]+"&");
        tmpsb.append("ddlBirthDayD="+rz[2]+"&");
        //minzu
        if(m_Minzu.getText().length()<=0||m_Minzu.getText().equals("－请选择－")) {
            Toast.makeText(BaseInfoHJActivity.this,"请正确填写民族",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("ddlEthnic＝"+m_Minzu.getText()+"&");
        m_Selmz = Utils.getCode(m_Minzu.getText().toString(),FunctionHelper.minzuList);
        tmpsb.append("ddlEthnicCode="+m_Selmz+"&");
        //minzuyuyan
//        if(m_Language.getText().length()<=0||m_Language.getText().equals("－请选择－")) {
//            Toast.makeText(BaseInfoHJActivity.this,"请正确填写民族语言",Toast.LENGTH_SHORT).show();
//            return -1;
//        }
        tmpsb.append("ddlEthnicLanguage="+m_Language.getText()+"&");
        m_Selmzyy = Utils.getCode(m_Language.getText().toString(),FunctionHelper.languageList);
        tmpsb.append("ddlEthnicLanguageCode="+m_Selmzyy+"&");
        //zongjiaoxinyang
        tmpsb.append("tbxReligious="+m_Etzj.getText()+"&");
        //jiankangzhuangk
        if(m_Health.getText().length()<=0||m_Health.getText().equals("－请选择－")) {
            Toast.makeText(BaseInfoHJActivity.this,"请正确填写健康状况",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("ddlHealth＝"+m_Health.getText()+"&");
        m_Seljkzk = Utils.getCode(m_Health.getText().toString(),FunctionHelper.healthList);
        tmpsb.append("ddlHealthCode="+m_Seljkzk+"&");
        //mima
        if(m_Etpwd.getText().length()<=0||m_Etpwd.getText().equals("－请选择－")) {
            Toast.makeText(BaseInfoHJActivity.this,"请正确设置登录密码",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("tbxStuPwd＝"+m_Etpwd.getText()+"&");

        FunctionHelper.sendSB.append(tmpsb);
        return 0;
    }
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
    private void setSendMap(){
        ViewGroup  vg = (ViewGroup)getLayoutInflater().inflate(R.layout.activity_base_info_hj, null);
        LinearLayout layout = (LinearLayout)vg.findViewById(R.id.hjb);
        Utils.getKeyValueByView(layout);
    }



}
