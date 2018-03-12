/*
* Copyright(C) 2017-2020 贯众健康公司
* @date 2017-12-02
*/
package com.gz.medicine.yun.doctor.controller;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.common.mybatisPageVo.Page;
import com.gz.medicine.common.mybatisPageVo.PageModel;
import com.gz.medicine.common.util.SimpleCode;
import com.gz.medicine.common.util.SimpleResult;
import com.gz.medicine.yun.doctor.bean.DiagReport;
import com.gz.medicine.yun.doctor.service.DiagReportService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version V1.0
 * @Author fendo
 * @ClassName DiagReportController
 * @PackageName com.gz.medicine.yun.doctor.controller
 * @Description 奕诊 Controller
 * @Data 2018年01月04日 10时29分19秒 星期四 
 **/
@Controller
@RequestMapping("diagreport")
public class DiagReportController {

	public static final Logger LOGGER = Logger.getLogger(DiagReportController.class);

    @Autowired
    private DiagReportService diagReportService;

    /**
     *
     *@Title findById
     *@Description: 根据ID获取奕诊
     *@Author fendo
     *@Date 2018年01月04日 10时29分19秒 星期四 
     *@param id(主键)
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/diagreport/findById?id=xxxx
     */
    @RequestMapping(value="findById",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult findById(String id){
        LOGGER.info("[/DiagReportController/findById]");
		SimpleResult simpleResult;
		DiagReport diagReport;
        try {
            simpleResult = new SimpleResult();
            simpleResult.put("code","000");
            simpleResult.put("message","成功");
            diagReport = diagReportService.find(id);
            simpleResult.put("data",diagReport);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }

    /**
     *
     *@Title findById
     *@Description: 根据用户ID获取最新一条奕诊
     *@Author fendo
     *@Date 2018年01月04日 10时29分19秒 星期四
     *@param id(主键)
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/diagreport/findByUserId?id=AAA9E0A9B207581AE040007F010063F7
     */
    @RequestMapping(value="findByUserId",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult findByUserId(String id){
        LOGGER.info("[/DiagReportController/findById]");
        SimpleResult simpleResult;
        DiagReport diagReport;
        try {
            simpleResult = new SimpleResult();
            simpleResult.put("code","000");
            simpleResult.put("message","成功");
            diagReport = diagReportService.findByUserId(id);
            simpleResult.put("data",diagReport);
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
     *@Date 2018年01月04日 10时29分19秒 星期四 
     *@param page(分页数据)
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/diagreport/getPage?pageNo=2&pageSize=1
     */
    @RequestMapping(value="getPage",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult getPage(PageModel page){
        LOGGER.info("[/DiagReportController/getPage]");
        SimpleResult simpleResult;
        Page p;
        try {
            simpleResult = new SimpleResult();
            simpleResult.put("code","000");
            simpleResult.put("message","成功");
            p = diagReportService.getPage(page);
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
     *@Description: 新建奕诊
     *@Author fendo
     *@Date 2018年01月04日 10时29分19秒 星期四 
     *@param diagReport
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/diagreport/create?xxx
     */
    @RequestMapping(value="create",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult create(DiagReport diagReport){
    	SimpleResult simpleResult;
	    LOGGER.info("[/DiagReportController/create]");
        try {
            simpleResult=SimpleResult.success();
            diagReportService.insert(diagReport);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult; 
    }

    /**
     *
     *@Title deleteById
     *@Description: 根据主键删除奕诊
     *@Author fendo
     *@Date 2018年01月04日 10时29分19秒 星期四 
     *@param id
     *@return SimpleResult
     *@throws
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
    public SimpleResult deleteById(String id) throws Exception {
        SimpleResult simpleResult;
	    LOGGER.info("[/DiagReportController/deleteById]");
        try {
            simpleResult=SimpleResult.success();
            diagReportService.delete(id);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }

    /**
     *
     *@Title update
     *@Description: 更新奕诊
     *@Author fendo
     *@Date 2018年01月04日 10时29分19秒 星期四 
     *@param diagReport
     *@return SimpleResult
     *@throws
     */
    @RequestMapping(value = "update", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult update(DiagReport diagReport) throws Exception {
        SimpleResult simpleResult;
	    LOGGER.info("[/DiagReportController/update]");
        try {
            simpleResult=SimpleResult.success();
            diagReportService.update(diagReport);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }

}