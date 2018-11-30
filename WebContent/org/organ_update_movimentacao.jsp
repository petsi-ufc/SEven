<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.entity.MovimentacaoFinanceira"%>
<%@page import="java.util.*"%>
<%@page import="br.ufc.pet.services.MovimentacaoFinanceiraService"%>
<%@page import="br.ufc.pet.util.UtilSeven"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <%
      MovimentacaoFinanceira movf = (MovimentacaoFinanceira) session.getAttribute("atualizarmovimentacaofinanceira");
      //String codigo = request.getParameter("id_mf");
        //        Long cod = Long.parseLong(codigo);
          //      MovimentacaoFinanceiraService s = new MovimentacaoFinanceiraService();
            //    MovimentacaoFinanceira movf = s.getById(cod);
                //session.setAttribute("atualizarmovimentacaofinanceira", movfinanAtualizar);
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <title>Centro de Controle :: Administrador</title>
    </head>
    <body>
        <div id="container">
            <div id="top">
                <%-- Incluindo o Menu --%>
                <%@include file="organ_menu.jsp" %>
            </div>
            <div id="content">
                <h1 class="titulo">Alterar Movimentação Financeira</h1>
                <form action="../ServletCentral?comando=CmdUpdateMovimentacaoFinanceira" method="post" class="atualizar_cadastro">
                    <fieldset>
                        <label>Descrição:</label><br />
                        <input type="text" name="descrição_update" value="<%=movf.getDescricao()%>"/><br />
                        <label>Valor:</label><br />
                        <input type="text" name="valor_update" value="<%=movf.getValor()%>"/><br />
                        <label>Data:</label><br />
                        <input type="text" name="data_update" value="<%=UtilSeven.treatToString(movf.getData())%>"/><br />
                        <label style="float: left">Tipo:</label><br />
                        <label style="float: left">Credito</label><input type="radio" value="" class="radio" /><label style="float: left">Debito</label><input type="radio" value="" class="radio" /><br />
                        <%session.setAttribute("alteradomovfinan","alterado");%>
                    </fieldset>
                    <input type="submit" value="Enviar" class="button" />
                    <a href="../ServletCentral?comando=CmdListarMovimentacaoFinanceira" class="voltarCadastro" title="">Voltar</a>
                </form>
            </div>
            <div id="footer"></div>
        </div>
    </body>
</html>
<!-- onclick="history.back(); return false;" class="voltar"-->