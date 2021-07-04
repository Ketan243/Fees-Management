/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 *
 * @author Ketan Bhenwal
 */
@WebServlet(urlPatterns = {"/addstud"})
@MultipartConfig(maxFileSize = 16177215) // upload file up to 16MB

public class addstud extends HttpServlet {
    private static final long serialVersionUID = -1623656324694499109L;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addstud</title>");            
            out.println("</head>");
            out.println("<body>");
            InputStream inputStream = null;
            Part filePart = request.getPart("photo");
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String addr = request.getParameter("address");
            String pass = request.getParameter("pass");
            String fees_p = request.getParameter("fees_p");
            String fees_d = request.getParameter("fees_d");
            if (filePart != null) {
            // debug messages
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
             }

            String message = null; // message will be sent back to client

            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
                
                
                String sc = "insert into addstud(image,id,name,email,addr,pass,feepaid,feedue) values(?,?,?,?,?,?,?,?)";
                /*File imgfile = new File("C:\\Users\\Ketan Bhenwal\\Documents\\Visual Studio 2015\\Projects\\hospital management system1\\hospital management system1\\images\\cor.jpg");

                FileInputStream fin = new FileInputStream(imgfile);*/

                PreparedStatement ps = con.prepareStatement(sc);
                /*ps.setBinaryStream(1,(InputStream)fin,(int)imgfile.length());*/
                if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                ps.setBlob(1, inputStream);
            }
                ps.setString(2,id);
                ps.setString(3,name);
                ps.setString(4,email);
                ps.setString(5,addr);
                ps.setString(6,pass);
                ps.setString(7,fees_p);
                ps.setString(8,fees_d);
                 
                int row = ps.executeUpdate();
                
		if (row > 0) {
                message = "Image is uploaded successfully into the Database";
                 }
                out.println(message);
                ps.close();
		con.close();
                out.println("records inserted");  
                
           
            }
             catch (SQLException ex) {
            message = "ERROR: " + ex.getMessage();
        }
            out.println("<h1>Servlet addstud at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addstud.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addstud.class.getName()).log(Level.SEVERE, null, ex);
        }
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
