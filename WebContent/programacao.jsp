<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    br.ufc.pet.services.EventoService es = new br.ufc.pet.services.EventoService();
    java.util.ArrayList<br.ufc.pet.entity.Evento> eventos = es.buscarEventosAbertos();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilo.css" rel="stylesheet" type="text/css" />
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="imagens/favicon.png" type="image/x-icon"/>
        <title>SEven</title>
        <script language="javascript" src="jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script type="text/javascript" src="jquery/jquery.dataTables.js"></script>
        <script type="text/javascript" src="jquery/initDataTable.js"></script>
    </head>
    <body>

        <div id="container">
            <%@include file="menu_index.jsp"%>
        </div>

        <div id="content">
            <br><br>
            <center>
                <h1 class="titulo">Eventos Abertos para Inscrições</h1>


                <div style="width: 80%;">


                    <table id="data_table" >
                        <thead>
                            <tr>
                                <th>Evento</th>
                                <th>Sigla</th>
                                <th>Programação</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%if (eventos != null) {%>
                            <% for (br.ufc.pet.entity.Evento e : eventos) {%>
                            <tr>
                                <td> <%= e.getNome()%>  </td>
                                <td> <%= e.getSigla()%> </td>
                                <td> <a href="ServletCentral?comando=CmdVisualizarProgramacao&id=<%=e.getId()%>" title="Programacao" > Visualizar</a> </td>
                            </tr>
                            <% }%>
                            <% }%>
                        </tbody>
                    </table>
                </div>

            </center>
        </div><br/><br/><br/><br/><br/>
        <%@include file="footer.jsp" %>
    </body>
</html>
