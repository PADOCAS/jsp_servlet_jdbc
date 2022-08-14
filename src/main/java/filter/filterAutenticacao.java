/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filter;

import connection.SingleConnection;
import dao.DAOVersionadorBanco;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Login;

/**
 *
 * @author lucia
 */
//Intercepta todas as requisições que vierem do projeto ou mapeamento
//Tudo que vier de dentro da pasta principal do webApp vai ser interceptado (urlPatterns = {"/principal/*"})
@WebFilter(filterName = "filterAutenticacao", urlPatterns = {"/principal/*"})
public class filterAutenticacao implements Filter {

    private static Connection connection;

    private static final boolean debug = true;

    private FilterConfig filterConfig = null;

    public filterAutenticacao() {
    }

    //Intercepta requisições e respostas no sistema
    //Tudo que fizer no sistema passará por aqui
    //Exemplo, validação de autenticação, dar commit e rollback, validar e fazer redirecionamento de páginas
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession session = req.getSession();
            //Url que está sendo acessada:
            String urlParaAutenticar = req.getServletPath(); //não pega a URL toda assim, ficando errado ao direcionar

            //Validar se está logado, se não redireciona para tela de login (pega o atributo do objeto session - "usuario"):
            if (session.getAttribute("usuario") == null
                    || ((Login) session.getAttribute("usuario")).getLogin() == null
                    || ((Login) session.getAttribute("usuario")).getLogin().isEmpty()) {
                if (!urlParaAutenticar.contains("/principal/ServletLogin")) {
                    //URL não contem ServletLogin, deve voltar para tela de login
                    //Guardar a URL que ele estava tentando acessar antes, assim que logar novamente jogá ele para a nova URL.
                    //Passando ?url= voce ta mandando um novo parametro com a URL dentro para ser usada no Servlet redirecionamento após logar
                    RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
                    request.setAttribute("msg", "Por favor realize o LOGIN!");
                    redirecionar.forward(request, response);
                    return;
                }
            }

            chain.doFilter(request, response);

            connection.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
            //Redirecionar para uma tela de erro (erro.jsp)
            RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
            request.setAttribute("msg", ex.getMessage());
            redirecionar.forward(request, response);
        }
    }

    //Encerra os processos quando o servidor é parado
    //Exemplo, mataria os processos de conexão com o banco
    @Override
    public void destroy() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    //Inicia os processos ou recursos quando o servidor inicia
    //Exemplo, iniciar conexão com o banco
    @Override
    public void init(FilterConfig filterConfig) {
        connection = SingleConnection.getConnection();

        //Ao subir o projeto, ja verificar o versionador de SQL:
        try {
            DAOVersionadorBanco daoVersionadorBanco = new DAOVersionadorBanco();
            String caminhoPastaSql = filterConfig.getServletContext().getRealPath("versionadorBancoSql") + File.separator;

            //Varre a lista de arquivos para saber o que precisa ser rodado..
            //Nao gostei.. nao pega uma sequencia de arquivos.. zuado.. pode dar pal em alters que facam alteracoes em mesma tabela e tal.. zuadoo essa forma ai!
            File[] files = new File(caminhoPastaSql).listFiles();

            for (File file : files) {
                Boolean alterJaRodado = daoVersionadorBanco.getArquivoSqlRodado(file.getName());

                if (!alterJaRodado) {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    Scanner scanner = new Scanner(fileInputStream, "UTF-8");

                    StringBuilder sql = new StringBuilder();
                    while (scanner.hasNext()) {
                        sql.append(scanner.nextLine());
                    }

                    try (PreparedStatement psqlExecute = connection.prepareStatement(sql.toString());) {
                        psqlExecute.executeUpdate();
                        
                        //Atualizar a tabela com o ALTER ja rodado!
                        daoVersionadorBanco.updateVersaoArquivoRodado(file.getName());
                        
                        connection.commit();
                        System.out.println("Controle Versão - Rodado Alter: " + file.getName());
                        
                        scanner.close();
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
    }
}
