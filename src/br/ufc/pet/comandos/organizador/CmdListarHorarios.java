package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.evento.Horario;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.HorarioService;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author Escritorio projetos
 */
public class CmdListarHorarios implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        Evento evento = (Evento) request.getSession().getAttribute("evento");
        HorarioService hs = new HorarioService();
        ArrayList<Horario> horarios = hs.getHorariosByEventoId(evento.getId());
        Collections.sort(horarios);
        request.getSession().setAttribute("horarios", horarios);
        return "/org/organ_gerenciar_horario.jsp";
    }
}
