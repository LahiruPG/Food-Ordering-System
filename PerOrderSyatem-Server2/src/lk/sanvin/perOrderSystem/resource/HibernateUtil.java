/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.resource;

import java.io.File;
import lk.sanvin.perOrderSystem.entity.Admin;
import lk.sanvin.perOrderSystem.entity.Cashier;
import lk.sanvin.perOrderSystem.entity.Cook;
import lk.sanvin.perOrderSystem.entity.Deliver;
import lk.sanvin.perOrderSystem.entity.Item;
import lk.sanvin.perOrderSystem.entity.OrderDetail;
import lk.sanvin.perOrderSystem.entity.Orders;
import lk.sanvin.perOrderSystem.entity.TPO;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author LahiruPG
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    private static StandardServiceRegistry registry;

    static {
        try {

            File hibernateProperties = new File("settings/hibernate.properties");
            System.out.println(hibernateProperties);
            registry = new StandardServiceRegistryBuilder().loadProperties(hibernateProperties).build();

            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(TPO.class)
                    .addAnnotatedClass(Cook.class)
                    .addAnnotatedClass(Item.class)
                    .addAnnotatedClass(Orders.class)
                    .addAnnotatedClass(OrderDetail.class)
                    .addAnnotatedClass(Cashier.class)
                    .addAnnotatedClass(Admin.class)
                    .addAnnotatedClass(Deliver.class)
                    .buildMetadata().buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);

            StandardServiceRegistryBuilder.destroy(registry);

            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
