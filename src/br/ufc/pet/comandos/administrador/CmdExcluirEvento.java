package br.ufc.pet.comandos.administrador;

import br.ufc.pet.evento.Administrador;
import br.ufc.pet.evento.Evento;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.EventoService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author welligton
 */
public class CmdExcluirEvento implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);

        Administrador admin = (Administrador) session.getAttribute("user");
        String codigo = request.getParameter("id");
        Long cod = Long.parseLong(codigo);
        EventoService s = new EventoService();
        Evento e = s.getEventoById(cod);
        ArrayList<Evento> evts = admin.getEventos();
        if(e.getOrganizadores().size() > 0){
            session.setAttribute("erro", "Evento com Organizadores, remova-os antes de proseguir");
            return "/admin/index.jsp";
        }
        if (s.excluir(e)) {
            for (int i = 0; i < evts.size(); i++) {
                if (evts.get(i).getId().equals(cod)) {
                    evts.remove(i);
                }
            }
            admin.setEventos(evts);
            session.setAttribute("user", admin);
            session.setAttribute("sucesso", "Evento excluído com sucesso");
        } else {
            session.setAttribute("erro", "Erro ao excluir evento");
        }

        return "/admin/index.jsp";
    }
}
