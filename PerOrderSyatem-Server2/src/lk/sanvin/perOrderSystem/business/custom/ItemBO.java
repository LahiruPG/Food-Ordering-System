/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.business.custom;

import java.util.List;
import lk.sanvin.perOrderSystem.business.SuperBO;
import lk.sanvin.perOrderSystem.dto.CookDTO;
import lk.sanvin.perOrderSystem.dto.ItemDTO;
import lk.sanvin.perOrderSystem.dto.TPODTO;

/**
 *
 * @author LahiruPG
 */
public interface ItemBO extends SuperBO{
    public boolean add(ItemDTO o)throws Exception;
    public boolean remove(ItemDTO o)throws Exception;
    public boolean update(ItemDTO o) throws Exception;
    public List<ItemDTO> getAll()throws Exception;
}
