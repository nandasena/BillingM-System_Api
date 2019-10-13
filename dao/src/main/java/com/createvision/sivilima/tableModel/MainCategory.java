package com.createvision.sivilima.tableModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "main_category")
public class MainCategory extends BaseObject implements Serializable {
    @Basic
    @Column(name = "name")
    private String name;

}
