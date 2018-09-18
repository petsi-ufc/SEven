package br.ufc.pet.comandos.administrador;

import br.ufc.pet.evento.Administrador;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.EventoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author ismaily
 */
public class CmdListarEventos implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {

       HttpSession session = request.getSession(true);
       Administrador admin = (Administrador) session.getAttribute("user");
       EventoService es = new EventoService();
       admin.setEventos(es.buscarEventosAbertos());
       session.removeAttribute("evento");
       return "/admin/manege_events.jsp";
    }

}
