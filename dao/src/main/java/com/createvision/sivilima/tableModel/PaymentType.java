package com.createvision.sivilima.tableModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "payment_type")
public class PaymentType extends BaseObject implements Serializable {

    @Basic
    @Column(name = "payment_type_name")
    private String  paymentTypeName;

    @Basic
    @Column(name = "payment_type_id")
    private int paymentTypeId;


}
