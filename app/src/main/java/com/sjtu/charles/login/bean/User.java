package com.sjtu.charles.login.bean;

import java.io.Serializable;

/**
 * Created by charles on 2016/3/29.
 */
public class User implements Serializable {
    private static final long serialVersionUID = -2431302683946145513L;
    private String facility;
    private String funcList;
    private String jobCard;
    private String password;
    private String permissionList;
    private String permissionmap;
    private String sysbuttonmap;
    private String sysdepartmentid;
    private String username;
    private String token;
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getFuncList() {
        return funcList;
    }

    public void setFuncList(String funcList) {
        this.funcList = funcList;
    }

    public String getJobCard() {
        return jobCard;
    }

    public void setJobCard(String jobCard) {
        this.jobCard = jobCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(String permissionList) {
        this.permissionList = permissionList;
    }

    public String getPermissionmap() {
        return permissionmap;
    }

    public void setPermissionmap(String permissionmap) {
        this.permissionmap = permissionmap;
    }

    public String getSysbuttonmap() {
        return sysbuttonmap;
    }

    public void setSysbuttonmap(String sysbuttonmap) {
        this.sysbuttonmap = sysbuttonmap;
    }

    public String getSysdepartmentid() {
        return sysdepartmentid;
    }

    public void setSysdepartmentid(String sysdepartmentid) {
        this.sysdepartmentid = sysdepartmentid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
