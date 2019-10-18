package com.createvision.sivilima.tableModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Customer extends BaseObject implements Serializable {
    @Basic
    @Column(name = "name")
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Basic
    @Column(name = "address")
    private String  address;
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address;}


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
}
