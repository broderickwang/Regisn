package com.example.ttb.regisn.util;

import com.example.ttb.regisn.bean.BaseInfo;
import com.example.ttb.regisn.bean.InfoBean;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by ttb on 16/4/18.
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

    public static String URL_CS = "http://119.167.227.12/sbbm2016service/CJ.ashx";

 //   public static String URL_CS = "http://111.17.218.35:223/cjservice/CJ.ashx";


    //public static String testStr = "{\"ID\":33,\"schoolID\":362,\"tbxStuName\":\"青岛吉林路小学\",\"stuCode\":null,\"stuNo\":null,\"ddlstuName\":\"测试家庭5\",\"HouseholdType\":\"户籍\",\"inYear\":null,\"instCode\":null,\"namePY\":null,\"oldName\":null,\"gradeName\":\"一年级\",\"classNo\":null,\"className\":null,\"stuType\":null,\"stuCardNo\":\"WEW232312CC111\",\"stuPwd\":\"111111\",\"stuCardTypeCode\":3,\"stuCardType\":\"其他-O\",\"stuCardDateStart\":null,\"stuCardDateEnd\":null,\"stuCardDate\":null,\"stuCardDateBY\":null,\"stuCardDateBM\":null,\"stuCardDateBD\":null,\"stuCardDateEY\":null,\"stuCardDateEM\":null,\"stuCardDateED\":null,\"stuSex\":\"男-1\",\"stuSexCode\":2,\"stuBlood\":null,\"stuBloodCode\":null,\"hujiertongbirthday\":\"2010-3-1\",\"birthDayY\":\"2010\",\"birthDayM\":\"3\",\"birthDayD\":\"1\",\"chushengdi\":\"山东省德州市德城区-371402\",\"jiguan\":\"山东省\",\"birthPlaceC\":\"德州市\",\"birthPlaceA\":\"德城区\",\"nativePlace\":\"上海市-310000\",\"nativePlaceP\":\"上海市\",\"nativePlaceC\":\"\",\"nativePlaceA\":\"\",\"ddlethnic\":\"汉族-01\",\"ethnicCode\":1,\"ddlethnicLanguage\":\"汉语(中文)\",\"ethnicLanguageCode\":1,\"religious\":null,\"HKMType\":\"无-0\",\"HKMTypeCode\":1,\"ddlHealth\":\"健康或良好-10\",\"healthCode\":1,\"politicalStatus\":null,\"politicalStatusCode\":null,\"stuAddress\":\"山东省青岛市市北区舞阳路电车站大楼\",\"stuAddressP\":\"山东省\",\"stuAddressC\":\"青岛市\",\"stuAddressA\":\"市北区\",\"stuAddressDetail\":null,\"regPlace\":\"山东省青岛市市北区颐中银街D区\",\"regPlaceP\":\"山东省\",\"regPlaceC\":\"青岛市\",\"regPlaceA\":\"市北区\",\"ddlregPlaceType\":\"非农业户口-2\",\"regPlaceTypeCode\":2,\"ddlpeopleType\":\"非流动人口-0\",\"peopleTypeCode\":1,\"ddlNation\":\"中国-156\",\"nationCode\":1,\"speciality\":null,\"stuPhone\":null,\"commAddress\":null,\"zipCode\":null,\"eMail\":null,\"url\":null,\"ddlforeignLanguage\":\"英语-en\",\"foreignLanguageCode\":2,\"onlyChildTypeCode\":1,\"ddlonlyChildType\":\"独生子女-0\",\"aidType\":null,\"ifReStudy\":null,\"ifSelectSchool\":null,\"oldSchoolCode\":null,\"inDate\":null,\"inType\":null,\"stuSource\":null,\"studyType\":null,\"examCode\":null,\"examScore\":null,\"sourceAddress\":null,\"sourceAddressP\":null,\"sourceAddressC\":null,\"sourceAddressA\":null,\"classStudentNo\":null,\"englishName\":null,\"marryState\":null,\"homeAddress\":null,\"ifBoarder\":null,\"ddlIfPreStudy\":\"是-y\",\"ifStayChild\":null,\"ifFollowChild\":null,\"ifWorkChild\":null,\"ifAloneChild\":null,\"ifMartyrChild\":null,\"ifBuy\":null,\"ifNeedAid\":null,\"ifOneAid\":null,\"ifRideBus\":null,\"schoolDistince\":null,\"transTypeCode\":1,\"ddlTransType\":\"步行-1\",\"ifReal\":null,\"ifFood\":null,\"repeatType\":null,\"remark\":null,\"name1\":\"姓名1\",\"sex1\":\"男-1\",\"Sex1Code\":2,\"ifGuardian1\":\"是-y\",\"relation1Code\":51,\"relation1\":\"父亲-51\",\"political1\":\"中国共产党预备党员-02\",\"political1Code\":2,\"study1\":\"研究生-10\",\"study1Code\":1,\"phone1\":\"联系电话1\",\"zipCode1\":null,\"address1\":\"联系地址1\",\"workUnit1\":\"工作单位1\",\"Nation1\":\"中国-156\",\"Nation1Code\":1,\"IDCard1\":\"身份证件号1\",\"Job1\":null,\"JobCategory1\":\"务工\",\"BusinessLicense1\":null,\"SSCard1\":\"社保卡号1\",\"name2\":\"姓名2\",\"sex2\":null,\"sex2Code\":null,\"ifGuardian2\":\"否-n\",\"relation2Code\":57,\"ddlrelation2\":\"继父或养父-57\",\"political2\":\"中国共产主义青年团团员-03\",\"political2Code\":3,\"study2Code\":7,\"study2\":\"大学肄业-29\",\"phone2\":null,\"zipCode2\":null,\"address2\":\"联系地址2\",\"workUnit2\":null,\"Job2\":null,\"Nation2\":\"阿尔巴尼亚-008\",\"Nation2Code\":4,\"IDCard2\":null,\"JobCategory2\":\"个体\",\"BusinessLicense2\":null,\"SSCard2\":null,\"ClassTmpNo\":null,\"SchoolCode\":null,\"StudentSeqNo\":null,\"StudentID\":null,\"ClassRealNo\":null,\"ClassRealName\":null,\"ifPublicSchool\":null,\"ifModified\":null,\"lastModifyTime\":\"2016-04-14T14:43:37.777Z\",\"HuZhuName\":\"户主姓名\",\"HuZhuGuanXi\":\"儿童与户主关系\",\"HuJiQianRuTime\":\"2000-01-01T00:00:00Z\",\"HuJiDiChanQuanType\":\"自有产权\",\"HuJiDiChanQuanTypeCode\":1,\"CqrFangChanZhenHao\":null,\"CqrFangChanZhenTime\":null,\"CqrName\":null,\"CqrIDCard\":null,\"CqrHuZhuGuanXi\":null,\"CqrStuGuanxiCode\":null,\"CqrStuGuanxi\":null,\"FangWuLaiYuan\":null,\"IsRealNowAddr\":0,\"IsTeShuRegAddr\":1,\"TeShuRegAddr\":\"颐中银街D区\",\"RegRoadName\":\"贮水山支路\",\"RegRoadCode\":41,\"RegDoorplateNum\":1231,\"RegDoorplateMarkNum\":\"癸\",\"RegTowerNum\":1231,\"RegDanYuanNum\":132,\"RegHuNum\":13,\"IsTeShuAddr\":1,\"TeShuAddr\":\"舞阳路电车站大楼\",\"RoadName\":\"邱县路\",\"RoadCode\":1,\"DoorplateNum\":9,\"DoorplateMarkNum\":null,\"TowerNum\":1231,\"DanYuanNum\":132,\"HuNum\":13,\"JYJCheck\":4,\"JYJCheckMark\":null,\"SchoolCheck\":4,\"SchoolCheckMark\":null,\"IsMB\":null,\"EditDataType\":null}";

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

}
