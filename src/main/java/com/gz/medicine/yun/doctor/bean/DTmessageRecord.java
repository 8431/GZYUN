package com.gz.medicine.yun.doctor.bean;

import java.util.Date;

/**
 * 短信记录
 * 
 * @author 维维
 *
 */
public class DTmessageRecord {
	private String guid;// '内部唯一编号';

	private String org;// '系统编号';

	private String docguid;// '医生guid';

	private String usrguid;// '患者guid';

	private Date messagesenddate;// '短信发送时间';

	private String messagesendcontent;// '短信发送内容';

	private String crtusr;// '创建人';

	private Date crtdat;// '创建时间';

	private String updateusr;// '更新人';

	private Date updatedate;// '更新时间';

	private String status; //删除标志位
	
	private String followupdate;
	
	private String followtype;
	
	private String name;

	
	
	public String getFollowupdate() {
		return followupdate;
	}

	public void setFollowupdate(String followupdate) {
		this.followupdate = followupdate;
	}

	public String getFollowtype() {
		return followtype;
	}

	public void setFollowtype(String followtype) {
		this.followtype = followtype;
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
		this.guid = guid == null ? null : guid.trim();
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org == null ? null : org.trim();
	}

	public String getDocguid() {
		return docguid;
	}

	public void setDocguid(String docguid) {
		this.docguid = docguid == null ? null : docguid.trim();
	}

	public String getUsrguid() {
		return usrguid;
	}

	public void setUsrguid(String usrguid) {
		this.usrguid = usrguid == null ? null : usrguid.trim();
	}

	public Date getMessagesenddate() {
		return messagesenddate;
	}

	public void setMessagesenddate(Date messagesenddate) {
		this.messagesenddate = messagesenddate;
	}

	public String getMessagesendcontent() {
		return messagesendcontent;
	}

	public void setMessagesendcontent(String messagesendcontent) {
		this.messagesendcontent = messagesendcontent == null ? null : messagesendcontent.trim();
	}

	public String getCrtusr() {
		return crtusr;
	}

	public void setCrtusr(String crtusr) {
		this.crtusr = crtusr == null ? null : crtusr.trim();
	}

	public Date getCrtdat() {
		return crtdat;
	}

	public void setCrtdat(Date crtdat) {
		this.crtdat = crtdat;
	}

	public String getUpdateusr() {
		return updateusr;
	}

	public void setUpdateusr(String updateusr) {
		this.updateusr = updateusr == null ? null : updateusr.trim();
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}