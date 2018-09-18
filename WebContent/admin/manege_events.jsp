<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.evento.Administrador" %>
<%@page import="br.ufc.pet.evento.Evento" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <%        Administrador admin = (Administrador) session.getAttribute("user");
    %>
    <head>
        <script type="text/javascript"  language="javascript" src="../Script.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <title>Centro de Controle :: Administrador</title>
        <script language="javascript" src="../jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="../jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script type="text/javascript" src="../jquery/jquery.dataTables.js"></script>
        <script type="text/javascript" src="../jquery/initDataTable.js"></script>
    </head>
    <body>
        <div id="container">
            <div id="top">
                <%-- Incluindo o Menu --%>
                <%@include file="admin_menu.jsp" %>
            </div>
            <div id="content">
                <h1 class="titulo">Gerenciar Eventos</h1>
                <a style="font-size: medium;" href="add_events.jsp" title="Adicionar Eventos">Criar novo evento</a>
                <%@include file="/error.jsp"%>
                <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Sigla</th>
                            <th>Nome</th>
                            <th>Período de Inscrição</th>                       
                            <th>Gerenciar Dados Cadastrais</th>
                            <th>Excluir</th>
                            <th>Encerrar</th>
                            <th>Programação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Evento E : admin.getEventos()) {%>
                        <tr>
                            <td><%=E.getSigla()%></td>
                            <td><%=E.getNome()%></td>
                            <td><%=br.ufc.pet.util.UtilSeven.treatToString(E.getInicioPeriodoInscricao()) + " à " + br.ufc.pet.util.UtilSeven.treatToString(E.getFimPeriodoInscricao())%></td>
                            <td><a href="../ServletCentral?comando=CmdBuscarEvento&id=<%=E.getId()%>">Visualizar / Alterar</a></td>
                            <td><a href="../ServletCentral?comando=CmdExcluirEvento&id=<%=E.getId()%>" onclick="return confirmarExclucao()">Excluir</a></td>
                            <td><a href="../ServletCentral?comando=CmdEncerrarEvento&id=<%=E.getId()%>" onclick="return confirm('Deseja realmente encerrar Evento?')">Encerrar</a></td>
                            <td> <a href="../ServletCentral?comando=CmdVisualizarProgramacao&id=<%=E.getId()%>" title="Programacao" > Visualizar</a> </td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
                </div>
            </div>
            <div id="footer"></div>
        </div>
    </body>
</html>
