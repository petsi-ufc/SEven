<%@page import="br.ufc.pet.entity.Atividade"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.ufc.pet.util.UtilSeven"%>
<%@page import="br.ufc.pet.entity.Evento"%>
<%@page import="br.ufc.pet.entity.Inscricao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
    br.ufc.pet.services.EventoService es = new br.ufc.pet.services.EventoService();
    java.util.ArrayList<br.ufc.pet.entity.Evento> eventos = es.buscarEventosAbertos();

    Inscricao inscricao = (Inscricao) session.getAttribute("dados_inscricao");
    session.removeAttribute("dados_inscricao");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="imagens/favicon.png" type="image/x-icon"/>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>
        <script language="javascript" src="jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script type="text/javascript" src="jquery/jquery.dataTables.js"></script>
        <script type="text/javascript" src="jquery/initDataTable.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript"  language="javascript" src="Script.js"></script>
    </head>
    <body>
        <div id="container-validate">
            <%@include file="menu_index.jsp"%>
                <% if (inscricao == null) { %>
              
                
                        <div class="panel panel-default">
                            <div class="panel-heading text-center">Sobre a validação</div>
                            <div class="panel-body">                              
                                <div class="row">
                                   <div class="col-lg-12">  
                                    <div class="text-center-validate">
                                        Para validar um documento gerado pelo SEven, Informe o código de validação, localizado na parte inferior do documento.
                                    </div>
                                   </div>    
                                </div>
                              
                            </div>
                        </div>
                        <form action="ServletCentral" method="post">
                            <input type="hidden" name="comando" value="CmdValidarDocumento" />
                           <div class="form-group">
                                   <label for="">Código de Verificação</label>
                                <input data-toggle="tooltip" title="Código de Verificação" type="text" class="form-control" id="codigo" placeholder="Digite o Código de validação" name="codigo" />
                           </div>
                             <%@include file="error.jsp" %>
                            <p class="text-center"><button data-toggle="tooltip" title="Validar Documento" type="submit" class="btn btn-default">Validar Documento</button></p>
                        </form>
          
                <% } else {%>

                <center><h1 class="titulo">Informações do documento</h1></center>

                <div  class="validacaoCertificado">
                    <div id="content_left">
                        <h3 class="titulo">Proprietário do documento: </h3>
                        <span class="docInfo">
                            <%=inscricao.getParticipante().getUsuario().getNome()%>
                        </span>
                        <br><br>
                                <h3 class="titulo">Evento: </h3>
                                <span class="docInfo">
                                    <%=(inscricao.getEvento().getNome() + " - " + inscricao.getEvento().getSigla())%>
                                </span>
                                <br><br>
                                        <h3 class="titulo">Período do evento: </h3>
                                        <span class="docInfo">
                                            <%=(UtilSeven.treatToString(inscricao.getEvento().getInicioPeriodoEvento()) + " até " + UtilSeven.treatToString(inscricao.getEvento().getFimPeriodoEvento()))%>
                                        </span>
                                        <br><br>
                                                </div>
                                                <div id="content_right">
                                                    <center><h3  class="titulo" style="text-indent: 0px;">Atividades realizadas no evento: </h3></center>
                                                    <table id="data_table" style="margin-top: 7px;">
                                                        <thead>
                                                            <tr>
                                                                <th>Nome</th>
                                                                <th>Carga Horária</th>
                                                                <th>Local</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <% for (Atividade a : inscricao.getAtividades()) {%>
                                                            <tr>
                                                                <td><%=a.getNome()%></td>
                                                                <td><%=a.getCargaHoraria()%></td>
                                                                <td><%=a.getLocal()%></td>
                                                            </tr>
                                                            <% } %>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                </div>


                                                <% }%>
                                                </div>
                                                <%@include file="footer.jsp" %>
                                          
                                                </body>
                                                </html>
