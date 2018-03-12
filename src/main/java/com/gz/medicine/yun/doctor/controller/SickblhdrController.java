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
import com.gz.medicine.common.util.UUIDTool;
import com.gz.medicine.common.util.ValidateWithException;
import com.gz.medicine.yun.doctor.bean.*;
import com.gz.medicine.yun.doctor.mapper.DTfollowupPlanMapper;
import com.gz.medicine.yun.doctor.mapper.DTsickbldtlMapper;
import com.gz.medicine.yun.doctor.request.DTsickbldtlRequestList;
import com.gz.medicine.yun.doctor.request.SickbldtlRequest;
import com.gz.medicine.yun.doctor.request.SickblhdrRequest;
import com.gz.medicine.yun.doctor.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Validator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @version V1.0
 * @Author fendo
 * @ClassName SickblhdrController
 * @PackageName com.gz.medicine.yun.doctor.controller
 * @Description 病历表 Controller
 * @Data 2017年12月22日 10时23分36秒 星期五 
 **/
@Controller
@RequestMapping("sickblhdr")
public class SickblhdrController extends ValidateWithException {

	public static final Logger LOGGER = Logger.getLogger(SickblhdrController.class);

    @Autowired
    private SickblhdrService sickblhdrService;

    /**
     * 用户
     */
    @Autowired
    private DTUsrService dtUsrService;

    /**
     * 病历
     */
    @Autowired
    private DTCaseHistoryService dtCaseHistoryService;

    @Autowired
    private DTfollowupPlanService dTfollowupPlanService;

    @Autowired
    private DTfollowupPlanMapper dTfollowupPlanMapper;

    @Autowired
    private InspectionItemsService inspectionItemsService;

    @Autowired
    private DTsickbldtlMapper dTsickbldtlMapper;

    /**
     * 随访计划查询
     */
    @Autowired
    private DTDocSfConRoomService dTDocSfConRoomService;

    @Autowired
    Validator validator;

    @Autowired
    private DiagnosisRecordsService diagnosisRecordsService;


