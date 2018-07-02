<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="imagens/favicon.png" type="image/x-icon"/>
        <link href="css/estilo.css" rel="stylesheet" type="text/css" />
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>SEven</title>
    </head>
    <body>
        <div id="container">           
            <%@include file="menu_index.jsp"%>    
            <div class="margin-contato">
                <img id="img-centro-pet" src="imagens/PET.png"/>
                <img id="img-centro" src="imagens/favicon.png"/>
            </div>
            
            <div class="centralizar_contato">
                <address>
                    <span class="text-bold">Universidade Federal do Ceará - Campus Quixadá</span><br>
                    Endereço: Av. José de Freitas Queiroz, 5003 – Cedro – Quixadá – Ceará 63902-580<br/>                    
                    <em><a href="mailto: <pet-ufc-si_quixada@googlegroups.com>,"> pet-ufc-si_quixada@googlegroups.com</a></em><br/>
                    <em><a href="mailto: <petufc.quixada@gmail.com>,"> petufc.quixada@gmail.com </a></em>
                </address>
                <div class="fb-page" data-href="https://www.facebook.com/petsiufc/" data-small-header="false" data-adapt-container-width="true" data-hide-cover="false" data-show-facepile="true"><blockquote cite="https://www.facebook.com/petsiufc/" class="fb-xfbml-parse-ignore"><a href="https://www.facebook.com/petsiufc/">PET - Sistemas de Informação UFC</a></blockquote></div>
               
            </div>
        </div>
            
        <%@include file="footer.jsp" %>
     
    </body>
    <div id="fb-root"></div>
    <script>(function(d, s, id) {
      var js, fjs = d.getElementsByTagName(s)[0];
      if (d.getElementById(id)) return;
      js = d.createElement(s); js.id = id;
      js.src = 'https://connect.facebook.net/pt_BR/sdk.js#xfbml=1&version=v2.12';
      fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));</script>
</html>
