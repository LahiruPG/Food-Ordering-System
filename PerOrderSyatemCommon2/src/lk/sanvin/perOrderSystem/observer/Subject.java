/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.observer;

import lk.sanvin.perOrderSystem.service.SuperService;


/**
 *
 * @author LahiruPG
 */
public interface Subject extends SuperService {

    public void regesterObserver(Observer o) throws Exception;

    public void unregesterObserver(Observer o) throws Exception;

    public void notfyObserver() throws Exception;
}
