package com.gz.medicine.yun.doctor.request;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class DTmessageRecordRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="guid不能为空！")
	private String guid;// '内部唯一编号';

	private String usrguid;// '内部唯一编号';

	private String docguid;// '内部唯一编号';

	public String getUsrguid() {
		return usrguid;
	}

	public void setUsrguid(String usrguid) {
		this.usrguid = usrguid;
	}

	public String getDocguid() {
		return docguid;
	}

	public void setDocguid(String docguid) {
		this.docguid = docguid;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

}
