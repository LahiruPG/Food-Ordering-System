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
import lk.sanvin.perOrderSystem.business.custom.AdminBO;
import lk.sanvin.perOrderSystem.dto.AdminDTO;
import lk.sanvin.perOrderSystem.observer.Observer;
import lk.sanvin.perOrderSystem.service.custom.AdminService;

/**
 *
 * @author LahiruPG
 */
public class AdminServiceImpl extends UnicastRemoteObject implements AdminService{
    private AdminBO bO;
    public static ArrayList<Observer> alObservers = new ArrayList<>();
    
    public AdminServiceImpl() throws Exception{
        bO=  (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ADMIN);
    }

    @Override
    public boolean add(AdminDTO o) throws Exception {
        boolean result = bO.add(o);
        return result;
    }

    @Override
    public boolean remove(AdminDTO o) throws Exception {
        boolean remove = bO.remove(o);
        return remove;
    }

    @Override
    public boolean update(AdminDTO o) throws Exception {
        boolean result = bO.update(o);
        return result;
    }

    @Override
    public List<AdminDTO> getAll() throws Exception {
        return bO.getAll();
       
    }

    @Override
    public List<AdminDTO> finbyNicAndPassword(String nic, String password) throws Exception {
        return bO.finbyNicAndPassword(nic, password);
    }

    @Override
    public List<AdminDTO> finbyStatus(String status) throws Exception {
        return bO.finbyStatus(status);
    }

}
