<%-- 
    Document   : principal
    Created on : 5 de jun. de 2022, 11:50:05
    Author     : lucia
--%>

<%@page import="model.Login"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"%>

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
                                                    <div class="col-sm-12">
                                                        <!-- Basic Form Inputs card start -->
                                                        <div class="card">
                                                            <div class="card-block">
                                                                <h4 class="sub-title">Cadastro de Usuário</h4>

                                                                <form class="form-material" action="<%= request.getContextPath()%>/ServletUsuarioController" method="post" id="formUser" enctype="multipart/form-data">
                                                                <input type="hidden" name="acao" id="acao" value="" />
                                                                <input type="hidden" name="paginaAtual" id="paginaAtual" value="${paginaAtual}" />
                                                                <input type="hidden" name="totalPagina" id="totalPagina" value="${totalPagina}" />
                                                                <input type="hidden" name="paginaAtualAjax" id="paginaAtualAjax" />
                                                                <input type="hidden" name="totalPaginaAjax" id="totalPaginaAjax" />

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="login" id="login" class="form-control" placeholder="Informe o Login" required="required" maxlength="20" autocomplete="off" value="${modelLogin.login}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Login</label>
                                                                </div>                                        

                                                                <p>Imagem</p>
                                                                <div class="form-group form-default input-group mb-4">
                                                                    <div class="input-group-prepend" >
                                                                        <c:if test="${modelLogin.fotoUser != null
                                                                                      && modelLogin.fotoUser != ''}">
                                                                              <!--Se tem imagem:-->
                                                                              <!--envolvido o objeto img num link (a) para fazer a parte do download da imagem-->
                                                                              <!--Componente href vai sempre po doGet da servlet-->
                                                                              <a href="<%= request.getContextPath()%>/ServletUsuarioController?acao=downloadFoto&login=${modelLogin.login}">
                                                                                  <img id="fotoBase64" alt="Imagem User" src="${modelLogin.fotoUser}" width="100px;" />                                                                        
                                                                              </a>
                                                                        </c:if>
                                                                        <c:if test="${modelLogin.fotoUser == null
                                                                                      || modelLogin.fotoUser == ''}">
                                                                              <!--Se não tem imagem:-->
                                                                              <img id="fotoBase64" alt="Imagem User" src="assets/images/User_font_awesome.svg.png" width="100px;" />                                                                        
                                                                        </c:if>
                                                                    </div>
                                                                    <!--Aceitar apenas imagens: >> accept="image/*"-->
                                                                    <input id="fileFoto" name="fileFoto" type="file" accept="image/*" onchange="visualizarImagem('fotoBase64', 'fileFoto');" class="form-control-file" style="margin-top: 15px; margin-left: 5px;"/>
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
                                                                    <select class="form-control" aria-label="Default select example" name="perfil" required="required">
                                                                        <option disabled="disabled">[Selecione o Perfil do Usuário]</option>
                                                                        <option value="ADMIN" <%
                                                                            Login loginCheck = (Login) request.getAttribute("modelLogin");

                                                                            if (loginCheck != null
                                                                                    && loginCheck.getPerfil() != null
                                                                                    && loginCheck.getPerfil().equals("ADMIN")) {
                                                                                out.print(" ");
                                                                                out.print("selected=\"selected\"");
                                                                                out.print(" ");
                                                                            }
                                                                                %>>Administrador</option>
                                                                        <option value="SECRETARIA" <%
                                                                            loginCheck = (Login) request.getAttribute("modelLogin");

                                                                            if (loginCheck != null
                                                                                    && loginCheck.getPerfil() != null
                                                                                    && loginCheck.getPerfil().equals("SECRETARIA")) {
                                                                                out.print(" ");
                                                                                out.print("selected=\"selected\"");
                                                                                out.print(" ");
                                                                            }
                                                                                %>>Secretária</option>
                                                                        <option value="AUXILIAR" <%
                                                                            loginCheck = (Login) request.getAttribute("modelLogin");

                                                                            if (loginCheck != null
                                                                                    && loginCheck.getPerfil() != null
                                                                                    && loginCheck.getPerfil().equals("AUXILIAR")) {
                                                                                out.print(" ");
                                                                                out.print("selected=\"selected\"");
                                                                                out.print(" ");
                                                                            }
                                                                                %>>Auxiliar</option>
                                                                    </select>    
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Perfil</label>
                                                                </div>

                                                                <div class="form-group form-default form-static-label">
                                                                    <p>Sexo</p>
                                                                    <input type="radio" name="sexo" value="M" class="radio-sexo" required="required" <%
                                                                        loginCheck = (Login) request.getAttribute("modelLogin");

                                                                        if (loginCheck != null
                                                                                && loginCheck.getSexo() != null
                                                                                && loginCheck.getSexo().equals("M")) {
                                                                            out.print(" ");
                                                                            out.print("checked=\"checked\"");
                                                                            out.print(" ");
                                                                        }
                                                                           %>>Masculino</>
                                                                    <input type="radio" name="sexo" value="F" class="radio-sexo" required="required" <%
                                                                        loginCheck = (Login) request.getAttribute("modelLogin");

                                                                        if (loginCheck != null
                                                                                && loginCheck.getSexo() != null
                                                                                && loginCheck.getSexo().equals("F")) {
                                                                            out.print(" ");
                                                                            out.print("checked=\"checked\"");
                                                                            out.print(" ");
                                                                        }
                                                                           %>>Feminino</>
                                                                </div>

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="nome" id="nome" class="form-control" placeholder="Informe o Nome" required="required" maxlength="100" autocomplete="off" value="${modelLogin.nome}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Nome</label>
                                                                </div>   

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="dataNascimento" id="dataNascimento" class="form-control" placeholder="Informe a data de Nascimento" autocomplete="off" value="${modelLogin.dataNascimento}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Data Nascimento</label>
                                                                </div>  

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="rendaMensal" id="rendaMensal" class="form-control" required="required" placeholder="Informe a Renda Mensal" autocomplete="off" value="${modelLogin.rendaMensal}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Renda Mensal</label>
                                                                </div>  

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="email" name="email" id="email" class="form-control" placeholder="Informe o Email" required="required" maxlength="100" autocomplete="off" value="${modelLogin.email}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Email</label>
                                                                </div>

                                                                <div class="form-group form-default form-static-label">
                                                                    <input onblur="pesquisaCep();" type="text" name="cep" id="cep" class="form-control" placeholder="Informe o CEP" required="required" maxlength="12" autocomplete="off" value="${modelLogin.cep}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">CEP</label>
                                                                </div>   

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="logradouro" id="logradouro" class="form-control" placeholder="Informe o Logradouro" required="required" maxlength="100" autocomplete="off" value="${modelLogin.logradouro}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Logradouro</label>
                                                                </div>   

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="bairro" id="bairro" class="form-control" placeholder="Informe o Bairro" maxlength="60" required="required" autocomplete="off" value="${modelLogin.bairro}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Bairro</label>
                                                                </div>   

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="localidade" id="localidade" class="form-control" placeholder="Informe a Localidade" required="required" maxlength="60" autocomplete="off" value="${modelLogin.localidade}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Localidade</label>
                                                                </div>   

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="uf" id="uf" class="form-control" placeholder="Informe o Estado" maxlength="2" required="required" autocomplete="off" value="${modelLogin.uf}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Estado</label>
                                                                </div>   

                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="numero" id="numero" class="form-control" placeholder="Informe o Número" required="required" maxlength="15" autocomplete="off" value="${modelLogin.numero}">
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Número</label>
                                                                </div>           

                                                                <button type="submit" class="btn btn-primary waves-effect waves-light">Salvar</button>
                                                                <button type="submit" class="btn btn-success waves-effect waves-light">Salvar/Novo</button>
                                                                <button type="button" class="btn btn-danger waves-effect waves-light" onclick="deletarComAjax();">Excluir</button>
                                                                <!--Vai mandar um Get para a servlet onde será feito uma consulta dos telefones do usuario-->
                                                                <a href="<%= request.getContextPath()%>/ServletTelefoneController?login=${modelLogin.login}" class="btn btn-warning waves-effect waves-light">Telefone</a>
                                                                <button type="button" class="btn btn-inverse waves-effect waves-light" onclick="limpar();">Limpar</button>
                                                                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#modalPesquisaUsuario" onclick="limpaCamposPesquisa();">Pesquisar</button>
                                                            </form>
                                                        </div>

                                                        <div class="card-footer">
                                                            <span><code id="msg" style="${msg == null or empty msg ? 'display: none;' : ''}">${msg}</code></span>
                                                        </div>

                                                        <div class="card-footer">
                                                            <span><code id="msgListaUser" style="${msgListaUser == null or empty msgListaUser ? 'display: none;' : ''}">${msgListaUser}</code></span>
                                                        </div>

                                                        <!--<div style="height: 500px; overflow: scroll;">-->
                                                        <div class="table-responsive" id="tabelaListarUsuarios">
                                                            <table class="table table-striped">
                                                                <thead class="thead-dark">
                                                                    <tr>
                                                                        <th scope="col">Login</th>
                                                                        <th scope="col">Nome</th>
                                                                        <th scope="col">Email</th>
                                                                        <th scope="col">Editar</th>
                                                                        <th scope="col">Excluir</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach items="${listModelLogin}" var="modelLoginAux">
                                                                        <tr>
                                                                            <td><c:out value="${modelLoginAux.login}"></c:out></td>
                                                                            <td><c:out value="${modelLoginAux.nome}"></c:out></td>
                                                                            <td><c:out value="${modelLoginAux.email}"></c:out></td>
                                                                            <td><button type="button" class="btn btn-info" onclick="selEditar('${modelLoginAux.login}');">Editar</button></td>
                                                                            <td><button type="button" class="btn btn-danger" onclick="deletarComAjaxDiretoLista('${modelLoginAux.login}');">Excluir</button></td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div>    

                                                        <nav aria-label="Paginação" style="margin-left: 5px;">
                                                            <ul class="pagination">
                                                                <li class="page-item"><a class="page-link" style="cursor: pointer;" onclick="paginaAnterior();">Anterior</a></li>
                                                                    <%
                                                                        Long totalPagina = (Long) request.getAttribute("totalPagina");

                                                                        for (int i = 0; i < totalPagina; i++) {
                                                                            //Acao para servlet paginar passando a pagina que esta visualizando:
                                                                            //Pega a pagina e multiplica por 5, pois é 5 por pagina para iniciar o offset
                                                                            String url = request.getContextPath() + "/ServletUsuarioController?acao=paginar&pagina=" + (i * 5) + "&paginaAtual=" + (i + 1);
                                                                            //Mostra na tela a pagina i + 1, pois o indice começa com zero aqui no teste
                                                                            out.print("<li class=\"page-item\"><a class=\"page-link button_pagination_treinamento_jsp\" href=\"" + url + "\">" + (i + 1) + "</a></li>");
                                                                        }
                                                                    %>
                                                                <li class="page-item"><a class="page-link" style="cursor: pointer;" onclick="paginaProxima();">Próxima</a></li>
                                                            </ul>
                                                        </nav>

                                                        <br/>

                                                        <span id="totalResListaUsuario" style="color: black; font-weight: bold; margin-left: 5px;">${totalResListaUsuario}</span>
                                                        <!--</div>-->
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

                            <nav aria-label="Paginação" style="margin-left: 5px;">
                                <ul class="pagination" id="ulPaginacaoUserAjax">

                                </ul>
                            </nav>

                            <br/>

                            <span id="totalResPesquisa" style="color: black; font-weight: bold; margin-left: 5px;"></span>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                        </div>
                    </div>
                </div>
            </div>

            <script type="text/javascript" >
                //Renda Mensal - campo monetário:
                $("#rendaMensal").maskMoney({showSymbol: true, symbol: "R$ ", decimal: ",", thousands: ".", precision: 2, allowZero: true});
                //forçar a formatacao do campo monetario e dar o foco nele:
                const formatterMoney = new Intl.NumberFormat('pt-BR', {
                    currency: 'BRL',
                    minimumFractionDigits: 2
                });

                $("#rendaMensal").val(formatterMoney.format($("#rendaMensal").val()));
                $("#rendaMensal").focus();

                //forçar a formatacao do campo data nascimento:
                let dataNasc = $("#dataNascimento").val();
                if (dataNasc !== null
                        && dataNasc !== "") {
                    let dateFormat = new Date(dataNasc);
                    $("#dataNascimento").val(dateFormat.toLocaleDateString('pt-BR', {
                        timeZone: 'UTC'
                    }));
                }
                $("#nome").focus();

                //Função para calendario na data Nascimento:
                //Vai traduzir o calendario padrao:
                $(function () {
                    $("#dataNascimento").datepicker({
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

                //Funções para validar apenas números:
                $("#numero").keypress(function (event) {
                    return /\d/.test(String.fromCharCode(event.keyCode));
                });

                $("#cep").keypress(function (event) {
                    return /\d/.test(String.fromCharCode(event.keyCode));
                });

                function pesquisaCep() {
                    var cep = $("#cep").val();

                    //Busca no WebService por jQuery mesmo:
                    if (cep !== null
                            && cep !== '') {
                        //Expressão regular para validar o CEP.
                        var validacep = /^[0-9]{8}$/;

                        //Valida o formato do CEP.
                        if (validacep.test(cep)) {
                            $.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function (dados) {
                                if (!("erro" in dados)) {
                                    //Atualiza os campos com os valores da consulta.
                                    if (dados.cep !== null) {
                                        $("#cep").val(dados.cep.toUpperCase());
                                    }

                                    if (dados.logradouro !== null) {
                                        $("#logradouro").val(dados.logradouro.toUpperCase());
                                    }

                                    if (dados.bairro !== null) {
                                        $("#bairro").val(dados.bairro.toUpperCase());
                                    }

                                    if (dados.localidade !== null) {
                                        $("#localidade").val(dados.localidade.toUpperCase());
                                    }

                                    if (dados.uf !== null) {
                                        $("#uf").val(dados.uf.toUpperCase());
                                    }
                                } else {
                                    //CEP pesquisado não foi encontrado.
                                    limpa_formulário_cep();
                                    alert("CEP não encontrado.");
                                }
                            });
                        } else {
                            //cep é inválido.
                            limpa_formulário_cep();
                            alert("Formato de CEP inválido.");
                        }
                    }
                }

                function limpa_formulário_cep() {
                    // Limpa valores do formulário de cep.
                    $("#logradouro").val("");
                    $("#bairro").val("");
                    $("#localidade").val("");
                    $("#uf").val("");
                }

                function visualizarImagem(fotoBase64, fileFoto) {
                    var preview = document.getElementById(fotoBase64); //Campo Imagem do Html
                    var fileUser = document.getElementById(fileFoto).files[0]; //Campo File do Html 
                    var reader = new FileReader();
                    reader.onloadend = function () {
                        preview.src = reader.result; //Carrega a Foto na tela
                    };

                    if (fileUser) {
                        reader.readAsDataURL(fileUser); //Preview da Imagem
                    } else {
                        preview.src = '';
                    }
                }

                function limpar() {
                    var elementos = document.getElementById("formUser").elements; //Retorno os elementos HTML  dentro do form

                    for (p = 0; p < elementos.length; p++) {
                        if (elementos[p].type !== null
                                && elementos[p].type !== 'radio') {
                            elementos[p].value = '';
                        }
                    }

                    //Limpar a Imagem para a padrão:
                    if (document.getElementById("fotoBase64") !== null
                            && document.getElementById("fotoBase64") !== '') {
                        document.getElementById("fotoBase64").src = "assets/images/User_font_awesome.svg.png";
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
                            contentType: "application/x-www-form-urlencoded;charset=utf-8",
                            //Parametros fica no data
                            data: "login=" + idLogin + "&acao=deletarajax",
                            success: function (response) {
                                //roda o limpar formulario se tudo ok:
                                limpar();
                                //Adicionando a resposta dentro do componente msg (das mensagens)
                                document.getElementById("msg").textContent = response;
//                                alert(response);
//                                
                                //Redirecionar com ajax carregando a listas de usuarios:
                                window.location.href = urlAction + "?acao=listarUsuarios&msgAuxiliar=" + response;
                            }
                        }).fail(function (xhr, status, errorThrow) {
                            document.getElementById("msg").textContent = xhr.responseText;
//                            alert("Erro ao deletar usuário!\n" + xhr.responseText);
                        });
                    }
                }

                function paginaAnterior() {
                    //Botão Anterior:
                    var urlAction = document.getElementById("formUser").action;
                    var paginaAtual = document.getElementById("paginaAtual").value;
                    var offset;
                    var paginaNova;

                    //PaginaAtual recebe as paginas nao o offset (de 1 pra cima):
                    if (parseInt(paginaAtual) <= 1) {
                        offset = 0;
                        paginaNova = 1;
                    } else {
                        offset = (parseInt(paginaAtual) - 2) * 5;
                        paginaNova = (parseInt(paginaAtual) - 1);
                    }

                    $.ajax({
                        method: "get",
                        url: urlAction,
                        contentType: "application/x-www-form-urlencoded;charset=utf-8",
                        //Parametros fica no data
                        data: "acao=paginar&pagina=" + offset + "&paginaAtual=" + paginaNova,
                        success: function (response) {
                            //Redirecionar com ajax carregando a listas de usuarios:
                            window.location.href = urlAction + "?acao=paginar&pagina=" + offset + "&paginaAtual=" + paginaNova;
                        }
                    }).fail(function (xhr, status, errorThrow) {
                        document.getElementById("msg").textContent = xhr.responseText;
                    });
                }

                function paginaProxima() {
                    //Botão Proximo:
                    var urlAction = document.getElementById("formUser").action;
                    var paginaAtual = document.getElementById("paginaAtual").value;
                    var totalPagina = document.getElementById("totalPagina").value;
                    var offset;
                    var paginaNova;

                    //PaginaAtual recebe as paginas nao o offset (de 1 pra cima):
                    if (parseInt(paginaAtual) >= parseInt(totalPagina)) {
                        offset = (parseInt(paginaAtual) - 1) * 5;
                        paginaNova = totalPagina;
                    } else {
                        offset = (parseInt(paginaAtual) * 5);
                        paginaNova = (parseInt(paginaAtual) + 1);
                    }

                    $.ajax({
                        method: "get",
                        url: urlAction,
                        contentType: "application/x-www-form-urlencoded;charset=utf-8",
                        //Parametros fica no data
                        data: "acao=paginar&pagina=" + offset + "&paginaAtual=" + paginaNova,
                        success: function (response) {
                            //Redirecionar com ajax carregando a listas de usuarios:
                            window.location.href = urlAction + "?acao=paginar&pagina=" + offset + "&paginaAtual=" + paginaNova;
                        }
                    }).fail(function (xhr, status, errorThrow) {
                        document.getElementById("msg").textContent = xhr.responseText;
                    });
                }

                function paginaProximaAjax() {
                    //Botão Proximo Consulta por Nome Ajax:
                    var nomePesquisa = document.getElementById("nomePesquisa").value;
                    var paginaAtualAjaxCampo = document.getElementById("paginaAtualAjax").value;
                    var totalPaginaAjaxCampo = document.getElementById("totalPaginaAjax").value;
                    var offset;
                    var paginaNova;

                    //PaginaAtual recebe as paginas nao o offset (de 1 pra cima):
                    if (parseInt(paginaAtualAjaxCampo) >= parseInt(totalPaginaAjaxCampo)) {
                        offset = (parseInt(paginaAtualAjaxCampo) - 1) * 5;
                        paginaNova = totalPaginaAjaxCampo;
                    } else {
                        offset = (parseInt(paginaAtualAjaxCampo) * 5);
                        paginaNova = (parseInt(paginaAtualAjaxCampo) + 1);
                    }

                    var urlNova = "nomePesquisa=" + nomePesquisa + "&acao=consultarajaxPage&paginaAjax=" + offset + "&paginaAtualAjax=" + paginaNova;
                    buscarUsuarioPageAjax(urlNova);
                }

                function paginaAnteriorAjax() {
                    //Botão Anterior:
                    var nomePesquisa = document.getElementById("nomePesquisa").value;
                    var paginaAtualAjaxCampo = document.getElementById("paginaAtualAjax").value;
                    var offset;
                    var paginaNova;

                    //PaginaAtual recebe as paginas nao o offset (de 1 pra cima):
                    if (parseInt(paginaAtualAjaxCampo) <= 1) {
                        offset = 0;
                        paginaNova = 1;
                    } else {
                        offset = (parseInt(paginaAtualAjaxCampo) - 2) * 5;
                        paginaNova = (parseInt(paginaAtualAjaxCampo) - 1);
                    }

                    var urlNova = "nomePesquisa=" + nomePesquisa + "&acao=consultarajaxPage&paginaAjax=" + offset + "&paginaAtualAjax=" + paginaNova;
                    buscarUsuarioPageAjax(urlNova);
                }

                window.onload = function () {
                    //Sempre que acabar de carregar a pagina roda o evento de chegar pagina ativa:
                    selectButtonPagination();
                };

                function selectButtonPaginationAjax() {
//                    Fazer o querySelector pegar todos os elementos da tela que satisfação a condição de classe (.button_pagination_treinamento_consulta_nome_ajax_jsp):
                    let listBtnDispPagination = document.querySelectorAll(".button_pagination_treinamento_consulta_nome_ajax_jsp");
                    let paginaAtual = document.getElementById("paginaAtualAjax").value;

                    if (listBtnDispPagination !== null
                            && listBtnDispPagination !== ''
                            && paginaAtual !== null
                            && paginaAtual !== '') {
                        //Remove a Classe de selecao para todos:
                        for (i = 0; i < listBtnDispPagination.length; i++) {
                            if (listBtnDispPagination[i] !== null
                                    && listBtnDispPagination[i] !== ''
                                    && listBtnDispPagination[i] !== 'undefined'
                                    && listBtnDispPagination[i].classList !== null
                                    && listBtnDispPagination[i].classList !== ''
                                    && listBtnDispPagination[i].classList !== 'undefined') {
                                if (listBtnDispPagination[i].classList.contains('button_pagination_selection')) {
                                    listBtnDispPagination[i].classList.remove("button_pagination_selection");
                                }
                            }
                        }

                        //Coloca a Classe apenas no selecionado:
                        for (i = 0; i < listBtnDispPagination.length; i++) {
                            if (listBtnDispPagination[i] !== null
                                    && listBtnDispPagination[i] !== ''
                                    && listBtnDispPagination[i] !== 'undefined'
                                    && listBtnDispPagination[i].classList !== null
                                    && listBtnDispPagination[i].classList !== ''
                                    && listBtnDispPagination[i].classList !== 'undefined'
                                    && listBtnDispPagination[i].innerHTML !== null) {
                                if (listBtnDispPagination[i].innerHTML === paginaAtual) {
                                    listBtnDispPagination[i].classList.add("button_pagination_selection");
                                }
                            }
                        }
                    }
                }

                function selectButtonPagination() {
//                    Fazer o querySelector pegar todos os elementos da tela que satisfação a condição de classe (.button_pagination_treinamento_jsp):
                    let listBtnDispPagination = document.querySelectorAll(".button_pagination_treinamento_jsp");
                    let paginaAtual = document.getElementById("paginaAtual").value;

                    if (listBtnDispPagination !== null
                            && listBtnDispPagination !== ''
                            && paginaAtual !== null
                            && paginaAtual !== '') {
                        //Remove a Classe de selecao para todos:
                        for (i = 0; i < listBtnDispPagination.length; i++) {
                            if (listBtnDispPagination[i] !== null
                                    && listBtnDispPagination[i] !== ''
                                    && listBtnDispPagination[i] !== 'undefined'
                                    && listBtnDispPagination[i].classList !== null
                                    && listBtnDispPagination[i].classList !== ''
                                    && listBtnDispPagination[i].classList !== 'undefined') {
                                if (listBtnDispPagination[i].classList.contains('button_pagination_selection')) {
                                    listBtnDispPagination[i].classList.remove("button_pagination_selection");
                                }
                            }
                        }

                        //Coloca a Classe apenas no selecionado:
                        for (i = 0; i < listBtnDispPagination.length; i++) {
                            if (listBtnDispPagination[i] !== null
                                    && listBtnDispPagination[i] !== ''
                                    && listBtnDispPagination[i] !== 'undefined'
                                    && listBtnDispPagination[i].classList !== null
                                    && listBtnDispPagination[i].classList !== ''
                                    && listBtnDispPagination[i].classList !== 'undefined'
                                    && listBtnDispPagination[i].innerHTML !== null) {
                                if (listBtnDispPagination[i].innerHTML === paginaAtual) {
                                    listBtnDispPagination[i].classList.add("button_pagination_selection");
                                }
                            }
                        }
                    }
                }

                function deletarComAjaxDiretoLista(loginSel) {
                    //Aparecer um dialog para o usuario confirmar a exclusao
                    if (confirm('Deseja realmente excluir o usuário ?')) {
                        //Pega a URLAction do formulário para cair no doGet (servlet utilizado)
                        var urlAction = document.getElementById("formUser").action;

                        $.ajax({
                            method: "get",
                            url: urlAction,
                            contentType: "application/x-www-form-urlencoded;charset=utf-8",
                            //Parametros fica no data
                            data: "login=" + loginSel + "&acao=deletarajax",
                            success: function (response) {
                                //roda o limpar formulario se tudo ok:
                                limpar();

                                document.getElementById("msg").textContent = response;

                                if (response !== null
                                        && response !== "") {
                                    document.getElementById("msg").style.display = 'block';
                                } else {
                                    document.getElementById("msg").style.display = 'none';
                                }
//                                
                                //Redirecionar com ajax carregando a listas de usuarios:
                                window.location.href = urlAction + "?acao=listarUsuarios&msgAuxiliar=" + response;
                            }
                        }).fail(function (xhr, status, errorThrow) {
                            document.getElementById("msg").textContent = xhr.responseText;
                        });
                    }
                }

                function buscarUsuarioPageAjax(url) {
                    var nomePesquisa = document.getElementById("nomePesquisa").value;

                    //Pega a URLAction do formulário para cair no doGet (servlet utilizado)
                    var urlAction = document.getElementById("formUser").action;

                    $.ajax({
                        method: "get",
                        url: urlAction,
                        contentType: "application/x-www-form-urlencoded;charset=utf-8",
                        data: url,
                        success: function (response, textStatus, xhr) {
                            //Remove todos os resultados da tabela (pesquisa anteriores)
                            $('#tabelaUsuarioPesquisa > tbody > td').remove();
                            $('#tabelaUsuarioPesquisa > tbody > tr').remove();
                            //Limpa a paginação ja realizada antes!
                            $('#ulPaginacaoUserAjax > li').remove();

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
                                        //Na String do button, para passar aspas duplas e não dar pal na string, colocar assim \' com isso funciona normalmente?
                                        $('#tabelaUsuarioPesquisa > tbody').append('<td><button type="button" onclick="selEditar(\'' + json[i].login + '\');" class="btn btn-info">Selecionar</button></td>');
                                        $('#tabelaUsuarioPesquisa > tbody').append("</tr>");
                                    }

                                    var totalRegistrosConsultaNome = xhr.getResponseHeader("totalRegistrosConsultaNome");

                                    document.getElementById("totalResPesquisa").textContent = "Total de Registros: " + totalRegistrosConsultaNome;

                                    var totalPaginaAjax = xhr.getResponseHeader("totalPaginaAjax");
                                    var paginaAtualAjax = xhr.getResponseHeader("paginaAtualAjax");

                                    $('#ulPaginacaoUserAjax').append("<li class=\"page-item\"><a class=\"page-link\" onclick=\"paginaAnteriorAjax();\" style=\"cursor: pointer;\">Anterior</a></li>");

                                    for (var i = 0; i < totalPaginaAjax; i++) {
                                        var urlNova = "nomePesquisa=" + nomePesquisa + "&acao=consultarajaxPage&paginaAjax=" + (i * 5) + "&paginaAtualAjax=" + (i + 1);
                                        //ulPaginacaoUserAjax (Acrescentar as paginas)
                                        $('#ulPaginacaoUserAjax').append('<li class="page-item"><a class="page-link button_pagination_treinamento_consulta_nome_ajax_jsp" style="cursor: pointer;" onclick="buscarUsuarioPageAjax(\'' + urlNova + '\');">' + (i + 1) + '</a></li>');
                                    }

                                    $("#ulPaginacaoUserAjax").append("<li class=\"page-item\"><a class=\"page-link\" onclick=\"paginaProximaAjax();\" style=\"cursor: pointer;\">Próxima</a></li>");

                                    document.getElementById("paginaAtualAjax").value = paginaAtualAjax;
                                    document.getElementById("totalPaginaAjax").value = totalPaginaAjax;
                                    selectButtonPaginationAjax();
                                } catch (e) {
                                    document.getElementById("totalResPesquisa").textContent = "Total de Registros: 0";
                                    //Remove todos os resultados da tabela (pesquisa anteriores)
                                    $('#tabelaUsuarioPesquisa > tbody > td').remove();
                                    $('#tabelaUsuarioPesquisa > tbody > tr').remove();
                                    //Limpa a paginação ja realizada antes!
                                    $('#ulPaginacaoUserAjax > li').remove();

                                    alert(response);
                                    selectButtonPaginationAjax();
                                }
                            }
                        }
                    }).fail(function (xhr, status, errorThrow) {
                        document.getElementById("totalResPesquisa").textContent = "Total de Registros: 0";
                        //Remove todos os resultados da tabela (pesquisa anteriores)
                        $('#tabelaUsuarioPesquisa > tbody > td').remove();
                        $('#tabelaUsuarioPesquisa > tbody > tr').remove();

                        alert("Erro ao pesquisar usuário!\n" + xhr.responseText);
                        selectButtonPaginationAjax();
                    });
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
                            contentType: "application/x-www-form-urlencoded;charset=utf-8",
                            //Parametros fica no data
                            data: "nomePesquisa=" + nomePesquisa + "&acao=consultarajax",
                            success: function (response, textStatus, xhr) {
                                //Remove todos os resultados da tabela (pesquisa anteriores)
                                $('#tabelaUsuarioPesquisa > tbody > td').remove();
                                $('#tabelaUsuarioPesquisa > tbody > tr').remove();
                                //Limpa a paginação ja realizada antes!
                                $('#ulPaginacaoUserAjax > li').remove();

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
                                            //Na String do button, para passar aspas duplas e não dar pal na string, colocar assim \' com isso funciona normalmente?
                                            $('#tabelaUsuarioPesquisa > tbody').append('<td><button type="button" onclick="selEditar(\'' + json[i].login + '\');" class="btn btn-info">Selecionar</button></td>');
                                            $('#tabelaUsuarioPesquisa > tbody').append("</tr>");
                                        }

                                        var totalRegistrosConsultaNome = xhr.getResponseHeader("totalRegistrosConsultaNome");

                                        document.getElementById("totalResPesquisa").textContent = "Total de Registros: " + totalRegistrosConsultaNome;

                                        var totalPaginaAjax = xhr.getResponseHeader("totalPaginaAjax");
                                        var paginaAtualAjax = xhr.getResponseHeader("paginaAtualAjax");

                                        $('#ulPaginacaoUserAjax').append("<li class=\"page-item\"><a class=\"page-link\" onclick=\"paginaAnteriorAjax();\" style=\"cursor: pointer;\">Anterior</a></li>");

                                        for (var i = 0; i < totalPaginaAjax; i++) {
                                            var url = "nomePesquisa=" + nomePesquisa + "&acao=consultarajaxPage&paginaAjax=" + (i * 5) + "&paginaAtualAjax=" + (i + 1);
                                            //ulPaginacaoUserAjax (Acrescentar as paginas)
                                            $('#ulPaginacaoUserAjax').append('<li class="page-item"><a class="page-link button_pagination_treinamento_consulta_nome_ajax_jsp" style="cursor: pointer;" onclick="buscarUsuarioPageAjax(\'' + url + '\');">' + (i + 1) + '</a></li>');
                                        }

                                        $("#ulPaginacaoUserAjax").append("<li class=\"page-item\"><a class=\"page-link\" onclick=\"paginaProximaAjax();\" style=\"cursor: pointer;\">Próxima</a></li>");

                                        document.getElementById("paginaAtualAjax").value = paginaAtualAjax;
                                        document.getElementById("totalPaginaAjax").value = totalPaginaAjax;

                                        selectButtonPaginationAjax();
                                    } catch (e) {
                                        document.getElementById("totalResPesquisa").textContent = "Total de Registros: 0";
                                        //Remove todos os resultados da tabela (pesquisa anteriores)
                                        $('#tabelaUsuarioPesquisa > tbody > td').remove();
                                        $('#tabelaUsuarioPesquisa > tbody > tr').remove();
                                        //Limpa a paginação ja realizada antes!
                                        $('#ulPaginacaoUserAjax > li').remove();

                                        alert(response);

                                        selectButtonPaginationAjax();
                                    }
                                }
                            }
                        }).fail(function (xhr, status, errorThrow) {
                            document.getElementById("totalResPesquisa").textContent = "Total de Registros: 0";
                            //Remove todos os resultados da tabela (pesquisa anteriores)
                            $('#tabelaUsuarioPesquisa > tbody > td').remove();
                            $('#tabelaUsuarioPesquisa > tbody > tr').remove();

                            alert("Erro ao pesquisar usuário!\n" + xhr.responseText);
                            selectButtonPaginationAjax();
                        });
                    } else {
                        document.getElementById("totalResPesquisa").textContent = "Total de Registros: 0";
                        //Remove todos os resultados da tabela (pesquisa anteriores)
                        $('#tabelaUsuarioPesquisa > tbody > td').remove();
                        $('#tabelaUsuarioPesquisa > tbody > tr').remove();

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

