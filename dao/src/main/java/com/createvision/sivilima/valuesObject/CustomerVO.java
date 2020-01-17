package com.createvision.sivilima.valuesObject;

import java.lang.invoke.LambdaConversionException;

public class CustomerVO {

    private String firstName;
    private String lastName;
    private Long customerId;
    private Long supplierId;


    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId;}

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
}
