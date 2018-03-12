package com.gz.medicine.yun.doctor.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.common.util.UUIDTool;
import com.gz.medicine.yun.doctor.bean.ChisPrints;
import com.gz.medicine.yun.doctor.mapper.ChisPrintsMapper;
import com.gz.medicine.yun.doctor.service.ChisPrintsService;

@Service
public class ChisPrintsServiceImpl implements ChisPrintsService {
	
	@Autowired
	private ChisPrintsMapper chisPrintsMapper;

	@Override
	public void insert(ChisPrints record) throws CommonException {
		record.setGuid(UUIDTool.getUUID());
		record.setStat("1");
		record.setDat(new Date());
		int a = chisPrintsMapper.insert(record);
		if(a<0){
			throw new CommonException("002", "插入数据失败！");
		}
	}

}
