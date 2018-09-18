<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@page 
    import="java.util.ArrayList,br.ufc.pet.entity.Horario,br.ufc.pet.entity.Evento,java.text.DateFormat, java.text.SimpleDateFormat" 
 %>
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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
        <script type="text/javascript" src="../Script.js"></script>
    </head>
    <body>
        <%
                    Horario horario = (Horario) session.getAttribute("horario");
                    String titulo = "Adicionar";
                    String hi = "";
                    String mi = "";
                    String hf = "";
                    String mf = "";
                    String data = "";
                    String timeI = "";
                    String timeF = "";
                    if (horario != null) {
                        hi = String.format("%02d", horario.getHoraInicial()) ;
                        mi = String.format("%02d", horario.getMinutoInicial());
                        hf = String.format("%02d", horario.getHoraFinal());
                        mf = String.format("%02d", horario.getMinutoFinal());
                        data = br.ufc.pet.util.UtilSeven.treatToString(horario.getDia());
                        titulo = "Editar";
                        timeI = hi + ":" + mi;
                        timeF = hf + ":" + mf;
                    }
                    Evento evento = (Evento) session.getAttribute("evento");
                    String dataInicio = "--/--/----";
                    String dataFim = "--/--/----";
                    if(evento != null){
                        DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
                        dataInicio = format.format(evento.getInicioPeriodoEvento());
                        dataFim = format.format(evento.getFimPeriodoEvento());
                    }
        %>
        <div id="container">
                <%-- Incluindo o Menu --%>
                <%@include file="organ_menu.jsp" %>
                
                <div id="content">
                <h1 class="titulo"><%=titulo%> Horario</h1>
                 <%@include file="/error.jsp" %>
                <form action="../ServletCentral?comando=CmdAdicionarHorario" method="post" class="text-center">
                    <div class="form-group form-inline">
                        <label>Horário Inicial:</label><br/>
                        <input data-toggle="tooltip" title="Horário Inicial" type="text" name="inicial" value="<%=timeI%>" class="time form-control" />
                        <!--<input maxlength="2" type="text" onkeypress="return validaNumerosSilencioso(event)" name="hora_inicial" value="<%=hi%>" class="form-control"/>:-->
                        <!--<input maxlength="2" type="text" onkeypress="return validaNumerosSilencioso(event)" name="min_inicial" value="<%=mi%>" class="form-control"/>-->
                    </div>
                    <div class="form-group form-inline">
                        <label>Horário Final:</label><br/>
                        <input data-toggle="tooltip" title="Horário Final" type="text" name="final" value="<%=timeF%>" class="time form-control" />
<!--                        <input maxlength="2" type="text"  onkeypress="return validaNumerosSilencioso(event)" name="hora_final" value="<%=hf%>" class="form-control"/> :
                        <input maxlength="2" type="text"  onkeypress="return validaNumerosSilencioso(event)" name="min_final" value="<%=mf%>" class="form-control"/>-->
                    </div>
                    <br>
                    <div class="form-group">
                        <label>Data do Evento - De: <strong><%=dataInicio%></strong></label> 
                        <label> até: <strong><%=dataFim%></strong> </label> 
                    </div>
                    <div class="form-group form-inline">
                        <label>Data do Horário:</label><br/>
                        <input data-toggle="tooltip" title="Data do Horário Escolhido" type="text" name="data_completa" value="<%=data%>" class="date form-control" />
                        <!--<input type="text" onkeypress="return formataData(this,event)" maxlength="10" name="dia" value="<%=data%>" class="form-control"/>-->
                    </div>
                    
                    <a href="../ServletCentral?comando=CmdListarHorarios" title="" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
                    <input data-toggle="tooltip" title="Adicionar Horário" type="submit" value="Adicionar" class="btn btn-default" onclick="return confirm('Deseja realmente confirmar esses dados?')" />
                </form>
            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
