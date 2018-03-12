package com.gz.medicine.yun.MultiPersonConsultation.requestBean;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by dlf on 2018/1/29 0029.
 * Email 1429264916@qq.com
 */
public class UpdateMutilVo implements Serializable{


    //医生编号
    @NotEmpty(message = "医生编号不能为空")
    private String id;
    //医生名称
    @NotEmpty(message = "医生名称不能为空")
    private String name;
    private String guid;

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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getRefid() {
        return refid;
    }

    public void setRefid(String refid) {
        this.refid = refid;
    }
}
