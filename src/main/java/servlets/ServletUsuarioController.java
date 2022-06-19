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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Login;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import servlets.util.ServletUtil;

/**
 *
 * @author lucia
 */
@MultipartConfig
@WebServlet(name = "ServletUsuarioController", urlPatterns = {"/principal/ServletUsuarioController", "/ServletUsuarioController"})
public class ServletUsuarioController extends HttpServlet {

    DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

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
            String msgAuxiliar = request.getParameter("msgAuxiliar");

            if (acao != null
                    && !acao.isEmpty()
                    && acao.equals("deletar")) {
                //Rotina para carregar Lista de Usuarios sempre que abrir a tela de usuario:
                List<Login> listModelLogin = daoUsuarioRepository.consultarTodosUsuarios(servletUtil.getUsuarioLogado(request));
                //Passa o objeto como parâmetro para tela de volta:
                request.setAttribute("listModelLogin", listModelLogin);
                request.setAttribute("totalResListaUsuario", "Total de Registros: " + (listModelLogin == null ? "0" : listModelLogin.size()));

                if (listModelLogin != null
                        && !listModelLogin.isEmpty()) {
                    request.setAttribute("msgListaUser", "Listagem de Usuários");
                } else {
                    request.setAttribute("msgListaUser", "Nenhum usuário cadastrado");
                }

                String login = request.getParameter("login");
                String senha = request.getParameter("senha");
                String confirmSenha = request.getParameter("confirmSenha");
                String nome = request.getParameter("nome");
                String email = request.getParameter("email");
                String perfil = request.getParameter("perfil");
                String sexo = request.getParameter("sexo");

                if (login != null
                        && !login.isEmpty()) {
                    Login newLogin = new Login();
                    newLogin.setLogin(login);
                    newLogin.setSenha(senha);
                    newLogin.setConfirmSenha(confirmSenha);
                    newLogin.setNome(nome);
                    newLogin.setEmail(email);
                    newLogin.setPerfil(perfil);
                    newLogin.setSexo(sexo);

                    Login consultaLogin = daoUsuarioRepository.consultarUsuario(login, servletUtil.getUsuarioLogado(request));

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
                    Login consultaLogin = daoUsuarioRepository.consultarUsuario(login, servletUtil.getUsuarioLogado(request));

                    if (consultaLogin != null) {
                        daoUsuarioRepository.deletarUsuario(login);

                        //Resposta para o Ajax:
                        response.setContentType("text/html; charset=UTF-8");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write("Usuário " + login + " excluído com sucesso!");
                    } else {
                        //Resposta para o Ajax:
                        response.setContentType("text/html; charset=UTF-8");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write("Usuário " + login + " não existe ainda para ser deletado!");
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
                    List<Login> listLogin = daoUsuarioRepository.consultarUsuarioPorNome(nomePesquisa, servletUtil.getUsuarioLogado(request));

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
                    //Rotina para carregar Lista de Usuarios sempre que abrir a tela de usuario:
                    List<Login> listModelLogin = daoUsuarioRepository.consultarTodosUsuarios(servletUtil.getUsuarioLogado(request));
                    //Passa o objeto como parâmetro para tela de volta:
                    request.setAttribute("listModelLogin", listModelLogin);
                    request.setAttribute("totalResListaUsuario", "Total de Registros: " + (listModelLogin == null ? "0" : listModelLogin.size()));

                    if (listModelLogin != null
                            && !listModelLogin.isEmpty()) {
                        request.setAttribute("msgListaUser", "Listagem de Usuários");
                    } else {
                        request.setAttribute("msgListaUser", "Nenhum usuário cadastrado");
                    }

                    Login consultaLogin = daoUsuarioRepository.consultarUsuario(loginSel, servletUtil.getUsuarioLogado(request));

                    if (consultaLogin != null) {
                        //Passa o objeto como parâmetro para tela de volta:
                        request.setAttribute("modelLogin", consultaLogin);
                        request.setAttribute("msg", "Alteração de Usuário");

                        //Redireciona
                        RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/usuario.jsp");
                        redirecionar.forward(request, response);
                    }
                }
            } else if (acao != null
                    && !acao.isEmpty()
                    && acao.equals("listarUsuarios")) {
                //Rotina para carregar Lista de Usuarios sempre que abrir a tela de usuario:
                List<Login> listModelLogin = daoUsuarioRepository.consultarTodosUsuarios(servletUtil.getUsuarioLogado(request));
                //Passa o objeto como parâmetro para tela de volta:
                request.setAttribute("listModelLogin", listModelLogin);
                request.setAttribute("totalResListaUsuario", "Total de Registros: " + (listModelLogin == null ? "0" : listModelLogin.size()));

                if (listModelLogin != null
                        && !listModelLogin.isEmpty()) {
                    request.setAttribute("msgListaUser", "Listagem de Usuários");
                } else {
                    request.setAttribute("msgListaUser", "Nenhum usuário cadastrado");
                }

                if (msgAuxiliar != null
                        && !msgAuxiliar.isEmpty()) {
                    request.setAttribute("msg", msgAuxiliar);
                }

                //Redireciona
                RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/usuario.jsp");
                redirecionar.forward(request, response);
            } else if (acao != null
                    && !acao.isEmpty()
                    && acao.equals("downloadFoto")) {
                //Ajax não atualizar o formulário... nao tem redirecionamento!
                String login = request.getParameter("login");

                if (login != null
                        && !login.isEmpty()) {
                    Login consultaLogin = daoUsuarioRepository.consultarUsuario(login, servletUtil.getUsuarioLogado(request));

                    if (consultaLogin != null
                            && consultaLogin.getFotoUser() != null
                            && !consultaLogin.getFotoUser().isEmpty()
                            && consultaLogin.getExtensaoFotoUser() != null
                            && !consultaLogin.getExtensaoFotoUser().isEmpty()) {
                        //Enviar como resposta um arquivo para download:
                        response.setHeader("Content-Disposition", "attachment;filename=arquivo." + consultaLogin.getExtensaoFotoUser());
                        //Quebrar a String nas virgulas (split\\,) e pega a segunda string quebrada, joga fora a primeira parte (data:image/" + part.getContentType().split("\\/")[1] + ";base64,)
                        //vai pegar so o que vem depois do base64, >>> daqui em diante:
                        response.getOutputStream().write(new Base64().decode(consultaLogin.getFotoUser().split("\\,")[1]));
                    }
                }
            } else {
                //Rotina para carregar Lista de Usuarios sempre que abrir a tela de usuario:
                List<Login> listModelLogin = daoUsuarioRepository.consultarTodosUsuarios(servletUtil.getUsuarioLogado(request));
                //Passa o objeto como parâmetro para tela de volta:
                request.setAttribute("listModelLogin", listModelLogin);
                request.setAttribute("totalResListaUsuario", "Total de Registros: " + (listModelLogin == null ? "0" : listModelLogin.size()));

                if (listModelLogin != null
                        && !listModelLogin.isEmpty()) {
                    request.setAttribute("msgListaUser", "Listagem de Usuários");
                } else {
                    request.setAttribute("msgListaUser", "Nenhum usuário cadastrado");
                }

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
            String perfil = request.getParameter("perfil");
            String sexo = request.getParameter("sexo");

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
                    && !confirmSenha.isEmpty()
                    && perfil != null
                    && !perfil.isEmpty()
                    && sexo != null
                    && !sexo.isEmpty()) {
                Login newLogin = new Login();
                newLogin.setLogin(login);
                newLogin.setSenha(senha);
                newLogin.setConfirmSenha(confirmSenha);
                newLogin.setNome(nome);
                newLogin.setEmail(email);
                newLogin.setPerfil(perfil);
                newLogin.setSexo(sexo);

                Boolean informouFoto = false;

                if (ServletFileUpload.isMultipartContent(request)) {
                    //Pegar Foto da Tela:
                    Part part = request.getPart("fileFoto");
                    if (part != null
                            && part.getSize() > 0) {
                        informouFoto = true;
                        //Converte Imagem para byte:
                        byte[] foto = org.apache.commons.compress.utils.IOUtils.toByteArray(part.getInputStream());
                        //Converte para String base 64:
                        String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64," + new Base64().encodeBase64String(foto);

                        //Seta informações da foto a gravar:
                        newLogin.setFotoUser(imagemBase64);
                        newLogin.setExtensaoFotoUser(part.getContentType().split("\\/")[1]);
                    }
                }

                Login consultaLogin = daoUsuarioRepository.consultarUsuarioGeralSaberSeInsert(login, servletUtil.getUsuarioLogado(request));

                if (!confirmSenha.equals(senha)) {
                    request.setAttribute("msg", "Senha e confirmação de senha devem ser iguais. Verifique!");
                } else if (consultaLogin != null) {
                    //Login já existente >> Update:
                    daoUsuarioRepository.salvarUsuario(newLogin, true, servletUtil.getUsuarioLogado(request));
                    request.setAttribute("msg", "Usuário atualizado com sucesso!");

                    //Caso der update e não alterou a foto, seta a existente para carregar em tela:
                    if (!informouFoto) {
                        newLogin.setFotoUser(consultaLogin.getFotoUser());
                        newLogin.setExtensaoFotoUser(consultaLogin.getExtensaoFotoUser());
                    }
                } else {
                    //Login Novo >> Insert:
                    daoUsuarioRepository.salvarUsuario(newLogin, false, servletUtil.getUsuarioLogado(request));
                    request.setAttribute("msg", "Usuário Incluído com sucesso!");
                }

                //Rotina para carregar Lista de Usuarios sempre que abrir a tela de usuario:
                List<Login> listModelLogin = daoUsuarioRepository.consultarTodosUsuarios(servletUtil.getUsuarioLogado(request));
                //Passa o objeto como parâmetro para tela de volta:
                request.setAttribute("listModelLogin", listModelLogin);
                request.setAttribute("totalResListaUsuario", "Total de Registros: " + (listModelLogin == null ? "0" : listModelLogin.size()));

                if (listModelLogin != null
                        && !listModelLogin.isEmpty()) {
                    request.setAttribute("msgListaUser", "Listagem de Usuários");
                } else {
                    request.setAttribute("msgListaUser", "Nenhum usuário cadastrado");
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
