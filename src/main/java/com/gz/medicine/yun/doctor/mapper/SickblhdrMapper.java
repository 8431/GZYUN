/*
* Copyright(C) 2017-2020 贯众健康公司
* @date 2017-12-02
*/
package com.gz.medicine.yun.doctor.mapper;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.gzyun.health.common.mapper.BaseMapper;
import com.gz.medicine.yun.doctor.bean.Sickblhdr;
import org.apache.ibatis.annotations.Param;

/**
 * @version V1.0
 * @Author fendo
 * @ClassName SickblhdrMapper
 * @PackageName com.gz.medicine.yun.doctor.mapper
 * @Description 病历表 Mapper
 * @Data 2017年12月22日 10时23分36秒 星期五 
 **/
public interface SickblhdrMapper extends BaseMapper<Sickblhdr> {

    /**
     * 根据主键删除病历_物理删除
     * @param guid
     * @return
     * @throws CommonException
     */
    int deleteOK(@Param("guid") String guid)throws CommonException;
}