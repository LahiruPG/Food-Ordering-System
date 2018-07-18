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
import lk.sanvin.perOrderSystem.dto.CookDTO;
import lk.sanvin.perOrderSystem.observer.Observer;
import lk.sanvin.perOrderSystem.observer.Subject;
import lk.sanvin.perOrderSystem.service.custom.CookService;
import static lk.sanvin.perOrderSystem.service.custom.impl.OrderServiceImpl.alObservers;

/**
 *
 * @author LahiruPG
 */
public class CookServiceImpl extends UnicastRemoteObject implements CookService,Subject{
    private CookBO bO;
    public static ArrayList<Observer> alObservers = new ArrayList<>();
    
    public CookServiceImpl() throws Exception{
        bO= (CookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COOK);
    }

    @Override
    public boolean add(CookDTO o) throws Exception {
        boolean result = bO.add(o);
        return result;
    }

    @Override
    public boolean remove(CookDTO o) throws Exception {
        boolean remove = bO.remove(o);
        return remove;
    }

    @Override
    public boolean update(CookDTO o) throws Exception {
        boolean result = bO.update(o);
        notfyObserver();
        return result;
    }

    @Override
    public List<CookDTO> getAll() throws Exception {
        return bO.getAll();
       
    }

    @Override
    public List<CookDTO> finbyNicAndPassword(String nic, String password) throws Exception {
        return bO.finbyNicAndPassword(nic, password);
    }

    @Override
    public List<CookDTO> finbyStatus(String status) throws Exception {
        return bO.finbyStatus(status);
    }

    @Override
    public void regesterObserver(Observer o) throws Exception {
        System.out.println("regesterd Cook");
        alObservers.add(o);
    }

    @Override
    public void unregesterObserver(Observer o) throws Exception {
        System.out.println("Unregesterd Cook");
        alObservers.remove(o);
    }

    @Override
    public void notfyObserver() throws Exception {
        System.out.println("NotifyObserver Cook");
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
