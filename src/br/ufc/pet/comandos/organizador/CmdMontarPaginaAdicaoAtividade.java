package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.ResponsavelAtividade;
import br.ufc.pet.interfaces.Comando;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Escritorio projetos
 */
public class CmdMontarPaginaAdicaoAtividade implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("atividade");
        ArrayList<ResponsavelAtividade> responsaveis = new ArrayList<ResponsavelAtividade>();
        session.setAttribute("responsaveisEscolhidos", responsaveis);
        return "/org/organ_add_atividades.jsp";
    }
}
