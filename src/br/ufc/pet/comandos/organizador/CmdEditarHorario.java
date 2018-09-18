package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Horario;
import br.ufc.pet.interfaces.Comando;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Escritorio projetos
 */
public class CmdEditarHorario implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String horarioID = request.getParameter("hor_id");
        Horario horario = null;

        ArrayList<Horario> hors = (ArrayList<Horario>) session.getAttribute("horarios");
        for (Horario h : hors) {
            if (h.getId().compareTo(Long.parseLong(horarioID)) == 0) {
                horario = h;
                break;
            }
        }
        session.setAttribute("horario", horario);
        return "/org/organ_add_horario.jsp";
    }
}
