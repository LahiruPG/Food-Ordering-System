/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.repository.custom;

import java.util.List;
import lk.sanvin.perOrderSystem.entity.Deliver;
import lk.sanvin.perOrderSystem.repository.SuperRepository;
import org.hibernate.Session;

/**
 *
 * @author LahiruPG
 */
public interface DeliverRepository extends SuperRepository<Deliver,String>{
    public void setS(Session s);
    public List<Deliver> finbyNicAndPassword(String nic,String password);
    public List<Deliver> finbyStatus(String status);
}
