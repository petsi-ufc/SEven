package br.ufc.pet.comandos.organizador;

import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.AtividadeService;
import br.ufc.pet.services.InscricaoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Caio
 */
public class CmdListarInscritosEmAtividade implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String cod = request.getParameter("aId");
        Long codigo = Long.parseLong(cod);

        InscricaoService is = new InscricaoService();
        session.setAttribute("atividade", new AtividadeService().getAtividadeById(codigo));
        session.setAttribute("inscAtiv", is.getAllInscricoesByAtividadeId(codigo));
        return "/org/organ_gerenciar_inscricoes_atividade.jsp";
    }
}
