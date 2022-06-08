<%-- 
    Document   : principal
    Created on : 5 de jun. de 2022, 11:50:05
    Author     : lucia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <jsp:include page="template/head.jsp" ></jsp:include>

        <body>
            <!-- Pre-loader start -->
        <jsp:include page="template/theme-loader.jsp" ></jsp:include>

            <!-- Pre-loader end -->
            <div id="pcoded" class="pcoded">
                <div class="pcoded-overlay-box"></div>
                <div class="pcoded-container navbar-wrapper">
                <jsp:include page="template/navbar.jsp" ></jsp:include>

                    <div class="pcoded-main-container">
                        <div class="pcoded-wrapper">
                        <jsp:include page="template/navbarmainmenu.jsp" ></jsp:include>

                            <div class="pcoded-content">
                                <!-- Page-header start -->
                            <jsp:include page="template/pageheader.jsp" ></jsp:include>

                                <!-- Page-header end -->
                                <div class="pcoded-inner-content">
                                    <!-- Main-body start -->
                                    <div class="main-body">
                                        <div class="page-wrapper">
                                            <!-- Page-body start -->
                                            <div class="page-body">

                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <!-- Basic Form Inputs card start -->
                                                        <div class="card">
                                                            <div class="card-header">
                                                                <span><code style="${msg == null or empty msg ? 'display: none;' : ''}">${msg}</code></span>
                                                        </div>

                                                        <div class="card-block">
                                                            <h4 class="sub-title">Cadastro de Usuário</h4>

                                                            <form class="form-material" action="<%= request.getContextPath()%>/ServletUsuarioController" method="post">
                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="login" id="login" class="form-control" placeholder="Informe o Login" required="required" maxlength="20" autocomplete="off" value="${modelLogin.login}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Login</label>
                                                                </div>                                                                    
                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="password" name="senha" id="senha" class="form-control" placeholder="Informe a Senha" required="required" maxlength="20" autocomplete="off" value="${modelLogin.senha}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Senha</label>
                                                                </div>
                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="password" name="confirmSenha" id="confirmSenha" class="form-control" placeholder="Confirmação da Senha" required="required" maxlength="20" autocomplete="off" value="${modelLogin.confirmSenha}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Confirmação da Senha</label>
                                                                </div>
                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="nome" id="nome" class="form-control" placeholder="Informe o Nome" required="required" maxlength="100" autocomplete="off" value="${modelLogin.nome}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Nome</label>
                                                                </div>                                                                    
                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="email" name="email" id="email" class="form-control" placeholder="Informe o Email" required="required" maxlength="100" autocomplete="off" value="${modelLogin.email}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Email</label>
                                                                </div>

                                                                <button class="btn btn-primary waves-effect waves-light">Salvar</button>
                                                                <button class="btn btn-success waves-effect waves-light">Salvar/Novo</button>
                                                                <button class="btn btn-danger waves-effect waves-light">Excluir</button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- Page-body end -->
                                        </div>
                                        <div id="styleSelector"> </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Required Jquery -->
            <jsp:include page="template/javascript-file.jsp" ></jsp:include>
    </body>

</html>

