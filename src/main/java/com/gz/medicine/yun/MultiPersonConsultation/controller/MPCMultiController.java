package com.gz.medicine.yun.MultiPersonConsultation.controller;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.common.mybatisPageVo.Page;
import com.gz.medicine.common.mybatisPageVo.PageModel;
import com.gz.medicine.common.util.SimpleCode;
import com.gz.medicine.common.util.SimpleResult;
import com.gz.medicine.common.util.UUIDTool;
import com.gz.medicine.yun.MultiPersonConsultation.requestBean.JoinMutilVo;
import com.gz.medicine.yun.MultiPersonConsultation.requestBean.MultiVo;
import com.gz.medicine.yun.MultiPersonConsultation.service.IMultiControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.gz.medicine.common.util.ValidateWithException.validates;

/**
 * Created by 邓岚峰 on 2018/01/29 0017.
 */
@RestController
@RequestMapping("/v1/mpc/")
public class MPCMultiController {
    @Autowired
    IMultiControllerService imulticontrollerservice;
    @Autowired
    Validator validator;

    //患者编号查询
    @RequestMapping(value = "/queryUsrNumber", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public SimpleResult queryUsrNumber(PageModel model) {
        SimpleResult sr = null;
        Page pe = null;
        try {
            pe = imulticontrollerservice.queryUsrNumber(model);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(), e.getDesc());
        }
        sr = SimpleResult.success();
        sr.put("data", pe);
        return sr;
    }

    //机构查询
    @RequestMapping(value = "/queryLoc", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public SimpleResult queryLoc(PageModel model) {
        SimpleResult sr = null;
        Page pe = null;
        try {
            pe = imulticontrollerservice.queryLoc(model);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(), e.getDesc());
        }
        sr = SimpleResult.success();
        sr.put("data", pe);
        return sr;
    }

    //创建多人会诊
    @RequestMapping(value = "/createMulti", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public SimpleResult createMulti(MultiVo mv) {
        SimpleResult sr = null;
        String uuid = "";
        try {
            if (validates(validator, mv) != null) {
                return SimpleResult.error(SimpleCode.ERROR.getCode(), validates(validator, mv));
            }
            mv.setGuid(UUIDTool.getUUID());
            uuid = imulticontrollerservice.createMulti(mv);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(), e.getDesc());
        }
        sr = SimpleResult.success();
        sr.put("data", uuid);
        return sr;
    }

    //分页查询多人会诊
    @RequestMapping(value = "/queryMulti", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public SimpleResult queryMulti(PageModel model) {
        SimpleResult sr = null;
        Page pe = null;
        try {
            pe = imulticontrollerservice.queryPageMulti(model);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(), e.getDesc());
        }
        sr = SimpleResult.success();
        sr.put("data", pe);
        return sr;
    }

    //查询未读消息数
    @RequestMapping(value = "/readMseeageSum", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public SimpleResult readMseeageSum(@RequestParam(value = "usrid", required = true) String usrid) {
        SimpleResult sr = null;
        List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
        try {
            li = imulticontrollerservice.readMseeageSum(usrid);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(), e.getDesc());
        }
        sr = SimpleResult.success();
        sr.put("data", li);
        return sr;
    }

    //查询邀请医生页面
    @RequestMapping(value = "/queryMultiDoctor", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public SimpleResult queryMultiDoctor(PageModel model) {
        SimpleResult sr = null;
        Page pe = null;
        try {
            pe = imulticontrollerservice.queryMultiDoctor(model);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(), e.getDesc());
        }
        sr = SimpleResult.success();
        sr.put("data", pe);
        return sr;
    }

    // 邀请医生页面初始化加
    @RequestMapping(value = "/loadMultiDoctor", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public SimpleResult loadMultiDoctor(@RequestParam(value = "param", required = false) String param) {
        SimpleResult sr = null;
        List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
        try {
            li = imulticontrollerservice.loadMultiDoctor(param);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(), e.getDesc());
        }
        sr = SimpleResult.success();
        sr.put("data", li);
        return sr;
    }

    // 多人诊室详情
    @RequestMapping(value = "/queryMutilDetail", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public SimpleResult queryMutilDetail(@RequestParam(value = "param", required = true) String param) {
        SimpleResult sr = null;
        List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
        try {
            li = imulticontrollerservice.queryMutilDetail(param);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(), e.getDesc());
        }
        sr = SimpleResult.success();
        sr.put("data", li);
        return sr;
    }

