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

import java.util.ArrayList;
import java.util.List;

// TODO: 16/4/23    登录成功  后 显示的界面 
/**
 * Created by ttb on 16/4/23.
 */
public class ShowInfoAsynTask extends AsyncTask {
    String url = "";
    @Override
    protected Object doInBackground(Object[] objects) {
        if(FunctionHelper.isHjchild){
            url = FunctionHelper.URL_CS+"?action=UpdateHuJiStu";
        }else{
            url = FunctionHelper.URL_CS+"?action=GetFeiHuJiCheckSchoolName";
        }
        HttpClient hc = new DefaultHttpClient();
        HttpPost hp = new HttpPost(url);
        HttpResponse httpResponse = null;


        try {

            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String out = Utils.getStringFromOutMap();
            params.add(new BasicNameValuePair("STR", FunctionHelper.sendSB.toString()));
            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            httpResponse = new DefaultHttpClient().execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200)
            {
                String result = EntityUtils.toString(httpResponse
                        .getEntity());
                Log.i("recevied by server----",result);
                return result;
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
