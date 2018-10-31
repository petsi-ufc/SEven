package br.ufc.pet.comandos.administrador;

import br.ufc.pet.entity.Administrador;
import br.ufc.pet.entity.Evento;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.EventoService;
import br.ufc.pet.util.UtilSeven;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author welligton
 */
public class CmdAlterarEvento implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Administrador admin = (Administrador) session.getAttribute("user");

        String nomeEvento = request.getParameter("nome_evento");
        session.setAttribute("nomeEvento", nomeEvento);
        String siglaEvento = request.getParameter("sigla_evento");
        session.setAttribute("siglaEvento", siglaEvento);
        String descricao = request.getParameter("descricao");
        session.setAttribute("descricao", descricao);
        String tema = request.getParameter("tema_evento");
        session.setAttribute("tema", tema);
        String inicioEvento = request.getParameter("inicio_evento");
        session.setAttribute("inicioEvento", inicioEvento);
        String fimEvento = request.getParameter("fim_evento");
        session.setAttribute("fimEvento", fimEvento);
        String inicioInscricao = request.getParameter("inicio_periodo_inscricao");
        session.setAttribute("inicioInscricao", inicioInscricao);
        String fimInscricao = request.getParameter("fim_periodo_inscricao");
        session.setAttribute("fimInscricao", fimInscricao);
        String limiteDeAtividadesPorParticipante = request.getParameter("limite_de_atividades_por_participante");
        session.setAttribute("limiteDeAtividadesPorParticipante", limiteDeAtividadesPorParticipante);
        String gratuito = request.getParameter("gratuito");
        session.setAttribute("gratuito", gratuito);

//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        Date date = new Date();
//        Date data = UtilSeven.treatToDate(dateFormat.format(date));

        int limiteDeAtividades;

        Evento E = null;
        if (nomeEvento==null || nomeEvento.trim().equals("") || 
            siglaEvento==null || siglaEvento.trim().equals("") ||
            descricao==null || descricao.trim().equals("") ||
            tema==null || tema.trim().equals("") ||
            inicioInscricao==null || inicioInscricao.trim().equals("") || 
            fimInscricao==null || fimInscricao.trim().equals("") ||
            limiteDeAtividadesPorParticipante==null || limiteDeAtividadesPorParticipante.trim().equals("")) {
            session.setAttribute("erro", "Preencha todos os campos");
            session.setAttribute("evento", E);
            return "/admin/edit_events.jsp";
        } /*else if (UtilSeven.validaData(inicioInscricao) != true || UtilSeven.validaData(fimInscricao) != true
                || !UtilSeven.validaData(inicioEvento) || !UtilSeven.validaData(fimEvento)) {
            session.setAttribute("erro", "Data Inválida, digite no formato dd/mm/aaaa");
            return "/admin/edit_events.jsp";
        }*/ else {

            try{
                limiteDeAtividades = Integer.parseInt(limiteDeAtividadesPorParticipante);
            }
            catch(NumberFormatException e){
                session.setAttribute("erro", "Limite de atividades inválido. Por favor digite apenas números.");
                return "/admin/edit_events.jsp";
            }

//            if (UtilSeven.treatToDate(inicioEvento).before(data)) {
//                session.setAttribute("erro", "Data de início do evento anterior a data de hoje.");
//                return "/admin/edit_events.jsp";
//            }
//            if (UtilSeven.treatToDate(inicioEvento).after(UtilSeven.treatToDate(fimEvento))) {
//                session.setAttribute("erro", "Data de início do evento posterior ao término do evento.");
//                return "/admin/edit_events.jsp";
//            }
//            if (UtilSeven.treatToDate(inicioInscricao).before(data)) {
//                session.setAttribute("erro", "Data de início das incrições anterior a data de hoje.");
//                return "/admin/edit_events.jsp";
//            }
//            if (UtilSeven.treatToDate(inicioInscricao).after(UtilSeven.treatToDate(fimEvento))) {
//                session.setAttribute("erro", "Data de início das inscrições posterior ao término do evento.");
//                return "/admin/edit_events.jsp";
//            }
//            if (UtilSeven.treatToDate(inicioInscricao).after(UtilSeven.treatToDate(inicioEvento))) {
//                session.setAttribute("erro", "Data de início das inscrições posterior ao início do evento.");
//                return "/admin/edit_events.jsp";
//            }
//            if (UtilSeven.treatToDate(inicioInscricao).after(UtilSeven.treatToDate(fimInscricao))) {
//                session.setAttribute("erro", "Data de início das inscrições posterior ao término das inscrições.");
//                return "/admin/edit_events.jsp";
//            }
//            if (UtilSeven.treatToDate(fimInscricao).after(UtilSeven.treatToDate(inicioEvento))) {
//                session.setAttribute("erro", "Data de fim das inscrições posterior ao início do evento.");
//                return "/admin/edit_events.jsp";
//            }

                EventoService es = new EventoService();

                

                admin.removerEventoById(E.getId());
                E.setNome(nomeEvento);
                E.setSigla(siglaEvento);
                E.setTema(tema);
                E.setInicioPeriodoEvento(UtilSeven.treatToDate(inicioEvento));
                E.setFimPeriodoEvento(UtilSeven.treatToDate(fimEvento));
                E.setInicioPeriodoInscricao(UtilSeven.treatToDate(inicioInscricao));
                E.setFimPeriodoInscricao(UtilSeven.treatToDate(fimInscricao));
                E.setLimiteAtividadePorParticipante(limiteDeAtividades);
                E.setAdministrador(admin);
                E.setDescricao(descricao);
                
                if(gratuito.equals("true")){
                    E.setGratuito(true);
                }else{ 
                    E.setGratuito(false);
                }
                
                es.atualizar(E);
                admin.addEvento(E);
                
                session.setAttribute("sucesso", "Evento alterado com sucesso");
                

        }
        return "/admin/index.jsp";

    }
}
