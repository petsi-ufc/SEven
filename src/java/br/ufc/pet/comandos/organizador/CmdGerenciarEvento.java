package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.EventoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author fernando
 */
public class CmdGerenciarEvento implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String cod = request.getParameter("cod_evento");

        Long codigo = Long.parseLong(cod);

        EventoService es = new EventoService();
        Evento e = es.getEventoById(codigo);

        if (e != null) {
            session.setAttribute("evento", e);
            return "/org/organ_gerenciar_atividades.jsp";
        }

        return "/org/index.jsp";
    }
}
