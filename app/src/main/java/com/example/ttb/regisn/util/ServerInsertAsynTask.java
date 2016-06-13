package com.example.ttb.regisn.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by ttb on 16/4/18.
 */
public class ServerInsertAsynTask extends AsyncTask {
    private final static  String TAG = "main------->";
    boolean rt = false;
    @Override
    protected Object doInBackground(final Object[] objects) {
        String url = "";
        if(FunctionHelper.isHjchild){
            url = FunctionHelper.URL_CS+"?action=InsertHuJiStu";

        }else{
            url = FunctionHelper.URL_CS+"?action=InsertFeiHuJiStu";
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
                String value;
                if(key.equalsIgnoreCase("ddlIsRealNowAddr")){
                    value = FunctionHelper.outMap.get(key).toString();
                    if(value.equalsIgnoreCase("是"))
                        value = "1";
                    else
                        value = "0";
                }else {
                    value = FunctionHelper.outMap.get(key).toString();
                }
                params.add(new BasicNameValuePair(key, value));
            }
            params.add(new BasicNameValuePair("IsTeShuAddr", FunctionHelper.teshuaddr));
            params.add(new BasicNameValuePair("IsTeShuRegAddr", FunctionHelper.teshuregaddr));
            if(FunctionHelper.isHjchild){
                params.add(new BasicNameValuePair("ddlHKMType", "无-0"));
                params.add(new BasicNameValuePair("ddlHKMTypeCode", "1"));
            }
            if(!FunctionHelper.isHjchild){
                params.add(new BasicNameValuePair("ddlJobCategory1", "务工"));
                params.add(new BasicNameValuePair("ddlJobCategory2", "务工"));
            }

            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            httpResponse = new DefaultHttpClient().execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200)
            {
                String result = EntityUtils.toString(httpResponse.getEntity());
                Log.i("recevied by server----",result);
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
