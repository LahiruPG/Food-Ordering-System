/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.business.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.sanvin.perOrderSystem.business.custom.CookBO;
import lk.sanvin.perOrderSystem.dto.CookDTO;
import lk.sanvin.perOrderSystem.entity.Cook;
import lk.sanvin.perOrderSystem.repository.RepositoryFactory;
import lk.sanvin.perOrderSystem.repository.custom.CookRepository;
import lk.sanvin.perOrderSystem.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author LahiruPG
 */
public class CookBOImpl implements CookBO {

    private CookRepository repository;

    public CookBOImpl() {
        this.repository = (CookRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.COOK);
    }

    @Override
    public boolean add(CookDTO o) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            repository.setSession(session);
            session.beginTransaction();
            Cook cook = new Cook(o.getName(), o.getGender(), o.getAddress(), o.getContact(),o.getPassword(), o.getDob(), o.getNic());
            boolean result = repository.save(cook);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public boolean remove(CookDTO o) throws Exception {
         try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            repository.setSession(session);
            
            session.beginTransaction();
            
            Cook cook=new Cook(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(),o.getPassword(), o.getDob(), o.getNic(),o.getStatus());
            
            boolean result = false;

            if (cook != null) {

                repository.delete(cook);
            }
            
            session.getTransaction().commit();

            return result;
        }catch(Exception exp){
            exp.printStackTrace();
            return false;
        }
    
    }

    @Override
    public boolean update(CookDTO o) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            repository.setSession(session);
            
            session.beginTransaction();
            
            Cook cook=new Cook(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(),o.getPassword(), o.getDob(), o.getNic(),o.getStatus());
            repository.update(cook);
            
            session.getTransaction().commit();
            
            return true;
        }catch(Exception exp){
            exp.printStackTrace();
            return false;
        }
        
    }

    @Override
    public List<CookDTO> getAll() throws Exception {
            try(Session session=HibernateUtil.getSessionFactory().openSession()){
                repository.setSession(session);
                session.beginTransaction();
                List<Cook>tpos=repository.findAll();
                session.getTransaction().commit();
                if(tpos!=null){
                    List<CookDTO> allDto=new ArrayList<>();
                    for (Cook o : tpos) {
                        CookDTO dto=new CookDTO(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getDob(), o.getPassword(), o.getNic(),o.getStatus());
                        allDto.add(dto);
                    }
                    return allDto;
                }else{
                    return null;
                }
            }
    }

    @Override
    public List<CookDTO> finbyNicAndPassword(String nic, String password) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
                repository.setS(session);
                session.beginTransaction();
                List<Cook>tpos=repository.finbyNicAndPassword(nic, password);
                session.getTransaction().commit();
                if(tpos!=null){
                    List<CookDTO> allDto=new ArrayList<>();
                    for (Cook o : tpos) {
                        CookDTO dto=new CookDTO(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getDob(), o.getPassword(), o.getNic(),o.getStatus());
                        allDto.add(dto);
                    }
                    return allDto;
                }else{
                    return null;
                }
            }
    }

    @Override
    public List<CookDTO> finbyStatus(String status) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
                repository.setS(session);
                session.beginTransaction();
                List<Cook>tpos=repository.finbyStatus(status);
                session.getTransaction().commit();
                if(tpos!=null){
                    List<CookDTO> allDto=new ArrayList<>();
                    for (Cook o : tpos) {
                        CookDTO dto=new CookDTO(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getDob(), o.getPassword(), o.getNic(),o.getStatus());
                        allDto.add(dto);
                    }
                    return allDto;
                }else{
                    return null;
                }
            }
    }

}
