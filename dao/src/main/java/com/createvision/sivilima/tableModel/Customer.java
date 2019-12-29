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

    @Basic
    @Column(name = "fk_branch_id")
    private Brand  brand;
    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }
}
