package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.evento.ModalidadeInscricao;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.ModalidadeInscricaoService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Franklin
 */
public class CmdListarTipoModalidade implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Evento evento = (Evento) session.getAttribute("evento");
        ModalidadeInscricaoService modalidade = new ModalidadeInscricaoService();
        ArrayList<ModalidadeInscricao> modalidades = modalidade.getModalidadesInscricaoByEventoId(evento.getId());
        session.setAttribute("modalidades", modalidades);

        return "/org/organ_gerenciar_modalidade.jsp";

    }
}
