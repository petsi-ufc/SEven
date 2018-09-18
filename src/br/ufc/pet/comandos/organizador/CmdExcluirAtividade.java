package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.AtividadeService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Escritorio projetos
 */
public class CmdExcluirAtividade implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        AtividadeService atividadeService = new AtividadeService();
        String ativ_id = request.getParameter("ativ_id");
        Long id = Long.parseLong(ativ_id);
        if (atividadeService.excluir(id)) {
            Evento ev = (Evento) session.getAttribute("evento");
            ev.removerAtividadeCadastradaById(id);
            session.setAttribute("sucesso", "Atividade excluida com sucesso!");
            return "/org/organ_gerenciar_atividades.jsp?msg=ok_delete";
        } else {
            session.setAttribute("erro", "Atividade nao pode ser excluida!");
            return "/org/organ_gerenciar_atividades.jsp?msg=no_delete";
        }
    }
}
