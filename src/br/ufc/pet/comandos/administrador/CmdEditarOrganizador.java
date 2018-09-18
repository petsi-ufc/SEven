package br.ufc.pet.comandos.administrador;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.evento.Organizacao;
import br.ufc.pet.evento.Organizador;
import br.ufc.pet.evento.Usuario;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.OrganizacaoService;
import br.ufc.pet.services.OrganizadorService;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author ismaily
 */
public class CmdEditarOrganizador implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {


        HttpSession session = request.getSession();
        String mantAtiv = (String) request.getParameter("manterAtv");
        String mantMod = (String) request.getParameter("manterMod");

        boolean manterAtividade = false;
        boolean manterModulo = false;


        if (mantAtiv != null) {
            manterAtividade = true;
        }
        if (mantMod != null) {
            manterModulo = true;
        }

        Evento en = (Evento) session.getAttribute("evento");
        Usuario u = (Usuario) session.getAttribute("uEditar");
        OrganizadorService orgS = new OrganizadorService();
        Organizador org = orgS.getByUsuarioId(u.getId());

        if (org != null) {
            boolean organizaEvento = false;
            for (Organizacao organ : org.getOrganizacoes()) {

                if (organ.getEvento().getId().equals(en.getId())) {
                    organizaEvento = true;
                }

            }

            Organizacao orga = criarOrganizacao(org, en, manterAtividade, manterModulo);
            OrganizacaoService orgaS = new OrganizacaoService();

            if (orgaS.update(orga)) {
                if (organizaEvento == false) {
                    orgaS.adicionar(orga);
                    org.setOrganizacoes(orga);
                    en.addOrganizador(org);
                }
                session.setAttribute("sucesso", "Alterado com sucesso!");
                return "/admin/organ_listar_movimentacao.jsp";
            }


        } else {

            OrganizacaoService orgaS = new OrganizacaoService();
            org = criarOrganizador(u, new Date(), true);
            Organizacao orga = criarOrganizacao(org, en, manterAtividade, manterModulo);
            if (orgS.adicionar(org)) {
                if (orgaS.adicionar(orga)) {
                    org.setOrganizacoes(orga);
                    en.addOrganizador(org);
                    session.setAttribute("sucesso", "Alterado com sucesso!");
                    return "/admin/organ_listar_movimentacao.jsp";
                }
            }

        }
        session.setAttribute("erro", "Falha ao Alterado!");
        return "/admin/organ_listar_movimentacao.jsp";
    }

    private Organizacao criarOrganizacao(Organizador org, Evento en, boolean manterAtividade, boolean manterModulo) {

        Organizacao orga = new Organizacao();
        orga.setEvento(en);
        orga.setManterAtividade(manterAtividade);
        orga.setOrganizador(org);
        orga.setManterModuloFinanceiro(manterModulo);
        return orga;

    }

    private Organizador criarOrganizador(Usuario u, Date dataCriacao, boolean status) {
        Organizador org = new Organizador();
        org.setDataCriacao(dataCriacao);
        org.setStatus(status);
        org.setUsuario(u);

        return org;
    }
}
