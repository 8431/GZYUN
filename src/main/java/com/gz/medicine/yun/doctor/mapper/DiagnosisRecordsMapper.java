/*
* Copyright(C) 2017-2020 贯众健康公司
* @date 2017-12-02
*/
package com.gz.medicine.yun.doctor.mapper;

import java.util.*;
import java.util.Map;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.gzyun.health.common.mapper.BaseMapper;
import com.gz.medicine.yun.doctor.bean.DiagnosisRecords;
import org.apache.ibatis.annotations.Param;

/**
 * @version V1.0
 * @Author fendo
 * @ClassName DiagnosisRecordsMapper
 * @PackageName com.gz.medicine.yun.doctor.mapper
 * @Description 诊断表_病历 Mapper
 * @Data 2018年01月08日 10时34分34秒 星期一 
 **/
public interface DiagnosisRecordsMapper extends BaseMapper<DiagnosisRecords> {

    /**
     * 根据病历ID删除诊断_物理删除
     * @return
     * @throws CommonException
     */
    int deleteOK(@Param("sid") String sid)throws CommonException;
}