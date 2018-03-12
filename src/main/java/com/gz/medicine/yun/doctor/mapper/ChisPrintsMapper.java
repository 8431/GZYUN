package com.gz.medicine.yun.doctor.mapper;

import com.gz.medicine.yun.doctor.bean.ChisPrints;
import com.gz.medicine.yun.doctor.bean.ChisPrintsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChisPrintsMapper {
    int countByExample(ChisPrintsExample example);

    int deleteByExample(ChisPrintsExample example);

    int insert(ChisPrints record);

    int insertSelective(ChisPrints record);

    List<ChisPrints> selectByExample(ChisPrintsExample example);

    int updateByExampleSelective(@Param("record") ChisPrints record, @Param("example") ChisPrintsExample example);

    int updateByExample(@Param("record") ChisPrints record, @Param("example") ChisPrintsExample example);
}