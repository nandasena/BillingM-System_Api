package com.createvision.sivilima.tableModel;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "bank_detail")
public class BankDetail extends BaseObject implements Serializable {

    @Basic
    @Column(name = "bank_name")
    private String bankName;

    @Basic
    @Column(name = "branch_name")
    private String branchName;

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getBranchName() { return branchName; }
    public void setBranchName(String branchName) { this.branchName = branchName; }
}
