/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.service;

import java.rmi.Remote;



/**
 *
 * @author LahiruPG
 */
public interface ServiceFactory extends Remote{
    enum ServiceTypes{
        TPO,COOK,ITM,ORDER,ORDER_DETAIL,ADMIN,CASHIER,DELIVER
    }
    public SuperService getService(ServiceTypes types)throws Exception;
}
