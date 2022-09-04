<%-- 
    Document   : navbarmainmenu
    Created on : 7 de jun. de 2022, 11:43:02
    Author     : lucia
--%>

<%@page import="model.Login"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<nav class="pcoded-navbar">
    <div class="sidebar_toggle"><a href="#"><i class="icon-close icons"></i></a></div>
    <div class="pcoded-inner-navbar main-menu">
        <div class="">
            <div class="main-menu-header">
                <!--  <img class="img-80 img-radius" src="<%= request.getContextPath()%>/assets/images/avatar-4.jpg" alt="User-Profile-Image">   -->

                <c:if test="${usuario != null
                              && usuario.fotoUser != null
                              && usuario.fotoUser != ''}">
                      <!--Se tem imagem:-->
                      <img class="img-80 img-radius" src="${usuario.fotoUser}">                                                                                                                                          
                </c:if>
                <c:if test="${usuario == null
                              || usuario.fotoUser == null
                              || usuario.fotoUser == ''}">
                      <!--Se não tem imagem:-->
                      <img class="img-80 img-radius" src="<%= request.getContextPath()%>/assets/images/User_font_awesome.svg.png" alt="User-Profile-Image">                                                                  
                </c:if>
                <div class="user-details">
                    <span id="more-details">${usuario.login}<i class="fa fa-caret-down"></i></span>
                </div>
            </div>

            <div class="main-menu-content">
                <ul>
                    <li class="more-details">
                        <!--<a href="user-profile.html"><i class="ti-user"></i>View Profile</a>-->
                        <!--<a href="#!"><i class="ti-settings"></i>Settings</a>-->
                        <a href="<%= request.getContextPath()%>/ServletLogin?acao=logout"><i class="ti-layout-sidebar-left"></i>Sair</a>
                    </li>
                </ul>
            </div>
        </div>

        <!--<div class="pcoded-navigation-label" data-i18n="nav.category.navigation">Layout</div>-->
        <ul class="pcoded-item pcoded-left-item">
            <li class="active">
                <a href="<%= request.getContextPath()%>/principal/principal.jsp" class="waves-effect waves-dark" style="margin-top: 10px;">
                    <span class="pcoded-micon"><i class="ti-home"></i><b>D</b></span>
                    <span class="pcoded-mtext" data-i18n="nav.dash.main">Início</span>
                    <span class="pcoded-mcaret"></span>
                </a>
            </li>
            <li class="pcoded-hasmenu">
                <!------------------------ CADASTROS       !-->
                <a href="javascript:void(0)" class="waves-effect waves-dark">
                    <span class="pcoded-micon"><i class="ti-layout-grid2-alt"></i></span>
                    <span class="pcoded-mtext"  data-i18n="nav.basic-components.main">Cadastros</span>
                    <span class="pcoded-mcaret"></span>
                </a>
                <ul class="pcoded-submenu">
                    <!--Condição para aparecer o menu usuario apenas para administrador:-->
                    <c:if test="<%= request.getSession().getAttribute("usuario") != null
                            && ((Login) request.getSession().getAttribute("usuario")).getPerfil() != null
                            && ((Login) request.getSession().getAttribute("usuario")).getPerfil().equals("ADMIN")%>" >
                          <li class=" ">
                              <a href="<%= request.getContextPath()%>/ServletUsuarioController?acao=listarUsuarios" class="waves-effect waves-dark">
                                  <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                                  <span class="pcoded-mtext" data-i18n="nav.basic-components.alert">Usuário</span>
                                  <span class="pcoded-mcaret"></span>
                              </a>
                          </li>
                    </c:if>
                </ul>
            </li>

            <li class="pcoded-hasmenu">
                <!------------------------ MOVIMENTACOES       !-->
                <a href="javascript:void(0)" class="waves-effect waves-dark">
                    <span class="pcoded-micon"><i class="ti-layout-grid2-alt"></i></span>
                    <span class="pcoded-mtext"  data-i18n="nav.basic-components.main">Movimentações</span>
                    <span class="pcoded-mcaret"></span>
                </a>
                <ul class="pcoded-submenu">

                </ul>
            </li>

            <li class="pcoded-hasmenu">
                <!------------------------ CONSULTAS         !-->
                <a href="javascript:void(0)" class="waves-effect waves-dark">
                    <span class="pcoded-micon"><i class="ti-layout-grid2-alt"></i></span>
                    <span class="pcoded-mtext"  data-i18n="nav.basic-components.main">Consultas</span>
                    <span class="pcoded-mcaret"></span>
                </a>
                <ul class="pcoded-submenu">
                    <li class=" ">
                        <a href="<%= request.getContextPath()%>/principal/relatorio-usuario.jsp" class="waves-effect waves-dark" style="margin-top: 10px;">
                            <span class="pcoded-micon"><i class="ti-home"></i><b>D</b></span>
                            <span class="pcoded-mtext" data-i18n="nav.dash.main">Usuário</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                    </li>
                </ul>
            </li>

            <li class="pcoded-hasmenu">
                <!------------------------ GRAFICOS       !-->
                <a href="javascript:void(0)" class="waves-effect waves-dark">
                    <span class="pcoded-micon"><i class="ti-layout-grid2-alt"></i></span>
                    <span class="pcoded-mtext"  data-i18n="nav.basic-components.main">Gráficos</span>
                    <span class="pcoded-mcaret"></span>
                </a>
                <ul class="pcoded-submenu">
                    <li class=" ">
                        <a href="<%= request.getContextPath()%>/principal/relatorio-usuario_grafico.jsp" class="waves-effect waves-dark" style="margin-top: 10px;">
                            <span class="pcoded-micon"><i class="ti-home"></i><b>D</b></span>
                            <span class="pcoded-mtext" data-i18n="nav.dash.main">Média Salarial por Tipo Usuário</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>