/*
* Copyright(C) 2017-2020 贯众健康公司
* @date 2017-12-02
*/
package com.gz.medicine.yun.doctor.service.impl;

import java.util.List;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.common.mybatisPageVo.Page;
import com.gz.medicine.common.mybatisPageVo.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.gz.medicine.yun.doctor.bean.Inspectionitem;
import com.gz.medicine.yun.doctor.service.InspectionitemService;
import com.gz.medicine.yun.doctor.mapper.InspectionitemMapper;

/**   
 * @Title: InspectionitemImpl.java 
 * @Package com.gz.medicine.yun.doctor.serviceImpl
 * @Description: 检查项目 ServiceImpl
 * @author fendo
 * @date 2018年01月02日 14时46分37秒 星期二 
 * @version V1.0   
*/
@Service
public class InspectionitemServiceImpl implements InspectionitemService{

    public static final Logger LOGGER = Logger.getLogger(InspectionitemService.class);

	@Autowired
	public InspectionitemMapper inspectionitemMapper;

	@Override
	public Inspectionitem find(String id) throws CommonException{
	    LOGGER.info("[/InspectionitemImpl/find]");
	    Inspectionitem inspectionitem;
	    try {
            inspectionitem = inspectionitemMapper.selectByPrimaryKey(id);
        }catch (Exception e){
            LOGGER.error("inspectionitem-find()-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","inspectionitem-find()-Service层出现异常",e);
        }
		return inspectionitem;
	}

	@Override
	public List<Inspectionitem> findList(Inspectionitem bean) throws CommonException{
	    LOGGER.info("[/InspectionitemImpl/findList]");
	    List<Inspectionitem> inspectionitemList;
	    try {
            inspectionitemList = inspectionitemMapper.selectAll(bean);
        }catch (Exception e){
            LOGGER.error("inspectionitem-findList-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","inspectionitem-findList()-Service层出现异常",e);
        }
		return inspectionitemList;
	}

	@Override
	public Page getPage(PageModel page) throws CommonException{
	    LOGGER.info("[/InspectionitemImpl/getPage]");
	    Page p=page.getPage();
	    try {
             List<Inspectionitem> pageList = inspectionitemMapper.queryPage(p);
		     p.setParameterType(pageList);
        }catch (Exception e){
            LOGGER.error("inspectionitem-getPage-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","inspectionitem-getPage()-Service层出现异常",e);
        }
		return p;
	}

	@Override
	public Inspectionitem update(Inspectionitem bean) throws CommonException{
	    LOGGER.info("[/InspectionitemImpl/update]");
	    try {
           inspectionitemMapper.updateByPrimaryKeySelective(bean);
        }catch (Exception e){
            LOGGER.error("inspectionitem-update-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","inspectionitem-update()-Service层出现异常",e);
        }
		return null;
	}

	@Override
	public int insert(Inspectionitem bean) throws CommonException{
	    LOGGER.info("[/InspectionitemImpl/insert]");
	    int flag;
	    try {
           flag = inspectionitemMapper.insertSelective(bean);
        }catch (Exception e){
            LOGGER.error("inspectionitem-update-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","inspectionitem-insert()-Service层出现异常",e);
        }
		return flag;
	}

	@Override
	public int delete(String id) throws CommonException{
	    LOGGER.info("[/InspectionitemImpl/delete]");
	    int flag;
	    try {
           flag = inspectionitemMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            LOGGER.error("inspectionitem-update-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","inspectionitem-delete()-Service层出现异常",e);
        }
		return flag;
	}

}
