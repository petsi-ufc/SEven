<%@page import="java.util.ArrayList"%>
<%@page import="br.ufc.pet.evento.ModalidadeInscricao"%>
<%@page import="br.ufc.pet.evento.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
        <script type="text/javascript" src="../Script.js"> </script>
    </head>
    <body>
        <%            Evento e = (Evento) session.getAttribute("evento");
            ArrayList<ModalidadeInscricao> modalidades = (ArrayList<ModalidadeInscricao>) session.getAttribute("modalidades");
        %>
        <div id="container">
                <%-- Incluindo o Menu --%>
                <%@include file="organ_menu.jsp"%>
                
            <div id="content">
                <h1 class="titulo">Gerenciar Modalidades de Inscriçao do evento<br/> <%= e.getNome()%></h1>
                <%@include file="/error.jsp"%>
                <div>

                    <table id="data_table" class="table table-hover text-center">
                        <%if (modalidades == null || modalidades.size() == 0) {%>
                        <div class="alert alert-warning text-center" role="alert">Sem Modalidades Cadastradas no momento.</div>
                            <%} else {%>
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Alterar</th>
                                <th>Excluir</th>
                            </tr>                   
                        </thead>
                        <tbody>
                            <%for (ModalidadeInscricao modalidade : modalidades) {%>
                            <tr>
                                <td ><label><%=modalidade.getTipo()%></label></td>
                                <td><a href="../ServletCentral?comando=CmdAtualizarModalidade&mod_id=<%=modalidade.getId()%>" title="Alterar Tipo Modalidade"><span class="text-uppercase label label-success">Alterar</span></a></td>
                                <td><a href="../ServletCentral?comando=CmdExcluirModalidade&mod_id=<%=modalidade.getId()%>" title="Excluir Tipo Modalidade" onclick="return confirm('Tem certeza que deseja excluir essa de Modalidade?')"><span class="text-uppercase label label-danger">Excluir</span></a></td>
                            </tr>
                            <%}%>
                        </tbody>
                        <%}%>
                    </table>
                </div>
                <a href="" title="" onclick="history.back(); return false;" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
                <a data-toggle="tooltip" href="organ_add_modalidade.jsp" title="Adicionar uma Nova Modalidade" class="btn btn-default">Adicionar Modalidade</a>
            </div>
            <div class="footer-top">        
                <%@include file="../footer.jsp" %>
            </div> 
        </div>
    </body>
</html>
