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
                                                <div class="row">

                                                    <!--Aqui começam as implementações do que quer fazer-->
                                                    <div class="col-sm-12">
                                                        <!-- Basic Form Inputs card start -->
                                                        <div class="card">
                                                            <div class="card-block">
                                                                <h4 class="sub-title">Consulta de Usuários</h4>
                                                                <!-- Vamos Usar a mesma servlet do usuario ja existente:-->
                                                                <!-- vamos chamar pro metodo GET da servlet -->
                                                                <form class="form-material" action="<%= request.getContextPath()%>/ServletUsuarioController" method="get" id="formUser">
                                                                <!--Vamos passar o parametro acao aqui no input, pois direto no formulario nao esta sendo enviado no get-->
                                                                <input type="hidden" id="acao" name="acao" value="imprimirRelUser" />

                                                                <div class="form-row align-items-center">
                                                                    <!--Filtro de datas não requerido-->
                                                                    <div class="col-auto">
                                                                        <label class="sr-only" for="dataInicial">Data Inicial</label>
                                                                        <input type="text" name="dataInicial" id="dataInicial" value="${dataInicial}" autocomplete="off" class="form-control mb-2" placeholder="Data Nascimento Inicial">
                                                                    </div>
                                                                    <div class="col-auto">
                                                                        <label class="sr-only" for="dataFinal">Data Final</label>
                                                                        <input type="text" name="dataFinal" id="dataFinal" value="${dataFinal}" autocomplete="off" class="form-control mb-2" placeholder="Data Nascimento Final">
                                                                    </div>
                                                                    <div class="col-auto">
                                                                        <button type="submit" id="btnImprimir" class="btn btn-primary mb-2">Imprimir</button>
                                                                    </div>
                                                                </div>
                                                            </form>

                                                            <div style="height: 400px; overflow: scroll;">
                                                                <table class="table" id="tabelaUsuarios">
                                                                    <thead class="thead-dark">
                                                                        <tr>
                                                                            <th scope="col">Login</th>
                                                                            <th scope="col">Nome</th>
                                                                            <th scope="col">Email</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <c:forEach items="${listModelLoginGeral}" var="modelLoginAux">
                                                                            <tr>
                                                                                <td><c:out value="${modelLoginAux.login}"></c:out></td>
                                                                                <td><c:out value="${modelLoginAux.nome}"></c:out></td>
                                                                                <td><c:out value="${modelLoginAux.email}"></c:out></td>
                                                                                </tr>

                                                                            <c:if test="${modelLoginAux.listTelefone != null and !empty modelLoginAux.listTelefone}" >
                                                                                <tr>
                                                                                    <th scope="col" style="color: blue; font-size: 10px; font-weight: bold;">Telefone(s) ${modelLoginAux.login}</th>
                                                                                    <th scope="col" style="color: blue; font-size: 10px; font-weight: bold;"></th>
                                                                                    <th scope="col" style="color: blue; font-size: 10px; font-weight: bold;"></th>
                                                                                    
                                                                                        <c:forEach items="${modelLoginAux.listTelefone}" var="telefone">
                                                                                    <tr>
                                                                                        <td style="font-size: 9px; color: blue;"><c:out value="${telefone.numero}"></c:out></td>
                                                                                        <td style="font-size: 9px; color: blue;"></td>
                                                                                        <td style="font-size: 9px; color: blue;"></td>
                                                                                        </tr>
                                                                                </c:forEach>
                                                                                </tr>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </tbody>
                                                                </table>
                                                            </div>

                                                            <br/>

                                                            <span id="totalResListaUsuario" style="${totalResListaUsuario == null or empty totalResListaUsuario ? 'display: none;' : ''} color: black; font-weight: bold; margin-left: 5px;">${totalResListaUsuario}</span>
                                                        </div>
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

        <script type="text/javascript" >
            //Função para calendario nas datas
            //Vai traduzir o calendario padrao:
            $(function () {
                $("#dataInicial").datepicker({
                    dateFormat: 'dd/mm/yy',
                    dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
                    dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S', 'D'],
                    dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb', 'Dom'],
                    monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
                    monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
                    nextText: 'Próximo',
                    prevText: 'Anterior'
                });
            });

            $(function () {
                $("#dataFinal").datepicker({
                    dateFormat: 'dd/mm/yy',
                    dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
                    dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S', 'D'],
                    dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb', 'Dom'],
                    monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
                    monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
                    nextText: 'Próximo',
                    prevText: 'Anterior'
                });
            });
        </script>
    </body>

</html>

