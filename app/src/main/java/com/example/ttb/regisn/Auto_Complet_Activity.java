package com.example.ttb.regisn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.ttb.regisn.activity.PickerView;
import com.example.ttb.regisn.activity.SelectModeActivity;
import com.example.ttb.regisn.bean.ProvinceBean;
import com.example.ttb.regisn.mycontrols.CustomerSpinner;

import java.util.ArrayList;

public class Auto_Complet_Activity extends AppCompatActivity {
    public static ArrayList<String> list = new ArrayList<String>();

    private ArrayList<ProvinceBean> options1Items = new ArrayList<ProvinceBean>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<ArrayList<ArrayList<String>>>();


    private String[] str = {" 东城区", "西城区", "崇文区", "宣武区", "朝阳区", "海淀区", "丰台区", "石景山区", "门头沟区",
            "房山区", "通州区", "顺义区", "大兴区", "昌平区", "平谷区", "怀柔区", "密云县",
            "延庆县"};
    private CustomerSpinner customerSpinner;
    private ArrayAdapter<String> adapter;
    private OptionsPickerView pvOptions;
    private TextView tvTime, tvOptions;
    private View vMasker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto__complet_);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, str);
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.auto);
        actv.setAdapter(aa);

        init();

        customerSpinner = (CustomerSpinner)findViewById(R.id.spinner);

        customerSpinner.setList(list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        customerSpinner.setAdapter(adapter);

        Button next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Auto_Complet_Activity.this, PickerView.class);
                startActivity(intent);
            }
        });
        tvOptions=(TextView) findViewById(R.id.tvOptions);
        vMasker = (View)findViewById(R.id.vMasker);
        pvOptions = new OptionsPickerView(this);
        //选项1
        options1Items.add(new ProvinceBean(0,"广东","广东省，以岭南东道、广南东路得名","其他数据"));
        options1Items.add(new ProvinceBean(1,"湖南","湖南省地处中国中部、长江中游，因大部分区域处于洞庭湖以南而得名湖南","芒果TV"));
        options1Items.add(new ProvinceBean(2,"广西","嗯～～",""));
//        options1Items.add( "广东省");
//        options1Items.add("湖南省");
//        options1Items.add("广西");

        //选项2
        ArrayList<String> options2Items_01=new ArrayList<String>();
        options2Items_01.add("广州");
        options2Items_01.add("佛山");
        options2Items_01.add("东莞");
        options2Items_01.add("阳江");
        options2Items_01.add("珠海");
        ArrayList<String> options2Items_02=new ArrayList<String>();
        options2Items_02.add("长沙");
        options2Items_02.add("岳阳");
        ArrayList<String> options2Items_03=new ArrayList<String>();
        options2Items_03.add("桂林");
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);

        //选项3
        ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> options3Items_02 = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> options3Items_03 = new ArrayList<ArrayList<String>>();
        ArrayList<String> options3Items_01_01=new ArrayList<String>();
        options3Items_01_01.add("白云");
        options3Items_01_01.add("天河");
        options3Items_01_01.add("海珠");
        options3Items_01_01.add("越秀");
        options3Items_01.add(options3Items_01_01);
        ArrayList<String> options3Items_01_02=new ArrayList<String>();
        options3Items_01_02.add("南海");
        options3Items_01_02.add("高明");
        options3Items_01_02.add("顺德");
        options3Items_01_02.add("禅城");
        options3Items_01.add(options3Items_01_02);
        ArrayList<String> options3Items_01_03=new ArrayList<String>();
        options3Items_01_03.add("其他");
        options3Items_01_03.add("常平");
        options3Items_01_03.add("虎门");
        options3Items_01.add(options3Items_01_03);
        ArrayList<String> options3Items_01_04=new ArrayList<String>();
        options3Items_01_04.add("其他1");
        options3Items_01_04.add("其他2");
        options3Items_01_04.add("其他3");
        options3Items_01.add(options3Items_01_04);
        ArrayList<String> options3Items_01_05=new ArrayList<String>();
        options3Items_01_05.add("其他1");
        options3Items_01_05.add("其他2");
        options3Items_01_05.add("其他3");
        options3Items_01.add(options3Items_01_05);

        ArrayList<String> options3Items_02_01=new ArrayList<String>();
        options3Items_02_01.add("长沙长沙长沙长沙长沙长沙长沙长沙长沙1111111111");
        options3Items_02_01.add("长沙2");
        options3Items_02_01.add("长沙3");
        options3Items_02_01.add("长沙4");
        options3Items_02_01.add("长沙5");
        options3Items_02_01.add("长沙6");
        options3Items_02_01.add("长沙7");
        options3Items_02_01.add("长沙8");
        options3Items_02.add(options3Items_02_01);
        ArrayList<String> options3Items_02_02=new ArrayList<String>();
        options3Items_02_02.add("岳1");
        options3Items_02_02.add("岳2");
        options3Items_02_02.add("岳3");
        options3Items_02_02.add("岳4");
        options3Items_02_02.add("岳5");
        options3Items_02_02.add("岳6");
        options3Items_02_02.add("岳7");
        options3Items_02_02.add("岳8");
        options3Items_02_02.add("岳9");
        options3Items_02.add(options3Items_02_02);
        ArrayList<String> options3Items_03_01=new ArrayList<String>();
        options3Items_03_01.add("好山水");
        options3Items_03.add(options3Items_03_01);

        options3Items.add(options3Items_01);
        options3Items.add(options3Items_02);
        options3Items.add(options3Items_03);

        //三级联动效果
        pvOptions.setPicker(options1Items, options2Items, options3Items, true);
        //设置选择的三级单位
//        pwOptions.setLabels("省", "市", "区");
        pvOptions.setTitle("选择城市");
        pvOptions.setCyclic(false, true, true);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(1, 1, 1);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String s = options1Items.get(options1).getName();
                String tx = options1Items.get(options1).getPickerViewString()
                        + options2Items.get(options1).get(option2)
                        + options3Items.get(options1).get(option2).get(options3);
                tvOptions.setText(tx);
                vMasker.setVisibility(View.GONE);
            }
        });
        //点击弹出选项选择器
        tvOptions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });
    }

    private void init(){
        list.add("-－请选择－-");
        list.add("路飞");
        list.add("索隆");
        list.add("山治");
        list.add("乔巴");
        list.add("罗宾");
        list.add("娜美");
        list.add("乌索普");
        list.add("弗兰奇");
        list.add("布鲁克");
    }
}
