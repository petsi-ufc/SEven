<%@page import="br.ufc.pet.entity.Participante"%>
<%@page import="br.ufc.pet.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String mensagem = (String)request.getAttribute("mensagem");
    
            Participante p = (Participante) session.getAttribute("user");
            session.removeAttribute("user");
            
                
            String nome = (String) session.getAttribute("nome");
            session.removeAttribute("nome");
            if(nome==null)
                nome="";
            
            String email = (String) session.getAttribute("email");
            session.removeAttribute("email");
            if(email==null)
                email="";
            
            String instituicao = (String) session.getAttribute("instituicao");
            session.removeAttribute("instituicao");
            if(instituicao==null)
                instituicao="";
            
            if(p != null){
                nome = p.getUsuario().getNome();
                email = p.getUsuario().getEmail();
                instituicao = p.getUsuario().getInstituicao();
            }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="css/estilo.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="imagens/favicon.png" type="image/x-icon"/>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script language="javascript" src="jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script type="text/javascript" src="jquery/jquery.dataTables.js"></script>
        <script type="text/javascript" src="jquery/initDataTable.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="Script.js"> </script>
        <title>SEven</title>
    </head>
    <body>
        <div id="container-register">
            <%@include file="menu_index.jsp"%>
                
                <h1 class="title-register">Cadastro</h1><hr/>
                <%if (mensagem != null) {%>
                <h3><%=mensagem%></h3><br />
                <% } %>
               <%@include file="error.jsp" %>
              
                <form action="ServletCentral" method="post">
                    <input type="hidden" name="comando" value="CmdCadastrarParticipante" />
                       
                         <div class="form-group">
                              <label for="">Nome Completo</label>
                            <input data-toggle="tooltip" title="Nome Completo" type="text" maxlength="50" class="form-control" value="<%=nome%>" id="email" placeholder="Nome completo" name="nome"/>
                         </div>
                         <div class="form-group">
                              <label for="">Email</label>
                            <input data-toggle="tooltip" title="Email" type="email" maxlength="50" class="form-control" value="<%=email%>" id="email" placeholder="Email" name="email"/>
                         </div>
                         <div class="form-group">
                              <label for="">Instituição</label>
                            <input data-toggle="tooltip" title="Instituição" type="text" maxlength="50" class="form-control" value="<%=instituicao%>" id="email" placeholder="Instituição" name="instituicao"/>
                         </div>
                         <div class="row">  
                            <div class="form-group col-md-6">
                                <label for="">Senha</label>
                                <input data-toggle="tooltip" title="Senha" type="password" maxlength="18" minlength="6" class="form-control" id="senha" placeholder="Senha" name="senha"/>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="">Repita a senha</label>
                                <input data-toggle="tooltip" title="Repita a Senha" type="password" maxlength="18" minlength="6" class="form-control" id="confsenha" placeholder="Repita a senha" name="r-senha"/>
                            </div>
                        </div>
                        <div class="text-center"> 
                        <button data-toggle="tooltip" title="Submeter" style="width: 200px;" type="submit" class="btn btn-default">Cadastar</button>
                        </div>
                </form>
                     
                <br/>
                <a href="javascript:history.back();" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
          </div>
         <%@include file="footer.jsp" %>
    </body>
</html>
