package com.example.ttb.regisn.util;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by ttb on 16/4/18.
 */
public class ServerProvinceAsynTask extends AsyncTask {
    private final static  String TAG = "main------->";
    @Override
    protected Object doInBackground(Object[] objects) {
        HttpClient hc = new DefaultHttpClient();
//        HttpPost hp = new HttpPost("http://119.167.227.12/sbbm2016service/CJ.ashx?action=GetShengDLL");

        HttpPost hp = new HttpPost(FunctionHelper.URL_CS+"?action=GetShengDLL");

        JSONObject jo = new JSONObject();

        try {
//            if(objects.length > 1){
//                jo.put("name",objects[0]);
//                jo.put("age",objects[1]);
//
//            }else if(objects.length == 1){
//                jo = (JSONObject)objects[0];
//            }
//            else{
//                jo.put("error",objects[0]);
//            }
//            Log.i(TAG,"params:"+jo.toString());
//
//            hp.setEntity(new StringEntity(jo.toString()));
            HttpResponse hr = hc.execute(hp);

            String result = null;
            //获取报文
            if(hr.getStatusLine().getStatusCode() == 200){
                result = EntityUtils.toString(hr.getEntity());
                Log.i(TAG,"result = "+result);
                // TODO: 16/4/17  test delete
//                JSONObject jo1 = new JSONObject(result);
                JSONArray ja1 = new JSONArray(result);
                JsonUtil.JsonInfoProvinces(ja1);
            }
            //关闭连接
            if(hc != null){
                hc.getConnectionManager().shutdown();
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
