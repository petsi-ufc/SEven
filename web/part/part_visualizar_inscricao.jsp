<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="br.ufc.pet.evento.Atividade"%>
<%@page import="br.ufc.pet.evento.Inscricao"%>
<%@page import="br.ufc.pet.evento.TipoAtividade"%>
<%@page import="br.ufc.pet.evento.Horario"%>
<%@page import="br.ufc.pet.services.InscricaoService"%>
<%@page import="br.ufc.pet.util.UtilSeven"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <%          //pega inscrição a ser visualizada
                Inscricao i = (Inscricao) session.getAttribute("inscricao");
    %>
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
        <div id="container">
            
                <%-- Incluindo Menu --%>
                <%@include file="part_menu.jsp" %>
            
            <div id="content">
                <h1 class="titulo">Visualizar Inscrição</h1>
                <div class="panel panel-default">
                    <div class="panel-heading text-center">Confirmação da inscrição</div>
                    <div class="panel-body">  
                        <div class="col-lg-12 space-top">
                
                  <%//formata as datas para exibição em texto
                                String data = UtilSeven.treatToLongString(i.getDataRealizada());
//                                String datapg = UtilSeven.treatToLongString(i.getDataPagamento());
                            %>
                            <p><strong>Evento:</strong> <%=i.getEvento().getNome()%></p>
                            <p><strong>Participante:</strong> <%=i.getParticipante().getUsuario().getNome()%></p>
                            <p><strong>Modalidade:</strong> <%=i.getModalidade().getTipo()%></p>
                            <p><strong>Data: </strong><%=data%></p>
                            <p><strong>Atividades: </strong></p>
                             <ol>
                                <%for (Atividade a : i.getAtividades()) {%>
                           
                                    <p><li><b><%=a.getNome()%></b></li>
                                    <ul>
                                        <%for (Horario h : a.getHorarios()) {%><%--Exibe horários de cada atividade--%>
                                            <li>(<%=h.printHorario()%>)</li>
                                        <%}%>
                                    </ul>
                                    </p>                            
                            <%}%>
                            </ol>                            
                            
                            <%
                                InscricaoService IS = new InscricaoService();
                                String preco = UtilSeven.precoFormater(IS.getPrecoInscricao(i));
                            %>
                            <p><strong>Preço: </strong><%=preco%></p>
                        </div>
                    </div>
                </div>
            </div>

                <form action="../ServletCentral?comando=CmdEditarInscricao" method="post" class="cadastro">
                    <a href="" title="" onclick="history.back(); return false;" class="btn btn-default"><span aria-hidden="true">&larr;</span>Voltar</a>
                    <input data-toggle="tooltip" title="Editar" type="submit" value="Editar" class="btn btn-default" />
                </form>
            </div>
        </div>
        <div class="footer-top">        
            <%@include file="../footer.jsp" %>
        </div> 
    </body>
</html>