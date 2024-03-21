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
import java.sql.Date;
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
        String wquery = "SELECT u.uid,u.name,u.gender,u.startdate,u.email,u.phone,u.address,a.permission FROM [User] u join Account a on u.uid=a.uid";
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
            int permission=resultSet.getInt(8);
            User u= new User(uid, name, gender, startdate, email, phone, address, permission);
            userlist.add(u);
        }
        
        return userlist;
    }
    
    public ArrayList<User> getWokerList() throws SQLException{
        UserDBContext udb= new UserDBContext();
        ArrayList<User> workerlist= new ArrayList<User>();
        String wquery = "SELECT u.uid,u.name,u.gender,u.startdate,u.email,u.phone,u.address FROM [User] u join Account a on u.uid=a.uid where a.permission='0'";
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
            workerlist.add(u);
        }
        
        return workerlist;
    }
    
    public User getUserByUid(String uid) throws SQLException{
        UserDBContext udb= new UserDBContext();
        User user= new User();
        String wquery = "SELECT u.uid,u.name,u.gender,u.startdate,u.email,u.phone,u.address,a.permission FROM [User] u join Account a on u.uid=a.uid where u.uid like  ?";
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
            int permission=resultSet.getInt(8);
            user= new User(uid, name, gender, startdate, email, phone, address,permission);
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
    
     public boolean addUser(User user, String account, String password, int permission,String aid) {
        Connection uconnection= UserDBContext();
         try {
            // Open a connection

            // Begin a transaction

            // Insert user into User table
            String insertUserQuery = "INSERT INTO [User] (uid, name, gender, startdate, email, phone, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = uconnection.prepareStatement(insertUserQuery);
            preparedStatement.setString(1, user.getUid());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setBoolean(3, user.isGender());
            preparedStatement.setDate(4, user.getStartdate());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.executeUpdate();

            // Insert user into Account table
            String insertAccountQuery = "INSERT INTO Account (aid, account, password, permission, uid) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = uconnection.prepareStatement(insertAccountQuery);
            preparedStatement.setString(1, aid); // For simplicity, using uid as aid
            preparedStatement.setString(2, account);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, permission);
            preparedStatement.setString(5, user.getUid());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
     
    public boolean deleteUser(String uid) {
        Connection uconnection= UserDBContext();
        try {
            // Open a connection

            // Begin a transaction
            uconnection.setAutoCommit(false);

            // Delete user from Account table
            String deleteAccountQuery = "DELETE FROM Account WHERE uid = ?";
            PreparedStatement preparedStatement = uconnection.prepareStatement(deleteAccountQuery);
            preparedStatement.setString(1, uid);
            preparedStatement.executeUpdate();

            // Delete user from WorkList table
            String deleteWorkListQuery = "DELETE FROM WorkList WHERE uid = ?";
            preparedStatement = uconnection.prepareStatement(deleteWorkListQuery);
            preparedStatement.setString(1, uid);
            preparedStatement.executeUpdate();

            // Delete user from User table
            String deleteUserQuery = "DELETE FROM [User] WHERE uid = ?";
            preparedStatement = uconnection.prepareStatement(deleteUserQuery);
            preparedStatement.setString(1, uid);
            preparedStatement.executeUpdate();

            // Commit the transaction
            uconnection.commit();
            return true;
        } catch (SQLException e) {

            return false;
        }
    } 
    
     public boolean updateUser(User user) {
         Connection uconnection= UserDBContext();
        try {

            // Update user in User table
            String updateUserQuery = "UPDATE [User] SET name = ?, gender = ?, startdate = ?, email = ?, phone = ?, address = ? WHERE uid = ?";
             PreparedStatement preparedStatement = uconnection.prepareStatement(updateUserQuery);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setBoolean(2, user.isGender());
            preparedStatement.setDate(3, user.getStartdate());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getUid());
            preparedStatement.executeUpdate();


            // Commit the transaction
            uconnection.commit();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDBContext udb= new UserDBContext();
        System.out.println(udb);
        ArrayList<User> userlist=udb.getUserList();
        for(User u: userlist){
            System.out.println(u.toString());
        }
////        User u= udb.getUserByUid("user2");
////        System.out.println(u.toString());
////        ArrayList<String> uidlist = udb.getUserIdlist();
////        for(String s: uidlist){
////            System.out.println(s);
////        }
//        Date date=Date.valueOf("2023-04-05");
//        User u= new User("U005", "Nhat Hoang", false, date, "hoang@example.com", "0823368797", "Ha Noi");
////        if(udb.addUser(u, "123", "123", 0)){
////            System.out.println("Sucess");
////        } else{
////            System.out.println("Failed"); 
////       }
//        User u1= new User("U005", " Mai Nhat Hoang", true, date, "nhathoang@example.com", "0823368797", "Nghe An");
////        if(udb.updateUser(u1)){
////            System.out.println("Sucess");
////        } else{
////            System.out.println("Failed"); 
////       }
//        if(udb.deleteUser("U005")){
//            System.out.println("Sucess");
//        } else{
//            System.out.println("Failed"); 
//       }
    }
    
}
