package com.gz.medicine.yun.MultiPersonConsultation.service;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.common.mybatisPageVo.Page;
import com.gz.medicine.common.mybatisPageVo.PageModel;
import com.gz.medicine.yun.MultiPersonConsultation.requestBean.JoinMutilVo;
import com.gz.medicine.yun.MultiPersonConsultation.requestBean.MultiVo;

import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2018/1/29 0029.
 * Email 1429264916@qq.com
 */
public interface IMultiControllerService {
    /**
     * 查询患者编号（手机号）
     * @param param
     * @return
     * @throws CommonException
     */

    Page queryUsrNumber(PageModel param) throws CommonException;

    /**
     * 查询患者机构
     * @param param
     * @return
     * @throws CommonException
     */
    Page queryLoc(PageModel param) throws CommonException;

    /**
     * 创建多人会诊
     * @param param
     * @throws CommonException
     */
    String createMulti(MultiVo  param) throws CommonException;

    /**
     * 分页查询多人会诊
     * @param pm
     * @return
     * @throws CommonException
     */
    Page queryPageMulti(PageModel pm) throws CommonException;

    /**
     * 查询邀请医生页面
     * @param param
     * @return
     * @throws CommonException
     */
    Page queryMultiDoctor(PageModel param) throws CommonException;

    /**
     *  邀请医生页面初始化加载在线医生
     * @param param
     * @return
     * @throws CommonException
     */
    List<Map<String,Object>> loadMultiDoctor(String param) throws CommonException;

    /**
     * 医生申请加入
     * @param usrid
     * @param refid
     * @return
     * @throws CommonException
     */
    void doctorForJoin(String usrid,String refid) throws CommonException;

    /**
     * 管理员邀请加入
     * @param param
     * @return
     * @throws CommonException
     */
    void adminForJoin(List<JoinMutilVo> param) throws CommonException;
    /**
     * 查询未读消息数量
     * @param param
     * @return
     * @throws CommonException
     */
    List<Map<String,Object>> readMseeageSum(String param) throws CommonException;

    /**
     * 多人诊室详情
     * @param param
     * @return
     * @throws CommonException
     */
    List<Map<String,Object>> queryMutilDetail(String param) throws CommonException;

    /**
     * 查询诊室未读消息
     * @param usrid
     * @return
     * @throws CommonException
     */
    List<Map<String,Object>> queryMutilMsg(String usrid) throws CommonException;

    /**
     * 查询会诊成员列表
     * @param usrid
     * @return
     * @throws CommonException
     */
    List<Map<String,Object>> queryMutilBer(String usrid) throws CommonException;

    void commit(String refid,String usrid,String inviteusrid,String ckflg) throws CommonException;

    /**
     * 聊天记录查询
     * @param pm
     * @return
     * @throws CommonException
     */
    Page queryPageChatMsg(PageModel pm) throws CommonException;

    /**
     * 结束会诊
     * @param refguid
     * @throws CommonException
     */
    void closeMutil(String refguid) throws CommonException;

    /**
     * 删除会诊
     * @param refguid
     * @throws CommonException
     */
    void deleteMutil(String refguid) throws CommonException;

    /**
     * 更新多人会诊
     * @param mv
     * @throws CommonException
     */
    void updateMutil(MultiVo mv) throws CommonException;

}
