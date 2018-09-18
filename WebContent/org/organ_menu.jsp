<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.ufc.pet.entity.Evento"%>

<%            Evento eventSelected = (Evento) session.getAttribute("evento");
%>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">
                <img style="width: 78px; margin-top: -10px;" alt="Logo"  src="../imagens/Seven.png"/>
            </a>
        </div>
        
        <ul class="nav navbar-nav">
            <li><a href="../ServletCentral?comando=CmdRecarregarEventosOrganizador" title="Página Inicial">Home</a></li>
        <!--<li><a href="organ_selecionar_evento.jsp" title="Gerenciar Atividades">Eventos e Atividades</a></li>-->
        <li><a href="../ServletCentral?comando=CmdGerenciarEvento&cod_evento=<%= eventSelected.getId()%>">Eventos e Atividades</a></li>
        <li><a href="../ServletCentral?comando=CmdListarMovimentacaoFinanceira" title="Módulo Financeiro">Financeiro</a></li>
        <!--<li><a href="organ_inscricoes.jsp" title="Gerenciar Inscrições">Inscrições</a></li>-->
        <li><a href="../ServletCentral?comando=CmdGerenciarInscricoes&cod_evento=<%= eventSelected.getId()%>">Inscrições</a></li>
        <li><a href="organ_relatorios.jsp" title="Relatórios">Relatórios</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="../ServletCentral?comando=CmdLogout" title="Logout"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Sair</a></li>
        </ul>
    </div>
</nav>
