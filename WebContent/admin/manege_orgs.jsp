<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.evento.Evento" %>
<%@page import="br.ufc.pet.evento.Administrador" %>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <%
                Administrador admin = (Administrador) session.getAttribute("user");
                ArrayList<Evento> eventos = admin.getEventos();
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
        <div id="container-admin-manege-org">
            <%-- Incluindo o Menu --%>
            <%@include file="admin_menu.jsp" %>
 
      
                <form action="" >
                    <input type="hidden" name="comando"/>
                    <input type="hidden" name="idEvento"/>
                    <h1 class="titulo-manege text-center">Gerenciar Organizadores</h1></br>
                    <div id="list">
                        <%if (eventos == null || eventos.size() == 0) {%>
                        <center><label>Sem eventos no momento</label></center>
                        <%}%>
                     
                        <div class="panel panel-default">
                            <div class="panel-heading text-center">Escolha o evento do qual você deseja gerenciar os organizadores</div>
                            <div class="panel-body">
                            <div class="table-responsive">
                                <div style="height:335px; overflow: auto;">
                                <table class="table table-hover">
                                <thead>
                                    <tr><th>Eventos atuais</th></tr>
                                </thead>
                                <tbody>
                                    <%for (Evento event : admin.getEventos()) {%>
                                    <tr><td><a href="../ServletCentral?comando=CmdListarOrganizadorEventos&idEvento=<%=event.getId()%>"><%=event.getNome()%> - <%=event.getSigla() %></a></td></tr>
                                    <%}%>
                                </tbody>
                            </table>
                            </div>
                            </div>
                    </div>
                </form>
        </div>
        <%@include file="../footer.jsp" %>
    </body>
</html>
