/*
* Copyright(C) 2017-2020 贯众健康公司
* @date 2017-12-02
*/
package com.gz.medicine.yun.doctor.mapper;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.gzyun.health.common.mapper.BaseMapper;
import com.gz.medicine.yun.doctor.bean.DiagReport;
import org.apache.ibatis.annotations.Param;

/**
 * @version V1.0
 * @Author fendo
 * @ClassName DiagReportMapper
 * @PackageName com.gz.medicine.yun.doctor.mapper
 * @Description 奕诊 Mapper
 * @Data 2018年01月04日 10时29分19秒 星期四 
 **/
public interface DiagReportMapper extends BaseMapper<DiagReport> {

    /**
     * 根据用户ID获取最新一条数据
     * @param guid
     * @return
     * @throws CommonException
     */
    DiagReport findByUserId(@Param("guid") String guid)throws CommonException;
}