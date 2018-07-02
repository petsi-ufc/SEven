<%@page import="br.ufc.pet.services.UsuarioService"%>
<%@page import="br.ufc.pet.evento.InscricaoAtividade"%>
<%@page import="br.ufc.pet.services.AtividadeService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="br.ufc.pet.evento.Inscricao"%>
<%@page import="br.ufc.pet.services.InscricaoService"%>
<%@page import="br.ufc.pet.evento.Participante"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>

    <%          //recupera participante da sessão
        Participante p = (Participante) session.getAttribute("user");

        ArrayList<Inscricao> array = p.getInscricoes();

        String estado;//será utilizada para dizer se uma inscrição está ou não está consolidada

        //Para atualizão de dados em relação ao certificado
        UsuarioService us = new UsuarioService();
        p.setUsuario(us.getById(p.getUsuario().getId()));
        session.setAttribute("user", p);

    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <link href="../css/modal.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>
        <script language="javascript" src="../jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="../jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
        <script src="../Script.js" ></script>
    </head>
    <body>
        <div id="container">
            <%-- Incluindo Menu --%>
            <%@include file="part_menu.jsp" %>
            
            <div id="content">
                <%@include file="/error.jsp"%>
                <div class="panel panel-default">
                    <div class="panel-heading text-center">Visualizar Minhas Inscrições</div>
                    <div class="panel-body">
                        <%if (array == null || array.isEmpty()) {%>
                        <center><label>Não há inscrições cadastradas</label></center>
                            <%} else {%>

                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Tipo</th>
                                    <th>Nome do Evento</th>
                                    <th>Status do Pagamento</th>
                                    <!--<th>Gerar boleto</th>-->
                                    <th>Gerar certificado</th>
                                    <th>Visualizar</th>
                                    <th>Excluir</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%

                                    AtividadeService as = new AtividadeService();
                                    ArrayList<InscricaoAtividade> ias = new ArrayList<InscricaoAtividade>();
                                    for (Inscricao i : array) {
                                        boolean liberarCertificado = false;
                                        ias = as.getIncricaoAtividadeByInscricao(i.getId());
                                        if (ias != null) {
                                            for (int cont = 0; cont < ias.size(); cont++) {
                                                System.out.println(ias.get(cont).isConfirmaCertificado());
                                                if (ias.get(cont).isConfirmaCertificado()) {
                                                    liberarCertificado = true;
                                                    break;
                                                }
                                            }
                                        }

                                %>
                                <tr>
                                    <td><center><%=i.getModalidade().getTipo()%></center></td>
                                    <td><center><%=i.getEvento().getNome()%></center></td>
                                    <%
                                        if (i.isConfirmada()) {
                                            estado = "Efetuado";
                                        } else {
                                            estado = "Não Efetuado";
                                        }
                                    %>
                                    <td><center><%=estado%></center></td>
                                    <!--<% if (!i.isConfirmada()) {%>
                                    <td><a href="../ServletCentral?comando=CmdGerarBoletoPagamento&id=<%=i.getId()%>"title=""><span class="text-uppercase label label-success">Gerar Boleto</span></a></td>
                                    <% } else {%>
                                    <td> - </td>
                                    <% }%>!-->
                                    <% if (liberarCertificado) {%>
                                    <td><center><a href="../ServletCentral?comando=CmdGerarCertificadoParticipante&insc_id=<%=i.getId()%>" title="Gerar Certificado" onclick="return clickGerarCerticiado(<%=p.getUsuario().isCertificadoGerado()%>, <%=i.getId()%>);" ><span class=" text-uppercase label label-warning">Gerar Certificado</span></a></center> </td>
                                    <% } else {%>
                                    <td> <center>Indisponível </center></td>
                                    <% }%>
                                    <% if (!i.isConfirmada()) {%><%--link chama o comando de visualização/edição--%>
                                    <td><center><a href="../ServletCentral?comando=CmdVisualizarInscricao&iId=<%=i.getId()%>" title="Visualizar/Editar"><span class="text-uppercase label label-info">Visualizar</span></a></center></td>
                                    <% } else {%>
                                    <td><center> -</center> </td>
                                    <% }%>
                                    <td><center><a href="../ServletCentral?comando=CmdExcluirInscricao&iId=<%=i.getId()%>" onclick="return confirm('ATENÇÃO: Se você excluir uma inscrição que já foi paga ela não estará mais no sistema e você não poderá recuperar a quantia gasta. Você também poderá perder as vagas e os certificados relacionados com esta inscrição. Tem certeza que dejesa excluir esta inscrição?');" title="Excluir"><span class="text-uppercase label label-danger">Excluir</span></a></center></td>
                                </tr>
                                <%}%> 
                            </tbody>
                        </table>
                        <%}%>
                        <div id="list">  
                        </div>
                    </div>
                </div>
                <a data-toggle="tooltip" href="../ServletCentral?comando=CmdListarEventosAbertos" title="Nova Inscrição" class="btn btn-default">Nova Inscrição</a>
            </div>
        </div>

        <div id="openModal" class="modalDialog">
            <div>
                <a href="#close" title="Close" class="close">X</a>
                <p>
                    Após gerar o certificado você não poderá mais editar seu nome. 
                    Atualmente seu nome no sistema é 
                    <b><%=p.getUsuario().getNome().toUpperCase()%>,</b>
                    e esse será usado em todos os certificados gerados.
                </p>
                <p>
                    Deseja realmente utilizar esse nome em seus certificados?
                </p>
                <a href="part_conta.jsp" >ALTERAR MEUS DADOS</a>
                <a id="linkGerarCertificado"    
                   href="../ServletCentral?comando=CmdGerarCertificado&insc_id="
                   onclick="clickSimModal();" >
                    <button id="btModalSim" class="btModal" >SIM</button>
                </a>
                <br><br>
                        </div>
                        </div>
                        <div class="footer-top">        
                            <%@include file="../footer.jsp" %>
                        </div>
                        </body>
                        </html>
