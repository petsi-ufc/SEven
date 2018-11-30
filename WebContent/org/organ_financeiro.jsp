<%@page import="br.ufc.pet.entity.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script language="javascript" src="../jquery/jquery-1.10.2.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
        <title>SEven</title>
    </head>
    <body>
        <div id="container">
            
                <%-- Incluindo o Menu --%>
                <%@include file="organ_menu.jsp" %>
            
            <div id="content">
                <%Evento e = (Evento) session.getAttribute("evento");%>
                <%if (e.isGratuito()) {%>
                <h1 class="titulo">Este é um evento gratuito.</h1>
                <%} else {%>
                <div class="col-lg-6 ">
                    <h1 class="titulo">Movimentações</h1>
                    <%if (session.getAttribute("todosPagamentosRecebidos") != null) {%>
                       <div class="alert alert-success alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                        <span class="sr-only">Success:</span> 
                        Todas as inscrições tiveram seu pagamento realizado com sucesso.
                       </div>
                    <%}
                        session.removeAttribute("todosPagamentosRecebidos");%>
                        <div class="panel panel-default space-top">
                        <div class="panel-cor panel-heading text-center">Movimentação Finaceira</div>
                        <div class="panel-body text-center">
                            <label><a href="../ServletCentral?comando=CmdListarMovimentacaoFinanceira" title="">Movimentação Financeira</a></label>
                        </div>
                    </div>
                   
                </div>
                <div class="col-lg-6">
                    <h1 class="titulo">Pagamentos</h1>
                    <div class="panel panel-default space-top">
                        <div class="panel-cor panel-heading text-center">Receber Individual</div>
                        <div class="panel-body text-center">
                            <label><a href="organ_listar_pagamento.jsp" title="">Receber Pagamento de Inscrição Por Inscrição</a></label><br/>
                            <label><a href="organ_listar_pagamento_part.jsp" title="">Receber Pagamento de Inscrição Por Participante</a></label>
                        </div>
                    </div>
                    <div class="panel panel-default space-top">
                        <div class="panel-cor panel-heading text-center">Receber Todos</div>
                        <div class="panel-body text-center">
                            <label><a href="../ServletCentral?comando=CmdReceberPagamentoTodasInscricoes" title="" class="button2" onclick="return confirm('Deseja realmente efetuar esta operacao?')">Receber Pagamento de todas as Inscrições</a></label> 
                        </div>
                    </div>

                </div>

                <%}%>            

            </div>
                <div class="footer-top">
                    <%@include file="../footer.jsp" %>
                </div>
        </div>
    </body>
</html>
