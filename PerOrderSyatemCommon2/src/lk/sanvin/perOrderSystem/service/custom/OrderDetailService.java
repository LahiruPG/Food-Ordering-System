/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.service.custom;

import java.util.List;
import lk.sanvin.perOrderSystem.dto.OrderDTO;
import lk.sanvin.perOrderSystem.dto.OrderDetailDTO;
import lk.sanvin.perOrderSystem.service.SuperService;

/**
 *
 * @author LahiruPG
 */
public interface OrderDetailService extends SuperService{
    public boolean add(OrderDetailDTO o)throws Exception;
    public boolean remove(OrderDetailDTO o)throws Exception;
    public boolean update(OrderDetailDTO o) throws Exception;
    public List<OrderDetailDTO> getAll()throws Exception;
    public List<OrderDetailDTO> findByOid(int oid)throws Exception;
    public List<OrderDetailDTO> finbyid(int oid)throws Exception;
}
