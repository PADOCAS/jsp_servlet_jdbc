/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DAOUsuarioRepository;
import java.io.IOException;
import java.util.List;
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

    DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

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
            String acao = request.getParameter("acao");

            if (acao != null
                    && !acao.isEmpty()
                    && acao.equals("deletar")) {
                String login = request.getParameter("login");
                String senha = request.getParameter("senha");
                String confirmSenha = request.getParameter("confirmSenha");
                String nome = request.getParameter("nome");
                String email = request.getParameter("email");

                if (login != null
                        && !login.isEmpty()) {
                    Login newLogin = new Login();
                    newLogin.setLogin(login);
                    newLogin.setSenha(senha);
                    newLogin.setConfirmSenha(confirmSenha);
                    newLogin.setNome(nome);
                    newLogin.setEmail(email);

                    Login consultaLogin = daoUsuarioRepository.consultarUsuario(login);

                    if (consultaLogin != null) {
                        daoUsuarioRepository.deletarUsuario(login);

                        request.setAttribute("msg", "Usuário excluído com sucesso!");

                        //Redireciona
                        RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/usuario.jsp");
                        //Passa um novo objeto como parâmetro para tela de volta:
                        request.setAttribute("modelLogin", new Login());
                        redirecionar.forward(request, response);
                    } else {
                        request.setAttribute("msg", "Usuário não existe ainda para ser deletado!");

                        //Redireciona
                        RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/usuario.jsp");
                        //Passa o próprio objeto como parâmetro para tela de volta:
                        request.setAttribute("modelLogin", newLogin);
                        redirecionar.forward(request, response);
                    }
                }
            } else if (acao != null
                    && !acao.isEmpty()
                    && acao.equals("deletarajax")) {
                //Ajax não atualizar o formulário... nao tem redirecionamento!
                String login = request.getParameter("login");

                if (login != null
                        && !login.isEmpty()) {
                    Login consultaLogin = daoUsuarioRepository.consultarUsuario(login);

                    if (consultaLogin != null) {
                        daoUsuarioRepository.deletarUsuario(login);

                        //Resposta para o Ajax:
                        response.setContentType("text/html; charset=UTF-8");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write("Usuário excluído com sucesso!");
                    } else {
                        //Resposta para o Ajax:
                        response.setContentType("text/html; charset=UTF-8");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write("Usuário não existe ainda para ser deletado!");
                    }
                }
            } else if (acao != null
                    && !acao.isEmpty()
                    && acao.equals("consultarajax")) {
                //Consultar Ajax:
                //Ajax não atualizar o formulário... nao tem redirecionamento!
                String nomePesquisa = request.getParameter("nomePesquisa");

                if (nomePesquisa != null
                        && !nomePesquisa.isEmpty()) {
                    List<Login> listLogin = daoUsuarioRepository.consultarUsuarioPorNome(nomePesquisa);

                    if (listLogin != null
                            && !listLogin.isEmpty()) {
                        //Resposta para o Ajax em json (Transforma lista em String json):
                        ObjectMapper mapper = new ObjectMapper();
                        String jsonResposta = mapper.writeValueAsString(listLogin);

                        response.setContentType("text/html; charset=UTF-8");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(jsonResposta);
                    } else {
                        //Resposta para o Ajax:
                        response.setContentType("text/html; charset=UTF-8");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write("Nenhum Usuário encontrado!");
                    }
                }
            } else if (acao != null
                    && !acao.isEmpty()
                    && acao.equals("selecionarUsuario")) {
                String loginSel = request.getParameter("loginSel");

                if (loginSel != null
                        && !loginSel.isEmpty()) {
                    Login consultaLogin = daoUsuarioRepository.consultarUsuario(loginSel);

                    if (consultaLogin != null) {
                        //Passa o objeto como parâmetro para tela de volta:
                        request.setAttribute("modelLogin", consultaLogin);
                        request.setAttribute("msg", "Alteração de Usuário");

                        //Redireciona
                        RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/usuario.jsp");
                        redirecionar.forward(request, response);
                    }
                }
            } else {
                //Não é delete nada continua o fluxo normal:
                RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/usuario.jsp");
                redirecionar.forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //Redirecionar para uma tela de erro (erro.jsp)
            RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
            request.setAttribute("msg", ex.getMessage());
            redirecionar.forward(request, response);
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
            //Request é o que vem da tela
            //Response é o que você vai mandar como resposta
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            String confirmSenha = request.getParameter("confirmSenha");
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");

            //Validação:
            if (login != null
                    && !login.isEmpty()
                    && senha != null
                    && !senha.isEmpty()
                    && nome != null
                    && !nome.isEmpty()
                    && email != null
                    && !email.isEmpty()
                    && confirmSenha != null
                    && !confirmSenha.isEmpty()) {
                Login newLogin = new Login();
                newLogin.setLogin(login);
                newLogin.setSenha(senha);
                newLogin.setConfirmSenha(confirmSenha);
                newLogin.setNome(nome);
                newLogin.setEmail(email);

                Login consultaLogin = daoUsuarioRepository.consultarUsuario(login);

                if (!confirmSenha.equals(senha)) {
                    request.setAttribute("msg", "Senha e confirmação de senha devem ser iguais. Verifique!");
                } else if (consultaLogin != null) {
                    //Login já existente >> Update:
                    daoUsuarioRepository.salvarUsuario(newLogin, true);
                    request.setAttribute("msg", "Usuário atualizado com sucesso!");
                } else {
                    //Login Novo >> Insert:
                    daoUsuarioRepository.salvarUsuario(newLogin, false);
                    request.setAttribute("msg", "Usuário Incluído com sucesso!");
                }

                //Redireciona
                RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/usuario.jsp");
                //Passa o objeto como parâmetro para tela de volta:
                request.setAttribute("modelLogin", newLogin);
                redirecionar.forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //Redirecionar para uma tela de erro (erro.jsp)
            RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
            request.setAttribute("msg", ex.getMessage());
            redirecionar.forward(request, response);
        }
    }
}
