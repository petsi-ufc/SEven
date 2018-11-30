package br.ufc.pet.comandos.organizador;

import br.ufc.pet.entity.Organizador;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.OrganizacaoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author fernando
 */
public class CmdRecarregarEventosOrganizador implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Organizador organizador = (Organizador) session.getAttribute("user");

        OrganizacaoService os = new OrganizacaoService();
        organizador.setOrganizacoes(os.getAllOrganizacoesByOrganizadorId(organizador.getId()));

        return "/org/index.jsp";
    }

}
