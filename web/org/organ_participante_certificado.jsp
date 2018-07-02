<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.evento.Participante" %>
<%@page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../ErroAutenticacaoUser.jsp" %>
<%
     ArrayList<Participante> parts = (ArrayList<Participante>) session.getAttribute("listaParticipantes");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <title>Centro de Controle :: Administrador</title>
    </head>
    <body>
        <div id="container">
            <div id="top">
                <%-- Incluindo o Menu --%>
                <%@include file="organ_menu.jsp" %>
            </div>
            <div id="content">
                <h1 class="titulo">Lista de Participantes do Evento</h1>
                     <table class="extend">
                    <tr>
                        <th>Nome</th>
                        <th width="200px">Gerar Certificado</th>

                    </tr>
                    <%for (Participante part : parts) {%>
                    <tr>
                        <td><%=part.getUsuario().getNome()%></td>
                        <td>
                            <a href="">Gerar</a>
                        </td>
                    </tr>
                    <%}%>
                </table>

            </div>
            <div id="footer"></div>
        </div>
    </body>
</html>
