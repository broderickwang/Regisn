package com.example.ttb.regisn.util;

import com.example.ttb.regisn.bean.BaseInfo;
import com.example.ttb.regisn.bean.InfoBean;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by ttb on 16/4/18.
 *
 * Modified by Marc on 2017-03-28
 *
 * FunctionHelper存储公共变量
 */
public class FunctionHelper {

    public static ArrayList<InfoBean> zongList = new ArrayList<>();

    public static ArrayList<InfoBean> minzuList = new ArrayList<>();

    public static ArrayList<InfoBean> shenfenzhengList = new ArrayList<>();

    public static ArrayList<InfoBean> sexList = new ArrayList<>();

    public static ArrayList<InfoBean> languageList = new ArrayList<>();

    public static ArrayList<InfoBean> healthList = new ArrayList<>();

    public static ArrayList<InfoBean> dushengzinvList = new ArrayList<>();

    public static ArrayList<InfoBean> foreignList = new ArrayList<>();

    public static ArrayList<InfoBean> shangxiaxueList = new ArrayList<>();

    public static ArrayList<InfoBean> hukouxingzhiList = new ArrayList<>();

    public static ArrayList<InfoBean> liudongrenkouList = new ArrayList<>();

    public static ArrayList<InfoBean> hujidichanquantypeList = new ArrayList<>();

    public static ArrayList<InfoBean> fangchanowner2childList = new ArrayList<>();

    public static ArrayList<InfoBean> shifoushouguoxqjy = new ArrayList<>();

    public static ArrayList<InfoBean> ertong2huzhu = new ArrayList<>();

    public static ArrayList<InfoBean> hujidiisequaltonow = new ArrayList<>();

    public static ArrayList<InfoBean> hujidiisnow = new ArrayList<>();

    public static ArrayList<InfoBean> provinces = new ArrayList<>();

    public static ArrayList<ArrayList<String>> cities = new ArrayList<>();

    public static ArrayList<ArrayList<ArrayList<String>>> contries = new ArrayList<>();

    public static ArrayList<String> province1 = new ArrayList<>();

    public static ArrayList<InfoBean> city1 = new ArrayList<>();

    public static Map<String ,ArrayList<InfoBean>> country0405 = new HashMap<>();

    public static ArrayList<String> county1 = new ArrayList<>();

    public static ArrayList<String> block1 = new ArrayList<>();

    public static ArrayList<InfoBean> fuhao = new ArrayList<>();

    public static ArrayList<InfoBean> fangwulaiyuan = new ArrayList<>();

    public static JSONObject sendObject = new JSONObject();

    public static StringBuffer sendSB = new StringBuffer();

    public static ArrayList<InfoBean> yuertongguanxi = new ArrayList<>();

    public static ArrayList<InfoBean> guobie = new ArrayList<>();

    public static ArrayList<InfoBean> wenhuachengdu = new ArrayList<>();

    public static ArrayList<InfoBean> zhengzhimianmao = new ArrayList<>();

    public static ArrayList<InfoBean> gongzuoxingzhi = new ArrayList<>();

    public static ArrayList<InfoBean> shifoujianhuaren = new ArrayList<>();

    public static ArrayList<InfoBean> gangaotaiqiao = new ArrayList<>();

    public static ArrayList<InfoBean> country = new ArrayList<>();

    public static ArrayList<InfoBean> road = new ArrayList<>();

    public static ArrayList<InfoBean> country_QD = new ArrayList<>();

    public static boolean isHjchild = true;

    public static Hashtable sendTable = new Hashtable();

    public static HashMap sendMap = new HashMap();

    public static HashMap outMap = new HashMap();

    public static HashMap inMap = new HashMap();

    public static HashMap tempMap = new HashMap();

//    public static String URL_ZS = "http://111.17.218.35:223/cjservice/CJ.ashx";

//    public static String URL_CS = "http://119.167.227.12/sbbm2016service/CJ.ashx";

    public static String URL_CS = "http://111.17.218.35:223/cjservice/CJ.ashx";

    public static boolean isModify = false;

    public static String sessionID = "";

    public static String userName = "";

    public static String stuPWD = "";

