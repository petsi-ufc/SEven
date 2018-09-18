package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.TipoAtividade;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.TipoAtividadeService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Escritorio projetos
 */
public class CmdExcluirTipoAtividade implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String tipoID = request.getParameter("ta_id");
        TipoAtividadeService tas = new TipoAtividadeService();
        if (tas.excluir(Long.parseLong(tipoID))) {
            session.setAttribute("sucesso", "Tipo de Atividade removido com sucesso!");
            ArrayList<TipoAtividade> tipos = (ArrayList<TipoAtividade>) session.getAttribute("tiposAtividades");
            for (int i = 0; i < tipos.size(); i++) {
                if (tipos.get(i).getId().compareTo(Long.parseLong(tipoID)) == 0) {
                    tipos.remove(i);
                }
            }
        } else {
            session.setAttribute("erro", "Tipo de Atividade não pode ser removido!");
        }
        return "/org/organ_gerenciar_tipo_ativ.jsp";

    }
}
