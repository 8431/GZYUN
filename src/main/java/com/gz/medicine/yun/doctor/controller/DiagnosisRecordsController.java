/*
* Copyright(C) 2017-2020 贯众健康公司
* @date 2017-12-02
*/
package com.gz.medicine.yun.doctor.controller;

import com.gz.medicine.common.mybatisPageVo.Page;
import com.gz.medicine.common.mybatisPageVo.PageModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.common.util.SimpleCode;
import com.gz.medicine.common.util.SimpleResult;


import com.gz.medicine.yun.doctor.bean.DiagnosisRecords;
import com.gz.medicine.yun.doctor.service.DiagnosisRecordsService;

/**
 * @version V1.0
 * @Author fendo
 * @ClassName DiagnosisRecordsController
 * @PackageName com.gz.medicine.yun.doctor.controller
 * @Description 诊断表_病历 Controller
 * @Data 2018年01月08日 10时34分34秒 星期一 
 **/
@Controller
@RequestMapping("diagnosisrecords")
public class DiagnosisRecordsController {

	public static final Logger LOGGER = Logger.getLogger(DiagnosisRecordsController.class);

    @Autowired
    private DiagnosisRecordsService diagnosisRecordsService;

    /**
     *
     *@Title findById
     *@Description: 根据ID获取诊断表_病历
     *@Author fendo
     *@Date 2018年01月08日 10时34分34秒 星期一 
     *@param id(主键)
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/diagnosisrecords/findById?id=xxxx
     */
    @RequestMapping(value="findById",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult findById(String id){
        LOGGER.info("[/DiagnosisRecordsController/findById]");
		SimpleResult simpleResult;
		DiagnosisRecords diagnosisRecords;
        try {
            simpleResult = new SimpleResult();
            simpleResult.put("code","000");
            simpleResult.put("message","成功");
            diagnosisRecords = diagnosisRecordsService.find(id);
            simpleResult.put("data",diagnosisRecords);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }
    
    
    /**
     *
     *@Title getPage
     *@Description: 分页查询
     *@Author fendo
     *@Date 2018年01月08日 10时34分34秒 星期一 
     *@param page(分页数据)
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/diagnosisrecords/getPage?pageNo=2&pageSize=1
     */
    @RequestMapping(value="getPage",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult getPage(PageModel page){
        LOGGER.info("[/DiagnosisRecordsController/getPage]");
        SimpleResult simpleResult;
        Page p;
        try {
            simpleResult = new SimpleResult();
            simpleResult.put("code","000");
            simpleResult.put("message","成功");
            p = diagnosisRecordsService.getPage(page);
            simpleResult.put("data",p);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }

    /**
     *
     *@Title create
     *@Description: 新建诊断表_病历
     *@Author fendo
     *@Date 2018年01月08日 10时34分34秒 星期一 
     *@param diagnosisRecords
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/diagnosisrecords/create?xxx
     */
    @RequestMapping(value="create",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult create(DiagnosisRecords diagnosisRecords){
    	SimpleResult simpleResult;
	    LOGGER.info("[/DiagnosisRecordsController/create]");
        try {
            simpleResult=SimpleResult.success();
            diagnosisRecordsService.insert(diagnosisRecords);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult; 
    }

    /**
     *
     *@Title deleteById
     *@Description: 根据主键删除诊断表_病历
     *@Author fendo
     *@Date 2018年01月08日 10时34分34秒 星期一 
     *@param id
     *@return SimpleResult
     *@throws
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
    public SimpleResult deleteById(String id) throws Exception {
        SimpleResult simpleResult;
	    LOGGER.info("[/DiagnosisRecordsController/deleteById]");
        try {
            simpleResult=SimpleResult.success();
            diagnosisRecordsService.delete(id);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }

    /**
     *
     *@Title update
     *@Description: 更新诊断表_病历
     *@Author fendo
     *@Date 2018年01月08日 10时34分34秒 星期一 
     *@param diagnosisRecords
     *@return SimpleResult
     *@throws
     */
    @RequestMapping(value = "update", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult update(DiagnosisRecords diagnosisRecords) throws Exception {
        SimpleResult simpleResult;
	    LOGGER.info("[/DiagnosisRecordsController/update]");
        try {
            simpleResult=SimpleResult.success();
            diagnosisRecordsService.update(diagnosisRecords);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }

}