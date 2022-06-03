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
                let nome = document.getElementById("nome").value;
                let email = document.getElementById("email").value;

                if (nome === null
                        || nome === '') {
                    alert("Informe o nome!");
                    //Return False para não passar pelo submit!
                    return false;
                } else if (email === null
                        || email === '') {
                    alert("Informe o Email!");
                    //Return False para não passar pelo submit!
                    return false;
                }
                
                //Retorna True se tudo tiver ok para passar pelo validator.
                return true;
            }
        </script>
    </head>
    
    <body>
        <h1>Hello World!</h1>        
        <% out.print("Seu sucesso garantido!"); %>
        
        <br/>
        <br/>
        
        <form action="receber-nome.jsp">
            <label>Nome:</label>
            <input name="nome" id="nome" />
            <br/>
            <label>Email:</label>
            <input name="email" id="email" />
            
            <input type="submit" value="Enviar" onclick="return validarDados();" />
        </form>
    </body>

</html>
