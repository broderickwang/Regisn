package com.example.ttb.regisn.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ttb.regisn.bean.InfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ttb on 16/4/22.
 */
public class OutPut {
//    public static void setOutPut(TextView tv){
//        FunctionHelper.outMap.put(tv.getTag(),tv.getText());
//    }
//    public static void setOutPut(ArrayList<TextView> al){
//        for(int i=0;i<al.size();i++){
//            TextView tv = al.get(i);
//            FunctionHelper.outMap.put(tv.getTag(),tv.getText());
//        }
//    }
    //获取界面中的所有控件
    public static List<View> getAllChildViews(View view) {

        List<View> allchildren = new ArrayList<View>();

        if (view instanceof ViewGroup) {

            ViewGroup vp = (ViewGroup) view;

            for (int i = 0; i < vp.getChildCount(); i++) {

                View viewchild = vp.getChildAt(i);

                allchildren.add(viewchild);

                allchildren.addAll(getAllChildViews(viewchild));

            }

        }

        return allchildren;

    }
    //将 控件的tag和填写的value赋值到tempmap中
    public static List<View> setTempMap(List<View> list){
        List<View> lt = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            View v = list.get(i);
            if(v instanceof TextView){
                if(v.getTag() != null){
                    FunctionHelper.tempMap.put(v.getTag(),((TextView) v).getText());
                    lt.add(v);
                }
            }
        }
        return lt;
    }
    //将 控件的tag和填写的value赋值到inmap中,修改的时候，也可以返回查看
    public static List<View> setInMap(List<View> list){
        List<View> lt = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            View v = list.get(i);
            if(v instanceof TextView){
                if(v.getTag() != null){
                    FunctionHelper.inMap.put(v.getTag(),((TextView) v).getText());
                    lt.add(v);
                }
            }
        }
        return lt;
    }
    //将 控件的tag和填写的value赋值到outmap中
    public static List<View> setOutMap(List<View> list){
        List<View> lt = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            View v = list.get(i);
            if(v instanceof TextView){
                if(v.getTag() != null){
                    if(v instanceof EditText){
                        FunctionHelper.outMap.put(v.getTag(),((EditText) v).getText());
                    }else{
                        //  16/4/22  出生地和日期进行特殊操作 分开
                        if(v.getTag().toString().equalsIgnoreCase("chushengdi")){
                            String s = (String)((TextView) v).getText();
                            String[] ss = s.split("-");
                            if(ss.length == 3){
                                FunctionHelper.outMap.put("ddlBirthPlaceP",ss[0]);
                                FunctionHelper.outMap.put("ddlBirthPlaceC",ss[1]);
                                FunctionHelper.outMap.put("ddlBirthPlaceA",ss[2]);
                                FunctionHelper.outMap.put("ddlBirthPlacePCode",ss[0]);
                                FunctionHelper.outMap.put("ddlBirthPlaceCCode",ss[1]);
                                FunctionHelper.outMap.put("ddlBirthPlaceACode",ss[2]);
                            }
//                            else if(ss.length == 2){
//                                FunctionHelper.outMap.put("ddlBirthPlaceP",ss[0]);
//                                FunctionHelper.outMap.put("ddlBirthPlaceC",ss[1]);
//                                FunctionHelper.outMap.put("ddlBirthPlaceA","0");
//                                FunctionHelper.outMap.put("ddlBirthPlacePCode",ss[0]);
//                                FunctionHelper.outMap.put("ddlBirthPlaceCCode",ss[1]);
//                                FunctionHelper.outMap.put("ddlBirthPlaceACode","0");
//                            }else if(ss.length == 1){
//                                FunctionHelper.outMap.put("ddlBirthPlaceP",ss[0]);
//                                FunctionHelper.outMap.put("ddlBirthPlaceC","0");
//                                FunctionHelper.outMap.put("ddlBirthPlaceA","0");
//                                FunctionHelper.outMap.put("ddlBirthPlacePCode",ss[0]);
//                                FunctionHelper.outMap.put("ddlBirthPlaceCCode","0");
//                                FunctionHelper.outMap.put("ddlBirthPlaceACode","0");
//                            }
                            else{
                                FunctionHelper.outMap.put("ddlBirthPlaceP",ss[0]);
                                FunctionHelper.outMap.put("ddlBirthPlaceC","0");
                                FunctionHelper.outMap.put("ddlBirthPlaceA","0");
                                FunctionHelper.outMap.put("ddlBirthPlacePCode",ss[0]);
                                FunctionHelper.outMap.put("ddlBirthPlaceCCode","0");
                                FunctionHelper.outMap.put("ddlBirthPlaceACode","0");
                            }
                        }else if(v.getTag().toString().equalsIgnoreCase("jiguan")){
                            String s = (String)((TextView) v).getText();
                            String[] ss = s.split("-");
                            if(ss.length == 3){
                                FunctionHelper.outMap.put("ddlNativePlaceP",ss[0]);
                                FunctionHelper.outMap.put("ddlNativePlaceC",ss[1]);
                                FunctionHelper.outMap.put("ddlNativePlaceA",ss[2]);
                                FunctionHelper.outMap.put("ddlNativePlacePCode",ss[0]);
                                FunctionHelper.outMap.put("ddlNativePlaceCCode",ss[1]);
                                FunctionHelper.outMap.put("ddlNativePlaceACode",ss[2]);
                            }
//                            else if(ss.length == 2){
//                                FunctionHelper.outMap.put("ddlNativePlaceP",ss[0]);
//                                FunctionHelper.outMap.put("ddlNativePlaceC",ss[1]);
//                                FunctionHelper.outMap.put("ddlNativePlaceA","0");
//                                FunctionHelper.outMap.put("ddlNativePlacePCode",ss[0]);
//                                FunctionHelper.outMap.put("ddlNativePlaceCCode",ss[1]);
//                                FunctionHelper.outMap.put("ddlNativePlaceACode","0");
//                            }else if(ss.length == 1){
//                                FunctionHelper.outMap.put("ddlNativePlaceP",ss[0]);
//                                FunctionHelper.outMap.put("ddlNativePlaceC","0");
//                                FunctionHelper.outMap.put("ddlNativePlaceA","0");
//                                FunctionHelper.outMap.put("ddlNativePlacePCode",ss[0]);
//                                FunctionHelper.outMap.put("ddlNativePlaceCCode","0");
//                                FunctionHelper.outMap.put("ddlNativePlaceACode","0");
//                            }
                            else{
                                FunctionHelper.outMap.put("ddlNativePlaceP",ss[0]);
                                FunctionHelper.outMap.put("ddlNativePlaceC","0");
                                FunctionHelper.outMap.put("ddlNativePlaceA","0");
                                FunctionHelper.outMap.put("ddlNativePlacePCode",ss[0]);
                                FunctionHelper.outMap.put("ddlNativePlaceCCode","0");
                                FunctionHelper.outMap.put("ddlNativePlaceACode","0");
                            }
                        }else if(v.getTag().toString().equalsIgnoreCase("chushengriqi")){
                            String s = (String)((TextView) v).getText();
                            String[] ss = s.split("-");
                            if(ss.length == 3){
                                FunctionHelper.outMap.put("ddlBirthDayY",ss[0]);
                                FunctionHelper.outMap.put("ddlBirthDayM",ss[1]);
                                FunctionHelper.outMap.put("ddlBirthDayD",ss[2]);
                                FunctionHelper.outMap.put("ddlBirthDayYCode",ss[0]);
                                FunctionHelper.outMap.put("ddlBirthDayMCode",ss[1]);
                                FunctionHelper.outMap.put("ddlBirthDayDCode",ss[2]);
                            }else{
                                FunctionHelper.outMap.put("ddlBirthDayY","");
                                FunctionHelper.outMap.put("ddlBirthDayM","");
                                FunctionHelper.outMap.put("ddlBirthDayD","");
                                FunctionHelper.outMap.put("ddlBirthDayYCode","");
                                FunctionHelper.outMap.put("ddlBirthDayMCode","");
                                FunctionHelper.outMap.put("ddlBirthDayDCode","");
                            }
                        }else if(v.getTag().toString().equalsIgnoreCase("hujiqianrushijian")){
                            String s = (String)((TextView) v).getText();
                            String[] ss = s.split("-");
                            if(ss.length == 3){
                                FunctionHelper.outMap.put("tbxHuJiQianRuTime_Y",ss[0]);
                                FunctionHelper.outMap.put("ddlHuJiQianRuTime_M",ss[1]);
                                FunctionHelper.outMap.put("ddlHuJiQianRuTime_D",ss[2]);
                                FunctionHelper.outMap.put("tbxHuJiQianRuTime_YCode",ss[0]);
                                FunctionHelper.outMap.put("ddlHuJiQianRuTime_MCode",ss[1]);
                                FunctionHelper.outMap.put("ddlHuJiQianRuTime_DCode",ss[2]);
                            }else{
                                FunctionHelper.outMap.put("tbxHuJiQianRuTime_Y","");
                                FunctionHelper.outMap.put("ddlHuJiQianRuTime_M","");
                                FunctionHelper.outMap.put("ddlHuJiQianRuTime_D","");
                                FunctionHelper.outMap.put("tbxHuJiQianRuTime_YCode","");
                                FunctionHelper.outMap.put("ddlHuJiQianRuTime_MCode","");
                                FunctionHelper.outMap.put("ddlHuJiQianRuTime_DCode","");
                            }
                        }else if(v.getTag().toString().equalsIgnoreCase("fangchanzhengbanfashijian")){
                            String s = (String)((TextView) v).getText();
                            String[] ss = s.split("-");
                            if(ss.length == 3){
                                FunctionHelper.outMap.put("tbxCqrFangChanZhenTime_Y",ss[0]);
                                FunctionHelper.outMap.put("ddlCqrFangChanZhenTime_M",ss[1]);
                                FunctionHelper.outMap.put("ddlCqrFangChanZhenTime_D",ss[2]);
                                FunctionHelper.outMap.put("tbxCqrFangChanZhenTime_YCode",ss[0]);
                                FunctionHelper.outMap.put("ddlCqrFangChanZhenTime_MCode",ss[1]);
                                FunctionHelper.outMap.put("ddlCqrFangChanZhenTime_DCode",ss[2]);
                            }else{
                                FunctionHelper.outMap.put("tbxCqrFangChanZhenTime_Y","");
                                FunctionHelper.outMap.put("ddlCqrFangChanZhenTime_M","");
                                FunctionHelper.outMap.put("ddlCqrFangChanZhenTime_D","");
                                FunctionHelper.outMap.put("tbxCqrFangChanZhenTime_YCode","");
                                FunctionHelper.outMap.put("ddlCqrFangChanZhenTime_MCode","");
                                FunctionHelper.outMap.put("ddlCqrFangChanZhenTime_DCode","");
                            }
                        }else if(v.getTag().toString().equalsIgnoreCase("gongzuoxingzhi1")){
                            FunctionHelper.outMap.put("ddlJobCategory1",FunctionHelper.isTAG1);
                        }else if(v.getTag().toString().equalsIgnoreCase("gongzuoxingzhi2")){
                            FunctionHelper.outMap.put("ddlJobCategory2",FunctionHelper.isTAG2);
                        }
                        else{
                            if(((TextView) v).getText().equals("－请选择－")  ) {
                                FunctionHelper.outMap.put(v.getTag(), "");
                            }else if(((TextView) v).getText().equals("-")){
                                FunctionHelper.outMap.put(v.getTag(), "0");
                            }
                            else{
                                if(((TextView) v).getText().toString().equalsIgnoreCase("无"))
                                    FunctionHelper.outMap.put(v.getTag(), "");
                                else
                                    FunctionHelper.outMap.put(v.getTag(), ((TextView) v).getText());
                            }
                            InfoBean ib = new InfoBean();

                            // 如果是TextView控件，是下拉的，则追加一个Code
                            int icount = FunctionHelper.zongList.size();
                            for(int j=0;j<icount;j++){
                                String text = FunctionHelper.zongList.get(j).getText();
                                if(text.equalsIgnoreCase(((TextView) v).getText().toString()))
                                    ib = FunctionHelper.zongList.get(j);
                            }
                            String code = ib.getCode();
                            if(code  != null) {
                                if(code.equalsIgnoreCase("无"))
                                    code = "";
                                FunctionHelper.outMap.put(v.getTag() + "Code", code);
                            }
                            else
                                FunctionHelper.outMap.put(v.getTag()+"Code","");
                        }


                    }
                    lt.add(v);
                }
            }
        }
        return lt;
    }
    //获得所有有tag属性的控件
    public static List<View> getTagViewRG(List<View> list){
        List<View> lt = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            View v = list.get(i);

                if(v.getTag() != null){
                    lt.add((TextView) v);
                }

        }
        return lt;
    }
    //获得所有有tag属性的控件
    public static List<TextView> getTagView(List<View> list){
        List<TextView> lt = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            View v = list.get(i);
            if(v instanceof TextView){
                if(v.getTag() != null){
                    lt.add((TextView) v);
                }
            }
        }
        return lt;
    }
}
