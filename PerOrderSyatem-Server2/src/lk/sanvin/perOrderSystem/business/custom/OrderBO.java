/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.business.custom;

import java.util.List;
import lk.sanvin.perOrderSystem.business.SuperBO;
import lk.sanvin.perOrderSystem.dto.OrderDTO;
import lk.sanvin.perOrderSystem.entity.Orders;

/**
 *
 * @author LahiruPG
 */
public interface OrderBO extends SuperBO{
    public boolean add(OrderDTO o)throws Exception;
    public boolean remove(OrderDTO o)throws Exception;
    public boolean update(OrderDTO o) throws Exception;
    public List<OrderDTO> getAll()throws Exception;
    public List<OrderDTO> findByDate(String date)throws Exception;
    public List<OrderDTO> finbyStatusAndDate(String status,String date)throws Exception;
}
