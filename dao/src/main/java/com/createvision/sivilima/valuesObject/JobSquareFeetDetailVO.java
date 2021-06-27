package com.createvision.sivilima.valuesObject;

public class JobSquareFeetDetailVO {
    private int id;
    private double amount;
    private String description;
    private double ratePerSquareFeet;
    private double squareFeet;


    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public double getRatePerSquareFeet() {return ratePerSquareFeet;}
    public void setRatePerSquareFeet(double ratePerSquareFeet) {this.ratePerSquareFeet = ratePerSquareFeet;}

    public double getSquareFeet() {return squareFeet;}
    public void setSquareFeet(double squareFeet) {this.squareFeet = squareFeet;}

    public double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount = amount;}
}
