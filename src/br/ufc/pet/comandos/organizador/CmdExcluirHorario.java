package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Horario;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.HorarioService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Escritorio projetos
 */
public class CmdExcluirHorario implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String horarioID = request.getParameter("hor_id");
        HorarioService hs = new HorarioService();
        if (hs.excluir(Long.parseLong(horarioID))) {
            session.setAttribute("sucesso", "Horário removido com sucesso!");
            ArrayList<Horario> hors = (ArrayList<Horario>) session.getAttribute("horarios");
            for (int i=0;i<hors.size();i++) {
                if (hors.get(i).getId().compareTo(Long.parseLong(horarioID)) == 0) {
                    hors.remove(i) ;
                }
            }
        } else {
            session.setAttribute("erro", "Horário não pode ser removido!");
        }

        return "/org/organ_gerenciar_horario.jsp";
    }
}
