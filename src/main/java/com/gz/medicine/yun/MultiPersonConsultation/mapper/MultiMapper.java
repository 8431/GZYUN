package com.gz.medicine.yun.MultiPersonConsultation.mapper;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.common.mybatisPageVo.Page;
import com.gz.medicine.gzyun.weixin.bean.Usr;

import java.util.List;
import java.util.Map;

public interface MultiMapper {
    /**
     * 执行sql
     * @return
     * @throws CommonException
     */
    List<Map<String,Object>> exSql(Map<String, Object> param)throws CommonException;

    /**
     * 插入sql
     * @param param
     * @throws CommonException
     */
    void insertSql(Map<String, Object> param)throws CommonException;

    /**
     * 更新
     * @param param
     * @throws CommonException
     */
    void updateSql(Map<String, Object> param)throws CommonException;

    /**
     * 分页查询统一接口
     * @param page
     * @return
     * @throws CommonException
     */
    List<Map<String, Object>> queryPageUtil(Page page)throws CommonException;
    /**
     * 删除
     * @param param
     * @throws CommonException
     */
    void deleteSql(Map<String, Object> param)throws CommonException;


}