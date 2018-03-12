/*
* Copyright(C) 2017-2020 贯众健康公司
* @date 2017-12-02
*/
package com.gz.medicine.yun.doctor.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title DiagReport表的实体类
 * @Description 奕诊
 * @version 1.0
 * @Author fendo
 * @Date 2018年01月04日 10时29分19秒 星期四 
 */
public class DiagReport implements Serializable{ 


    private static final long serialVersionUID = 1L;
 
    /**
    *  
    */  
    private String guid;
    /**
    *  病人id

    */  
    private String patientid;
    /**
    *  从Envive获取的病人的访问ID

    */  
    private String patientvid;
    /**
    *  医生id

    */  
    private String docid;
    /**
    *  提交时间
    */  
    private String submitime;
    /**
    *  医院名称或医院代码
    */  
    private String hospital;
    /**
    *  合作方

    */  
    private String partner;
    /**
    *  时间戳
    */  
    private String time;
    /**
    *  症状
    */  
    private String symptoms;
    /**
    *  诊断
    */  
    private String diagnosis;
    /**
    *  进一步检查项目
    */  
    private String tests;
    /**
    *  药品
    */  
    private String medicines;
    /**
    *  
    */  
    private Date crtdat;
     
    public DiagReport(){
    } 
    
    public String getGuid(){    
      return this.guid;    
    }

    public void setGuid(String guid){    
      this.guid = guid;    
    }    
    public String getPatientid(){    
      return this.patientid;    
    }

    public void setPatientid(String patientid){    
      this.patientid = patientid;    
    }    
    public String getPatientvid(){    
      return this.patientvid;    
    }

    public void setPatientvid(String patientvid){    
      this.patientvid = patientvid;    
    }    
    public String getDocid(){    
      return this.docid;    
    }

    public void setDocid(String docid){    
      this.docid = docid;    
    }    
    public String getSubmitime(){    
      return this.submitime;    
    }

    public void setSubmitime(String submitime){    
      this.submitime = submitime;    
    }    
    public String getHospital(){    
      return this.hospital;    
    }

    public void setHospital(String hospital){    
      this.hospital = hospital;    
    }    
    public String getPartner(){    
      return this.partner;    
    }

    public void setPartner(String partner){    
      this.partner = partner;    
    }    
    public String getTime(){    
      return this.time;    
    }

    public void setTime(String time){    
      this.time = time;    
    }    
    public String getSymptoms(){    
      return this.symptoms;    
    }

    public void setSymptoms(String symptoms){    
      this.symptoms = symptoms;    
    }    
    public String getDiagnosis(){    
      return this.diagnosis;    
    }

    public void setDiagnosis(String diagnosis){    
      this.diagnosis = diagnosis;    
    }    
    public String getTests(){    
      return this.tests;    
    }

    public void setTests(String tests){    
      this.tests = tests;    
    }    
    public String getMedicines(){    
      return this.medicines;    
    }

    public void setMedicines(String medicines){    
      this.medicines = medicines;    
    }    
    public Date getCrtdat(){    
      return this.crtdat;    
    }

    public void setCrtdat(Date crtdat){    
      this.crtdat = crtdat;    
    }    
  
      
}  