<div id="error_msg">
    <%
                String sucesso = (String) session.getAttribute("sucesso");
                String erro = (String) session.getAttribute("erro");
                session.removeAttribute("sucesso");
                session.removeAttribute("erro");

                if (erro != null) {%>
    <div class="alert alert-danger alert-dismissable fade in">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
         <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
         <span class="sr-only">Error:</span> 
        <%=erro%>
    </div>
    <%}
                if (sucesso != null) {%>
    <div class="alert alert-success alert-dismissable">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
        <span class="sr-only">Success:</span> 
        <%=sucesso%>
    </div>
    <%}%>
</div>