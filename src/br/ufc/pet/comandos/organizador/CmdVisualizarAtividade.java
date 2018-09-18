package br.ufc.pet.comandos.organizador;

import br.ufc.pet.entity.Atividade;
import br.ufc.pet.entity.Evento;
import br.ufc.pet.interfaces.Comando;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Escritorio projetos
 */
public class CmdVisualizarAtividade implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session=request.getSession(true);


       String ativ_id = request.getParameter("ativ_id");
        Long id = Long.parseLong(ativ_id);
        Evento eventoSessao = (Evento) session.getAttribute("evento");
        Atividade atividade = eventoSessao.recuperarAtividadeCadastrada(id);
        Collections.sort(atividade.getHorarios());
        session.setAttribute("atividade", atividade);
        return "/org/organ_visualizar_atividade.jsp";
    }
}
