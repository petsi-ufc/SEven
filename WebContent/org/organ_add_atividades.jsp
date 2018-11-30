<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList,java.util.List, br.ufc.pet.entity.TipoAtividade,br.ufc.pet.entity.ResponsavelAtividade,br.ufc.pet.entity.Horario,br.ufc.pet.entity.Atividade,br.ufc.pet.entity.Evento" %>
<%@page import="br.ufc.pet.services.TipoAtividadeService"%>
<%@page import="br.ufc.pet.services.HorarioService"%>

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
        <script type="text/javascript">
            function forceSubmitListarRelatoriosAtividades(formName) {
                document.forms[formName].action = "organ_add_responsavel.jsp";
                document.forms[formName].submit();
            }
        </script>
        <style type="text/css">
            #content form.cadastro input {
                width: 200px;
            }
        </style>
    </head>
    <body>
        <%            Evento evento = (Evento) session.getAttribute("evento");
            ArrayList<TipoAtividade> tas = TipoAtividadeService.getTiposDeAtividadeByEventoId(evento.getId());
            ArrayList<ResponsavelAtividade> resps = (ArrayList<ResponsavelAtividade>) session.getAttribute("responsaveisEscolhidos");
            Atividade a = (Atividade) session.getAttribute("atividade");
            Atividade ativTemp = (Atividade) session.getAttribute("atividadeTemp");
            session.removeAttribute("atividadeTemp");
            String nome = "";
            String local = "";
            String vagas = "";

            if (a != null) {
                nome = a.getNome();
                local = a.getLocal();

                vagas = "" + a.getVagas();
            } else if (ativTemp != null) {
                nome = ativTemp.getNome();
                local = ativTemp.getLocal();
                vagas = "" + ativTemp.getVagas();
            }
        %>
        <div id="container">
            <%-- Incluindo o Menu --%>
            <%@include file="organ_menu.jsp" %>

            <div id="content">
                <h1 class="titulo">Atividade</h1>
                <%@include file="/error.jsp" %>
                <form name="formAddAtividade" action="../ServletCentral?comando=CmdAdicionarAtividade" method="post">

                    <div class="panel panel-default space-top">
                        <div class="panel-cor panel-heading text-center">Responsáveis da atividade</div>
                        <div class="panel-body text-center">
                            <%if (resps != null && !resps.isEmpty()) {%>
                            <table id="data_table" class="table table-hover">                           
                                <thead>
                                    <tr>
                                        <th>Nome</th>
                                        <th>Alterar</th>
                                        <th>Remover</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        for (ResponsavelAtividade resp : resps) {%>
                                    <tr class="text-center">
                                        <td><label><%=resp.getUsuario().getNome()%></label></td>
                                        <td><a href="../ServletCentral?comando=CmdSelecionarResponsavelEdicao&usuario_id=<%=resp.getUsuario().getId()%>"><span class="text-uppercase label label-success">Editar</span></a></td>
                                        <td><a href="../ServletCentral?comando=CmdRemoverResponsavelAtividade&usuario_id=<%=resp.getUsuario().getId()%>" onclick="return confirm('Tem certeza que deseja excluir esse responsavel?')"><span class="text-uppercase label label-danger">Excluir</span></a></td>
                                    </tr>
                                    <%}
                                    %>
                                </tbody>
                            </table>
                            <%} else {%>
                            <p>Nenhum responsável no momento</p>
                            <%}%>
                        </div>
                    </div>

                    <a data-toggle="tooltip" title="Adicionar um Responsável" href="#" onclick="forceSubmitListarRelatoriosAtividades('formAddAtividade')" class="btn btn-default pull-right">Adicionar Responsável</a><br/><br/>
                
                    <div class="panel panel-default space-top">
                        <div class="panel-cor panel-heading text-center">Dados da atividade</div>
                        <div class="panel-body">                            
                            <div class="col-lg-9 space-top">
                                <div class="form-group">
                                    <label>Nome:</label>
                                    <input data-toggle="tooltip" title="Nome da Atividade" type="text" name="nome_atividade" value="<%=nome%>" class="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label>Local:</label>
                                    <input data-toggle="tooltip" title="Local da Atividade" type="text" name="local" value="<%=local%>" class="form-control"/>
                                </div>
                            </div>  
                             <div class="col-lg-2 space-top">    
                                <div class="form-group">
                                    <label>N° de Vagas:</label><br />
                                    <input data-toggle="tooltip" title="Quantidade de Vagas Disponibilizadas" type="number" min="1" max="1000" onkeypress="return validaNumerosSilencioso(event)" name="vagas" value="<%=vagas%>" class="form-control"/>
                                </div>
                             </div>
                             <div class="col-lg-3">       
                                <label>Aceitar inscrições?</label><br/>
                                <div class="radio-inline">
                                    <% if (a == null) {%>
                                     <label class="radio-inline">
                                        <input type="radio" name="inscritivel" value="SIM" class="radio" checked="checked"/>Sim
                                     </label>
                                     <label class="radio-inline">
                                        <input type="radio" name="inscritivel" value="NAO" class="radio"/>Não
                                     </label>
                                     
                                    <%} else {
                                        String checkedSIM = "";
                                        String checkedNAO = "";
                                        if (a.isAceitaInscricao() == true) {
                                            checkedSIM = "checked=\"checked\"";
                                        } else {
                                            checkedNAO = "checked=\"checked\"";
                                        }
                                    %>
                                    <label class="radio-inline">
                                        <input type="radio" name="inscritivel" value="SIM" class="radio" <%=checkedSIM%>/>Sim
                                    </label >
                                    <label class="radio-inline">
                                       <input type="radio" name="inscritivel" value="NAO" class="radio"  <%=checkedNAO%>/>Não
                                    </label>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                    </div>
                   </div>
                                
                    <div class="panel panel-default space-top">
                        <div class="panel-cor panel-heading text-center">Tipo da atividade</div>
                        <div class="panel-body">
                            <div class="col-lg-12 space-top">
                                <div>
                                    <% for (TipoAtividade ta : tas) {%>
                                    <% if (a != null && (a.getTipo().getId().compareTo(ta.getId()) == 0)) {%>
                                    <div class="radio">
                                        <label><input type="radio" name="tipo_id" value="<%=ta.getId()%>"  checked="checked"/><strong><%=ta.getNome()%></strong></label>
                                    </div>
                                        <%} else {%>
                                        <div class="radio radiosCheck" id="rdCurso">
                                            <label><input type="radio" name="tipo_id" value="<%=ta.getId()%>"/><strong><%=ta.getNome()%></strong></label>
                                        </div>
                                        <%}
                                            }%>
                                </div><br/>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default space-top">
                        <div class="panel-cor panel-heading text-center">Horários da atividade</div>
                        <div class="panel-body">
                            <div class="col-lg-12 space-top">
                                <table class="table table-hover text-center" id="checkTable">
                                    <thead> 
                                        <tr>
                                            <th>Marcar</th>
                                            <th>Horarios Disponiveis</th> 
                                        </tr> 
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<Horario> horarios = HorarioService.getHorariosByEvento(evento.getId());
                                            java.util.Collections.sort(horarios);
                                            for (Horario h1 : horarios) {%>

                                        <% String checked = "";
                                            if (a != null) {
                                                for (Horario h2 : a.getHorarios()) {
                                                    if (h1.isEqual(h2)) {
                                                        checked = "checked=\"checked\"";
                                                    }%>
                                        <%}%>
                                         <tr>
                                            <td>
                                                <input type="checkbox" name="cb_horario_<%=h1.getId()%>" id="chekbox" value="ON" <%=checked%> class="chk_box"/></td>
                                            <td><label > <%=h1.printHorario()%></label></td>
                                        </tr>
                                         <%} else {%>
                                        <tr>
                                            <td>
                                                <input type="checkbox" id="checkbox" name="cb_horario_<%=h1.getId()%>" value="ON" class="chk_box"/></td>
                                            <td><label><%=h1.printHorario()%></label></td>
                                        </tr>
                                        <%}
                                                        }%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <input data-toggle="tooltip" title="Confirmar Atividade" type="submit" value="Confirmar" class="btn btn-default pull-right" onclick="return confirm('Deseja realmente confirmar esses dados?')" />
                    <a href="" title="" onclick="history.back();return false;" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
                </form>
            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>