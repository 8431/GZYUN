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
import com.gz.medicine.yun.doctor.bean.DiagnosisRecords;
import com.gz.medicine.yun.doctor.service.DiagnosisRecordsService;
import com.gz.medicine.yun.doctor.mapper.DiagnosisRecordsMapper;

/**   
 * @Title: DiagnosisRecordsImpl.java 
 * @Package com.gz.medicine.yun.doctor.serviceImpl
 * @Description: 诊断表_病历 ServiceImpl
 * @author fendo
 * @date 2018年01月08日 10时34分34秒 星期一 
 * @version V1.0   
*/
@Service
public class DiagnosisRecordsServiceImpl implements DiagnosisRecordsService{

    public static final Logger LOGGER = Logger.getLogger(DiagnosisRecordsService.class);

	@Autowired
	public DiagnosisRecordsMapper diagnosisRecordsMapper;

	@Override
	public DiagnosisRecords find(String id) throws CommonException{
	    LOGGER.info("[/DiagnosisRecordsImpl/find]");
	    DiagnosisRecords diagnosisRecords;
	    try {
            diagnosisRecords = diagnosisRecordsMapper.selectByPrimaryKey(id);
        }catch (Exception e){
            LOGGER.error("diagnosisRecords-find()-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","diagnosisRecords-find()-Service层出现异常",e);
        }
		return diagnosisRecords;
	}

	@Override
	public List<DiagnosisRecords> findList(DiagnosisRecords bean) throws CommonException{
	    LOGGER.info("[/DiagnosisRecordsImpl/findList]");
	    List<DiagnosisRecords> diagnosisRecordsList;
	    try {
            diagnosisRecordsList = diagnosisRecordsMapper.selectAll(bean);
        }catch (Exception e){
            LOGGER.error("diagnosisRecords-findList-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","diagnosisRecords-findList()-Service层出现异常",e);
        }
		return diagnosisRecordsList;
	}

	@Override
	public Page getPage(PageModel page) throws CommonException{
	    LOGGER.info("[/DiagnosisRecordsImpl/getPage]");
	    Page p=page.getPage();
	    try {
             List<DiagnosisRecords> pageList = diagnosisRecordsMapper.queryPage(p);
		     p.setParameterType(pageList);
        }catch (Exception e){
            LOGGER.error("diagnosisRecords-getPage-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","diagnosisRecords-getPage()-Service层出现异常",e);
        }
		return p;
	}

	@Override
	public DiagnosisRecords update(DiagnosisRecords bean) throws CommonException{
	    LOGGER.info("[/DiagnosisRecordsImpl/update]");
	    try {
           diagnosisRecordsMapper.updateByPrimaryKeySelective(bean);
        }catch (Exception e){
            LOGGER.error("diagnosisRecords-update-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","diagnosisRecords-update()-Service层出现异常",e);
        }
		return null;
	}

	@Override
	public int insert(DiagnosisRecords bean) throws CommonException{
	    LOGGER.info("[/DiagnosisRecordsImpl/insert]");
	    int flag;
	    try {
           flag = diagnosisRecordsMapper.insertSelective(bean);
        }catch (Exception e){
            LOGGER.error("diagnosisRecords-update-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","diagnosisRecords-insert()-Service层出现异常",e);
        }
		return flag;
	}

	@Override
	public int delete(String id) throws CommonException{
	    LOGGER.info("[/DiagnosisRecordsImpl/delete]");
	    int flag;
	    try {
           flag = diagnosisRecordsMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            LOGGER.error("diagnosisRecords-update-Service层出现异常:" + e.getMessage(), e);
            throw  new CommonException("COM001","diagnosisRecords-delete()-Service层出现异常",e);
        }
		return flag;
	}

	@Override
	public int inserList(List<DiagnosisRecords> diagnosisRecordsList) throws CommonException {
		LOGGER.info("[/DiagnosisRecordsImpl/inserList]");
		int flag = 0;
		try {
			if(diagnosisRecordsList.size()>0){
				for (int i = 0; i < diagnosisRecordsList.size(); i++) {
					flag = diagnosisRecordsMapper.insertSelective(diagnosisRecordsList.get(i));
				}
			}

		}catch (Exception e){
			LOGGER.error("diagnosisRecords-inserList-Service层出现异常:" + e.getMessage(), e);
			throw  new CommonException("COM001","diagnosisRecords-inserList()-Service层出现异常",e);
		}
		return flag;
	}

	@Override
	public int deleteOK(String sid) throws CommonException {
		LOGGER.info("[/DiagnosisRecordsImpl/deleteOK]");
		int flag;
		try {
			flag = diagnosisRecordsMapper.deleteOK(sid);
		}catch (Exception e){
			LOGGER.error("diagnosisRecords-update-Service层出现异常:" + e.getMessage(), e);
			throw  new CommonException("COM001","diagnosisRecords-deleteOK()-Service层出现异常",e);
		}
		return flag;
	}
}
