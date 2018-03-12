package com.gz.medicine.yun.doctor.utilTaskTime;

import com.google.gson.Gson;
import com.gz.medicine.yun.doctor.bean.DTteamsg;
import com.gz.medicine.yun.doctor.mapper.DTBaseDao;
import com.gz.medicine.yun.doctor.mapper.DTteamsgextMapper;
import com.gz.medicine.yun.doctor.redisService.RedisDaoService;
import com.gz.medicine.yun.doctor.service.DTBaseService;
import com.gz.medicine.yun.doctor.service.DTDoctorSfhzglService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by Administrator on 2017/8/19 0019.
 */
@Service("updateSfjhState")
public class updateSfjhState {
    public static final Logger LOGGER = Logger.getLogger(updateSfjhState.class);
    @Autowired
    DTDoctorSfhzglService dtdoctorsfhzglservice;
    @Autowired
    DTBaseDao dtbasedao;

    public synchronized void updateState() {
        try {
            /*  （1）未开始：未被点击“开始随访”；

                （2）随访中：已被开始随访，但未被关闭；

                （3）已随访：已保存随访信息，被医生关闭；

                （4）超时未开始：随访超时未开始被自动关闭；

                （5）未随访被关闭：未保存随访信息，被医生主动关闭；

                （6）未随访被超时关闭：未保存随访信息，超时自动关闭。

                （7）已随访：已保存随访信息，被超时自动关闭；*/


            //1.超时未开始：随访超时未开始被自动关闭,修改状态为4；晚上00:00执行
            String sql4 = "update Chis_Followup_Plan set followstate='4' where STATUS='1' and followstate='1' and FOLLOWDATETIME<=sysdate ";
            Map<String, Object> param = new HashedMap();
            param.put("updateSql", sql4);
            dtbasedao.updateSql(param);
            //2.已随访：已保存随访信息，被超时自动关闭，修改状态为7；晚上00:00执行
            String sql7 = "update Chis_Followup_Plan set followstate='7' where guid in(" +
                    "select c.guid from Chis_Followup_Plan c,Chis_Followup_Record r where c.guid=r.followupplanid" +
                    " and c.status='1' and c.followstate='2') and FOLLOWDATETIME<=sysdate ";
            param.put("updateSql", sql7);
            dtbasedao.updateSql(param);
            //3.当2被执行完毕的时候，状态为2的一定是没有保存随访信息的，所以查询状态为2的直接更新到6
            // 未随访被超时关闭：未保存随访信息，超时自动关闭,修改状态为6；晚上00:00执行
            String sql6 = "update Chis_Followup_Plan set followstate='6' where status='1' and followstate='2'  and FOLLOWDATETIME<=sysdate ";
            param.put("updateSql", sql6);
            dtbasedao.updateSql(param);
        } catch (Exception e) {
            LOGGER.error("定时器更新随访计划状态异常：" + e.getMessage(), e);
        }
    }


}
