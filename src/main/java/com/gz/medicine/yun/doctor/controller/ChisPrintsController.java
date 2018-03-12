package com.gz.medicine.yun.doctor.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.common.util.SimpleCode;
import com.gz.medicine.common.util.SimpleResult;
import com.gz.medicine.yun.doctor.bean.ChisPrints;
import com.gz.medicine.yun.doctor.service.ChisPrintsService;

@Controller
@RequestMapping("chisprints")
public class ChisPrintsController {
	
	public static final Logger LOGGER = Logger.getLogger(ChisPrintsController.class);
	
	@Autowired
	private ChisPrintsService chisPrintsService;
	
	@RequestMapping(value = "insert", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
    @ResponseBody
	public SimpleResult insert(ChisPrints record){
		SimpleResult simpleResult;
	    LOGGER.info("[/ChisPrintsController/insert]");
        try {
            simpleResult=SimpleResult.success();
            chisPrintsService.insert(record);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
	}

}
