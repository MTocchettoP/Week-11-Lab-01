/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.PasswordChangeService;
import businesslogic.UserService;
import dataaccess.NotesDBException;
import domainmodel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 733196
 */
public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String msg = (String) session.getAttribute("msg");
        if (msg != null) {
            request.setAttribute("errormessager", msg);
        }

        String uuid = request.getParameter("ret");
        if (uuid != null) {
            try {
                UserService us = new UserService();
                User user = us.retrivePassword(uuid);
                if(user != null){
                    request.setAttribute("username", user.getUsername());
                    request.setAttribute("password", user.getPassword());
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                }else{
                    request.setAttribute("errormessager", "The link provided was altered. Bad you");
                }
            } catch (NotesDBException ex) {
                Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        getServletContext().getRequestDispatcher("/WEB-INF/forgotpassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");

        if (email == null || email.isEmpty()) {
            session.setAttribute("errormessager", "fill in emai field");
            response.sendRedirect("/forgot");
        }

        String username = (String) session.getAttribute("username");
        UserService us = new UserService();
        try {
            us.sendRetrivalMail(email);
            session.setAttribute("msg", "An e-mail was sent to " + email + " . Please follow the link there to reset your password");
            response.sendRedirect("/forgot");
        } catch (Exception ex) {
            Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
