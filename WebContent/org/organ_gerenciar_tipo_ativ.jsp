<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="br.ufc.pet.entity.TipoAtividade, br.ufc.pet.util.UtilSeven" %>
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
        <script type="text/javascript"  language="javascript" src="../Script.js"></script>   
    </head>
    <body>
        <%            br.ufc.pet.entity.Evento e = (br.ufc.pet.entity.Evento) session.getAttribute("evento");
            ArrayList<TipoAtividade> tipoAtivs = (ArrayList<TipoAtividade>) session.getAttribute("tiposAtividades");
            session.removeAttribute("tipoAtividade");
        %>
        <div id="container">
                <%-- Incluindo o Menu --%>
                <%@include file="organ_menu.jsp"%>
                
            <div id="content">
                <h1 class="titulo">Gerenciar os Tipos de Atividades do evento<br/><%=e.getNome()%></h1>
                <%@include file="/error.jsp"%>
                    <div>
                        <table id="data_table" class="table table-hover text-center">
                            <%if (tipoAtivs == null || tipoAtivs.size() == 0) {%>
                            <div class="alert alert-warning text-center" role="alert">Sem Tipos de Atividade Cadastrados no momento</div>
                                <%} else {%>
                            <thead>
                                <tr>                        
                                    <th>Nome</th>
                                    <th>Alterar</th>
                                    <th>Excluir</th>
                                </tr>
                            </thead>
                            <tbody>

                                <%for (TipoAtividade ta : tipoAtivs) {%>
                                <tr>
                                    <td ><label><%=ta.getNome()%></label></td>
                                    <td><a href="../ServletCentral?comando=CmdEditarTipoAtividade&ta_id=<%=ta.getId()%>" title="Alterar Tipo Atividade"><span class="text-uppercase label label-success">Alterar</span></a></th>
                                    <td><a href="../ServletCentral?comando=CmdExcluirTipoAtividade&ta_id=<%=ta.getId()%>" title="Excluir Tipo Atividade" onclick="return confirm('Tem certeza que deseja excluir esse Tipo de Atividade?')"><span class="text-uppercase label label-danger">Excluir</span></a></td>
                                </tr>

                                <%}%>
                            </tbody>
                            <%}%>

                        </table>
                    </div>
                <a href="../ServletCentral?comando=CmdListarAtividades" title="" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
                <a data-toggle="tooltip" href="organ_add_tipo_ativ.jsp" title="Adicionar um novo Tipo de Atividade" class="btn btn-default">Adicionar Tipo Atividade</a>
            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
