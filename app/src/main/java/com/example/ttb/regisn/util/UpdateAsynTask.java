package com.example.ttb.regisn.util;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by ttb on 16/4/23.
 */
public class UpdateAsynTask extends AsyncTask {
    boolean rt = false;
    String url = "";
    @Override
    protected Object doInBackground(Object[] objects) {
        if(FunctionHelper.isHjchild){
            url = FunctionHelper.URL_CS+"?action=UpdateHuJiStu";
        }else{
            url = FunctionHelper.URL_CS+"?action=UpdateFeiHuJiStu";
        }
        HttpClient hc = new DefaultHttpClient();
        HttpPost hp = new HttpPost(url);
        HttpResponse httpResponse = null;


        try {

            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String out = Utils.getStringFromOutMap();
            Set set = FunctionHelper.outMap.keySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()){
                String key = (String)iterator.next();
                String value = FunctionHelper.outMap.get(key).toString();
                params.add(new BasicNameValuePair(key, value));
            }
            params.add(new BasicNameValuePair("IsTeShuAddr", FunctionHelper.teshuaddr));
            params.add(new BasicNameValuePair("IsTeShuRegAddr", FunctionHelper.teshuregaddr));
            params.add(new BasicNameValuePair("StuID",FunctionHelper.stuID));

            if(!FunctionHelper.isHjchild){
                params.add(new BasicNameValuePair("ddlJobCategory1", FunctionHelper.isTAG1));
                params.add(new BasicNameValuePair("ddlJobCategory2", FunctionHelper.isTAG2));
            }

            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            httpResponse = new DefaultHttpClient().execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200)
            {
                String result = EntityUtils.toString(httpResponse.getEntity());
                JSONObject jo = new JSONObject(result);
                int r = jo.getInt("result");
                if(r == 1) {
                    rt = true;
                    FunctionHelper.stuID = jo.getString("StuID");
                }
                else{
                    FunctionHelper.errorMsg = jo.getString("msg");
                    rt = false;
                }
                return rt;
            }

        }catch (Exception e){
            e.printStackTrace();
            return rt;
        }
        return rt;
    }
}
