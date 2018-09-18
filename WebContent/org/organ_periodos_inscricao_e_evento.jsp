<%@page import="br.ufc.pet.util.UtilSeven"%>
<%@page import="br.ufc.pet.evento.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="../css/ripples.min.css" />
        <link rel="stylesheet" href="../css/bootstrap-material-datetimepicker.css" />
        <link href='http://fonts.googleapis.com/css?family=Roboto:400,500' rel='stylesheet' type='text/css' />
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
        <title>SEven</title>
        <script language="javascript" src="../jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="../jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
        <script src="../js/ripples.min.js"></script>
        <script src="../js/material.min.js"></script>
        <script type="text/javascript" src="../js/moment-with-locales.min.js"></script>
	<script type="text/javascript" src="../js/bootstrap-material-datetimepicker.js"></script>
	<script type="text/javascript" src="../js/datetimepicker.js"></script>
        <script type="text/javascript"  language="javascript" src="../Script.js"></script>        
    </head>
    <body>
        <%            Evento evento = (Evento) session.getAttribute("evento");

            String inicioEvento = (String) session.getAttribute("inicioEvento");
            session.removeAttribute("inicioEvento");
            if (inicioEvento == null) {
                inicioEvento = UtilSeven.treatToString(evento.getInicioPeriodoEvento());
            }

            String fimEvento = (String) session.getAttribute("fimEvento");
            session.removeAttribute("fimEvento");
            if (fimEvento == null) {
                fimEvento = UtilSeven.treatToString(evento.getFimPeriodoEvento());
            }

            String inicioInscricao = (String) session.getAttribute("inicioInscricao");
            session.removeAttribute("inicioInscricao");
            if (inicioInscricao == null) {
                inicioInscricao = UtilSeven.treatToString(evento.getInicioPeriodoInscricao());
            }

            String fimInscricao = (String) session.getAttribute("fimInscricao");
            session.removeAttribute("fimInscricao");
            if (fimInscricao == null) {
                fimInscricao = UtilSeven.treatToString(evento.getFimPeriodoInscricao());
            }

        %>
        <div id="container">
            <%-- Incluindo o Menu --%>
            <%@include file="organ_menu.jsp" %>

            <div id="content">
                <%@include file="/error.jsp" %>
                <h1 class="titulo">Gerenciar Períodos de Inscrição e Evento</h1>
                <div class="row center-block" style="width: 50%">
                    <form action="../ServletCentral?comando=CmdAlterarPeriodoInscricaoeEvento" method="POST">
                        <div class="form-group">
                            <label>Inicio do Evento: </label><br/>
                            <input id="date-start-event" data-toggle="tooltip" title="Data de Início do Evento" type="text" name="inicioEvento" value="<%=inicioEvento%>" class="form-control">                           
                            <!--<input data-toggle="tooltip" title="Data de Início do Evento" type="text" name="inicioEvento" maxlength="10" value="<%=inicioEvento%>" onkeypress="return formataData(this, event)" class="form-control">-->
                        </div>
                        <div class="form-group">
                            <label>Fim do Evento: </label><br/>
                            <input id="date-end-event" data-toggle="tooltip" title="Data de Fim do Evento" type="text" name="fimEvento" value="<%=fimEvento%>" class="form-control">
                            <!--<input data-toggle="tooltip" title="Data de Fim do Evento" type="text" name="fimEvento" maxlength="10" value="<%=fimEvento%>" onkeypress="return formataData(this, event)" class="form-control">-->
                        </div>
                        <div class="form-group">
                            <label>Inicio das Incrições: </label><br/>
                            <input id="date-start-in" data-toggle="tooltip" title="Data de Início das Inscrições" type="text" name="inicioInscricao" maxlength="10" value="<%=inicioInscricao%>" class="form-control">
                            <!--<input data-toggle="tooltip" title="Data de Início das Inscrições" type="text" name="inicioInscricao" maxlength="10" value="<%=inicioInscricao%>" onkeypress="return formataData(this, event)" class="form-control">-->
                        </div>
                        <div class="form-group">
                            <label>Fim das Inscrições: </label>
                            <input id="date-end-in" data-toggle="tooltip" title="Data de Fim das Inscrições" type="text" name="fimInscricao" maxlength="10" value="<%=fimInscricao%>" class="form-control">
                            <!--<input data-toggle="tooltip" title="Data de Fim das Inscrições" type="text" name="fimInscricao" maxlength="10" value="<%=fimInscricao%>" onkeypress="return formataData(this, event)" class="form-control">-->
                        </div>

                        <input data-toggle="tooltip" title="Alterar Datas" type="submit" value="Alterar" class="btn btn-default pull-right" onclick="return confirm('Deseja realmente executar essa modificação?')" />
                        <a href="" title="" onclick="history.back(); return false;" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
                    </form>

                </div>
            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
