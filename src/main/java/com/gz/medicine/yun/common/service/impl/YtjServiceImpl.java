package com.gz.medicine.yun.common.service.impl;

import com.google.gson.Gson;
import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.common.util.UUIDTool;
import com.gz.medicine.yun.common.bean.InsertVo;
import com.gz.medicine.yun.common.mapper.YtjDMapper;
import com.gz.medicine.yun.common.service.YtjService;
import com.gz.medicine.yun.doctor.common.GzYunCodeEnum;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2017/12/26 0026.
 * Email 1429264916@qq.com
 */
@Service("YtjService")
public class YtjServiceImpl implements YtjService {
    public static final Logger LOGGER = Logger.getLogger(YtjServiceImpl.class);
    @Autowired
    YtjDMapper ytjdmapper;

    @Override
    public InsertVo insertYtjData(List<Map<String, Object>> li) throws CommonException {
        InsertVo vo = new InsertVo();
        try {
            //后期如果li.size>1改成批量新增
            StringBuffer sql = new StringBuffer();
            DecimalFormat df = new DecimalFormat("0.00");
            sql.append("insert into CHIS_BLOODPRESSURE(GUID,MEASURETIME,IDCODE,WEIGHT,OXYGEN,HR,TEMPERATURE,BLOODSUGAR,HIGHPRESSURE,LOWPRESSURE) ");
            sql.append("values(#{GUID},#{MEASURETIME},#{IDCODE},#{WEIGHT},#{OXYGEN},#{HR},#{TEMPERATURE},#{BLOODSUGAR},#{HIGHPRESSURE},#{LOWPRESSURE})");
            Map<String, Object> m = li.get(0);
            Map<String, Object> param = new HashedMap();
            param.put("GUID", UUIDTool.getUUID());
            //测量时间
            String InspectDate = StringUtils.isEmpty((String) m.get("InspectDate")) ? "" : (String) m.get("InspectDate");
            param.put("MEASURETIME", InspectDate);
            //身份证
            String CardNo = StringUtils.isEmpty((String) m.get("CardNo")) ? "" : (String) m.get("CardNo");
            param.put("IDCODE", CardNo);
            //体重
            Map LogWeight = (Map) m.get("LogWeight");
            Double weight = (Double) LogWeight.get("weight") == 0.0d ? 0.0d : (Double) LogWeight.get("weight");
            param.put("WEIGHT", df.format(weight));
            //血氧
            Map LogOxygenLevel = (Map) m.get("LogOxygenLevel");
            Double Oxygen = (Double) LogOxygenLevel.get("Oxygen") == 0.0d ? 0.0d : (Double) LogOxygenLevel.get("Oxygen");

            param.put("OXYGEN", df.format(Oxygen));
            //心率
            Map LogEcg = (Map) m.get("LogEcg");
            Double HeartRate = (Double) LogEcg.get("HeartRate") == 0.0d ? 0.0d : (Double) LogEcg.get("HeartRate");
            param.put("HR", df.format(HeartRate));
            //体温
            Map LogTemperature = (Map) m.get("LogTemperature");
            Double Tempera = (Double) LogTemperature.get("Tempera") == 0.0d ? 0.0d : (Double) LogTemperature.get("Tempera");
            param.put("TEMPERATURE", df.format(Tempera));
            //血糖
            Map LogBloodGlucose = (Map) m.get("LogBloodGlucose");
            Double Glucose = (Double) LogBloodGlucose.get("Glucose") == 0.0d ? 0.0d : (Double) LogBloodGlucose.get("Glucose");
            param.put("BLOODSUGAR", df.format(Glucose));
            //血压
            Map LogBp = (Map) m.get("LogBp");
            Double Systolic = (Double) LogBp.get("Systolic") == 0.0d ? 0.0d : (Double) LogBp.get("Systolic");
            Double Diastolic = (Double) LogBp.get("Diastolic") == 0.0d ? 0.0d : (Double) LogBp.get("Diastolic");
            param.put("HIGHPRESSURE", df.format(Systolic));
            param.put("LOWPRESSURE", df.format(Diastolic));
            //只有新增操作
            param.put("sql", sql);
            ytjdmapper.insert(param);
            if (weight != 0.0d) {
                vo.setValue1(df.format(weight));
                vo.setType("5");

            }
            if (Oxygen != 0.0d) {
                vo.setValue1(df.format(Oxygen));
                vo.setType("6");

            }
            if (HeartRate != 0.0d) {
                vo.setValue1(df.format(HeartRate));
                vo.setType("4");

            }
            if (Tempera != 0.0d) {
                vo.setValue1(df.format(Tempera));
                vo.setType("1");
            }
            if (Glucose != 0.0d) {
                vo.setValue1(df.format(Glucose));
                vo.setType("2");
            }
            if (Systolic != 0.0d || Diastolic != 0.0d) {
                vo.setType("3");
                vo.setValue1(df.format(Systolic));
                vo.setValue2(df.format(Diastolic));
            }
            vo.setMeatime(InspectDate);
            vo.setIdcard(CardNo);
        } catch (Exception e) {
            LOGGER.error("YtjService一体机数据异常：" + e.getMessage(), e);
            if (e instanceof CommonException) {
                throw new CommonException(GzYunCodeEnum.WEIXIN_YTJ_INSERT, e.getMessage(), e);
            } else {
                throw new CommonException(GzYunCodeEnum.WEIXIN_YTJ_INSERT, e);
            }
        }
        return vo;
    }


