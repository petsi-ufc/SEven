package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Evento;
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
public class CmdAdicionarTipoAtividade implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Evento evento = (Evento) session.getAttribute("evento");
        String tipoNme = request.getParameter("tipo_nome");
        TipoAtividade taEdit = (TipoAtividade) session.getAttribute("tipoAtividade");
        session.removeAttribute("tipoAtividade");

        TipoAtividadeService tas = new TipoAtividadeService();

        if (tipoNme == null || tipoNme.equals("")) {
            session.setAttribute("erro", "Preencha todos os campos obrigatórios.");
            return "/org/organ_add_tipo_ativ.jsp";
        } else {
            if (taEdit != null) {
                taEdit.setNome(tipoNme.trim());
                if (tas.atualizar(taEdit)) {
                    session.setAttribute("sucesso", "Tipo de Atividade atualizado com sucesso!");
                } else {
                    session.setAttribute("erro", "Falha na atualização!");
                }

                return "/org/organ_gerenciar_tipo_ativ.jsp";
            } else {
                TipoAtividade tipoNovo = new TipoAtividade();
                tipoNovo.setNome(tipoNme.trim());
                tipoNovo.setEventoId(evento.getId());
                tas.adicionar(tipoNovo);
                ArrayList<TipoAtividade> tipos = (ArrayList<TipoAtividade>) session.getAttribute("tiposAtividades");
                tipos.add(tipoNovo);
                session.setAttribute("sucesso", "Tipo de Atividade adcicionado com sucesso.");
                return "/org/organ_gerenciar_tipo_ativ.jsp";
            }
        }
    }
}
