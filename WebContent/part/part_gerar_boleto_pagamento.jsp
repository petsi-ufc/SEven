<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.entity.Inscricao" %>
<%@page import="java.util.*"%>
<%@page import="br.ufc.pet.entity.Participante"%>
<%@page import="br.ufc.pet.services.ParticipanteService"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <%
                ArrayList<Inscricao> inscricoes = (ArrayList<Inscricao>) session.getAttribute("inscricoes");
                Participante p = (Participante) session.getAttribute("user");
                String men = (String) session.getAttribute("men");
                session.removeAttribute("men");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <title>SEven</title>
    </head>
    <body>
        <div id="container">
            <div id="top">
            </div>
            <div id="content">
                <div id="list">       
                    <%if (men != null) {%>
                    <label><center><%=men%></center></label><br />
                    <%}%>
                    <%if (inscricoes == null || inscricoes.size() == 0) {%>
                    <center><label>Sem Inscrições no momento</label></center>
                    <%} else
                        for (Inscricao ics : inscricoes) {%>
                    <a href="../ServletCentral?comando=CmdGerarBoletoPagamento&idInc=<%=ics.getId()%>">Boleto</a>
                    <%}%>
                </div>
            </div>
        </div>
        <%@include file="../footer.jsp" %>
    </body>
</html>
