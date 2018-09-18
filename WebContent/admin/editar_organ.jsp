<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.evento.Evento" %>
<%@page import="br.ufc.pet.evento.Usuario" %>
<%@page import="br.ufc.pet.evento.Organizacao" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <%Evento event = (Evento) session.getAttribute("evento");
                Usuario u = (Usuario) session.getAttribute("uEditar");
                Organizacao org = (Organizacao) session.getAttribute("organizacao");
                session.removeAttribute("organizacao");
                String manterAtvi = "";
                String manterMod = "";
                if (org != null) {
                    boolean matAt = org.getManterAtividade();
                    boolean matMod = org.getManterModuloFinanceiro();
                    if (matAt) {
                        manterAtvi = "checked";
                    }
                    if (matMod) {
                        manterMod = "checked";
                    }
                }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="shortcut icon" href="../imagens/favicon.png" type="image/x-icon"/>
        <link href="../css/estilo.css" rel="stylesheet" type="text/css" />
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>
        <script language="javascript" src="../jquery/jquery-1.10.2.js"></script>
        <script language="javascript" src="../jquery/jquery-ui-1.10.4.custom.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../Script.js"> </script>
        
    </head>
    <body>
        <div id="container">
           
                <%-- Incluindo o Menu --%>
                <%@include file="admin_menu.jsp" %>
        
            <div id="content">  
            
                <form action="../ServletCentral" method="post" class="cadastro">
                    <input type="hidden" name="comando" value="CmdEditarOrganizador"/>
                    
                 <div class="space-top panel panel-default">
                  <div class="panel-heading text-center">Permissão do usúario</div>
                   <div class="panel-body">  
                       <div class="col-lg-12 space-top">                   
                        <label>Nome:</label>  <%=u.getNome()%><br />
                        <label>Email:</label> <%=u.getEmail()%><br />
                        <label>Telefone:</label> <%=u.getFone()%><br />
                        <label>Instituição:</label> <%=u.getInstituicao()%><br/>
                        <label>Cidade:</label> <%=u.getCidade()%></label><br />
                        <label><input type="checkbox"  <%=manterAtvi%> name="manterAtv" class="chk_box" /> Manter Atividade</label><br/>
                        <label><input type="checkbox"  <%=manterMod%> name="manterMod" class="chk_box" /> Mater Módulo Financeiro</label><br/><br/>
                      </div> 
                   </div>
                  </div>   
                    <div class="text-center">
                     <button data-toggle="tooltip" title="Incluir Organizador" type="submit" class="btn btn-default">Incluir organizador</button> 
                    </div>
                </form> 
                   
                <a href="" title="" onclick="history.back(); return false;" class="btn btn-default pull-left">← Voltar</a>
          </div>
                
        </div>
              <div class="footer-top"><%@include file="../footer.jsp" %></div>                
    </body>
</html>
