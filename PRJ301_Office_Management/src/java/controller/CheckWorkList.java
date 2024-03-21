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
import model.Work;
import model.User;
import dal.WorkListDBContext;
import dal.UserDBContext;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author LAPTOP 247
 */
public class CheckWorkList extends HttpServlet {
   
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
            out.println("<title>Servlet CheckWorkList</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckWorkList at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession();
        String loginuid=(String) session.getAttribute("loginuid");
        WorkListDBContext wdb= new WorkListDBContext();
        UserDBContext udb= new UserDBContext();
        
        try {     
            User u=udb.getUserByUid(loginuid);
            ArrayList<Work> wlstatus= wdb.getWorkListByStatus("Pending");
            String check=request.getParameter("check");
            String wid=request.getParameter("workId");
            ArrayList<Work> wcheck=wdb.getWorkbyWid(wid);
            if(check==null){                
                if(u.getPermission()==1){
                    request.setAttribute("worklist", wlstatus);
                    request.getRequestDispatcher("checkworklist.jsp").forward(request, response);
                } else{
                    ArrayList<Work> wpass=wdb.getWorkListByUidAndStatus(loginuid, "Finished");
                    ArrayList<Work> wfailed=wdb.getWorkListByUidAndStatus(loginuid, "Failed");
                    wlstatus=wdb.getWorkListByUidAndStatus(loginuid, "Pending");
                    request.setAttribute("worklist", wlstatus);
                    request.setAttribute("wpass", wpass);
                    request.setAttribute("wfailed", wfailed);
                    request.getRequestDispatcher("checkstatus.jsp").forward(request, response);
                }
            } else{
                request.setAttribute("check", check);
                request.setAttribute("wid", wid);
                request.setAttribute("rs", wcheck);
                request.getRequestDispatcher("view/work.jsp").forward(request, response);
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(CheckWorkList.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        processRequest(request, response);
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
