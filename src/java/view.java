/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
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
import javax.servlet.ServletOutputStream;

/**
 *
 * @author Ketan Bhenwal
 */
@WebServlet(urlPatterns = {"/view"})
public class view extends HttpServlet {

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
             response.setContentType("image/jpeg");
        //PrintWriter out = response.getWriter();
            
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
            String email = request.getParameter("email");
            try{
                
                Class.forName("com.mysql.jdbc.Driver");
                Connection con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
                
		PreparedStatement ps=con.prepareStatement("select id,name,email,addr,pass,feepaid,feedue from addstud where email ='"+ email+"' "); 
		ResultSet rs=ps.executeQuery();
                
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
                out.println("</TABLE></P>");
                return rowCount;
		
			
		
             
                

           
            }
            catch(ClassNotFoundException | SQLException e){
                out.println(e);
            }
            out.println("<h1>Servlet view at " + request.getContextPath() + "</h1>");
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
