<%-- 
    Document   : pageheader
    Created on : 7 de jun. de 2022, 11:44:43
    Author     : lucia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="page-header">
    <div class="page-block">
        <div class="row align-items-center">
            <div class="col-md-8">
                <div class="page-header-title">
                    <h5 class="m-b-10">Projeto JAVA JSP - JDev Luciano</h5>
                    <p class="m-b-0">Bem vindo!</p>
                </div>
            </div>
            <div class="col-md-4">
                <ul class="breadcrumb-title">
                    <li class="breadcrumb-item">
                        <a href="<%= request.getContextPath()%>/principal/principal.jsp"> <i class="fa fa-home"></i> </a>
                    </li>
                    <li class="breadcrumb-item"><a href="#!">Projeto JAVA JSP</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
