<%-- 
    Document   : index
    Created on : 3 de jun. de 2022, 11:51:48
    Author     : lucia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Curso JSP</title>

        <script type="text/javascript" >
            function validarDados() {
                let login = document.getElementById("login").value;
                let senha = document.getElementById("senha").value;

                if (login === null
                        || login === '') {
                    alert("Informe o login!");
                    //Return False para não passar pelo submit!
                    return false;
                } else if (senha === null
                        || senha === '') {
                    alert("Informe a senha!");
                    //Return False para não passar pelo submit!
                    return false;
                }

                //Retorna True se tudo tiver ok para passar pelo validator.
                return true;
            }
        </script>
    </head>

    <body>
        <h1>Bem vindo ao sistema!</h1>        
        <% out.print("Seu sucesso garantido!");%>

        <br/>
        <br/>

        <form action="ServletLogin" method="post">
            <input type="hidden" value="<%= request.getParameter("url") %>" name="url" />
            
            <table>
                <tr>
                    <td><label>Login:</label></td>
                    <td><input name="login" id="login" type="text" /></td>
                </tr>

                <tr>
                    <td><label>Senha:</label></td>
                    <td><input name="senha" id="senha" type="password" /></td>
                </tr>

                <tr>
                    <td/>
                    <td><input type="submit" value="Enviar" />
                </tr>
            </table>
        </form>
        
        <h4>${msg}</h4>
    </body>

</html>
