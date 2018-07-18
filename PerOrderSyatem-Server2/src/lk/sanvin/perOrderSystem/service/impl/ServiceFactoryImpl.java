/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import lk.sanvin.perOrderSystem.service.ServiceFactory;
import lk.sanvin.perOrderSystem.service.SuperService;
import lk.sanvin.perOrderSystem.service.custom.impl.AdminServiceImpl;
import lk.sanvin.perOrderSystem.service.custom.impl.CashierServiceImpl;
import lk.sanvin.perOrderSystem.service.custom.impl.CookServiceImpl;
import lk.sanvin.perOrderSystem.service.custom.impl.DeliverServiceImpl;
import lk.sanvin.perOrderSystem.service.custom.impl.ItemServiceImpl;
import lk.sanvin.perOrderSystem.service.custom.impl.OrderDetailServiceImpl;
import lk.sanvin.perOrderSystem.service.custom.impl.OrderServiceImpl;
import lk.sanvin.perOrderSystem.service.custom.impl.TPOServiceImpl;

/**
 *
 * @author LahiruPG
 */
public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory{

    public static ServiceFactory serviceFactory;

    public ServiceFactoryImpl()throws RemoteException{
    }
   
    public static ServiceFactory getInstance()throws RemoteException{
        if(serviceFactory==null){
            serviceFactory=new ServiceFactoryImpl();
        }
        return serviceFactory;
    }
    @Override
    public SuperService getService(ServiceTypes types) throws Exception {
        switch(types){
            case TPO:
                return new TPOServiceImpl();
            case COOK:
                return new CookServiceImpl();
            case ITM:
                return new ItemServiceImpl();
            case ORDER:
                return new OrderServiceImpl();
            case ORDER_DETAIL:
                return new OrderDetailServiceImpl();
            case ADMIN:
                return new AdminServiceImpl();
            case CASHIER:
                return new CashierServiceImpl();
            case DELIVER:
                return new DeliverServiceImpl();
            default:
                return null;
        }
    }

}
