/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.business.custom;

import java.util.List;
import lk.sanvin.perOrderSystem.business.SuperBO;
import lk.sanvin.perOrderSystem.dto.TPODTO;

/**
 *
 * @author LahiruPG
 */
public interface TPOBO extends SuperBO{
    public boolean add(TPODTO o)throws Exception;
    public boolean remove(TPODTO o)throws Exception;
    public boolean update(TPODTO o) throws Exception;
    public List<TPODTO> getAll()throws Exception;
    public List<TPODTO> finbyNicAndPassword(String nic,String password)throws Exception;
    public List<TPODTO> finbyStatus(String status)throws Exception;
}
