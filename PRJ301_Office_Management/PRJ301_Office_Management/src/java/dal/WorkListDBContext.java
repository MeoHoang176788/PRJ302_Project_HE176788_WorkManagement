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

    ResourceURL rs = new ResourceURL();

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

    public ArrayList<Work> getWorkList() throws SQLException {
        ArrayList<Work> worklist = new ArrayList<Work>();
        String wquery = "Select * from WorkDetail";

        Connection connection = WorkListDBContext();
        PreparedStatement sta = connection.prepareStatement(wquery);
        ResultSet rs = sta.executeQuery();
        while (rs.next()) {
            String id = rs.getString(1);
            String title = rs.getString(2);
            String request = rs.getString(3);
            String submit = rs.getString(4);
            Date worksubmitdate = rs.getDate(5);
            Date workstartdate = rs.getDate(6);
            Date workenddate = rs.getDate(7);
            String status = rs.getString(8);
            Work w = new Work(id, title, request, submit, workstartdate, workenddate, worksubmitdate, status);
            worklist.add(w);
        }

        return worklist;
    }

    //Note need to config sql statement
    public ArrayList<Work> getWorkListbyUid(String uid) throws SQLException {
        ArrayList<Work> worklist = new ArrayList<Work>();
        String wquery = "Select w.workid,w.worktitle,w.workrequest,w.worksubmit,w.workstartdate,"
                + "w.workenddate,w.worksubmitdate,w.workstatus\n"
                + "from WorkDetail w join WorkList wl on w.workid=wl.workid where wl.uid like ?";

        Connection connection = WorkListDBContext();
        PreparedStatement sta = connection.prepareStatement(wquery);
        sta.setString(1, "%" + uid + "%");
        ResultSet rs = sta.executeQuery();
        while (rs.next()) {
            String id = rs.getString(1);
            String title = rs.getString(2);
            String request = rs.getString(3);
            String submit = rs.getString(4);
            Date workstartdate = rs.getDate(5);
            Date workenddate = rs.getDate(6);
            Date worksubmitdate = rs.getDate(7);
            String status = rs.getString(8);
            Work w = new Work(id, title, request, submit, workstartdate, workenddate, worksubmitdate, status);
            worklist.add(w);
        }
        return worklist;
    }

    public ArrayList<Work> getWorkbyWid(String wid) throws SQLException {
        Work work = new Work();
        ArrayList<Work> worklist = new ArrayList<Work>();
        String wquery = """
                        Select w.workid,w.worktitle,w.workrequest,w.worksubmit,w.workstartdate,w.workenddate,w.worksubmitdate,w.workstatus,wl.uid
                        from WorkDetail w join WorkList wl on w.workid=wl.workid where wl.workid like ?""";

        Connection connection = WorkListDBContext();
        PreparedStatement sta = connection.prepareStatement(wquery);
        sta.setString(1, "%" + wid + "%");
        ResultSet rsg = sta.executeQuery();
        while (rsg.next()) {
            String id = rsg.getString(1);
            String title = rsg.getString(2);
            String request = rsg.getString(3);
            String submit = rsg.getString(4);
            Date workstartdate = rsg.getDate(5);
            Date workenddate = rsg.getDate(6);
            Date worksubmitdate = rsg.getDate(7);
            String status = rsg.getString(8);
            String uid = rsg.getString(9);
            Work w = new Work(id, title, request, submit, workstartdate, workenddate, worksubmitdate, status, uid);
            worklist.add(w);
        }
        return worklist;
    }

    public boolean addWork(Work work) {
        Connection connection = WorkListDBContext();
        try {

            // Insert work into WorkDetail table
            String insertWorkDetailQuery = "INSERT INTO WorkDetail (workid, worktitle, workrequest, worksubmit, worksubmitdate, workstartdate, workenddate, workstatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertWorkDetailQuery);
            preparedStatement.setString(1, work.getWorkid());
            preparedStatement.setString(2, work.getWorktitle());
            preparedStatement.setString(3, work.getWorkrequest());
            preparedStatement.setString(4, work.getWorksubmit());
            preparedStatement.setDate(5, work.getWorksubmitdate());
            preparedStatement.setDate(6, work.getWorkstartdate());
            preparedStatement.setDate(7, work.getWorkenddate());
            preparedStatement.setString(8, work.getWorkstatus());
            preparedStatement.executeUpdate();

            // Insert work into WorkList table
            String insertWorkListQuery = "INSERT INTO WorkList (uid, workid) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(insertWorkListQuery);
            preparedStatement.setString(1, work.getUid());
            preparedStatement.setString(2, work.getWorkid());
            preparedStatement.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteWork(String wid) {
        Connection connection = WorkListDBContext();
        try {

            // Delete work from WorkList table
            String deleteWorkListQuery = "DELETE FROM WorkList WHERE workid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteWorkListQuery);
            preparedStatement.setString(1, wid);
            preparedStatement.executeUpdate();

            // Delete work from WorkDetail table
            String deleteWorkDetailQuery = "DELETE FROM WorkDetail WHERE workid = ?";
            preparedStatement = connection.prepareStatement(deleteWorkDetailQuery);
            preparedStatement.setString(1, wid);
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {;
            return false;
        }
    }

    public boolean updateWork(Work work) {
        Connection connection = WorkListDBContext();
        try {
            // Open a connection
            // Begin a transaction
            connection.setAutoCommit(false);

            // Update work in WorkDetail table
            String updateWorkDetailQuery = "UPDATE WorkDetail SET worktitle = ?, workrequest = ?, worksubmit = ?, worksubmitdate = ?, workstartdate = ?, workenddate = ?, workstatus = ? WHERE workid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateWorkDetailQuery);
            preparedStatement.setString(1, work.getWorktitle());
            preparedStatement.setString(2, work.getWorkrequest());
            preparedStatement.setString(3, work.getWorksubmit());
            preparedStatement.setDate(4, work.getWorksubmitdate());
            preparedStatement.setDate(5, work.getWorkstartdate());
            preparedStatement.setDate(6, work.getWorkenddate());
            preparedStatement.setString(7, work.getWorkstatus());
            preparedStatement.setString(8, work.getWorkid());
            preparedStatement.executeUpdate();

            // Commit the transaction
            connection.commit();
            return true;
        } catch (SQLException e) {

            return false;
        }
    }

    public ArrayList<Work> getAdminWorkList() throws SQLException {
        ArrayList<Work> worklist = new ArrayList<Work>();
        String wquery = "Select w.workid,w.worktitle,w.workrequest,w.worksubmit,w.workstartdate,w.workenddate,w.worksubmitdate,w.workstatus,wl.uid \n"
                + "from WorkDetail w left join WorkList wl on w.workid=wl.workid";

        Connection connection = WorkListDBContext();
        PreparedStatement sta = connection.prepareStatement(wquery);
        ResultSet resultSet = sta.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String title = resultSet.getString(2);
            String request = resultSet.getString(3);
            String submit = resultSet.getString(4);
            Date worksubmitdate = resultSet.getDate(5);
            Date workstartdate = resultSet.getDate(6);
            Date workenddate = resultSet.getDate(7);
            String status = resultSet.getString(8);
            String uid = resultSet.getString(9);
            Work w = new Work(id, title, request, submit, workstartdate, workenddate, worksubmitdate, status, uid);
            worklist.add(w);
        }

        return worklist;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        WorkListDBContext wdb = new WorkListDBContext();
        System.out.println(wdb);
        ArrayList<Work> worklist= wdb.getAdminWorkList();
        for(Work w: worklist){
            System.out.println(w.toString());
        }
//        ArrayList<Work> worklist1 = wdb.getWorkbyWid("W014");
//        for (Work w : worklist1) {
//            System.out.println(w.toString());
//        }
//        Date startdate = Date.valueOf("2024-04-01");
//        Date enddate = Date.valueOf("2024-05-15");
////        Work w=new Work("W015", "Project PRJ", "",null, startdate, enddate, null, "Pending", "U001");
////        if(wdb.addWork(w)){
////            System.out.println("Sucess");
////        } else{
////            System.out.println("Failed"); 
////       }
//        Work w1 = new Work("W015", "Project PRJ", "Create an Office Management Program", null, startdate, enddate, null, "Pending", "U002");
//        if (wdb.updateWork(w1)) {
//            System.out.println("Sucess");
//        } else {
//            System.out.println("Failed");
//        }
////        if (wdb.deleteWork("W015")) {
////            System.out.println("Sucess");
////        } else {
////            System.out.println("Failed");
////        }
    }
}
