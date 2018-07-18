/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.repository.custom.impl;

import java.util.List;
import lk.sanvin.perOrderSystem.entity.Deliver;
import lk.sanvin.perOrderSystem.repository.SuperRepositoryImpl;
import lk.sanvin.perOrderSystem.repository.custom.DeliverRepository;
import org.hibernate.Session;

/**
 *
 * @author LahiruPG
 */
public class DeliverRepositoryImpl extends SuperRepositoryImpl<Deliver, String> implements DeliverRepository {
 private Session s;
    public DeliverRepositoryImpl() {
    }

    @Override
    public void setS(Session s) {
         this.s = s;
    }

    @Override
    public List<Deliver> finbyNicAndPassword(String nic, String password) {
        String query = "from Deliver o where o.nic='"+ nic +"' and o.password='"+ password +"'";
        List list = s.createQuery(query).list();
        return list;
    }

    @Override
    public List<Deliver> finbyStatus(String status) {
        String query = "from Deliver o where o.status='"+status+"'";
        List list = s.createQuery(query).list();
        return list;
    }


}
