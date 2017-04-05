package com.example.ttb.regisn.util;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Created by ttb on 16/4/25.
 */
public class HJInfoShowAsynTask extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {
        boolean rt = false;

        HttpClient hc = new DefaultHttpClient();
        HttpPost hp = new HttpPost(FunctionHelper.URL_CS+"?action=GetHuJiStuSort&StuID="
        +FunctionHelper.stuID);

        try {
            HttpResponse hr = hc.execute(hp);

            String result = null;
            //获取报文
            if(hr.getStatusLine().getStatusCode() == 200){
                result = EntityUtils.toString(hr.getEntity());
                JSONObject jo = new JSONObject(result);
                FunctionHelper.stuSchool = jo.getString("SchoolName");
                FunctionHelper.stuTime = jo.getString("STime");
                rt = true;
            }
            //关闭连接
            if(hc != null){
                hc.getConnectionManager().shutdown();
            }
            return rt;
        }catch (Exception e){
            e.printStackTrace();
            return rt;
        }
    }
}
