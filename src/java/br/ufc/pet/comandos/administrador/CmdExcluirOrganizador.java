package br.ufc.pet.comandos.administrador;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.evento.Organizacao;
import br.ufc.pet.evento.Organizador;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.OrganizacaoService;
import br.ufc.pet.services.OrganizadorService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author ismaily
 */
public class CmdExcluirOrganizador implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Evento en = (Evento) session.getAttribute("evento");
        Long id = Long.parseLong(request.getParameter("idUsuario"));
        int i = 0;
        OrganizadorService orgS = new OrganizadorService();
        Organizador org = orgS.getByUsuarioId(id);
        Organizacao orga = new Organizacao();
        orga.setEvento(en);
        orga.setOrganizador(org);
        OrganizacaoService orgaS = new OrganizacaoService();
        if (org.deleteOrganizacao(en)) {
            if (orgaS.delete(orga)) {
                if (en.deleteOrganizador(org)) {
                    session.setAttribute("evento", en);
                    session.setAttribute("sucesso", "Excluído com sucesso!");
                    return "/admin/organ_listar_movimentacao.jsp";
                }
            }
        }
        session.setAttribute("erro", "Erro ao Excluir!");
        return "/admin/organ_listar_movimentacao.jsp";
    }
}
