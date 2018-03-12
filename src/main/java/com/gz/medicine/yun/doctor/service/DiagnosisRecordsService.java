/*
* Copyright(C) 2017-2020 贯众健康公司
* @date 2017-12-02
*/
package com.gz.medicine.yun.doctor.service;

import java.util.*;
import java.util.Map;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.yun.doctor.bean.DiagnosisRecords;
import com.gz.medicine.gzyun.health.common.service.BaseService;


/**   
 * @Title: DiagnosisRecords.java 
 * @Package com.gz.medicine.yun.doctor.service
 * @Description: 诊断表_病历 Service
 * @author fendo
 * @date 2018年01月08日 10时34分34秒 星期一 
 * @version V1.0   
*/
public interface DiagnosisRecordsService extends BaseService<DiagnosisRecords>{

    /**
     * 批量新增诊断
     * @param diagnosisRecordsList
     * @return
     * @throws CommonException
     */
    int inserList(List<DiagnosisRecords> diagnosisRecordsList)throws CommonException;

    /**
     * 根据病历ID删除诊断
     * @return
     * @throws CommonException
     */
    int deleteOK(String sid)throws CommonException;
}