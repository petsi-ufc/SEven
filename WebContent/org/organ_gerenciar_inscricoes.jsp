<%@page import="br.ufc.pet.entity.ResponsavelAtividade"%>
<%@page import="br.ufc.pet.entity.Atividade"%>
<%@page import="br.ufc.pet.entity.Inscricao"%>
<%@page import="br.ufc.pet.entity.Organizador"%>
<%@page import="br.ufc.pet.entity.Participante"%>
<%@page import="br.ufc.pet.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@include file="../ErroAutenticacaoUser.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%  br.ufc.pet.entity.Evento e = (br.ufc.pet.entity.Evento) session.getAttribute("evento");
    Organizador organizador = (Organizador) session.getAttribute("user");
    ArrayList<Inscricao> inscricoesNoEvento = (ArrayList<Inscricao>) session.getAttribute("inscricoes");
    String estado = "";
    String nome = "";
    int flag = 0;
    ArrayList<Atividade> ats = e.getAtividades();
%>

<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>
        <script language="javascript" src="../jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="../jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script language="javascript" src="../Script.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div id="container">
            <%-- Incluindo Menu --%>
            <%@include file="organ_menu.jsp" %>

            <div id="content">
                <h1 class="titulo">Visualizar as Inscrições do Evento<br/> <%=e.getNome()%></h1>
                    <%@include file="/error.jsp"%>
                <div class="panel panel-default">
                    <div class="panel-heading text-center">Listagem de inscritos por atividade (Selecione a atividade)</div>
                    <div class="panel-body">  
                        <div class="col-lg-12 space-top overflowController">
                            <%if (ats == null || ats.size() == 0) {%>
                            <center><label>Sem atividades no momento</label></center>
                                <%} else {%>
                            <table class="table table-hover text-center">
                                <thead>
                                    <tr>
                                        <th>Nome</th>
                                        <th>Tipo</th>
                                        <th>Local</th>
                                        <th>Responsável</th>
                                        <th>Capacidade</th>
                                        <th>N° Inscritos</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <%for (Atividade a : ats) {
                                            if (a.isAceitaInscricao()) {%>
                                    <tr>
                                        <td><a href="../ServletCentral?comando=CmdListarInscritosEmAtividade&aId=<%=a.getId()%>"><%=a.getNome()%></a></td>
                                        <td><%=a.getTipo().getNome()%></td>
                                        <td><%=a.getLocal()%></td>
                                        <% java.lang.StringBuffer sb = new java.lang.StringBuffer("");
                                            for (ResponsavelAtividade ra : a.getResponsaveis()) {
                                                sb.append(ra.getUsuario().getNome());
                                                sb.append("<br>");
                                            }%>
                                        <td><%=sb.toString()%></td>                       
                                        <td><%=a.getVagas()%></td>
                                        <%
                                            br.ufc.pet.services.InscricaoService IS = new br.ufc.pet.services.InscricaoService();
                                            long vagasOcupadas = IS.getInscritosByAtividadeId(a.getId());
                                        %>
                                        <td><%=vagasOcupadas%></td>
                                    </tr>
                                    <%}
                                        }%>
                                </tbody>
                            </table>
                            <%}%>

                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading text-center">Lista de todos os inscritos no evento</div>
                    <div class="panel-body">
                        <div align="center" class="space-top">
                            <div class="row"> 
                                <div class="input-group">
                                    <label>Buscar Participante:</label>
                                    <input type="text" id="searchInput" class="form-control" placeholder="Buscar Participante" onkeyup="searchPart()"/>
                                </div>                                    
                            </div>
                        </div>
                        <div class="col-lg-12 space-top overflowController">
                            <%if (inscricoesNoEvento == null || inscricoesNoEvento.isEmpty()) {%>
                            <p style="text-align: center;"><label>Não há inscrições cadastradas</label></p>
                            <%} else {%>
                            <table class="table table-hover text-center" id="myTable">
                                <thead>
                                    <tr>
                                        <th>Nome do inscrito</th>
                                        <th>Status do Pagamento</th>
                                        <th>Editar</th>
                                        <th>Excluir</th>
                                        <th>Certificado</th>
                                        <th>Receber Pagamento</th>
                                    </tr>
                                </thead>
                                <tbody>
                                  <% for (Inscricao i : inscricoesNoEvento) {%>
                                    <tr><%  if (i.getParticipante() != null){
                                    			Participante p = i.getParticipante();
                                    			if(p.getUsuario() != null){
                                    				Usuario us = p.getUsuario();
                                    				nome = us.getNome();
                                    			}	
                                    } %>
                                        <td><%=nome%></td>
                                        <%
                                            if (i.isConfirmada()) {
                                                estado = "Efetuado";
                                                flag = 1;
                                                pageContext.setAttribute("teste", flag);
                                            } else {
                                                estado = "Não Efetuado";
                                                flag = 0;
                                                pageContext.setAttribute("teste", flag);
                                            }
                                        %>
                                        <td><%=estado%></td>
                                        <td><a href="../ServletCentral?comando=CmdBuscarInscricao&id=<%=i.getId()%>" title="Visualizar/Editar"><span class="text-uppercase label label-success">Visualizar / Editar</span></a></td>
                                        <td><a href="../ServletCentral?comando=CmdOrganExcluirInscricao&iId=<%=i.getId()%>" onclick="return confirm('ATENÇÃO: Se você excluir uma inscrição que já foi paga ela não estará mais no sistema e não será possível recuperar a quantia paga. Também poderá causar a perda das vagas e dos certificados relacionados com esta inscrição. Tem certeza que dejesa excluir esta inscrição?');" title="Excluir"><span class="text-uppercase label label-danger">Excluir</span></a></td>
                                        <td><a href="../ServletCentral?comando=CmdGerarCertificado&insc_id=<%=i.getId()%>" title="Gerar"><span class="text-uppercase label label-info">Gerar</span></a></td>
                                        <c:set var = "est"  scope="page" value ="${teste}"/>
                                        <c:if test = "${est == 1}">
                                            <td><a href="../ServletCentral?comando=CmdReceberPagamento&id_inscricao=<%=i.getId()%>" title="" class="btn disabled" role="button" aria-disabled="true" ><span class="text-uppercase label label-primary " >Pagar</span></a>
                                            </td>
                                        </c:if>
                                        <c:if test = "${est == 0}">
                                            <td><a href="../ServletCentral?comando=CmdReceberPagamento&id_inscricao=<%=i.getId()%>" title="Pagar" ><span class="text-uppercase label label-primary" >Pagar</span></a>
                                            </td>
                                        </c:if>
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table>
                            <%}%>
                        </div>
                    </div>
                </div>
                <a href="organ_inscricoes.jsp" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>  
            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
