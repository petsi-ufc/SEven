package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Atividade;
import br.ufc.pet.evento.Evento;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.AtividadeService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author ismaily
 */
public class CmdListarAtividadesDownload implements Comando{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {

    HttpSession session = request.getSession(true);
        Evento en= (Evento)session.getAttribute("evento");
        AtividadeService ats = new AtividadeService();
        ArrayList<Atividade> ativis = ats.getAtividadeByEventoId(en.getId());
        session.setAttribute("atividades", ativis);
        return "/org/organ_listar_atividades_download.jsp";
    }
}
