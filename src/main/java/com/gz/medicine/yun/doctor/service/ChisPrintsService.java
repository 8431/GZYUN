package com.gz.medicine.yun.doctor.service;

import org.springframework.stereotype.Service;

import com.gz.medicine.common.exception.CommonException;
import com.gz.medicine.yun.doctor.bean.ChisPrints;

@Service("ChisPrintsService")
public interface ChisPrintsService {
	
	public void insert(ChisPrints record) throws CommonException;

}
