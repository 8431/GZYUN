package com.gz.medicine.yun.common.mapper;

import com.gz.medicine.common.exception.CommonException;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2017/12/26 0026.
 * Email 1429264916@qq.com
 */
public interface YtjDMapper {
    @Insert("${sql}")
   int  insert(Map<String,Object> param)throws CommonException;
    @Select("${sql}")
    List<Map<String,Object>> query(Map<String,Object> param)throws CommonException;
    @Update("${sql}")
    int update (Map<String,Object> param)throws CommonException;

}
