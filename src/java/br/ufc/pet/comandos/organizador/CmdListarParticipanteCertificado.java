package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.evento.Participante;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.ParticipanteService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Welligton
 */
public class CmdListarParticipanteCertificado implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        Evento en= (Evento)session.getAttribute("evento");
        ParticipanteService partServi = new ParticipanteService();
        ArrayList<Participante> listaPart = partServi.getParticipantesQuitesByEventoId(en.getId());
        session.setAttribute("listaParticipantes", listaPart);
        return "/org/organ_participante_certificado.jsp";
    }



}
