package br.ufc.pet.comandos;

import br.ufc.pet.interfaces.Comando;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Gleyson
 */
public class CmdLogout implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "/index.jsp";
    }
}
