/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.service.custom.impl;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import lk.sanvin.perOrderSystem.business.BOFactory;
import lk.sanvin.perOrderSystem.business.custom.CashierBO;
import lk.sanvin.perOrderSystem.dto.CashierDTO;
import lk.sanvin.perOrderSystem.observer.Observer;
import lk.sanvin.perOrderSystem.observer.Subject;
import lk.sanvin.perOrderSystem.service.custom.CashierService;

/**
 *
 * @author LahiruPG
 */
public class CashierServiceImpl extends UnicastRemoteObject implements CashierService,Subject{
    private CashierBO bO;
    public static ArrayList<Observer> alObservers = new ArrayList<>();
    
    public CashierServiceImpl() throws Exception{
        bO= (CashierBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CASHIER);
    }

    @Override
    public boolean add(CashierDTO o) throws Exception {
        boolean result = bO.add(o);
        return result;
    }

    @Override
    public boolean remove(CashierDTO o) throws Exception {
        boolean remove = bO.remove(o);
        return remove;
    }

    @Override
    public boolean update(CashierDTO o) throws Exception {
        boolean result = bO.update(o);
        notfyObserver();
        return result;
    }

    @Override
    public List<CashierDTO> getAll() throws Exception {
        return bO.getAll();
       
    }

    @Override
    public List<CashierDTO> finbyNicAndPassword(String nic, String password) throws Exception {
        return bO.finbyNicAndPassword(nic, password);
    }

    @Override
    public List<CashierDTO> finbyStatus(String status) throws Exception {
        return bO.finbyStatus(status);
    }

    @Override
    public void regesterObserver(Observer o) throws Exception {
        System.out.println("regesterd Cashier");
        alObservers.add(o);
    }

    @Override
    public void unregesterObserver(Observer o) throws Exception {
        System.out.println("Unregesterd Cashier");
        alObservers.remove(o);
    }

    @Override
    public void notfyObserver() throws Exception {
        System.out.println("NotifyObserver Cashier");
        new Thread(() -> {
            for (Observer o : alObservers) {
                try {
                    o.updateObserver();
                } catch (Exception ex) {
                   // System.out.println("Notify e");
                }
            }
        }).start();
    }
    
    
}
