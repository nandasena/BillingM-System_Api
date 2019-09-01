package com.createvision.sivilims.valueobject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class BaseObjectVO implements Serializable {

    private static final long serialVersionUID = -5553688390351585204L;

    protected Long id;
    protected Long createdBy;
    protected Date createdAt;
    protected Long updatedBy;
    protected Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}