/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.business.custom;

import java.util.List;
import lk.sanvin.perOrderSystem.business.SuperBO;
import lk.sanvin.perOrderSystem.dto.CashierDTO;

/**
 *
 * @author LahiruPG
 */
public interface CashierBO extends SuperBO{
    public boolean add(CashierDTO o)throws Exception;
    public boolean remove(CashierDTO o)throws Exception;
    public boolean update(CashierDTO o) throws Exception;
    public List<CashierDTO> getAll()throws Exception;
    public List<CashierDTO> finbyNicAndPassword(String nic,String password)throws Exception;
    public List<CashierDTO> finbyStatus(String status)throws Exception;
}
