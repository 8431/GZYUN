package com.gz.medicine.yun.common.controller;

import com.google.gson.Gson;
import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.yun.common.service.YtjService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2017/12/26 0026.
 * Email 1429264916@qq.com
 * generalJsonSync
 */
@RestController
@RequestMapping("/Data")
public class YtjController {
    @Autowired
    YtjService ytjservice;

    @RequestMapping(value = "/Upload", method = RequestMethod.POST)
    public Map<String, Object> Upload(HttpServletRequest request) {
        Map<String, Object>  rest=request.getParameterMap();
        String[] InspectList=((String[]) rest.get("InspectList"));
        Gson gn=new Gson();
        List<Map<String, Object>> param=gn.fromJson(InspectList[0],List.class);
        Map<String, Object> sr = new HashedMap();
        try {
            ytjservice.insertYtjData(param);
        } catch (CommonException e) {
            sr.put("resultMsg", "系统内部异常：" + e.getDesc());
            sr.put("resultCode", e.getCode());
            sr.put("logTime", new Date().getTime());
            return sr;
        }
        sr.put("logTime", new Date().getTime());
        sr.put("resultMsg", "同步成功");
        sr.put("resultCode", "00");
        return sr;
    }


}
