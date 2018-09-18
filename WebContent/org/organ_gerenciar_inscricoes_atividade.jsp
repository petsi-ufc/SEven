<%@page import="br.ufc.pet.entity.Atividade"%>
<%@page import="br.ufc.pet.entity.Inscricao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.ufc.pet.entity.Organizador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../ErroAutenticacaoUser.jsp" %>
<%    br.ufc.pet.entity.Evento e = (br.ufc.pet.entity.Evento) session.getAttribute("evento");
    Atividade a = (Atividade) session.getAttribute("atividade");
    Organizador organizador = (Organizador) session.getAttribute("user");
    ArrayList<Inscricao> inscricoesNaAtividade = (ArrayList<Inscricao>) session.getAttribute("inscAtiv");
    String estado = null;
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

        <%-- Incluindo Menu --%>
        <%@include file="organ_menu.jsp" %>

        <div id="content">
            <%@include file="/error.jsp"%>
            <h1 class="titulo">Visualizar as Inscrições na Atividade <%=a.getNome()%></h1>
            <div class="panel panel-default">
                <div class="panel-heading text-center">Lista de todos os inscritos na atividade</div>
                <div class="panel-body">
                    <div class="col-lg-12 space-top">
                        <%if (inscricoesNaAtividade == null || inscricoesNaAtividade.isEmpty()) {%>
                        <p style="text-align: center;"><label>Não há inscrições cadastradas</label></p>
                        <%} else {%>
                        <table id="data_table" class="table table-hover text-center">
                            <thead>
                                <tr>
                                    <th>Nome do inscrito</th>
                                    <th>Tipo</th>
                                    <th>Atividades</th>
                                    <th>Status do Pagamento</th>
                                    <th>Editar</th>
                                    <th>Excluir</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Inscricao i : inscricoesNaAtividade) {%>
                                <tr>
                                    <td><%= i.getParticipante().getUsuario().getNome()%></td>
                                    <td><%= i.getModalidade().getTipo()%></td>
                                    <td>
                                        <%for (Atividade at : i.getAtividades()) {%>
                                        <a href="../ServletCentral?comando=CmdListarInscritosEmAtividade&aId=<%=at.getId()%>"><%=at.getNome()%></a>
                                        <%}%>
                                    </td>
                                    <%
                                        if (i.isConfirmada()) {
                                            estado = "Efetuado";
                                        } else {
                                            estado = "Não Efetuado";
                                        }
                                    %>
                                    <td><%=estado%></td>
                                    <td><a href="../ServletCentral?comando=CmdBuscarInscricao&id=<%=i.getId()%>" title="Visualizar/Editar"><span class="text-uppercase label label-success">Visualizar / Editar</span></a></td>
                                    <td><a href="../ServletCentral?comando=CmdOrganExcluirInscricao&iId=<%=i.getId()%>" onclick="return confirm('ATENÇÃO: Se você excluir uma inscrição que já foi paga ela não estará mais no sistema e não será possível recuperar a quantia paga. Também poderá causar a perda das vagas e dos certificados relacionados com esta inscrição. Tem certeza que dejesa excluir esta inscrição?');" title="Excluir"><span class="text-uppercase label label-danger">Excluir Inscrição</span></a></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                        <%}%>
                    </div>
                </div>
            </div>
            <a href="../ServletCentral?comando=CmdGerenciarInscricoes&cod_evento=<%= e.getId()%>" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
        </div>
        <div id="footer"></div>
    </div>
</body>
</html>
