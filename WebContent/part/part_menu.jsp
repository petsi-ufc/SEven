<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">
                <img style="width: 78px; margin-top: -10px;" alt="Logo"  src="../imagens/Seven.png"/>
            </a>
        </div>
        
        <ul class="nav navbar-nav">
            <li><a href="index.jsp" title="Página Inicial">Home</a></li>
            <li><a href="../ServletCentral?comando=CmdListarEventosAbertos" title="Inscrições">Nova Inscrição</a></li>
            <li><a href="part_visualizar_inscricoes.jsp" title="Inscrições">Minhas Inscrições</a></li>
            <li><a href="gerencia_conta.jsp" title="Editar Dados">Gerenciar Conta</a></li>
            <li><a href="part_edit_senha.jsp" title="Alterar Senha">Alterar Senha</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="../ServletCentral?comando=CmdLogout" title="Logout"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Sair</a></li>
        </ul>
    </div>
</nav>