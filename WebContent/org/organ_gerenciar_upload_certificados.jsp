<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="br.ufc.pet.entity.Atividade,br.ufc.pet.entity.Organizador,br.ufc.pet.entity.Organizacao,br.ufc.pet.entity.ResponsavelAtividade" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
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
        <script type="text/javascript">
            function checkFile() {
                var fileElement = document.getElementById("uploadFile");
                var fileExtension = "";
                if (fileElement.value.lastIndexOf(".") > 0) {
                    fileExtension = fileElement.value.substring(fileElement.value
                            .lastIndexOf(".") + 1, fileElement.value.length);
                }
                if (fileExtension == "jpg") {
                    return true;
                } else {
                    alert("Você deve selecionar um arquivo no formarto 'jpg' para upload.");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <%            br.ufc.pet.entity.Evento e = (br.ufc.pet.entity.Evento) session.getAttribute("evento");
            Organizador organizador = (Organizador) session.getAttribute("user");
            ArrayList<Atividade> ats = e.getAtividades();
        %>
        <div id="container">
            <%-- Incluindo o Menu --%>
            <%@include file="organ_menu.jsp"%>

            <div id="content">
                <h1 class="titulo">Gerenciar emissão de certificados<br/><%=e.getNome()%></h1>
                <%@include file="../error.jsp" %>
                <div class="panel panel-warning">
                    <div class="panel-heading text-center">Atenção!<br/>Deve ser feito o upload de um template seguindo os passos abaixo</div>
                    <div class="panel-body">
                        <ol>
                            <li>Faça o download do template de exemplo <a href="../resources/template.pptx"><span class="label label-info">AQUI</span></a><br/></li>
                            <li>Abra com o Power Point</li>
                            <li>Edite o modelo de certificado ao seu gosto, porém deixe as caixas pretas como estão.</li>
                            <li>Depois apague as caixas pretas. (O sistema irá preencher esses espaços com informações do participante)</li>
                            <li>Salve o tempate em formato <em>.jpg</em></li>
                            <li>Faça o upload do template.</li>
                        </ol>
                    </div>
                </div>  
                <form action="../ServletCentral?comando=CmdUploadModeloCertificado&id_evento=<%=e.getId()%>" method="post" enctype="multipart/form-data" onsubmit="return checkFile();">
                    <div class="form-group">
                        <input data-toggle="tooltip" title="Selecione um Template" type="file" name="arquivo" id="uploadFile"/>
                    </div>
                    <input data-toggle="tooltip" title="Enviar Template" type="submit" value="Enviar" class="btn btn-default pull-right"/>
                    <a href="" title="" onclick="history.back(); return false;" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
                </form>
               
            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
