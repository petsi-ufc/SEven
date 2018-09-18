<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.entity.Usuario"%>
<%@include file="../ErroAutenticacaoUser.jsp" %>
<%    String mensagem = (String) request.getAttribute("mensagem");
    Usuario usuarioTemp = (Usuario) session.getAttribute("usuarioTemp");
    session.removeAttribute("usuarioTemp");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
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
            <%@include file="organ_menu.jsp" %>

            <div id="content">
                <%@include file="/error.jsp" %>

                <h1 class="titulo">Cadastro</h1>
                <%if (mensagem != null) {%>
                <h3><%=mensagem%></h3><br />
                <% }%>
                <form action="../ServletCentral" method="post">
                    <input type="hidden" name="comando" value="CmdCadastrarUsuarioResponsavel" />
                    <div class="panel panel-default">
                        <div class="panel-heading text-center">Dados cadastrais</div>
                        <div class="panel-body">  
                            <div class="col-lg-12 space-top">
                                <div class="form-group">
                                    <input data-toggle="tooltip" title="Nome Completo do Responsável" type="text" name="nome" value="<%= (usuarioTemp != null) ? usuarioTemp.getNome() : ""%>" placeholder="Nome Completo" class="form-control" required/>
                                </div>
                                <div class="form-group">
                                    <input data-toggle="tooltip" title="Email do Responsável" type="text" name="email"value="<%=(usuarioTemp != null) ? usuarioTemp.getEmail() : ""%>" placeholder="E-mail" class="form-control" required/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <a href="" title="" onclick="history.back(); return false;" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
                    <input data-toggle="tooltip" title="Cadastrar Novo Responsável" type="submit" value="Confirmar" class="btn btn-default" />
                </form>
            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
