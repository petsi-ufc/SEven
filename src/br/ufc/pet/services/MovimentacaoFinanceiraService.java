package br.ufc.pet.services;

import br.ufc.pet.daos.MovimentacaoFinanceiraDAO;
import br.ufc.pet.evento.MovimentacaoFinanceira;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author fernando
 */
public class MovimentacaoFinanceiraService {

    private final MovimentacaoFinanceiraDAO movimentacaoFinanceiraDAO;

    public MovimentacaoFinanceiraService() {
        movimentacaoFinanceiraDAO = new MovimentacaoFinanceiraDAO();
    }

    public boolean adicionar(MovimentacaoFinanceira movimentacaoFinanceira) {
        try {
            movimentacaoFinanceiraDAO.insert(movimentacaoFinanceira);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean excluir(MovimentacaoFinanceira movimentacaofinanceira) {
        try {
            movimentacaoFinanceiraDAO.delete(movimentacaofinanceira.getId());
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public boolean atualizar(MovimentacaoFinanceira movimentacaofinanceira) {
        try {
            movimentacaoFinanceiraDAO.update(movimentacaofinanceira);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public MovimentacaoFinanceira getById(Long id) {
        try {
            return movimentacaoFinanceiraDAO.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<MovimentacaoFinanceira> getMovimentacaoFinanceirasByEventoId(Long id) {
        try {
            return movimentacaoFinanceiraDAO.getAllByEventoId(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
