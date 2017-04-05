package com.example.ttb.regisn.util;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ttb.regisn.bean.BaseInfo;
import com.example.ttb.regisn.bean.InfoBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ttb on 16/4/14.
 */
public class JsonUtil {
    //// TODO: 16/4/14 json 解析的方法
    public static JSONObject toJson(BaseInfo baseInfo){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject obj = new JSONObject();
        JSONObject oa = new JSONObject();

        try {
            oa.put("name",baseInfo.getName());
            oa.put("age",baseInfo.getAge());
            jsonObject.put("basinfo",oa);
            jsonArray.put(jsonObject);

            obj.put("MAIN",jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;

    }

    public static String FromJson2String(JSONObject jsonObject){
        BaseInfo b = null;
        try {
            JSONArray array = jsonObject.getJSONArray("MAIN");
            for(int i=0;i<array.length();i++){
                JSONObject obj = (JSONObject)array.get(i);
                JSONObject binfo = obj.getJSONObject("basinfo");
                binfo.getString("name");
                binfo.getInt("age");
                b = new BaseInfo(binfo.getString("name"),binfo.getInt("age"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (b == null)
            return null;
        return b.toString();
    }

    public static void JsonInfoRude(JSONArray jsonArray){
        FunctionHelper.road.clear();
        int icount = jsonArray.length();
        for(int i=0;i<icount;i++){
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
//                InfoBean infoBean = new InfoBean(jsonObject.getString("code"),
                InfoBean infoBean = new InfoBean(jsonObject.getString("code"),
                        jsonObject.getString("text"),jsonObject.getString("text"));
                FunctionHelper.road.add(infoBean);
                FunctionHelper.zongList.add(infoBean);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    public static void JsonInfoCounty(String city,JSONArray jsonArray){
        FunctionHelper.country.clear();
        int icount = jsonArray.length();
        ArrayList<InfoBean> putInfos = new ArrayList<>();
        for(int i=0;i<icount;i++){
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
                InfoBean infoBean = new InfoBean(jsonObject.getString("code"),
                        jsonObject.getString("text"),"");
                FunctionHelper.country.add(infoBean);
                putInfos.add(infoBean);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        FunctionHelper.country0405.put(city,putInfos);
    }
    public static void JsonInfoQDCounty(JSONArray jsonArray){
        FunctionHelper.country.clear();
        int icount = jsonArray.length();
        for(int i=0;i<icount;i++){
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
                InfoBean infoBean = new InfoBean(jsonObject.getString("code"),
                        jsonObject.getString("text"),"");
                FunctionHelper.country_QD.add(infoBean);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    public static void JsonInfoProvinces(JSONArray jsonArray){
        int icount = jsonArray.length();
        for(int i=0;i<icount;i++){
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
                InfoBean infoBean = new InfoBean(jsonObject.getString("code"),
                        jsonObject.getString("text"),"");
                FunctionHelper.provinces.add(infoBean);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    public static void JsonInfoCities(JSONArray jsonArray){
        int icount = jsonArray.length();
        ArrayList<String> nl = new ArrayList<>();
        ArrayList<String> cities=new ArrayList<String>();
        nl.add("--");
        for(int i=0;i<icount;i++){
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
                cities.add(jsonObject.getString("text"));
                FunctionHelper.city1.add(new InfoBean("i",jsonObject.getString("text"),""));

                new ServerCountiesAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,jsonObject.getString("text"));
//                InfoBean infoBean = new InfoBean(jsonObject.getString("code"),
//                        jsonObject.getString("text"),jsonObject.getString("category"));
//                FunctionHelper.provinces.add(infoBean);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
//        FunctionHelper.city1 = cities;
        for(int i=0;i<FunctionHelper.provinces.size();i++){
            if(FunctionHelper.provinces.get(i).getText().equalsIgnoreCase("山东省")){
                FunctionHelper.cities.add(cities);
            }else{
                FunctionHelper.cities.add(nl);
            }
        }
    }

    public static void JsonInfoCountiesFromServer(JSONArray jsonArray,String city){

//        Set kSet = FunctionHelper.sendMap.keySet();
//        Iterator it =  kSet.iterator();
//        while (it.hasNext()){
//            Object o = it.next();
//
//        }


        int icount = jsonArray.length();
        ArrayList<String> nl = new ArrayList<>();
        ArrayList<String> counties=new ArrayList<String>();
        ArrayList<String> options3Items_01_01=new ArrayList<String>();
        ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<ArrayList<String>>();

        nl.add("--");
        for(int i=0;i<icount;i++){
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
                options3Items_01_01.add(jsonObject.getString("text"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        ///
        for(int i=0;i<FunctionHelper.cities.size();i++){

        }
        for(int i=0;i<FunctionHelper.cities.size();i++){
            if(FunctionHelper.provinces.get(i).getText().equalsIgnoreCase("山东省")){
                options3Items_01.add(counties);
            }else{

            }
        }
    }

    //解析json串
    public static void JsonInfoParser(JSONArray jsonArray){
        int icount = jsonArray.length();
        for(int i=0;i<icount;i++){
            try {
                //将JsonObject放到总list中
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                InfoBean infoBean = new InfoBean(jsonObject.getString("code"),
                        jsonObject.getString("text"),jsonObject.getString("category"));
                FunctionHelper.zongList.add(infoBean);
                switch (infoBean.getCategory()){
                    case "14":
                        //身份证类型
                        FunctionHelper.shenfenzhengList.add(infoBean);
                        break;
                    case "35":
                        FunctionHelper.minzuList.add(infoBean);
                        break;
                    case "2":
                        break;
                    case "3":
                        break;

                    case "15":
                        //性别
                        FunctionHelper.sexList.add(infoBean);
                        break;
                    case "17":
                        //语言
                        FunctionHelper.languageList.add(infoBean);
                        break;
                    case "13":
                        //健康状况
                        FunctionHelper.healthList.add(infoBean);
                        break;
                    case "24":
                        //独生子女状况
                        FunctionHelper.dushengzinvList.add(infoBean);
                        break;
                    case "23":
                        //外语语种
                        FunctionHelper.foreignList.add(infoBean);
                        break;
                    case "32":
                        //上下学方式
                        FunctionHelper.shangxiaxueList.add(infoBean);
                        break;
                    case "22":
                        //户口性质
                        FunctionHelper.hukouxingzhiList.add(infoBean);
                        break;
                    case "21":
                        //流动人口状况
                        FunctionHelper.liudongrenkouList.add(infoBean);
                        break;
                    case "36":
                        //户籍地产权类型
                        FunctionHelper.hujidichanquantypeList.add(infoBean);
                        break;
                    case "37":
                        //房产所有人与儿童关系
                        FunctionHelper.fangchanowner2childList.add(infoBean);
                        break;
                    case "33":
                        FunctionHelper.yuertongguanxi.add(infoBean);
                        break;
                    case "20":
                        FunctionHelper.guobie.add(infoBean);
                        break;
                    case "6":
                        FunctionHelper.wenhuachengdu.add(infoBean);
                        break;
                    case "19":
                        FunctionHelper.zhengzhimianmao.add(infoBean);
                        break;
                    case "18":
                        FunctionHelper.gangaotaiqiao.add(infoBean);
                        break;
                    default:
                        break;
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void parseJson(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
