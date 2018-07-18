/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.repository.custom;

import java.util.List;
import lk.sanvin.perOrderSystem.entity.Admin;
import lk.sanvin.perOrderSystem.entity.Cashier;
import lk.sanvin.perOrderSystem.entity.TPO;
import lk.sanvin.perOrderSystem.repository.SuperRepository;
import org.hibernate.Session;

/**
 *
 * @author LahiruPG
 */
public interface AdminRepository extends SuperRepository<Admin,String>{
    public void setS(Session s);
    public List<Admin> finbyNicAndPassword(String nic,String password);
    public List<Admin> finbyStatus(String status);
}
