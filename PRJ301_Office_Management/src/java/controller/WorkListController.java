/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.WorkListDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Work;

/**
 *
 * @author LAPTOP 247
 */
public class WorkListController extends HttpServlet {
   
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
            out.println("<title>Servlet WorkListController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WorkListController at " + request.getContextPath () + "</h1>");
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
        String per=request.getParameter("per");
        boolean check=Boolean.getBoolean(per);
        request.setAttribute("per", check);
        WorkListDBContext wdb= new WorkListDBContext();
        String uid=request.getParameter("uid");
        ArrayList<Work> worklist = new ArrayList<Work>();
        if(uid==null || uid.isEmpty()){
            try {
                worklist=wdb.getWorkList();
            } catch (SQLException ex) {
                Logger.getLogger(WorkListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else{
            try {
                worklist=wdb.getWorkListbyUid(uid);
            } catch (SQLException ex) {
                Logger.getLogger(WorkListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }         
        request.setAttribute("preuid", uid);
        request.setAttribute("worklist", worklist);
        request.getRequestDispatcher("worklist.jsp").forward(request, response);
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
        WorkListDBContext wdb= new WorkListDBContext();
        String uid=request.getParameter("uid");
        request.setAttribute("per", true);
        ArrayList<Work> worklist = new ArrayList<Work>();
        if(uid==null || uid.isEmpty()){
            try {
                worklist=wdb.getWorkList();
            } catch (SQLException ex) {
                Logger.getLogger(WorkListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else{
            try {
                worklist=wdb.getWorkListbyUid(uid);
            } catch (SQLException ex) {
                Logger.getLogger(WorkListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }         
        request.setAttribute("preuid", uid);
        request.setAttribute("worklist", worklist);
        request.getRequestDispatcher("worklist.jsp").forward(request, response);
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
