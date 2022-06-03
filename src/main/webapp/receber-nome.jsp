<%-- 
    Document   : receber-nome
    Created on : 3 de jun. de 2022, 12:40:09
    Author     : lucia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Receber-Nome</title>
    </head>
    <body>
        <%
            String nome = request.getParameter("nome");
            out.println("Nome: " + nome);

            String email = request.getParameter("email");
            out.println("Email: " + email);
        %>
    </body>
</html>
