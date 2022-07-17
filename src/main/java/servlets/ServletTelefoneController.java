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
import model.Telefone;
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
            String acao = request.getParameter("acao");

            if (acao != null
                    && !acao.isEmpty()
                    && acao.equals("excluir")) {
                String loginExclusaoTel = request.getParameter("loginExclusaoTel");
                String idExclusaoTel = request.getParameter("idExclusaoTel");

                if (loginExclusaoTel != null
                        && !loginExclusaoTel.isEmpty()
                        && idExclusaoTel != null
                        && !idExclusaoTel.isEmpty()) {
                    Telefone telExc = daoTelefoneRepository.getConsultaTelefone(loginExclusaoTel, Long.parseLong(idExclusaoTel));

                    if (telExc != null) {
                        daoTelefoneRepository.deleteTelefone(telExc);

                        //Recarrega Tela com os telefones ainda disponiveis e prontos para cadastrar novos:
                        Login loginUsuario = daoLoginRepository.consultarUsuario(loginExclusaoTel);
                        request.setAttribute("modelLogin", loginUsuario);

                        List<Telefone> listTelefones = daoTelefoneRepository.getListConsultaTelefone(loginUsuario);
                        request.setAttribute("listTelefones", listTelefones);

                        if (listTelefones != null
                                && !listTelefones.isEmpty()) {
                            request.setAttribute("msgListaTelefones", "Listagem de Telefones");
                        } else {
                            request.setAttribute("msgListaTelefones", "Nenhum telefone cadastrado");
                        }

                        request.setAttribute("msg", "Telefone Exclúido com sucesso!");

                        //Redireciona
                        RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/telefone.jsp");
                        redirecionar.forward(request, response);
                    }
                }
            } else {
                //Se não foi chamada por ACAO, executa o padrão aqui de abertura de tela telefone:
                String login = request.getParameter("login");
                System.out.println("Login para Consultar Telefone: " + login);

                if (login != null
                        && !login.isEmpty()) {
                    Login loginUsuario = daoLoginRepository.consultarUsuario(login);
                    request.setAttribute("modelLogin", loginUsuario);

                    List<Telefone> listTelefones = daoTelefoneRepository.getListConsultaTelefone(loginUsuario);
                    request.setAttribute("listTelefones", listTelefones);

                    if (listTelefones != null
                            && !listTelefones.isEmpty()) {
                        request.setAttribute("msgListaTelefones", "Listagem de Telefones");
                    } else {
                        request.setAttribute("msgListaTelefones", "Nenhum telefone cadastrado");
                    }

                    request.setAttribute("msg", "Inclusão de Telefone");

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
        try {
            //Request é o que vem da tela
            //Response é o que você vai mandar como resposta
            String login = request.getParameter("login");
            String numero = request.getParameter("numero");

            //Validação:
            if (login != null
                    && !login.isEmpty()
                    && numero != null
                    && !numero.isEmpty()) {
                Login modelLogin = daoLoginRepository.consultarUsuario(login);
                Telefone newTelefone = new Telefone();
                newTelefone.setLogin(modelLogin);
                newTelefone.setUsuarioLogin(daoLoginRepository.consultarUsuario(servletUtil.getUsuarioLogado(request)));
                newTelefone.setNumero(numero);

                daoTelefoneRepository.salvarTelefone(newTelefone);

                request.setAttribute("msg", "Telefone Incluído com sucesso para o usuário " + login + "!");
                request.setAttribute("modelLogin", modelLogin);  //Importante para manter o LOGIN e NOME em tela e salvar mais telefones!

                //Carregar listas de telefones para mostrar atualizada:
                List<Telefone> listTelefones = daoTelefoneRepository.getListConsultaTelefone(modelLogin);
                request.setAttribute("listTelefones", listTelefones);

                if (listTelefones != null
                        && !listTelefones.isEmpty()) {
                    request.setAttribute("msgListaTelefones", "Listagem de Telefones");
                } else {
                    request.setAttribute("msgListaTelefones", "Nenhum telefone cadastrado");
                }

                //Redireciona para inserir novos telefones:
                RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/telefone.jsp");
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
