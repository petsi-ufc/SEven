package br.ufc.pet.comandos.organizador;

import br.ufc.pet.evento.Evento;
import br.ufc.pet.evento.MovimentacaoFinanceira;
import br.ufc.pet.evento.Organizacao;
import br.ufc.pet.evento.Organizador;
import br.ufc.pet.interfaces.Comando;
import br.ufc.pet.services.MovimentacaoFinanceiraService;
import br.ufc.pet.util.UtilSeven;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author franklin barroso
 */

public class CmdAddMovimentacaoFinanceira implements Comando {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Organizacao organizacao = new Organizacao();
        

        Organizador org = (Organizador) session.getAttribute("user");
        Evento ev = (Evento) session.getAttribute("evento");
        organizacao.setEvento(ev);
        organizacao.setOrganizador(org);
        
        
        String descricao = (String) request.getParameter("descrição");
        String valor = (String) request.getParameter("valor");
        String date = (String) request.getParameter("data");
        //verificar a data, se está vazia
        Date data = UtilSeven.treatToDate(date);
        String tipo = request.getParameter("tipo_movimentacaofinanceira");

        MovimentacaoFinanceira MF = (MovimentacaoFinanceira) session.getAttribute("atualizarmovimentacaofinanceira");
        session.removeAttribute("atualizarmovimentacaofinanceira");

        if (tipo == null || date == null || date.trim().isEmpty() == true || UtilSeven.validaData(date) == false || descricao == null || valor == null || descricao.trim().isEmpty() == true || valor.trim().isEmpty() == true) {
            session.setAttribute("addmovimentacaofinanceira", "vazio");
            return "/org/organ_add_movimentacao.jsp";
        }
        if (MF == null) {

            MovimentacaoFinanceira newmf = CriarMovimentacaoFinanceira(descricao, Double.parseDouble(valor), data, tipo, ev, org);
            MovimentacaoFinanceiraService newmfs = new MovimentacaoFinanceiraService();
            if (newmfs.adicionar(newmf) == true) {
                session.setAttribute("estado", "adicionado");
                return "/ServletCentral?comando=CmdListarMovimentacaoFinanceira";
            } else {
                session.setAttribute("addmovimentacaofinanceira", "erro");
            }
            return "/org/organ_add_movimentacao.jsp";
        }

        MovimentacaoFinanceira alterdadomovfinanceiraU = CriarMovimentacaoFinanceira(descricao, Double.parseDouble(valor), data, tipo, ev, org);
        alterdadomovfinanceiraU.setId(MF.getId());
        MovimentacaoFinanceiraService alteradomovimentacaoS = new MovimentacaoFinanceiraService();
        alteradomovimentacaoS.atualizar(alterdadomovfinanceiraU);
        session.setAttribute("estado", "atualizado");




        return "/ServletCentral?comando=CmdListarMovimentacaoFinanceira";


    }

    private MovimentacaoFinanceira CriarMovimentacaoFinanceira(String descricao, Double valor, Date data, String tipo, Evento evento, Organizador organizador) {
        MovimentacaoFinanceira movfinan = new MovimentacaoFinanceira();

        movfinan.setDescricao(descricao);
        movfinan.setValor(valor);
        movfinan.setData(data);
        movfinan.setTipo(tipo);
        movfinan.setEvento(evento);
        movfinan.setOrganizador(organizador);

        return movfinan;
    }
}
