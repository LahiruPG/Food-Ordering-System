/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.service.custom.impl;

import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.sanvin.perOrderSystem.business.BOFactory;
import lk.sanvin.perOrderSystem.business.custom.ItemBO;
import lk.sanvin.perOrderSystem.business.custom.impl.ItemBOImpl;
import lk.sanvin.perOrderSystem.dto.ItemDTO;
import lk.sanvin.perOrderSystem.service.custom.ItemService;

/**
 *
 * @author LahiruPG
 */
public class ItemServiceImpl extends UnicastRemoteObject implements ItemService{
    private ItemBO bO;

    public ItemServiceImpl()throws Exception{
      
            bO=   (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
      

    }

    @Override
    public boolean add(ItemDTO o)  throws Exception{
       
            boolean result = bO.add(o);
            return result;
        
    }

    @Override
    public boolean remove(ItemDTO o) throws Exception {
        boolean remove = bO.remove(o);
        return remove;
    }

    @Override
    public boolean update(ItemDTO o) throws Exception {
        boolean result = bO.update(o);
        return result;
    }

    @Override
    public List<ItemDTO> getAll() throws Exception {
        return bO.getAll();
       
    }
    
    
}
