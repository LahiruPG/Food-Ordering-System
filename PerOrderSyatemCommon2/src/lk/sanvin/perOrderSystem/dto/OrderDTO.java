/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.dto;

import java.util.List;

/**
 *
 * @author LahiruPG
 */
public class OrderDTO extends SuperDTO {

    private int oid;
    private String name;
    private String contact;
    private String address;
    private String date;
    private String deliveryDateTime;
    private Double amount;
    private String top;
    private String cook;
    private String cashier;
    private String deliver;
    private String status;
    private List<OrderDetailDTO> orderDetails;

    public OrderDTO() {
    }
    
     public OrderDTO(int oid) {
         this.oid=oid;
    }
     
    public OrderDTO(int oid, String name, String contact, String address, String date, String deliveryDateTime, Double amount, String top, String cook,String cashier, String deliver, String status, List<OrderDetailDTO> orderDetails) {
        this.oid = oid;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.date = date;
        this.deliveryDateTime = deliveryDateTime;
        this.amount = amount;
        this.top = top;
        this.cook = cook;
        this.cashier=cashier;
        this.deliver = deliver;
        this.status = status;
        this.orderDetails = orderDetails;
    }

    /**
     * @return the oid
     */
    public int getOid() {
        return oid;
    }

    /**
     * @param oid the oid to set
     */
    public void setOid(int oid) {
        this.oid = oid;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the deliveryDateTime
     */
    public String getDeliveryDateTime() {
        return deliveryDateTime;
    }

    /**
     * @param deliveryDateTime the deliveryDateTime to set
     */
    public void setDeliveryDateTime(String deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return the top
     */
    public String getTop() {
        return top;
    }

    /**
     * @param top the top to set
     */
    public void setTop(String top) {
        this.top = top;
    }

    /**
     * @return the cook
     */
    public String getCook() {
        return cook;
    }

    /**
     * @param cook the cook to set
     */
    public void setCook(String cook) {
        this.cook = cook;
    }

    /**
     * @return the deliver
     */
    public String getDeliver() {
        return deliver;
    }

    /**
     * @param deliver the deliver to set
     */
    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the orderDetails
     */
    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    /**
     * @param orderDetails the orderDetails to set
     */
    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "oid=" + oid + ", name=" + name + ", contact=" + contact + ", address=" + address + ", date=" + date + ", deliveryDateTime=" + deliveryDateTime + ", amount=" + amount + ", top=" + top + ", cook=" + cook + ", deliver=" + deliver + ", status=" + status + ", orderDetails=" + orderDetails + '}';
    }

    /**
     * @return the cashier
     */
    public String getCashier() {
        return cashier;
    }

    /**
     * @param cashier the cashier to set
     */
    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

}
