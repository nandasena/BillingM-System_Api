package com.createvision.sivilima.tableModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "company_detail")
public class CompanyDetail extends BaseObject implements Serializable {
    @Basic
    @Column(name = "company_name")
    String companyName;

    @Basic
    @Column(name = "description")
    private String description;
}
