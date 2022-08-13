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
                                                                <h4 class="sub-title">Gráfico de Usuários</h4>
                                                                <!-- Vamos Usar a mesma servlet do usuario ja existente:-->
                                                                <!-- vamos chamar pro metodo GET da servlet -->
                                                                <form class="form-material" action="<%= request.getContextPath()%>/ServletUsuarioController" method="get" id="formUser">
                                                                <!--Vamos passar o parametro acao aqui no input, pois direto no formulario nao esta sendo enviado no get-->
                                                                <input type="hidden" id="acaoConsultarImprimir" name="acao" value="consultarRelUser" />

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
                                                                        <button type="button" onclick="gerarGrafico();" id="btnGerarGrafico" class="btn btn-primary mb-2">Gerar Gráfico</button>
                                                                    </div>
                                                                </div>
                                                            </form>

                                                            <div class="card-footer">
                                                                <span><code id="msg" style="${msg == null or empty msg ? 'display: none;' : ''}">${msg}</code></span>
                                                            </div>

                                                            <!--Canvas do gráfico vai sair dentro da div-->
                                                            <div>
                                                                <canvas id="myChart"></canvas>
                                                            </div>
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

        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

        <script type="text/javascript" >

                                                                            var myChart = new Chart(document.getElementById('myChart'));

                                                                            function gerarGrafico() {
                                                                                //Pega a URLAction do formulário para cair no doGet (servlet utilizado)
                                                                                var urlAction = document.getElementById("formUser").action;
                                                                                var dataInicial = document.getElementById("dataInicial").value;
                                                                                var dataFinal = document.getElementById("dataFinal").value;

                                                                                $.ajax({
                                                                                    method: "get",
                                                                                    url: urlAction,
                                                                                    contentType: "application/x-www-form-urlencoded;charset=utf-8",
                                                                                    //Parametros fica no data
                                                                                    data: "dataInicial=" + dataInicial + "&dataFinal=" + dataFinal + "&acao=graficoMediaSalarialUsuario",
                                                                                    success: function (response, textStatus, xhr) {
                                                                                        //Limpar o grafico para gerar novamente:
                                                                                        myChart.destroy();

                                                                                        if (response !== null
                                                                                                && response !== "") {
                                                                                            var json = JSON.parse(response);

                                                                                            myChart = new Chart(
                                                                                                    document.getElementById('myChart'),
                                                                                                    {
                                                                                                        type: 'line',
                                                                                                        data: {
                                                                                                            labels: json.listPerfil,
                                                                                                            datasets: [{
                                                                                                                    label: 'Média Salarial por Tipo Usuário',
                                                                                                                    backgroundColor: 'rgb(255, 99, 132)',
                                                                                                                    borderColor: 'rgb(255, 99, 132)',
                                                                                                                    data: json.listSalario,
                                                                                                                }]
                                                                                                        },
                                                                                                        options: {}
                                                                                                    }
                                                                                            );
                                                                                        }

                                                                                        var message = xhr.getResponseHeader("msg");
                                                                                        document.getElementById("msg").textContent = message;
                                                                                        document.getElementById("msg").style.display = 'block';
                                                                                    }
                                                                                }).fail(function (xhr, status, errorThrow) {
                                                                                    alert(xhr.responseText);
                                                                                });
                                                                            }

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

