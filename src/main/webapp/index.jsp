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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


        <title>JSP/Servlets/JDBC</title>

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

        <style type="text/css" >
            form {
                position: absolute;
                top: 40%;
                left: 33%;
            }

            h1 {
                position: absolute;
                top: 30%;
                left: 33%;
            }

            h5 {
                position: absolute;
                top: 70%;
                left: 33%;
                font-size: 15px;
                color: #842029;
                background-color: #f8d7da;
                border-color: #f5c2c7;
            }

            @media(max-width: 767px) {
                form {
                    top: 35%;
                    left: 10%;
                }

                h1 {
                    top: 25%;
                    left: 10%;
                    font-size: 20px;
                }

                h5 {                   
                    top: 92%;
                    left: 10%;
                    font-size: 12px;                    
                }

                .txt-info-login{
                    width: 80%;
                }
            }
        </style>
    </head>

    <body>
        <h1>Bem vindo ao sistema!</h1>        

        <br/>
        <br/>

        <form action="<%= request.getContextPath()%>/ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
            <input type="hidden" value="<%= request.getParameter("url")%>" name="url" />

            <div class="col-md-6">
                <label class="form-label">Login:</label>
                <input name="login" id="login" type="text" class="form-control txt-info-login" required="required" />
                <div class="invalid-feedback">
                    Informe o Login!
                </div>
            </div>

            <div class="col-md-6">
                <label class="form-label">Senha:</label>
                <input name="senha" id="senha" type="password" class="form-control txt-info-login" required="required" />
                <div class="invalid-feedback">
                    Informe a Senha!
                </div>
            </div>

            <div class="col-12">
                <input type="submit" value="Acessar" class="btn btn-primary" />
            </div>
        </form>

        <h5>${msg}</h5>

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

        <script type="text/javascript" >
            (function () {
                'use strict'

                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.querySelectorAll('.needs-validation')

                // Loop over them and prevent submission
                Array.prototype.slice.call(forms)
                        .forEach(function (form) {
                            form.addEventListener('submit', function (event) {
                                if (!form.checkValidity()) {
                                    event.preventDefault()
                                    event.stopPropagation()
                                }

                                form.classList.add('was-validated')
                            }, false)
                        })
            })()
        </script>
    </body>

</html>
