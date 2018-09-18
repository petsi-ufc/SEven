<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.evento.Participante" %>
<%@page import="br.ufc.pet.util.UtilSeven" %>
<%@page import="java.util.Date" %>
<%@include file="../ErroAutenticacaoUser.jsp" %>
<%    Participante part = (Participante) session.getAttribute("user");
    String fone = part.getUsuario().getFone();
    String instituicao = part.getUsuario().getInstituicao();
    Date dataNascimento = part.getUsuario().getDataNascimento();
    String sexo = part.getUsuario().getSexo();
    String rua = part.getUsuario().getRua();
    String cidade = part.getUsuario().getCidade();
    String bairro = part.getUsuario().getBairro();
    String uf = part.getUsuario().getUf();
    String numero = part.getUsuario().getNumero();
    String data = null;
    if (fone == null || fone.trim().isEmpty()) {
        fone = "Não informado";
    }
    if (cidade == null || cidade.trim().isEmpty()) {
        cidade = "Não informado";
    }
    if (instituicao == null || instituicao.trim().isEmpty()) {
        instituicao = "Não informado";
    }
    if (sexo == null || sexo.trim().isEmpty()) {
        sexo = "Não informado";
    }
    if (rua == null || rua.trim().isEmpty()) {
        rua = "Não informado";
    }
    if (bairro == null || bairro.trim().isEmpty()) {
        bairro = "Não informado";
    }
    if (uf == null || uf.trim().isEmpty()) {
        uf = "Não informado";
    }
    if (numero == null || numero.trim().isEmpty()) {
        numero = "Não informado";
    }
    if (dataNascimento == null) {
        data = "Não informado";
    } else {
        data = UtilSeven.treatToString(part.getUsuario().getDataNascimento());
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="../Script.js"></script>
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>
        <script language="javascript" src="../jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="../jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript"  language="javascript" src="../Script.js"></script> 
    </head>
    <body>
        <div id="container">
            <%@include file="part_menu.jsp" %>
            <div id="content">
                <h1 class="text-center">Gerenciar Conta</h1>
                <%@include file="../error.jsp" %>
                <div class="panel panel-default">
                    <div class="panel-heading text-center">Dados Cadastrais</div>
                    <form action="../ServletCentral" method="post">
                        <div class="panel-body">  
                            <div class="col-lg-12 space-top">
                                <label>Nome: </label> <%=part.getUsuario().getNome()%><br/>
                                <label>Telefone: </label> <%=fone%><br/>
                                <label>E-mail: </label> <%=part.getUsuario().getEmail()%><br/>
                                <label>Nascimento: </label> <%=data%><br/>
                                <label>Sexo: </label> <%=sexo%><br/>
                                <label>Instituição: </label> <%=instituicao%><br/>
                                <label>Rua: </label> <%=rua%><br/>
                                <label>Bairro: </label> <%=bairro%><br/>
                                <label>Número/Complemento: </label> <%=numero%><br/>
                                <label>Estado: </label> <%=uf%><br/>
                                <label>Cidade: </label> <%=cidade%><br/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="center-block">
                    <form action="../ServletCentral" method="post">
                        <a href="" title="" onclick="history.back(); return false;" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
                        <a data-toggle="tooltip" title="Editar Dados" class="btn btn-default pull-right" href="part_conta.jsp">Editar Dados</a>
                        <!--<a data-toggle="tooltip" title="Excluir Conta" class="btn btn-default pull-right " href="../ServletCentral?comando=CmdExcluirParticipante" onclick="return confirm('Tem certeza que deseja excluir conta?')" title="">Excluir Conta</a>-->
                    </form>
                </div>
            </div>
        </div>
        <div class="footer-top">        
            <%@include file="../footer.jsp" %>
        </div> 
    </body>
</html>
