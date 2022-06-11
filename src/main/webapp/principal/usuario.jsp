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
                                                            <div class="card-block">
                                                                <h4 class="sub-title">Cadastro de Usuário</h4>

                                                                <form class="form-material" action="<%= request.getContextPath()%>/ServletUsuarioController" method="post" id="formUser">
                                                                <input type="hidden" name="acao" id="acao" value="" />

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

                                                                <button type="submit" class="btn btn-primary waves-effect waves-light">Salvar</button>
                                                                <button type="submit" class="btn btn-success waves-effect waves-light">Salvar/Novo</button>
                                                                <button type="button" class="btn btn-danger waves-effect waves-light" onclick="deletarComAjax();">Excluir</button>
                                                                <button type="button" class="btn btn-inverse waves-effect waves-light" onclick="limpar();">Limpar</button>
                                                                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#modalPesquisaUsuario" onclick="limpaCamposPesquisa();">Pesquisar</button>
                                                            </form>
                                                        </div>

                                                        <div class="card-footer">
                                                            <span><code id="msg" style="${msg == null or empty msg ? 'display: none;' : ''}">${msg}</code></span>
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

            <!--Modal para pesquisa de usuario:-->
            <!-- Modal -->
            <div class="modal fade" id="modalPesquisaUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document" style="max-width: 800px;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Pesquisa de Usuário</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="input-group mb-3">
                                <input type="text" id="nomePesquisa" class="form-control" placeholder="Nome do Usuário" aria-label="Nome" aria-describedby="basic-addon2">
                                <div class="input-group-append">
                                    <button class="btn btn-success" type="button" onclick="buscarUsuario();">Pesquisar</button>
                                </div>
                            </div>
                            <div style="height: 300px; overflow: scroll;">
                                <table class="table" id="tabelaUsuarioPesquisa">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th scope="col">Login</th>
                                            <th scope="col">Nome</th>
                                            <th scope="col">Email</th>
                                            <th scope="col">Selecionar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!--Sera preenchido pelo javascript na mão sempre as linhas e colunas-->
                                    </tbody>
                                </table>
                            </div>
                            <span id="totalResPesquisa" style="color: black; font-weight: bold;"></span>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                        </div>
                    </div>
                </div>
            </div>


            <script type="text/javascript" >
                function limpar() {
                    var elementos = document.getElementById("formUser").elements; //Retorno os elementos HTML  dentro do form

                    for (p = 0; p < elementos.length; p++) {
                        elementos[p].value = '';
                    }
                }

                function limpaCamposPesquisa() {
                    document.getElementById("nomePesquisa").value = "";
                    document.getElementById("totalResPesquisa").textContent = "Total de Registros: 0";
                    //Remove todos os resultados da tabela (pesquisa anteriores)
                    $('#tabelaUsuarioPesquisa > tbody > td').remove();
                    $('#tabelaUsuarioPesquisa > tbody > tr').remove();
                }

                function deletar() {
                    //Aparecer um dialog para o usuario confirmar a exclusao
                    if (confirm('Deseja realmente excluir o usuário ?')) {
                        //Trocar para o method get o formulário:
                        document.getElementById("formUser").method = 'get';
                        //Atributo acao recebe o valor 'deletar' para trabalhar com ele no metodo doGet dentro do Servlet
                        document.getElementById("acao").value = 'deletar';
                        //Envia o formulário
                        document.getElementById("formUser").submit();
                    }
                }

                function deletarComAjax() {
                    //Aparecer um dialog para o usuario confirmar a exclusao
                    if (confirm('Deseja realmente excluir o usuário ?')) {
                        //Pega a URLAction do formulário para cair no doGet (servlet utilizado)
                        var urlAction = document.getElementById("formUser").action;
                        var idLogin = document.getElementById("login").value;

                        $.ajax({
                            method: "get",
                            url: urlAction,
                            //Parametros fica no data
                            data: "login=" + idLogin + "&acao=deletarajax",
                            success: function (response) {
                                //roda o limpar formulario se tudo ok:
                                limpar();
                                //Adicionando a resposta dentro do componente msg (das mensagens)
                                document.getElementById("msg").textContent = response;
//                                alert(response);
                            }
                        }).fail(function (xhr, status, errorThrow) {
                            document.getElementById("msg").textContent = xhr.responseText;
//                            alert("Erro ao deletar usuário!\n" + xhr.responseText);
                        });
                    }
                }

                function buscarUsuario() {
                    var nomePesquisa = document.getElementById("nomePesquisa").value;

                    if (nomePesquisa !== null
                            && nomePesquisa !== ""
                            && nomePesquisa.trim() !== "") {
                        //Pega a URLAction do formulário para cair no doGet (servlet utilizado)
                        var urlAction = document.getElementById("formUser").action;

                        $.ajax({
                            method: "get",
                            url: urlAction,
                            //Parametros fica no data
                            data: "nomePesquisa=" + nomePesquisa + "&acao=consultarajax",
                            success: function (response) {
                                //Remove todos os resultados da tabela (pesquisa anteriores)
                                $('#tabelaUsuarioPesquisa > tbody > td').remove();
                                $('#tabelaUsuarioPesquisa > tbody > tr').remove();

                                if (response !== null
                                        && response !== "") {
                                    try {
                                        var json = JSON.parse(response);

                                        console.info(json);

                                        for (var i = 0; i < json.length; i++) {
                                            $('#tabelaUsuarioPesquisa > tbody').append("<tr>");
                                            $('#tabelaUsuarioPesquisa > tbody').append("<td>" + json[i].login + "</td>");
                                            $('#tabelaUsuarioPesquisa > tbody').append("<td>" + json[i].nome + "</td>");
                                            $('#tabelaUsuarioPesquisa > tbody').append("<td>" + json[i].email + "</td>");
                                            //Na String do button, para passar aspas duplas e não dar pal na string, colocar assim \' com isso funciona normalmente…
                                            $('#tabelaUsuarioPesquisa > tbody').append('<td><button type="button" onclick="selEditar(\'' + json[i].login + '\');" class="btn btn-info">Selecionar</button></td>');
                                            $('#tabelaUsuarioPesquisa > tbody').append("</tr>");
                                        }

                                        document.getElementById("totalResPesquisa").textContent = "Total de Registros: " + json.length;
                                    } catch (e) {
                                        document.getElementById("totalResPesquisa").textContent = "Total de Registros: 0";
                                        //Remove todos os resultados da tabela (pesquisa anteriores)
                                        $('#tabelaUsuarioPesquisa > tbody > td').remove();
                                        $('#tabelaUsuarioPesquisa > tbody > tr').remove();

                                        alert(response);
                                    }
                                }
                            }
                        }).fail(function (xhr, status, errorThrow) {
                            document.getElementById("totalResPesquisa").textContent = "Total de Registros: 0";
                            //Remove todos os resultados da tabela (pesquisa anteriores)
                            $('#tabelaUsuarioPesquisa > tbody > td').remove();
                            $('#tabelaUsuarioPesquisa > tbody > tr').remove();

                            alert("Erro ao pesquisar usuário!\n" + xhr.responseText);
                        });
                    } else {
                        alert("Digite alguma informação para habilitar a pesquisa!");
                    }
                }

                function selEditar(loginSel) {
                    //Pega a URLAction do formulário para cair no doGet (servlet utilizado)
                    var urlAction = document.getElementById("formUser").action;

                    //Redirecionar com ajax:
                    window.location.href = urlAction + "?acao=selecionarUsuario&loginSel=" + loginSel;
                }
            </script>
    </body>

</html>

