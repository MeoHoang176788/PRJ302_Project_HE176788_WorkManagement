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
import java.sql.Date;
import model.Work;
import Resource.ResourceURL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LAPTOP 247
 */
public class WorkListDBContext {
    
    
    ResourceURL rs= new ResourceURL();
    
    //Connection wconnection;

    public Connection WorkListDBContext() {
        Connection connection = null;
        try {
            String user = "hoangmnhe176788";
            String pass = "176788";
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=PRJ301_Project_SP24_HE176788";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }        
    public ArrayList<Work> getWorkList() throws SQLException{
        ArrayList<Work> worklist= new ArrayList<Work>();
        String wquery = "SELECT * FROM Worklist";
       
        Connection connection= WorkListDBContext();
        PreparedStatement sta= connection.prepareStatement(wquery);
        ResultSet rs = sta.executeQuery();
        while(rs.next()){
            String id = rs.getString(1);
            String title=rs.getString(2);
            String request=rs.getString(3);
            String submit=rs.getString(4);
            Date date=rs.getDate(5);
            String status=rs.getString(6);
            Work w=new Work(id, title, request, submit, date, status);
            worklist.add(w);
        }
        
        return worklist;
    }
    
    
    
    //Note need to config sql statement
    public ArrayList<Work> getWorkListbyUid(String uid) throws SQLException{
        ArrayList<Work> worklist= new ArrayList<Work>();
        String wquery = "Select w.workid, w.worktitle,w.workrequest, w.worksubmit, w.workstartdate, w.workstatus \n" +
                        "from Worklist w join Work_Detail wd on w.workid=wd.workid\n" +
                        "where wd.uid Like ?";
       
        Connection connection= WorkListDBContext();
        PreparedStatement sta= connection.prepareStatement(wquery);
        sta.setString(1, "%" + uid + "%");
        ResultSet rs = sta.executeQuery();
        while(rs.next()){
            String id = rs.getString(1);
            String title=rs.getString(2);
            String request=rs.getString(3);
            String submit=rs.getString(4);
            Date date=rs.getDate(5);
            String status=rs.getString(6);
            Work w=new Work(id, title, request, submit, date, status);
            worklist.add(w);
        }
        return worklist;
    }
    
    public ArrayList<Work> getWorkbyWid(String wid) throws SQLException{
        ArrayList<Work> worklist= new ArrayList<Work>();
        String wquery = """
                        Select w.workid,w.workrequest,w.worksubmit,w.workstartdate,w.workstatus from Worklist w join [User] u on u.workid=w.workid
                        where w.workid like ?""";
       
        Connection connection= WorkListDBContext();
        PreparedStatement sta= connection.prepareStatement(wquery);
        sta.setString(1, "%" + wid + "%");
        ResultSet rs = sta.executeQuery();
        while(rs.next()){
            String id = rs.getString(1);
            String title=rs.getString(2);
            String request=rs.getString(3);
            String submit=rs.getString(4);
            Date date=rs.getDate(5);
            String status=rs.getString(6);
            Work w=new Work(wid, title, request, submit, date, status);
            worklist.add(w);
        }
        return worklist;
    }
    
    public void addNewWork(Work w) throws SQLException{
//        String wquery = "SELECT * FROM Worklist";
//        wconnection= DriverManager.getConnection(rs.databaseURL, rs.userURL, rs.passURL);
//        PreparedStatement sta= wconnection.prepareStatement(wquery);
//        ResultSet rs = sta.executeQuery();
          
            try {
            // Open a connection
            Connection connection= WorkListDBContext();

            // SQL query to insert a new work into the worklist table
            String wquery= "INSERT INTO worklist (workid, worktitle, workrequest, worksubmit, workstartdate, workstatus) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement sta= connection.prepareStatement(wquery);
                // Create a PreparedStatement object
            // Set values for the parameters
            sta.setString(1, w.getWorkid());
            sta.setString(2, w.getWorkrequest());
            sta.setString(3, w.getWorksubmit());
            sta.setDate(4, w.getWorkstartdate());
            sta.setString(5, w.getWorkstatus());

            // Execute the PreparedStatement
            sta.executeUpdate();

            // Return true if the insertion was successful
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void deleteWork(String workid) throws SQLException{
        try {
            // Open a connection
            Connection connection= WorkListDBContext();

            // SQL query to insert a new work into the worklist table
            String sql = "DELETE FROM worklist WHERE workid = ?";

            // Create a PreparedStatement object
            PreparedStatement sta = connection.prepareStatement(sql);

            // Set the workid parameter
            sta.setString(1, workid);

            // Execute the PreparedStatement
            sta.executeUpdate();

            // Return true if the insertion was successful
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateWork(String worksubmit, String workstatus, String workid){
        try {
            // Open a connection
            Connection connection= WorkListDBContext();

            // SQL query to insert a new work into the worklist table
            String sql = "UPDATE worklist SET worksubmit = ?, workstatus = ? WHERE workid = ?";
            
            PreparedStatement sta = connection.prepareStatement(sql);

            sta.setString(1, worksubmit);
            sta.setString(2, workstatus);
            sta.setString(3, workid);


            // Execute the PreparedStatement
            sta.executeUpdate();

            // Return true if the insertion was successful
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Work> getAdminWorkList() throws SQLException {
        ArrayList<Work> worklist = new ArrayList<Work>();
        String wquery = "SELECT w.workid,w.worktitle,w.workrequest,w.worksubmit,w.workstartdate,w.workstatus,wd.uid "
                + "FROM Worklist w join Work_Detail wd "
                + "on w.workid=wd.workid";

        Connection connection = WorkListDBContext();
        PreparedStatement sta = connection.prepareStatement(wquery);
        ResultSet resultSet = sta.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String title = resultSet.getString(2);
            String request = resultSet.getString(3);
            String submit = resultSet.getString(4);
            Date date = resultSet.getDate(5);
            String status = resultSet.getString(6);
            String uid = resultSet.getString(7);
            Work w = new Work(id, title, request, submit, date, status,uid);
            worklist.add(w);
        }

        return worklist;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        WorkListDBContext wdb= new WorkListDBContext();
        System.out.println(wdb);
        ArrayList<Work> worklist= wdb.getWorkListbyUid("user1");
        for(Work w: worklist){
            System.out.println(w.toString());
        }
   }
    
}
