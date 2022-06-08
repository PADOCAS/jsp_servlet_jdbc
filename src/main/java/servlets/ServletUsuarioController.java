/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Login;

/**
 *
 * @author lucia
 */
@WebServlet(name = "ServletUsuarioController", urlPatterns = {"/principal/ServletUsuarioController", "/ServletUsuarioController"})
public class ServletUsuarioController extends HttpServlet {

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
        //Request é o que vem da tela
        //Response é o que você vai mandar como resposta
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String confirmSenha = request.getParameter("confirmSenha");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        
        Login newLogin = new Login();
        newLogin.setLogin(login);
        newLogin.setSenha(senha);
        newLogin.setNome(nome);
        newLogin.setEmail(email);
        
        //Redireciona
        RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/usuario.jsp");
        redirecionar.forward(request, response);        
    }
}