    public static void main(String[] args) {
        String json = "[\n" +
                "    {\n" +
                "        \"LogOxygenLevel\": {\n" +
                "            \"Oxygen\": 99,\n" +
                "            \"DeviceSN\": \"\",\n" +
                "            \"Pulse\": \"87\",\n" +
                "            \"DeviceName\": \"spo2\"\n" +
                "        },\n" +
                "        \"LogBloodGlucose\": {\n" +
                "            \"Glucose\": 0,\n" +
                "            \"DeviceSN\": \"\",\n" +
                "            \"DeviceName\": \"bg\"\n" +
                "        },\n" +
                "        \"Type\": \"LogOxygenLevel\",\n" +
                "        \"InspectDate\": \"2017-12-27 09:32:37\",\n" +
                "        \"LogEcg\": {\n" +
                "            \"DeviceName\": \"ecg\",\n" +
                "            \"ECGData\": 0,\n" +
                "            \"ECGResult\": -1,\n" +
                "            \"DeviceSN\": \"\",\n" +
                "            \"HeartRate\": 0\n" +
                "        },\n" +
                "        \"LogNcgModel\": {\n" +
                "            \"Glu\": -1,\n" +
                "            \"Pro\": -1,\n" +
                "            \"Bld\": -1,\n" +
                "            \"Leu\": -1,\n" +
                "            \"Bil\": -1,\n" +
                "            \"Vc\": -1,\n" +
                "            \"Ket\": -1,\n" +
                "            \"Sg\": -1,\n" +
                "            \"Nit\": -1,\n" +
                "            \"Ubg\": -1,\n" +
                "            \"Ph\": -1\n" +
                "        },\n" +
                "        \"LogTemperature\": {\n" +
                "            \"DeviceSN\": \"\",\n" +
                "            \"Tempera\": 0,\n" +
                "            \"DeviceName\": \"temperature\"\n" +
                "        },\n" +
                "        \"LogWeight\": {\n" +
                "            \"DeviceSN\": \"\",\n" +
                "            \"DeviceName\": \"weight\",\n" +
                "            \"weight\": 0\n" +
                "        },\n" +
                "        \"LogBp\": {\n" +
                "            \"Pulse\": 0,\n" +
                "            \"DeviceName\": \"bp\",\n" +
                "            \"DeviceSN\": \"\",\n" +
                "            \"Systolic\": 0,\n" +
                "            \"Diastolic\": 0\n" +
                "        },\n" +
                "        \"LogDate\": \"2017-12-27 09:32:37\",\n" +
                "        \"InspectUUID\": \"567bab5e-cdd7-4add-b82c-7b67ee9de94a\",\n" +
                "        \"CardNo\": \"220106199408040627\"\n" +
                "    }\n" +
                "]";
        Gson gn = new Gson();
        List<Map<String, Object>> li = gn.fromJson(json, List.class);
        StringBuffer sb = new StringBuffer();
        sb.append("1,2,");
        System.out.println(sb.lastIndexOf(","));
        System.out.println(sb.length());
        if (sb.lastIndexOf(",") + 1 == sb.length()) {
            sb.delete(sb.length() - 1, sb.length());

            System.out.println(sb.toString());
        }


    }


}
