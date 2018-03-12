package com.gz.medicine.yun.doctor.service.impl;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.common.mybatisPageVo.Page;
import com.gz.medicine.common.mybatisPageVo.PageModel;
import com.gz.medicine.common.util.SimpleResult;
import com.gz.medicine.common.util.TimeUtil;
import com.gz.medicine.yun.doctor.bean.DTfollowupPlan;
import com.gz.medicine.yun.doctor.bean.DTfollowupRecord;
import com.gz.medicine.yun.doctor.common.GzYunCodeEnum;
import com.gz.medicine.yun.doctor.mapper.DTfollowupOptionMapper;
import com.gz.medicine.yun.doctor.mapper.DTfollowupPlanMapper;
import com.gz.medicine.yun.doctor.mapper.DTfollowupRecordMapper;
import com.gz.medicine.yun.doctor.reponse.DTfollowupPlanResponse;
import com.gz.medicine.yun.doctor.request.DTfollowupPlanRequest;
import com.gz.medicine.yun.doctor.request.DTfollowupRecordRequestV2;
import com.gz.medicine.yun.doctor.service.DTDoctorSfhzglService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2017/8/19 0019.
 */
@Service("DTDoctorSfhzglService")
public class DTDoctorSfhzglServiceImpl implements DTDoctorSfhzglService {
    public static final Logger LOGGER = Logger.getLogger(DTDoctorSfhzglServiceImpl.class);

    @Autowired
    DTfollowupOptionMapper dtfollowupoptionmapper;
    @Autowired
    DTfollowupPlanMapper dtfollowupplanmapper;
    @Autowired
    DTfollowupRecordMapper dtfollowuprecordmapper;