    /**
     *
     *@Title findByWay
     *@Description: 查询途径
     *@Author fendo
     *@Date 2017年12月22日 10时23分36秒 星期五
     *@param
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/sickblhdr/findByWay
     */
    @RequestMapping(value="findByWay",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult findByWay(PageModel page){
        LOGGER.info("[/SickblhdrController/findById]");
        SimpleResult simpleResult;
        List<Map<String, Object>> mapList;
        Page p = null;
        try {
            simpleResult = new SimpleResult();
            simpleResult.put("code","000");
            simpleResult.put("message","成功");
            p = sickblhdrService.queryPageAllWay(page);
            simpleResult.put("data",p);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }

    /**
     *
     *@Title findByFrequency
     *@Description: 查询频次
     *@Author fendo
     *@Date 2017年12月22日 10时23分36秒 星期五
     *@param
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/sickblhdr/findByFrequency?name=
     */
    @RequestMapping(value="findByFrequency",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult findByFrequency(PageModel page){
        LOGGER.info("[/SickblhdrController/findById]");
        SimpleResult simpleResult;
        Page p = null;
        List<Map<String, Object>> mapList;
        try {
            simpleResult = new SimpleResult();
            simpleResult.put("code","000");
            simpleResult.put("message","成功");
            p = sickblhdrService.queryPageFrequency(page);
            simpleResult.put("data",p);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }



    /**
     *
     *@Title findById
     *@Description: 根据ID获取病历表
     *@Author fendo
     *@Date 2017年12月22日 10时23分36秒 星期五 
     *@param id(主键)
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/sickblhdr/findById?id=xxxx
     */
    @RequestMapping(value="findById",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult findById(String id){
        LOGGER.info("[/SickblhdrController/findById]");
		SimpleResult simpleResult;
		Sickblhdr sickblhdr;
        try {
            simpleResult = new SimpleResult();
            simpleResult.put("code","000");
            simpleResult.put("message","成功");
            sickblhdr = sickblhdrService.find(id);
            simpleResult.put("data",sickblhdr);
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
     *@Date 2017年12月22日 10时23分36秒 星期五 
     *@param page(分页数据)
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/sickblhdr/getPage?pageNo=2&pageSize=1
     */
    @RequestMapping(value="getPage",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult getPage(PageModel page){
        LOGGER.info("[/SickblhdrController/getPage]");
        SimpleResult simpleResult;
        Page p;
        try {
            simpleResult = new SimpleResult();
            simpleResult.put("code","000");
            simpleResult.put("message","成功");
            p = sickblhdrService.getPage(page);
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
     *@Description: 新建病历表-bean
     *@Author fendo
     *@Date 2017年12月22日 10时23分36秒 星期五 
     *@param sickblhdr
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/sickblhdr/create?xxx
     */
    @RequestMapping(value="create",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult create(Sickblhdr sickblhdr){
    	SimpleResult simpleResult;
	    LOGGER.info("[/SickblhdrController/create]");
        try {
            simpleResult=SimpleResult.success();
            sickblhdrService.insert(sickblhdr);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult; 
    }

    /**
     *
     *@Title create
     *@Description: 新建病历表-Map
     *@Author fendo
     *@Date 2017年12月22日 10时23分36秒 星期五
     *@param map
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/sickblhdr/create?xxx
     */
    @RequestMapping(value="createSickblhdr",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult createSickblhdr(@RequestBody Map map){
        SimpleResult simpleResult;
        Sickblhdr sickblhdr = new Sickblhdr();
        InspectionItems inspectionItems = null;
        DTfollowupPlan dTfollowupPlan = null;
        SickblhdrRequest sickblhdrRequest = null;
        SickbldtlRequest sickbldtlRequest = null;
        DiagnosisRecords diagnosisRecords = null;
        List<DiagnosisRecords> diagnosisRecordsList = new ArrayList<DiagnosisRecords>();
        StringBuffer stringBufferID = new StringBuffer();
        StringBuffer stringBufferName = new StringBuffer();
        DTsickbldtl dTsickbldtl = new DTsickbldtl();
        String docguid;
        String sickguid;
        JSONObject jsonObj = null;
        String sickUUID = null;
        LOGGER.info("[/SickblhdrController/createSickblhdr]");
        try {
            simpleResult = SimpleResult.success();
            sickUUID = UUIDTool.getUUID();
            JSONObject jsonObject = JSONObject.fromObject(map);
            String userid = jsonObject.getString("userid");
            String docid = jsonObject.getString("docid");
            //医生或用户ID不为空
            if(StringUtils.isNotEmpty(userid)||StringUtils.isNotEmpty(docid)){
                sickblhdrRequest = new SickblhdrRequest();
                sickblhdrRequest.setGuid(sickUUID);
                sickblhdrRequest.setSickguid(userid); //患者ID
                sickblhdrRequest.setDocguid(docid); //医生ID
                sickblhdrRequest.setState("0"); //设置状态为0
                //新增病历
                BeanUtils.copyProperties(sickblhdrRequest,sickblhdr);
                sickblhdrService.insert(sickblhdr);
            }else {

                JSONArray sickList = (JSONArray)jsonObject.fromObject(map).get("sick");
                if(sickList.size()>0) {
                    //判断是自动还是手动保存
                    if(!"".equals(jsonObject.getString("flag")) && jsonObject.getString("flag")!=null){

                        jsonObj = JSONObject.fromObject(sickList.get(0));
                        sickblhdrRequest = (SickblhdrRequest)JSONObject.toBean(jsonObj, SickblhdrRequest.class);
                        //判断病历ID是否为空
                        if("".equals(sickblhdrRequest.getGuid()) || sickblhdrRequest.getGuid() == null ){
                            //设置主键
                            //sickUUID = UUIDTool.getUUID();
                        }else {
                            sickUUID = sickblhdrRequest.getGuid();
                            //先删除随访计划
                            dTfollowupPlanService.deleteByFollowupPlan(sickUUID);
                            //检查项删除
                            inspectionItemsService.deleteByInspectionItems(sickUUID);
                            //先删除用药方案
                            sickblhdrService.deleteDTsickbldtl(sickUUID);
                            //先删除诊断
                            diagnosisRecordsService.deleteOK(sickUUID);
                            //先删除病历
                            sickblhdrService.deleteOK(sickUUID);
                        }

                        //1手动 做校验
                        if("1".equals(jsonObject.getString("flag"))){
                            //患者性别
                            if(jsonObj.get("sex")!=null){
                                String sex= jsonObj.get("sex").toString();
                                if(!"".equals(sex)&&sex!=null){
                                    if("男".equals(sex)){
                                        sex="1";
                                    }else if("女".equals(sex)){
                                        sex="2";
                                    }else {
                                        sickblhdrRequest.setSex(sex);
                                    }
                                }else {
                                    return SimpleResult.error(SimpleCode.ERROR.getCode(), "患者性别不能为空！");
                                }
                            }else {
                                return SimpleResult.error(SimpleCode.ERROR.getCode(), "患者性别不能为空！");
                            }

                            //心率: 数字
                            if(jsonObj.get("xl") != null){
                                String xl = jsonObj.get("xl").toString();
                                if(!"".equals(xl) && xl != null){
                                    if(isNumeric(xl)){
                                        sickblhdrRequest.setXl(xl);
                                    }else {
                                        return SimpleResult.error(SimpleCode.ERROR.getCode(), "心率只能是整数型,不能带小数点!");
                                    }

                                }
                            }
                            //血压上: 数字
                            if(jsonObj.get("xy1") != null){
                                String xy1 = jsonObj.get("xy1").toString();
                                if(!"".equals(xy1) && xy1 != null){
                                    if(isNumeric(xy1)){
                                        sickblhdrRequest.setXy1(xy1);
                                    }else {
                                        return SimpleResult.error(SimpleCode.ERROR.getCode(), "血压上只能是整数型,不能带小数点!");
                                    }

                                }
                            }
                            //血压下: 数字
                            if(jsonObj.get("xy2") != null){
                                String xy2 = jsonObj.get("xy2").toString();
                                if(!"".equals(xy2) && xy2 != null){
                                    if(isNumeric(xy2)){
                                        sickblhdrRequest.setXy2(xy2);
                                    }else {
                                        return SimpleResult.error(SimpleCode.ERROR.getCode(), "血压下只能是整数型,不能带小数点!");
                                    }

                                }
                            }
                            //体重: 数字
                            if(jsonObj.get("weight") != null){
                                String weight = jsonObj.get("weight").toString();
                                if(!"".equals(weight) && weight != null){
                                    if(isNumeric(weight)){
                                        sickblhdrRequest.setWeight(weight);
                                    }else {
                                        return SimpleResult.error(SimpleCode.ERROR.getCode(), "体重只能是整数型,不能带小数点!");
                                    }

                                }
                            }
                            //血氧: 数字
                            if(jsonObj.get("glucose") != null){
                                String glucose = jsonObj.get("glucose").toString();
                                if(!"".equals(glucose) && glucose != null){
                                    if(isNumeric(glucose)){
                                        sickblhdrRequest.setGlucose(glucose);
                                    }else {
                                        return SimpleResult.error(SimpleCode.ERROR.getCode(), "血氧只能是整数型,不能带小数点!");
                                    }

                                }
                            }

                            ////预约时间
                            //if(jsonObj.get("clinicaltime") != null && !"".equals(jsonObj.get("clinicaltime"))){
                            //    String clinicaltime = jsonObj.get("clinicaltime").toString();
                            //    if(!"".equals(clinicaltime) && clinicaltime != null){
                            //        //SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                            //        //String clinic = date.format(clinicaltime);
                            //        sickblhdrRequest.setClinicaltime(clinicaltime);
                            //    }
                            //}

                            if(validates(validator, sickblhdrRequest)!=null){
                                return SimpleResult.error(SimpleCode.ERROR.getCode(), validates(validator, sickblhdrRequest));
                            }

                        //2自动
                        }else if("2".equals(jsonObject.getString("flag"))){
                            //患者性别
                            if(jsonObj.get("sex")!=null){
                                String sex= jsonObj.get("sex").toString();
                                if(!"".equals(sex)&&sex!=null){
                                    if("男".equals(sex)){
                                        sex="1";
                                    }else if("女".equals(sex)){
                                        sex="2";
                                    }else {
                                        sickblhdrRequest.setSex(sex);
                                    }
                                }
                            }
                            //心率: 数字
                            if(jsonObj.get("xl") != null){
                                String xl = jsonObj.get("xl").toString();
                                if(!"".equals(xl) && xl != null){
                                    if(isNumeric(xl)){
                                        sickblhdrRequest.setXl(xl);
                                    }
                                }
                            }
                            //血压上: 数字
                            if(jsonObj.get("xy1") != null){
                                String xy1 = jsonObj.get("xy1").toString();
                                if(!"".equals(xy1) && xy1 != null){
                                    if(isNumeric(xy1)){
                                        sickblhdrRequest.setXy1(xy1);
                                    }
                                }
                            }
                            //血压下: 数字
                            if(jsonObj.get("xy2") != null){
                                String xy2 = jsonObj.get("xy2").toString();
                                if(!"".equals(xy2) && xy2 != null){
                                    if(isNumeric(xy2)){
                                        sickblhdrRequest.setXy2(xy2);
                                    }
                                }
                            }
                            //体重: 数字
                            if(jsonObj.get("weight") != null){
                                String weight = jsonObj.get("weight").toString();
                                if(!"".equals(weight) && weight != null){
                                    if(isNumeric(weight)){
                                        sickblhdrRequest.setWeight(weight);
                                    }
                                }
                            }
                            //血氧: 数字
                            if(jsonObj.get("glucose") != null){
                                String glucose = jsonObj.get("glucose").toString();
                                if(!"".equals(glucose) && glucose != null){
                                    if(isNumeric(glucose)){
                                        sickblhdrRequest.setGlucose(glucose);
                                    }

                                }
                            }

                            sickblhdrRequest.setState("0"); //设置状态为0
                        }else {
                            return SimpleResult.error(SimpleCode.ERROR.getCode(), "请输入标志位:是手动还是自动");
                        }


                        docguid = jsonObj.get("docguid").toString();
                        sickguid = jsonObj.get("sickguid").toString();
                        //诊断
                        JSONArray diagnosisarray = (JSONArray)jsonObject.fromObject(map).get("diagnosis");
                        if(diagnosisarray.size()>0){
                            for (int i = 0; i < diagnosisarray.size(); i++) {
                                diagnosisRecords = new DiagnosisRecords();
                                diagnosisRecords.setSid(sickUUID);
                                diagnosisRecords.setId(UUIDTool.getUUID());
                                JSONObject jso = JSONObject.fromObject(diagnosisarray.get(i));
                                diagnosisRecords.setZid(jso.getString("zhand"));
                                diagnosisRecords.setZname(jso.getString("zdname"));
                                diagnosisRecordsList.add(diagnosisRecords);
                                if(i==0){
                                    stringBufferID.append(diagnosisRecords.getZid());
                                    stringBufferName.append(diagnosisRecords.getZname());
                                }else {
                                    stringBufferID.append(","+diagnosisRecords.getZid());
                                    stringBufferName.append(","+diagnosisRecords.getZname());
                                }

                            }
                            sickblhdrRequest.setZhand(stringBufferID.toString());
                            sickblhdrRequest.setZdnam(stringBufferName.toString());
                        }


                        sickblhdrRequest.setGuid(sickUUID);
                        //设置部门
                        sickblhdrRequest.setAcc("CHIS");
                        sickblhdrRequest.setOrg("CHIS");
                        //新增病历
                        BeanUtils.copyProperties(sickblhdrRequest,sickblhdr);
                        //预约时间
                        if(jsonObj.get("clinicaltime") != null && !"".equals(jsonObj.get("clinicaltime"))){
                            String clinicaltime = jsonObj.get("clinicaltime").toString();
                            if(!"".equals(clinicaltime) && clinicaltime != null){
                                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                                //sickblhdrRequest.setClinicaltime(date.format(clinicaltime));
                                try {
                                    sickblhdr.setClinicaltime(date.parse(clinicaltime));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        sickblhdrService.insert(sickblhdr);

                        //新增诊断
                        diagnosisRecordsService.inserList(diagnosisRecordsList);

                        //检查项目
                        JSONArray checkarray = (JSONArray)jsonObject.fromObject(map).get("check");
                        if(checkarray.size()>0) {
                            for (int i = 0; i < checkarray.size(); i++) {
                                jsonObj = JSONObject.fromObject(checkarray.get(i));
                                inspectionItems = (InspectionItems) JSONObject.toBean(jsonObj, InspectionItems.class);
                                inspectionItems.setSickblhdrid(sickUUID); //设置关联关系
                                inspectionItems.setId(UUIDTool.getUUID());
                                //检查项目新增
                                inspectionItemsService.insert(inspectionItems);

                            }
                        }
                        //随访计划
                        JSONArray planarray = (JSONArray)jsonObject.fromObject(map).get("plan");
                        if(planarray.size()>0) {
                            for (int i = 0; i < planarray.size(); i++) {
                                jsonObj = JSONObject.fromObject(planarray.get(i));
                                dTfollowupPlan = (DTfollowupPlan) JSONObject.toBean(jsonObj, DTfollowupPlan.class);
                                dTfollowupPlan.setSicguid(sickUUID);//关联病历
                                dTfollowupPlan.setDocguid(docguid);
                                dTfollowupPlan.setUsrguid(sickguid);
                                //随访时间
                                if(jsonObj.get("followdatetime") != null){
                                    String followdatetime = jsonObj.get("followdatetime").toString();
                                    if(!"".equals(followdatetime) && followdatetime != null){
                                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                        Date time = null;
                                        try {
                                            time = date.parse(followdatetime);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        dTfollowupPlan.setFollowdatetime(time);
                                    }
                                }

                                //新增随访计划
                                dTfollowupPlan.setGuid(UUIDTool.getUUID());
                                dTfollowupPlanService.insertFollowupPlan(dTfollowupPlan);

                            }
                        }
                        //用药方案
                        //将json格式的字符串转换为json数组对象
                        JSONArray dtsarray = (JSONArray)jsonObject.fromObject(map).get("dts");
                        if(dtsarray.size()>0){
                            for (int i = 0; i <dtsarray.size() ; i++) {
                                //取得json数组中的第一个对象
                                jsonObj = JSONObject.fromObject(dtsarray.get(i));
                                sickbldtlRequest = (SickbldtlRequest) JSONObject.toBean(jsonObj, SickbldtlRequest.class);

                                //1手动 做校验
                                if("1".equals(jsonObject.getString("flag"))){
                                    if(validates(validator, sickbldtlRequest)!=null){
                                        return SimpleResult.error(SimpleCode.ERROR.getCode(), validates(validator, sickbldtlRequest));
                                    }
                                }
                                sickbldtlRequest.setFormguid(sickUUID);
                                sickbldtlRequest.setGuid(UUIDTool.getUUID());
                                BeanUtils.copyProperties(sickbldtlRequest,dTsickbldtl);
                                sickblhdrService.insertDTsickbldtl(dTsickbldtl);

                            }
                        }
                    }
                }else {
                    return SimpleResult.error(SimpleCode.ERROR.getCode(), "请代入病历相关数据!");
                }

            }
            simpleResult.putData("guid",sickUUID);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }

    /**
     *
     *@Title create
     *@Description: 根据病历ID获取病历数据
     *@Author fendo
     *@Date 2017年12月22日 10时23分36秒 星期五
     *@param sickid
     *@return SimpleResult
     *@throws
     *@测试地址: http://localhost:8080/GZ/sickblhdr/getSickblhdr?sickid=ff6c16bbf6a741278a5414576d6343a0
     */
    @RequestMapping(value="getSickblhdr",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult getSickblhdr(String sickid){
        SimpleResult simpleResult;
        LOGGER.info("[/SickblhdrController/create]");
        Sickblhdr sickblhdr; //病历
        List<DTsickbldtl> dTsickbldtlList; //用药方案
        List<DTfollowupPlan> dTfollowupPlanList;//随访计划
        List<InspectionItems> inspectionItemsList;//检查项目
        try {
           if(StringUtils.isNotEmpty(sickid)){
               simpleResult = SimpleResult.success();
               sickblhdr = sickblhdrService.find(sickid); //病历
               dTsickbldtlList = dTsickbldtlMapper.selectByform(sickid); //用药方案
               dTfollowupPlanList = dTfollowupPlanService.getFollowupPlanList(sickid); //随访计划
               inspectionItemsList = inspectionItemsService.getInspectionItemsList(sickid);//检查项目
               simpleResult.putData("sickblhdr",sickblhdr);
               simpleResult.putData("dTsickbldtl",dTsickbldtlList);
               simpleResult.putData("dTfollowupplan",dTfollowupPlanList);
               simpleResult.putData("sickblinspectionitems",inspectionItemsList);
           }else {
               return SimpleResult.error(SimpleCode.ERROR.getCode(), "请传入患者ID");
           }
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }

    /**
     *
     *@Title deleteById
     *@Description: 根据机构码获取数据
     *@Author fendo
     *@Date 2017年12月22日 10时23分36秒 星期五
     *@param deptidnum 机构码 xh104
     *@param guid 用户ID 5FBC3F95409E0824E050007F01002E5F
     *@return SimpleResult
     *@测试地址: http://localhost:8080/GZ/sickblhdr/getDate?deptidnum=A001&guid=5FBC3F95409E0824E050007F01002E5F
     *@throws
     */
    @RequestMapping(value = "getDate", method = RequestMethod.GET, produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult getDate(String deptidnum,String guid) throws Exception {
        SimpleResult simpleResult;
        LOGGER.info("[/SickblhdrController/deleteById]");
        //		deptIdNum：机构码
        //		String deptIdNum="52750B1FF4F4D368E053AA0012AC681E";
        //		guid="591D597C76C917F3E050007F01005E79";
        try {
            simpleResult = SimpleResult.success();
            simpleResult = dTDocSfConRoomService.getData(deptidnum,guid);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }


    /**
     *
     *@Title deleteById
     *@Description: 根据主键删除病历表
     *@Author fendo
     *@Date 2017年12月22日 10时23分36秒 星期五 
     *@param id
     *@return SimpleResult
     *@throws
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult deleteById(String id) throws Exception {
        SimpleResult simpleResult;
	    LOGGER.info("[/SickblhdrController/deleteById]");
        try {
            simpleResult=SimpleResult.success();
            sickblhdrService.delete(id);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }

    /**
     *
     *@Title deleteByPlanId
     *@Description: 根据主键删除随访计划
     *@Author fendo
     *@Date 2017年12月22日 10时23分36秒 星期五
     *@param id
     *@return SimpleResult
     *@测试地址: http://localhost:8080/GZ/sickblhdr/deleteByPlanId?id=0a1b0361374d4512bbbb897bf9d2afb3
     *@throws
     */
    @RequestMapping(value = "deleteByPlanId", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult deleteByPlanId(String id) throws Exception {
        SimpleResult simpleResult;
        LOGGER.info("[/SickblhdrController/deleteByDtlId]");
        try {
            simpleResult=SimpleResult.success();
            DTfollowupPlan dTfollowupPlan = new DTfollowupPlan();
            dTfollowupPlan.setGuid(id);
            dTfollowupPlan.setStatus("0");
            dTfollowupPlanMapper.updateByPrimaryKeySelective(dTfollowupPlan);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }

    /**
     *
     *@Title deleteByDtlId
     *@Description: 根据主键删除用药方案
     *@Author fendo
     *@Date 2017年12月22日 10时23分36秒 星期五
     *@param id
     *@return SimpleResult
     *@测试地址: http://localhost:8080/GZ/sickblhdr/deleteByDtlId?id=79fa5f633bbd4d3aa5c797a674da4f27
     *@throws
     */
    @RequestMapping(value = "deleteByDtlId", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult deleteByDtlId(String id) throws Exception {
        SimpleResult simpleResult;
        LOGGER.info("[/SickblhdrController/deleteByDtlId]");
        try {
            simpleResult=SimpleResult.success();
            DTsickbldtl dTsickbldtl = new DTsickbldtl();
            dTsickbldtl.setGuid(id);
            dTsickbldtl.setState("0");
            dTsickbldtlMapper.updateDTsickbldtl(dTsickbldtl);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }
    /**
     *
     *@Title update
     *@Description: 更新病历表
     *@Author fendo
     *@Date 2017年12月22日 10时23分36秒 星期五 
     *@param sickblhdr
     *@return SimpleResult
     *@throws
     */
    @RequestMapping(value = "update", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult update(Sickblhdr sickblhdr) throws Exception {
        SimpleResult simpleResult;
	    LOGGER.info("[/SickblhdrController/update]");
        try {
            simpleResult=SimpleResult.success();
            sickblhdrService.update(sickblhdr);
        }catch (CommonException e){
            LOGGER.error(e);
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getDesc());
        }
        return  simpleResult;
    }

    /**
     * 判断是否是整数
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断对象是否为空
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj)
    {
        if (obj == null)
        {
            return true;
        }
        if ((obj instanceof List))
        {
            return ((List) obj).size() == 0;
        }
        if ((obj instanceof String))
        {
            return ((String) obj).trim().equals("");
        }
        return false;
    }

}