/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.service.custom;

import java.util.List;
import lk.sanvin.perOrderSystem.dto.DeliverDTO;
import lk.sanvin.perOrderSystem.service.SuperService;

/**
 *
 * @author LahiruPG
 */
public interface DeliverService extends SuperService{
    public boolean add(DeliverDTO o)throws Exception;
    public boolean remove(DeliverDTO o)throws Exception;
    public boolean update(DeliverDTO o) throws Exception;
    public List<DeliverDTO> getAll()throws Exception;
    public List<DeliverDTO> finbyNicAndPassword(String nic,String password)throws Exception;
    public List<DeliverDTO> finbyStatus(String status)throws Exception;
}
