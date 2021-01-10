package com.createvision.sivilima.tableModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "temp_customer")
public class TempCustomer extends BaseObject implements Serializable {

    @Basic
    @Column(name = "first_name")
    private String firstName;
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    @Basic
    @Column(name = "last_name")
    private String lastName;
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    @Basic
    @Column(name = "address1")
    private String  address1;
    public String getAddress1() {return address1;}
    public void setAddress1(String address1) {this.address1 = address1;}

    @Basic
    @Column(name = "telephone_no")
    private String telephoneNo;
    public String getTelephoneNo() {return telephoneNo;}
    public void setTelephoneNo(String telephoneNo) {this.telephoneNo = telephoneNo;}
}
