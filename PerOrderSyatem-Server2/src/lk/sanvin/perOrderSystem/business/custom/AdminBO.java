/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.business.custom;

import java.util.List;
import lk.sanvin.perOrderSystem.business.SuperBO;
import lk.sanvin.perOrderSystem.dto.AdminDTO;

/**
 *
 * @author LahiruPG
 */
public interface AdminBO extends SuperBO{
    public boolean add(AdminDTO o)throws Exception;
    public boolean remove(AdminDTO o)throws Exception;
    public boolean update(AdminDTO o) throws Exception;
    public List<AdminDTO> getAll()throws Exception;
    public List<AdminDTO> finbyNicAndPassword(String nic,String password)throws Exception;
    public List<AdminDTO> finbyStatus(String status)throws Exception;
}