    //医生申请加入
    @RequestMapping(value = "/doctorForJoin", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public SimpleResult queryMultiDoctor(@RequestParam(value = "usrid", required = true) String usrid,
                                         @RequestParam(value = "refid", required = true) String refid) {
        SimpleResult sr = null;
        try {
            imulticontrollerservice.doctorForJoin(usrid, refid);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(), e.getDesc());
        }
        sr = SimpleResult.success();
        return sr;
    }

    //管理员邀请加入
    @RequestMapping(value = "/adminForJoin", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public SimpleResult queryMultiDoctor(@RequestBody List<JoinMutilVo> jv) {
        SimpleResult sr = null;
        StringBuffer sb = new StringBuffer();
        if (jv != null && jv.size() > 0) {
            for (JoinMutilVo r : jv) {
                String result = validates(validator, r);
                if (result != null) {
                    sb.append(result);
                }
            }
        }
        if (!StringUtils.isEmpty(sb.toString())) {
            return SimpleResult.error(SimpleCode.ERROR.getCode(), sb.toString());
        }
        try {
            imulticontrollerservice.adminForJoin(jv);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(), e.getDesc());
        }
        sr = SimpleResult.success();
        return sr;
    }

    //查看消息列表
    @RequestMapping(value = "/queryMutilMsg", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public SimpleResult queryMutilMsg(@RequestParam(value = "usrid", required = true) String usrid) {
        SimpleResult sr = null;
        List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
        try {
            li = imulticontrollerservice.queryMutilMsg(usrid);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(), e.getDesc());
        }
        sr = SimpleResult.success();
        sr.put("data", li);
        return sr;
    }

    //会诊成员列表
    @RequestMapping(value = "/queryMutilBer", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public SimpleResult queryMutilBer(@RequestParam(value = "refid", required = true) String refid) {
        SimpleResult sr = null;
        List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
        try {
            li = imulticontrollerservice.queryMutilBer(refid);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(), e.getDesc());
        }
        sr = SimpleResult.success();
        sr.put("data", li);
        return sr;
    }

    //查看消息，确认或者取消
    @RequestMapping(value = "/commit", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public SimpleResult commit(@RequestParam(value = "refid", required = true) String refid,
                               @RequestParam(value = "usrid", required = true) String usrid,
                               @RequestParam(value = "inviteusrid", required = true) String inviteusrid,
                               @RequestParam(value = "ckflg", required = true) String ckflg) {
        SimpleResult sr = null;
        List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
        try {
            imulticontrollerservice.commit(refid, usrid, inviteusrid, ckflg);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(), e.getDesc());
        }
        sr = SimpleResult.success();
        return sr;
    }

    //聊天记录查询
    @RequestMapping(value = "/queryPageChatMsg", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult queryPageChatMsg(PageModel page) {
        SimpleResult sr = null;
        Page p = null;
        try {
            p = imulticontrollerservice.queryPageChatMsg(page);
        } catch (CommonException e) {
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getMessage());
        }
        sr = SimpleResult.success();
        sr.put("data", p);
        return sr;
    }

    //结束会诊
    @RequestMapping(value = "/closeMutil", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult closeMutil(@RequestParam(value = "refid", required = true) String refid) {
        SimpleResult sr = null;
        try {
            imulticontrollerservice.closeMutil(refid);
        } catch (CommonException e) {
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getMessage());
        }
        sr = SimpleResult.success();
        return sr;
    }

    //删除会诊
    @RequestMapping(value = "/deleteMutil", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public SimpleResult deleteMutil(@RequestParam(value = "refid", required = true) String refid) {
        SimpleResult sr = null;
        try {
            imulticontrollerservice.deleteMutil(refid);
        } catch (CommonException e) {
            return SimpleResult.error(SimpleCode.ERROR.getCode(), e.getMessage());
        }
        sr = SimpleResult.success();
        return sr;
    }
    //修改多人会诊
    @RequestMapping(value = "/updateMutil", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public SimpleResult updateMutil(@RequestBody MultiVo jv) {
        SimpleResult sr = null;
        if (validates(validator, jv) != null) {
            return SimpleResult.error(SimpleCode.ERROR.getCode(), validates(validator, jv));
        }
        try {
            imulticontrollerservice.updateMutil(jv);
        } catch (CommonException e) {
            return SimpleResult.error(e.getCode(), e.getDesc());
        }
        sr = SimpleResult.success();
        return sr;
    }

}
