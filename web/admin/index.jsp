<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.evento.Administrador"%>
<%@page import="br.ufc.pet.evento.Evento" %>
<%@include file="../ErroAutenticacaoUser.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%    Administrador admin = (Administrador) session.getAttribute("user");
    String nomeSaudacao = admin.getUsuario().getNome().split(" ")[0];
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
            <h1 class="titulo space-top">Olá, <%= nomeSaudacao%></h1>
                <p>Esta é a página inicial do Administrador. <br/>Quando desejar retornar a esta página, 
                clique na opção <span class="text-uppercase label label-default"><em>Home</em></span> no menu acima.</p>
            <br/>
            <%@include file="/error.jsp" %>
            <div class="table-responsive scroll-table">
                
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Sigla</th>
                            <th>Nome</th>
                            <th>Período de Inscrição</th>
                            <!--<th>Excluir</th>-->
                            <th>Encerrar Evento</th>
                            <th>Alterar Dados</th>
                            <th>Gerenciar Organizador</th>
                            <th>Programação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Evento E : admin.getEventos()) {%>
                        <tr>
                            <td><%=E.getSigla()%></td>
                            <td><%=E.getNome()%></td>
                            <td><%=br.ufc.pet.util.UtilSeven.treatToString(E.getInicioPeriodoInscricao()) + "</br>" + br.ufc.pet.util.UtilSeven.treatToString(E.getFimPeriodoInscricao())%></td>
                            <!--<td><a href="../ServletCentral?comando=CmdExcluirEvento&id=<%=E.getId()%>" onclick="return confirmarExclucao()"><span class="text-uppercase label label-danger">Excluir</span></a></td>-->
                            <td><a href="../ServletCentral?comando=CmdEncerrarEvento&id=<%=E.getId()%>" onclick="return confirm('Deseja realmente encerrar Evento?')"><span class=" text-uppercase label label-warning">Encerrar</span></a></td>
                            <td><a href="../ServletCentral?comando=CmdBuscarEvento&id=<%=E.getId()%>"><span class="text-uppercase label label-success">Alterar</span></a></td>
                            <td><a href="../ServletCentral?comando=CmdListarOrganizadorEventos&idEvento=<%=E.getId()%>" title="Gerenciar" ><span class="text-uppercase label label-primary">Gerenciar</span></a> </td>
                            <td><a href="../ServletCentral?comando=CmdVisualizarProgramacao&id=<%=E.getId()%>" title="Programacao" ><span class="text-uppercase label label-info">Visualizar</span></a> </td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </div>
            <div align="right"><a data-toggle="tooltip" title="Criar Novo Evento" class="btn btn-default space-top" href="add_events.jsp" role="button">Criar novo evento</a></div>
        </div>          
        <div class="footer-top">
            <%@include file="../footer.jsp" %>
        </div>
    </body>
</html>
