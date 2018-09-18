<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.entity.Atividade" %>
<%@page import="br.ufc.pet.entity.Participante" %>
<%@page import="br.ufc.pet.entity.Horario" %>
<%@page import="br.ufc.pet.util.UtilSeven" %>

<%@page import="java.util.*"%>
<%@include file="../ErroAutenticacaoUser.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <%        Atividade at = (Atividade) session.getAttribute("atividade");
        ArrayList<Participante> parts = (ArrayList<Participante>) session.getAttribute("participantes");
        ArrayList<Horario> horarios = at.getHorarios();

        int horario = horarios.size();


    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>
        <script language="javascript" src="../jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="../jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>>
    </head>
    <body>
        <div id="container">
            <%-- Incluindo o Menu --%>
            <%@include file="organ_menu.jsp" %>

            <div id="content">
                <h1 class="titulo">Lista de Frequência</h1>

                <div class="panel panel-default center-block" style="width: 50%">
                    <div class="panel-heading text-center">Dados do evento: <%=at.getEvento().getNome()%> </div>
                    <div class="panel-body text-center"> 
                        <label>Cod:</label><%=at.getId()%><br/>
                        <label>Local:</label><%=at.getLocal()%><br/>
                        <label>Vagas:</label><%=at.getVagas()%><br/>
                        <label>Tipo:</label><%=at.getTipo().getNome()%><br/>
                        <label>Nome:</label><%=at.getNome()%><br/>
                        <%
                            for (int i = 0; i < horario; i++) {
                                String data = UtilSeven.treatToString(horarios.get(i).getDia());
                                int hInicial = horarios.get(i).getHoraInicial();
                                String horaInicial = "" + hInicial;
                                if (hInicial <= 9 && hInicial >= 0) {
                                    horaInicial = "0" + hInicial;
                                }
                                int hFinal = horarios.get(i).getHoraFinal();
                                String horaFinal = "" + hFinal;
                                if (hFinal <= 9 && hFinal >= 0) {
                                    horaFinal = "0" + hFinal;
                                }
                                int mInicial = horarios.get(i).getMinutoInicial();
                                String minInicial = "" + mInicial;
                                if (mInicial <= 9 && mInicial >= 0) {
                                    minInicial = "0" + mInicial;
                                }
                                int mFinal = horarios.get(i).getMinutoFinal();
                                String minFinal = "" + mFinal;
                                if (mFinal <= 9 && mFinal >= 0) {
                                    minFinal = "0" + minFinal;
                                }


                        %>
                        <label>Data:</label><%=data%><br/>
                        <label>Horário:</label><%=horaInicial + ":" + minInicial + " às " + horaFinal + ":" + minFinal%>
                        <%}%><br/>
                    </div>
                </div>

                <table id="data_table" class="extend table table-hover text-center">
                    <thead>
                        <tr>
                            <th width="50px">N°</th>
                            <th>Nome</th>

                        </tr>
                    </thead>
                    <tbody>
                        <%for (int i = 0; parts != null && i < parts.size(); i++) {%>
                        <tr>
                            <td><%=i + 1%></td>
                            <%
                                Participante p = parts.get(i);
                            %>
                            <td><%=p.getUsuario().getNome()%></td>
                        </tr>
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
