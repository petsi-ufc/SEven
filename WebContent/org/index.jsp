<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.entity.Organizador"%>
<%@page import="br.ufc.pet.entity.Organizacao"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <%        Organizador org = (Organizador) session.getAttribute("user");
        String nomeSaudacao = org.getUsuario().getNome().split(" ")[0];
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>
        <script language="javascript" src="../jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="../jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div id="container">
            <%-- Incluindo o Menu --%>            
            <%@include file="menu_inicial_org.jsp" %>
            <div id="content">
                <div class="row">
                <div class="col-lg-6">               
                    <div class="panel">   
                        <div class="col-lg-12 jumbotron">
                            <h1 class="text-center text-bold">Olá, <%= nomeSaudacao%></h1>
                            <h5 class="text-center">Esta é a página inicial do Organizador de eventos. Quando desejar retornar a esta página, 
                                clique na opção <span class="label label-default text-uppercase"><em>Home</em></span> no menu acima.</h><br/><br/> 
                            <%@include file="../error.jsp" %>
                        </div>
                    </div>              
                </div>
                <div class="container col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <h1 style="text-align: center; margin-top: 8.5px; font-size: 18px;"class="titulo">Tentando gerenciar atividades em evento? </h1>
                    <p style="text-align: center">Escolha um dos links abaixo para gerenciar um evento!</p>
                    <p style="text-align: center">Para isso click no nome do evento!</p>

                    <%if (org.getOrganizacoes() == null || org.getOrganizacoes().size() == 0) {%>
                    <p class="text-center text-bold">Sem eventos no momento</p>
                    <%} else {%>
                    <table id="data_table" class="table table-hover">
                        <thead>
                            <tr>
                                <th>Sigla</th>
                                <th>Evento</th>
                                <th>Programação</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Organizacao o : org.getOrganizacoes()) {
                                    if (o.getEvento() != null) {%>
                            <tr class="text-center">
                                <td> <%= o.getEvento().getSigla()%> </td>
                                <td> <a href="../ServletCentral?comando=CmdGerenciarEvento&cod_evento=<%= o.getEvento().getId()%>"><%= o.getEvento().getNome()%></a>  </td>
                                <td> <a href="../ServletCentral?comando=CmdVisualizarProgramacao&id=<%= o.getEvento().getId()%>" title="Programacao" > Visualizar</a> </td>
                            </tr>
                            <% }
                                    }
                                }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <%@include file="../footer.jsp" %>
    </body>
</html>
