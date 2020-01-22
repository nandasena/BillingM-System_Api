package com.createvision.sivilima.valuesObject;

import java.util.List;

public class PurchaseOrderVO {
    private String description;
    private String estimateReceiveDate;
    private Long supplierId;
    private Long userId;
    private String purchaseCode;
    private String supplierName;
    private String address1;
    private String address2;
    private List<ItemVO> itemVOList;
    private String userName;
    private double totalAmount;
    private double totalDiscount;
    private Long purchaseOrderId;
    private String supplierCode;
    private Long branchId;
    private String purchaseOrderDate;


    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getEstimateReceiveDate() { return estimateReceiveDate; }
    public void setEstimateReceiveDate(String estimateReceiveDate) { this.estimateReceiveDate = estimateReceiveDate; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<ItemVO> getItemVOList() { return itemVOList; }
    public void setItemVOList(List<ItemVO> itemVOList) { this.itemVOList = itemVOList; }

    public String getPurchaseCode() { return purchaseCode; }
    public void setPurchaseCode(String purchaseCode) { this.purchaseCode = purchaseCode; }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    public String getAddress1() { return address1; }
    public void setAddress1(String address1) { this.address1 = address1; }

    public String getAddress2() { return address2; }
    public void setAddress2(String address2) { this.address2 = address2; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public double getTotalDiscount() { return totalDiscount; }
    public void setTotalDiscount(double totalDiscount) { this.totalDiscount = totalDiscount; }

    public Long getPurchaseOrderId() { return purchaseOrderId; }
    public void setPurchaseOrderId(Long purchaseOrderId) { this.purchaseOrderId = purchaseOrderId; }

    public String getSupplierCode() { return supplierCode; }
    public void setSupplierCode(String supplierCode) { this.supplierCode = supplierCode; }

    public Long getBranchId() { return branchId; }
    public void setBranchId(Long branchId) { this.branchId = branchId;}

    public String getPurchaseOrderDate() { return purchaseOrderDate; }
    public void setPurchaseOrderDate(String purchaseOrderDate) { this.purchaseOrderDate = purchaseOrderDate; }
}
