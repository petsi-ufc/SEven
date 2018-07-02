package br.ufc.pet.comandos.administrador;

import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.evento.Evento;
import br.ufc.pet.services.EventoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author ismaily
 */
public class CmdListarOrganizadorEventos implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        Long id = Long.parseLong(request.getParameter("idEvento"));
        EventoService eve = new EventoService();
        Evento en = eve.getEventoById(id);
        session.setAttribute("evento", en);
        return "/admin/organ_listar_movimentacao.jsp";
    }
}
