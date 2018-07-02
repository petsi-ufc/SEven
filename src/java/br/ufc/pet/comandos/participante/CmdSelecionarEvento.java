package br.ufc.pet.comandos.participante;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.evento.Inscricao;
import br.ufc.pet.evento.ModalidadeInscricao;
import br.ufc.pet.evento.Participante;
import br.ufc.pet.evento.TipoAtividade;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.EventoService;
import br.ufc.pet.services.InscricaoService;
import br.ufc.pet.services.ModalidadeInscricaoService;
import br.ufc.pet.services.TipoAtividadeService;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Caio
 */
public class CmdSelecionarEvento implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Participante p = (Participante) session.getAttribute("user");


        String id = request.getParameter("id");

        EventoService es = new EventoService();
        Evento ev;

        //Verifica se o id que foi n?o ? nulo e ? um numero passado como parametro ? valido
        try{
            ev = es.getEventoById(Long.parseLong(id));
        }catch(NullPointerException e){
            session.setAttribute("erro", "Evento inválido");
            return "/part/part_buscar_evento.jsp";

        }catch(NumberFormatException e){
            session.setAttribute("erro", "Evento inválido");
            return "/part/part_buscar_evento.jsp";
        }


        if(ev == null){
            session.setAttribute("erro", "Evento inválido");
            return "/part/part_buscar_evento.jsp";
        }

        //Verifica a se est? no per?odo de inscri??es
        Date hoje = new Date();
        if (hoje.before(ev.getInicioPeriodoInscricao())){
            session.setAttribute("erro", "Ops!, as inscrições para este evento ainda não começaram!");
            return "/part/part_buscar_evento.jsp";
        }
        if (hoje.after(ev.getFimPeriodoInscricao())){
            session.setAttribute("erro", "Ops!, as inscrições para este evento ja terminaram!");
            return "/part/part_buscar_evento.jsp";
        }

        InscricaoService is = new InscricaoService();
        ArrayList<Inscricao> inscricoesPart = is.getAllInscricaoByParticipanteId(p.getId());

        ModalidadeInscricaoService ms = new ModalidadeInscricaoService();
        ArrayList<ModalidadeInscricao> modalidades = ms.getModalidadesInscricaoByEventoId(ev.getId());

        TipoAtividadeService ts = new TipoAtividadeService();
        ArrayList<TipoAtividade> arrayDeTipos= ts.getTiposDeAtividadesByEventoId(ev.getId());

        //limpagem da montagem anterior, caso essa pagina tenha vindo de um "voltar"
        if (session.getAttribute("inscricao") != null) {
            session.removeAttribute("inscricao");
        }
        if (session.getAttribute("selecionadas") != null) {
            session.removeAttribute("selecionadas");
        }

        for(Inscricao i : inscricoesPart){
            if(i.getEvento().getId().equals(ev.getId())){
                session.setAttribute("erro", "Seleção inválida, você já se inscreveu neste evento.");
                return "/part/part_buscar_evento.jsp";
            }
        }
        session.setAttribute("arrayDeTipos", arrayDeTipos);
        session.setAttribute("eventoSelecionado", ev);
        session.setAttribute("modalidades", modalidades);

        return "/part/part_fazer_inscricao.jsp";
    }
}
