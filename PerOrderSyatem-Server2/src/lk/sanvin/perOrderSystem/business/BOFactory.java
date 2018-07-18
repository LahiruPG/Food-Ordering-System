/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.business;

import lk.sanvin.perOrderSystem.business.custom.impl.AdminBOImpl;
import lk.sanvin.perOrderSystem.business.custom.impl.CashierBOImpl;
import lk.sanvin.perOrderSystem.business.custom.impl.CookBOImpl;
import lk.sanvin.perOrderSystem.business.custom.impl.DeliverBOImpl;
import lk.sanvin.perOrderSystem.business.custom.impl.ItemBOImpl;
import lk.sanvin.perOrderSystem.business.custom.impl.OrderBOImpl;
import lk.sanvin.perOrderSystem.business.custom.impl.OrderDetailBOImpl;
import lk.sanvin.perOrderSystem.business.custom.impl.TPOBOImpl;

/**
 *
 * @author LahiruPG
 */
public class BOFactory {

    public enum BOTypes {
        TPO, COOK, ITEM, ORDER, ORDER_DETAIL, ADMIN, CASHIER, DELIVER
    }
    public static BOFactory bOFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        if (bOFactory == null) {
            bOFactory = new BOFactory();
        }
        return bOFactory;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case TPO:
                return new TPOBOImpl();
            case COOK:
                return new CookBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDER_DETAIL:
                return new OrderDetailBOImpl();
            case ADMIN:
                return new AdminBOImpl();
            case CASHIER:
                return new CashierBOImpl();
            case DELIVER:
                return new DeliverBOImpl();
            default:
                return null;
        }
    }
}
