package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.evento.TipoAtividade;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.TipoAtividadeService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author Escritorio projetos
 */
public class CmdListarTipoAtividade implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {

        Evento evento = (Evento) request.getSession().getAttribute("evento");
        TipoAtividadeService tas = new TipoAtividadeService();
        ArrayList<TipoAtividade> tiposAtividades = tas.getTiposDeAtividadesByEventoId(evento.getId());
        request.getSession().setAttribute("tiposAtividades", tiposAtividades);
        return "/org/organ_gerenciar_tipo_ativ.jsp";
    }
}
