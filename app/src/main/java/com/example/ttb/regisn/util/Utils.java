package com.example.ttb.regisn.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.example.ttb.regisn.bean.InfoBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by ttb on 16/4/18.
 */
public class Utils {
    private static int result = 0;
    public static void initPickViewData3(final TextView tv, String str, OptionsPickerView pickerView,
                                        final ArrayList<InfoBean> list1,final ArrayList<ArrayList<String>> list2,
                                        final ArrayList<ArrayList<ArrayList<String>>> list3,final View m_Masker) {
//        InitData.loadProvices(options1Items,options2Items,options3Items);
//        pickerView.setPicker(options1Items,options2Items,options3Items,true);
        pickerView.setPicker(list1,list2,true);
        pickerView.setCancelable(true);

        pickerView.setCyclic(false);
//        pickerView.setTitle(str);
        pickerView.setSelectOptions(0, 0, 0);

        pickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String tx = list1.get(options1).getText()+" "
                        +list2.get(options1).get(option2);// +" "+list3.get(options1).get(option2).get(options3);
                tv.setText(tx);
                m_Masker.setVisibility(View.GONE);
            }
        });
    }

    //初始化选择器的数据
    public static String initPickViewData(final TextView tv, String str, OptionsPickerView pickerView,
                                        final ArrayList<InfoBean> list,final View m_Masker) {
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            String str1 = tv.getText().toString();
            if(list.get(i).getText().equalsIgnoreCase(str1)){
                index = i;
                break;
            }
        }

        pickerView.setPicker(list);
        pickerView.setCancelable(true);

        pickerView.setCyclic(false);
        pickerView.setSelectOptions(index);

        pickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String tx = list.get(options1).getText();
                result = options1+1;
                tv.setText(tx);
                m_Masker.setVisibility(View.GONE);
            }
        });
        return "";
    }

    /*public static String initPickViewDataIndex(final TextView tv, String str, OptionsPickerView pickerView,
                                          final ArrayList<InfoBean> list,final View m_Masker,int index) {
        pickerView.setPicker(list);
        pickerView.setCancelable(true);
        pickerView.setSelectOptions(index);

        pickerView.setCyclic(false);
        pickerView.setSelectOptions(0, 0, 0);

        pickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String tx = list.get(options1).getText();
                result = options1+1;
                tv.setText(tx);
                m_Masker.setVisibility(View.GONE);
            }
        });
        return "";
    }*/
    public static String initPickViewDataDefault(final TextView tv, String str, OptionsPickerView pickerView,
                                                 final ArrayList<InfoBean> list,final View m_Masker,int defaultOptions){
        pickerView.setPicker(list);
        pickerView.setCancelable(true);

        pickerView.setCyclic(false);
        pickerView.setSelectOptions(defaultOptions, 0, 0);

        pickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String tx = list.get(options1).getText();
                result = options1+1;
                tv.setText(tx);
                m_Masker.setVisibility(View.GONE);
            }
        });
        return "";
    }

    public static void initTimePickViewData(TimePickerView m_TimePicker,final TextView textView){
        m_TimePicker.setTime(new Date());
        m_TimePicker.setCyclic(false);
        m_TimePicker.setCancelable(true);
        //时间选择后回调
        m_TimePicker.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                textView.setText(getTime(date));
            }
        });
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    //显示说明提示对话框
    public static void showDialog(Context context,String str){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("说明");
        builder.setMessage(str);
        builder.setPositiveButton("确定", null);
        builder.show();
    }

    public static String getCode(String a,ArrayList<InfoBean> al){
        String result = "";
        for(int i=0;i<al.size();i++){
            InfoBean ib = al.get(i);
            String str = ib.getText();
            if(str.equalsIgnoreCase(a)){
                result = ib.getCode();
                break;
            }
            else
                continue;
//                continue;
        }
        return result;
    }
    public static void getKeyValueByView(ViewGroup loginLayout){

        for (int i = 0; i < loginLayout.getChildCount(); i++) {
            View v=loginLayout.getChildAt(i);
            if(v instanceof EditText){
                FunctionHelper.sendMap.put(v.getTag(),((EditText) v).getText());
            }
            else if(v instanceof TextView){
                FunctionHelper.sendMap.put(v.getTag(),((TextView) v).getText());
                FunctionHelper.sendMap.put(v.getTag()+"Code",((TextView) v).getText());

            }else if(v instanceof ScrollView){
                getKeyValueByView((ScrollView)v);
            }else if(v instanceof LinearLayout){
                getKeyValueByView((LinearLayout)v);
            }

        }


    }
    public static void parserJson2Model(JSONArray array){
        for(int i=0;i<array.length();i++){

        }
    }

    //将获取到的字符串，放到inmap中，方便给控件赋值
    public static void setString2InMap(String str){
        FunctionHelper.inMap.clear();
        try {
            JSONObject jsonObject = new JSONObject(str);
            Iterator io = jsonObject.keys();
            while (io.hasNext()){
                String key = (String)io.next();
                if(key.equalsIgnoreCase("tbxHuZhuGuanXi_Text")){
                    boolean is = false;
                    for(int i=0;i<FunctionHelper.ertong2huzhu.size();i++){
                        if(FunctionHelper.ertong2huzhu.get(i).getText().equalsIgnoreCase(jsonObject.getString(key)))
                            is = true;
                    }
                    if(is)
                        FunctionHelper.inMap.put("ddlHuZhuGuanXi",jsonObject.getString(key));
                    else
                        FunctionHelper.inMap.put("tbxHuZhuGuanXi",jsonObject.getString(key));
                }else
                    FunctionHelper.inMap.put(key,jsonObject.getString(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //根据tempMap对list中的textview进行赋值
    public static void setGetValues2KJByTempMap(List<TextView> arrayList){
        for(int i=0;i<arrayList.size();i++){
            String tag = (String)arrayList.get(i).getTag();
            String text = "";
            Object ob = FunctionHelper.tempMap.get(tag);
            if(ob != null) {
                text = FunctionHelper.tempMap.get(tag).toString();
                if (text != null) {
                    if (text.equalsIgnoreCase("null"))
                        text = "";
                } else
                    text = "";
            }else{
                text = "";
            }

            arrayList.get(i).setText(text);
        }

    }
    //根据inmap对list中的textview进行赋值
    public static void setGetValues2KJRG(List<View> arrayList){
        for(int i=0;i<arrayList.size();i++){
            if(arrayList.get(i) instanceof  TextView){
                TextView tmpTV = (TextView)arrayList.get(i);
                String tag = (String)arrayList.get(i).getTag();
                String text = "";
                if(tag.equalsIgnoreCase("chushengriqi")){
                    text = (String) FunctionHelper.inMap.get("ddlBirthDayY")+"-"
                            +(String) FunctionHelper.inMap.get("ddlBirthDayM")+"-"+
                            (String) FunctionHelper.inMap.get("ddlBirthDayD");
                }else if(tag.equalsIgnoreCase("jiguan")){
                    text = (String) FunctionHelper.inMap.get("ddlNativePlaceP")+"-"
                            +(String) FunctionHelper.inMap.get("ddlNativePlaceC")+"-"+
                            (String) FunctionHelper.inMap.get("ddlNativePlaceA");
                }else if(tag.equalsIgnoreCase("chushengdi")){
                    text = (String) FunctionHelper.inMap.get("ddlBirthPlaceP")+"-"
                            +(String) FunctionHelper.inMap.get("ddlBirthPlaceC")+"-"+
                            (String) FunctionHelper.inMap.get("ddlBirthPlaceA");
                }else if(tag.equalsIgnoreCase("ddlIsRealNowAddr")){
                    String index = (String)FunctionHelper.inMap.get("ddlIsRealNowAddr");
                    if("0".equalsIgnoreCase(index)){
                        text = "否-n";
                    }else{
                        text = "是-y";
                    }
                } else {
                    Object ob = FunctionHelper.inMap.get(tag);
                    if(ob != null)
                        text = FunctionHelper.inMap.get(tag).toString();
                    else
                        text = "";
                }
                if(text != null){
                    if(text.equalsIgnoreCase("null"))
                        text = "";
                }else
                    text = "";

                tmpTV.setText(text);
            }
            else{

            }

        }

    }

    //根据inmap对list中的textview进行赋值
    public static void setGetValues2KJ(List<TextView> arrayList){
        for(int i=0;i<arrayList.size();i++){
            String tag = (String)arrayList.get(i).getTag();
            String text = "";
            if(tag.equalsIgnoreCase("chushengriqi")){
                text = (String) FunctionHelper.inMap.get("ddlBirthDayY")+"-"
                +(String) FunctionHelper.inMap.get("ddlBirthDayM")+"-"+
                        (String) FunctionHelper.inMap.get("ddlBirthDayD");
            }else if(tag.equalsIgnoreCase("jiguan")){
                text = (String) FunctionHelper.inMap.get("ddlNativePlaceP")+"-"
                        +(String) FunctionHelper.inMap.get("ddlNativePlaceC")+"-"+
                        (String) FunctionHelper.inMap.get("ddlNativePlaceA");
            }else if(tag.equalsIgnoreCase("chushengdi")){
                text = (String) FunctionHelper.inMap.get("ddlBirthPlaceP")+"-"
                        +(String) FunctionHelper.inMap.get("ddlBirthPlaceC")+"-"+
                        (String) FunctionHelper.inMap.get("ddlBirthPlaceA");
            }else if(tag.equalsIgnoreCase("ddlTeShuAddr")){
                Object ob = FunctionHelper.inMap.get(tag);
                if(ob != null)
                    text = FunctionHelper.inMap.get(tag).toString();
                else
                    text = "";
                String ts = ob.toString();
                if(ts.equalsIgnoreCase("null"))
                    FunctionHelper.isTeshu = false;
                else
                    FunctionHelper.isTeshu = true;
            }else if(tag.equalsIgnoreCase("ddlTeShuRegAddr")){
                Object ob = FunctionHelper.inMap.get(tag);
                if(ob != null)
                    text = FunctionHelper.inMap.get(tag).toString();
                else
                    text = "";
                String ts = ob.toString();
                if(ts.equalsIgnoreCase("null"))
                    FunctionHelper.isTeshuReg = false;
                else
                    FunctionHelper.isTeshuReg = true;
            }else if(tag.equalsIgnoreCase("tbxSSCard1")){
                Object ob = FunctionHelper.inMap.get(tag);
                if(ob != null)
                    text = FunctionHelper.inMap.get(tag).toString();
                else
                    text = "";
                String ts = ob.toString();
                if(ts.equalsIgnoreCase("null"))
                    FunctionHelper.isShowWG1 = false;
                else
                    FunctionHelper.isShowWG1 = true;
            }else if(tag.equalsIgnoreCase("tbxSSCard2")){
                Object ob = FunctionHelper.inMap.get(tag);
                if(ob != null)
                    text = FunctionHelper.inMap.get(tag).toString();
                else
                    text = "";
                String ts = ob.toString();
                if(ts.equalsIgnoreCase("null"))
                    FunctionHelper.isShowWG2 = false;
                else
                    FunctionHelper.isShowWG2 = true;
            }else if(tag.equalsIgnoreCase("ddlIsRealNowAddr")){
                Object ob = FunctionHelper.inMap.get(tag);
                String a = "";
                if(ob != null)
                    a = FunctionHelper.inMap.get(tag).toString();
                if(a.equalsIgnoreCase("0")){
                    text = "否-n";
                }else{
                    text = "是-y";
                }
            }
            else {
                Object ob = FunctionHelper.inMap.get(tag);
                if(ob != null)
                    text = FunctionHelper.inMap.get(tag).toString();
                else
                    text = "";
            }
            if(text != null){
                if(text.equalsIgnoreCase("null"))
                    text = "";
            }else
                text = "";

            arrayList.get(i).setText(text);
        }

    }
    //对家庭成员特殊 控制，radiogroup
    public static void setValue2TagKJInParentViewRG(View parent){
        List<View> list = OutPut.getTagViewRG(OutPut.getAllChildViews(parent));
        //对tag控件进行赋值
        Utils.setGetValues2KJRG(list);
    }

    //对一个view视图中的虽有tag控件进行赋值，根据inmap中的值
    public static void setValue2TagKJInParentView(View parent){
        List<TextView> list = OutPut.getTagView(OutPut.getAllChildViews(parent));
        //对tag控件进行赋值
        Utils.setGetValues2KJ(list);
    }
    //对一个view视图中的虽有tag控件进行赋值，根据inmap中的值
    public static void setValue2TagKJInParentViewByTempMap(View parent){
        List<TextView> list = OutPut.getTagView(OutPut.getAllChildViews(parent));
        //对tag控件进行赋值
        Utils.setGetValues2KJByTempMap(list);
    }
    //根据outmap组合字符串
    public static String getStringFromOutMap(){
        StringBuffer sb = new StringBuffer();
        Set set = FunctionHelper.outMap.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            String key = (String)iterator.next();
            sb.append(key+"=");
            String value = FunctionHelper.outMap.get(key).toString();
            sb.append(value+"&");
        }
        return sb.toString();
    }
    //通过身份证号码，获取出生日期
    public static String getBirthdayByCardNum(String number){
        StringBuffer sb = new StringBuffer();
        char[] c=new char[number.length()];
        c=number.toCharArray();
        //370724199009192955
        for(int i=0;i<c.length;i++){
            if(i >=6 && i<=13){
                sb.append(c[i]);
                if(i ==9 || i==11)
                    sb.append("-");
            }
        }
        return sb.toString();
    }

    //获取省市区三级联动数据
    public static void getPCAData_3j(String str){
        try {
            JSONArray ja = new JSONArray(str);
            ArrayList<String> a1 = new ArrayList<>();
            ArrayList<ArrayList<String>> a2 = new ArrayList<>();
            ArrayList<ArrayList<ArrayList<String>>> a3 = new ArrayList<>();
            for(int i=0;i<ja.length();i++){
                ArrayList<String> t1 = new ArrayList<>();
                ArrayList<String> tz2 = new ArrayList<>();
                ArrayList<ArrayList<String>> tt2 = new ArrayList<>();
                JSONObject jo = ja.getJSONObject(i);
                a1.add(jo.getString("DivisionName"));
                if(jo.getString("DivisionName").equalsIgnoreCase("山东省")){
                    JSONArray jacy = jo.getJSONArray("DivisionSub");
                    for(int j=0;j<jacy.length();j++){
                        JSONObject jco = jacy.getJSONObject(j);
                        t1.add(jco.getString("DivisionName"));

                        JSONArray jacn = jco.getJSONArray("DivisionSub");
                        ArrayList<String> t2 = new ArrayList<>();
                        for(int x=0;x<jacn.length();x++){
                            JSONObject jacno = jacn.getJSONObject(x);
                            t2.add(jacno.getString("DivisionName"));
                        }
                        tt2.add(t2);
                    }
                    a3.add(tt2);
                }
                else{

                    t1.add("-");

                    tz2.add("-");
                    tt2.add(tz2);
                    a3.add(tt2);

                }
                a2.add(t1);

                FunctionHelper.province_3j = a1;
                FunctionHelper.cities_3j = a2;
                FunctionHelper.contries_3j = a3;
            }
        }catch (Exception e){

        }
    }
}
