/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.startup;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.sanvin.perOrderSystem.service.impl.ServiceFactoryImpl;

/**
 *
 * @author LahiruPG
 */
public class StartServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Registry registry=LocateRegistry.createRegistry(5050);
            registry.rebind("pos", ServiceFactoryImpl.getInstance());
            System.out.println("Server Started");
        } catch (RemoteException ex) {
            Logger.getLogger(StartServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
    }
    
}
