package br.ufc.pet.comandos.organizador;

import br.ufc.pet.interfaces.Comando;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Escritorio projetos
 */
public class CmdListarAtividades implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        return "/org/organ_gerenciar_atividades.jsp";
    }
}
