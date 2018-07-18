/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.service.custom;

import java.util.List;
import lk.sanvin.perOrderSystem.dto.CashierDTO;
import lk.sanvin.perOrderSystem.service.SuperService;

/**
 *
 * @author LahiruPG
 */
public interface CashierService extends SuperService{
    public boolean add(CashierDTO o)throws Exception;
    public boolean remove(CashierDTO o)throws Exception;
    public boolean update(CashierDTO o) throws Exception;
    public List<CashierDTO> getAll()throws Exception;
    public List<CashierDTO> finbyNicAndPassword(String nic,String password)throws Exception;
    public List<CashierDTO> finbyStatus(String status)throws Exception;
}
