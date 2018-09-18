package br.ufc.pet.comandos.participante;

import br.ufc.pet.evento.Participante;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.InscricaoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Caio
 */
public class CmdExcluirInscricao implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        InscricaoService is = new InscricaoService();
        is.excluir(Long.parseLong(request.getParameter("iId")));
        Participante p = (Participante) session.getAttribute("user");
        p.setInscricoes(is.getAllInscricaoByParticipanteId(p.getId()));
        session.setAttribute("user", p);
        session.setAttribute("sucesso", "Inscrição excluída com sucesso.");
        return "/part/part_visualizar_inscricoes.jsp";
    }
}
