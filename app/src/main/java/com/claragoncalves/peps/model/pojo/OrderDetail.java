package com.claragoncalves.peps.model.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "order_detail_table", foreignKeys = {@ForeignKey(entity = Order.class, parentColumns = "id", childColumns = "orderId", onDelete = ForeignKey.CASCADE), @ForeignKey(entity = Product.class, parentColumns = "id", childColumns = "productId", onDelete = ForeignKey.CASCADE),  @ForeignKey(entity = Contact.class, parentColumns = "id", childColumns = "contactId", onDelete = ForeignKey.CASCADE)})
public class OrderDetail {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;
    private Integer orderId;
    private String contactId;
    private Integer productId;
    private Integer productQuantity;

    public OrderDetail() {
    }

    @Ignore
    public OrderDetail(Integer orderId, String contactId, Integer productId, Integer productQuantity) {
        this.orderId = orderId;
        this.contactId = contactId;
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Ignore
    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", contactId='" + contactId + '\'' +
                ", productId=" + productId +
                ", productQuantity=" + productQuantity +
                '}';
    }
}
