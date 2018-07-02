package br.ufc.pet.daos;

import br.ufc.pet.config.PostgresMapConfig;
import br.ufc.pet.evento.MovimentacaoFinanceira;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author fernando
 */
public class MovimentacaoFinanceiraDAO {

    public void insert(MovimentacaoFinanceira movimentacaoFinanceira) throws SQLException {
        movimentacaoFinanceira.setId(proxId());
        PostgresMapConfig.getSqlMapClient().insert("addMovimentacaoFinanceira", movimentacaoFinanceira);
    }

    public void update(MovimentacaoFinanceira movimentacaoFinanceira) throws SQLException {
        PostgresMapConfig.getSqlMapClient().update("updateMovimentacaoFinanceira", movimentacaoFinanceira);
    }

    public void delete(Long id) throws SQLException {
        PostgresMapConfig.getSqlMapClient().delete("deleteMovimentacaoFinanceira", id);
    }

    public MovimentacaoFinanceira getById(Long id) throws SQLException {
        return (MovimentacaoFinanceira) PostgresMapConfig.getSqlMapClient().queryForObject("getMovimentacaoFinanceiraById", id);
    }

    public ArrayList<MovimentacaoFinanceira> getAllByEventoId(Long id) throws SQLException {
        return (ArrayList<MovimentacaoFinanceira>) PostgresMapConfig.getSqlMapClient().queryForList("getAllMovimentacaoFinanceiraByEventoId", id);
    }

    private Long proxId() throws SQLException {
        Long id = (Long) PostgresMapConfig.getSqlMapClient().queryForObject("getMaxIdMovimentacaoFinanceira");
        if (id == null) {
            id = 0L;
        }
        return id + 1;
    }
}
