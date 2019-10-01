package com.createvision.sivilima.tableModel;

//import com.sun.xml.internal.ws.developer.StreamingAttachment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User extends BaseObject implements Serializable {

    @Basic
    @Column(name = "user_name")
    private String userName;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "created_date")
    private Date createdDate;

    @Basic
    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id",nullable = false,referencedColumnName = "role_id")
    private Set<UserRole> userRole;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Fetch(FetchMode.SELECT)
    @JsonIgnore
    private Set<Invoice> invoice = new HashSet<Invoice>(0);

    @Basic
    @Column(name = "is_delete",nullable = false,columnDefinition = "boolean default false")
    private boolean isDelete;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyDetail companyDetail;


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

    public Set<UserRole> getUserRole() { return userRole; }
    public void setUserRole(Set<UserRole> userRole) { this.userRole = userRole; }

    public Set<Invoice> getInvoice() { return invoice; }
    public void setInvoice(Set<Invoice> invoice) { this.invoice = invoice; }

    public boolean isDelete() { return isDelete;}
    public void setDelete(boolean delete) { isDelete = delete;}

    public CompanyDetail getCompanyDetail() { return companyDetail; }
    public void setCompanyDetail(CompanyDetail companyDetail) { this.companyDetail = companyDetail;}
}
