package com.createvision.sivilima.tableModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "brand")
public class Brand extends BaseObject implements Serializable {

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "brand_code")
    private String brand_code;

    @Basic
    @Column(name = "remark")
    private String remark;

    @Basic
    @Column(name = "description")
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand_code() {
        return brand_code;
    }

    public void setBrand_code(String brand_code) {
        this.brand_code = brand_code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
