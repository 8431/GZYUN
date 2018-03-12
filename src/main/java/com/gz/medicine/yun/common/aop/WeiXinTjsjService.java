package com.gz.medicine.yun.common.aop;

import com.google.gson.Gson;
import com.gz.medicine.common.util.HttpRequest;
import com.gz.medicine.common.util.PropertyUtil;
import com.gz.medicine.yun.common.bean.InsertVo;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by dlf on 2017/12/28 0028.
 * Email 1429264916@qq.com
 */
@Service("WeiXinTjsjService")
public class WeiXinTjsjService {
    public static final Logger LOGGER = Logger.getLogger(WeiXinTjsjService.class);

    public void AfterWeiXinTjsj(JoinPoint joinPoint, Object retValue) {
        if (retValue instanceof InsertVo) {
            InsertVo vo = (InsertVo) retValue;
            String getId = PropertyUtil.getPropery("weixin.getId");
            Map<String, String> param = new HashedMap();
            param.put("idcard", vo.getIdcard());
            String getIdResponse = HttpRequest.sendPost(getId, param);
            Gson gn = new Gson();
            Map<String, Object> getIdMap = gn.fromJson(getIdResponse, Map.class);
            if ("000".equals(getIdMap.get("code"))) {
                String guid=null;
                if (!StringUtils.isEmpty(getIdMap.get("data"))) {
                    Map<String, Object> usrMap = (Map<String, Object>) getIdMap.get("data");
                     guid = (String) usrMap.get("guid");
                }else{
                    guid=vo.getIdcard();
                }
                vo.setUsrguid(guid);
                String inserttjsj = PropertyUtil.getPropery("weixin.inserttjsj");
                Map inserttjsjParam = gn.fromJson(gn.toJson(vo), Map.class);
                String inserttjsjResponse = HttpRequest.sendPost(inserttjsj, inserttjsjParam);
                Map inserttjsjMap = gn.fromJson(inserttjsjResponse, Map.class);
                if (!"000".equals(inserttjsjMap.get("code"))) {
                    LOGGER.error("同步微信体检数据异常：" + inserttjsj + "接口异常");
                }
            }

        } else {
            LOGGER.error("WeiXinTjsjService数据类型错误！");
        }

    }




}
