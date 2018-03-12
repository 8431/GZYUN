/*
* Copyright(C) 2017-2020 贯众健康公司
* @date 2017-12-02
*/
package com.gz.medicine.yun.doctor.service;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.common.mybatisPageVo.Page;
import com.gz.medicine.common.mybatisPageVo.PageModel;
import com.gz.medicine.gzyun.health.common.service.BaseService;
import com.gz.medicine.yun.doctor.bean.Diagnosis;


/**   
 * @Title: Diagnosis.java 
 * @Package com.gz.medicine.yun.doctor.service
 * @Description: 诊断表 Service
 * @author fendo
 * @date 2017年12月26日 12时18分34秒 星期二 
 * @version V1.0   
*/
public interface DiagnosisService extends BaseService<Diagnosis>{

    /**
     * 模糊查询
     * @return
     * @throws CommonException
     */
    Page queryPagelike(PageModel page)throws CommonException;

}