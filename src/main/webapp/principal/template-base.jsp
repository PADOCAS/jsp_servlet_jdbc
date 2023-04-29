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
                                                    
                                                    <!--Aqui começam as implementações do que quer fazer-->
                                                    <!--<h1>Conteudo da página do sistema</h1>-->
                                                    
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

