/*
* Copyright(C) 2017-2020 贯众健康公司
* @date 2017-12-02
*/
package com.gz.medicine.yun.doctor.bean;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * @Title Inspectionitem表的实体类
 * @Description 检查项目
 * @version 1.0
 * @Author fendo
 * @Date 2018年01月02日 14时46分37秒 星期二 
 */
public class Inspectionitem implements Serializable{ 


    private static final long serialVersionUID = 1L;
 
    /**
    *  主键
    */  
    private String id;
    /**
    *  名字
    */  
    private String name;
    /**
    *  所属类别
    */  
    private String category;
    /**
    *  创建人
    */  
    private String createname;
    /**
    *  创建时间
    */  
    private Date createdate;
    /**
    *  更新人
    */  
    private String updatename;
    /**
    *  更新时间
    */  
    private Date updatedate;
     
    public Inspectionitem(){
    } 
    
    public String getId(){    
      return this.id;    
    }

    public void setId(String id){    
      this.id = id;    
    }    
    public String getName(){    
      return this.name;    
    }

    public void setName(String name){    
      this.name = name;    
    }    
    public String getCategory(){    
      return this.category;    
    }

    public void setCategory(String category){    
      this.category = category;    
    }    
    public String getCreatename(){    
      return this.createname;    
    }

    public void setCreatename(String createname){    
      this.createname = createname;    
    }    
    public Date getCreatedate(){    
      return this.createdate;    
    }

    public void setCreatedate(Date createdate){    
      this.createdate = createdate;    
    }    
    public String getUpdatename(){    
      return this.updatename;    
    }

    public void setUpdatename(String updatename){    
      this.updatename = updatename;    
    }    
    public Date getUpdatedate(){    
      return this.updatedate;    
    }

    public void setUpdatedate(Date updatedate){    
      this.updatedate = updatedate;    
    }    
  
      
}  