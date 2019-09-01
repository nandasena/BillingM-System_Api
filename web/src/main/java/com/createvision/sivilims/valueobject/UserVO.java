package com.createvision.sivilims.valueobject;

//import com.sun.xml.internal.ws.developer.StreamingAttachment;

import com.createvision.sivilima.model.BaseObject;
import com.createvision.sivilima.model.Invoice;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


public class UserVO extends BaseObject implements Serializable {

    private String userName;
    private String name;
    private String password;
    private Date createdDate;
    private Date lastUpdatedDate;
    private int roleId;

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    public Date getLastUpdatedDate() { return lastUpdatedDate; }
    public void setLastUpdatedDate(Date lastUpdatedDate) { this.lastUpdatedDate = lastUpdatedDate; }

    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }


}
