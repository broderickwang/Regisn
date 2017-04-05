package com.example.ttb.regisn.util;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

/**
 * Created by ttb on 16/4/18.
 */
public class ServerCitiesAsynTask extends AsyncTask {
    private final static  String TAG = "main------->";
    @Override
    protected Object doInBackground(Object[] objects) {
        HttpClient hc = new DefaultHttpClient();
//        HttpPost hp = new HttpPost("http://119.167.227.12/sbbm2016service/CJ.ashx?action=GetShiDLL");
        HttpPost hp = new HttpPost(FunctionHelper.URL_CS +"?action=GetShiDLL");

        try {
            HttpResponse hr = hc.execute(hp);

            String result = null;
            //获取报文
            if(hr.getStatusLine().getStatusCode() == 200){
                result = EntityUtils.toString(hr.getEntity());
                JSONArray ja1 = new JSONArray(result);
                JsonUtil.JsonInfoCities(ja1);
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
