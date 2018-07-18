/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.proxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.sanvin.perOrderSystem.service.ServiceFactory;
import lk.sanvin.perOrderSystem.service.SuperService;
import lk.sanvin.perOrderSystem.service.custom.CookService;
import lk.sanvin.perOrderSystem.service.custom.OrderDetailService;
import lk.sanvin.perOrderSystem.service.custom.OrderService;

/**
 *
 * @author LahiruPG
 */
public class ProxyHandler implements ServiceFactory {

    private static ProxyHandler proxyHandler;
    private ServiceFactory serviceFactory;
    private OrderService orderService;
    private OrderDetailService orderDetailService;
    private CookService cookService;

    public ProxyHandler() {
        try {
            serviceFactory = (ServiceFactory) Naming.lookup("rmi://localhost:5050/pos");
            orderService = (OrderService) serviceFactory.getService(ServiceTypes.ORDER);
            orderDetailService = (OrderDetailService) serviceFactory.getService(ServiceTypes.ORDER_DETAIL);
            cookService = (CookService) serviceFactory.getService(ServiceTypes.COOK);
        } catch (NotBoundException ex) {
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ProxyHandler getInstance() {
        if (proxyHandler == null) {
            proxyHandler = new ProxyHandler();
        }
        return proxyHandler;
    }

    @Override
    public SuperService getService(ServiceTypes types) throws Exception {
        switch (types) {
            case ORDER:
                return orderService;
            case ORDER_DETAIL:
                return orderDetailService;
            case COOK:
                return cookService;
            default:
                return null;
        }

    }
}
