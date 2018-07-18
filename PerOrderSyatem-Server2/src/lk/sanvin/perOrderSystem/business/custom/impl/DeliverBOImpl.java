/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.business.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.sanvin.perOrderSystem.business.custom.DeliverBO;
import lk.sanvin.perOrderSystem.dto.CookDTO;
import lk.sanvin.perOrderSystem.dto.DeliverDTO;
import lk.sanvin.perOrderSystem.entity.Cook;
import lk.sanvin.perOrderSystem.entity.Deliver;
import lk.sanvin.perOrderSystem.repository.RepositoryFactory;
import lk.sanvin.perOrderSystem.repository.custom.DeliverRepository;
import lk.sanvin.perOrderSystem.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author LahiruPG
 */
public class DeliverBOImpl implements DeliverBO {

    private DeliverRepository repository;

    public DeliverBOImpl() {
        this.repository = (DeliverRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.DELIVER);
    }

    @Override
    public boolean add(DeliverDTO o) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            repository.setSession(session);
            session.beginTransaction();
           Deliver deliver = new Deliver(o.getName(), o.getGender(), o.getAddress(), o.getContact(),o.getPassword(), o.getDob(), o.getNic());
            boolean result = repository.save(deliver);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public boolean remove(DeliverDTO o) throws Exception {
         try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            repository.setSession(session);
            
            session.beginTransaction();
            
            Deliver deliver=new Deliver(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(),o.getPassword(), o.getDob(), o.getNic(),o.getStatus());
            
            boolean result = false;

            if (deliver != null) {

                repository.delete(deliver);
            }
            
            session.getTransaction().commit();

            return result;
        }catch(Exception exp){
            exp.printStackTrace();
            return false;
        }
    
    }

    @Override
    public boolean update(DeliverDTO o) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            repository.setSession(session);
            
            session.beginTransaction();
            
            Deliver deliver=new Deliver(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(),o.getPassword(), o.getDob(), o.getNic(),o.getStatus());
            repository.update(deliver);
            
            session.getTransaction().commit();
            
            return true;
        }catch(Exception exp){
            exp.printStackTrace();
            return false;
        }
        
    }

    @Override
    public List<DeliverDTO> getAll() throws Exception {
            try(Session session=HibernateUtil.getSessionFactory().openSession()){
                repository.setSession(session);
                session.beginTransaction();
                List<Deliver>delivers=repository.findAll();
                session.getTransaction().commit();
                if(delivers!=null){
                    List<DeliverDTO> allDto=new ArrayList<>();
                    for (Deliver o : delivers) {
                        DeliverDTO dto=new DeliverDTO(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getDob(), o.getPassword(), o.getNic(),o.getStatus());
                        allDto.add(dto);
                    }
                    return allDto;
                }else{
                    return null;
                }
            }
    }

    @Override
    public List<DeliverDTO> finbyNicAndPassword(String nic, String password) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
                repository.setS(session);
                session.beginTransaction();
                List<Deliver>delivers=repository.finbyNicAndPassword(nic, password);
                session.getTransaction().commit();
                if(delivers!=null){
                    List<DeliverDTO> allDto=new ArrayList<>();
                    for (Deliver o : delivers) {
                        DeliverDTO dto=new DeliverDTO(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getDob(), o.getPassword(), o.getNic(),o.getStatus());
                        allDto.add(dto);
                    }
                    return allDto;
                }else{
                    return null;
                }
            }
    }

    @Override
    public List<DeliverDTO> finbyStatus(String status) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
                repository.setS(session);
                session.beginTransaction();
                List<Deliver>delivers=repository.finbyStatus(status);
                session.getTransaction().commit();
                if(delivers!=null){
                    List<DeliverDTO> allDto=new ArrayList<>();
                    for (Deliver o : delivers) {
                        DeliverDTO dto=new DeliverDTO(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getDob(), o.getPassword(), o.getNic(),o.getStatus());
                        allDto.add(dto);
                    }
                    return allDto;
                }else{
                    return null;
                }
            }
    }

}
