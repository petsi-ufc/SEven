<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.evento.MovimentacaoFinanceira"%>
<%@page import="java.util.*"%>
<%@page import="br.ufc.pet.util.UtilSeven"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <%        br.ufc.pet.evento.Evento evento = (br.ufc.pet.evento.Evento) session.getAttribute("evento");
        ArrayList<MovimentacaoFinanceira> movfs = evento.getMovimentacoesFinanceiras();
        String estado = (String) session.getAttribute("estado");
        session.removeAttribute("estado");
        Boolean permissao = (Boolean) session.getAttribute("permissao");
    %>
    <script type="text/javascript" >
        function confirmar() {
            if (<%=permissao%>) {
                if (confirm("Deseja realmente excluir essa movimentação financeira?"))
                    return true;
            } else {
                alert("Você não possui acesso?");
            }
            return false;
        }

        function permissao() {
            if (!<%=permissao%>) {
                alert("Você não possui acesso?");
                return false;
            }
        }
    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>
        <script language="javascript" src="../jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="../jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../Script.js"></script>
    </head>
    <body>
        <div id="container">

            <%-- Incluindo o Menu --%>
            <%@include file="organ_menu.jsp" %>

            <div id="content">
                <h1 class="titulo">Movimentações Financeiras</h1>
                <%if (estado == "atualizado") {%>
                  <div class="alert alert-success alert-dismissable">
                     <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                     <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                     <span class="sr-only">Success:</span> 
                        Atualização realizada com sucesso
                   </div>
                <%} else if (estado == "excluido") {%>
                  <div class="alert alert-success alert-dismissable">
                     <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                     <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                     <span class="sr-only">Success:</span> 
                        Exclusão realizada com sucesso
                   </div>
                <%} else if (estado == "adicionado") {%>
                    <div class="alert alert-success alert-dismissable">
                     <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                     <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                     <span class="sr-only">Success:</span> 
                        Adição realizada com sucesso
                   </div>
                <%}%>
                <div>
                    <table id="data_table" class="table table-hover">
                        <thead>
                            <tr>
                                <th>Descrição</th>
                                <th>Tipo</th>
                                <th>Data</th>
                                <th>Valor</th>
                                <th>Alterar</th>
                                <th>Excluir</th>
                            </tr>
                        </thead>
                        <tbody class="text-center">
                            <%for (MovimentacaoFinanceira mov : movfs) {%>
                            <tr class="text-center">
                                <td><%=mov.getDescricao()%></td>
                                <td><%=mov.getTipo()%></td>
                                <td><%=UtilSeven.treatToString(mov.getData())%></td>
                                <td><%=mov.getValor()%></td>
                                <td><a href="../ServletCentral?comando=CmdUpdateMovimentacaoFinanceira&id_mf=<%=mov.getId()%>" title="Alterar" onclick="return permissao()"><span class="text-uppercase label label-success">Alterar</span></a></td>
                                <td><a href="../ServletCentral?comando=CmdExcluirMovimentacaoFinanceira&id_mf=<%=mov.getId()%>" title="Excluir"  onclick="return confirmar()"><span class="text-uppercase label label-danger">Excluir</span></a></td>
                            </tr>
                            <%}%>
                            <%session.setAttribute("alteradomovfinan", "sem_alteracao");%>
                        </tbody>
                    </table>
                    <%
                        // Calcula o total de creditos, debitos e o balanco.
                        String creditos = "00,00";
                        String debitos = "00,00";
                        String balanco = "00,00";
                        double cred = 0, deb = 0;
                        for (MovimentacaoFinanceira mov : movfs) {
                            if (mov.getTipo().equals("credito")) {
                                cred += mov.getValor();
                            }
                            if (mov.getTipo().equals("debito")) {
                                deb += mov.getValor();
                            }
                        }

                        creditos = String.format("%.2f", cred);
                        debitos = String.format("%.2f", deb);
                        balanco = String.format("%.2f", cred - deb);
                    %>
                    <br/>
                    <table border="0" class="table table-striped">
                        <thead>
                            <th>Créditos</th>
                            <th>Débitos</th>
                            <th>Balanço</th>
                        </thead>
                        <tbody>
                            <tr class="text-center">
                                <td> <%=creditos%> </td>
                                <td> <%=debitos%> </td>
                                <td> <%=balanco%> </td>
                            </tr>
                        </tbody>
                    </table>
                    <a href="organ_financeiro.jsp" title=""  class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a><!--onclick="history.back(); return false;"-->
                    <a data-toggle="tooltip" href="organ_add_movimentacao.jsp" title="Adicionar uma Nova Movimentação" onclick="return permissao()" class="btn btn-default pull-right">Adicionar nova movimentação</a>
                </div>
            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
