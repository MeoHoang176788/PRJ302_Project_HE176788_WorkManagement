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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LAPTOP 247
 */
public class UpdateWork extends HttpServlet {
   
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
            out.println("<title>Servlet UpdateWork</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateWork at " + request.getContextPath () + "</h1>");
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
                String wid=request.getParameter("wid");
                String wsubmit=request.getParameter("submit");
                WorkListDBContext wdb=new WorkListDBContext();
                Work w=wdb.getWorkbyWid(wid).get(0);
                w.setWorksubmit(wsubmit);
                w.setWorkstatus(status);
                if(wdb.updateWork(w)){
                    response.sendRedirect("ManageWork");
                } else{
                    response.sendRedirect("home");
                }
            } catch (SQLException ex) {
                Logger.getLogger(UpdateWork.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("ManageWork");
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
        try {
            PrintWriter out = response.getWriter();
            String status=request.getParameter("status");
            String wid=request.getParameter("wid");
            String wsubmit=request.getParameter("submit");
            WorkListDBContext wdb=new WorkListDBContext();
            Work w=wdb.getWorkbyWid(wid).get(0);
            out.println(w.toString());
//            w.setWorksubmit(wsubmit);
            w.setWorkstatus(status);
            out.println("After update:" +wsubmit+" "+status);
            out.println(w.toString());
//            if(wdb.updateWork(w)){
//                response.sendRedirect("ManageWork");
//            } else{
//                response.sendRedirect("home");
//            }
            } catch (SQLException ex) {
                Logger.getLogger(UpdateWork.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("ManageWork");
            }
        //request.getRequestDispatcher("ManageWork").forward(request, response);
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
