/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.repository.custom.impl;

import java.util.List;
import lk.sanvin.perOrderSystem.entity.Cashier;
import lk.sanvin.perOrderSystem.repository.SuperRepositoryImpl;
import org.hibernate.Session;
import lk.sanvin.perOrderSystem.repository.custom.CashierRepository;

/**
 *
 * @author LahiruPG
 */
public class CashierRepositoryImpl extends SuperRepositoryImpl<Cashier, String> implements CashierRepository {
 private Session s;
    public CashierRepositoryImpl() {
    }

    @Override
    public void setS(Session s) {
         this.s = s;
    }

    @Override
    public List<Cashier> finbyNicAndPassword(String nic, String password) {
        String query = "from Cashier o where o.nic='"+ nic +"' and o.password='"+ password +"'";
        List list = s.createQuery(query).list();
        return list;
    }

    @Override
    public List<Cashier> finbyStatus(String status) {
        String query = "from Cashier o where o.status='"+status+"'";
        List list = s.createQuery(query).list();
        return list;
    }


}
