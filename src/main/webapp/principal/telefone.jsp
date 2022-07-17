<%-- 
    Document   : principal
    Created on : 5 de jun. de 2022, 11:50:05
    Author     : lucia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

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
                                                <!--Novo Cadastro de Telefone-->
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <!-- Basic Form Inputs card start -->
                                                        <div class="card">
                                                            <div class="card-block">
                                                                <h4 class="sub-title">Cadastro de Telefone</h4>

                                                                <form class="form-material" action="<%= request.getContextPath()%>/ServletTelefoneController" method="post" id="formTelefone">

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="login" id="login" class="form-control" readonly="readonly" required="required" maxlength="20" autocomplete="off" value="${modelLogin.login}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Login</label>
                                                                </div>

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="nome" id="nome" class="form-control" required="required" readonly="readonly" maxlength="100" autocomplete="off" value="${modelLogin.nome}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Nome</label>
                                                                </div>  

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="numero" id="numero" placeholder="Informe o Número do Telefone" class="form-control" required="required" maxlength="11" autocomplete="off">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Número</label>
                                                                </div> 

                                                                <button type="submit" class="btn btn-primary waves-effect waves-light">Salvar</button>
                                                            </form>
                                                        </div>

                                                        <div class="card-footer">
                                                            <span><code id="msg" style="${msg == null or empty msg ? 'display: none;' : ''}">${msg}</code></span>
                                                        </div>

                                                        <div class="card-footer">
                                                            <span><code id="msgListaTelefones" style="${msgListaTelefones == null or empty msgListaTelefones ? 'display: none;' : ''}">${msgListaTelefones}</code></span>
                                                        </div>

                                                        <!--<div style="height: 500px; overflow: scroll;">-->
                                                        <table class="table" id="tabelaListarTelefones">
                                                            <thead class="thead-dark">
                                                                <tr>
                                                                    <th scope="col">Login</th>
                                                                    <th scope="col">ID</th>
                                                                    <th scope="col">Número</th>
                                                                    <th scope="col">Excluir</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach items="${listTelefones}" var="modelTelefoneAux">
                                                                    <tr>
                                                                        <td><c:out value="${modelTelefoneAux.login.login}"></c:out></td>
                                                                        <td><c:out value="${modelTelefoneAux.id}"></c:out></td>
                                                                        <td><c:out value="${modelTelefoneAux.numero}"></c:out></td>
                                                                            <!--Não fazer por Button, apenas href para executar o get e não post-->
                                                                            <td><a class="btn btn-danger" href="<%= request.getContextPath()%>/ServletTelefoneController?acao=excluir&loginExclusaoTel=${modelTelefoneAux.login.login}&idExclusaoTel=${modelTelefoneAux.id}">Excluir</a></td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <!--Novo Cadastro de Telefone-->
                                        </div>
                                        <!-- Page-body end -->
                                    </div>
                                    <div id="styleSelector"> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Required Jquery -->
        <jsp:include page="template/javascript-file.jsp" ></jsp:include>

        <script type="text/javascript" >
            //Funções para validar apenas números:
            $("#numero").keypress(function (event) {
                return /\d/.test(String.fromCharCode(event.keyCode));
            });
        </script>
    </body>

</html>

