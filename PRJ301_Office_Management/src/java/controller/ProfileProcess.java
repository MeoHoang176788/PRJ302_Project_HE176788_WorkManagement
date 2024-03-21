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
import model.User;
import dal.UserDBContext;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LAPTOP 247
 */
public class ProfileProcess extends HttpServlet {
   
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
            out.println("<title>Servlet ProfileProcess</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileProcess at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
            String uid=request.getParameter("uid");
            User u=udb.getUserByUid(uid);
            String status= request.getParameter("status");
            if(status!=null){
                if(status.equals("edit")){
                    request.setAttribute("User", u);
                    request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                }
                if(status.equals("save")){
                    String fullname=request.getParameter("name");
                    String raw_gender=request.getParameter("gender");
                    boolean gender=true;
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
                    u.setName(fullname);
                    u.setGender(gender);
                    u.setEmail(email);
                    u.setPhone(phone);
                    u.setAddress(address);
                    u.setStartdate(startdate);
                    if(udb.updateUser(u)){
                        request.setAttribute("User", u);
                        request.getRequestDispatcher("view/profile.jsp").forward(request, response);
                    }else{
                        request.setAttribute("ustatus", "failed");
                        request.getRequestDispatcher("editprofile.jsp").forward(request, response);

                    }
                    
                }
            }
            
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProfileProcess.class.getName()).log(Level.SEVERE, null, ex);
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
