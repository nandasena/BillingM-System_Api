package com.createvision.sivilima.tableModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "return_type")
public class ReturnType extends BaseObject implements Serializable {


    @Basic
    @Column(name = "description")
    private String  description;

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Basic
    @Column(name = "return_type_code")
    private String  returnTypeCode;

    public String getReturnTypeCode() { return returnTypeCode; }
    public void setReturnTypeCode(String returnTypeCode) { this.returnTypeCode = returnTypeCode; }
}
