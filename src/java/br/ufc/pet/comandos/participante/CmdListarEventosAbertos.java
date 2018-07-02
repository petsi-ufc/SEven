package br.ufc.pet.comandos.participante;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.EventoService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Caio
 */
public class CmdListarEventosAbertos implements Comando{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        EventoService es = new EventoService();

        ArrayList <Evento> ea = es.buscarEventosComInscricoesAbertas();
        HttpSession session = request.getSession();
        session.setAttribute("eventosAbertos", ea);

        return "/part/part_buscar_evento.jsp";
    }

}
