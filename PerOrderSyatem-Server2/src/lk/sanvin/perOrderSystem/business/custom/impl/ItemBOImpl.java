/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.business.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.sanvin.perOrderSystem.business.custom.ItemBO;
import lk.sanvin.perOrderSystem.dto.ItemDTO;
import lk.sanvin.perOrderSystem.entity.Item;
import lk.sanvin.perOrderSystem.repository.RepositoryFactory;
import lk.sanvin.perOrderSystem.repository.custom.ItemRepository;
import lk.sanvin.perOrderSystem.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author LahiruPG
 */
public class ItemBOImpl implements ItemBO {

    private ItemRepository repository;

    public ItemBOImpl() {
        this.repository = (ItemRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ITEM);
    }

    @Override
    public boolean add(ItemDTO o) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            repository.setSession(session);
            session.beginTransaction();
            Item item = new Item(o.getDiscription(), o.getAmount());
            boolean result = repository.save(item);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public boolean remove(ItemDTO o) throws Exception {
         try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            repository.setSession(session);
            
            session.beginTransaction();
            
          Item item = new Item(o.getId(),o.getDiscription(), o.getAmount());
            
            boolean result = false;

            if (item != null) {

                repository.delete(item);
            }
            
            session.getTransaction().commit();

            return result;
        }catch(Exception exp){
            exp.printStackTrace();
            return false;
        }
    
    }

    @Override
    public boolean update(ItemDTO o) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            repository.setSession(session);
            
            session.beginTransaction();
            
            Item item = new Item(o.getId(),o.getDiscription(), o.getAmount());
            repository.update(item);
            
            session.getTransaction().commit();
            
            return true;
        }catch(Exception exp){
            exp.printStackTrace();
            return false;
        }
        
    }

    @Override
    public List<ItemDTO> getAll() throws Exception {
            try(Session session=HibernateUtil.getSessionFactory().openSession()){
                repository.setSession(session);
                session.beginTransaction();
                List<Item>cools=repository.findAll();
                session.getTransaction().commit();
                if(cools!=null){
                    List<ItemDTO> allDto=new ArrayList<>();
                    for (Item o : cools) {
                        ItemDTO item = new ItemDTO(o.getId(),o.getDiscription(), o.getAmount());
                        allDto.add(item);
                    }
                    return allDto;
                }else{
                    return null;
                }
            }
    }

}
