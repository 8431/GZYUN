/*
* Copyright(C) 2017-2020 贯众健康公司
* @date 2017-12-02
*/
package com.gz.medicine.yun.doctor.bean;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * @Title DiagnosisRecords表的实体类
 * @Description 诊断表_病历
 * @version 1.0
 * @Author fendo
 * @Date 2018年01月08日 10时34分34秒 星期一 
 */
public class DiagnosisRecords implements Serializable{ 


    private static final long serialVersionUID = 1L;
 
    /**
    *  主键
    */  
    private String id;
    /**
    *  诊断ID
    */  
    private String zid;
    /**
    *  诊断名称
    */  
    private String zname;

    /**
     * 病历ID
     */
    private String sid;

    public DiagnosisRecords(){
    } 
    
    public String getId(){    
      return this.id;    
    }

    public void setId(String id){    
      this.id = id;    
    }    
    public String getZid(){    
      return this.zid;    
    }

    public void setZid(String zid){    
      this.zid = zid;    
    }    
    public String getZname(){    
      return this.zname;    
    }

    public void setZname(String zname){    
      this.zname = zname;    
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}