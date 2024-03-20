/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;


/**
 *
 * @author LAPTOP 247
 */
public class User {
    private String uid;
    private String name;
    private boolean gender;
    private Date startdate;
    private String email;
    private String phone;
    private String address;

    public User() {
    }

    public User(String uid, String name, boolean gender, Date startdate, String email, String phone, String address) {
        this.uid = uid;
        this.name = name;
        this.gender = gender;
        this.startdate = startdate;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" + "uid=" + uid + ", name=" + name + ", gender=" + gender + ", startdate=" + startdate + ", email=" + email + ", phone=" + phone + ", address=" + address + '}';
    }

    
    
    
}
