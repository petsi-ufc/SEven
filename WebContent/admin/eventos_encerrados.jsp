<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.entity.Administrador"%>
<%@page import="br.ufc.pet.entity.Evento" %>
<%@include file="../ErroAutenticacaoUser.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
            Administrador admin = (Administrador) session.getAttribute("user");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>
        <script language="javascript" src="../jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="../jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript"  language="javascript" src="../Script.js"></script>
    </head>
    <body>
        <div id="container-admin-home">
            <%-- Incluindo o Menu --%>
            <%@include file="admin_menu.jsp" %>
            <h1 class="title-register">Eventos Encerrados</h1><hr/>
            <br/>
            <br/>
            <%@include file="/error.jsp" %>
            <div class="table-responsive scroll-table">
                
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Sigla</th>
                            <th>Nome</th>
                            <th>Período de Inscrição</th>
                            <th>Reativar</th>
                            <!--<th>Excluir</th>-->
                            <!--<th>Alterar Dados</th>-->
                            <!--<th>Gerenciar Organizador</th>-->
                            <th>Programação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Evento E : admin.getEventosEncerrados()) {%>
                        <tr>
                            <td><%=E.getSigla()%></td>
                            <td><%=E.getNome()%></td>
                            <td><%=br.ufc.pet.util.UtilSeven.treatToString(E.getInicioPeriodoInscricao()) + "</br>" + br.ufc.pet.util.UtilSeven.treatToString(E.getFimPeriodoInscricao())%></td>
                            <td><a href="../ServletCentral?comando=CmdAtivarEvento&id=<%=E.getId()%>"><span class="text-uppercase label label-success">Ativar</span></a></td>
                            <!--<td><a href="../ServletCentral?comando=CmdExcluirEvento&id=<%=E.getId()%>" onclick="return confirmarExclucao()"><span class="text-uppercase label label-danger">Excluir</span></a></td>-->
                            <!--<td><a href="../ServletCentral?comando=CmdBuscarEvento&id=<%=E.getId()%>"><span class="text-uppercase label label-success">Alterar</span></a></td>-->
                            <!--<td><a href="../ServletCentral?comando=CmdListarOrganizadorEventos&idEvento=<%=E.getId()%>" title="Gerenciar" ><span class="text-uppercase label label-primary">Gerenciar</span></a> </td>-->
                            <td><a href="../ServletCentral?comando=CmdVisualizarProgramacao&id=<%=E.getId()%>" title="Programacao" ><span class="text-uppercase label label-info">Visualizar</span></a> </td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </div>
        </div>          
        <div class="footer-top">
            <%@include file="../footer.jsp" %>
        </div>
    </body>
</html>
