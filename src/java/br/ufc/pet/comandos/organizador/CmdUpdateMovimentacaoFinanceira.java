package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.MovimentacaoFinanceira;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.MovimentacaoFinanceiraService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author franklin barroso
 */
public class CmdUpdateMovimentacaoFinanceira implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String codigo = request.getParameter("id_mf");
        Long cod = Long.parseLong(codigo);
        MovimentacaoFinanceiraService s = new MovimentacaoFinanceiraService();
        MovimentacaoFinanceira movfinanAtualizar = s.getById(cod);
        session.setAttribute("atualizarmovimentacaofinanceira", movfinanAtualizar);
        return "/org/organ_add_movimentacao.jsp";
    }
}
