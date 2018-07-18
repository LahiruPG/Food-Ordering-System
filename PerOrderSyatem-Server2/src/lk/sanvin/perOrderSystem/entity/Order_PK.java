/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.entity;

import java.io.Serializable;

/**
 *
 * @author LahiruPG
 */
public class Order_PK implements Serializable{
    private int tpoId;
    private int coolId;
    private int DeliverId;

    public Order_PK() {
    }

    public Order_PK(int tpoId, int coolId, int DeliverId) {
        this.tpoId = tpoId;
        this.coolId = coolId;
        this.DeliverId = DeliverId;
    }

}
