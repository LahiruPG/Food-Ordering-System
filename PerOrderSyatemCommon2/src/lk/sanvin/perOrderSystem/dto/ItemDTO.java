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
public class ItemDTO extends SuperDTO {

    private int id;
    private String discription;
    private double amount;

    public ItemDTO() {
    }

    public ItemDTO(int id, String discription, double amount) {
        this.id = id;
        this.discription = discription;
        this.amount = amount;
    }

    public ItemDTO(String discription, double amount) {
        this.discription = discription;
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

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", discription=" + discription + ", amount=" + amount + '}';
    }

}
