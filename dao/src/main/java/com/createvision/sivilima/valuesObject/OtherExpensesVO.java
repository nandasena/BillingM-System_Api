package com.createvision.sivilima.valuesObject;

public class OtherExpensesVO {
    private Long id;
    private String name;
    private String description;
    private double amount;
    private Long expensesTypeId;


    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount = amount;}

    public Long getExpensesTypeId() {return expensesTypeId;}
    public void setExpensesTypeId(Long expensesTypeId) {this.expensesTypeId = expensesTypeId;}
}
