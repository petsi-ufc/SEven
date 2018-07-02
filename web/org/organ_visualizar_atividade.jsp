<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, br.ufc.pet.evento.TipoAtividade,br.ufc.pet.evento.ResponsavelAtividade,br.ufc.pet.evento.Horario,br.ufc.pet.evento.Atividade" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>
    </head>
    <body>
        <%            Atividade a = (Atividade) session.getAttribute("atividade");
        %>
        <div id="container">
            <%@include file="organ_menu.jsp" %>
            <div id="content">
                <div class="panel panel-default">
                    <div class="panel-heading text-center">Atividade</div>
                    <div class="panel-body">
                        <div class="col-lg-12 space-top">
                            <label> Nome:</label>
                            <%=a.getNome()%><br/>
                            <label>Local:</label>
                            <%=a.getLocal()%><br/>
                            <label>Vagas:</label>
                            <%=" " + a.getVagas()%><br/>
                            <label>Tipo:</label>
                            <%=a.getTipo().getNome()%><br/>
                            <label>Horarios:</label><br/>
                            <%for (Horario h1 : a.getHorarios()) {%>
                            <%=h1.printHorario() + " <br>"%>
                            <%}%>
                            <label>Responsáveis:</label><br/>
                            <% for (ResponsavelAtividade resp : a.getResponsaveis()) {%>
                            <label><%=resp.getUsuario().getNome()%></label><br />
                            <%}%>
                        </div>
                    </div>
                </div>
                <a href="" title="" onclick="history.back(); return false;" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
