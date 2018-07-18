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
public class OrderDetail_PK implements Serializable{
    private int oid;
  

    public OrderDetail_PK() {
    }

    public OrderDetail_PK(int oid) {
        this.oid = oid;
       
    }
    
}
