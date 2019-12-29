package com.createvision.sivilima.tableModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "supplier")
public class Supplier extends BaseObject implements Serializable {

    @Basic
    @Column(name = "supplier_id",nullable = false)
    private String  supplierId;

    @Basic
    @Column(name = "name")
    private String  name;

    @Basic
    @Column(name = "address_1")
    private String  address1;

    @Basic
    @Column(name = "address_2")
    private String  address2;

    @Basic
    @Column(name = "phone_number")
    private String  phoneNumber;

    @Basic
    @Column(name = "fk_branch_id")
    private Brand  brand;


    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
