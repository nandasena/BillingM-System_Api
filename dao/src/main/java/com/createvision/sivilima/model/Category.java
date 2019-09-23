package com.createvision.sivilima.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "category")
public class Category extends BaseObject implements Serializable {
    @Basic
    @Column(name = "name")
    private String name;
    public String getName() { return name; }
    public void setName(String name) {this.name = name;}
}
