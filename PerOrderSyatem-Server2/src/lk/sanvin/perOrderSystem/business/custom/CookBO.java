/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.business.custom;

import java.util.List;
import lk.sanvin.perOrderSystem.business.SuperBO;
import lk.sanvin.perOrderSystem.dto.CookDTO;
import lk.sanvin.perOrderSystem.dto.TPODTO;

/**
 *
 * @author LahiruPG
 */
public interface CookBO extends SuperBO{
    public boolean add(CookDTO o)throws Exception;
    public boolean remove(CookDTO o)throws Exception;
    public boolean update(CookDTO o) throws Exception;
    public List<CookDTO> getAll()throws Exception;
    public List<CookDTO> finbyNicAndPassword(String nic,String password)throws Exception;
    public List<CookDTO> finbyStatus(String status)throws Exception;
}
