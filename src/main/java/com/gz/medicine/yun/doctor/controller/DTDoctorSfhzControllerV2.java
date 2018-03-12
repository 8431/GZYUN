package com.gz.medicine.yun.doctor.controller;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.common.mybatisPageVo.Page;
import com.gz.medicine.common.mybatisPageVo.PageModel;
import com.gz.medicine.common.util.SimpleCode;
import com.gz.medicine.common.util.SimpleResult;
import com.gz.medicine.yun.doctor.bean.DTfollowupPlan;
import com.gz.medicine.yun.doctor.request.DTfollowupRecordRequestV2;
import com.gz.medicine.yun.doctor.service.DTDoctorSfhzglService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;
import java.util.List;

import static com.gz.medicine.common.util.ValidateWithException.validates;

/**
 * Created by 邓岚峰 on 2017/8/17 0017.
 * <p>
 * （1）未开始：未被点击“开始随访”；
 * <p>
 * （2）随访中：已被开始随访，但未被关闭；
 * <p>
 * （3）已随访：已保存随访信息，被医生关闭；
 * <p>
 * （4）超时未开始：随访超时未开始被自动关闭；
 * <p>
 * （5）未随访被关闭：未保存随访信息，被医生主动关闭；
 * <p>
 * （6）未随访被超时关闭：未保存随访信息，超时自动关闭。
 * （7）已随访：已保存随访信息，超时自动关闭；
 */
@RestController
@RequestMapping("/v2/cloud/")
public class DTDoctorSfhzControllerV2 {
    @Autowired
    DTDoctorSfhzglService dtdoctorsfhzglservice;
    @Autowired
    Validator validator;

    //开机随访，关闭随访接口
    @RequestMapping(value = "/stateKeyForSfjh", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public SimpleResult stateKeyForSfjh(@RequestParam(value = "id", required = true) String id,
                                        @RequestParam(value = "state", required = true) String state) {
        SimpleResult sr = null;
        try {
            dtdoctorsfhzglservice.stateKeyForSfjh(id, state);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(), e.getDesc());
        }
        sr = SimpleResult.success();
        return sr;
    }

    //保存随访计录
    @RequestMapping(value = "/saveSfjl", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public SimpleResult saveSfjl(DTfollowupRecordRequestV2 df) {
        SimpleResult sr = null;
        try {
            if (validates(validator, df) != null) {
                return SimpleResult.error(SimpleCode.ERROR.getCode(), validates(validator, df));
            }
            dtdoctorsfhzglservice.saveSfjl(df);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(), e.getDesc());
        }
        sr = SimpleResult.success();
        return sr;
    }

    //云诊室 待随访-已关闭成员列表查询
    @RequestMapping(value = "/queryYzsDetailForMember", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public SimpleResult follUpTasks(PageModel page) {
        SimpleResult simpleResult = null;
        Page pages = null;
        try {
            pages = dtdoctorsfhzglservice.queryYzsDetailForMember(page);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(), e.getDesc());
        }
        simpleResult = SimpleResult.success();
        simpleResult.put("data", pages);
        return simpleResult;
    }



}
