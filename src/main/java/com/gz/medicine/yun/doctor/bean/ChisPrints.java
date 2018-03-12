package com.gz.medicine.yun.doctor.bean;

import java.util.Date;

public class ChisPrints {
    private String guid;

    private String cfguid;

    private String stat;

    private Date dat;

    private String syt;

    private String org;

    private String acc;

    private String dept;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getCfguid() {
        return cfguid;
    }

    public void setCfguid(String cfguid) {
        this.cfguid = cfguid == null ? null : cfguid.trim();
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat == null ? null : stat.trim();
    }

    public Date getDat() {
        return dat;
    }

    public void setDat(Date dat) {
        this.dat = dat;
    }

    public String getSyt() {
        return syt;
    }

    public void setSyt(String syt) {
        this.syt = syt == null ? null : syt.trim();
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org == null ? null : org.trim();
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc == null ? null : acc.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }
}