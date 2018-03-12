package com.gz.medicine.yun.MultiPersonConsultation.service.impl;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.common.mybatisPageVo.Page;
import com.gz.medicine.common.mybatisPageVo.PageModel;
import com.gz.medicine.common.util.*;
import com.gz.medicine.yun.MultiPersonConsultation.mapper.MultiMapper;
import com.gz.medicine.yun.MultiPersonConsultation.requestBean.JoinMutilVo;
import com.gz.medicine.yun.MultiPersonConsultation.requestBean.MultiVo;
import com.gz.medicine.yun.MultiPersonConsultation.requestBean.UpdateMutilVo;
import com.gz.medicine.yun.MultiPersonConsultation.service.IMultiControllerService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by dlf on 2018/1/29 0029.
 * Email 1429264916@qq.com
 */
@Service("IMultiControllerService")
public class IMultiControllerServiceImpl implements IMultiControllerService {
    public static final Logger LOGGER = Logger.getLogger(IMultiControllerServiceImpl.class);
    @Autowired
    MultiMapper multimapper;

    @Override
    public Page queryUsrNumber(PageModel pm) throws CommonException {
        Page pe = pm.getPage();
        try {
            String param = (String) pe.get("param");

            String sql = null;
            if (StringUtils.isEmpty(param)) {
                String id = (String) pe.get("id");
                if (StringUtils.isEmpty(id)) {
                    throw new CommonException("医生id不能为空");
                }
                pe.put("id", id);
                sql = "select guid,name,id,mobile,sex,'0' as age from usr where id in(select distinct(SICKGUID)  from Sickblhdr  where    docguid in (select guid from usr where id=#{id}) ) ";
            } else {
                boolean re = Pattern.matches("^[0-9]*$", param);
                if (re) {
                    //手机号查询
                    sql = "select guid,name,id,mobile,sex,'0' as age from usr where id like  '%'||#{param}||'%' ";

                } else {
                    //姓名查询
                    sql = "select guid,name,id,mobile,sex,'0' as age from usr where name like '%'||#{param}||'%' ";
                }
            }

            pe.put("sql", sql);
            List<Map<String, Object>> li = multimapper.queryPageUtil(pe);
            pe.setParameterType(li);
            pe.remove("sql");
        } catch (Exception e) {
            LOGGER.error("queryUsrNumber异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "queryUsrNumber异常" + e.getMessage(), e);
        }
        return pe;
    }

    @Override
    public Page queryLoc(PageModel pm) throws CommonException {
        Page pe = pm.getPage();
        try {
            String param = (String) pe.get("param");
            if (StringUtils.isEmpty(param)) {
                pe.put("sql", "select id,name from loc where   org='CHIS' order by id");
            } else {
                pe.put("sql", "select id,name from loc where id like '%'||#{param}||'%'  or name like '%'||#{param}||'%'  and  org='CHIS' order by id");
            }
            List<Map<String, Object>> li = multimapper.queryPageUtil(pe);
            pe.setParameterType(li);
            pe.remove("sql");
        } catch (Exception e) {
            LOGGER.error("queryLoc异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "queryLoc异常" + e.getMessage(), e);
        }
        return pe;
    }

    @Override
    public String createMulti(MultiVo m) throws CommonException {
        String re = "";
        try {
            Map<String, Object> parm = new HashedMap();
            parm.put("guid", m.getGuid());
            parm.put("name", m.getName());
            parm.put("patient", m.getPatient());
            parm.put("dept", m.getDept());
            parm.put("dat1", m.getDat1());
            parm.put("time", m.getTime());
            if (StringUtils.isEmpty(m.getNote())) {
                m.setNote("");
            }
            parm.put("sql", "select * from CHIS_Multpyconst where name=#{name}");
            parm.put("name",  m.getName());
           List<Map<String,Object>> mLi= multimapper.exSql(parm);
           if(mLi!=null&&mLi.size()>0){
               throw new CommonException("该诊室名称已被使用，请重新取名！");
           }
            parm.put("note", m.getNote());
            parm.put("crtusr", m.getCrtusr());
            String sql = "insert into CHIS_Multpyconst(guid,name,patient,dept,dat1,time,note,crtusr,org,acc)values(#{guid},#{name},#{patient},#{dept},TO_DATE(#{dat1},'YYYY-MM-DD'),#{time},#{note},#{crtusr},'CHIS','CHIS')";
            parm.put("sql", sql);
            multimapper.insertSql(parm);
            re = m.getGuid();
        } catch (Exception e) {
            LOGGER.error("createMulti异常：" + e.getMessage(), e);
            throw new CommonException("COM001",e.getMessage(), e);
        }
        return re;
    }

    @Override
    public Page queryPageMulti(PageModel pm) throws CommonException {
        Page p = null;
        Page pe = pm.getPage();
        String sql = "\n" +
                "select *\n" +
                "  from (select guid, crtusr, name, usr, dat, stat\n" +
                "          from (select a.guid,\n" +
                "                       a.CRTUSR,\n" +
                "                       a.name,\n" +
                "                       b.name usr,\n" +
                "                       to_char(a.dat1, 'yyyy-mm-dd') || ' ' || a.time dat,\n" +
                "                       '3' stat\n" +
                "                  from CHIS_Multpyconst a, usr b\n" +
                "                 where a.crtusr = b.id\n" +
                "                   and stat = #{state}" +
                "                   and a.crtusr = #{usrid})\n" +
                "        union all\n" +
                "        \n" +
                "        select distinct a.guid, a.CRTUSR, a.name, a.usr, a.dat, b.stat\n" +
                "          from (select a.guid,\n" +
                "                       a.CRTUSR,\n" +
                "                       a.name,\n" +
                "                       b.name usr,\n" +
                "                       to_char(a.dat1, 'yyyy-mm-dd') || ' ' || a.time dat\n" +
                "                  from CHIS_Multpyconst a, usr b\n" +
                "                 where a.crtusr = b.id\n" +
                "                   and stat = #{state}) a,\n" +
                "               CHIS_ConstMber b\n" +
                "         where a.guid = b.refid\n" +
                "           and b.id = #{usrid}\n" +
                "           and a.CRTUSR not in (#{usrid})) m\n" +
                " order by m.dat desc\n";
        List<Map<String, Object>> mpMangeLi = new ArrayList<Map<String, Object>>();
        try {
            if (StringUtils.isEmpty((String) pe.get("usrid")) || StringUtils.isEmpty((String) pe.get("state"))) {
                throw new CommonException("usrid,state不能为空");
            }
            pe.put("sql", sql);
            mpMangeLi = multimapper.queryPageUtil(pe);
            pe.setParameterType(mpMangeLi);
            pe.put("sql", "");
        } catch (Exception e) {
            LOGGER.error("queryPageMulti异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "queryPageMulti异常:" + e.getMessage(), e);
        }
        return pe;
    }

    @Override
    public Page queryMultiDoctor(PageModel pm) throws CommonException {
        Page pe = pm.getPage();
        try {
            String param = (String) pe.get("param");
            String sql = "";
            if (StringUtils.isEmpty(param)) {
                sql = "select * from (select * from ( select distinct * from (  \n" +
                        "          select b.guid, b.id, b.name, d.name job,c.isty, c.hospital,c.sex, c.img, decode(sign(length(c.begoodat)-45),1,substr(c.begoodat,0,42)||'...',c.begoodat) begoodat,case when (sysdate - b.currenttime) * 24 * 60 * 60 < 15 then '1' else '2' end stat,c.sortid,'' pb,b.isol,c.USRSTAT,f.name deptnam\n" +
                        "           from  usr b, yx_doc c, yx_docjob d,YX_DOCDEPT f\n" +
                        "          where b.id = c.id\n" +
                        "            and c.jobtitle = d.id(+)\n" +
                        "            and c.DEPARTMENT=f.id\n" +
                        "            and b.org = 'CHIS'\n" +
                        "            and (sysdate - b.currenttime) * 24 * 60 * 60 < 15\n" +
                        "            union all\n" +
                        "          select b.guid, b.id, b.name, d.name job,c.isty, c.hospital,c.sex, c.img, decode(sign(length(c.begoodat)-45),1,substr(c.begoodat,0,42)||'...',c.begoodat) begoodat,case when (sysdate - b.currenttime) * 24 * 60 * 60 < 15 then '1' else '2' end stat,c.sortid,a.guid pb,b.isol,c.USRSTAT,f.name deptname\n" +
                        "           from yx_monthwork a,usr b, yx_doc c, yx_docjob d,YX_DOCDEPT f\n" +
                        "          where a.crtusr = b.id\n" +
                        "            and a.crtusr = c.id\n" +
                        "            and a.org = b.org\n" +
                        "            and a.org = c.org\n" +
                        "            and c.jobtitle = d.id(+)\n" +
                        "            and c.DEPARTMENT=f.id\n" +
                        "            and a.org = 'CHIS'\n" +
                        "            and c.type2<>'2'\n" +
                        "            and a.dat = to_char(sysdate, 'yyyy-mm-dd')\n" +
                        "             and (sysdate - b.currenttime) * 24 * 60 * 60 >= 15\n" +
                        "            union all \n" +
                        "          select b.guid, b.id, b.name, d.name job,c.isty, c.hospital,c.sex, c.img,  decode(sign(length(c.begoodat)-45),1,substr(c.begoodat,0,42)||'...',c.begoodat) begoodat,case when (sysdate - b.currenttime) * 24 * 60 * 60 < 15 then '1' else '2' end stat,c.sortid,'' pb,b.isol,c.USRSTAT,f.name deptname\n" +
                        "           from usr b, yx_doc c, yx_docjob d,YX_DOCDEPT f\n" +
                        "          where b.id = c.id\n" +
                        "            and b.org = c.org\n" +
                        "            and c.jobtitle = d.id(+)\n" +
                        "            and c.DEPARTMENT=f.id\n" +
                        "            and b.org = 'CHIS'\n" +
                        "            and ((sysdate - b.currenttime) * 24 * 60 * 60 >= 15\n" +
                        "            or b.currenttime is null)\n" +
                        "             ) where 1=1  and id in('1104','1107','15288888888','1108','1109','1112','1113')) order by stat,usrstat,nvl(isol,0), pb, sortid desc) where  stat='1' ";

            } else {
                sql = "" +
                        "select id," +
                        "       guid," +
                        "       name," +
                        "       job," +
                        "       DECODE(IMG, NULL, '1D297BEF3F912598E050007F01006C34', IMG) AS img," +
                        "       begoodat," +
                        "       hospital," +
                        "       openroom," +
                        "       deptnam," +
                        "       uguid," +
                        "       stat" +
                        "  from (select a.sortid," +
                        "               a.id id," +
                        "               a.guid guid," +
                        "               a.name name," +
                        "               b.name job," +
                        "               a.img img," +
                        "               a.begoodat begoodat," +
                        "               a.hospital hospital," +
                        "               c.openroom openroom," +
                        "               d.name deptnam," +
                        "               c.guid uguid," +
                        "               case" +
                        "                 when (sysdate - c.currenttime) * 24 * 60 * 60 < 15 then" +
                        "                  'online'" +
                        "                 else" +
                        "                  'offline'" +
                        "               end stat" +
                        "          from yx_doc a, yx_docjob b, usr c, yx_docdept d" +
                        "         where a.jobtitle = b.id(+)" +
                        "           and a.department = d.id(+)" +
                        "           and d.org(+) = 'CHIS'" +
                        "           and a.org = 'CHIS'" +
                        "           and c.org = 'CHIS'" +
                        "              " +
                        "           and a.id = c.id" +
                        "        " +
                        "        ) dt" +
                        " where name like '%'||#{param}||'%' " +
                        "    or job like '%'||#{param}||'%' " +
                        "    or deptnam like '%'||#{param}||'%' " +
                        " order by stat desc," +
                        "          sortid asc," +
                        "          decode(job," +
                        "                 '主任医生'," +
                        "                 '1'," +
                        "                 '副主任医生'," +
                        "                 '2'," +
                        "                 '主治医生'," +
                        "                 '3'," +
                        "                 '') asc," +
                        "          id asc";

            }

            pe.put("sql", sql);
            List<Map<String, Object>> li = multimapper.queryPageUtil(pe);
            pe.setParameterType(li);
        } catch (Exception e) {
            LOGGER.error("queryMultiDoctor异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "queryMultiDoctor异常" + e.getMessage(), e);
        }
        return pe;
    }

    @Override
    public List<Map<String, Object>> loadMultiDoctor(String param) throws CommonException {
        List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
        try {
            Map<String, Object> parm = new HashedMap();
            String sql = "" +
                    "select id," +
                    "       guid," +
                    "       name," +
                    "       job," +
                    "       DECODE(img, NULL, '1D297BEF3F912598E050007F01006C34', img) IMG," +
                    "       begoodat," +
                    "       hospital," +
                    "       openroom," +
                    "       deptnam," +
                    "       uguid," +
                    "       stat" +
                    "  from (select a.sortid," +
                    "               a.id id," +
                    "               a.guid guid," +
                    "               a.name name," +
                    "               b.name job," +
                    "               a.img img," +
                    "               a.begoodat begoodat," +
                    "               a.hospital hospital," +
                    "               c.openroom openroom," +
                    "               d.name deptnam," +
                    "               c.guid uguid," +
                    "               case" +
                    "                 when (sysdate - c.currenttime) * 24 * 60 * 60 < 15 then" +
                    "                  'online'" +
                    "                 else" +
                    "                  'offline'" +
                    "               end stat" +
                    "          from yx_doc a, yx_docjob b, usr c, yx_docdept d" +
                    "         where a.jobtitle = b.id(+)" +
                    "           and a.department = d.id(+)" +
                    "           and d.org(+) = 'CHIS'" +
                    "           and a.org = 'CHIS'" +
                    "           and c.org = 'CHIS'" +
                    "           and a.USRSTAT = '1'" +
                    "           and a.id = c.id" +
                    "           and a.img is not null" +
                    "           and a.type = '2') dt" +
                    " where stat = 'online'" +
                    " order by stat desc," +
                    "          sortid asc," +
                    "          decode(job," +
                    "                 '主任医生'," +
                    "                 '1'," +
                    "                 '副主任医生'," +
                    "                 '2'," +
                    "                 '主治医生'," +
                    "                 '3'," +
                    "                 '') asc," +
                    "          id asc";
            parm.put("param", param);
            parm.put("sql", sql);
            li = multimapper.exSql(parm);
        } catch (Exception e) {
            LOGGER.error("queryMultiDoctor异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "queryMultiDoctor异常" + e.getMessage(), e);
        }
        return li;
    }

    @Override
    public void doctorForJoin(String usrid, String refid) throws CommonException {
        try {
            Map<String, Object> parm = new HashedMap();
            String sql = "select name from CHIS_Multpyconst where guid=#{guid}";
            parm.put("guid", refid);
            parm.put("sql", sql);
            List<Map<String, Object>> li = multimapper.exSql(parm);
            String title = null;
            String name = null;
            if (li != null && li.size() == 1) {
                title = (String) li.get(0).get("name");
            } else {
                throw new CommonException("该多人诊室guid不存在！");
            }
            sql = "select name from usr where id=#{usrid} and org='CHIS'";
            parm.put("sql", sql);
            parm.put("usrid", usrid);
            li = multimapper.exSql(parm);
            if (li != null && li.size() == 1) {
                name = (String) li.get(0).get("name");
            } else {
                throw new CommonException("改用户不存在usrid无效！");
            }
            //stat 状态(0.申请状态 1.管理员确认 2.医生确认 3.邀请成功)
            sql = "insert into CHIS_ConstMber(ID,NAME,STAT,REFID) values(#{id},#{name},#{stat},#{refid})";
            parm.put("id", usrid);
            parm.put("name", name);
            parm.put("stat", "0");
            parm.put("refid", refid);
            parm.put("sql", sql);
            multimapper.insertSql(parm);
            sql = "insert into CHIS_InviteRecd(CRTUSR,INVITEUSR,STAT,title,refid) values(#{usrid},#{name},#{stat},#{title},#{refid})";
            parm.put("usrid", usrid);
            parm.put("name", usrid);
            parm.put("stat", "0");
            parm.put("title", title);
            parm.put("refid", refid);
            parm.put("sql", sql);
            multimapper.insertSql(parm);
        } catch (Exception e) {
            LOGGER.error("doctorForJoin异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "doctorForJoin异常:" + e.getMessage(), e);

        }
    }

    @Override
    public void adminForJoin(List<JoinMutilVo> param) throws CommonException {

        try {
            JoinMutilVo jv = param.get(0);

            Map<String, Object> parm = new HashedMap();
            String sql = "select CRTUSR,name from CHIS_Multpyconst where guid=#{guid}";
            parm.put("guid", jv.getRefid());
            parm.put("sql", sql);
            List<Map<String, Object>> li = multimapper.exSql(parm);
            String crtusr = null;
            String title = null;
            if (li != null && li.size() == 1) {
                crtusr = (String) li.get(0).get("crtusr");
                title = (String) li.get(0).get("name");
            } else {
                throw new CommonException("该多人诊室guid不存在！");
            }
            //数量较少，不做批量插入
            for (JoinMutilVo j : param) {
                //与pc端打通，默认2管理员邀请
                String state = "";
                if("1".equals(j.getStat())||StringUtils.isEmpty(j.getStat())){
                    state="2";
                }else{
                    state=j.getStat();
                }
                //判断是否已经被邀请过或者状态是不是已加入
                parm.put("sql", "select stat from CHIS_ConstMber where refid =#{refid} and id=#{id}");
                parm.put("id", j.getId());
                parm.put("refid", j.getRefid());
                List<Map<String, Object>> statLi = multimapper.exSql(parm);
                if (!(statLi != null && statLi.size() > 0)) {
                    parm.put("sql", "insert into CHIS_ConstMber(ID,NAME,STAT,REFID) values(#{id},#{name},#{stat},#{refid})");
                    parm.put("id", j.getId());
                    parm.put("name", j.getName());
                    parm.put("stat", state);
                    parm.put("refid", j.getRefid());
                    multimapper.insertSql(parm);
                    parm.put("sql", "insert into CHIS_InviteRecd(CRTUSR,INVITEUSR,STAT,title,refid) values(#{crtusr},#{inviteusr},#{stat},#{title},#{refid})");
                    parm.put("crtusr", crtusr);
                    parm.put("inviteusr", j.getId());
                    parm.put("stat", state);
                    parm.put("title", title);
                    parm.put("refid", j.getRefid());
                    multimapper.insertSql(parm);
                }
                //判断消息


            }
        } catch (Exception e) {
            LOGGER.error("adminForJoin异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "adminForJoin异常:" + e.getMessage(), e);
        }

    }


    @Override
    public List<Map<String, Object>> readMseeageSum(String usrid) throws CommonException {
        List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
        String sql = "" +
                "select count(*) as num" +
                "  from (select a.crtusr    crtusrid," +
                "               b.name      crtusrnam," +
                "               a.inviteusr inviteusrid," +
                "               c.name      inviteusrnam," +
                "               a.title," +
                "               a.refid," +
                "               stat," +
                "               b.IMGGUID   img" +
                "          from CHIS_InviteRecd a, usr b, usr c" +
                "         where a.crtusr = b.id" +
                "           and a.inviteusr = c.id" +
                "           and a.inviteusr = #{usrid}" +
                "           and a.stat = '2'" +
                "        union all" +
                "        select a.crtusr    crtusrid," +
                "               b.name      crtusrnam," +
                "               a.inviteusr inviteusrid," +
                "               c.name      inviteusrnam," +
                "               a.title," +
                "               a.refid," +
                "               stat," +
                "               b.IMGGUID   img" +
                "          from CHIS_InviteRecd a, usr b, usr c" +
                "         where a.crtusr = b.id" +
                "           and a.inviteusr = c.id" +
                "           and a.crtusr = #{usrid}" +
                "           and a.stat = '1'" +
                "        union all" +
                "        select a.crtusr    crtusrid," +
                "               b.name      crtusrnam," +
                "               a.inviteusr inviteusrid," +
                "               c.name      inviteusrnam," +
                "               a.title," +
                "               a.refid," +
                "          (select name from usr where id=a.PATIENT) as PATIENTName," +
                "          (select name from usr where id=a.PATIENT) as PATIENTName," +
                "               stat," +
                "               b.IMGGUID   img" +
                "          from CHIS_InviteRecd a, usr b, usr c" +
                "         where a.crtusr = b.id" +
                "           and a.inviteusr = c.id" +
                "           and a.CRTUSR = a.INVITEUSR" +
                "           and a.stat = '0'" +
                "           and (select CRTUSR from CHIS_Multpyconst where guid = a.refid) =" +
                "               #{usrid})";
        try {
            Map<String, Object> parm = new HashedMap();
            parm.put("sql", sql);
            parm.put("usrid", usrid);
            li = multimapper.exSql(parm);
        } catch (Exception e) {
            LOGGER.error("readMseeageSum异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "readMseeageSum异常:" + e.getMessage(), e);
        }

        return li;
    }

    @Override
    public List<Map<String, Object>> queryMutilDetail(String param) throws CommonException {
        List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
        String sql = "   \n" +
                "   select a.guid,\n" +
                "          a.name,\n" +
                "          a.crtusr,\n" +
                "          a.dept,\n" +
                "          to_char(a.dat1, 'yyyy-mm-dd') || ' ' || a.time dat,\n" +
                "          a.note,\n" +
                "          (select name from usr where id=a.PATIENT) as PATIENTName,\n" +
                "          (select name from loc where   org='CHIS' and id=a.dept) as deptName ,\n" +
                "          a.PATIENT,\n" +
                "          DECODE(img, NULL, '1D297BEF3F912598E050007F01006C34', img) IMG\n" +
                "     from CHIS_Multpyconst a, usr b, yx_doc c, yx_docjob d\n" +
                "    where a.crtusr = b.id\n" +
                "      and b.id = c.id\n" +
                "      and c.jobtitle = d.id(+)\n" +
                "      and b.org = 'CHIS'\n" +
                "      and a.guid =#{param}";
        try {
            if (StringUtils.isEmpty(param)) {
                throw new CommonException("参数不能为空！");
            }
            Map<String, Object> parm = new HashedMap();
            parm.put("sql", sql);
            parm.put("param", param);
            li = multimapper.exSql(parm);
        } catch (Exception e) {
            LOGGER.error("queryMutilDetail异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "queryMutilDetail异常:" + e.getMessage(), e);
        }
        return li;
    }

    @Override
    public List<Map<String, Object>> queryMutilMsg(String usrid) throws CommonException {
        List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
        try {
            if (StringUtil.isEmpty(usrid)) {
                throw new CommonException("usrid不能为空");
            }
            Map<String, Object> parm = new HashedMap();
            String sql = "\n" +
                    "select a.crtusr crtusrid,\n" +
                    "       b.name crtusrnam,\n" +
                    "       a.inviteusr inviteusrid,\n" +
                    "       c.name inviteusrnam,\n" +
                    "       a.title,\n" +
                    "       a.refid,\n" +
                    "       stat,\n" +
                    "       DECODE(b.IMGGUID,\n" +
                    "              NULL,\n" +
                    "              '1D297BEF3F912598E050007F01006C34',\n" +
                    "              b.IMGGUID) img\n" +
                    "  from CHIS_InviteRecd a, usr b, usr c\n" +
                    " where a.crtusr = b.id\n" +
                    "   and a.inviteusr = c.id\n" +
                    "   and a.inviteusr = #{usrid}\n" +
                    "   and a.stat = '2'\n" +
                    "union all\n" +
                    "select a.crtusr crtusrid,\n" +
                    "       b.name crtusrnam,\n" +
                    "       a.inviteusr inviteusrid,\n" +
                    "       c.name inviteusrnam,\n" +
                    "       a.title,\n" +
                    "       a.refid,\n" +
                    "       stat,\n" +
                    "       DECODE(b.IMGGUID,\n" +
                    "              NULL,\n" +
                    "              '1D297BEF3F912598E050007F01006C34',\n" +
                    "              b.IMGGUID) img\n" +
                    "  from CHIS_InviteRecd a, usr b, usr c\n" +
                    " where a.crtusr = b.id\n" +
                    "   and a.inviteusr = c.id\n" +
                    "   and a.crtusr = #{usrid}\n" +
                    "   and a.stat = '1'\n" +
                    "union all\n" +
                    "select a.crtusr crtusrid,\n" +
                    "       b.name crtusrnam,\n" +
                    "       a.inviteusr inviteusrid,\n" +
                    "       c.name inviteusrnam,\n" +
                    "       a.title,\n" +
                    "       a.refid,\n" +
                    "       stat,\n" +
                    "       DECODE(b.IMGGUID,\n" +
                    "              NULL,\n" +
                    "              '1D297BEF3F912598E050007F01006C34',\n" +
                    "              b.IMGGUID) img\n" +
                    "  from CHIS_InviteRecd a, usr b, usr c\n" +
                    " where a.crtusr = b.id\n" +
                    "   and a.inviteusr = c.id\n" +
                    "   and a.CRTUSR = a.INVITEUSR\n" +
                    "   and a.stat = '0'\n" +
                    "   and (select CRTUSR from CHIS_Multpyconst where guid = a.refid) = #{usrid}";
            parm.put("sql", sql);
            parm.put("usrid", usrid);
            li = multimapper.exSql(parm);
        } catch (Exception e) {
            LOGGER.error("queryMutilMsg异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "queryMutilMsg异常:" + e.getMessage(), e);
        }
        return li;
    }

    @Override
    public List<Map<String, Object>> queryMutilBer(String refid) throws CommonException {

        List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
        try {
            if (StringUtil.isEmpty(refid)) {
                throw new CommonException("refid不能为空");
            }
            Map<String, Object> parm = new HashedMap();
            String sql = "select a.id," +
                    "       a.name," +
                    "a.stat, "+
                    "       c.name jobtitle," +
                    "       d.name dept," +
                    "       a.guid," +
                    "       DECODE(b.img, NULL, '1D297BEF3F912598E050007F01006C34', b.img) img" +
                    "  from CHIS_ConstMber a, yx_doc b, yx_docjob c, yx_docdept d" +
                    " where b.org = 'CHIS'" +
                    "   and a.id = b.id" +
                    "   and b.JOBTITLE = c.id(+)" +
                    "   and b.DEPARTMENT = d.id(+)" +
                    "   and a.refid = #{refid}";
            parm.put("sql", sql);
            parm.put("refid", refid);
            li = multimapper.exSql(parm);
        } catch (Exception e) {
            LOGGER.error("queryMutilMsg异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "queryMutilMsg异常:" + e.getMessage(), e);
        }

        return li;
    }

    @Override
    public void commit(String refid, String usrid, String inviteusrid, String ckflg) throws CommonException {

        try {
            if (StringUtil.isEmpty(refid) || StringUtil.isEmpty(usrid) || StringUtil.isEmpty(inviteusrid) || StringUtil.isEmpty(ckflg)) {
                throw new CommonException("usrid,refid,inviteusrid,ckflg都不能为空");
            }
            String crtusr = null;
            Map<String, Object> parm = new HashedMap();
            parm.put("sql", "select CRTUSR from CHIS_Multpyconst where guid=#{guid}");
            parm.put("guid", refid);
            List<Map<String, Object>> li = multimapper.exSql(parm);
            if (li != null && li.size() == 1) {
                crtusr = (String) li.get(0).get("crtusr");

            } else {
                throw new CommonException("该会诊不存在，refid输入有误");
            }
            String stat = null;
            parm.put("sql", "select max(stat) as stat  from CHIS_ConstMber where refid=#{guid} and id=#{inviteusrid}");
            parm.put("guid", refid);
            parm.put("inviteusrid", inviteusrid);
            li = multimapper.exSql(parm);
            if (li != null && li.size() == 1) {
                stat = (String) li.get(0).get("stat");
            } else {
                throw new CommonException("成员表没有该会诊，refid，inviteusrid输入有误");
            }
            if ("1".equals(ckflg)) {
                if (crtusr == usrid) {
                    if ("0".equals(stat)) {
                        //申请人提交申请时管理员直接确认
                        String sql2 = "update CHIS_ConstMber set stat='3' where refid=#{refid}and id=#{inviteusrid}";
                        parm.put("refid", refid);
                        parm.put("inviteusrid", inviteusrid);
                        parm.put("sql", sql2);
                        multimapper.insertSql(parm);
                        sql2 = "update CHIS_InviteRecd set STAT='3' where refid=#{refid} and INVITEUSR=#{inviteusrid}";
                        parm.put("sql", sql2);
                        multimapper.insertSql(parm);
                    } else {
                        //将会诊医师成员列表的状态改为医生确认 2 )
                        String sql3 = "update CHIS_ConstMber set stat='2' where refid=#{refid}  and id=#{inviteusrid}";
                        parm.put("refid", refid);
                        parm.put("inviteusrid", inviteusrid);
                        parm.put("sql", sql3);
                        multimapper.insertSql(parm);
                        sql3 = "update CHIS_InviteRecd set STAT='2' where refid=#{refid}and INVITEUSR=#{inviteusrid}";
                        parm.put("sql", sql3);
                        multimapper.insertSql(parm);
                    }
                } else {
                    //将会诊医师成员列表的状态改为邀请成功 3 )
                    String sql4 = "update CHIS_ConstMber set stat='3' where refid=#{refid} and id=#{usrid}";
                    parm.put("refid", refid);
                    parm.put("usrid", usrid);
                    parm.put("sql", sql4);
                    multimapper.updateSql(parm);
                    sql4 = "update CHIS_InviteRecd set STAT='3' where refid=#{refid}  and INVITEUSR=#{inviteusrid}";
                    parm.put("inviteusrid", inviteusrid);
                    parm.put("sql", sql4);
                    multimapper.updateSql(parm);
                }
            } else {
                String sql5 = "delete CHIS_ConstMber  where refid=#{refid}  and id=#{inviteusrid}";
                parm.put("inviteusrid", inviteusrid);
                parm.put("refid", refid);
                parm.put("sql", sql5);
                multimapper.deleteSql(parm);
                sql5 = "update CHIS_InviteRecd set STAT='5' where refid=#{refid} and INVITEUSR=#{inviteusrid}";
                parm.put("sql", sql5);
                multimapper.updateSql(parm);
            }

        } catch (Exception e) {
            LOGGER.error("queryMutilMsg异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "queryMutilMsg异常:" + e.getMessage(), e);
        }
    }

    @Override
    public Page queryPageChatMsg(PageModel pm) throws CommonException {
        Page pe = pm.getPage();
        String usrid = (String) pe.get("usrid");
        String tousr = (String) pe.get("tousr");
        String guid = (String) pe.get("guid");
        if (StringUtils.isEmpty(usrid) || StringUtils.isEmpty(tousr) || StringUtils.isEmpty(guid)) {
            throw new CommonException("usrid或者tousr或者guid不能为空");
        }
        String sql = " select * from (select * from chis_chartmsg where usrid=#{usrid} and tousr=#{tousr} and type='speak' and  mid=#{guid}" +
                "    union all" +
                "    select * from chis_chartmsg where usrid=#{tousr} and tousr=#{usrid} and type='speak' and mid=#{guid} " +
                "    ) order by CRTDAT desc";
        List<Map<String, Object>> mpMangeLi = new ArrayList<Map<String, Object>>();
        try {
            pe.put("sql", sql);
            pe.put("guid", guid);
            mpMangeLi = multimapper.queryPageUtil(pe);
            pe.setParameterType(mpMangeLi);
            pe.put("sql", "");
        } catch (Exception e) {
            LOGGER.error("聊天记录查询Server层异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "聊天记录查询Server层异常:" + e.getMessage(), e);
        }
        return pe;
    }

    @Override
    public void closeMutil(String refguid) throws CommonException {
        try {
            if (StringUtils.isEmpty(refguid)) {
                throw new CommonException("refid不能为空");
            }

            Map<String, Object> param = new HashedMap();
            param.put("sql", "update CHIS_Multpyconst set stat='1' where guid=#{refguid}");
            param.put("refguid", refguid);
            multimapper.updateSql(param);
        } catch (Exception e) {
            LOGGER.error("closeMutilServer层异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "closeMutilerver层异常:" + e.getMessage(), e);
        }

    }

    @Override
    public void deleteMutil(String refguid) throws CommonException {
        try {
            if (StringUtils.isEmpty(refguid)) {
                throw new CommonException("refid不能为空");
            }
            Map<String, Object> param = new HashedMap();
            param.put("sql", "delete CHIS_Multpyconst where guid=#{refguid}");
            param.put("refguid", refguid);
            multimapper.deleteSql(param);
        } catch (Exception e) {
            LOGGER.error("deleteMutil server层异常：" + e.getMessage(), e);
            throw new CommonException("COM001", "deleteMutil server层异常:" + e.getMessage(), e);
        }

    }

    @Override
    public void updateMutil(MultiVo mv) throws CommonException {
        try {
            if (StringUtils.isEmpty(mv.getGuid())) {
                throw new CommonException("诊室guid不能为空！");
            }
            deleteMutil(mv.getGuid());
            //创建诊室
            createMulti(mv);
            List<UpdateMutilVo> jv = mv.getJoinmutilvo();
            List<JoinMutilVo> jm = new ArrayList<JoinMutilVo>();
            Map<String, Object> param = new HashedMap();
            if (jv != null) {
                for (UpdateMutilVo j : jv) {
                    if (StringUtils.isEmpty(j.getId()) || StringUtils.isEmpty(j.getName())) {
                        throw new CommonException("医生id和name不能为空");
                    }
                    //1.判断医生是否存在，并且是否已经接受，否则重新发送消息通知
                    param.put("sql", "select stat from CHIS_ConstMber where refid =#{refid} and id=#{id}");
                    param.put("refid", mv.getGuid());
                    param.put("id", j.getId());

                    List<Map<String, Object>> li = multimapper.exSql(param);
                    JoinMutilVo m = new JoinMutilVo();
                    m.setRefid(mv.getGuid());
                    m.setId(j.getId());
                    m.setName(j.getName());

                    if (li != null && li.size() > 0) {
                        m.setStat((String) li.get(0).get("stat"));

                    } else {
                        m.setStat("2");
                    }
                    jm.add(m);
                }
                //先删除
                param.put("sql", "delete CHIS_ConstMber where refid=#{refid}");
                param.put("refid", mv.getGuid());
                multimapper.deleteSql(param);
                //添加成员
                if (jm.size() > 0){
                    adminForJoin(jm);
                }
            }

        } catch (Exception e) {
            LOGGER.error("updateMutil server层异常：" + e.getMessage(), e);
            throw new CommonException("COM001",e.getMessage(), e);
        }
    }


}
