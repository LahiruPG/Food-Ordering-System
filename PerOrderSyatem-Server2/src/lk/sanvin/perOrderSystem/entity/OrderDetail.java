/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

/**
 *
 * @author LahiruPG
 */
@Entity
public class OrderDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int qty;
    private double unitPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "oid", referencedColumnName = "oid", insertable = false, updatable = false)
    private Orders order;
    private String Discription;
    private double amount;
    @EmbeddedId
    private OrderDetail_PK orderDetail_PK;

    public OrderDetail() {
    }

    public OrderDetail(int id, int qty, double unitPrice, Orders order, String itemDiscription, double amount, OrderDetail_PK orderDetail_PK) {
        this.id = id;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.order = order;
        this.Discription = itemDiscription;
        this.amount = amount;
        this.orderDetail_PK = orderDetail_PK;
    }
    public OrderDetail(int id, int qty, double unitPrice, Orders order, String itemDiscription, double amount) {
        this.id = id;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.order = order;
        this.Discription = itemDiscription;
        this.amount = amount;
    }
    public OrderDetail(int qty, double unitPrice, Orders order, String itemDiscription, double amount) {
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.order = order;
        this.Discription = itemDiscription;
        this.amount = amount;
    }
    

    public OrderDetail(int qty, double unitPrice, double amount, Orders order, String itemDiscription, OrderDetail_PK orderDetail_PK) {
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.order = order;
        this.amount = amount;
        this.Discription = itemDiscription;
        this.orderDetail_PK = orderDetail_PK;
    }

    public OrderDetail(int qty, double unitPrice, double amount, Orders order, String itemDiscription, OrderDetail_PK orderDetail_PK, int oid) {
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.order = order;
        this.Discription = itemDiscription;
        this.orderDetail_PK = new OrderDetail_PK(oid);
    }

    /**
     * @return the qty
     */
    public int getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * @return the unitPrice
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the order
     */
    public Orders getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Orders order) {
        this.order = order;
    }

    /**
     * @return the Discription
     */
    public String getDiscription() {
        return Discription;
    }

    /**
     * @param itemDiscription the Discription to set
     */
    public void setDiscription(String itemDiscription) {
        this.Discription = itemDiscription;
    }

    /**
     * @return the orderDetail_PK
     */
    public OrderDetail_PK getOrderDetail_PK() {
        return orderDetail_PK;
    }

    /**
     * @param orderDetail_PK the orderDetail_PK to set
     */
    public void setOrderDetail_PK(OrderDetail_PK orderDetail_PK) {
        this.orderDetail_PK = orderDetail_PK;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "qty=" + qty + ", unitPrice=" + unitPrice + ", order=" + order + ", itemDiscription=" + Discription + ", orderDetail_PK=" + orderDetail_PK + '}';
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

}
