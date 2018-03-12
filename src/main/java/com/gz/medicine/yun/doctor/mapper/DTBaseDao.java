package com.gz.medicine.yun.doctor.mapper;

import com.gz.medicine.common.exception.CommonException;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * Created by 邓岚峰 on 2017/8/21 0021.
 */
public interface DTBaseDao {
    /**
     * 统一执行查询sql
     * @param mp
     * @return
     * @throws CommonException
     */
    @Select("${querySql}")
    List<Map<String,Object>> querySql(Map<String,Object> mp) throws CommonException;

    /**
     * 统一执行更新sql
     * @param mp
     * @throws CommonException
     */
    @Update("${updateSql}")
    int updateSql(Map<String,Object> mp) throws CommonException;

}
