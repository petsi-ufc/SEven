<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, br.ufc.pet.entity.Participante,br.ufc.pet.entity.Evento,br.ufc.pet.entity.Atividade" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
            function forceSubmitListarRelatoriosAtividades(formName) {
                document.forms[formName].action = "../ServletCentral?comando=CmdListarParticipantes";
                document.forms[formName].submit();
            }
        </script>
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>
        <script language="javascript" src="../jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="../jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
    </head>
    <%--sComboPeriodo = "<select name=\"periodo\" onchange=\"forceSubmitCarregarTurmasPeriodo(\'form_avalicao\');\">";--%>
    <%  ArrayList<Participante> parts = (ArrayList<Participante>) session.getAttribute("listPartic");
        br.ufc.pet.services.AtividadeService ativService = new br.ufc.pet.services.AtividadeService();
        Evento ev = (Evento) session.getAttribute("evento");
        Long ativId = (Long) session.getAttribute("atividadeSelect");
        session.removeAttribute("atividadeSelect");

    %>
    <body>
        <div id="container">
            <%-- Incluindo o Menu --%>
            <%@include file="organ_menu.jsp" %>

            <div id="content">
                <form name="formListarAtividade" method="POST">
                    <label>Filtrar Por Atividade:</label>
                    <select name="ativEscolhida" class="form-control" onchange="forceSubmitListarRelatoriosAtividades('formListarAtividade')">
                        <option value="null" >Todas as inscricoes</option>
                        <% for (Atividade a : ativService.getAtividadesByEventoId(ev.getId())) {
                                    if (ativId != null && ativId.compareTo(a.getId()) == 0) {%>
                        <option selected="selected" value="<%= a.getId()%>"><%=a.getNome()%></option>
                        <%} else if (a.isAceitaInscricao()) {%>
                        <option value="<%= a.getId()%>" ><%=a.getNome()%></option>
                        <%}
                                }%>
                    </select>
                    <a href="../org/organ_listar_email_participantes.jsp" class="btn btn-default space-top">Ver apenas Email</a>
                </form>
                <% if (parts != null && !parts.isEmpty()) {%>
                <table id="data_table" class="table table-hover text-center space-top">
                    <thead>
                        <tr>
                            <th>Nº</th>
                            <th>Nome</th>
                            <th>Email</th>
                            <th>Inscricao Confirmada</th>
                            <th>Atividades</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (int i = 0; i < parts.size(); i++) {
                                    Participante p = parts.get(i);%>
                        <tr>
                            <td><%=i + 1%></td>
                            <td><%=p.getUsuario().getNome()%></td>
                            <td><%=p.getUsuario().getEmail()%></td>
                            <td><%=(p.getInscricoes().size() > 0) ? p.getInscricaoByEvento(ev.getId()).confimadaToString() : "0"%></td>
                            <td><%=(p.getInscricoes().size() > 0) ? p.getInscricaoByEvento(ev.getId()).getAtividades().size() : "0"%></td>
                        </tr>
                        <%}
                            } else {%>
                    <br/>
                    <div class="alert alert-warning text-center" role="alert">Nenhuma inscricao para essa atividade no momento</div>

                    <%}%>
                    </tbody>
                </table>
                <a href="" title="" onclick="history.back(); return false;" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>

</html>