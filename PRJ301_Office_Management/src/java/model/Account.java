/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LAPTOP 247
 */
public class Account {
    private String aid;
    private String account;
    private String password;
    private int permission;
    private String uid;

    public Account() {
    }

    public Account(String aid, String account, String password, int permission, String uid) {
        this.aid = aid;
        this.account = account;
        this.password = password;
        this.permission = permission;
        this.uid = uid;
    }
    

    public String getAid() {
        return aid;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public int getPermission() {
        return permission;
    }

    public String getUid() {
        return uid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    
    
    
    
}
