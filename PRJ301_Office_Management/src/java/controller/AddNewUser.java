/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import dal.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LAPTOP 247
 */
public class AddNewUser extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddNewUser</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewUser at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setAttribute("status", "none");
        request.getRequestDispatcher("adduser.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            UserDBContext udb= new UserDBContext();
            String username=request.getParameter("username");
            String fullname=request.getParameter("name");
            String password=request.getParameter("password");
            String raw_gender=request.getParameter("gender");
            boolean gender;
            if(raw_gender.equals("1")){
                gender=true;
            } else{
                gender=false;
            }
            Date startdate=Date.valueOf(request.getParameter("date"));
            int permission=0;
            String email=request.getParameter("email");
            String phone=request.getParameter("phone");
            String address=request.getParameter("address");
            int id=udb.getUserList().size()+1;
            String uid="U";
            String aid="A";
            int n=3;
            String number="";
            while(n>0){
                n--;
                number=id%10+number;
                id=id/10;
            }
            uid=uid+number;
            aid=aid+number;
            User u= new User(uid, fullname, gender, startdate, email, phone, address);
            
            if(udb.addUser(u, username, password, permission, aid)){
                request.setAttribute("status", "success");
                response.sendRedirect("ManageUser");
            } else{
                request.setAttribute("status", "failed");
                request.getRequestDispatcher("adduser.jsp").forward(request, response);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AddNewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
