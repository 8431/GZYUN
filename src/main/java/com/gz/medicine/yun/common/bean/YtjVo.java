package com.gz.medicine.yun.common.bean;

/**
 * Created by dlf on 2017/12/26 0026.
 * Email 1429264916@qq.com
 */
public class YtjVo {
    // 设备编号
    private  String devSn;
    // 时间戳，接⼜⼝口调⽤用时间 格式：yyyy-MM-dd HH:mm:ss
    private  String logTime;
    //检测时间 格式：yyyy-MM-dd HH:mm:ss
    private  String inspTime;
    // 检测标识 格式：32 位 UUID（不带连接符“-” ）
    private  String inspId;
    //检测结果描述
    private  String inspResul;
    //姓名
    private  String psnName;
    //⾝身份证件类型代码 注：详见第⼆二部分⾝身份证件类型字典，默认为居民⾝身份证
    private  String idntCertCat;
    //⾝身份证件号码
    private  String idCertNum;
    //性别代码
    private  String sex;
    //联系电话
    private  String linkTel;
    //⾝⾼ 单位：CM
    private  Double height;
    //体重  单位：kg
    private  Double weight;
    //体温 单位：℃
    private  Double temperature;
    //血糖
    private  Double bloodGlucose;
    //血压
    private  String bloodPress;
            //收缩压
            private  Integer sbp;
            //舒张压
            private  Integer dbp;
            //平均
            private  Double avg;
            //脉动
//            private  Integer pulseRate;
    //血氧
    private  String bloodOxyge;
            //含氧
            private  Double oxygen;
            //脉率
            private  Integer pulseRate;
    //尿常规
    private  String urineRoutin;
            //⽩白细胞
            private  String leu;
            //酸碱度
            private  Double ph;
            //亚硝酸盐
            private  String nit;
            //葡萄糖
            private  String glu;
            //尿蛋⽩
            private  String pr;
            //维⽣生素 C
            private  String vc;
            //尿⽐重
            private  Double sg;
            //尿胆原
            private  Double ub;
            //胆红素
            private  String lry;
            //尿酮体
            private  String ket;
            //尿隐⾎（潜⾎）
            private  String bu;
            //保留位
            private  String tmp;
    //⼼电
    private  String ecg;
            //⼼率
            private  Integer heartRate;
            //⼼心电结果
            private  String ecgResult;
            //波形数据
            private  String ecgData;
                    //第⼏几导联，I,II,III,V1,V2,V3,V4,V5,V6,aVR,aVL,aVF
                    private  String leadType;
                    //导连数据以英⽂文逗号分
                    private  String leadData;
                    //QRS 轴，单位：°
                    private  Double qrsAxes;
                    //QRS 宽度；单位：ms
                    private  Double qrsWidt;
                    //ST 段，单位：ms
                    private  Double stLvl;
                    //RV5，单位：mV
                    private  Double rv5;
                    //SV1，单位：mV
                    private  Double sv1;
                    //P 轴，单位：°
                    private  Double pAxes;
                    //T 轴，单位：°
                    private  Double tAxes;
                    //PR 间期，单位：ms
                    private  Double prInter;
                    //QT 间期，单位：ms
                    private  Double qtInter;
                    //QT 校正，单位：ms
                    private  Double qtc;
                    //P 波，单位：ms
                    private  Double pWave;





/*
[
    {
        devSn: ”c9d78b00a18911e6bbd8000c29c335b3”,
        logTime: ”2016-11-0615: 38: 43”,
        inspTime: ”2016-11-0613: 43: 54”,
        inspId: ”dfae093ca18911e6bbd8000c29cebc56”,
        inspResult: ”检查⽆无异常”,
        psnName: ”张三”,
        idntCertCat: ”01”,
        idCertNum: ”130021198809180071”,
        sex: ”1”,
        名称描述类型⾮非空长度heartRate⼼心率Integer是ecgResult⼼心电结果String是1000ecgData波形数据json[

        ]是名称描述类型⾮非空长度leadType第⼏几导联，I,
            II,
            III,
            V1,
            V2,
            V3,
            V4,
            V5,
            V6,
            aVR,
            aVL,
            aVFString是20leadData导连数据以英⽂文逗号分String是qrsAxesQRS轴，单位：°Double是qrsWidthQRS宽度；单位：msDouble是stLvlST段，单位：msDouble是rv5RV5，单位：mVDouble是sv1SV1，单位：mVDouble是pAxesP轴，单位：°Double是tAxesT轴，单位：°Double是prIntervalPR间期，单位：msDouble是qtIntervalQT间期，单位：msDouble是qtcQT校正，单位：msDouble是pWaveP波，单位：msDouble是4linkTel: ”13299873345”,
        height: 178.5,
                weight: 67,
            temperature: 36.6,
            bloodGlucose: 5.6bloodPress: {
        sbp: 120,
                dbp: 90,
                avg: 105,
                pulseRate: 70
    },
        bloodOxygen: {
            oxygen: 99,
                    pulseRate: 70
        },
        urineRoutine: {
            leu: ”++”,
            ph: 6.5,
                    nit: ”++”,
            glu: ”++”,
            pro: ”++”,
            vc: ”++”,
            sg: 1.025,
                    ubg: ”++”,
            lry: ”++”,
            ket: ”++”,
            bu: ”++”
        },
        ecg: {
            heartrate: 70,
                    ecgResult: ”窦性⼼心律不齐”,
            qrs: ecgData: [
            {
                leadType: ”I”,
                leadData: ”533,
                    678,
                    709……”,
                qrsAxes: 90,
                        qrsWidth: 200,
                    5stLvl: 200,
                    rv5: 0.806,
                    sv1: 0.609,
                    pAxes: 90,
                    tAxes: 90,
                    prInterval: 200,
                    qtInterval: 200,
                    qtc: 199,
                    pWave: 100
            },
            {
                leadType: ”II”,
                leadData: ”533,
                    678,
                    709……”,
                qrsAxes: 90,
                        qrsWidth: 200,
                    stLvl: 200,
                    rv5: 0.806,
                    sv1: 0.609,
                    pAxes: 90,
                    tAxes: 90,
                    prInterval: 200,
                    qtInterval: 200,
                    qtc: 199,
                    pWave: 100
            },
                ……
            ]
        }
    },
    {
        devSn: ”c9d78b00a18911e6bbd8000c29c335b3”,
        logTime: ”2016-11-0615: 38: 43”,
        inspTime: ”2016-11-0613: 43: 54”,
        inspId: ”cb9e093ca18911e6bbd8000c29c335b3”,
        inspResult: ”检查⽆无异常”,
        psnName: ”李四”,
        idntCertCat: ”01”,
        idCertNum: ”130021198809180071”,
        sex: ”1”,
        linkTel: ”13299873345”,
        height: 178.5,
                weight: 67,
            temperature: 36.6,
            6bloodGlucose: 5.6bloodPress: {
        sbp: 120,
                dbp: 90,
                avg: 105,
                pulseRate: 70
    },
        bloodOxygen: {
            oxygen: 99,
                    pulseRate: 70
        },
        urineRoutine: {
            leu: ”++”,
            ph: 6.5,
                    nit: ”++”,
            glu: ”++”,
            pro: ”++”,
            vc: ”++”,
            sg: 1.025,
                    ubg: ”++”,
            lry: ”++”,
            ket: ”++”,
            bu: ”++”
        }ecg: {
        heartrate: 70,
                ecgResult: ”窦性⼼心律不齐”,
        ecgData: [
        {
            leadType: ”I”,
            leadData: ”533,
                678,
                709……”,
            qrsAxes: 90,
                    qrsWidth: 200,
                stLvl: 200,
                rv5: 0.806,
                sv1: 0.609,
                pAxes: 90,
                tAxes: 90,
                7prInterval: 200,
                qtInterval: 200,
                qtc: 199,
                pWave: 100
        },
        {
            leadType: ”II”,
            leadData: ”533,
                678,
                709……”,
            qrsAxes: 90,
                    qrsWidth: 200,
                stLvl: 200,
                rv5: 0.806,
                sv1: 0.609,
                pAxes: 90,
                tAxes: 90,
                prInterval: 200,
                qtInterval: 200,
                qtc: 199,
                pWave: 100
        }
            ]
    }
    }
]
*/
}
