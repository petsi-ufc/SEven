package br.ufc.pet.daos;

import br.ufc.pet.config.PostgresMapConfig;
import br.ufc.pet.evento.ModalidadeInscricao;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author Caio
 */
public class ModalidadeInscricaoDAO {
     public void insert(ModalidadeInscricao modalidadeInscricao) throws SQLException {
        Long id = getProxId();
        modalidadeInscricao.setId(id);
        PostgresMapConfig.getSqlMapClient().insert("addModalidadeInscricao", modalidadeInscricao);
    }

    public void delete(Long id) throws SQLException {
        PostgresMapConfig.getSqlMapClient().delete("deleteModalidadeInscricao", id);
    }

    public void update(ModalidadeInscricao modalidadeInscricao) throws SQLException {
        PostgresMapConfig.getSqlMapClient().update("updateModalidadeInscricao", modalidadeInscricao);
    }

    public ModalidadeInscricao getById(Long id) throws SQLException {
        return (ModalidadeInscricao) PostgresMapConfig.getSqlMapClient().queryForObject("getModalidadeInscricaoById", id);
    }
    
    public ModalidadeInscricao getByTipo(String tipo) throws SQLException {
        return (ModalidadeInscricao) PostgresMapConfig.getSqlMapClient().queryForObject("getModalidadeInscricaoByTipo", tipo);
    }

    private Long getProxId() throws SQLException {
        Long id= (Long) PostgresMapConfig.getSqlMapClient().queryForObject("getMaxIdModalidadeInscricao");

        if(id==null){
        id=0L;

        }

        return id+1L;
    }

    public ArrayList<ModalidadeInscricao> getAll() throws SQLException {
        return (ArrayList<ModalidadeInscricao>) PostgresMapConfig.getSqlMapClient().queryForList("getAllModalidadesInscricao");
    }

    public ArrayList<ModalidadeInscricao> getModalidadesByEventoId(Long id) throws SQLException {
        return (ArrayList<ModalidadeInscricao>) PostgresMapConfig.getSqlMapClient().queryForList("getModalidadesInscricaoByEventoId",id);
    }
}
