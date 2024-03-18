/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;
import Resource.ResourceURL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author LAPTOP 247
 */
public class UserDBContext {
     ResourceURL rs= new ResourceURL();
    
    

    public Connection UserDBContext() {        
        Connection uconnection = null;
        try {
            String user = "hoangmnhe176788";
            String pass = "176788";
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=PRJ301_Project_SP24_HE176788";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            uconnection = DriverManager.getConnection(url, user, pass);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uconnection;
    }            
    
    public ArrayList<User> getUserList() throws SQLException{
        UserDBContext udb= new UserDBContext();
        ArrayList<User> userlist= new ArrayList<User>();
        String wquery = "SELECT * FROM [User]";
        Connection uconnection= UserDBContext();
        PreparedStatement sta= uconnection.prepareStatement(wquery);
        ResultSet resultSet = sta.executeQuery();
        while(resultSet.next()){
            String uid = resultSet.getString(1);
            String name = resultSet.getString(2);
            boolean gender=resultSet.getBoolean(3);
            Date startdate=resultSet.getDate(4);
            String email=resultSet.getString(5);
            String phone=resultSet.getString(6);
            String address=resultSet.getString(7);
            User u= new User(uid, name, gender, startdate, email, phone, address);
            userlist.add(u);
        }
        
        return userlist;
    }
    
    public User getUserByUid(String uid) throws SQLException{
        UserDBContext udb= new UserDBContext();
        User user= new User();
        String wquery = "SELECT * FROM [User] WHERE uid LIKE ?";
        Connection uconnection= UserDBContext();
        PreparedStatement sta= uconnection.prepareStatement(wquery);
        sta.setString(1, "%" + uid + "%");
        ResultSet resultSet = sta.executeQuery();
        while(resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            boolean gender=resultSet.getBoolean(3);
            Date startdate=resultSet.getDate(4);
            String email=resultSet.getString(5);
            String phone=resultSet.getString(6);
            String address=resultSet.getString(7);
            user= new User(uid, name, gender, startdate, email, phone, address);
        }
        
        return user;
    }
    
    public ArrayList<String> getUserIdlist() throws SQLException{
        UserDBContext udb= new UserDBContext();
        ArrayList<String> uidlist= new ArrayList<>();
        String wquery = "SELECT u.uid FROM [User] u";
        Connection uconnection= UserDBContext();
        PreparedStatement sta= uconnection.prepareStatement(wquery);
        ResultSet resultSet = sta.executeQuery();
        while(resultSet.next()){
            String id = resultSet.getString(1);
            uidlist.add(id);
        }
        
        return uidlist;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDBContext udb= new UserDBContext();
        System.out.println(udb);
//        ArrayList<User> userlist=udb.getUserList();
//        for(User u: userlist){
//            System.out.println(u.toString());
//        }
//        User u= udb.getUserByUid("user2");
//        System.out.println(u.toString());
        ArrayList<String> uidlist = udb.getUserIdlist();
        for(String s: uidlist){
            System.out.println(s);
        }
    }
    
}
