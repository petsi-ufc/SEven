package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.TipoAtividade;
import br.ufc.pet.interfaces.Comando;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Escritorio projetos
 */
public class CmdEditarTipoAtividade implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String tipoID = request.getParameter("ta_id");
        TipoAtividade tipoAtiv = null;
       

        ArrayList<TipoAtividade> tipos = (ArrayList<TipoAtividade>) session.getAttribute("tiposAtividades");
        for (TipoAtividade ta : tipos) {
            if (ta.getId().compareTo(Long.parseLong(tipoID)) == 0) {
                tipoAtiv = ta;
                break;
            }
        }
        session.setAttribute("tipoAtividade", tipoAtiv);
        return "/org/organ_add_tipo_ativ.jsp";

    }
}
