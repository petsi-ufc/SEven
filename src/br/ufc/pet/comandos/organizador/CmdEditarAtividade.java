package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Atividade;
import br.ufc.pet.evento.Evento;
import br.ufc.pet.evento.ResponsavelAtividade;
import br.ufc.pet.interfaces.Comando;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Escritorio projetos
 */
public class CmdEditarAtividade implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String ativ_id = request.getParameter("ativ_id");
        Long id = Long.parseLong(ativ_id);
        Evento ev = (Evento) session.getAttribute("evento");
        Atividade atividade = ev.recuperarAtividadeCadastrada(id);

        ArrayList<ResponsavelAtividade> responsaveis = atividade.getResponsaveis();
        session.setAttribute("atividade", atividade);
        session.setAttribute("responsaveisEscolhidos", responsaveis);

        return "/org/organ_add_atividades.jsp";
    }
}
