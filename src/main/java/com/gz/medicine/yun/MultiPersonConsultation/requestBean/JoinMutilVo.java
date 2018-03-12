package com.gz.medicine.yun.MultiPersonConsultation.requestBean;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by dlf on 2018/1/29 0029.
 * Email 1429264916@qq.com
 */
public class JoinMutilVo implements Serializable{


    //医生编号
    @NotEmpty(message = "医生编号不能为空")
    private String id;
    //医生名称
    @NotEmpty(message = "医生名称不能为空")
    private String name;
    //状态(0.成员邀请 1.管理员确认 2.医生确认 3.邀请成功)
    private String stat;
    //诊室guid
    @NotEmpty(message = "诊室guid不能为空")
    private String refid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getRefid() {
        return refid;
    }

    public void setRefid(String refid) {
        this.refid = refid;
    }
}
