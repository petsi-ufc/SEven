package br.ufc.pet.daos;

import br.ufc.pet.config.PostgresMapConfig;
import br.ufc.pet.evento.TipoAtividade;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author Escritorio projetos
 */
public class TipoAtividadeDAO {

    public ArrayList<TipoAtividade> getAll() throws SQLException {
        return (ArrayList<TipoAtividade>) PostgresMapConfig.getSqlMapClient().queryForList("getAllTipoAtividade");
    }

    public TipoAtividade getById(long id) throws SQLException {
        return (TipoAtividade) PostgresMapConfig.getSqlMapClient().queryForObject("getTipoAtividadeById", id);

    }

    public ArrayList<TipoAtividade> getByEventoId(long id) throws SQLException {
        return (ArrayList<TipoAtividade>) PostgresMapConfig.getSqlMapClient().queryForList("getTipoAtividadeByEventoId", id);
    }

    public void insert(TipoAtividade tipo) throws SQLException {
        Long id = getProxId();
        tipo.setId(id);
        PostgresMapConfig.getSqlMapClient().insert("addTipoAtividade", tipo);
    }

    public void delete(Long id) throws SQLException {
        PostgresMapConfig.getSqlMapClient().delete("deleteTipoAtividade", id);
    }

    public void update(TipoAtividade taEdit) throws SQLException {
        PostgresMapConfig.getSqlMapClient().update("updateTipoAtividade", taEdit);
    }

    private Long getProxId() throws SQLException {
        Long l = (Long) PostgresMapConfig.getSqlMapClient().queryForObject("getMaxIdTipoAtividade");
        if (l == null) {
            l = 0L;
        }
        return l + 1;
    }
}
