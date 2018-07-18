/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.util;

import lk.sanvin.perOrderSystem.observer.Observer;
import lk.sanvin.perOrderSystem.observer.Subject;

/**
 *
 * @author LahiruPG
 */
public class ObserverCollector {
    private Subject cookSubject;
    private Observer cookObject;
    
    private Subject tpoSubject;
    private Observer tpoObject;
    
    private Subject cashierSubject;
    private Observer cashierObject;
    
    private Subject orderSubject;
    private Observer orderObject;
    
    public static ObserverCollector collector;

    private ObserverCollector() {
    }
    public static ObserverCollector getInstance(){
        if(collector==null){
            collector=new ObserverCollector();
        }
        return collector;
    }

    public void setCookSubject(Subject subject,Observer object){
        this.cookSubject=subject;
        this.cookObject=object;
        System.out.println("set Cook Oberver");
    }
    public void setTpoSubject(Subject subject,Observer object){
        this.tpoSubject=subject;
        this.tpoObject=object;
        System.out.println("set TPO Oberver");
    }
    public void setCashierSubject(Subject subject,Observer object){
        this.cashierSubject=subject;
        this.cashierObject=object;
        System.out.println("set Cashier Oberver");
    }
    public void setOrderSubject(Subject subject,Observer object){
        this.orderSubject=subject;
        this.orderObject=object;
        System.out.println("set Order Oberver");
    }
    public void unregesterAllObservers(){
        try {
            cookSubject.unregesterObserver((Observer) cookObject);
            tpoSubject.unregesterObserver((Observer) tpoObject);
            cashierSubject.unregesterObserver((Observer) cashierObject);
            orderSubject.unregesterObserver((Observer) orderObject);
        } catch (Exception ex) {
            System.out.println("Unregester Observers");
        }
    }
    
}
