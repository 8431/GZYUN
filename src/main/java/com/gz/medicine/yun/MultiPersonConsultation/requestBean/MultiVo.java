package com.gz.medicine.yun.MultiPersonConsultation.requestBean;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dlf on 2018/1/29 0029.
 * Email 1429264916@qq.com
 */
public class MultiVo  implements Serializable{
    private String guid;
    //诊室名称
    @NotEmpty(message = "诊室名称不能为空。")
    private String name;
    //患者编号
    @NotEmpty(message = "患者编号不能为空。")
    private String patient;
    //患者机构码
    @NotEmpty(message = "患者机构码不能为空。")
    private String dept;
    //开始日期
    @NotEmpty(message = "开始日期不能为空。")
    private String dat1;
    //时间
    @NotEmpty(message = "时间不能为空。")
    private String time;

    @NotEmpty(message = "创建人不能为空。")
    private String crtusr;
    //诊室说明
    private String note;

    private List<UpdateMutilVo> joinmutilvo;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDat1() {
        return dat1;
    }

    public void setDat1(String dat1) {
        this.dat1 = dat1;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCrtusr() {
        return crtusr;
    }

    public void setCrtusr(String crtusr) {
        this.crtusr = crtusr;
    }

    public List<UpdateMutilVo> getJoinmutilvo() {
        return joinmutilvo;
    }

    public void setJoinmutilvo(List<UpdateMutilVo> joinmutilvo) {
        this.joinmutilvo = joinmutilvo;
    }
}
