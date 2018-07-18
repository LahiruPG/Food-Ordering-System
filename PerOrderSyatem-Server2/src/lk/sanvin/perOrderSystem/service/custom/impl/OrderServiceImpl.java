/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.service.custom.impl;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.sanvin.perOrderSystem.business.BOFactory;
import lk.sanvin.perOrderSystem.business.custom.OrderBO;
import lk.sanvin.perOrderSystem.dto.OrderDTO;
import lk.sanvin.perOrderSystem.observer.Observer;
import lk.sanvin.perOrderSystem.observer.Subject;
import lk.sanvin.perOrderSystem.service.custom.OrderService;

/**
 *
 * @author LahiruPG
 */
public class OrderServiceImpl extends UnicastRemoteObject implements OrderService, Subject {

    private OrderBO bO;
    public static ArrayList<Observer> alObservers = new ArrayList<>();

    public OrderServiceImpl() throws Exception {
        bO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);
    }

    @Override
    public boolean add(OrderDTO o) throws Exception {
        boolean result = bO.add(o);
        notfyObserver();
        return result;
    }

    @Override
    public boolean remove(OrderDTO o) throws Exception {
        boolean remove = bO.remove(o);
        notfyObserver();
        return remove;
    }

    @Override
    public boolean update(OrderDTO o) throws Exception {
        boolean result = bO.update(o);
        notfyObserver();
        return result;
    }

    @Override
    public List<OrderDTO> getAll() throws Exception {
        return bO.getAll();

    }

    @Override
    public List<OrderDTO> findByDate(String date) throws Exception {
        return bO.findByDate(date);
    }

    @Override
    public List<OrderDTO> finbyStatusAndDate(String status, String date) {
        try {
            return bO.finbyStatusAndDate(status, date);
        } catch (Exception ex) {
            Logger.getLogger(OrderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void regesterObserver(Observer o) throws Exception {
        System.out.println("regesterd order");
        alObservers.add(o);
    }

    @Override
    public void unregesterObserver(Observer o) throws Exception {
        System.out.println("Unregesterd order");
        alObservers.remove(o);
    }

    @Override
    public void notfyObserver() throws Exception {
        System.out.println("NotifyObserver order");
        new Thread(() -> {
            for (Observer o : alObservers) {
                try {
                    o.updateObserver();
                } catch (Exception ex) {
                    //System.out.println("Notify e");
                }
            }
        }).start();
    }

}
