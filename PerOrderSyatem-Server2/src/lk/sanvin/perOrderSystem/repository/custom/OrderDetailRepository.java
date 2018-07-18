/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.repository.custom;

import java.util.List;
import lk.sanvin.perOrderSystem.entity.OrderDetail;
import lk.sanvin.perOrderSystem.repository.SuperRepository;
import org.hibernate.Session;

/**
 *
 * @author LahiruPG
 */
public interface OrderDetailRepository extends SuperRepository<OrderDetail,String>{
   public void setS(Session s);
    public List<OrderDetail> finbyOid(int oid);
    public List<OrderDetail> finbyid(int oid);
    public void deleteOd(int id);
}
