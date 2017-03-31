package com.example.ttb.regisn.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.example.ttb.regisn.R;
import com.example.ttb.regisn.util.FunctionHelper;
import com.example.ttb.regisn.util.IDCard;
import com.example.ttb.regisn.util.OutPut;
import com.example.ttb.regisn.util.Utils;
import com.gc.materialdesign.views.ButtonRectangle;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseInfoFHJActivity extends AppCompatActivity implements View.OnClickListener {

    private Button next;
    private boolean isSFZ;
    private View m_Masker;
//    private TextView m_Jiguan,m_Birthday,m_Minzu,m_SFZType,m_Sex,m_Language,m_Health,m_Chushengdi;
    private OptionsPickerView m_OptionJiGuan, m_OptionMenu;
    private TimePickerView m_TimePicker;
    private EditText m_Etertongxingming,m_Etcengyongming,m_Etzhengjiaohao,m_Etzongjiaoxinyang,m_Etdenglumima;
    private TextView m_Chushengdi,m_Jiguan,m_Shenfenzhengleixing,m_Xingbie,m_Chushengriqi,m_Minzu,m_Minzuyuyan
            ,m_Gangaotiaiqiao,m_Jiankangzhuangk,m_Guobie;

    private String mSelchushengdi,mSeljiguan,mSelshengfenzhengleixing,mSelxingbie,mSelchushengriqi,mSelminzu,mSelminzuyuyan,
    mSelgangaotaiqiao,mSeljiankangzhuangk,mSelguobie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_info_fhj);
        FunctionHelper.isHjchild = false;

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

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setCustomView(R.layout.layout_actionbar);
        TextView tvTitle = (TextView) actionBar.getCustomView().findViewById(R.id.title);
        tvTitle.setText("基本信息");

        initTimeData();
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
        return  super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        View viewtemp = this.getWindow().getDecorView();
        OutPut.setTempMap(OutPut.getAllChildViews(viewtemp));
        OutPut.setInMap(OutPut.getAllChildViews(viewtemp));
        super.onBackPressed();
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
                m_Chushengriqi.setText(Utils.getTime(date));
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            // TODO: 16/4/20
            case R.id.chushengdi:
                initAddresData3(m_Chushengdi);
                m_OptionJiGuan.show();
                break;
            case R.id.jiguan:
                initAddresData3(m_Jiguan);
                m_OptionJiGuan.show();
                break;
            case R.id.sfztype:
                Utils.initPickViewData(m_Shenfenzhengleixing,"请选择身份证类型",m_OptionMenu, FunctionHelper.shenfenzhengList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.sex:
                if(isSFZ)
                    m_Chushengriqi.setText(Utils.getBirthdayByCardNum(m_Etzhengjiaohao.getText().toString()));
                Utils.initPickViewData(m_Xingbie,"请选择身份证类型",m_OptionMenu, FunctionHelper.sexList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.birthday:
                if(m_Shenfenzhengleixing.getText().equals("身份证-I")) {
                    Toast.makeText(BaseInfoFHJActivity.this, "根据身份证号码，自动生成！", Toast.LENGTH_SHORT).show();
                    m_Chushengriqi.setText(Utils.getBirthdayByCardNum(m_Etzhengjiaohao.getText().toString()));
                }
                else
                    m_TimePicker.show();
//                if(isSFZ)
//                    Toast.makeText(BaseInfoFHJActivity.this,"根据身份证号码，自动生成！",Toast.LENGTH_SHORT).show();
//                else
//                    m_TimePicker.show();
                break;
            case R.id.minzu:
                Utils.initPickViewData(m_Minzu,"请选择身份证类型",m_OptionMenu, FunctionHelper.minzuList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.language:
                Utils.initPickViewData(m_Minzuyuyan,"请选择身份证类型",m_OptionMenu, FunctionHelper.languageList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.gangaotaiqiao:
                Utils.initPickViewData(m_Gangaotiaiqiao,"请选择身份证类型",m_OptionMenu, FunctionHelper.gangaotaiqiao,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.health:
                Utils.initPickViewData(m_Jiankangzhuangk,"请选择身份证类型",m_OptionMenu, FunctionHelper.healthList,m_Masker);
                m_OptionMenu.show();
                break;
            case R.id.guobie:
                Utils.initPickViewData(m_Guobie,"请选择身份证类型",m_OptionMenu, FunctionHelper.guobie,m_Masker);
                m_OptionMenu.show();
                break;
        }
    }
    private void  initView(){
        next = (Button)findViewById(R.id.next);
        m_OptionJiGuan = new OptionsPickerView(this);
        m_OptionMenu = new OptionsPickerView(this);
        m_Masker = (View)findViewById(R.id.vMasker);

        m_Etertongxingming = (EditText)findViewById(R.id.et_etname);
        m_Etcengyongming = (EditText)findViewById(R.id.et_cym);
        m_Etzhengjiaohao = (EditText)findViewById(R.id.et_zjh);
        m_Etzongjiaoxinyang = (EditText)findViewById(R.id.et_zj);
        m_Etdenglumima = (EditText)findViewById(R.id.et_pwd);

        m_Etzhengjiaohao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    if(m_Shenfenzhengleixing.getText().toString().equalsIgnoreCase("身份证-I"))
                        isSFZ = true;
                }else{
                    if(isSFZ)
                        m_Chushengriqi.setText(Utils.getBirthdayByCardNum(m_Etzhengjiaohao.getText().toString()));
                }
            }
        });

        m_Chushengdi = (TextView)findViewById(R.id.chushengdi);
        m_Jiguan = (TextView)findViewById(R.id.jiguan);
        m_Shenfenzhengleixing = (TextView)findViewById(R.id.sfztype);
        m_Xingbie = (TextView)findViewById(R.id.sex);
        m_Chushengriqi = (TextView)findViewById(R.id.birthday);
        m_Minzu = (TextView)findViewById(R.id.minzu);
        m_Minzuyuyan = (TextView)findViewById(R.id.language);
        m_Gangaotiaiqiao = (TextView)findViewById(R.id.gangaotaiqiao);
        m_Jiankangzhuangk = (TextView)findViewById(R.id.health);
        m_Guobie = (TextView)findViewById(R.id.guobie);
    }
    private void initAddresData3(final TextView tv){
        if(FunctionHelper.province_3j.size() > 0) {
            m_OptionJiGuan.setPicker(FunctionHelper.province_3j, FunctionHelper.cities_3j, FunctionHelper.contries_3j, true);
            m_OptionJiGuan.setCancelable(true);

            m_OptionJiGuan.setCyclic(false);

            m_OptionJiGuan.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    String tx = FunctionHelper.province_3j.get(options1) + "-" +
                            FunctionHelper.cities_3j.get(options1).get(option2) + "-" +
                            FunctionHelper.contries_3j.get(options1).get(option2).get(options3);
                    tv.setText(tx);
                }
            });
        }
    }
    private void setListner(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m_Shenfenzhengleixing.getText().toString().equalsIgnoreCase("身份证-I")){
                    String rt = null;
                    try {
                        rt = IDCard.IDCardValidate(m_Etzhengjiaohao.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if(!rt.equalsIgnoreCase("")){
                        Toast.makeText(BaseInfoFHJActivity.this,"请填写正确的身份证号码",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if(!FunctionHelper.isModify) {
                    FunctionHelper.stuCardNo = m_Etzhengjiaohao.getText().toString();
                    FunctionHelper.stuPWD = m_Etdenglumima.getText().toString();
                }
                View view1 = BaseInfoFHJActivity.this.getWindow().getDecorView();
                List<View> list = OutPut.setOutMap(OutPut.getAllChildViews(view1));
//                setSendMap();
                int i = setValue();
                if(i<0)
                    return;
                Intent intent = new Intent(BaseInfoFHJActivity.this,FuzhuInfoActivity.class);
                startActivity(intent);
            }
        });

        m_Chushengdi.setOnClickListener(this);
        m_Jiguan.setOnClickListener(this);
        m_Shenfenzhengleixing.setOnClickListener(this);
        m_Xingbie.setOnClickListener(this);
        m_Chushengriqi.setOnClickListener(this);
        m_Minzu.setOnClickListener(this);
        m_Minzuyuyan.setOnClickListener(this);
        m_Gangaotiaiqiao.setOnClickListener(this);
        m_Jiankangzhuangk.setOnClickListener(this);
        m_Guobie.setOnClickListener(this);
    }
    private int setValue(){
        StringBuffer tmpsb = new StringBuffer();
        //ertongxingming
        tmpsb.append("tbxStuName="+m_Etertongxingming.getText()+"&");
        FunctionHelper.sendTable.put("tbxStuName",m_Etertongxingming.getText());
        if(m_Etertongxingming.getText().length()<=0||m_Etertongxingming.getText().equals("－请选择－")) {
            Toast.makeText(BaseInfoFHJActivity.this,"请正确填写儿童姓名",Toast.LENGTH_SHORT).show();
            return -1;
        }
        //shengfenzhengleixing
        tmpsb.append("ddlStuCardType＝"+m_Shenfenzhengleixing.getText()+"&");
        FunctionHelper.sendTable.put("ddlStuCardType",m_Shenfenzhengleixing.getText());
        mSelshengfenzhengleixing = Utils.getCode(m_Shenfenzhengleixing.getText().toString(),FunctionHelper.shenfenzhengList);
        FunctionHelper.sendTable.put("ddlStuCardTypeCode",mSelshengfenzhengleixing);
        tmpsb.append("ddlStuCardTypeCode="+mSelshengfenzhengleixing+"&");
        if(m_Shenfenzhengleixing.getText().length()<=0||m_Shenfenzhengleixing.getText().equals("－请选择－")) {
            Toast.makeText(BaseInfoFHJActivity.this,"请正确选择身份证类型",Toast.LENGTH_SHORT).show();
            return -1;
        }
        //chushengdi
        String a = m_Chushengdi.getText().toString();
        String[] addrs = a.split("-");
        if(addrs.length<1){
            Toast.makeText(BaseInfoFHJActivity.this,"请正确填写出生地",Toast.LENGTH_SHORT).show();
            return -1;
        }
        //jiguan
        String b = m_Jiguan.getText().toString();
        String[] ads=b.split("-");
        if(ads.length < 1){
            Toast.makeText(BaseInfoFHJActivity.this,"请正确填写籍贯",Toast.LENGTH_SHORT).show();
            return -1;
        }
        //cengyongming
        FunctionHelper.sendTable.put("tbxOldName",m_Etcengyongming.getText());
        tmpsb.append("tbxOldName＝"+m_Etcengyongming.getText()+"&");

        //zhengjianhao
        FunctionHelper.sendTable.put("tbxStuCardNo",m_Etzhengjiaohao.getText());
        tmpsb.append("tbxStuCardNo＝"+m_Etzhengjiaohao.getText()+"&");
        //xingbie
        FunctionHelper.sendTable.put("ddlStuSex",m_Xingbie.getText());
        tmpsb.append("ddlStuSex＝"+m_Xingbie.getText()+"&");
        mSelxingbie = Utils.getCode(m_Xingbie.getText().toString(),FunctionHelper.sexList);
        FunctionHelper.sendTable.put("ddlStuSexCode",mSelxingbie);
        tmpsb.append("ddlStuSexCode="+mSelxingbie+"&");
        //chushengriqi
        String[] rz = m_Chushengriqi.getText().toString().split("-");
        if(rz.length < 1){
            Toast.makeText(BaseInfoFHJActivity.this,"请正确填写出生日期",Toast.LENGTH_SHORT).show();
            return -1;
        }
        tmpsb.append("ddlBirthDayY="+rz[0]+"&");
        tmpsb.append("ddlBirthDayM="+rz[1]+"&");
        tmpsb.append("ddlBirthDayD="+rz[2]+"&");
        //minzu
        tmpsb.append("ddlEthnic＝"+m_Minzu.getText()+"&");
        FunctionHelper.sendTable.put("ddlEthnic",m_Minzu.getText());
        mSelminzu = Utils.getCode(m_Minzu.getText().toString(),FunctionHelper.minzuList);
        FunctionHelper.sendTable.put("ddlEthnicCode",mSelminzu);
        tmpsb.append("ddlEthnicCode="+mSelminzu+"&");
        //minzuyuyan
        tmpsb.append("ddlEthnicLanguage="+m_Minzuyuyan.getText()+"&");
        FunctionHelper.sendTable.put("ddlEthnicLanguage",m_Minzuyuyan.getText());
        mSelminzuyuyan = Utils.getCode(m_Minzuyuyan.getText().toString(),FunctionHelper.languageList);
        FunctionHelper.sendTable.put("ddlEthnicLanguageCode",mSelminzuyuyan);
        tmpsb.append("ddlEthnicLanguageCode="+mSelminzuyuyan+"&");
        //zongjiaoxinyang
        tmpsb.append("tbxReligious="+m_Etzongjiaoxinyang.getText()+"&");
        FunctionHelper.sendTable.put("tbxReligious",m_Etzongjiaoxinyang.getText());
        //jiankangzhuangk
        tmpsb.append("ddlHealth＝"+m_Jiankangzhuangk.getText()+"&");
        FunctionHelper.sendTable.put("ddlHealth",m_Jiankangzhuangk.getText());
        mSeljiankangzhuangk = Utils.getCode(m_Jiankangzhuangk.getText().toString(),FunctionHelper.healthList);
        tmpsb.append("ddlHealthCode="+mSeljiankangzhuangk+"&");
        FunctionHelper.sendTable.put("ddlHealthCode",mSeljiankangzhuangk);
        //mima
        tmpsb.append("tbxStuPwd＝"+m_Etdenglumima.getText()+"&");
        FunctionHelper.sendTable.put("tbxStuPwd",m_Etdenglumima.getText());

        //guobie
        tmpsb.append("ddlNation="+m_Guobie.getText()+"&");
        FunctionHelper.sendTable.put("ddlNation",m_Guobie.getText());
        mSelguobie = Utils.getCode(m_Guobie.getText().toString(),FunctionHelper.guobie);
        tmpsb.append("ddlNationCode="+mSelguobie);
        FunctionHelper.sendTable.put("ddlNationCode",mSelguobie);
        //gangaotiaqiao
        tmpsb.append("ddlHKMType="+m_Gangaotiaiqiao.getText()+"&");
        FunctionHelper.sendTable.put("ddlHKMType",m_Gangaotiaiqiao.getText());
        mSelgangaotaiqiao = Utils.getCode(m_Gangaotiaiqiao.getText().toString(),FunctionHelper.gangaotaiqiao);
        tmpsb.append("ddlHKMTypeCode="+mSelgangaotaiqiao+"&");
        FunctionHelper.sendTable.put("ddlHKMTypeCode",mSelgangaotaiqiao);

        FunctionHelper.sendSB.append(tmpsb);
        return 0;
    }
    private void setSendMap(){
        ViewGroup layout = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_base_info_fhj, null);
        Utils.getKeyValueByView(layout);
    }
    private void setOut(){
//        private EditText m_Etertongxingming,m_Etcengyongming,m_Etzhengjiaohao,m_Etzongjiaoxinyang,m_Etdenglumima;
//        private TextView m_Chushengdi,m_Jiguan,m_Shenfenzhengleixing,m_Xingbie,m_Chushengriqi,m_Minzu,m_Minzuyuyan
//                ,m_Gangaotiaiqiao,m_Jiankangzhuangk,m_Guobie;
        ArrayList<TextView> al = new ArrayList<>();

    }
}
