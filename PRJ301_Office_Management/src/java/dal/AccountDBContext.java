/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author LAPTOP 247
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import Resource.ResourceURL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDBContext {
    
    protected Connection connection;
    
    ResourceURL rs= new ResourceURL();

    public AccountDBContext() {
        
        try {
            String user = "hoangmnhe176788";
            String pass = "176788";
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=PRJ301_Project_SP24_HE176788";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Phương thức để lấy danh sách Account từ bảng
    public ArrayList<Account> getAllAccounts() throws SQLException {
        ArrayList<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM Account";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        
            while (resultSet.next()) {
                String aid = resultSet.getString(1);
                String account = resultSet.getString(2);
                String password = resultSet.getString(3);
                int permission= resultSet.getInt(4);
                String uid = resultSet.getString(5);

                Account acc = new Account(aid, account, password, permission, uid);
                accounts.add(acc);
            }
        

        return accounts;
    }

    // Bạn có thể thêm các phương thức khác tùy thuộc vào nhu cầu của bạn

    public static void main(String[] args) throws SQLException {
        // Test phương thức getAllAccounts
        AccountDBContext adb = new AccountDBContext();
        ArrayList<Account> accounts = adb.getAllAccounts();
        System.out.println(adb);
        // In ra thông tin các Account
        for (Account acc : accounts) {
            System.out.println("AID: " + acc.getAid());
            System.out.println("Account: " + acc.getAccount());
            System.out.println("Password: " + acc.getPassword());
            System.out.println("Permission: "+acc.getPermission());
            System.out.println("UID: " + acc.getUid());
            System.out.println("-------------");
        }
    }
}

