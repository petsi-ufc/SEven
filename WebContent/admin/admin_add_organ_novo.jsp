<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.entity.Organizador"%>
<%@page import="br.ufc.pet.entity.Evento" %>
<%@page import="br.ufc.pet.util.UtilSeven"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <%
            Evento en = (Evento) session.getAttribute("evento");
            Organizador o = (Organizador) session.getAttribute("organizador");
            session.removeAttribute("organizador");
                
            String nome = (String) session.getAttribute("nome");
            session.removeAttribute("nome");
            if(nome==null)
                nome="";
            
            String rua = (String) session.getAttribute("rua");
            session.removeAttribute("rua");
            if(rua==null)
                rua="";
            
            String bairro = (String) session.getAttribute("bairro");
            session.removeAttribute("bairro");
            if(bairro==null)
                bairro="";
            
            String sexo = (String) session.getAttribute("sexo");
            session.removeAttribute("sexo");
            if(sexo==null)
                sexo="";
            
            String email = (String) session.getAttribute("email");
            session.removeAttribute("email");
            if(email==null)
                email="";
            
            String telefone = (String) session.getAttribute("telefone");
            session.removeAttribute("telefone");
            if(telefone==null)
                telefone="";
            
            String data = (String) session.getAttribute("data");
            session.removeAttribute("data");
            if(data==null)
                data="";
            
            String instituicao = (String) session.getAttribute("instituicao");
            session.removeAttribute("instituicao");
            if(instituicao==null)
                instituicao="";
            
            String uf = (String) session.getAttribute("uf");
            session.removeAttribute("uf");
            if(uf==null)
                uf="";
            
            String cidade = (String) session.getAttribute("cidade");
            session.removeAttribute("cidade");
            if(cidade==null)
                cidade="";
            
            String numero = (String) session.getAttribute("numero");
            session.removeAttribute("numero");
            if(numero==null)
                numero="";
            
            if(o != null){
                nome = o.getUsuario().getNome();
                rua = o.getUsuario().getRua();
                bairro = o.getUsuario().getBairro();
                sexo = o.getUsuario().getSexo();
                email = o.getUsuario().getEmail();
                telefone = o.getUsuario().getFone();
                data = UtilSeven.treatToString(o.getUsuario().getDataNascimento());
                instituicao = o.getUsuario().getInstituicao();
                uf = o.getUsuario().getUf();
                cidade = o.getUsuario().getCidade();
                numero = o.getUsuario().getNumero();
            }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/> 
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
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
        <script type="text/javascript"  language="javascript" src="../Script.js"></script>
    </head>
    <body>
                 
        <div id="container">     
          <%-- Incluindo o Menu --%>
          <%@include file="admin_menu.jsp" %>  
           <div id="content">
             <%@include file="/error.jsp"%>   
             <div class="panel panel-default">
                 <div class="panel-heading text-center"><%=en.getNome()%><br/> Dados do organizador</div>
                <div class="panel-body"> 
                       
            <form action="../ServletCentral" method="post">
                <input type="hidden" name="comando" value="CmdAddOrganizadorNovo"/>    
                <div class="row">   
                     <div class="col-lg-offset-1 col-lg-5 space-top">                 
                         <div class="pull-left text-uppercase label label-warning">* Campos obrigatórios</div><br/>
                        <div class="form-group">
                            <input data-toggle="tooltip" title="Nome Completo" class="form-control" value="<%=nome%>" type="text" name="nome" placeholder="* Nome" maxlength="50" />
                        </div>
                        
                        <div class="form-group">
                            <input data-toggle="tooltip" title="Email" class="form-control" value="<%=email%>" type="text" name="email"  placeholder="* E-mail"  maxlength="50" />
                        </div>
                        
                        <div class="form-group">
                            <input data-toggle="tooltip" title="Telefone" class="form-control" value="<%=telefone%>" type="text" name="fone" onkeypress="return formataContato(this , event)"  placeholder="Telefone" maxlength="13" />
                        </div>
                                                
                        <div class="form-group input-group input-append date">
                                        <input data-toggle="tooltip" title="Data de Nascimento" placeholder="Data de Nascimento" type="text" name="data" value="<%=data%>" id="box_datenasc" onkeypress="return formataData(this,event)" maxlength="10" class="form-control"/>
                                        <span class="input-group-addon" id="datenasc_up" >
                                            <button name="dt_nascimento" id="datenasc" class="btn btn-outline-secondary glyphicon glyphicon-calendar form-control">
                                            </button>
                                        </span>
                                    </div>
                                        
                        <div class="form-group">                                      
                            <select class="form-control" id="sexo" name="sexo">
                                <option value="null" selected>Selecione o sexo</option>
                                <option value="Masculino">Masculino</option>
                                <option value="Feminino">Feminino</option>
                            </select>
                        </div>                   
                    
                        <div class="form-group">
                            <input data-toggle="tooltip" title="Instituição" class="form-control" value="<%=instituicao%>" type="text"  placeholder="Instituição" maxlength="50" name="instituicao" />
                        </div>
                                 
                   
                        <div class="form-group">
                            <input data-toggle="tooltip" title="Rua" class="form-control" value="<%=rua%>" type="text"  placeholder="* Rua" maxlength="50" name="rua" />
                        </div>
                        
                        <div class="form-group">
                            <input data-toggle="tooltip" title="Bairro" class="form-control" value="<%=bairro%>" type="text"  placeholder="* Bairro" maxlength="50" name="bairro" />
                        </div>                                                  
                </div>
                <div class="col-lg-5 space-top">  
                    <br/>
                    <div class="form-group">                                        
                        <input data-toggle="tooltip" title="Número/Complemento" placeholder="* Número/Complemento" type="text" maxlength="12" value="<%=numero%>" name="numero" class="form-control" required/>
                    </div>
                                                                     
                    <div class="form-group">
                        <select id="estados" data-toggle="tooltip" title="Estado" name="uf" value="<%=uf%>" class="form-control" required>
                            <option value=""></option>
                        </select>
                    </div>
                                    
                    <div class="form-group">
                        <select id="cidades" data-toggle="tooltip" title="Cidade" name="cidade" value="<%=cidade%>" class="form-control" required>
                                      
                        </select>
                    </div>
                    
