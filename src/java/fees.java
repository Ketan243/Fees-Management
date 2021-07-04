/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ketan Bhenwal
 */
@WebServlet(urlPatterns = {"/fees"})
public class fees extends HttpServlet {

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
            out.println("<title>Addmission</title>"
                    + "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css\"/>\n" +
                    "\n" +
                    "<!-- jQuery library -->\n" +
                    "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" +
                    "\n" +
                    "<!-- Latest compiled JavaScript -->\n" +
                    "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>");                    
            out.println("</head>");
            out.println("<body>");
            String name = request.getParameter("cardname");
            String crdno = request.getParameter("cardnumber");
            String expmon = request.getParameter("expmonth");
            String expyear = request.getParameter("expyear");
            String cvv = request.getParameter("cvv");
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
                
                
                String sc = "insert into payment(name,cardno,expno,expmon,cvv) values(?,?,?,?,?)";
               

                PreparedStatement ps = con.prepareStatement(sc);
                
                ps.setString(1, name);
                ps.setString(2,crdno);
                ps.setString(3,expyear);
                ps.setString(4,expmon);
                ps.setString(5,cvv);
                
                 
                ps.executeUpdate();
                
		
		
               
                String st = "select * from payment";
                PreparedStatement pt = con.prepareStatement(st);
                ResultSet rs=pt.executeQuery();
		int rowCount = 0;

                out.println("<P ALIGN='center'><TABLE class='table table-primary' BORDER=1>");
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                // table header
                out.println("<TR>");
                for (int i = 0; i < columnCount; i++) {
                  out.println("<TH>" + rsmd.getColumnLabel(i + 1) + "</TH>");
                  }
                out.println("</TR>");
                // the data
                while (rs.next()) {
                 rowCount++;
                 out.println("<TR>");
                 for (int i = 0; i < columnCount; i++) {
                   out.println("<TD>" + rs.getString(i + 1) + "</TD>");
                   }
                 out.println("</TR>");
                 }
                out.println("</TABLE></P><hr>");
                out.print("<button class='btn btn-success' type='submit'>Print Form</button><br>");
                
                return rowCount;
           
            }
            catch(ClassNotFoundException | SQLException e){
                out.println(e);
            }
            out.println("<h1>Servlet fees at " + request.getContextPath() + "</h1>");
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
