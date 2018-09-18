<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="br.ufc.pet.entity.Atividade"%>
<%@page import="br.ufc.pet.entity.Inscricao"%>
<%@page import="br.ufc.pet.entity.TipoAtividade"%>
<%@page import="br.ufc.pet.services.InscricaoService"%>
<%@page import="br.ufc.pet.entity.Horario"%>
<%@page import="br.ufc.pet.util.UtilSeven"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <%          //pega inscrição que foi montada pelo comando MontarInscricao
        Inscricao i = (Inscricao) session.getAttribute("inscricao");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>
    </head>
    <body>
        <div id="container">
            <%-- Incluindo Menu --%>
            <%@include file="organ_menu.jsp" %>

            <div id="content">
                <div class="panel panel-default">
                    <div class="panel-heading text-center">Atualizar Inscrição</div>
                    <div class="panel-body">
                        <div class="col-lg-12 space-top">
                            <label>Confirmação da Inscrição:</label>

                            <%//formata as datas para exibição em texto
                                String data = UtilSeven.treatToLongString(i.getDataRealizada());
                                String datapg = UtilSeven.treatToLongString(i.getDataPagamento());
                            %>
                            <br/>
                            <label>Evento: </label><%=i.getEvento().getNome()%><br/>
                            <label>Participante: </label><%=i.getParticipante().getUsuario().getNome()%><br/>
                            <label>Modalidade: </label><%=i.getModalidade().getTipo()%><br/>
                                <label>Data: </label><%=data%><br/>
                                <label>Prazo de pagamento: </label>Até <%=datapg%><br/>
                                <label>Atividades:</label>
                                <%for (Atividade a : i.getAtividades()) {%>
                                <label><%=a.getNome()%></label>
                                    <%for (Horario h : a.getHorarios()) {%><%--Exibe horários de cada atividade--%>
                                    <br/>
                                    (<%=h.printHorario()%>)
                                    <%}%>
                                
                                <%}%><br/>
                            <%
                                InscricaoService IS = new InscricaoService();
                                String preco = UtilSeven.precoFormater(IS.getPrecoInscricao(i));
                            %>
                            <label>Preço: </label><%=preco%><br/>
                        </div>
                    </div>
                </div>
                <form action="../ServletCentral?comando=CmdOrgSubmeterInscricao" method="post">
                    <a href="" title="" onclick="history.back(); return false;" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
                    <input type="submit" value="Submeter" class="btn btn-default" onclick="return confirm('Deseja realmente submeter essa inscrição?')" />
                </form>
            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
