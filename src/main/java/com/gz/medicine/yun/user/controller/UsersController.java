package com.gz.medicine.yun.user.controller;
import com.alipay.api.domain.CardInfo;
import com.google.gson.Gson;
import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.common.util.ValidateWithException;

import javax.validation.Valid;
import javax.validation.Validator;

import com.gz.medicine.yun.user.bean.Usr;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.medicine.common.util.SimpleCode;
import com.gz.medicine.common.util.SimpleResult;
import com.gz.medicine.yun.user.request.SelUserRequest;
import com.gz.medicine.yun.user.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/yunUser")
public class UsersController extends ValidateWithException {
    
    public static final Logger LOGGER = Logger.getLogger(UsersController.class);
    
    @Autowired
    private UserService userService;
    @Autowired
    Validator validator; 
    
    @RequestMapping(value = "/yunGetUser", method = RequestMethod.POST,produces="text/html;charset=UTF-8")  
    @ResponseBody
    public SimpleResult user(@Valid SelUserRequest data ) {
    	  SimpleResult sr=null;
    	  String pwd = null;
          try {
        	  if(validates(validator, data)!=null){
        		  return SimpleResult.error(SimpleCode.ERROR.getCode(), validates(validator, data)); 
        	  }
        	  pwd= userService.queryUser(data);
		} catch (CommonException e) {
			LOGGER.error(e);
			return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc()); 
		}
        sr = SimpleResult.success(); 
        sr.putData("pwd", pwd); 
        return  sr;
    }

    /**
     *
     *@Title findById
     *@Description: 根据ID获取用户
     *@Author fendo
     *@Date 2017年12月26日 12时18分34秒 星期二
     *@param id(主键)
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/yunUser/findById?id=xxxx
     */
    @RequestMapping(value="findById",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult findById(String id,String institutionsId){
        LOGGER.info("[/DiagnosisController/findById]");
        SimpleResult simpleResult;
        Usr usr;
        try {
            simpleResult = SimpleResult.success();
            if(StringUtils.isEmpty(id)){
                return SimpleResult.error(SimpleCode.ERROR.getCode(), "用户ID不能为空!!");
            }
            usr = userService.findById(id);
            //根据身份证获取年龄
            String idcard=usr.getIdcard();
            if(StringUtils.isNotEmpty(idcard)){
                usr.setSex(CardInfo(idcard));
                if(CardAge(idcard)!=0){
                    usr.setAge(""+CardAge(idcard));
                }
            }
            simpleResult.put("data",usr);
            if(StringUtils.isNotEmpty(institutionsId)){
                String hopflg = userService.findByLocId(institutionsId);
                if(hopflg!=null){
                    if("1".equals(hopflg)){
                        simpleResult.put("hopflg","远程医疗");
                    }else {
                        simpleResult.put("hopflg","远程咨询");
                    }
                }else {
                    simpleResult.put("hopflg","远程咨询");
                }
            }

        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }

    public static void main(String[] args) {
        //String re="{'data':{'name':'200.00'}}";
        //Gson gn=new Gson();
        //Map<String,String> mp=gn.fromJson(re, Map.class);
        System.out.println(new UsersController().CardInfo("511023199910180989"));
    }

    public String CardInfo(String card){
        String sex = null;
        System.out.println(card.length());
        if(18 == card.length()){
            if (Integer.parseInt(card.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别
                sex = "2";
            } else {
                sex = "1";
            }
        }else if(15 == card.length()){
            String usex = card.substring(14, 15);// 用户的性别
            if (Integer.parseInt(usex) % 2 == 0) {// 判断性别
                sex = "2";
            } else {
                sex = "1";
            }
        }
        return sex;
    }

    public Integer CardAge(String idcard){
        Integer age = 0;
        //根据身份证获取年龄
        if(!"".equals(idcard)&&idcard!=null){
            int leh = idcard.length();
            String dates="";
            if (leh == 18) {
                //int se = Integer.valueOf(idcard.substring(leh - 1)) % 2;
                dates = idcard.substring(6, 10);
                SimpleDateFormat df = new SimpleDateFormat("yyyy");
                String year=df.format(new Date());
                age=Integer.parseInt(year)-Integer.parseInt(dates);
            }else{
                dates = idcard.substring(6, 8);
                age=Integer.parseInt(dates);
            }

        }
        return age;
    }
}
