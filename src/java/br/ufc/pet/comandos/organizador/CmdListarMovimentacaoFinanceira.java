package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.evento.MovimentacaoFinanceira;
import br.ufc.pet.evento.Organizacao;
import br.ufc.pet.evento.Organizador;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.MovimentacaoFinanceiraService;
import br.ufc.pet.services.OrganizacaoService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author USER
 */
public class CmdListarMovimentacaoFinanceira implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);

        Organizacao organizacao = new Organizacao();
        OrganizacaoService orgS = new OrganizacaoService();

        Organizador org = (Organizador) session.getAttribute("user");
        Evento ev = (Evento) session.getAttribute("evento");
        organizacao.setEvento(ev);
        organizacao.setOrganizador(org);
        organizacao = orgS.getOrganizacaoByOrganizadorIdAndEventoId(organizacao);

        MovimentacaoFinanceiraService movimentacaofinanceiraservice = new MovimentacaoFinanceiraService();
        ArrayList<MovimentacaoFinanceira> movfs = movimentacaofinanceiraservice.getMovimentacaoFinanceirasByEventoId(ev.getId());
        ev.setMovimentacoesFinanceiras(movfs);

        session.setAttribute("permissao", organizacao.getManterModuloFinanceiro());
        session.removeAttribute("atualizarmovimentacaofinanceira");

        return "/org/organ_listar_movimentacao.jsp";
    }
}
