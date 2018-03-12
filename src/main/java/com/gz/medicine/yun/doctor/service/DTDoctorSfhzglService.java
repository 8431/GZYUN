package com.gz.medicine.yun.doctor.service;


import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.common.mybatisPageVo.Page;
import com.gz.medicine.common.mybatisPageVo.PageModel;
import com.gz.medicine.common.util.SimpleResult;
import com.gz.medicine.yun.doctor.bean.DTfollowupPlan;
import com.gz.medicine.yun.doctor.reponse.DTfollowupPlanResponse;
import com.gz.medicine.yun.doctor.request.DTfollowupPlanRequest;
import com.gz.medicine.yun.doctor.request.DTfollowupRecordRequestV2;

import java.util.List;
import java.util.Map;

public interface DTDoctorSfhzglService {

    /**
     * 查询随访患者管理分页
     * @param p
     * @return
     * @throws CommonException
     */
    SimpleResult queryPageSfhzgl(PageModel p) throws CommonException;

    /**
     * 新增随访计划
     * @param dpLi
     * @return
     * @throws CommonException
     */
    SimpleResult insertDTfollowupPlan(List<DTfollowupPlan> dpLi) throws CommonException;

    /**
     * 随访计划，项目列表
     * @return
     * @throws CommonException
     */
    SimpleResult querySfjhList()throws CommonException;

    /**
     * pc端APP端患者随访记录
     * @param p
     * @return
     * @throws CommonException
     */
    SimpleResult queryPageHzsfjh(PageModel p)throws CommonException;

    
    
    /**
     * 云随访诊室结束随访接口 根据患者GUID
     * @author 舵主
     *
     * 下午6:05:04
     */
	SimpleResult setFollowUpEnd(DTfollowupPlanRequest data) throws CommonException;

    /**
     * 查询某个患者的随访计划
     * @param docguid 医生guid
     * @param usrguid 患者guid
     * @return
     * @throws CommonException
     */
	SimpleResult queryPlan(String docguid,String usrguid) throws CommonException;

    /**
     * 云诊室随访患者信息
     *  2017、09、04 新增   by dlf
     * @param guid
     * @return
     * @throws CommonException
     */
	SimpleResult querySfjhToYzs(String guid) throws CommonException;

    /**
     * 更新随访患者计划状态
     * @param id
     * @param state
     * @param key
     * @throws CommonException
     */
	void updatePlanState(String id, String state, boolean key)throws CommonException;

    /**
     * 开始随访，结束随访
     * @param id
     * @param state
     * @throws CommonException
     */
    void stateKeyForSfjh(String id, String state)throws CommonException;
    void saveSfjl(DTfollowupRecordRequestV2 df)throws CommonException;

    /**
     * 云诊室-待随访，已关闭列表成员
     * @param page
     * @return
     * @throws CommonException
     */
    Page queryYzsDetailForMember(PageModel page) throws CommonException;

}
