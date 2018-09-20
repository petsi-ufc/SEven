<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.entity.Participante" %>
<%@page import="br.ufc.pet.util.UtilSeven" %>
<%@page import="br.ufc.pet.entity.Administrador" %>
<%@page import="java.util.Date" %>
<%@include file="../ErroAutenticacaoUser.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%
            Administrador admin = (Administrador) session.getAttribute("user");
%>
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
        <script type="text/javascript" src="../Script.js"> </script>
    </head>
    <body>
        <div id="container-register">
            <%@include file="admin_menu.jsp" %>
            <%@include file="/error.jsp" %>
                <h1 class="title-register">Alterar Senha</h1><hr>
                <form action="../ServletCentral" method="post">
                    <div class="form-group">
                         <label for="text_a">Senha Antiga</label>
                        <input data-toggle="tooltip" title="Antiga Senha" type="password" maxlength="50" class="form-control" placeholder="Digita a senha antiga" name="oldsenha" required/>
                    </div>
                    <div class="form-group">
                         <label for="text_a">Nova Senha</label>
                        <input data-toggle="tooltip" title="Senha" type="password" maxlength="50" class="form-control" placeholder="Digita a nova senha" name="senha" required/>
                    </div>
                    <div class="form-group">
                         <label for="text_a">Repita a Senha</label>
                        <input data-toggle="tooltip" title="Repita a Senha" type="password" maxlength="50" class="form-control" placeholder="Repita a nova senha" name="rsenha" required/>
                    </div>
                    <input type="hidden" value="CmdEditAdmin" name="comando"/>
                    <input type="hidden" value="<%=admin.getUsuario().getId()%>" name="id">
                       
                    <div align="center"><button data-toggle="tooltip" title="Atualizar" type="submit" class="btn btn-default" onclick="return confirmarCadastrado()">Atualizar</button></div>
                </form>
        </div>
        <%@include file="../footer.jsp"%>
    </body>
</html>
