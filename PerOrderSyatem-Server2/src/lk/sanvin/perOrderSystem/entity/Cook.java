/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author LahiruPG
 */
@Entity
public class Cook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String gender;
    private String address;
    private String contact;
    private String password;
    private String dob;
    private String nic;
    private String status;

    public Cook() {
    }

    public Cook(int id, String name, String gender, String address, String contact, String password, String dob, String nic,String status) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.contact = contact;
        this.password = password;
        this.dob = dob;
        this.nic = nic;
        this.status=status;
    }
    public Cook(String name, String gender, String address, String contact, String password, String dob, String nic) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.contact = contact;
        this.password = password;
        this.dob = dob;
        this.nic = nic;
        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Cook{" + "id=" + id + ", name=" + name + ", gender=" + gender + ", address=" + address + ", contact=" + contact + ", password=" + password + ", dob=" + dob + ", nic=" + nic + ", status=" + status + '}';
    }

    /**
     * @return the id
     */
   
}
