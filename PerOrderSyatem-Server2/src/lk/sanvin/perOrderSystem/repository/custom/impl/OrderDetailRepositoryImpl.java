/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.repository.custom.impl;

import java.util.List;
import lk.sanvin.perOrderSystem.entity.OrderDetail;
import lk.sanvin.perOrderSystem.entity.Orders;
import lk.sanvin.perOrderSystem.repository.SuperRepositoryImpl;
import lk.sanvin.perOrderSystem.repository.custom.OrderDetailRepository;
import lk.sanvin.perOrderSystem.repository.custom.OrderRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author LahiruPG
 */
public class OrderDetailRepositoryImpl extends SuperRepositoryImpl<OrderDetail, String> implements OrderDetailRepository {

    private Session s;

    public OrderDetailRepositoryImpl() {
    }

    @Override
    public void setS(Session s) {
        this.s = s;
    }

    @Override
    public List<OrderDetail> finbyOid(int oid) {
        try {
            String query = "from OrderDetail o where o.order = '" + oid + "'";
            List list = s.createQuery(query).list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OrderDetail> finbyid(int oid) {
        try {
            String query = "from OrderDetail o where o.id = '" + oid + "'";
            List list = s.createQuery(query).list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteOd(int id) {
        System.out.println(id);
        String query = "delete OrderDetail where id = '" + id + "'";
        Query createQuery = s.createQuery(query); 
        System.out.println("od delete query is run");
        System.out.println(createQuery);
        createQuery.executeUpdate();
    }

}
