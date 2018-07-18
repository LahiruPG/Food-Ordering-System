/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.business.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.sanvin.perOrderSystem.business.custom.CashierBO;
import lk.sanvin.perOrderSystem.dto.CashierDTO;
import lk.sanvin.perOrderSystem.entity.Cashier;
import lk.sanvin.perOrderSystem.repository.RepositoryFactory;
import lk.sanvin.perOrderSystem.repository.custom.CashierRepository;
import lk.sanvin.perOrderSystem.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author LahiruPG
 */
public class CashierBOImpl implements CashierBO {

    private CashierRepository repository;

    public CashierBOImpl() {
        this.repository = (CashierRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.CASHIER);
    }

    @Override
    public boolean add(CashierDTO o) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            repository.setSession(session);
            session.beginTransaction();
            Cashier cashier = new Cashier(o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getPassword(), o.getDob(), o.getNic());
            boolean result = repository.save(cashier);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public boolean remove(CashierDTO o) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            repository.setSession(session);

            session.beginTransaction();

            Cashier cashier = new Cashier(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getPassword(), o.getDob(), o.getNic(), o.getStatus());

            boolean result = false;

            if (cashier != null) {

                repository.delete(cashier);
            }

            session.getTransaction().commit();

            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean update(CashierDTO o) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            repository.setSession(session);

            session.beginTransaction();

            Cashier cashier = new Cashier(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getPassword(), o.getDob(), o.getNic(), o.getStatus());
            repository.update(cashier);

            session.getTransaction().commit();

            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }

    }

    @Override
    public List<CashierDTO> getAll() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            repository.setSession(session);
            session.beginTransaction();
            List<Cashier> tpos = repository.findAll();
            session.getTransaction().commit();
            if (tpos != null) {
                List<CashierDTO> allDto = new ArrayList<>();
                for (Cashier o : tpos) {
                    CashierDTO dto = new CashierDTO(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getDob(), o.getPassword(), o.getNic(), o.getStatus());
                    allDto.add(dto);
                }
                return allDto;
            } else {
                return null;
            }
        }
    }

    @Override
    public List<CashierDTO> finbyNicAndPassword(String nic, String password) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            repository.setS(session);
            session.beginTransaction();
            List<Cashier> tpos = repository.finbyNicAndPassword(nic, password);
            session.getTransaction().commit();
            if (tpos != null) {
                List<CashierDTO> allDto = new ArrayList<>();
                for (Cashier o : tpos) {
                    CashierDTO dto = new CashierDTO(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getDob(), o.getPassword(), o.getNic(), o.getStatus());
                    allDto.add(dto);
                }
                return allDto;
            } else {
                return null;
            }
        }
    }

    @Override
    public List<CashierDTO> finbyStatus(String status) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            repository.setS(session);
            session.beginTransaction();
            List<Cashier> tpos = repository.finbyStatus(status);
            session.getTransaction().commit();
            if (tpos != null) {
                List<CashierDTO> allDto = new ArrayList<>();
                for (Cashier o : tpos) {
                    CashierDTO dto = new CashierDTO(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getDob(), o.getPassword(), o.getNic(), o.getStatus());
                    allDto.add(dto);
                }
                return allDto;
            } else {
                return null;
            }
        }
    }

}
