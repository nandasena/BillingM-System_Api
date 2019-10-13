package com.createvision.sivilima.tableModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "main_category")
public class MainCategory {
    @Basic
    @Column(name = "name")
    private String name;

}
