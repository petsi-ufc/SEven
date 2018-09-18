<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="br.ufc.pet.util.UtilSeven"%>
<%@page import="br.ufc.pet.entity.Atividade"%>
<%@page import="br.ufc.pet.entity.Evento"%>
<%@page import="br.ufc.pet.entity.Horario"%>
<%@page import="br.ufc.pet.entity.Inscricao"%>
<%@page import="br.ufc.pet.entity.ModalidadeInscricao"%>
<%@page import="br.ufc.pet.entity.TipoAtividade"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
    <%@include file="../ErroAutenticacaoUser.jsp" %>
    <%          Inscricao anterior; //recupera a inscricao do caso de uso Editar Inscricao
        if (session.getAttribute("inscricao") != null) {
            anterior = (Inscricao) session.getAttribute("inscricao");
        } else {//para o caso de não ser uma edição, um objeto em branco será usado.
            anterior = new Inscricao();
        }
        //pega evento selecionado da sessão
        Evento e = (Evento) session.getAttribute("eventoSelecionado");
        //gera array com as atividades ofertadas pelo evento
        ArrayList<Atividade> oferta = e.getAtividadeQueAceitamInscricao();
        // for (int i = 0; i < oferta.size(); i++) {
        //   if (!oferta.get(i).isAceitaInscricao()) {
        //     oferta.remove(i);
        //}
        // }
        //gera array que conterá as atividades que o participante vai selecionar
        ArrayList<Atividade> arrayDeSelecionadas = new ArrayList();
        //atividades que ele selecionou anteriormente, caso ele tenha feito isso
        if (session.getAttribute("inscricao") != null) {
            arrayDeSelecionadas.addAll(anterior.getAtividades());
        } //se ele nao selecionou nenhuma anteriormente, o array vai ficar vazio.
        //pega da sessão as modalidades de inscricao
        ArrayList<ModalidadeInscricao> modalidades;
        if (session.getAttribute("modalidades") != null) {
            modalidades = (ArrayList<ModalidadeInscricao>) session.getAttribute("modalidades");
        } else {
            modalidades = new ArrayList();
        }
        //pega array de tipos de atividades da sessão para gerar a tabela de preços
        ArrayList<TipoAtividade> arrayDeTipos = (ArrayList<TipoAtividade>) session.getAttribute("arrayDeTipos");
        //pega da sessão alguma mensagem de erro, caso algum problema retorne para esta pagina
    %>
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
    </head>
    <body>
        <div id="container">
            <%-- Incluindo Menu --%>
            <%@include file="organ_menu.jsp" %>

            <div id="content">
                <%@include file="/error.jsp"%>
                <%if (modalidades.isEmpty()) {
                        session.setAttribute("erro", "Atenção: erro interno - não foram recuperadas as modalidades de inscricao com sucesso.");
                    }
                    if (arrayDeTipos.isEmpty()) {
                        session.setAttribute("erro", "Atenção: erro interno - não foram recuperados os tipos de atividade com sucesso.");
                    }
                %>
                <h1 class="titulo">Inscrição no evento <%=e.getNome()%></h1>
                <div class="panel panel-default">
                    <div class="panel-heading text-center">Tabela de preços</div>
                    <div class="panel-body">
                        <div class="col-lg-12 space-top">
                            <table class="table table-hover text-center">
                                <thead>
                                    <tr>
                                        <th>Tipos de atividades</th>
                                            <%for (ModalidadeInscricao m : modalidades) {%>
                                        <th><%=m.getTipo()%></th>
                                            <%}%>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%for (TipoAtividade t : arrayDeTipos) {%>
                                    <tr>
                                        <td><%=t.getNome()%></td>
                                        <%for (ModalidadeInscricao m : modalidades) {
                                                String p;
                                                p = UtilSeven.precoFormater(UtilSeven.getPrecoTipo(t, m));
                                        %>
                                        <td><%=p%></td>
                                        <%}%>
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <form action="../ServletCentral?comando=CmdAtualizarInscricao" method="post" class="">
                    <div class="panel panel-default">
                        <div class="panel-heading text-center">Personalização da Inscrição</div>
                        <div class="panel-body">  
                            <div class="col-lg-12 space-top">
                                <p>Atividades atualmente selecionadas (não conflitantes):</p>
                                <%if (arrayDeSelecionadas.isEmpty()) {%><%--Utiliza o array de selecionadas--%>
                                <p>Nenhuma atividade selecionada</p>
                                <%} else {%>
                                <table class="table table-hover text-center">
                                    <thead>
                                        <tr>
                                            <th>Nome da Atividade</th>
                                            <th>Remover</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%for (Atividade a : arrayDeSelecionadas) {%><%--Exibindo as atividades selecionadas--%>
                                        <tr>
                                            <td><%=a.getNome()%></td>
                                            <td><a href="../ServletCentral?comando=CmdOrgRemoverAtividade&ativ=<%=a.getId()%>" title="RemoverAtividade">Remover</a></td>
                                        </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                                <%}%>

                                <hr style="height: 10px; border: 0; box-shadow: 0 10px 10px -10px #8c8b8b inset;"/>

                                <%if (oferta == null || oferta.isEmpty()) {%>
                                <p>Este evento não contém atividades selecionáveis.</p>
                                <%} else {%>
                                <p>Oferta de atividades opcionais:</p>
                                <table class="table table-hover text-center">
                                    <thead>
                                        <tr>
                                            <th>Nome da Atividade</th>
                                            <th>Horários</th>
                                            <th>Tipo</th>
                                            <th>Selecionar</th>
                                            <th>Vagas</th>
                                            <th>Vagas Disponíveis</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%for (Atividade a : oferta) {%> <%--Exibindo elementos do array de oferta--%>
                                        <tr>
                                            <td><%=a.getNome()%></td>
                                            <td>
                                                <%for (Horario h : a.getHorarios()) {%>
                                                (<%=h.printHorario()%>)
                                                <%}%>
                                            </td>
                                            <td><%=a.getTipo().getNome()%></td>
                                            <td><a href="../ServletCentral?comando=CmdOrgSelecionarAtividade&ativ=<%=a.getId()%>" title="SelecionarAtividade">Selecionar</a></td>
                                            <%int vagas = a.getVagas();
                                                br.ufc.pet.services.InscricaoService IS = new br.ufc.pet.services.InscricaoService();
                                                long vagasOcupadas = IS.getInscritosByAtividadeId(a.getId());
                                            %>
                                            <td><%=vagas%>
                                            </td>
                                            <td><%=vagas - vagasOcupadas%>
                                            </td>
                                            <%--O link redireciona ao comando, que por sua vez pega o id da atividade em questão e insere a mesma no array das atividades selecionadas--%>
                                        </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                                <%}%>

                                <hr style="height: 10px; border: 0; box-shadow: 0 10px 10px -10px #8c8b8b inset;"/>
                                <div class="radio-inline"
                                     <%if (modalidades.isEmpty()) {%>
                                     <p>Nenhuma modalidade de inscrição cadastrada.</p>
                                    <%} else {%>
                                    <p>Tipo de inscrição:</p><%--Modalidade da inscrição, Estudante ou profissional--%>
                                    <%for (ModalidadeInscricao m : modalidades) {%>
                                    <label<p><input class="radio" type="radio" name="tipo_inscricao" value="<%=m.getId()%>" <%if (m.getId().equals(anterior.getModalidade().getId())) {%> checked="checked" <%}%>/> <%=m.getTipo()%></p></label>
                                    <%}%>
                                    <%}%>
                                    <br/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading text-center">Preços para a seleção atual</div>
                        <div class="panel-body">
                            <div class="col-lg-12 space-top">
                                <%
                                    String preco;
                                    for (ModalidadeInscricao m : modalidades) {
                                        preco = UtilSeven.precoFormater(UtilSeven.getPrecoAtividades(arrayDeSelecionadas, m.getId()));
                                %>
                                <p> <%=m.getTipo()%> : <%=preco%></p>
                                <%}%>
                            </div>
                        </div>
                    </div>
                    <center><input data-toggle="tooltip" title="Atualizar Inscrição" type="submit" value="Atualizar" class=" btn btn-default" /></center>
                </form>
                <a href="" title="" onclick="history.back(); return false;" class="btn btn-default"><span aria-hidden="true">&larr;</span> Voltar</a>
            </div>
            <div class="footer-top">
                <%@include file="../footer.jsp" %>
            </div>
        </div>
    </body>
</html>
