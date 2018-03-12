/*
* Copyright(C) 2017-2020 贯众健康公司
* @date 2017-12-02
*/
package com.gz.medicine.yun.doctor.service;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.gzyun.health.common.service.BaseService;
import com.gz.medicine.yun.doctor.bean.DiagReport;


/**   
 * @Title: DiagReport.java 
 * @Package com.gz.medicine.yun.doctor.service
 * @Description: 奕诊 Service
 * @author fendo
 * @date 2018年01月04日 10时29分19秒 星期四 
 * @version V1.0   
*/
public interface DiagReportService extends BaseService<DiagReport>{


    /**
     * 根据用户ID获取最新一条数据
     * @param guid
     * @return
     * @throws CommonException
     */
    DiagReport findByUserId(String guid)throws CommonException;

}