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
import lk.sanvin.perOrderSystem.service.custom.ItemService;
import lk.sanvin.perOrderSystem.service.custom.OrderDetailService;
import lk.sanvin.perOrderSystem.service.custom.OrderService;
import lk.sanvin.perOrderSystem.service.custom.TPOService;

/**
 *
 * @author LahiruPG
 */
public class ProxyHandler implements ServiceFactory {

    private static ProxyHandler proxyHandler;
    private ServiceFactory serviceFactory;
    private TPOService tPOService;
    private CookService cookService;
    private ItemService itemService;
    private OrderService orderService;
    private OrderDetailService orderDetailService;

    public ProxyHandler() {
        try {
            serviceFactory = (ServiceFactory) Naming.lookup("rmi://localhost:5050/pos");
            tPOService = (TPOService) serviceFactory.getService(ServiceTypes.TPO);
            cookService = (CookService) serviceFactory.getService(ServiceTypes.COOK);
            orderService = (OrderService) serviceFactory.getService(ServiceTypes.ORDER );
            itemService =    (ItemService) serviceFactory.getService(ServiceTypes.ITM );
            orderDetailService = (OrderDetailService) serviceFactory.getService(ServiceTypes.ORDER_DETAIL);
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
            case TPO:
                return tPOService;
            case COOK:
                return cookService;
            case ITM:
                return itemService;
            case ORDER:
                return orderService;
            case ORDER_DETAIL:
                return orderDetailService;
            default:
                return null;
        }

    }
}
