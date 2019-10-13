package com.createvision.sivilima.valuesObject;

public class CategoryVO {
    long subCategoryId;
    long mainCategoryId;
    String name;

    public long getSubCategoryId() { return subCategoryId; }
    public void setSubCategoryId(long subCategoryId) { this.subCategoryId = subCategoryId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name;}

    public long getMainCategoryId() { return mainCategoryId;}
    public void setMainCategoryId(long mainCategoryId) { this.mainCategoryId = mainCategoryId;}
}
