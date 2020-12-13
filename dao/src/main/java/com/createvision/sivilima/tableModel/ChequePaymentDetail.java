package com.createvision.sivilima.tableModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cheque_payment_detail")
public class ChequePaymentDetail extends BaseObject implements Serializable {
    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "clearing_date")
    private Date clearingDate;

    @Basic
    @Column(name = "cheque_date")
    private Date chequeDate;

    @Basic
    @Column(name = "is_clear",columnDefinition = "boolean default false")
    private boolean isClear;

    @Basic
    @Column(name = "cheque_number")
    private String chequeNumber;

    @Basic
    @Column(name = "clearing_details")
    private String clearingDetails;

    @Enumerated(EnumType.STRING)
    private ChequeStatus cheque_status;

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_bank_id")
    private BankDetail bankDetail;

    @Basic
    @Column(name = "received_or_given")
    @Enumerated(EnumType.STRING)
    private ReceivedOrGivenCheque receivedOrGivenCheque;




    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getClearingDate() { return clearingDate; }
    public void setClearingDate(Date clearingDate) { this.clearingDate = clearingDate; }

    public Date getChequeDate() {return chequeDate;}
    public void setChequeDate(Date chequeDate) {this.chequeDate = chequeDate;}

    public boolean isClear() { return isClear; }
    public void setClear(boolean clear) { isClear = clear; }

    public String getChequeNumber() { return chequeNumber; }
    public void setChequeNumber(String chequeNumber) { this.chequeNumber = chequeNumber;}

    public String getClearingDetails() {return clearingDetails;}
    public void setClearingDetails(String clearingDetails) {this.clearingDetails = clearingDetails;}

    public BankDetail getBankDetail() { return bankDetail; }
    public void setBankDetail(BankDetail bankDetail) { this.bankDetail = bankDetail; }

    public ChequeStatus getCheque_status() {return cheque_status;}
    public void setCheque_status(ChequeStatus cheque_status) {this.cheque_status = cheque_status;}

    public ReceivedOrGivenCheque getReceivedOrGivenCheque() {return receivedOrGivenCheque;}
    public void setReceivedOrGivenCheque(ReceivedOrGivenCheque receivedOrGivenCheque) {this.receivedOrGivenCheque = receivedOrGivenCheque;}
}
