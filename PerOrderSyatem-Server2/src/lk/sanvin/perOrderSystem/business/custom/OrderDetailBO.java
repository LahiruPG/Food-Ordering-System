/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.business.custom;

import java.util.List;
import lk.sanvin.perOrderSystem.business.SuperBO;
import lk.sanvin.perOrderSystem.dto.OrderDetailDTO;

/**
 *
 * @author LahiruPG
 */
public interface OrderDetailBO extends SuperBO{
    public boolean add(OrderDetailDTO o)throws Exception;
    public boolean remove(OrderDetailDTO o)throws Exception;
    public boolean update(OrderDetailDTO o) throws Exception;
    public List<OrderDetailDTO> getAll()throws Exception;
    public List<OrderDetailDTO> finbyOid(int oid)throws Exception;
    public List<OrderDetailDTO> finbyid(int oid)throws Exception;
}
