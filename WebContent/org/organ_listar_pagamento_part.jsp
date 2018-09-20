<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.entity.Inscricao"%>
<%@page import="br.ufc.pet.entity.Evento"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <%
                //ArrayList<Inscricao> participanteinscrito = (ArrayList)session.getAttribute("inscricoesdoevento");
                Inscricao participanteinscrito = (Inscricao) session.getAttribute("inscricoesdoevento");
                String mensagem = (String) session.getAttribute("mensagem");
                session.removeAttribute("inscricoesdoevento");
                session.removeAttribute("mensagem");
                
                session.setAttribute("pag", "2");

    %>

    <%
            br.ufc.pet.services.EventoService es = new br.ufc.pet.services.EventoService();
            java.util.ArrayList<br.ufc.pet.entity.Evento> eventos = es.buscarEventosAbertos();
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>
        <script type="text/javascript" src="../Script.js"></script>
        <script language="javascript" src="../jquery/jquery-1.10.2.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div id="container">
                <%-- Incluindo o Menu --%>
                <%@include file="organ_menu.jsp" %>
                
            <div id="content">
                 <%@include file="/error.jsp" %>
                 <h1 class="titulo">Recebimento de Pagamentos de Inscrição</h1></br>
                <div align="center">
                 <div class="row"> 
                    <form style="width: 80%;" action="../ServletCentral?comando=CmdBuscarParticipantePorEmail" method="post">
                        <div class="input-group">
                            <input type="text" name="email" class="form-control" placeholder="E-mail do participante"/>
                            <span class="input-group-btn">
                              <button type="submit" class="btn btn-default">Buscar</button>
                            </span>
                        </div>
                    </form>
                 </div>
                </div>
                <br>
                <%if (mensagem == "naoencontrado") {%>
                <div align="center">
                    <div style="width: 83%;" class="alert alert-danger alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Error:</span> 
                        Inscrição não encontrada
                    </div>
                </div>
                <%} else if (mensagem == "vazio") {%>
                <div align="center">
                    <div style="width: 83%;" class="alert alert-danger alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Error:</span> 
                        Preencha o email do participante
                   </div>
                </div>
                <%} else {%>
                <%if (participanteinscrito != null) {%>
                <table class="table table-hover space-top">
                    <tr>
                        <th>Inscrição</th>
                        <th>Nome</th>
                        <th>Status</th>
                    </tr>
                    <%//for(Inscricao insc : participanteinscrito){%>
                    <tr>
                        <td><%=participanteinscrito.getId()%></td>
                        <%if (participanteinscrito.isConfirmada()) {%>
                        <td><%=participanteinscrito.getParticipante().getUsuario().getNome()%></td>
                        <% } else {%>
                        <td><a href="../ServletCentral?comando=CmdReceberPagamento&id_inscricao=<%=participanteinscrito.getId()%>" title="" ><%=participanteinscrito.getParticipante().getUsuario().getNome()%></a></td>
                        <%}%>
                        <td><%=participanteinscrito.isConfirmada() ? "Pago" : "Não pago"%></td>
                    </tr>
                    <%//}%>
                </table>
                <%}%>
                <%}%>
                <a href="organ_financeiro.jsp" title="" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a><!--onclick="history.back(); return false;"-->
            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
