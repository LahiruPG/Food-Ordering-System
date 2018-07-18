/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.repository.custom.impl;

import java.util.List;
import lk.sanvin.perOrderSystem.entity.Cook;
import lk.sanvin.perOrderSystem.entity.TPO;
import lk.sanvin.perOrderSystem.repository.SuperRepositoryImpl;
import lk.sanvin.perOrderSystem.repository.custom.CookRepository;
import org.hibernate.Session;

/**
 *
 * @author LahiruPG
 */
public class CookRepositoryImpl extends SuperRepositoryImpl<Cook, String> implements CookRepository {
 private Session s;
    public CookRepositoryImpl() {
    }

    @Override
    public void setS(Session s) {
         this.s = s;
    }

    @Override
    public List<Cook> finbyNicAndPassword(String nic, String password) {
        String query = "from Cook o where o.nic='"+ nic +"' and o.password='"+ password +"'";
        List list = s.createQuery(query).list();
        return list;
    }

    @Override
    public List<Cook> finbyStatus(String status) {
        String query = "from Cook o where o.status='"+status+"'";
        List list = s.createQuery(query).list();
        return list;
    }


}
