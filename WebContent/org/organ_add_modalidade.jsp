<%@page import="br.ufc.pet.entity.PrecoAtividade"%>
<%@page import="br.ufc.pet.entity.ModalidadeInscricao"%>
<%@page import="br.ufc.pet.util.UtilSeven"%>
<%@page import="br.ufc.pet.entity.TipoAtividade"%>
<%@page import=" br.ufc.pet.services.TipoAtividadeService"%>

<%@page import="java.util.ArrayList"%>
<%@page import="br.ufc.pet.entity.Evento"%>
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
        <script type="text/javascript" src="../Script.js"></script>
    </head>
    <body>
        <%            Evento e = (Evento) session.getAttribute("evento");
            ArrayList<TipoAtividade> tipos = TipoAtividadeService.getTiposDeAtividadeByEventoId(e.getId());
            ModalidadeInscricao modalidade = (ModalidadeInscricao) session.getAttribute("atualiazar_modalidade");
            session.removeAttribute("atualiazar_modalidade");
            Double valor = 0.0;
            String nome = "";
            String id = "";
            if (modalidade != null) {
                nome = modalidade.getTipo();
                id = modalidade.getId().toString();
            }
        %>
        <div id="container">
                <%-- Incluindo o Menu --%>
                <%@include file="organ_menu.jsp" %>
                
            <div id="content">
               
                <h1 class="titulo">Adicionar Modalidade</h1>
                <%@include file="/error.jsp" %>
                <form action="../ServletCentral?comando=CmdAdicionarModalidade&id_atualizar=<%=id%>" method="post">
                    <div class="form-group"><input data-toggle="tooltip" title="Nome da Modalidade" type="text" id="nomeModalidade" name="nomeModalidade" value="<%=nome%>" placeholder="Modalidade" class="form-control"/></div>
                    <table id="data_table" class="table table-hover text-center form-group">
                        <thead>
                            <tr>
                                <th>Tipo de Atividade</th>
                                <th>Preço de inscrição com esta modalidade</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (TipoAtividade tp : tipos) {%>
                            <tr>
                                <%if (modalidade != null) {
                                        for (PrecoAtividade p : modalidade.getPrecoAtividades()) {
                                            if (p.getTipoAtividadeId() == tp.getId()) {
                                                valor = p.getValor();
                                                break;
                                            }
                                        }
                                    }%>
                                <td><%=tp.getNome()%></td>
                                <%if (e.isGratuito()) {%>
                                <td><div class="form-group"><input type="text" name="preco_<%=tp.getId()%>" value="<%=valor%>" size="10"  disabled="disabled" class="form-control"/></div></td>
                                    <%} else {%>
                                <td><div class="form-group"><input data-toggle="tooltip" title="Preço da Inscrição desta Modalidade" type="text" name="preco_<%=tp.getId()%>" value="<%=valor%>" size="10" class="form-control "/></div></td>
                                    <%}%>
                            </tr>
                            <%valor = 0.0;
                            }%>
                        </tbody>
                    </table>
                    <a href="" title="" onclick="history.back();return false;" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
                    <input data-toggle="tooltip" title="Confirmar Modalidade" type="submit" value="Confirmar" class="btn btn-default" onclick="return confirm('Deseja realmente enviar esses dados?')" />
                </form>
            </div>
            <div class="footer-top">        
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
