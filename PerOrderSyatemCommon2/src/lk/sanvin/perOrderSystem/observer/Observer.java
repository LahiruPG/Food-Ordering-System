/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.observer;

import java.rmi.Remote;

/**
 *
 * @author LahiruPG
 */
public interface Observer extends Remote{
    public void updateObserver() throws Exception;
}
