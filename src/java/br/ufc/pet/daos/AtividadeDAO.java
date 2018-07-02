package br.ufc.pet.daos;

import br.ufc.pet.config.PostgresMapConfig;
import br.ufc.pet.evento.Atividade;
import br.ufc.pet.evento.InscricaoAtividade;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author fernando
 */
public class AtividadeDAO {

    public void insert(Atividade atividade) throws SQLException {
        Long id = getProxId();
        atividade.setId(id);
        PostgresMapConfig.getSqlMapClient().insert("addAtividade", atividade);
    }

    public void update(Atividade atividade) throws SQLException {
        PostgresMapConfig.getSqlMapClient().update("updateAtividade", atividade);
    }

    public void delete(Long id) throws SQLException {
        PostgresMapConfig.getSqlMapClient().delete("deleteAtividade", id);
    }

    public Atividade getById(Long id) throws SQLException {
        return (Atividade) PostgresMapConfig.getSqlMapClient().queryForObject("getAtividadeById", id);
    }

    public ArrayList<Atividade> getByEventoId(Long id) throws SQLException {
        return (ArrayList<Atividade>) PostgresMapConfig.getSqlMapClient().queryForList("getAtividadesByEventoId", id);
    }

    public ArrayList<Atividade> getByInscricaoId(Long id) throws SQLException {
        return (ArrayList<Atividade>) PostgresMapConfig.getSqlMapClient().queryForList("getAtividadesByInscricaoId", id);
    }

    public ArrayList<Atividade> getByOrganizadorId(Long id) throws SQLException {
        return (ArrayList<Atividade>) PostgresMapConfig.getSqlMapClient().queryForList("getAtividadesByOrganizadorId", id);
    }

    private Long getProxId() throws SQLException {
        Long id = (Long) PostgresMapConfig.getSqlMapClient().queryForObject("getMaxIdAtividade");
        if (id == null) {
            id = 0L;

        }
        return id + 1L;
    }
    
    
    
     public void confirmaLiberacaoCertificadoAtividade(InscricaoAtividade utility) throws SQLException{
        
         
         PostgresMapConfig.getSqlMapClient().update("confirmaLiberacaoCertificadoAtividade", utility);
         
     }
     
     public ArrayList<InscricaoAtividade> getIncricaoAtividadeByInscricao(Long idInscricao) throws SQLException{
         return (ArrayList<InscricaoAtividade>) PostgresMapConfig.getSqlMapClient().queryForList("getInscricaoAtividadeByInscricao", idInscricao);
     }
             
}
