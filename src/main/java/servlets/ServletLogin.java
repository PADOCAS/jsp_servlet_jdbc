/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import dao.DAOLoginRepository;
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
//O chamado Controller são as servlets ou ServletLoginController
//Mapeamento de URL que vem da tela (/principal/ServletLogin e /ServletLogin)
@WebServlet(name = "ServletLogin", urlPatterns = {"/principal/ServletLogin", "/ServletLogin"})
public class ServletLogin extends HttpServlet {

    private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();

    //Recebe os dados pela URL em parametros
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Request é o que vem da tela
        //Response é o que você vai mandar como resposta

        //Caso veio do Logout:
        String acao = request.getParameter("acao");

        if (acao != null
                && !acao.isEmpty()
                && acao.equals("logout")) {
            //Invalida a sessão, apaga todos os atributos da sessão, como o filtro já trabalha com o atributo usuário, ele vai trabalhar no filtro para reenviar para a tela de login
            request.getSession().invalidate();

            //Redireciona:
            RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
            redirecionar.forward(request, response);
        } else {
            doPost(request, response);
        }
    }

    //Recebe os dados enviados por um formulário
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Request é o que vem da tela
            //Response é o que você vai mandar como resposta
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            String url = request.getParameter("url");

            //Validações de login e senha antes de avançar:
            if (login == null
                    || login.isEmpty()) {
                //Direcionar para mesma tela, pois faltou informar os dados:
                RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
                request.setAttribute("msg", "Informe o Login!");
                redirecionar.forward(request, response);
            } else if (senha == null
                    || senha.isEmpty()) {
                //Direcionar para mesma tela, pois faltou informar os dados:
                RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
                request.setAttribute("msg", "Informe a senha!");
                redirecionar.forward(request, response);
            } else {
                Login newLogin = new Login();
                newLogin.setLogin(login);
                newLogin.setSenha(senha);

                //Validar Login:
                if (daoLoginRepository.getValidAutenticacao(newLogin)) {
                    //Colocar o objeto Login como atributo de sessão para acessar durante o uso do sistema pelo usuário
                    Login modelLogin = daoLoginRepository.consultarUsuario(login);

                    request.getSession().setAttribute("usuario", modelLogin);

                    if (url == null
                            || url.isEmpty()
                            || url.equals("null")) {
                        url = "principal/principal.jsp";
                    }

                    RequestDispatcher redirecionar = request.getRequestDispatcher(url);
                    redirecionar.forward(request, response);
                } else {
                    //Direcionar para mesma tela, pois não informou usuario/senha valido
                    RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
                    request.setAttribute("msg", "Usuário e/ou Senha inválido.");
                    redirecionar.forward(request, response);
                }
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