<!--                        <div class="form-group">
                            <input data-toggle="tooltip" title="Cidade" class="form-control" value="<%=cidade%>" type="text"  placeholder="* Cidade" maxlength="50" name="cidade" />
                        </div>                       
                  
                        <div class="form-group">
                            <input data-toggle="tooltip" title="UF" class="form-control" value="<%=uf%>" type="text"  placeholder="* UF" maxlength="50" name="uf" />
                        </div>  -->
                     
                        <div class="form-group">
                            <input data-toggle="tooltip" title="Senha" class="form-control" type="password"  placeholder="* Senha" maxlength="18" minlength="6" name="senha" />
                        </div>
                        
                        <div class="form-group">
                            <input data-toggle="tooltip" title="Repita a Senha" class="form-control" type="password"  placeholder="* Digite a senha novamente" maxlength="18" minlength="6" name="confirmacaoSenha" />
                        </div>
                        
                     <div class="panel panel-default">
                         <div class="panel-heading text-center">Permissões</div>
                       <div class="panel-body">  
                         <div class="col-lg-12"> 
                             <label><input type="checkbox" name="manterAtividade" class="chk_box"/> Manter Atividade</label><br/>
                             <label><input type="checkbox" name="manterModulo" class="chk_box"/> Manter Módulo Financeiro</label><br/>                 
                        </div>
                       </div> 
                         <input data-toggle="tooltip" title="Enviar" type="submit" value="Enviar" class="btn btn-default pull-right space-top" onclick="return confirmarCadastrado()"/>
                      </div>           
                  </div>                    
                </div>                    
               </form>
                    
              </div>
             </div>  
             <a href="" title=""  class="btn btn-default" onclick="history.back(); return false;" class="voltarCadastro">← Voltar</a>
             
           </div>
        </div>              
        <div class="footer-top">         
             <%@include file="../footer.jsp" %>
        </div>  
    </body>
</html>
