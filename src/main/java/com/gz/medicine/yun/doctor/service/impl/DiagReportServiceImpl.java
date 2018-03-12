/*
* Copyright(C) 2017-2020 贯众健康公司
* @date 2017-12-02
*/
package com.gz.medicine.yun.doctor.service.impl;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.common.mybatisPageVo.Page;
import com.gz.medicine.common.mybatisPageVo.PageModel;
import com.gz.medicine.yun.doctor.bean.DiagReport;
import com.gz.medicine.yun.doctor.mapper.DTsickbldtlMapper;
import com.gz.medicine.yun.doctor.mapper.DiagReportMapper;
import com.gz.medicine.yun.doctor.service.DiagReportService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**   
 * @Title: DiagReportImpl.java 
 * @Package com.gz.medicine.yun.doctor.serviceImpl
 * @Description: 奕诊 ServiceImpl
 * @author fendo
 * @date 2018年01月04日 10时29分19秒 星期四 
 * @version V1.0   
*/
@Service
public class DiagReportServiceImpl implements DiagReportService{

    public static final Logger LOGGER = Logger.getLogger(DiagReportService.class);

	@Autowired
	public DiagReportMapper diagReportMapper;

	@Autowired
	public DTsickbldtlMapper dTsickbldtlMapper;

	@Override
	public DiagReport find(String id) throws CommonException{
	    LOGGER.info("[/DiagReportImpl/find]");
	    DiagReport diagReport;
	    try {
            diagReport = diagReportMapper.selectByPrimaryKey(id);
        }catch (Exception e){
            LOGGER.error("diagReport-find()-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","diagReport-find()-Service层出现异常",e);
        }
		return diagReport;
	}

	@Override
	public DiagReport findByUserId(String guid) throws CommonException{
		LOGGER.info("[/DiagReportImpl/findByUserId]");
		DiagReport diagReport;
		JSONArray jsonArrayNew  = new JSONArray();
		try {
			diagReport = diagReportMapper.findByUserId(guid);
			if(diagReport != null){
				if(StringUtils.isNotEmpty(diagReport.getMedicines())){
					if(diagReport.getMedicines()!=null){
						JSONArray jsonArray = JSONArray.fromObject(diagReport.getMedicines());
						if(jsonArray.size()>0){
							for (int i = 0; i < jsonArray.size(); i++) {
								JSONObject json = JSONObject.fromObject(jsonArray.get(i));
								String med_code = json.getString("med_code");
								if(StringUtils.isNotEmpty(med_code)){
									int flag = dTsickbldtlMapper.countById(med_code);
									if(flag>0){
										jsonArrayNew.add(json);
									}
								}


							}
							diagReport.setMedicines(jsonArrayNew.toString());
						}
					}
				}
			}

		}catch (Exception e){
			LOGGER.error("diagReport-findByUserId()-Service层出现异常:" + e.getMessage(), e);
			throw  new CommonException("COM001","JOSN格式有误",e);
		}
		return diagReport;
	}

	@Override
	public List<DiagReport> findList(DiagReport bean) throws CommonException{
	    LOGGER.info("[/DiagReportImpl/findList]");
	    List<DiagReport> diagReportList;
	    try {
            diagReportList = diagReportMapper.selectAll(bean);
        }catch (Exception e){
            LOGGER.error("diagReport-findList-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","diagReport-findList()-Service层出现异常",e);
        }
		return diagReportList;
	}

	@Override
	public Page getPage(PageModel page) throws CommonException{
	    LOGGER.info("[/DiagReportImpl/getPage]");
	    Page p=page.getPage();
	    try {
             List<DiagReport> pageList = diagReportMapper.queryPage(p);
		     p.setParameterType(pageList);
        }catch (Exception e){
            LOGGER.error("diagReport-getPage-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","diagReport-getPage()-Service层出现异常",e);
        }
		return p;
	}

	@Override
	public DiagReport update(DiagReport bean) throws CommonException{
	    LOGGER.info("[/DiagReportImpl/update]");
	    try {
           diagReportMapper.updateByPrimaryKeySelective(bean);
        }catch (Exception e){
            LOGGER.error("diagReport-update-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","diagReport-update()-Service层出现异常",e);
        }
		return null;
	}

	@Override
	public int insert(DiagReport bean) throws CommonException{
	    LOGGER.info("[/DiagReportImpl/insert]");
	    int flag;
	    try {
           flag = diagReportMapper.insertSelective(bean);
        }catch (Exception e){
            LOGGER.error("diagReport-update-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","diagReport-insert()-Service层出现异常",e);
        }
		return flag;
	}

	@Override
	public int delete(String id) throws CommonException{
	    LOGGER.info("[/DiagReportImpl/delete]");
	    int flag;
	    try {
           flag = diagReportMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            LOGGER.error("diagReport-update-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","diagReport-delete()-Service层出现异常",e);
        }
		return flag;
	}

}
