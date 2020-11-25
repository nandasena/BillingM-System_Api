package com.createvision.sivilima.tableModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Customer extends BaseObject implements Serializable {
    @Basic
    @Column(name = "first_name")
    private String firstName;
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    @Basic
    @Column(name = "last_name")
    private String lastName;
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    @Basic
    @Column(name = "address1")
    private String  address1;
    public String getAddress1() { return address1; }
    public void setAddress1(String address1) { this.address1 = address1; }

    @Basic
    @Column(name = "address2")
    private String  address2;
    public String getAddress2() { return address2; }
    public void setAddress2(String address2) { this.address2 = address2; }

    @Basic
    @Column(name = "address3")
    private String  address3;
    public String getAddress3() { return address3; }
    public void setAddress3(String address3) { this.address3 = address3; }

    @Basic
    @Column(name = "telephone_no")
    private String telephoneNo;
    public String getTelephoneNo() {return telephoneNo;}
    public void setTelephoneNo(String telephoneNo) { this.telephoneNo = telephoneNo;}

    @Basic
    @Column(name = "email")
    private String email;
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email;}

    @Basic
    @Column(name = "NIC")
    private String  NIC;

    public String getNIC() { return NIC; }
    public void setNIC(String NIC) { this.NIC = NIC; }

    @Basic
    @Column(name = "remark")
    private String  remark;
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_branch_id")
    @JsonIgnore
    private Branch  branch;
    public Branch getBranch() {return branch;}
    public void setBranch(Branch branch) {this.branch = branch;}
}
