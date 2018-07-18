/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.repository;

import lk.sanvin.perOrderSystem.repository.custom.impl.AdminRepositoryImpl;
import lk.sanvin.perOrderSystem.repository.custom.impl.CashierRepositoryImpl;
import lk.sanvin.perOrderSystem.repository.custom.impl.CookRepositoryImpl;
import lk.sanvin.perOrderSystem.repository.custom.impl.DeliverRepositoryImpl;
import lk.sanvin.perOrderSystem.repository.custom.impl.ItemRepositoryImpl;
import lk.sanvin.perOrderSystem.repository.custom.impl.OrderDetailRepositoryImpl;
import lk.sanvin.perOrderSystem.repository.custom.impl.OrderRepositoryImpl;
import lk.sanvin.perOrderSystem.repository.custom.impl.TPORepositoryImpl;

/**
 *
 * @author LahiruPG
 */
public class RepositoryFactory {

    public enum RepositoryTypes {
        TPO, COOK,ITEM,ORDER,ORDER_DETAIL,ADMIN,CASHIER,DELIVER, Deliver
    }

    public static RepositoryFactory repositoryFactory;

    private RepositoryFactory() {
        System.out.println("Create Factory");
    }

    public static RepositoryFactory getInstance() {
        if (repositoryFactory == null) {

            repositoryFactory = new RepositoryFactory();
        }
        return repositoryFactory;
    }

    public SuperRepository getRepository(RepositoryTypes type) {
        switch (type) {
            case TPO:
                return new TPORepositoryImpl();
            case COOK:
                return new CookRepositoryImpl();
            case ITEM:
                return new ItemRepositoryImpl();
            case ORDER:
                return new OrderRepositoryImpl();
            case ORDER_DETAIL:
                return new OrderDetailRepositoryImpl();
            case ADMIN:
                return new AdminRepositoryImpl();
            case CASHIER:
                return new CashierRepositoryImpl();
            case DELIVER:
                return new DeliverRepositoryImpl();
            default:
                return null;
        }
    }
}
