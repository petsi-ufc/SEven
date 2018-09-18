<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.entity.Participante" %>
<%@page import="br.ufc.pet.util.UtilSeven" %>
<%@page import="java.util.Date" %>
<%@include file="../ErroAutenticacaoUser.jsp" %>
<%    String mensagem = (String) request.getAttribute("mensagem");
    Participante part = (Participante) session.getAttribute("user");
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
        fone = "";
    }
    if (cidade == null || cidade.trim().isEmpty()) {
        cidade = "";
    }
    if (instituicao == null || instituicao.trim().isEmpty()) {
        instituicao = "";
    }
    if (sexo == null || sexo.trim().isEmpty()) {
        sexo = "";
    }
    if (rua == null || rua.trim().isEmpty()) {
        rua = "";
    }
    if (bairro == null || bairro.trim().isEmpty()) {
        bairro = "";
    }
    if (uf == null || uf.trim().isEmpty()) {
        uf = "";
    }
    if (numero == null || numero.trim().isEmpty()) {
        numero = "";
    }
    if (dataNascimento == null) {
        data = "";
    } else {
        data = UtilSeven.treatToString(part.getUsuario().getDataNascimento());
    }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
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
        <script type="text/javascript" src="../js/cidade-dinamica.js"></script>
        <script type="text/javascript" src="../Script.js"></script> 
    </head>
    <body>
        <div id="container">

            <%@include file="part_menu.jsp" %>

            <div id="content">
                <div class="panel panel-default">
                    <%@include file="../error.jsp" %>
                    <div class="panel-heading text-center">Editar Dados Cadastrais</div>
                    <div class="panel-body"> 
                        <%if (mensagem != null) {%>
                        <h3><%=mensagem%></h3><br />
                        <% }%>

                        <div class="row">   

                            <form action="../ServletCentral" method="post">
                                <div class="col-lg-offset-1 col-lg-5 space-top"> 
                                    <input type="hidden" name="comando" value="CmdEditarParticipante" />
                                    <br/><div class="pull-left text-uppercase label label-warning">* Campos obrigatórios</div><br/>
                                    <div class="form-group">                                       
                                        <input data-toggle="tooltip" title="Nome Completo" type="text" maxlength="50" class="form-control space-top" value="<%=part.getUsuario().getNome()%>"  name="nome" />
                                    </div>
                                    <div class="form-group">
                                        <input data-toggle="tooltip" title="Telefone" placeholder="Telefone" type="text" maxlength="13" value="<%=fone%>" onkeypress="return formataContato(this, event)" name="fone" class="form-control" required/>
                                    </div>
                                    <div class="form-group">                                       
                                        <input data-toggle="tooltip" title="Email" placeholder="* E-mail" type="text" maxlength="50" value="<%=part.getUsuario().getEmail()%>" name="email" class="form-control" required/>
                                    </div>

                                    <div class="form-group input-group input-append date">
                                        <input data-toggle="tooltip" title="Data de Nascimento" placeholder="Data de Nascimento" type="text" name="dt_nascimento" value="<%=data%>" id="box_datenasc" onkeypress="return formataData(this, event)" maxlength="10" class="form-control" required/>
                                        <span class="input-group-addon" id="datenasc_up" >
                                            <button name="dt_nascimento" id="datenasc" class="btn btn-outline-secondary glyphicon glyphicon-calendar form-control">
                                            </button>
                                        </span>
                                    </div>

                                    <div class="form-group">                                      
                                        <select class="form-control" id="sexo" name="sexo">
                                            <option value="Masculino">Masculino</option>
                                            <option value="Feminino">Feminino</option>
                                        </select>
                                    </div>                          
                                    <div class="form-group">
                                        <input data-toggle="tooltip" title="Instituição" placeholder="Instituição" type="text" maxlength="50" value="<%=instituicao%>" name="instituicao" class="form-control" required/>
                                    </div>
                                    <div class="form-group">                                        
                                        <input data-toggle="tooltip" title="Rua" placeholder="* Rua" type="text" maxlength="50" value="<%=rua%>" name="rua" class="form-control" required/>
                                    </div>
                                </div>
                                <div class="col-lg-5 space-top">

                                    <div class="form-group">
                                        <input data-toggle="tooltip" title="Bairro" placeholder="* Bairro" type="text" maxlength="50" value="<%=bairro%>" name="bairro" class="form-control space-top-camp" required/>
                                    </div>
                                    <div class="form-group">                                        
                                        <input data-toggle="tooltip" title="Número/Complemento" placeholder="* Número/Complemento" type="text" maxlength="20" value="<%=numero%>" name="numero" class="form-control" required/>
                                        <!--<input data-toggle="tooltip" title="Número" placeholder="* Número" type="text" maxlength="6" value="<%=numero%>" onkeypress="return validaNumerosSilencioso(event);" name="numero" class="form-control" required/>-->
                                    </div>

                                    <div class="form-group">
                                        <input type="hidden" value="<%=uf%>" id="ufhidden"/>
                                        <select id="estados" data-toggle="tooltip" title="Estado" name="uf" value="<%=uf%>" class="form-control" required>
                                            <option value=""></option>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <input type="hidden" value="<%=cidade%>" id="cidadehidden"/>
                                        <select id="cidades" data-toggle="tooltip" title="Cidade" name="cidade" value="<%=cidade%>" class="form-control" required>

                                        </select>
                                    </div>

                                    <!--                                    <div class="form-group">
                                                                            <input data-toggle="tooltip" title="UF" placeholder="* UF" type="text" maxlength="50" value="<%=uf%>" name="uf" class="form-control" required/>
                                                                        </div>
                                                                        <div class="form-group">                                      
                                                                            <input data-toggle="tooltip" title="Cidade" placeholder="* Cidade" type="text" maxlength="50" value="<%=cidade%>" name="cidade" class="form-control" required/>
                                                                        </div>-->
<!--                                    <div class="form-group">
                                        <input data-toggle="tooltip" title="Antiga Senha" placeholder="* Antiga Senha" type="password" maxlength="18" minlength="6" value="" name="oldsenha" class="form-control"/>
                                    </div>
                                    <div class="form-group">
                                        <input data-toggle="tooltip" title="Senha" placeholder="* Senha" type="password" maxlength="18" minlength="6" value="" name="senha" class="form-control"/>
                                    </div>
                                    <div class="form-group">
                                        <input data-toggle="tooltip" title="Repeti a Senha" placeholder="* Digite a senha novamente" type="password" maxlength="18" minlength="6" value="" name="r-senha" class="form-control"/>
                                    </div>-->

                                    <button data-toggle="tooltip" title="Atualizar Dados" type="submit" class="btn btn-default pull-right" onclick="return confirmarCadastrado()">Atualizar</button>

                                </div>                                
                            </form>
                        </div>
                        <div class="space-top"></div>
                    </div>
                </div>
            </div>
            <a href="" title="" onclick="history.back(); return false;" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a><br/>
        </div>
        </div>
        <div class="footer-top">        
            <%@include file="../footer.jsp" %>
        </div>
        <script>
            window.onload = function() {
                var s = document.getElementById("sexo").options;
                for (var i = 0; i < s.length; i++) {
                    if (s[i].value === "<%=sexo%>") {
                        s[i].selected = true;
                        break;
                    }
                }
            }
        </script>
    </body>
</html>
