package com.gz.medicine.yun.doctor.mapper;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.common.mybatisPageVo.Page;
import com.gz.medicine.yun.doctor.bean.DTsickbldtl;
import com.gz.medicine.yun.doctor.reponse.DTsickbldtlResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * @Title: DTsickbldtlMapper.java
 * @Package com.gz.medicine.yun.doctor.mapper
 * @Description: 药品接口
 * @Author fendo
 * @Date 2017年8月7日 上午10:23:10
 * @Version V1.0
 */

public interface DTsickbldtlMapper {

    int insert(DTsickbldtl record);

    int insertSelective(DTsickbldtl record);


    /**
     * 根据guid获取用药方案
     * @param guid
     * @return
     */
    List<DTsickbldtlResponse> selectByformGuid(String guid);

    /**
     * 根据病历ID获取用药方案
     * @param guid
     * @return
     */
    List<DTsickbldtl> selectByform(String guid);

    /**
     * 更新用药方案
     * @param record
     * @return
     * @throws CommonException
     */
    int updateDTsickbldtl(DTsickbldtl record)throws CommonException;

    /**
     * 根据病历ID删除用药方案
     * @param sickguid
     * @return
     * @throws CommonException
     */
    int deleteDTsickbldtl(@Param("sickguid") String sickguid)throws CommonException;

    /**
     * 查询途径
     * @return
     * @throws CommonException
     */
    List<Map<String, Object>> queryPageAllWay(Page p)throws CommonException;

    /**
     * 查询频次
     * @return
     * @throws CommonException
     */
    List<Map<String,Object>> queryPageFrequency(Page p)throws CommonException;

    /**
     * 根据药品编码获取药品
     * @return
     * @throws CommonException
     */
    int countById(@Param("id")String id)throws CommonException;
}