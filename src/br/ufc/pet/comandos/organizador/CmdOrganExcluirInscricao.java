package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.InscricaoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Caio
 */
public class CmdOrganExcluirInscricao implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        InscricaoService is = new InscricaoService();
        is.excluir(Long.parseLong(request.getParameter("iId")));

        Evento e = (Evento) session.getAttribute("evento");
        if (e != null) {
            session.setAttribute("inscricoes", is.getAllInscricoesByEventoId(e.getId()));
            session.setAttribute("sucesso", "Inscrição excluída com sucesso.");
            return "/org/organ_gerenciar_inscricoes.jsp";
        }
        return "/org/index.jsp";
    }
}
