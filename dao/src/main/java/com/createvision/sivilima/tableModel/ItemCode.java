package com.createvision.sivilima.tableModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "item_code")
public class ItemCode extends BaseObject implements Serializable {

    @Basic
    @Column(name = "code_name")
    private String codeName;
    public String getCodeName() { return codeName; }
    public void setCodeName(String codeName) { this.codeName = codeName;}

    @Basic
    @Column(name = "code")
    private String code;
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code;}

    @Basic
    @Column(name = "last_number")
    private int lastNumber;
    public int getLastNumber() { return lastNumber; }
    public void setLastNumber(int lastNumber) { this.lastNumber = lastNumber; }
}
