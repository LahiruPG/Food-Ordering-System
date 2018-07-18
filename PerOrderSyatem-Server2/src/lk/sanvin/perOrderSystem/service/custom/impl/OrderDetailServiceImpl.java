/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.service.custom.impl;

import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import lk.sanvin.perOrderSystem.business.BOFactory;
import lk.sanvin.perOrderSystem.business.custom.OrderDetailBO;
import lk.sanvin.perOrderSystem.dto.OrderDetailDTO;
import lk.sanvin.perOrderSystem.service.custom.OrderDetailService;

/**
 *
 * @author LahiruPG
 */
public class OrderDetailServiceImpl extends UnicastRemoteObject implements OrderDetailService{
    private OrderDetailBO bo;

    public OrderDetailServiceImpl()throws Exception{
        bo=(OrderDetailBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER_DETAIL);
        
    }
    
    @Override
    public boolean add(OrderDetailDTO o) throws Exception {
        boolean result = bo.add(o);
        return result;
    }

    @Override
    public boolean remove(OrderDetailDTO o) throws Exception {
        boolean remove = bo.remove(o);
        return remove;
    }

    @Override
    public boolean update(OrderDetailDTO o) throws Exception {
        boolean result = bo.update(o);
        return result;
    }

    @Override
    public List<OrderDetailDTO> getAll() throws Exception {
        System.out.println("od serviveImpl.getAll +++++++++");
        return bo.getAll();
    }

    @Override
    public List<OrderDetailDTO> findByOid(int oid) throws Exception {
        return bo.finbyOid(oid);
    }

    @Override
    public List<OrderDetailDTO> finbyid(int oid) throws Exception {
        return bo.finbyid(oid);
    }
    
    
    
}
