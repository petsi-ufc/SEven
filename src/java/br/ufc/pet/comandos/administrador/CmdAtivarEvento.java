package br.ufc.pet.comandos.administrador;

import br.ufc.pet.evento.Administrador;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.EventoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CmdAtivarEvento implements Comando{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        EventoService es = new EventoService();
        Long id = Long.parseLong(request.getParameter("id"));
        if(es.ativar(id)){
            session.setAttribute("sucesso", "Evento ativado com sucesso!");
            Administrador admin = (Administrador) session.getAttribute("user");
            admin.setEventos(es.buscarAllEventos());
            return "/admin/eventos_encerrados.jsp";
        }else{
            session.setAttribute("erro", "Erro ao ativar evento!");
            return "/admin/eventos_encerrados.jsp";
        }
    }
}
