/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import dao.DAOLoginRepository;
import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Login;
import servlets.util.ServletUtil;

/**
 *
 * @author lucia
 */
@WebServlet(name = "ServletTelefoneController", urlPatterns = {"/principal/ServletTelefoneController", "/ServletTelefoneController"})
public class ServletTelefoneController extends HttpServlet {

    DAOTelefoneRepository daoTelefoneRepository = new DAOTelefoneRepository();

    DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

    DAOLoginRepository daoLoginRepository = new DAOLoginRepository();

    ServletUtil servletUtil = new ServletUtil();

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
            String login = request.getParameter("login");
            System.out.println("Login para Consultar Telefone: " + login);

            if (login != null
                    && !login.isEmpty()) {
                Login loginUsuario = daoLoginRepository.consultarUsuario(login);
                request.setAttribute("usuario", loginUsuario);

                //Redireciona
                RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/telefone.jsp");
                redirecionar.forward(request, response);
            } else {
                //Rotina padrão para ficar na tela de usuário, caso não tenha cadastrado ainda esse usuário não pode abrir telefone...
                List<Login> listModelLoginPaginada = daoUsuarioRepository.consultarTodosUsuariosPaginada(servletUtil.getUsuarioLogado(request), 0);
                List<Login> listModelLoginGeral = daoUsuarioRepository.consultarTodosUsuarios(servletUtil.getUsuarioLogado(request));
                //Passa o objeto como parâmetro para tela de volta:
                request.setAttribute("listModelLogin", listModelLoginPaginada);
                request.setAttribute("totalPagina", daoUsuarioRepository.getTotalPaginasConsultaTodosUsuarios(servletUtil.getUsuarioLogado(request)));
                request.setAttribute("paginaAtual", 1L); //Adicinando parametro para trabalhar com os botoes anterior e proximo
                request.setAttribute("totalResListaUsuario", "Total de Registros: " + (listModelLoginGeral == null ? "0" : listModelLoginGeral.size()));

                if (listModelLoginGeral != null
                        && !listModelLoginGeral.isEmpty()) {
                    request.setAttribute("msgListaUser", "Listagem de Usuários");
                } else {
                    request.setAttribute("msgListaUser", "Nenhum usuário cadastrado");
                }

                //Não é delete nada continua o fluxo normal:
                RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/usuario.jsp");
                redirecionar.forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(ServletTelefoneController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
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
    }
}
