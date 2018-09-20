<%@page import="br.ufc.pet.entity.Participante"%>
<%
    java.util.ArrayList<br.ufc.pet.entity.Evento> eventos = (java.util.ArrayList<br.ufc.pet.entity.Evento>) session.getAttribute("eventosAbertos");
    Participante part = (Participante) session.getAttribute("user");
    String nomeSaudacao = part.getUsuario().getNome().split(" ")[0];
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>        
        <title>SEven</title>
        <script language="javascript" src="../jquery/jquery-1.10.2.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>

    </head>
    <body>
        <div id="container">

                <%-- Incluindo Menu --%>
        <%@include file="part_menu.jsp" %> 

         
        <div id="content">
                    <%@include file="/error.jsp" %>
          <div class="row">   
            <div class="col-lg-6">               
               <div class="panel">   
                <div class="col-lg-12 jumbotron">
                    <h1 class="text-center text-bold">Olá, <%= nomeSaudacao%></h1>
                    <h5 class="text-center">Esta é a página inicial do Participante. Quando desejar retornar a esta página, 
                        clique na opção <span class="label label-default text-uppercase"><em>Home</em></span> no menu acima.</h><br/>
                </div>
               </div>              
            </div>
                <div class="container col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <h1 style="text-align: center; margin-top: 8.5px; font-size: 18px;"class="titulo">Tentando se inscrever em um evento? </h1>
                    <p class="text-center">Utilize o menu no topo da página ou solicite rapidamente uma nova inscrição clicando nos links rápidos abaixo!</p>
                    <%if (eventos == null || eventos.size() == 0) {%>
                     <div class="alert alert-warning text-center" role="alert">Sem eventos abertos no momento!</div>
                    <%} else {%>
                    <table id="data_table" class="table table-hover">
                        <thead>
                            <tr>
                                <th>Sigla</th>
                                <th>Evento</th>
                                <th>Programação</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (br.ufc.pet.entity.Evento e : eventos) {%>
                            <tr class="text-center">
                                <td> <%= e.getSigla()%> </td>
                                <td> <a href="../ServletCentral?comando=CmdSelecionarEvento&id=<%=e.getId()%>"><%= e.getNome()%></a>  </td>
                                <td> <a href="../ServletCentral?comando=CmdVisualizarProgramacao&id=<%=e.getId()%>" title="Programacao" >Visualizar</a> </td>
                            </tr>
                            <% }%>
                        </tbody>
                        <% }%>

                      </table>
                    <br/>
                </div>
              </div>          
            </div>
           </div>   
              <div class="footer-top"> <%@include file="../footer.jsp" %></div>
    </body>
</html>
