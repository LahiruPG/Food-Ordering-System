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
import lk.sanvin.perOrderSystem.business.custom.TPOBO;
import lk.sanvin.perOrderSystem.dto.TPODTO;
import lk.sanvin.perOrderSystem.observer.Observer;
import lk.sanvin.perOrderSystem.observer.Subject;
import lk.sanvin.perOrderSystem.service.custom.TPOService;
import static lk.sanvin.perOrderSystem.service.custom.impl.OrderServiceImpl.alObservers;

/**
 *
 * @author LahiruPG
 */
public class TPOServiceImpl extends UnicastRemoteObject implements TPOService, Subject {

    private TPOBO tpobo;
    public static ArrayList<Observer> alObservers = new ArrayList<>();

    public TPOServiceImpl() throws Exception {
        tpobo = (TPOBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.TPO);
    }

    @Override
    public boolean add(TPODTO o) throws Exception {
        boolean result = tpobo.add(o);
        return result;
    }

    @Override
    public boolean remove(TPODTO o) throws Exception {
        boolean remove = tpobo.remove(o);
        return remove;
    }

    @Override
    public boolean update(TPODTO o) throws Exception {
        boolean result = tpobo.update(o);
        notfyObserver();
        return result;
    }

    @Override
    public List<TPODTO> getAll() throws Exception {
        return tpobo.getAll();

    }

    @Override
    public List<TPODTO> finbyNicAndPassword(String nic, String password) throws Exception {
        return tpobo.finbyNicAndPassword(nic, password);
    }

    @Override
    public List<TPODTO> finbyStatus(String status) throws Exception {
        return tpobo.finbyStatus(status);
    }

    @Override
    public void regesterObserver(Observer o) throws Exception {
        System.out.println("regesterd TPO");
        alObservers.add(o);
    }

    @Override
    public void unregesterObserver(Observer o) throws Exception {
        System.out.println("Unregesterd TPO");

        alObservers.remove(o);
    }

    @Override
    public void notfyObserver() throws Exception {
        System.out.println("NotifyObserver TPO");
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
