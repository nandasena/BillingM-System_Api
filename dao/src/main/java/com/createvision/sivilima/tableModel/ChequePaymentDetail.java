package com.createvision.sivilima.tableModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cheque_payment_detail")
public class ChequePaymentDetail extends BaseObject implements Serializable {
    @Basic
    @Column(name = "description")
    String description;

    @Basic
    @Column(name = "clearing_date")
    private Date clearingDate;

    @Basic
    @Column(name = "is_clear",columnDefinition = "boolean default false")
    private boolean isClear;

    @Basic
    @Column(name = "cheque_number")
    private String chequeNumber;

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getClearingDate() { return clearingDate; }
    public void setClearingDate(Date clearingDate) { this.clearingDate = clearingDate; }

    public boolean isClear() { return isClear; }
    public void setClear(boolean clear) { isClear = clear; }

    public String getChequeNumber() { return chequeNumber; }
    public void setChequeNumber(String chequeNumber) { this.chequeNumber = chequeNumber;}
}
