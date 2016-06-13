package com.example.ttb.regisn.util;

import com.example.ttb.regisn.R;

import java.sql.Ref;
import java.util.Hashtable;

/**
 * Created by ttb on 16/4/21.
 */
public class IDReflect {
    public static Hashtable Reflect = new Hashtable();
    public static void InitReflect(){
        //映射户籍与非户籍共有的属性
//        Reflect.put("ddlStuCardType",R.id.shengfenzhengleixing);
        //辅助信息
        Reflect.put("ddlOnlyChildType",R.id.dushengzinv);
        Reflect.put("ddlIfPreStudy",R.id.shifoushouguoxqjy);
        Reflect.put("ddlForeignLanguage",R.id.foreign);
        Reflect.put("ddlTransType",R.id.shangxiaxue);
        Reflect.put("tbxStuPhone",R.id.phone);
        Reflect.put("tbxCommAddress",R.id.addr);
        Reflect.put("tbxZipCode",R.id.yb);
        Reflect.put("taRemark",R.id.bz);
        // 户籍地
        Reflect.put("ddlRegPlaceType",R.id.hukouxingzhi);
        Reflect.put("ddlPeopleType",R.id.liudongrenkouzhuangkuang);
        Reflect.put("ddlRegPlaceP",R.id.hujidisheng);
        Reflect.put("ddlRegPlaceC",R.id.hujidishi);
        Reflect.put("ddlRegPlaceA",R.id.hujidixian);
        //现住址
        Reflect.put("ddlFangWuLaiYuan",R.id.fangwulaiyuan);
        Reflect.put("ddlStuAddressP",R.id.nowsheng);
        Reflect.put("ddlStuAddressC",R.id.nowshi);
        Reflect.put("ddlStuAddressA",R.id.nowxian);
        Reflect.put("ddlRoadName",R.id.nowjiedao);
        Reflect.put("nbxDoorplateNum",R.id.nowmenpaihao);
        Reflect.put("ddlDoorplateMarkNum",R.id.nowfuhao);
        Reflect.put("nbxTowerNum",R.id.nowlouhao);
        Reflect.put("nbxDanYuanNum",R.id.nowdanyuan);
        Reflect.put("nbxHuNum",R.id.nowhuhao);
        //home1
        Reflect.put("ddlRelation1",R.id.yuertongguanxi1);
        Reflect.put("tbxName1",R.id.name1);
        Reflect.put("ddlSex1",R.id.sex1);
        Reflect.put("ddlNation1",R.id.guobie1);
        Reflect.put("ddlIfGuardian1",R.id.shifoujianhuren1);
        Reflect.put("tbxIDCard1",R.id.shengfenzhenghaoma1);
        Reflect.put("tbxAddress1",R.id.lianxidizhi1);
        Reflect.put("ddlStudy1",R.id.wenhuachengdu1);
        Reflect.put("ddlPolitical1",R.id.zhengzhimianmao1);
        Reflect.put("tbxPhone1",R.id.lianxidianhu1);
        Reflect.put("ddlJobCategory1",R.id.gongzuodanwei1);
        Reflect.put("tbxJob1",R.id.zhiwu1);
        //home2
        Reflect.put("ddlRelation2",R.id.yuertongguanxi2);
        Reflect.put("tbxName2",R.id.name2);
        Reflect.put("ddlSex2",R.id.sex2);
        Reflect.put("ddlNation2",R.id.guobie2);
        Reflect.put("ddlIfGuardian2",R.id.shifoujianhuren2);
        Reflect.put("tbxIDCard2",R.id.shengfenzhenghaoma2);
        Reflect.put("tbxAddress2",R.id.lianxidizhi2);
        Reflect.put("ddlStudy2",R.id.wenhuachengdu2);
        Reflect.put("ddlPolitical2",R.id.zhengzhimianmao2);
        Reflect.put("tbxPhone2",R.id.lianxidianhu2);
        Reflect.put("ddlJobCategory2",R.id.gongzuodanwei2);
        Reflect.put("tbxJob2",R.id.zhiwu2);

        //户籍与关系
        Reflect.put("ddlRegPlaceType",R.id.hjgxhukouxingzhi);
        Reflect.put("ddlPeopleType",R.id.hjgxliudongrenkou);
        Reflect.put("tbxHuZhuName",R.id.hjgxhzxm);
        Reflect.put("ddlHuZhuGuanXi",R.id.hjgxowner2child);
        Reflect.put("HuJiQianRuTime",R.id.hjgxhujiintime);
        Reflect.put("ddlHuJiDiChanQuanType",R.id.hjgxhujiditype);
        //户籍住址
        Reflect.put("ddlIsRealNowAddr",R.id.hjdisnow);
        Reflect.put("ddlRegPlaceP",R.id.sheng);
        Reflect.put("ddlRegPlaceC",R.id.shi);
        Reflect.put("ddlRegPlaceA",R.id.xian);
        Reflect.put("ddlRegRoadName",R.id.jiedao);
        Reflect.put("nbxRegDoorplateNum",R.id.menpaihao);
        Reflect.put("ddlRegDoorplateMarkNum",R.id.fuhao);
        Reflect.put("nbxRegTowerNum",R.id.louhao);
        Reflect.put("nbxRegDanYuanNum",R.id.danyuan);
        Reflect.put("nbxRegHuNum",R.id.huhao);
        //房产及关系
        Reflect.put("tbxCqrFangChanZhenHao",R.id.bianhao);
        Reflect.put("CqrFangChanZhenTime",R.id.hrtime);
        Reflect.put("tbxCqrName",R.id.chanquanren);
        Reflect.put("tbxCqrIDCard",R.id.chanquanrennum);
        Reflect.put("tbxCqrHuZhuGuanXi",R.id.suoyou2huzhu);
        Reflect.put("ddlCqrStuGuanxi",R.id.suoyou2ertong);
        //基本信息 非户籍
        Reflect.put("tbxStuName",R.id.et_etname);
        Reflect.put("BirthPlace",R.id.chushengdi);
        Reflect.put("NativePlace",R.id.jiguan);
        Reflect.put("tbxOldName",R.id.et_cym);
        Reflect.put("ddlStuCardType",R.id.sfztype);
        Reflect.put("tbxStuCardNo",R.id.et_zjh);
        Reflect.put("ddlStuSex",R.id.sex);
        Reflect.put("BirthDay",R.id.birthday);
        Reflect.put("ddlEthnic",R.id.minzu);
        Reflect.put("ddlEthnicLanguage",R.id.language);
        Reflect.put("tbxReligious",R.id.et_zj);
        Reflect.put("ddlHKMType",R.id.gangaotaiqiao);
        Reflect.put("ddlHealth",R.id.health);
        Reflect.put("ddlNation",R.id.guobie);
        Reflect.put("tbxStuPwd",R.id.et_pwd);
    }
}
