package com.createvision.sivilima.tableModel;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "jobs")
public class Job extends BaseObject implements Serializable {

    @Basic
    @Column(name = "job_number", unique = true, nullable = false)
    private String jobNumber;
    public String getJobNumber() {return jobNumber;}
    public void setJobNumber(String jobNumber) {this.jobNumber = jobNumber;}

    @Basic
    @Column(name = "name")
    private String jobName;
    public String getJobName() {return jobName;}
    public void setJobName(String jobName) {this.jobName = jobName;}


    @Basic
    @Column(name = "description")
    private String description;
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    @Basic
    @Column(name = "rate_per_square_feet")
    private double ratePerSqareFeet;
    public double getRatePerSqareFeet() {return ratePerSqareFeet;}
    public void setRatePerSqareFeet(double ratePerSqareFeet) {this.ratePerSqareFeet = ratePerSqareFeet;}

    @Basic
    @Column(name = "total_square_feet")
    private double totalSquareFeet;
    public double getTotalSquareFeet() {return totalSquareFeet;}
    public void setTotalSquareFeet(double totalSquareFeet) {this.totalSquareFeet = totalSquareFeet;}

    @Basic
    @Column(name = "start_date")
    private Date startDate;
    public Date getStartDate() {return startDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}

    @Basic
    @Column(name = "end_date")
    private Date endDate;
    public Date getEndDate() {return endDate;}
    public void setEndDate(Date endDate) {this.endDate = endDate;}

    @Basic
    @Column(name = "amount")
    private double amount;
    public double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount = amount;}

    @Basic
    @Column(name = "cost")
    private double cost;
    public double getCost() {return cost;}
    public void setCost(double cost) {this.cost = cost;}

    @Basic
    @Column(name = "discount")
    private double discount;
    public double getDiscount() {return discount;}
    public void setDiscount(double discount) {this.discount = discount;}

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_customer_id")
    private Customer customerId;
    public Customer getCustomerId() { return customerId;}
    public void setCustomerId(Customer customerId) {this.customerId = customerId;}

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_type_code",nullable = false,referencedColumnName = "type_code")
    private PaymentMethod paymentMethod;
    public PaymentMethod getPaymentMethod() {return paymentMethod;}
    public void setPaymentMethod(PaymentMethod paymentMethod) {this.paymentMethod = paymentMethod;}

    @Basic
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;
    public JobStatus getJobStatus() {return jobStatus;}
    public void setJobStatus(JobStatus jobStatus) {this.jobStatus = jobStatus;}


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "job")
    @Fetch(FetchMode.SELECT)
    private Set<JobDetails> jobDetails = new HashSet<JobDetails>(0);
    public Set<JobDetails> getInvoiceItemDetails() {return jobDetails;}
    public void setInvoiceItemDetails(Set<JobDetails> jobDetails) { this.jobDetails = jobDetails;}

    public Set<JobDetails> getJobDetails() {return jobDetails;}
    public void setJobDetails(Set<JobDetails> jobDetails) {this.jobDetails = jobDetails;}

}
