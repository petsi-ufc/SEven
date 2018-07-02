package br.ufc.pet.comandos.participante;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.EventoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Welligton Abreu
 */
public class CmdVisualizarProgramacao implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        EventoService evento = new EventoService();
        Evento e = evento.getEventoById(Long.parseLong(request.getParameter("id")));
        session.setAttribute("evento", e);
        return "/visualizar_programacao.jsp";
    }
}
