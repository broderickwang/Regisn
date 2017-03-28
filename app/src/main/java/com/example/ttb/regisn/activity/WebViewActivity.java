package com.example.ttb.regisn.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ttb.regisn.R;
import com.example.ttb.regisn.bean.BaseInfo;
import com.example.ttb.regisn.bean.InfoBean;
import com.example.ttb.regisn.util.FunctionHelper;
import com.example.ttb.regisn.util.IDReflect;
import com.example.ttb.regisn.util.JsonUtil;
import com.example.ttb.regisn.util.MyServerAsynTask;
import com.example.ttb.regisn.util.PCAAsynTask;
import com.example.ttb.regisn.util.ServerAsynTask;
import com.example.ttb.regisn.util.ServerCitiesAsynTask;
import com.example.ttb.regisn.util.ServerJiedaoAsynTask;
import com.example.ttb.regisn.util.ServerProvinceAsynTask;
import com.example.ttb.regisn.util.ServerQDCountiesAsynTask;
import com.example.ttb.regisn.util.Utils;
import com.gc.materialdesign.views.ButtonRectangle;

import org.json.JSONObject;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WebViewActivity extends AppCompatActivity {

    private TextView webView;
    private ButtonRectangle in,out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        getSupportActionBar().hide();

        JSONObject object = JsonUtil.toJson(new BaseInfo("abd",12));
        Log.i("MAIN:TOJSON---->",object.toString());

//        new AT().execute();

//        new MyServerAsynTask().execute(object);
        Object rs = new ServerAsynTask().execute(WebViewActivity.this);
//        if(rs == null){
//            Utils.showDialog(WebViewActivity.this,"连接网络失败，请检查网络设置！");
//            return;
//        }
        /*new ServerProvinceAsynTask().execute();
        new ServerCitiesAsynTask().execute();
        new ServerQDCountiesAsynTask().execute();
        new ServerJiedaoAsynTask().execute();
        new PCAAsynTask().execute();*/
        new ServerProvinceAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new ServerCitiesAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new ServerQDCountiesAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new ServerJiedaoAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new PCAAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        FunctionHelper.hujidiisequaltonow.add(new InfoBean("1","是-y",""));
        FunctionHelper.hujidiisequaltonow.add(new InfoBean("0","否-n",""));
        FunctionHelper.zongList.add(new InfoBean("1","是-y",""));
        FunctionHelper.zongList.add(new InfoBean("0","否-n",""));

        FunctionHelper.hujidiisnow.add(new InfoBean("1","是-y",""));
        FunctionHelper.hujidiisnow.add(new InfoBean("0","否-n",""));
        FunctionHelper.zongList.add(new InfoBean("1","是-y",""));
        FunctionHelper.zongList.add(new InfoBean("0","否-n",""));

        FunctionHelper.shifoushouguoxqjy.add(new InfoBean("1","是-y",""));
        FunctionHelper.shifoushouguoxqjy.add(new InfoBean("0","否-n",""));
        FunctionHelper.zongList.add(new InfoBean("1","是-y",""));
        FunctionHelper.zongList.add(new InfoBean("0","否-n",""));

        FunctionHelper.gongzuoxingzhi.add(new InfoBean("0","务工",""));
        FunctionHelper.gongzuoxingzhi.add(new InfoBean("1","个体",""));

        FunctionHelper.shifoujianhuaren.add(new InfoBean("1","是-y",""));
        FunctionHelper.shifoujianhuaren.add(new InfoBean("0","否-n",""));
        FunctionHelper.zongList.add(new InfoBean("1","是-y",""));
        FunctionHelper.zongList.add(new InfoBean("0","否-n",""));

        FunctionHelper.ertong2huzhu.add(new InfoBean("子","子",""));
        FunctionHelper.ertong2huzhu.add(new InfoBean("女","女",""));
        FunctionHelper.ertong2huzhu.add(new InfoBean("孙子","孙子",""));
        FunctionHelper.ertong2huzhu.add(new InfoBean("孙女","孙女",""));
        FunctionHelper.ertong2huzhu.add(new InfoBean("外孙子","外孙子",""));
        FunctionHelper.ertong2huzhu.add(new InfoBean("外孙女","外孙女",""));
        FunctionHelper.zongList.add(new InfoBean("子","子",""));
        FunctionHelper.zongList.add(new InfoBean("女","女",""));
        FunctionHelper.zongList.add(new InfoBean("孙子","孙子",""));
        FunctionHelper.zongList.add(new InfoBean("孙女","孙女",""));
        FunctionHelper.zongList.add(new InfoBean("外孙子","外孙子",""));
        FunctionHelper.zongList.add(new InfoBean("外孙女","外孙女",""));

        FunctionHelper.fangwulaiyuan.add(new InfoBean("自有","自有",""));
        FunctionHelper.fangwulaiyuan.add(new InfoBean("租赁","租赁",""));
        FunctionHelper.zongList.add(new InfoBean("自有","自有",""));
        FunctionHelper.zongList.add(new InfoBean("租赁","租赁",""));
        initTeshuAddr();


        webView = (TextView)findViewById(R.id.webview);
        String strNew= "<p class=\"close\" style=\"text-align: center; line-height:1.3em; font-size: 18px; color: rgb(255,15,15);font-weight:bold;\">关于市北区2016年适龄儿童入学信息采集工作的有关说明</p>\n" +
                "      <div style=\"font-size: 16px; line-height: 1.65em; text-indent: 2em;\">\n" +
                "<p>为充分做好入学调研和幼小衔接教育服务，简化优化报名流程，依法依规科学统筹做好适龄儿童入学工作，2016年市北区开发试用“小学入学信息采集系统”。特作以下说明：</p>\n" +
                "<p>1.本系统用于2016年市北区户籍的适龄入学儿童和符合市北区就读条件的外来务工就业人员适龄入学子女的信息采集。适龄入学儿童家长应确保录入本系统的信息真实、合法、有效。不实信息不作为报名入学的依据。</p>\n" +
                "<p>2.本系统面向适龄入学儿童家长开放，录入时间为：<span style=\"color:red\">2016年4月28日至5月27日</span>。</p>\n" +
                "<p>3.信息采集完毕后，本系统会生成登录帐号和登录密码，登录帐号即适龄入学儿童的身份证号码，登录密码可修改，适龄入学儿童家长请务必记录号账号、密码。适龄入学儿童家长使用登录帐号及实际登录密码，<span style=\"color:red\">可在4月28日至5月27日期间，登录本系统，查看或修改信息。</span></p>\n" +
                "<p>4.本系统将为每一位完成信息采集的适龄入学儿童提供“2016年市北区小学入学报名提示单”，请家长务必打印或记录，按照本系统提示的报名时间和报名地点等，带好相关材料原件、复印件正式报名。</p>\n" +
                "<p>5.区教育局将协调、统筹区域教育资源，积极创造条件，为家长提供网上入学信息采集服务。对于个别确因特殊情况没能按时进行网上入学信息采集的适龄入学儿童，家长可根据报名相关要求，<span style=\"color:red\">市北区户籍儿童在7月2日、7月3日，外来务工就业人员适龄入学子女7月4日到相应报名点现场报名。区教育局将制定相关工作预案，做好网上补录工作。</span></p>\n" +
                "<p style=\"color: red;\">6.本系统仅支持Windows系统下的IE8（或IE9等更高版本）、360、火狐等浏览器，支持手机、IPAD等移动设备。</p>\n" +
                "<p>7.本系统2016年为试用阶段，在此期间如产生填报等方面的问题，可拨打市北区教育局招生咨询电话，或向QQ客服反映具体情况。感谢您的理解支持。</p>\n" +
                "<p>市北区教育局招生咨询电话：0532-66751001  QQ客服：3403235812</p>\n";
        String str="<!DOCTYPE html>\n" +
                "\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "\n" +
                "<head>\n" +
                "<!--[if lte IE 8]>\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/pc-style.css\" />\n" +
                "<![endif]-->\n" +
                "<link rel=\"stylesheet\" media=\"screen and (max-width:820px)\" href=\"css/mid-style.css\" /><link rel=\"stylesheet\" media=\"screen and (min-width:821px)\" href=\"css/pc-style.css\" /><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" /><link type=\"text/css\" href=\"css/style.css\" rel=\"stylesheet\" /><title>\n" +
                "\t青岛市市北区2016年适龄儿童信息采集系统\n" +
                "</title>\n" +
                "\n" +
                "</head>\n" +
                "<body class=\"PC MID\">\n" +
                "<div class=\"center0\" >\n" +
                "    <form method=\"post\" action=\"default.aspx\" id=\"form1\">\n" +
                "<div class=\"aspNetHidden\">\n" +
                "<input type=\"hidden\" name=\"__VIEWSTATE\" id=\"__VIEWSTATE\" value=\"/wEPDwULLTE0NTA0OTg4MjFkZD+4RIt0CGLVtiQqF2+HY5zOU0MP8+an8xFl3SEAts/q\" />\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"aspNetHidden\">\n" +
                "\n" +
                "\t<input type=\"hidden\" name=\"__VIEWSTATEGENERATOR\" id=\"__VIEWSTATEGENERATOR\" value=\"89C80FB0\" />\n" +
                "</div>\n" +
                "    <div>\n" +
                "        <p style=\" margin-top:10px ;text-align: center; line-height: 2em; font-size: 16px; color: rgb(255,15,15);font-weight:bold\">关于市北区2016年适龄儿童入学信息采集工作的有关说明</p>\n" +
                "        <div style=\"font-size: 16px; line-height: 1.65em; text-indent: 2em;width:80%;margin: 10px auto;\">\n" +
                "\n" +
                "<p>为充分做好入学调研和幼小衔接教育服务，简化优化报名流程，依法依规科学统筹做好适龄儿童入学工作，2016年市北区开发试用“小学入学信息采集系统”。特作以下说明：</p>\n" +
                "<p>1.本系统用于2016年市北区户籍的适龄入学儿童和符合市北区就读条件的外来务工就业人员适龄入学子女的信息采集。适龄入学儿童家长应确保录入本系统的信息真实、合法、有效。不实信息不作为报名入学的依据。</p>\n" +
                "<p>2.本系统面向适龄入学儿童家长开放，录入时间为：<span style=\"color:red\">2016年4月28日至5月27日</span>。</p>\n" +
                "<p>3.信息采集完毕后，本系统会生成登录帐号和登录密码，登录帐号即适龄入学儿童的身份证号码，登录密码可修改，适龄入学儿童家长请务必记录号账号、密码。适龄入学儿童家长使用登录帐号及实际登录密码，<span style=\"color:red\">可在4月28日至5月27日期间，登录本系统，查看或修改信息。</span></p>\n" +
                "<p>4.本系统将为每一位完成信息采集的适龄入学儿童提供“2016年市北区小学入学报名提示单”，请家长务必打印或记录，按照本系统提示的报名时间和报名地点等，带好相关材料原件、复印件正式报名。</p>\n" +
                "<p>5.区教育局将协调、统筹区域教育资源，积极创造条件，为家长提供网上入学信息采集服务。对于个别确因特殊情况没能按时进行网上入学信息采集的适龄入学儿童，家长可根据报名相关要求，<span style=\"color:red\">市北区户籍儿童在7月2日、7月3日，外来务工就业人员适龄入学子女7月4日到相应报名点现场报名。区教育局将制定相关工作预案，做好网上补录工作。</span></p>\n" +
                "<p>6.本系统2016年为试用阶段，在此期间如产生填报等方面的问题，可拨打市北区教育局招生咨询电话，或向QQ客服反映具体情况。感谢您的理解支持。</p>\n" +
                "<p>市北区教育局招生咨询电话：0532-66751001  QQ客服：3403235812</p>\n";
        Spanned text = Html.fromHtml(str);
        webView.setText(text);

        in = (ButtonRectangle)findViewById(R.id.in);
        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WebViewActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        out = (ButtonRectangle)findViewById(R.id.out);
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FunctionHelper.tempMap.clear();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        FunctionHelper.tempMap.clear();
        super.onBackPressed();
    }
    private void initTeshuAddr(){
        String[] a = new String[]{
                "颐中银街D区","颐中银街E区","错埠岭靶场","错埠岭东舍","延安路37003委","长山路2号平房",
                "长山路2号筒子楼","长山路2号楼房","国棉二厂楼房","钢舍","电焊条宿舍","北山二路1号","三厂三舍",
                "舞阳路自来水宿舍","舞阳路电车站大楼","广昌路1-1","广昌路5-3","宜昌路31-1","兴元路1号楼","市场二路小区"
        };
        for(int i=0;i<a.length;i++) {
            FunctionHelper.teshuaddrlist.add(new InfoBean(a[i],a[i],""));
            FunctionHelper.zongList.add(new InfoBean(a[i],a[i],""));
        }
    }
}
