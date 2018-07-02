package br.ufc.pet.daos;

import br.ufc.pet.config.PostgresMapConfig;
import br.ufc.pet.evento.PrecoAtividade;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author Caio
 */
public class PrecoAtividadeDAO {

    public void insert(PrecoAtividade precoAtividade) throws SQLException{
        precoAtividade.setId(proxId());
        PostgresMapConfig.getSqlMapClient().insert("addprecoAtividade", precoAtividade);
    }

    public void delete(PrecoAtividade precoAtividade) throws SQLException {
        PostgresMapConfig.getSqlMapClient().delete("deleteprecoAtividade", precoAtividade);
    }

    public void update(PrecoAtividade precoAtividade) throws SQLException {
        PostgresMapConfig.getSqlMapClient().update("updateprecoAtividade", precoAtividade);
    }

    public ArrayList<PrecoAtividade> getAllPrecosByModalidadeId(Long id) throws SQLException {
        return (ArrayList<PrecoAtividade>) PostgresMapConfig.getSqlMapClient().queryForList("getAllPrecoByModalidadeId", id);
        
    }

    private Long proxId() throws SQLException{
        Long id= (Long) PostgresMapConfig.getSqlMapClient().queryForObject("getMaxIdPrecoAtividade");
        if(id==null){
        id=0L;
        }
        return id+1L;
    }
}
