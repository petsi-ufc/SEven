<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.ufc.pet.entity.Usuario,br.ufc.pet.entity.Atividade" %>
<%@page import="java.util.*"%>
<%@page import="java.util.ArrayList"%>
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
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
        <% ArrayList<Usuario> us = (ArrayList<Usuario>) session.getAttribute("usuarios");
            session.removeAttribute("usuarios");
            //recuperar dados do formulario que ja foram preenchidos para que nao sejam perdidos
            String nome = request.getParameter("nome_atividade");
            String local = request.getParameter("local");
            String vagas = request.getParameter("vagas");
            //String tipo = request.getParameter("tipo_id");

            Atividade ativTemp = new Atividade();
            if (nome != null) {
                ativTemp.setNome(nome);
            } else {
                ativTemp.setNome("");
            }
            if (local != null) {
                ativTemp.setLocal(local);
            } else {
                ativTemp.setLocal("");
            }
            try {
                ativTemp.setVagas(Integer.parseInt(vagas));
            } catch (Exception e) {
                ativTemp.setVagas(0);
            }
            if (nome != null || local != null || vagas != null) {
                session.setAttribute("atividadeTemp", ativTemp);
            }
        %>
        <div id="container">
            <%-- Incluindo o Menu --%>
            <%@include file="organ_menu.jsp" %>

            <div id="content">
                <h1 class="titulo">Adicionar um responsável à Atividade</h1>
                <%@include file="/error.jsp"%>
                <p style="font-size: medium; margin-left: -20px;">Você pode tornar responsável um usuário já cadastrado ou cadastrar um novo responsável.</p>

                <div class="panel panel-default">
                    <div class="panel-heading text-center">Busca de usuários pelo nome ou parte do nome</div>
                    <div class="panel-body">  
                        <div class="col-lg-12 space-top">
                            <form name="formAddResponsavel" action="../ServletCentral" method="POST">
                                <input type="hidden" name="comando" value="CmdBuscarUsuarioResponsavel">
                                <div class="form-group form-inline">
                                    <input data-toggle="tooltip" title="Nome do Responsável" type="text" name="nome_busca" placeholder="Nome" class="form-control"/>
                                    <input data-toggle="tooltip" title="Buscar Responsável" type="submit" name="btnBuscar" value="Buscar" class="btn btn-default">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <a href="" title="" onclick="history.back(); return false;" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
                <a data-toggle="tooltip" href="organ_add_novo_responsavel.jsp" title="Adicionar um Novo Responsável" class="btn btn-default">Cadastrar Novo Responsável</a>
                <% if (us != null && us.size() > 0) {%>
                <table id="data_table" class="table table-hover text-center space-top">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Email</th>
                            <th>Incluir</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Usuario u : us) {%>
                        <tr>
                            <td><%=u.getNome()%></td>
                            <td><%=u.getEmail()%></td>
                            <td><a href="../ServletCentral?comando=CmdIncluirResponsavel&usuario_id=<%=u.getId()%>" ><span class="text-uppercase label label-success">Incluir</span></a> </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table><br><br>
                <% }%>
                

            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
