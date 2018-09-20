<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="br.ufc.pet.entity.Horario,br.ufc.pet.util.UtilSeven" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>
        <script language="javascript" src="../jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="../jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../Script.js"></script>
    </head>
    <body>
        <%            br.ufc.pet.entity.Evento e = (br.ufc.pet.entity.Evento) session.getAttribute("evento");
            ArrayList<Horario> horarios = (ArrayList<Horario>) session.getAttribute("horarios");
            session.removeAttribute("horario");
        %>
        <div id="container">
                <%-- Incluindo o Menu --%>
                <%@include file="organ_menu.jsp"%>
                
            <div id="content">     
                <h1 class="titulo">Gerenciar Horários para atividades do evento <%=e.getNome()%></h1>
                <%@include file="/error.jsp"%>
                <table id="data_table" class="table table-hover text-center">
                    <%if (horarios == null || horarios.size() == 0) {%>
                    <div class="alert alert-warning text-center" role="alert">Sem horarios no momento</div>
                        <%} else {%>
                    <thead>
                        <tr>
                            <th>Horário Inicial</th>
                            <th>Horário Final</th>                       
                            <th>Dia</th>
                            <th>Alterar</th> 
                            <th>Excluir</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Horario horario : horarios) {%>
                        <tr>
                            <td><label><%=String.format("%02d", horario.getHoraInicial()) + " : " + String.format("%02d", horario.getMinutoInicial())%></label></td>
                            <td><label><%=String.format("%02d", horario.getHoraFinal()) + " : " + String.format("%02d", horario.getMinutoFinal())%></label></td>
                            <td><label><%=UtilSeven.treatToLongString(horario.getDia())%></label></td>
                            <td><a href="../ServletCentral?comando=CmdEditarHorario&hor_id=<%=horario.getId()%>" title="Alterar Atividade"><span class="text-uppercase label label-success">Alterar</span></a></td>
                            <td><a href="../ServletCentral?comando=CmdExcluirHorario&hor_id=<%=horario.getId()%>" title="Excluir Horario" onclick="return confirm('Tem certeza que deseja excluir esse horario?')"><span class="text-uppercase label label-danger">Excluir</span></a></td>
                        </tr>
                        <%}%>
                    </tbody>
                    <% }%>

                </table>
                <a href="../ServletCentral?comando=CmdListarAtividades" title=""  class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
                <a data-toggle="tooltip" title="Adicionar um Novo Horário" href="organ_add_horario.jsp" class="btn btn-default">Adicionar Horario</a>
            </div>          
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
