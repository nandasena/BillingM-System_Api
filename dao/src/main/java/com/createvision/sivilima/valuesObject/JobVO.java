package com.createvision.sivilima.valuesObject;

import java.util.List;

public class JobVO {
    private Long jobId;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private int state;
    private String status;
    private Long customerId;
    private double amount;
    private double discount;
    private double ratePerSquareFeet;
    private double squareFeet;
    private double cost;
    private int costType;
    private int paymentType;
    private String jobNumber;
    private List<ItemVO> itemVOList;
    private List<OtherExpensesVO>otherExpensesVOList;

    public Long getJobId() {return jobId;}
    public void setJobId(Long jobId) {this.jobId = jobId;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getStartDate() {return startDate;}
    public void setStartDate(String startDate) {this.startDate = startDate;}

    public String getEndDate() {return endDate;}
    public void setEndDate(String endDate) {this.endDate = endDate;}

    public int getState() {return state;}
    public void setState(int state) {this.state = state;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public Long getCustomerId() {return customerId;}
    public void setCustomerId(Long customerId) {this.customerId = customerId;}

    public double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount = amount;}

    public double getRatePerSquareFeet() {return ratePerSquareFeet;}
    public void setRatePerSquareFeet(double ratePerSquareFeet) {this.ratePerSquareFeet = ratePerSquareFeet;}

    public double getSquareFeet() {return squareFeet;}
    public void setSquareFeet(double squareFeet) {this.squareFeet = squareFeet;}

    public double getDiscount() {return discount;}
    public void setDiscount(double discount) {this.discount = discount;}

    public double getCost() {return cost;}
    public void setCost(double cost) {this.cost = cost;}

    public int getCostType() {return costType;}
    public void setCostType(int costType) {this.costType = costType;}

    public int getPaymentType() {return paymentType;}
    public void setPaymentType(int paymentType) {this.paymentType = paymentType;}

    public String getJobNumber() {return jobNumber;}
    public void setJobNumber(String jobNumber) {this.jobNumber = jobNumber;}

    public List<ItemVO> getItemVOList() {return itemVOList;}
    public void setItemVOList(List<ItemVO> itemVOList) {this.itemVOList = itemVOList;}

    public List<OtherExpensesVO> getOtherExpensesVOList() {return otherExpensesVOList;}
    public void setOtherExpensesVOList(List<OtherExpensesVO> otherExpensesVOList) {this.otherExpensesVOList = otherExpensesVOList;}
}
