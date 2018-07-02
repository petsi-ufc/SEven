package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.evento.Participante;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.ParticipanteService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author Escritorio projetos
 */
public class CmdListarParticipantes implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        ParticipanteService partService = new ParticipanteService();
        ArrayList<Participante> parts;
        String ativId = request.getParameter("ativEscolhida");
        if (ativId != null && !ativId.equals("null")) {
            Long id = Long.parseLong(ativId);
            request.getSession().setAttribute("atividadeSelect", id);
            parts = partService.getParticipanteByAtividadeId(id);
            request.getSession().setAttribute("listPartic", parts);
        } else {
            Evento ev = (Evento) request.getSession().getAttribute("evento");
            parts = partService.getParticipantesByEventoId(ev.getId());
            request.getSession().setAttribute("listPartic", parts);
        }
        return "/org/organ_listar_participantes.jsp";
    }
}
