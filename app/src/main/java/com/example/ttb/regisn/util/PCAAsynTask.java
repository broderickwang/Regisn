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
 * Created by ttb on 16/4/25.
 */
public class PCAAsynTask extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {

        HttpClient hc = new DefaultHttpClient();
        HttpPost hp = new HttpPost(FunctionHelper.URL_CS+"?action=GetAllArea");

        try {
            HttpResponse hr = hc.execute(hp);

            String result = null;
            //获取报文
            if(hr.getStatusLine().getStatusCode() == 200){
                result = EntityUtils.toString(hr.getEntity());
                Utils.getPCAData_3j(result);
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
