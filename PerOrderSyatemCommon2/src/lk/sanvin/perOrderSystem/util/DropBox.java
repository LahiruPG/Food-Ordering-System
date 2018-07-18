/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.util;

import lk.sanvin.perOrderSystem.dto.AdminDTO;
import lk.sanvin.perOrderSystem.dto.CashierDTO;
import lk.sanvin.perOrderSystem.dto.CookDTO;
import lk.sanvin.perOrderSystem.dto.DeliverDTO;
import lk.sanvin.perOrderSystem.dto.TPODTO;

/**
 *
 * @author LahiruPG
 */
public class DropBox {

    private TPODTO tpodto;
    private CookDTO cookdto;
    private DeliverDTO deliverdto;
    private CashierDTO cashierdto;
    private AdminDTO admindto;
    public static DropBox dropBox;
    

    private DropBox() {
    }

    public static DropBox getInstance() {
        if (dropBox == null) {
            dropBox = new DropBox();
        }
        return dropBox;
    }

    /**
     * @return the tpodto
     */
    public TPODTO getTpodto() {
        return tpodto;
    }

    /**
     * @param tpodto the tpodto to set
     */
    public void setTpodto(TPODTO tpodto) {
        this.tpodto = tpodto;
    }

    /**
     * @return the cookdto
     */
    public CookDTO getCookdto() {
        return cookdto;
    }

    /**
     * @param cookdto the cookdto to set
     */
    public void setCookdto(CookDTO cookdto) {
        this.cookdto = cookdto;
    }

    /**
     * @return the deliverdto
     */
    public DeliverDTO getDeliverdto() {
        return deliverdto;
    }

    /**
     * @param deliverdto the deliverdto to set
     */
    public void setDeliverdto(DeliverDTO deliverdto) {
        this.deliverdto = deliverdto;
    }

    /**
     * @return the cashierdto
     */
    public CashierDTO getCashierdto() {
        return cashierdto;
    }

    /**
     * @param cashierdto the cashierdto to set
     */
    public void setCashierdto(CashierDTO cashierdto) {
        this.cashierdto = cashierdto;
    }

    /**
     * @return the admindto
     */
    public AdminDTO getAdmindto() {
        return admindto;
    }

    /**
     * @param admindto the admindto to set
     */
    public void setAdmindto(AdminDTO admindto) {
        this.admindto = admindto;
    }
    
}
