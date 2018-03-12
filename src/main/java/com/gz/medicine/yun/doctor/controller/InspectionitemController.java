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


import com.gz.medicine.yun.doctor.bean.Inspectionitem;
import com.gz.medicine.yun.doctor.service.InspectionitemService;

/**
 * @version V1.0
 * @Author fendo
 * @ClassName InspectionitemController
 * @PackageName com.gz.medicine.yun.doctor.controller
 * @Description 检查项目 Controller
 * @Data 2018年01月02日 14时46分37秒 星期二 
 **/
@Controller
@RequestMapping("inspectionitem")
public class InspectionitemController {

	public static final Logger LOGGER = Logger.getLogger(InspectionitemController.class);

    @Autowired
    private InspectionitemService inspectionitemService;

    /**
     *
     *@Title findById
     *@Description: 根据ID获取检查项目
     *@Author fendo
     *@Date 2018年01月02日 14时46分37秒 星期二 
     *@param id(主键)
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/inspectionitem/findById?id=xxxx
     */
    @RequestMapping(value="findById",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult findById(String id){
        LOGGER.info("[/InspectionitemController/findById]");
		SimpleResult simpleResult;
		Inspectionitem inspectionitem;
        try {
            simpleResult = new SimpleResult();
            simpleResult.put("code","000");
            simpleResult.put("message","成功");
            inspectionitem = inspectionitemService.find(id);
            simpleResult.put("data",inspectionitem);
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
     *@Date 2018年01月02日 14时46分37秒 星期二 
     *@param page(分页数据)
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/inspectionitem/getPage?pageNo=2&pageSize=1&name=三
     */
    @RequestMapping(value="getPage",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult getPage(PageModel page){
        LOGGER.info("[/InspectionitemController/getPage]");
        SimpleResult simpleResult;
        Page p;
        try {
            simpleResult = new SimpleResult();
            simpleResult.put("code","000");
            simpleResult.put("message","成功");
            p = inspectionitemService.getPage(page);
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
     *@Description: 新建检查项目
     *@Author fendo
     *@Date 2018年01月02日 14时46分37秒 星期二 
     *@param inspectionitem
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/inspectionitem/create?xxx
     */
    @RequestMapping(value="create",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult create(Inspectionitem inspectionitem){
    	SimpleResult simpleResult;
	    LOGGER.info("[/InspectionitemController/create]");
        try {
            simpleResult=SimpleResult.success();
            inspectionitemService.insert(inspectionitem);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult; 
    }

    /**
     *
     *@Title deleteById
     *@Description: 根据主键删除检查项目
     *@Author fendo
     *@Date 2018年01月02日 14时46分37秒 星期二 
     *@param id
     *@return SimpleResult
     *@throws
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
    public SimpleResult deleteById(String id) throws Exception {
        SimpleResult simpleResult;
	    LOGGER.info("[/InspectionitemController/deleteById]");
        try {
            simpleResult=SimpleResult.success();
            inspectionitemService.delete(id);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }

    /**
     *
     *@Title update
     *@Description: 更新检查项目
     *@Author fendo
     *@Date 2018年01月02日 14时46分37秒 星期二 
     *@param inspectionitem
     *@return SimpleResult
     *@throws
     */
    @RequestMapping(value = "update", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult update(Inspectionitem inspectionitem) throws Exception {
        SimpleResult simpleResult;
	    LOGGER.info("[/InspectionitemController/update]");
        try {
            simpleResult=SimpleResult.success();
            inspectionitemService.update(inspectionitem);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }

}