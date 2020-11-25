package com.createvision.sivilima.valuesObject;

public class CustomerSupplierVO {

    private String firstName;
    private String lastName;
    private Long customerId;
    private Long supplierId;
    private String contactNumber;
    private String email;
    private String remark;
    private String address1;
    private String address2;
    private String address3;
    private String NIC;
    private Long branchId;


    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId;}

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    public String getAddress1() { return address1; }
    public void setAddress1(String address1) { this.address1 = address1; }

    public String getAddress2() { return address2; }
    public void setAddress2(String address2) { this.address2 = address2; }

    public String getAddress3() { return address3; }
    public void setAddress3(String address3) { this.address3 = address3; }

    public String getNIC() { return NIC; }
    public void setNIC(String NIC) { this.NIC = NIC; }

    public Long getBranchId() {return branchId;}
    public void setBranchId(Long branchId) {this.branchId = branchId;}
}
