<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="br.ufc.pet.entity.Evento"%>
<%@page import="br.ufc.pet.util.UtilSeven"%>
<%@page import="br.ufc.pet.entity.Atividade"%>
<%@page import="br.ufc.pet.entity.Organizador"%>
<%@page import="br.ufc.pet.entity.Participante"%>
<%@page import="br.ufc.pet.entity.Administrador"%>
<%@page import="br.ufc.pet.entity.Horario"%>
<%@page import="br.ufc.pet.services.HorarioService"%>
<%@page import="br.ufc.pet.entity.ResponsavelAtividade"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html  ng-app="app">
    <%
        Evento evento = (Evento) session.getAttribute("evento");

    %>

    <head>
        <script type="text/javascript"  language="javascript" src="Script.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="imagens/favicon.png" type="image/x-icon"/>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>

	<!-- AJS Calendar -->
        <script src="angularjs/jquery-3.2.1.min.js"></script>
        <script src="angularjs/moment.js"></script>
        <script src="angularjs/fullcalendar.min.js"></script>
        <script src="angularjs/gcal.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="angularjs/angular.min.js"></script>
        <script src="angularjs/calendar.js"></script>
        <script src="angularjs/ui-bootstrap-tpls-2.5.0.min.js"></script>
        <script src="angularjs/controllers/calendarCtrl.js"></script>
    </head>
    <body ng-controller="MyController">
        
        <div id="container-visualizar">
            <%@include file="menu_index.jsp"%>

                <!--<div id="content_left"></div>-->
                <h1 class="title-register">Programação <br/> <%=evento.getNome()%></h1>
                <%                    
                    ArrayList<Horario> horarios = HorarioService.getHorariosByEvento(evento.getId());
                   
                    String[] arrS = new String[horarios.size()];
                    for(int i = 0; i < horarios.size(); i++){
                        arrS[i] = horarios.get(i).exibirFormatoSimples();
                    }
                    Date diaComum = new Date(0L);
                    int count = 0;
                    for (Horario h : horarios) {
                        for (Atividade a : evento.getAtividades()) {
                         
                            if (!h.getDia().equals(diaComum)) {
                                diaComum = h.getDia();
                                if (count > 0) {%>
                <!-- Table utilizado para balancear as tabelas, isso é sim uma gambiarra criado por um cara e que a gente não soube resolver-->
                <table class="hidden">
                <%}%>
                
                <div class="hidden">
                <div class="well well-sm text-center"><%=UtilSeven.treatToLongString(h.getDia())%></div>
                <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Horário</th>
                            <th>Nome da Atividade</th>
                            <th>Tipo de Atividade</th>
                            <th>Local</th>
                            <th>Ministrante</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%count++;
                        }
                        if (a.temHorario(h.getId())) {%>
                        <tr>
                            <td class="hidden"><%=UtilSeven.formtStringDate(h.getDia())%></td>
                            <td class="hours"><%=h.exibirFormatoSimples()%></td>
                            <% String nome = a.getNome();%>
                            <td class="name"><%=nome%></td>
                        
                            <% String tipo = a.getTipo().getNome();%>
                            <td><%=tipo%></td>
                            <% String local = a.getLocal();%>
                            <td class="local"><%=local%></td>
                            <%
                                String nomePessoal = "";
                                for (ResponsavelAtividade r : a.getResponsaveis()) {
                                nomePessoal += r.getUsuario().getNome();
                            }%>
                            <td class="people"><%=nomePessoal%></td>
                        </tr>
                        <%             }
                            }
                        }
                        if (horarios.size() > 0) {%>
                    </tbody>
                </table>
                </div>
                <%}%>
                </table>
                <% if(count > 0) {%>
                   </div>
                <%}%>
            <div style="margin-top:50px ;width:100%;" align="center" class="calendario">
                <div ui-calendar="uiConfig.calendar" class="span8 calendar" ng-model="eventSources" calendar="myCalendar"></div> 
            </div>
            </br>
           <div align="left"><a href="javascript:history.back();" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a></div>
    
        </div>
        <%@include file="footer.jsp" %>
      
    </body>
</html>
