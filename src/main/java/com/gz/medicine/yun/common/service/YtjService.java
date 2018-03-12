package com.gz.medicine.yun.common.service;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.yun.common.bean.InsertVo;

import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2017/12/26 0026.
 * Email 1429264916@qq.com
 */
public interface YtjService {
    /**
     * 超思一体机检测数据同步
     * @param mp
     * @throws CommonException
     */
    InsertVo insertYtjData(List<Map<String, Object>> mp)throws CommonException;

}
