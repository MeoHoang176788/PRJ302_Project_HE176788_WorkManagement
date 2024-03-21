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
import dal.WorkListDBContext;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author LAPTOP 247
 */
public class WorkProcess extends HttpServlet {
   
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
            out.println("<title>Servlet WorkProcess</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WorkProcess at " + request.getContextPath () + "</h1>");
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
        if(loginuid.equals(null) || loginuid.isEmpty()){
            response.sendRedirect("index.html");
        } else{
            try {
                String status=request.getParameter("status");
                WorkListDBContext wdb=new WorkListDBContext();
                String wid=request.getParameter("wid");
                ArrayList<Work> w= wdb.getWorkbyWid(wid);
                String worksubmit=request.getParameter("workSubmit");
                w.get(0).setWorksubmit(worksubmit);
                String workstatus= w.get(0).getWorkstatus();
                if(status!=null){
                    workstatus=status;
                }
                request.setAttribute("wid", wid);
                request.setAttribute("rs", w);
                request.setAttribute("status", workstatus);
                request.getRequestDispatcher("view/work.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(WorkProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        PrintWriter out = response.getWriter();
        try {
            
            String status=request.getParameter("status");
            String wid=request.getParameter("wid");
            WorkListDBContext wdb=new WorkListDBContext();            
            ArrayList<Work> w= wdb.getWorkbyWid(wid);
            Work wupdate=w.get(0);
            String worksubmit=request.getParameter("workSubmit");
            String workstatus= wupdate.getWorkstatus();
            if(status==null){
                workstatus= w.get(0).getWorkstatus();
            } else{
                workstatus=status;    
                if(status.equals("Ongoing") || status.equals("Pending") || status.equals("Finished") || status.equals("Failed")){   
                    wupdate.setWorksubmit(worksubmit);
                    wupdate.setWorkstatus(status);
                    wdb.updateWork(wupdate);
                    response.sendRedirect("ManageWork");
                } else{
                    request.setAttribute("wid", wid);
                    request.setAttribute("rs", w);
                    request.setAttribute("status", workstatus);
                    request.getRequestDispatcher("view/work.jsp").forward(request, response);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkProcess.class.getName()).log(Level.SEVERE, null, ex);
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