    public static String stuID = "";

    public static String stuTime = "";

    public static String stuSchool = "";

    public static String stuCardNo = "";

    public static String stuName = "";

    public static String errorMsg = "";

    public static String isTAG1 = "";

    public static String isTAG2 = "";


    public static ArrayList<String> province_3j = new ArrayList<>();

    public static ArrayList<ArrayList<String>> cities_3j = new ArrayList<>();

    public static ArrayList<ArrayList<ArrayList<String>>> contries_3j = new ArrayList<>();

    public static boolean isShowWG1 = true;

    public static boolean isShowWG2 = true;

    public static boolean isTeshu = false;

    public static boolean isTeshuReg = false;

    //是否特殊现住址
    public static String teshuaddr = "0";

    //是否特殊户籍住址
    public static String teshuregaddr = "0";

    public static ArrayList<InfoBean> teshuaddrlist = new ArrayList<>();


    public static String WEB_STR="<!DOCTYPE html>\n" +
            "\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            "\n" +
            "<head>\n" +
            "<!--[if lte IE 8]>\n" +
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/pc-style.css\" />\n" +
            "<![endif]-->\n" +
            "<link rel=\"stylesheet\" media=\"screen and (max-width:820px)\" href=\"css/mid-style.css\" /><link rel=\"stylesheet\" media=\"screen and (min-width:821px)\" href=\"css/pc-style.css\" /><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" /><link type=\"text/css\" href=\"css/style.css\" rel=\"stylesheet\" /><title>\n" +
            "\t青岛市市北区2017年适龄儿童信息采集系统\n" +
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
            "        <p style=\" margin-top:10px ;text-align: center; line-height: 2em; font-size: 16px; color: rgb(255,15,15);font-weight:bold\">关于市北区2017年适龄儿童入学信息采集工作的有关说明</p>\n" +
            "        <div style=\"font-size: 16px; line-height: 1.65em; text-indent: 2em;width:80%;margin: 10px auto;\">\n" +
            "\n" +
            "<p>为充分做好入学调研和幼小衔接教育服务，简化优化报名流程，依法依规科学统筹做好适龄儿童入学工作，2017年市北区开发试用“小学入学信息采集系统”。特作以下说明：</p>\n" +
            "<p>1.本系统用于2017年市北区户籍的适龄入学儿童和符合市北区就读条件的外来务工就业人员适龄入学子女的信息采集。适龄入学儿童家长应确保录入本系统的信息真实、合法、有效。不实信息不作为报名入学的依据。</p>\n" +
            "<p>2.本系统面向适龄入学儿童家长开放，录入时间为：<span style=\"color:red\">2017年4月28日至5月27日</span>。</p>\n" +
            "<p>3.信息采集完毕后，本系统会生成登录帐号和登录密码，登录帐号即适龄入学儿童的身份证号码，登录密码可修改，适龄入学儿童家长请务必记录号账号、密码。适龄入学儿童家长使用登录帐号及实际登录密码，<span style=\"color:red\">可在4月28日至5月27日期间，登录本系统，查看或修改信息。</span></p>\n" +
            "<p>4.本系统将为每一位完成信息采集的适龄入学儿童提供“2017年市北区小学入学报名提示单”，请家长务必打印或记录，按照本系统提示的报名时间和报名地点等，带好相关材料原件、复印件正式报名。</p>\n" +
            "<p>5.区教育局将协调、统筹区域教育资源，积极创造条件，为家长提供网上入学信息采集服务。对于个别确因特殊情况没能按时进行网上入学信息采集的适龄入学儿童，家长可根据报名相关要求，<span style=\"color:red\">市北区户籍儿童在7月2日、7月3日，外来务工就业人员适龄入学子女7月4日到相应报名点现场报名。区教育局将制定相关工作预案，做好网上补录工作。</span></p>\n" +
            "<p>6.本系统2017年为试用阶段，在此期间如产生填报等方面的问题，可拨打市北区教育局招生咨询电话，或向QQ客服反映具体情况。感谢您的理解支持。</p>\n" +
            "<p>市北区教育局招生咨询电话：0532-66751001  QQ客服：3403235812</p>\n";

}
