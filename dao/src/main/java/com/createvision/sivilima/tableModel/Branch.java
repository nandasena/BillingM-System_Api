package com.createvision.sivilima.tableModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "branch")
public class Branch extends BaseObject implements Serializable {

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "remark")
    private String remark;

    @Basic
    @Column(name = "address1")
    private String address1;

    @Basic
    @Column(name = "address2")
    private String address2;

    @Basic
    @Column(name = "address3")
    private String address3;

    @Basic
    @Column(name = "postal_code")
    private String postal_code;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    public String getAddress1() { return address1; }
    public void setAddress1(String address1) { this.address1 = address1; }

    public String getAddress2() { return address2; }
    public void setAddress2(String address2) { this.address2 = address2; }

    public String getAddress3() { return address3; }
    public void setAddress3(String address3) { this.address3 = address3; }

    public String getPostal_code() { return postal_code; }
    public void setPostal_code(String postal_code) { this.postal_code = postal_code; }
}
