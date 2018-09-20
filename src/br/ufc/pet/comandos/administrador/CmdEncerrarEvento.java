package br.ufc.pet.comandos.administrador;

import br.ufc.pet.entity.Administrador;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.EventoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author ismaily
 */
public class CmdEncerrarEvento implements Comando{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        EventoService es= new EventoService();
        Long id= Long.parseLong(request.getParameter("id"));
        if(es.encerrar(id)){
            session.setAttribute("sucesso", "Evento encerrado com sucesso!");
            Administrador admin = (Administrador) session.getAttribute("user");
            admin.setEventos(es.buscarAllEventos());
        }else{
            session.setAttribute("erro", "Erro ao encerrar evento!");
        }
        return "/admin/index.jsp";
    }
}
