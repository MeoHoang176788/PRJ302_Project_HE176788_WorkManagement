/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;


import dal.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author LAPTOP 247
 */
public class loginservlet extends HttpServlet {
   
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
            out.println("<title>Servlet loginservlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginservlet at " + request.getContextPath () + "</h1>");
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
        request.getRequestDispatcher("index.html").forward(request, response);        
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
        HttpSession session = request.getSession();
        PrintWriter out= response.getWriter();
        
  
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        AccountDBContext accountdb= new AccountDBContext();
        ArrayList<Account> accountlist= new ArrayList<Account>();
        try {
            accountlist=accountdb.getAllAccounts();
        } catch (SQLException ex) {
            Logger.getLogger(loginservlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean loginSuccessful = false;
        String uid = null;
        int permission = -1;
        out.println(password+" "+email);
        out.println(accountlist.size());

        for (Account a : accountlist) {
            // Use .equals() for string comparison
            out.println(a.getAccount()+" "+a.getPassword());
            if (email.equals(a.getAccount()) && password.equals(a.getPassword())) {
                uid=a.getUid();
                permission=a.getPermission();
                loginSuccessful = true;
                break;
            }
        }
        String per;
        if(permission==1){
            per="1";
        } else{
            per="0";
        }
        if (loginSuccessful) {
            // Forward to userinterface.jsp on successful login
                
                session.setAttribute("loginuid", uid);
                session.setAttribute("permission", per);
                request.setAttribute("per", permission);
                request.setAttribute("uid", uid);
//                request.getRequestDispatcher("Home").forward(request, response);
                response.sendRedirect("Home");
        } else {
            // Redirect to an error page or handle the unsuccessful login scenario
            request.setAttribute("rs", false);
            //request.setAttribute("email", email);
            //request.setAttribute("password", password);
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
