<%@page import="br.ufc.pet.entity.Inscricao"%>
<%@page import="br.ufc.pet.entity.Participante"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.lang.Exception" %>
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
        <script type="text/javascript">
            function checkAllCheckboxes() {
                var aux = 1;
                for (var i = 0; i < document.formVerificarAluno.elements.length; i++) {
                    if (document.formVerificarAluno.elements[i].type == 'checkbox') {
                        aux = document.formVerificarAluno.elements[i].checked;
                        break;
                    }
                }
                for (var i = 0; i < document.formVerificarAluno.elements.length; i++) {
                    if (document.formVerificarAluno.elements[i].type == 'checkbox') {
                        document.formVerificarAluno.elements[i].checked = aux;
                    }
                }
            }
        </script>
    </head>


    <body>
        <%            br.ufc.pet.entity.Evento e = (br.ufc.pet.entity.Evento) session.getAttribute("evento");
            Organizador organizador = (Organizador) session.getAttribute("user");
            ArrayList<Atividade> ats = e.getAtividades();

            ArrayList<Participante> parts = (ArrayList) session.getAttribute("participantes");
            Long idAtividade = 0L;
            try {
                idAtividade = (Long) session.getAttribute("ativ_id");

            } catch (Exception e1) {
                System.out.println("Exception :: NULL");
                idAtividade = 0L;
            }

        %>
        <div id="container">
            <div id="top">
                <%-- Incluindo o Menu --%>
                <%@include file="organ_menu.jsp"%>
            </div>
            <div id="content">
                <h1 class="titulo">Selecionar Participantes para Liberar Certificado</h1>
                <%@include file="/error.jsp" %>
                <%if (parts == null || parts.size() == 0) {%>
                <center><label>Sem participantes quites nesta atividade</label></center>
                    <%} else {%>

                <form name="formVerificarAluno" action="../ServletCentral?comando=CmdConfirmarLiberacaoCertificado" class="confirmaCertificado" method="post" onsubmit="return confirm('Deseja realmente liberar os certificados para os participantes selecionados?');" >
                    <table id="data_table" class="table table-hover text-center space-top" id="checkTable">
                        <thead>
                            <tr>
                                <th><input type="checkbox" onclick="checkAllCheckboxes();" /></th>
                                <th>Nome</th>
                                <th>Instituição</th>
                                <th>E-mail</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Participante p : parts) {%>
                            <tr>
                                <td><input type="checkbox" name="verificaAluno" value="<%=p.getInscricaoByEvento(e.getId()).getId()%>" /></td>
                                <td><%=p.getUsuario().getNome()%></td>
                                <td><%=p.getUsuario().getInstituicao()%></td>
                                <td><%=p.getUsuario().getEmail()%></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                    <input type="hidden" name="ativ_id" value="<%=idAtividade%>" />
                    <a href="" title="" onclick="history.back(); return false;" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
                    <input data-toggle="tooltip" title="Liberar Certificados" type="submit" value="Liberar" class="btn btn-default pull-right" />
                </form>
                <% }%>
            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
