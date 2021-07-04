/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author Ketan Bhenwal
 */
@WebServlet(urlPatterns = {"/updatestud"})
public class updatestud extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected int processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updatestud</title>"
                    + "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css\"/>\n" +
                    "\n" +
                    "<!-- jQuery library -->\n" +
                    "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" +
                    "\n" +
                    "<!-- Latest compiled JavaScript -->\n" +
                    "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>");            
            out.println("</head>");
            out.println("<body>");
            String image = request.getParameter("photo");
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String addr = request.getParameter("address");
            String pass = request.getParameter("password");
            String fees_p = request.getParameter("fees_p");
            String fees_d = request.getParameter("fees_d");
            int status=0;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
                PreparedStatement ps=con.prepareStatement("update addstud set image=?,name=?,email=?,addr=?,pass=?,feepaid=?,feedue=? where id=?");
                ps.setString(1,"image");
                ps.setString(2,name);
		ps.setString(3,email);
		ps.setString(4,addr);
		ps.setString(5,pass);
		ps.setString(6,fees_p);
		ps.setString(7,fees_d);
		ps.setString(8,id);
		ps.execute();
                ps.close();
                con.close();
                out.println("<h1>Records Updated</h1>");
                 return status;
            }  
            catch(Exception e){
                out.println(e);
            }
                
            out.println("<h1>Servlet updatestud at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        return 0;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
