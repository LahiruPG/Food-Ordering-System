/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.repository.custom.impl;

import java.util.List;
import lk.sanvin.perOrderSystem.entity.Admin;
import lk.sanvin.perOrderSystem.repository.SuperRepositoryImpl;
import lk.sanvin.perOrderSystem.repository.custom.AdminRepository;
import org.hibernate.Session;

/**
 *
 * @author LahiruPG
 */
public class AdminRepositoryImpl extends SuperRepositoryImpl<Admin, String> implements AdminRepository {
 private Session s;
    public AdminRepositoryImpl() {
    }

    @Override
    public void setS(Session s) {
         this.s = s;
    }

    @Override
    public List<Admin> finbyNicAndPassword(String nic, String password) {
        String query = "from Admin o where o.nic='"+ nic +"' and o.password='"+ password +"'";
        List list = s.createQuery(query).list();
        return list;
    }

    @Override
    public List<Admin> finbyStatus(String status) {
        String query = "from Admin o where o.status='"+status+"'";
        List list = s.createQuery(query).list();
        return list;
    }


}
