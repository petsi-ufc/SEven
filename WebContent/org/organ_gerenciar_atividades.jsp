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
        <script language="javascript" src="../jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="../jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>

    </head>
    <body>
        <%            br.ufc.pet.entity.Evento e = (br.ufc.pet.entity.Evento) session.getAttribute("evento");
            Organizador organizador = (Organizador) session.getAttribute("user");
            ArrayList<Atividade> ats = e.getAtividades();
        %>
        <div id="container">
            <%-- Incluindo o Menu --%>
            <%@include file="organ_menu.jsp"%>
            <div id="content">
                <h1 class="titulo">Gerenciar as Atividades do evento<br/> <%=e.getNome()%></h1>
                 <%@include file="/error.jsp" %>
                <% if (organizador.recuperarOrganizacaoByEvendoId(e.getId()).isManterAtividade()) {%>
                <div style="margin-top: 65px;">
                <div class="col-lg-6">
                    <div class="panel panel-default space-top">
                        <div class="panel-cor panel-heading text-center">Gerenciar período de inscrição e do evento</div>
                        <div class="panel-body text-center">
                            <label><a href="organ_periodos_inscricao_e_evento.jsp" title="Gerenciar Periodo de Inscricao e do Evento">Gerenciar os periodos da inscrição e evento</a></label>
                        </div>
                    </div>
                    <div class="panel panel-default space-top">
                        <div class="panel-cor panel-heading text-center">Gerenciar os horarios que estarão disponíveis às atividades</div>
                        <div class="panel-body text-center">
                            <label><a href="../ServletCentral?comando=CmdListarHorarios" title="Gerencia atributos do Horario">Gerenciar os Horarios que estarão disponíveis às atividades</a></label>
                        </div>
                    </div>
                    <div class="panel panel-default space-top">
                        <div class="panel-cor panel-heading text-center">Gerenciamento dos tipos de atividade</div>
                        <div class="panel-body text-center">
                            <label><a href="../ServletCentral?comando=CmdListarTipoAtividade" title="Gerencia Tipo de Atividade">Gerenciar os Tipos de Atividades que o evento possui</a></label>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 ">
                    <div class="panel panel-default space-top">
                        <div class="panel-cor panel-heading text-center">Gerenciamento das modalidades de inscrição no evento</div>
                        <div class="panel-body text-center">
                            <label><a href="../ServletCentral?comando=CmdListarTipoModalidade" title="Insere uma nova modalidade de Inscrição ao evento">Gerenciar as Modalidades de inscrição deste evento</a></label>
                        </div>
                    </div>
                    <div class="panel panel-default space-top">
                        <div class="panel-cor panel-heading text-center">Gerenciamento de atividades</div>
                        <div class="panel-body text-center">
                            <label><a href="../ServletCentral?comando=CmdMontarPaginaAdicaoAtividade" title="Adicionar uma nova Atividade">Adicionar Atividade</a></label>
                        </div>
                    </div>
                    <div class="panel panel-default space-top">
                        <div class="panel-cor panel-heading text-center">Gerenciar emissão de certificados</div>
                        <div class="panel-body text-center">
                            <label><a href="../ServletCentral?comando=CmdGerenciarUploadCertificados" title="Gerenciar upload de certificados">Gerenciar upload de certificados</a></label><br/>
                            <label><a href="../ServletCentral?comando=CmdGerenciarEmissaoCertificados" title="Gerenciar emissão de certificados">Gerenciar emissão de certificados</a></label>
                        </div>
                    </div>
                </div>
                </div>
                <%}%>
                <%if (ats == null || ats.size() == 0) {%>
                <div style="margin-top: -50px; width: 96.5%; margin-left: 15px;"><div class="alert alert-warning text-center" role="alert">Sem atividades no momento</div></div>
                    <%} else {%>
                <table id="data_table" class="table table-hover">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Tipo</th>
                            <th>Capacidade</th>
                            <th>Local</th>
                            <th>Responsável</th>
                            <th>Alterar</th>
                            <th>Excluir</th>
                            <th>Visualizar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Atividade a : ats) {%>
                        <tr class="text-center">
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
                            <% if (organizador.recuperarOrganizacaoByEvendoId(e.getId()).isManterAtividade()) {%>
                            <td><a href="../ServletCentral?comando=CmdEditarAtividade&ativ_id=<%=a.getId()%>" title="Alterar Atividade"><span class="text-uppercase label label-success">Alterar</span></a></td> 
                            <td><a href="../ServletCentral?comando=CmdExcluirAtividade&ativ_id=<%=a.getId()%>" title="Excluir Atividade" onclick="return confirm('Tem certeza que deseja excluir essa atividade?')"><span class="text-uppercase label label-danger">Excluir</span></a></td>
                            <% } else {%>
                            <td><label title="Alterar Atividade"><span class="text-uppercase label label-success">Alterar</span></label></td>
                            <td><label title="Excluir"><span class="text-uppercase label label-danger">Excluir</span></label></td>
                            <%}%>
                            <td><a href="../ServletCentral?comando=CmdVisualizarAtividade&ativ_id=<%=a.getId()%>" title="Visualizar Atividade">Visualizar</a></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <% }%>
            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
