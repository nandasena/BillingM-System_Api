package com.createvision.sivilima.tableModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sub_category")
public class SubCategory extends BaseObject implements Serializable {
    @Basic
    @Column(name = "name")
    private String name;
    public String getName() { return name; }
    public void setName(String name) {this.name = name;}

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_category_id",nullable = false)
    @JsonIgnore
    private MainCategory mainCategory;

    public MainCategory getMainCategory() { return mainCategory;}
    public void setMainCategory(MainCategory mainCategory) {this.mainCategory = mainCategory;}
}
