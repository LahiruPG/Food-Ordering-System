/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author LahiruPG
 */
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oid;
    private String date;
    private String name;
    private String contact;
    private String address;
    private String deliveryDateTime;
    private String status;
    private String tpo;
    private String cook;
    private String cashier;
    private String deliver;

    public Orders() {
    }

    public Orders(int oid, String date, String name, String contact, String address, String deliveryDateTime, String status, String tpo, String cook,String cashier, String deliver) {
        this.oid = oid;
        this.date = date;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.deliveryDateTime = deliveryDateTime;
        this.status = status;
        this.tpo = tpo;
        this.cook = cook;
        this.cashier=cashier;
        this.deliver = deliver;
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
     * @return the tpo
     */
    public String getTpo() {
        return tpo;
    }

    /**
     * @param tpo the tpo to set
     */
    public void setTpo(String tpo) {
        this.tpo = tpo;
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

    @Override
    public String toString() {
        return "Orders{" + "oid=" + oid + ", date=" + date + ", name=" + name + ", contact=" + contact + ", address=" + address + ", deliveryDateTime=" + deliveryDateTime + ", status=" + status + ", tpo=" + tpo + ", cook=" + cook + ", deliver=" + deliver + '}';
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
