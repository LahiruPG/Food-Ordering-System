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
import lk.sanvin.perOrderSystem.business.custom.CookBO;
import lk.sanvin.perOrderSystem.business.custom.DeliverBO;
import lk.sanvin.perOrderSystem.dto.CookDTO;
import lk.sanvin.perOrderSystem.dto.DeliverDTO;
import lk.sanvin.perOrderSystem.observer.Observer;
import lk.sanvin.perOrderSystem.observer.Subject;
import lk.sanvin.perOrderSystem.service.custom.CookService;
import lk.sanvin.perOrderSystem.service.custom.DeliverService;
import static lk.sanvin.perOrderSystem.service.custom.impl.OrderServiceImpl.alObservers;

/**
 *
 * @author LahiruPG
 */
public class DeliverServiceImpl extends UnicastRemoteObject implements DeliverService,Subject{
    private DeliverBO bO;
    public static ArrayList<Observer> alObservers = new ArrayList<>();
    
    public DeliverServiceImpl() throws Exception{
        bO=  (DeliverBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DELIVER);
    }

    @Override
    public boolean add(DeliverDTO o) throws Exception {
        boolean result = bO.add(o);
        return result;
    }

    @Override
    public boolean remove(DeliverDTO o) throws Exception {
        boolean remove = bO.remove(o);
        return remove;
    }

    @Override
    public boolean update(DeliverDTO o) throws Exception {
        boolean result = bO.update(o);
        notfyObserver();
        return result;
    }

    @Override
    public List<DeliverDTO> getAll() throws Exception {
        return bO.getAll();
       
    }

    @Override
    public List<DeliverDTO> finbyNicAndPassword(String nic, String password) throws Exception {
        return bO.finbyNicAndPassword(nic, password);
    }

    @Override
    public List<DeliverDTO> finbyStatus(String status) throws Exception {
        return bO.finbyStatus(status);
    }

    @Override
    public void regesterObserver(Observer o) throws Exception {
        System.out.println("regesterd Deliver");
        alObservers.add(o);
    }

    @Override
    public void unregesterObserver(Observer o) throws Exception {
        System.out.println("Unregesterd Deliver");
        alObservers.remove(o);
    }

    @Override
    public void notfyObserver() throws Exception {
        System.out.println("NotifyObserver Deliver");
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
