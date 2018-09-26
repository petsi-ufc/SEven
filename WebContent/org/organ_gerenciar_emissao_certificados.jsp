<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="br.ufc.pet.entity.Atividade,br.ufc.pet.entity.Organizador,br.ufc.pet.entity.Organizacao,br.ufc.pet.entity.ResponsavelAtividade" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <head>      
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>
        <script type="text/javascript" src="../jquery/jquery.dataTables.js"></script>
        <script type="text/javascript" src="../jquery/initDataTable.js"></script>
        <script language="javascript" src="../jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="../jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../Script.js"> </script> 
    </head>
    <body>
        <%            br.ufc.pet.entity.Evento e = (br.ufc.pet.entity.Evento) session.getAttribute("evento");
            Organizador organizador = (Organizador) session.getAttribute("user");
            ArrayList<Atividade> ats = e.getAtividades();
        %>
        <div id="container">
            <div id="top">
                <%-- Incluindo o Menu --%>
                <%@include file="organ_menu.jsp"%>
            </div>
            <div id="content">
                <h1 class="titulo">Gerenciar Emissão de Certificados por Atividades do evento <br/><span style="color: black"><%=e.getNome()%></span></h1>
                <%@include file="/error.jsp" %>
                <% if (organizador.recuperarOrganizacaoByEvendoId(e.getId()).isManterAtividade()) {%>


                <%}%>
                <%if (ats == null || ats.size() == 0) {%>
                <center><label>Sem atividades no momento</label></center>
                    <%} else {%>
                <table id="data_table" class="table table-hover text-center space-top">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Tipo</th>
                            <th>Capacidade</th>
                            <th>Local</th>
                            <th>Responsável</th>
                            <th>Liberar Certificados</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Atividade a : ats) {%>
                        <tr>
                            <td><%=a.getNome()%></td>
                            <td><%=a.getTipo().getNome()%></td>
                            <td><%=a.getVagas()%></td>
                            <td><%=a.getLocal()%></td>
                            <% java.lang.StringBuffer sb = new java.lang.StringBuffer("");
                                for (ResponsavelAtividade ra : a.getResponsaveis()) {
                                    sb.append(ra.getUsuario().getNome());
                                    sb.append("<br>");
                                }%>
                            <td><%=sb.toString()%></td>
                            <td><a data-toggle="tooltip" href="../ServletCentral?comando=CmdGerenciarLiberacaoCertificadoAtividade&ativ_id=<%=a.getId()%>" title="Liberar Certificados" class="btn btn-default">Liberar</a></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <% }%>
            <a href="" title="" onclick="history.back(); return false;" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
