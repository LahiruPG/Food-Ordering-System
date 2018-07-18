/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.repository.custom.impl;

import java.util.List;
import lk.sanvin.perOrderSystem.entity.Orders;
import lk.sanvin.perOrderSystem.repository.SuperRepositoryImpl;
import lk.sanvin.perOrderSystem.repository.custom.OrderRepository;
import org.hibernate.Session;

/**
 *
 * @author LahiruPG
 */
public class OrderRepositoryImpl extends SuperRepositoryImpl<Orders, String> implements OrderRepository {

    private Session s;

    public OrderRepositoryImpl() {
    }

    @Override
    public void setS(Session s) {
        this.s = s;
    }

    @Override
    public List<Orders> finbyDate(String date) {
        try {
            String query = "from Orders o where o.deliveryDateTime like '" + date + "%'";
            List list = s.createQuery(query).list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Orders> finbyStatusAndDate(String status,String date) {
         try {
            String query = "from Orders o where o.status='"+status+"' and o.deliveryDateTime like '" + date + "%'";
            List list = s.createQuery(query).list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
