package com.gz.medicine.yun.doctor.common;

public enum GzYunCodeEnum {


    //云随访-随访计划
    GZYUN_UPDATEPLANSTATE("200000", "修改随访计划状态异常"),
    GZYUN_STATEKEYFORSFJH("200001", "开始或结束随访状态异常"),
    GZYUN_SAVESFJL("200002", "保存随访记录异常"),
    GZYUN_FOLLUPTASKS("200003", "云诊室-查询待随访和已关闭患者信息异常"),


    WEIXIN_YTJ_INSERT("10", "同步一体机数据异常"),

    WEIXIN_USR_DELTJSJ_CODE("100007", "删除体检数据的月份异常");

    String value;
    String name;

    GzYunCodeEnum(String name, String value) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }


    }
