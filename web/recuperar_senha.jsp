<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="imagens/favicon.png" type="image/x-icon"/>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
         <script language="javascript" src="jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript"  language="javascript" src="Script.js"></script>
        <title>SEven</title>
    </head>
    <body>
         <%@include file="menu_index.jsp"%>
        <div id="container-register">
            <h1 class="title-register">Recuperar Senha</h1><hr>
            <form action="ServletCentral?comando=CmdRecuperarSenha" method="POST">
                <div class="form-group">
                   <label for="">Email cadastrado</label>
                    <input data-toggle="tooltip" title="Email Cadastrado" type="text" class="form-control" placeholder="Digite o Email Cadastrado" name="email" required/>
                </div>
                <div align="center"><button data-toggle="tooltip" title="Submeter" type="submit" class="btn btn-default ">Submeter</button></div>
            </form>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>

