/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.business.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.sanvin.perOrderSystem.business.custom.AdminBO;
import lk.sanvin.perOrderSystem.dto.AdminDTO;
import lk.sanvin.perOrderSystem.entity.Admin;
import lk.sanvin.perOrderSystem.repository.RepositoryFactory;
import lk.sanvin.perOrderSystem.repository.custom.AdminRepository;
import lk.sanvin.perOrderSystem.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author LahiruPG
 */
public class AdminBOImpl implements AdminBO {

    private AdminRepository repository;

    public AdminBOImpl() {
        this.repository = (AdminRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ADMIN);
    }

    @Override
    public boolean add(AdminDTO o) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            repository.setSession(session);
            session.beginTransaction();
            Admin admin = new Admin(o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getPassword(), o.getDob(), o.getNic());
            boolean result = repository.save(admin);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public boolean remove(AdminDTO o) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            repository.setSession(session);
            session.beginTransaction();
            Admin admin = new Admin(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getPassword(), o.getDob(), o.getNic(), o.getStatus());
            boolean result = false;
            if (admin != null) {
                repository.delete(admin);
            }
            session.getTransaction().commit();
            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean update(AdminDTO o) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            repository.setSession(session);

            session.beginTransaction();

            Admin admin = new Admin(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getPassword(), o.getDob(), o.getNic(), o.getStatus());
            repository.update(admin);

            session.getTransaction().commit();

            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }

    }

    @Override
    public List<AdminDTO> getAll() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            repository.setSession(session);
            session.beginTransaction();
            List<Admin> admins = repository.findAll();
            session.getTransaction().commit();
            if (admins != null) {
                List<AdminDTO> allDto = new ArrayList<>();
                for (Admin o : admins) {
                    AdminDTO dto = new AdminDTO(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getDob(), o.getPassword(), o.getNic(), o.getStatus());
                    allDto.add(dto);
                }
                return allDto;
            } else {
                return null;
            }
        }
    }

    @Override
    public List<AdminDTO> finbyNicAndPassword(String nic, String password) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            repository.setS(session);
            session.beginTransaction();
            List<Admin> admins = repository.finbyNicAndPassword(nic, password);
            session.getTransaction().commit();
            if (admins != null) {
                List<AdminDTO> allDto = new ArrayList<>();
                for (Admin o : admins) {
                    AdminDTO dto = new AdminDTO(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getDob(), o.getPassword(), o.getNic(), o.getStatus());
                    allDto.add(dto);
                }
                return allDto;
            } else {
                return null;
            }
        }
    }

    @Override
    public List<AdminDTO> finbyStatus(String status) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            repository.setS(session);
            session.beginTransaction();
            List<Admin> admins = repository.finbyStatus(status);
            session.getTransaction().commit();
            if (admins != null) {
                List<AdminDTO> allDto = new ArrayList<>();
                for (Admin o : admins) {
                    AdminDTO dto = new AdminDTO(o.getId(), o.getName(), o.getGender(), o.getAddress(), o.getContact(), o.getDob(), o.getPassword(), o.getNic(), o.getStatus());
                    allDto.add(dto);
                }
                return allDto;
            } else {
                return null;
            }
        }
    }

}