    @Override
    public SimpleResult queryPageSfhzgl(PageModel pm) throws CommonException {
        SimpleResult sr = null;
        Page pe = pm.getPage();
        List<Map<String, Object>> mpLi = null;
        List<Map<String, Object>> mpMangeLi = new ArrayList<Map<String, Object>>();
        try {
            mpLi = dtfollowupoptionmapper.queryPageSfhzgl(pe);
            if (mpLi != null && mpLi.size() > 0) {
                for (int i = 0; i < mpLi.size(); i++) {
                    String sickguid = (String) mpLi.get(i).get("sickguid");
                    pe.put("usrguid", sickguid);
                    Map<String, Object> mpMange = dtfollowupoptionmapper.querySfjlList(pe);
                    if (mpMange != null) {
                        mpMange.put("R", i + 1);
                    }
                    mpMangeLi.add(mpMange);
                }

            }
            pe.setParameterType(mpMangeLi);
            sr = SimpleResult.success();
            sr.put("data", pe);
        } catch (Exception e) {
            LOGGER.error("分页查询随访患者管理Server层异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "分页查询随访患者管理Server层异常:", e);
        }
        return sr;
    }

    @Override
    public SimpleResult insertDTfollowupPlan(List<DTfollowupPlan> dpLi) throws CommonException {
        SimpleResult sr = null;
        //最多12条记录，所以不做批量新增更新
        for (DTfollowupPlan d : dpLi) {
            try {
                //判断更新还是新增
                String guid = d.getGuid();
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                if (!StringUtils.isEmpty(d.getFollowtime())) {
                    Date time = date.parse(d.getFollowtime());
                    d.setFollowdatetime(time);
                }

                if (StringUtils.isEmpty(guid)) {
                    d.setGuid(null);
                    d.setCrtdat(new Date());
                    dtfollowupplanmapper.insertSelective(d);
                } else {
                    //更新
                    d.setUpdatedate(new Date());
                    dtfollowupplanmapper.updateByPrimaryKeySelective(d);
                }
                sr = SimpleResult.success();
            } catch (Exception e) {
                LOGGER.error("新增随访记录异常：" + e.getMessage(), e);
                throw new CommonException("COM001", "新增随访记录Server层异常:", e);
            }

        }
        return sr;
    }

    @Override
    public SimpleResult querySfjhList() throws CommonException {
        SimpleResult sr = null;
        try {
            List<Map<String, Object>> li = dtfollowupoptionmapper.querySfjhList();
            sr = SimpleResult.success();
            sr.put("data", li);
        } catch (Exception e) {
            LOGGER.error("查询随访计划，项目列表：" + e.getMessage(), e);
            throw new CommonException("COM001", "查询随访计划，项目列表:", e);
        }
        return sr;
    }

    @Override
    public SimpleResult queryPageHzsfjh(PageModel pm) throws CommonException {
        SimpleResult sr = null;
        Page pe = pm.getPage();
        List<Map<String, Object>> mpLi = null;
        try {
            mpLi = dtfollowupplanmapper.queryPageHzsfjh(pe);
            pe.setParameterType(mpLi);
            sr = SimpleResult.success();
            sr.put("data", pe);
        } catch (Exception e) {
            LOGGER.error("分页查询随访患者管理Server层异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "分页查询随访患者管理Server层异常:", e);
        }
        return sr;
    }


    /**
     * 云随访诊室结束随访接口 根据患者GUID  serviceImp层
     *
     * @author 舵主
     */
    @Override
    public SimpleResult setFollowUpEnd(DTfollowupPlanRequest data) throws CommonException {
        SimpleResult simpleResult = null;
        DTfollowupPlan dTfollowupPlan = new DTfollowupPlan();
        try {
            BeanUtils.copyProperties(data, dTfollowupPlan);
            dtfollowupplanmapper.setFollowUpEnd(dTfollowupPlan);
        } catch (Exception e) {
            LOGGER.error("随访任务发送短信：" + e.getMessage(), e);
            throw new CommonException("COM001", "在执行SQL时出现异常:", e);
        }
        return simpleResult;
    }


    @Override
    public SimpleResult queryPlan(String docguid, String usrguid) throws CommonException {
        SimpleResult sr = null;
        Map<String, Object> m = new HashedMap();
        List<Map<String, Object>> plan = null;
        try {
            plan = dtfollowupplanmapper.queryPlan(docguid, usrguid);
            Map<String, Object> usrmp = dtfollowupplanmapper.queryUsrDetail(usrguid);
            sr = SimpleResult.success();
            m.put("usrDetail", usrmp);
            m.put("sfjhDetail", plan);
            sr.put("data", m);
        } catch (Exception e) {
            LOGGER.error("获取患者随访计划异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "获取患者随访计划异常:", e);
        }
        return sr;
    }

    @Override
    public SimpleResult querySfjhToYzs(String guid) throws CommonException {
        SimpleResult sr = null;
        try {
            if (StringUtils.isEmpty(guid)) {
                sr = SimpleResult.error("001", "guid不能为空");
            } else {
                DTfollowupPlan dp = dtfollowupplanmapper.selectByPrimaryKey(guid);
                if (dp == null || dp.getDocguid() == null || dp.getUsrguid() == null || dp.getSicguid() == null) {
                    throw new CommonException("没有找到该医生信息，请检查guid合法性");
                }
                Map<String, Object> doc = dtfollowupplanmapper.queryDoctor(dp.getDocguid());
                Map<String, Object> usr = dtfollowupplanmapper.queryUsrDetail(dp.getUsrguid());
                Map<String, Object> sic = dtfollowupplanmapper.querySic(dp.getSicguid());

                if (doc != null) {
                    if (usr != null) {
                        doc.put("usrName", usr.get("name"));
                        doc.put("usrAge", usr.get("age"));
                        doc.put("usrSex", usr.get("sex"));

                    }
                    if (sic != null) {
                        doc.put("zdnam", sic.get("ZDNAM"));
                        doc.put("sicguid", dp.getSicguid());
                    }
                    doc.put("followoption", dp.getFollowoption());

                }
                sr = SimpleResult.success();
                sr.put("data", doc);
            }
        } catch (Exception e) {
            LOGGER.error("云诊室随访计划详情异常：" + e.getMessage(), e);
            throw new CommonException("COM001", e.getMessage(), e);
        }
        return sr;
    }

    /***********************************************2017-12-13优化第二版 by dlf******************************************************************/
    @Override
    public void updatePlanState(String id, String state, boolean key) throws CommonException {
        //根据id查询随访计划状态
        try {
            if (StringUtils.isEmpty(id) || StringUtils.isEmpty(state)) {
                throw new CommonException("id,和state不能为空！");
            }
            DTfollowupPlan dp = dtfollowupplanmapper.selectByPrimaryKey(id);
            if (dp == null) {
                throw new CommonException("id无效,系统没有该随访计划数据！");
            } else {
                if (!key) {
                    dp.setFollowstate(state);
                    dtfollowupplanmapper.updateByPrimaryKeySelective(dp);
                } else {
                     /*
                （1）未开始：未被点击“开始随访”；

                （2）随访中：已被开始随访，但未被关闭；

                （3）已随访：已保存随访信息，被医生关闭

                （7）已随访：已保存随访信息，被超时自动关闭；

                （4）超时未开始：随访超时未开始被自动关闭；

                （5）随访中被关闭：未保存随访信息，被医生主动关闭；

                （6）随访中被超时关闭：未保存随访信息，超时自动关闭
                */
                    //需要判断是否保存随访信息
                    String dpState = dp.getFollowstate();
                    List<String> li = new ArrayList<String>();
                    if ("1".equals(dpState)) {
                        li.add("2");
                        li.add("4");
                    } else if ("2".equals(dpState)) {
                        //2随访中的状态可以变为
                        li.add("3");
                        li.add("5");
                        li.add("6");
                        li.add("7");
                    }
                    if (li.contains(state)) {
                        dp.setFollowstate(state);
                        dtfollowupplanmapper.updateByPrimaryKeySelective(dp);
                    } else {
                        throw new CommonException("随访状态不允许从" + dpState + "改为" + state);
                    }

                }

            }

        } catch (Exception e) {
            LOGGER.error("修改随访计划状态：" + e.getMessage(), e);
            if (e instanceof CommonException) {
                throw new CommonException(GzYunCodeEnum.GZYUN_UPDATEPLANSTATE, e.getMessage(), e);
            } else {
                throw new CommonException(GzYunCodeEnum.GZYUN_UPDATEPLANSTATE, e);
            }
        }

    }

    @Override
    public void stateKeyForSfjh(String id, String state) throws CommonException {
        try {
            if (StringUtils.isEmpty(id) || StringUtils.isEmpty(state)) {
                throw new CommonException("id,和state不能为空！");
            }
            if ("2".equals(state)) {
                updatePlanState(id, state, true);
            } else if ("3".equals(state) || "5".equals(state)) {
                DTfollowupRecord df = dtfollowuprecordmapper.selectDTfollowupRecordByPlanId(id);

                if ("3".equals(state) && df == null) {
                    throw new CommonException("修改状态为3，但是却没有保存随访信息");
                }
                if ("5".equals(state) && df != null) {
                    throw new CommonException("修改状态为5，但是却保存了随访信息");
                }
                updatePlanState(id, state, true);
            }
        } catch (Exception e) {
            LOGGER.error("修改随访计划状态：" + e.getMessage(), e);
            if (e instanceof CommonException) {
                throw new CommonException(GzYunCodeEnum.GZYUN_STATEKEYFORSFJH, e.getMessage(), e);
            } else {
                throw new CommonException(GzYunCodeEnum.GZYUN_STATEKEYFORSFJH, e);
            }
        }
    }

    @Override
    public void saveSfjl(DTfollowupRecordRequestV2 df) throws CommonException {
        try {

            DTfollowupRecord drd=dtfollowuprecordmapper.selectDTfollowupRecordByPlanId(df.getFollowupplanid());
            if(drd==null){
                DTfollowupRecord dd = new DTfollowupRecord();
                BeanUtils.copyProperties(df, dd);
                dtfollowuprecordmapper.insertSelective(dd);
            }else{
                DTfollowupRecord dd2 = new DTfollowupRecord();
                BeanUtils.copyProperties(df, dd2);
                dd2.setGuid(drd.getGuid());
                dtfollowuprecordmapper.updateByPrimaryKeySelective(dd2);
            }



        } catch (Exception e) {
            LOGGER.error("保存随访记录异常：" + e.getMessage(), e);
            if (e instanceof CommonException) {
                throw new CommonException(GzYunCodeEnum.GZYUN_SAVESFJL, e.getMessage(), e);
            } else {
                throw new CommonException(GzYunCodeEnum.GZYUN_SAVESFJL, e);
            }
        }
    }
   @Override
    public Page queryYzsDetailForMember(PageModel page) throws CommonException {
        Page p = page.getPage();
        String docguid = (String) p.get("docguid");
        String type = (String) p.get("type");
        if (org.apache.commons.lang3.StringUtils.isEmpty(type) || org.apache.commons.lang3.StringUtils.isEmpty(docguid)) {
            throw new CommonException("docguid和type不能为空");
        }
        List<DTfollowupPlanResponse> list = null;
        try {
            if ("1".equals(type)) {
                list = dtfollowupplanmapper.queryFollUpTasks(p);
            } else {
                list = dtfollowupplanmapper.queryPageFollUpTasksAll(p);
            }
            p.setParameterType(list);
        } catch (Exception e) {
            LOGGER.error("云诊室-查询待随访和已关闭患者信息异常：" + e.getMessage(), e);
            if (e instanceof CommonException) {
                throw new CommonException(GzYunCodeEnum.GZYUN_FOLLUPTASKS, e.getMessage(), e);
            } else {
                throw new CommonException(GzYunCodeEnum.GZYUN_FOLLUPTASKS, e);
            }
        }
        return p;

    }
}
