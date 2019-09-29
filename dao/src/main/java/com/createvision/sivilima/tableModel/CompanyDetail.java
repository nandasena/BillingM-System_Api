package com.createvision.sivilima.tableModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "company_detail")
public class CompanyDetail extends BaseObject implements Serializable {
    @Basic
    @Column(name = "company_name")
    String companyName;

    @Basic
    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "companyDetail")
    private Set<User> userList;


}
