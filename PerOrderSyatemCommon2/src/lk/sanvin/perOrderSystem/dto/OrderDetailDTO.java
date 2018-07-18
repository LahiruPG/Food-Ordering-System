/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.dto;

/**
 *
 * @author LahiruPG
 */
public class OrderDetailDTO extends SuperDTO{
    private int id;
    private String discription;
    private int qty;
    private double unitPrice;
    private double amount;
    private OrderDTO orderDTO;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int id, String discription, int qty, double unitPrice, double amount, OrderDTO orderDTO) {
        this.id = id;
        this.discription = discription;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.orderDTO = orderDTO;
    }
    public OrderDetailDTO(String discription, int qty, double unitPrice, double amount) {
        this.discription = discription;
        this.qty = qty;
        this.unitPrice = unitPrice;
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

    /**
     * @return the discription
     */
    public String getDiscription() {
        return discription;
    }

    /**
     * @param discription the discription to set
     */
    public void setDiscription(String discription) {
        this.discription = discription;
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
     * @return the orderDTO
     */
    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    /**
     * @param orderDTO the orderDTO to set
     */
    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" + "id=" + id + ", discription=" + discription + ", qty=" + qty + ", unitPrice=" + unitPrice + ", amount=" + amount + ", orderDTO=" + orderDTO + '}';
    }

}
