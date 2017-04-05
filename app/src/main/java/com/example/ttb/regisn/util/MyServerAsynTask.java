package com.example.ttb.regisn.util;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Created by ttb on 16/4/16.
 */
public class MyServerAsynTask extends AsyncTask {
    private final static  String TAG = "main------->";
    @Override
    protected Object doInBackground(Object[] objects) {
        HttpClient hc = new DefaultHttpClient();
        HttpPost hp = new HttpPost("http://192.168.10.124:8080/ServletDemo/servlet/TestServlet");

        JSONObject jo = new JSONObject();

        try {
            if(objects.length > 1){
                jo.put("name",objects[0]);
                jo.put("age",objects[1]);

            }else if(objects.length == 1){
                jo = (JSONObject)objects[0];
            }
            else{
                jo.put("error",objects[0]);
            }

            hp.setEntity(new StringEntity(jo.toString()));
            HttpResponse hr = hc.execute(hp);

            String result = null;
            //获取报文
            if(hr.getStatusLine().getStatusCode() == 200){
                result = EntityUtils.toString(hr.getEntity());
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
