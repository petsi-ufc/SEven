<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.evento.Organizador" %>
<%@page import="br.ufc.pet.evento.Evento" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <%        String men = (String) session.getAttribute("men");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>
    </head>
    <body>
        <div id="container">
            <%-- Incluindo o Menu --%>
            <%@include file="organ_menu.jsp" %>
            <div id="content">
                <h1 class="titulo"></h1>
                <div class="panel panel-default space-top">
                    <div class="panel-cor panel-heading text-center">Relatórios</div>
                    <div class="panel-body text-center">
                        <label><a href="../ServletCentral?comando=CmdListarAtividadesDownload" title="">Relatório de Participante por Atividade</a></label><br/>
                        <label><a href="../ServletCentral?comando=CmdListarAtividadeFrequencia" title="">Gerar Lista de Presença em Atividades</a></label><br/>
                        <label><a href="../ServletCentral?comando=CmdRelatorioParticipantesQuites" title="">Relatório de Participante Quites</a></label><br/>
                        <label><a href="../ServletCentral?comando=CmdListarParticipantes" title="">Listar Participantes</a></label><br/>
                        <label><a href="" title="">Relatório de Movimento do Caixa</a></label><br/>
                    </div>
                </div>
                <div id="list">
                    <%if (men != null) {%>
                    <center><label><%=men%></label></center>
                            <%}%>
                    <form action="" >
                        <input type="hidden" name="comando"/>

                    </form>
                </div>
            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
