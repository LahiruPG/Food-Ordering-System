/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.service.custom;

import java.util.List;
import lk.sanvin.perOrderSystem.dto.AdminDTO;
import lk.sanvin.perOrderSystem.service.SuperService;

/**
 *
 * @author LahiruPG
 */
public interface AdminService extends SuperService{
    public boolean add(AdminDTO o)throws Exception;
    public boolean remove(AdminDTO o)throws Exception;
    public boolean update(AdminDTO o) throws Exception;
    public List<AdminDTO> getAll()throws Exception;
    public List<AdminDTO> finbyNicAndPassword(String nic,String password)throws Exception;
    public List<AdminDTO> finbyStatus(String status)throws Exception;
}
