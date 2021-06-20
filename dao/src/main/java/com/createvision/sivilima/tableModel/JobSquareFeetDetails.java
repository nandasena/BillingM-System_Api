package com.createvision.sivilima.tableModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "jobSquareFeetDetails")
public class JobSquareFeetDetails extends BaseObject implements Serializable {

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
    @Column(name = "square_feet")
    private double squareFeet;
    public double getSquareFeet() {return squareFeet;}
    public void setSquareFeet(double squareFeet) {this.squareFeet = squareFeet;}

    @Basic
    @Column(name = "amount")
    private double amount;
    public double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount = amount;}


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id",nullable = false)
    @JsonIgnore
    private Job job;
    public Job getJob() {return job;}
    public void setJob(Job job) {this.job = job;}
}
