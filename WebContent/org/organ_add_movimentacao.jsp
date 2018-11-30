<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.entity.MovimentacaoFinanceira"%>
<%@page import="java.util.*"%>
<%@page import="br.ufc.pet.util.UtilSeven"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <%        String addmov = (String) session.getAttribute("addmovimentacaofinanceira");
        MovimentacaoFinanceira movf = (MovimentacaoFinanceira) session.getAttribute("atualizarmovimentacaofinanceira");
        String comand = "Adicionar Movimentação Financeira";
        String descricao = "";
        String valor = "";
        String data = "";
        String tipo = "";
        if (movf != null) {
            comand = "Alterar Movimentação Financeira";
            descricao = movf.getDescricao();
            valor = Double.toString(movf.getValor());
            data = UtilSeven.treatToString(movf.getData());
            tipo = movf.getTipo();
        }
    %>
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
        <div id="container">
            <%-- Incluindo o Menu --%>
            <%@include file="organ_menu.jsp" %>
            <div id="content">
                <h1 class="titulo"><%=comand%></h1>
                <form action="../ServletCentral?comando=CmdAddMovimentacaoFinanceira" method="post">
                    <div class="form-group"><input data-toggle="tooltip" title="Valor da Movimentação" type="text" name="valor" value="<%=valor%>" placeholder="Valor" class="form-control"/></div>
                    <div class="form-group"><input data-toggle="tooltip" title="Data da Movimentação" type="text" name="data" value="<%=data%>" placeholder="Data" class="date form-control"/></div>
                    <!--<input type="text" maxlength="10" name="data" onkeypress="return formataData(this, event)" value="<%=data%>" placeholder="Data" class="form-control"/></div>-->
                    <div class="form-group"><input data-toggle="tooltip" title="Descrição da Movimentação" type="text" name="descrição" value="<%=descricao%>" placeholder="Descrição" class="form-control"/></div>
                    
                    <div class="form-group form-inline">
                        <label>Tipo:</label>
                        <%if (tipo.isEmpty()) {%>
                        <label><input type="radio" name="tipo_movimentacaofinanceira" value="credito" class="radio" /> Crédito</label>
                        <label><input type="radio" name="tipo_movimentacaofinanceira" value="debito" class="radio" /> Débito</label><br/>

                        <%} else if (tipo.compareTo("credito") == 0) {%>
                        <input type="radio" name="tipo_movimentacaofinanceira" value="credito" class="radio" checked="checked"/><label> Crédito</label>
                        <input type="radio" name="tipo_movimentacaofinanceira" value="debito" class="radio" /><label> Débito</label><br/>

                        <%} else {%>
                        <input type="radio" name="tipo_movimentacaofinanceira" value="credito" class="radio" /><label> Crédito</label>
                        <input type="radio" name="tipo_movimentacaofinanceira" value="debito" class="radio" checked="checked"/><label> Débito</label><br/>
                    </div>
                        
                    <%}%>
                    <%if (addmov == "vazio") {%>
                    <label><center>***Verifique se não há dados em branco ou algum dado incorreto***</center></label><br />
                            <%}%>                        
                            <%if (addmov == "erro") {%>
                    <label><center>***Erro ao adicionar a movimentação***</center></label><br />
                            <%}%>
                            <%session.setAttribute("addmovimentacaofinanceira", null);%>
                    <div class="space-top">
                    <a href="../ServletCentral?comando=CmdListarMovimentacaoFinanceira" title="" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
                    <input data-toggle="tooltip" title="Confirmar Movimentação" type="submit" value="Confirmar" class="btn btn-default pull-right" />
                    </div>
                </form>
            </div>
        </div>
        <div class="footer-top">
            <%@include file="../footer.jsp" %>
        </div>
    </body>
</html>
<!-- onclick="history.back(); return false;" class="voltar"-->