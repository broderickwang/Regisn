package com.example.ttb.regisn.util;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by ttb on 16/4/8.
 */
public class InitWidgetUtil {
//    ArrayAdapter<String> provinceAdapter = null;  //省级适配器
//    ArrayAdapter<String> cityAdapter = null;    //地级适配器
//    ArrayAdapter<String> countyAdapter = null;    //县级适配器
    static int provincePosition = 3;

    //省级选项值
    private static String[] province = new String[] {"北京","上海","天津","广东"};//,"重庆","黑龙江","江苏","山东","浙江","香港","澳门"};
    //地级选项值
    private static String[][] city = new String[][]
            {
                    { " 东城区", "西城区", "崇文区", "宣武区", "朝阳区", "海淀区", "丰台区", "石景山区", "门头沟区",
                            "房山区", "通州区", "顺义区", "大兴区", "昌平区", "平谷区", "怀柔区", "密云县",
                            "延庆县"},
                    { "长宁区", "静安区", "普陀区", "闸北区", "虹口区" },
                    { "和平区", "河东区", "河西区", "南开区", "河北区", "红桥区", "塘沽区", "汉沽区", "大港区",
                            "东丽区" },
                    { "广州", "深圳", "韶关" // ,"珠海","汕头","佛山","湛江","肇庆","江门","茂名","惠州","梅州",
                            // "汕尾","河源","阳江","清远","东莞","中山","潮州","揭阳","云浮"
                    }
            };
    //县级选项值
    private static String[][][] county = new String[][][]
            {
                    {   //北京
                            {"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},
                            {"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"}
                    },
                    {    //上海
                            {"无"},{"无"},{"无"},{"无"},{"无"}
                    },
                    {    //天津
                            {"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"}
                    },
                    {    //广东
                            {"海珠区","荔湾区","越秀区","白云区","萝岗区","天河区","黄埔区","花都区","从化市","增城市","番禺区","南沙区"}, //广州
                            {"宝安区","福田区","龙岗区","罗湖区","南山区","盐田区"}, //深圳
                            {"武江区","浈江区","曲江区","乐昌市","南雄市","始兴县","仁化县","翁源县","新丰县","乳源县"}  //韶关
                    }
            };


    public static void InitSpinner(final Context context, Spinner one, final Spinner two, final Spinner three){
        //绑定适配器和值

        ArrayAdapter provinceAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, province);
        one.setAdapter(provinceAdapter);
        one.setSelection(3,true);  //设置默认选中项，此处为默认选中第4个值

        ArrayAdapter cityAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, city[3]);
        two.setAdapter(cityAdapter);
        two.setSelection(0,true);  //默认选中第0个

        ArrayAdapter countyAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, county[3][0]);
        three.setAdapter(countyAdapter);
        three.setSelection(0, true);

        one.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //position为当前省级选中的值的序号

                //将地级适配器的值改变为city[position]中的值
                ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,city[i]);
                // 设置二级下拉列表的选项内容适配器
                two.setAdapter(cityAdapter);
                //记录当前省级序号，留给下面修改县级适配器时用
                provincePosition = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        two.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<String> countyAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,county[provincePosition][i]);
                three.setAdapter(countyAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
