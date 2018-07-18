/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.business.custom.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import lk.sanvin.perOrderSystem.business.custom.TPOBO;
import lk.sanvin.perOrderSystem.dto.TPODTO;
import lk.sanvin.perOrderSystem.entity.TPO;
import lk.sanvin.perOrderSystem.repository.RepositoryFactory;
import lk.sanvin.perOrderSystem.repository.custom.TPORepository;
import lk.sanvin.perOrderSystem.repository.custom.impl.TPORepositoryImpl;
import lk.sanvin.perOrderSystem.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author LahiruPG
 */
public class TPOBOImpl implements TPOBO {

    private TPORepository tPORepository;

    public TPOBOImpl() {
        System.out.println("TPOBO impl");
        this.tPORepository = (TPORepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.TPO);
    }

    @Override
    public boolean add(TPODTO o) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tPORepository.setSession(session);
            session.beginTransaction();
            TPO tpo = new TPO(o.getName(), o.getGender(), o.getAddress(), o.getContact(),o.getPassword(), o.getDob(), o.getNic());
            boolean result = tPORepository.save(tpo);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public boolean remove(TPODTO o) throws Exception {
         try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tPORepository.setSession(session);
            
            session.beginTransaction();
            
            TPO tpo=new TPO(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(),o.getPassword(), o.getDob(), o.getNic(),o.getStatus());
            
            boolean result = false;

            if (tpo != null) {

                tPORepository.delete(tpo);
            }
            
            session.getTransaction().commit();

            return result;
        }catch(Exception exp){
            exp.printStackTrace();
            return false;
        }
    
    }

    @Override
    public boolean update(TPODTO o) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tPORepository.setSession(session);
            
            session.beginTransaction();
            
            TPO tpo=new TPO(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(),o.getPassword(), o.getDob(), o.getNic(),o.getStatus());
            tPORepository.update(tpo);
            
            session.getTransaction().commit();
            
            return true;
        }catch(Exception exp){
            exp.printStackTrace();
            return false;
        }
        
    }

    @Override
    public List<TPODTO> getAll() throws Exception {
            try(Session session=HibernateUtil.getSessionFactory().openSession()){
                tPORepository.setSession(session);
                session.beginTransaction();
                List<TPO>tpos=tPORepository.findAll();
                session.getTransaction().commit();
                if(tpos!=null){
                    List<TPODTO> allDto=new ArrayList<>();
                    for (TPO o : tpos) {
                        TPODTO dto=new TPODTO(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getDob(), o.getPassword(), o.getNic(),o.getStatus());
                        allDto.add(dto);
                    }
                    return allDto;
                }else{
                    return null;
                }
            }
    }

    @Override
    public List<TPODTO> finbyNicAndPassword(String nic, String password) throws Exception{
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
                tPORepository.setS(session);
                session.beginTransaction();
                List<TPO>tpos=tPORepository.finbyNicAndPassword(nic, password);
                session.getTransaction().commit();
                if(tpos!=null){
                    List<TPODTO> allDto=new ArrayList<>();
                    for (TPO o : tpos) {
                        TPODTO dto=new TPODTO(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getDob(), o.getPassword(), o.getNic(),o.getStatus());
                        allDto.add(dto);
                    }
                    return allDto;
                }else{
                    return null;
                }
            }
    }

    @Override
    public List<TPODTO> finbyStatus(String status) throws Exception{
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
                tPORepository.setS(session);
                session.beginTransaction();
                List<TPO>tpos=tPORepository.finbyStatus(status);
                session.getTransaction().commit();
                if(tpos!=null){
                    List<TPODTO> allDto=new ArrayList<>();
                    for (TPO o : tpos) {
                        TPODTO dto=new TPODTO(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getDob(), o.getPassword(), o.getNic(),o.getStatus());
                        allDto.add(dto);
                    }
                    return allDto;
                }else{
                    return null;
                }
            }
    }

}
