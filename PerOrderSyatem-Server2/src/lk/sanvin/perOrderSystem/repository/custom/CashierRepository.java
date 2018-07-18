/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.repository.custom;

import java.util.List;
import lk.sanvin.perOrderSystem.entity.Cashier;
import lk.sanvin.perOrderSystem.entity.TPO;
import lk.sanvin.perOrderSystem.repository.SuperRepository;
import org.hibernate.Session;

/**
 *
 * @author LahiruPG
 */
public interface CashierRepository extends SuperRepository<Cashier,String>{
    public void setS(Session s);
    public List<Cashier> finbyNicAndPassword(String nic,String password);
    public List<Cashier> finbyStatus(String status);
}
