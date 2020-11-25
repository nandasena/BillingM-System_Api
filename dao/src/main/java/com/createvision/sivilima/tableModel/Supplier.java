package com.createvision.sivilima.tableModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "supplier")
public class Supplier extends BaseObject implements Serializable {

    @Basic
    @Column(name = "supplier_id",nullable = false)
    private String  supplierId;

    @Basic
    @Column(name = "first_name")
    private String  firstName;

    @Basic
    @Column(name = "last_name")
    private String  lastName;

    @Basic
    @Column(name = "address_1")
    private String  address1;

    @Basic
    @Column(name = "address_2")
    private String  address2;
    @Basic
    @Column(name = "address_3")
    private String  address3;

    @Basic
    @Column(name = "phone_number")
    private String  phoneNumber;

    @Basic
    @Column(name = "email")
    private String  email;

    @Basic
    @Column(name = "NIC")
    private String  NIC;

    @Basic
    @Column(name = "remark")
    private String  remark;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_branch_id")
    @JsonIgnore
    private Branch  branch;


    public String getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() { return address3; }
    public void setAddress3(String address3) { this.address3 = address3; }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName;}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNIC() { return NIC; }
    public void setNIC(String NIC) { this.NIC = NIC; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    public Branch getBranch() {return branch;}
    public void setBranch(Branch branch) {this.branch = branch;}
}
