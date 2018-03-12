package com.gz.medicine.gzyun.heaman.reponse;

import java.io.Serializable;
import java.util.Date;

public class HmCaseupRecordReponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String guid; // guid

	private String usrguid; // 患者guid

	private String reporttype; // 报告类型

	private Date reporttime; // 报告时间

	private String picture; // 图片

	private String jdusr; // 解读员

	private String jddate; // 解读时间

	private String jdreport; // 解读内容

	private String stuts;

	private Date crtdat;

	private String name; // 姓名

	private String age; // 年龄

	private String sex;// 性别

	public HmCaseupRecordReponse() {
		// TODO Auto-generated constructor stub
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getUsrguid() {
		return usrguid;
	}

	public void setUsrguid(String usrguid) {
		this.usrguid = usrguid;
	}

	public String getReporttype() {
		return reporttype;
	}

	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}

	public Date getReporttime() {
		return reporttime;
	}

	public void setReporttime(Date reporttime) {
		this.reporttime = reporttime;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getJdusr() {
		return jdusr;
	}

	public void setJdusr(String jdusr) {
		this.jdusr = jdusr;
	}

	public String getJddate() {
		return jddate;
	}

	public void setJddate(String jddate) {
		this.jddate = jddate;
	}

	public String getJdreport() {
		return jdreport;
	}

	public void setJdreport(String jdreport) {
		this.jdreport = jdreport;
	}

	public String getStuts() {
		return stuts;
	}

	public void setStuts(String stuts) {
		this.stuts = stuts;
	}

	public Date getCrtdat() {
		return crtdat;
	}

	public void setCrtdat(Date crtdat) {
		this.crtdat = crtdat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "HmCaseupRecordReponse [guid=" + guid + ", usrguid=" + usrguid + ", reporttype=" + reporttype
				+ ", reporttime=" + reporttime + ", picture=" + picture + ", jdusr=" + jdusr + ", jddate=" + jddate
				+ ", jdreport=" + jdreport + ", stuts=" + stuts + ", crtdat=" + crtdat + "]";
	}

}
