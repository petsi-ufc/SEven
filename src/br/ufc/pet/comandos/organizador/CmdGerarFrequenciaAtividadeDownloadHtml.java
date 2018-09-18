package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Atividade;
import br.ufc.pet.evento.Horario;
import br.ufc.pet.evento.Participante;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.AtividadeService;
import br.ufc.pet.services.EventoService;
import br.ufc.pet.services.ParticipanteService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Welligton Abreu
 */
public class CmdGerarFrequenciaAtividadeDownloadHtml implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("idAtvDw"));
        Atividade at = new AtividadeService().getAtividadeById(id);
        at.setEvento(new EventoService().getEventoById(at.getEvento().getId()));
        session.setAttribute("atividade", at);

        ParticipanteService partser = new ParticipanteService();
        ArrayList<Participante> parts = partser.getParticipanteByAtividadeId(id);
        session.setAttribute("participantes", parts);

        if (parts == null || parts.isEmpty()) {
            session.setAttribute("erro", "Sem participantes no Momento");
            return "/org/organ_listar_atividades_frequencia.jsp";
        }

        ArrayList<Horario> horarios = at.getHorarios();
        if (horarios == null || horarios.isEmpty()) {
            session.setAttribute("erro", "Horários não definidos!");
            return "/org/organ_listar_atividades_frequencia.jsp";
        }

        return "/org/organ_listar_atividades_frequencia_html.jsp";
    }

}
