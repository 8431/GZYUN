package com.gz.medicine.gzyun.health.mapper;

import com.gz.medicine.gzyun.health.bean.healthPrice;
import org.apache.ibatis.annotations.Param;

public interface healthPriceMapper {

    int insert(healthPrice record);

    int insertSelective(healthPrice record);

    healthPrice selectByID(@Param("id") String id);

}