/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.business.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.sanvin.perOrderSystem.business.custom.OrderBO;
import lk.sanvin.perOrderSystem.dto.OrderDTO;
import lk.sanvin.perOrderSystem.dto.OrderDetailDTO;
import lk.sanvin.perOrderSystem.entity.OrderDetail;
import lk.sanvin.perOrderSystem.entity.OrderDetail_PK;
import lk.sanvin.perOrderSystem.entity.Orders;
import lk.sanvin.perOrderSystem.repository.RepositoryFactory;
import lk.sanvin.perOrderSystem.repository.custom.OrderDetailRepository;
import lk.sanvin.perOrderSystem.repository.custom.OrderRepository;
import lk.sanvin.perOrderSystem.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author LahiruPG
 */
public class OrderBOImpl implements OrderBO {

    private OrderRepository OrderRepository;
    private OrderDetailRepository orderDetailRepository;

    public OrderBOImpl() {
        this.OrderRepository = (OrderRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ORDER);
        this.orderDetailRepository = (OrderDetailRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ORDER_DETAIL);
    }

    @Override
    public boolean add(OrderDTO o) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            OrderRepository.setSession(session);
            session.beginTransaction();
            Orders orders = new Orders();
            orders.setName(o.getName());
            orders.setContact(o.getContact());
            orders.setAddress(o.getAddress());
            orders.setDate(o.getDate());
            orders.setDeliveryDateTime(o.getDeliveryDateTime());
            orders.setStatus("Pending");
            orders.setTpo(o.getTop());
            OrderRepository.save(orders);

            List<OrderDetailDTO> odList = o.getOrderDetails();
            for (OrderDetailDTO od : odList) {
                OrderDetail detail = new OrderDetail();
                detail.setDiscription(od.getDiscription());
                detail.setQty(od.getQty());
                detail.setUnitPrice(od.getUnitPrice());
                detail.setAmount(od.getAmount());
                detail.setOrder(orders);
               detail.setOrderDetail_PK(new OrderDetail_PK(orders.getOid()));
                orderDetailRepository.setSession(session);
                orderDetailRepository.save(detail);

            }
            session.getTransaction().commit();
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(OrderDTO o) throws Exception {
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            OrderRepository.setSession(session);
            
            session.beginTransaction();
            Orders orders = new Orders(o.getOid(), o.getDate(), o.getName(), o.getContact(), o.getAddress(), o.getDeliveryDateTime(), o.getStatus(), o.getTop(), o.getCook(),o.getCashier(),o.getDeliver());
            OrderRepository.delete(orders);
            
            session.getTransaction().commit();
            
            return true;
        }catch(Exception exp){
            exp.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean update(OrderDTO o) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            OrderRepository.setSession(session);
            
            session.beginTransaction();
            Orders orders = new Orders(o.getOid(), o.getDate(), o.getName(), o.getContact(), o.getAddress(), o.getDeliveryDateTime(), o.getStatus(), o.getTop(), o.getCook(),o.getCashier(), o.getDeliver());
            OrderRepository.update(orders);
            
            session.getTransaction().commit();
            
            return true;
        }catch(Exception exp){
            exp.printStackTrace();
            return false;
        }
        
    }

    @Override
    public List<OrderDTO> getAll() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            OrderRepository.setSession(session);
            session.beginTransaction();
            List<Orders> orderList = OrderRepository.findAll();
            session.getTransaction().commit();
            if (orderList != null) {
                List<OrderDTO> oDto = new ArrayList<>();
                for (Orders o : orderList) {
                    OrderDTO orders = new OrderDTO();
                    orders.setOid(o.getOid());
                    orders.setDate(o.getDate());
                    orders.setName(o.getName());
                    orders.setContact(o.getContact());
                    orders.setAddress(o.getAddress());
                    orders.setDeliveryDateTime(o.getDeliveryDateTime());
                    orders.setStatus(o.getStatus());
                    orders.setTop(o.getTpo());
                    orders.setCook(o.getCook());
                    orders.setCashier(o.getCashier());
                    orders.setDeliver(o.getDeliver());

                    oDto.add(orders);
                }
                return oDto;
            } else {
                return null;
            }
        }
    }

    @Override
    public List<OrderDTO> findByDate(String date) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            OrderRepository.setS(session);
            session.beginTransaction();
            List<Orders> orderList = OrderRepository.finbyDate(date);
            session.getTransaction().commit();
            if (orderList != null) {
                List<OrderDTO> oDto = new ArrayList<>();
                for (Orders o : orderList) {
                    OrderDTO orders = new OrderDTO();
                    orders.setOid(o.getOid());
                    orders.setDate(o.getDate());
                    orders.setName(o.getName());
                    orders.setContact(o.getContact());
                    orders.setAddress(o.getAddress());
                    orders.setDeliveryDateTime(o.getDeliveryDateTime());
                    orders.setStatus(o.getStatus());
                    orders.setTop(o.getTpo());
                    orders.setCashier(o.getCashier());
                    orders.setCook(o.getCook());
                    orders.setDeliver(o.getDeliver());

                    oDto.add(orders);
                }
                return oDto;
            } else {
                return null;
            }
        }
    }

    @Override
    public List<OrderDTO> finbyStatusAndDate(String status, String date) throws Exception{
      //  System.out.println(status+" "+date);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        OrderRepository.setS(session);
        session.beginTransaction();
        List<Orders> orderList = OrderRepository.finbyStatusAndDate(status, date);
        session.getTransaction().commit();
        if (orderList != null) {
        List<OrderDTO> oDto = new ArrayList<>();
        for (Orders o : orderList) {
        OrderDTO orders = new OrderDTO();
        orders.setOid(o.getOid());
        orders.setDate(o.getDate());
        orders.setName(o.getName());
        orders.setContact(o.getContact());
        orders.setAddress(o.getAddress());
        orders.setDeliveryDateTime(o.getDeliveryDateTime());
        orders.setStatus(o.getStatus());
        orders.setTop(o.getTpo());
        orders.setCashier(o.getCashier());
        orders.setCook(o.getCook());
        orders.setDeliver(o.getDeliver());
        
        oDto.add(orders);
        }
        return oDto;
        } else {
        return null;
        }
        }
       // return null;
    }

   

}
