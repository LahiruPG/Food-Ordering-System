/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.business.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.sanvin.perOrderSystem.business.custom.OrderDetailBO;
import lk.sanvin.perOrderSystem.dto.OrderDTO;
import lk.sanvin.perOrderSystem.dto.OrderDetailDTO;
import lk.sanvin.perOrderSystem.entity.Admin;
import lk.sanvin.perOrderSystem.entity.OrderDetail;
import lk.sanvin.perOrderSystem.entity.Orders;
import lk.sanvin.perOrderSystem.repository.RepositoryFactory;
import lk.sanvin.perOrderSystem.repository.custom.OrderDetailRepository;
import lk.sanvin.perOrderSystem.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author LahiruPG
 */
public class OrderDetailBOImpl implements OrderDetailBO {

    private OrderDetailRepository orderDetailRepository;

    public OrderDetailBOImpl() {
        this.orderDetailRepository =  (OrderDetailRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ORDER_DETAIL);

    }

    @Override
    public boolean add(OrderDetailDTO od) throws Exception {
        OrderDTO o = od.getOrderDTO();
        Orders orders = new Orders(o.getOid(), o.getDate(), o.getName(), o.getContact(), o.getAddress(), o.getDeliveryDateTime(), o.getStatus(), o.getTop(), o.getCook(),o.getCashier(),o.getDeliver());
         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            orderDetailRepository.setSession(session);
            session.beginTransaction();
            OrderDetail orderDetail = new OrderDetail(od.getQty(), od.getUnitPrice(),orders, od.getDiscription(), od.getAmount());
            boolean result = false;
            if (orderDetail != null) {
                orderDetailRepository.save(orderDetail);
            }
            session.getTransaction().commit();
            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(OrderDetailDTO od) throws Exception {
        //OrderDTO o = od.getOrderDTO();
      //  Orders orders = new Orders(o.getOid(), o.getDate(), o.getName(), o.getContact(), o.getAddress(), o.getDeliveryDateTime(), o.getStatus(), o.getTop(), o.getCook(),o.getCashier(),o.getDeliver());
         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            orderDetailRepository.setS(session);
            session.beginTransaction();
            OrderDetail orderDetail = new OrderDetail(od.getId(), od.getQty(), od.getUnitPrice(),null, od.getDiscription(), od.getAmount());
            boolean result = false;
            if (orderDetail != null) {
                orderDetailRepository.deleteOd(orderDetail.getId());
            }
            session.getTransaction().commit();
            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(OrderDetailDTO od) throws Exception {
         OrderDTO o = od.getOrderDTO();
        Orders orders = new Orders(o.getOid(), o.getDate(), o.getName(), o.getContact(), o.getAddress(), o.getDeliveryDateTime(), o.getStatus(), o.getTop(), o.getCook(),o.getCashier(),o.getDeliver());
         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            orderDetailRepository.setSession(session);
            session.beginTransaction();
            OrderDetail orderDetail = new OrderDetail(od.getId(), od.getQty(), od.getUnitPrice(),null, od.getDiscription(), od.getAmount());
            boolean result = false;
            if (orderDetail != null) {
                orderDetailRepository.update(orderDetail);
            }
            session.getTransaction().commit();
            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public List<OrderDetailDTO> getAll() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            orderDetailRepository.setSession(session);
            session.beginTransaction();
            List<OrderDetail> odList = orderDetailRepository.findAll();
            session.getTransaction().commit();

            if (odList != null) {
                List<OrderDetailDTO> dtoList = new ArrayList<>();
                for (OrderDetail od : odList) {
                    OrderDetailDTO dto = new OrderDetailDTO();
                    dto.setId(od.getId());
                    dto.setDiscription(od.getDiscription());
                    dto.setQty(od.getQty());
                    dto.setUnitPrice(od.getUnitPrice());
                    dto.setAmount(od.getAmount());
                    dto.setOrderDTO(new OrderDTO(od.getId()));
                    dtoList.add(dto);
                }
                return dtoList;
            } else {
                System.out.println("OrderDetailBOImpl.getAll return null");
                return null;
            }
        }
    }

    @Override
    public List<OrderDetailDTO> finbyOid(int oid) throws Exception{
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            orderDetailRepository.setS(session);
            session.beginTransaction();
            List<OrderDetail> odList = orderDetailRepository.finbyOid(oid);
            session.getTransaction().commit();

            if (odList != null) {
                List<OrderDetailDTO> dtoList = new ArrayList<>();
                for (OrderDetail od : odList) {
                    OrderDetailDTO dto = new OrderDetailDTO();
                    dto.setId(od.getId());
                    dto.setDiscription(od.getDiscription());
                    dto.setQty(od.getQty());
                    dto.setUnitPrice(od.getUnitPrice());
                    dto.setAmount(od.getAmount());
                   // dto.setOrderDTO(new OrderDTO(od.getId()));
                   
                    dto.setOrderDTO(new OrderDTO(od.getOrder().getOid()));
                    dtoList.add(dto);
                }
                return dtoList;
            } else {
                System.out.println("OrderDetailBOImpl.getAll return null");
                return null;
            }
        }
    }

    @Override
    public List<OrderDetailDTO> finbyid(int oid) throws Exception{
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            orderDetailRepository.setS(session);
            session.beginTransaction();
            List<OrderDetail> odList = orderDetailRepository.finbyid(oid);
            session.getTransaction().commit();

            if (odList != null) {
                List<OrderDetailDTO> dtoList = new ArrayList<>();
                for (OrderDetail od : odList) {
                    OrderDetailDTO dto = new OrderDetailDTO();
                    dto.setId(od.getId());
                    dto.setDiscription(od.getDiscription());
                    dto.setQty(od.getQty());
                    dto.setUnitPrice(od.getUnitPrice());
                    dto.setAmount(od.getAmount());
                    dto.setOrderDTO(new OrderDTO(od.getId()));
                    dtoList.add(dto);
                }
                return dtoList;
            } else {
                System.out.println("OrderDetailBOImpl.getAll return null");
                return null;
            }
        }
    }

}
