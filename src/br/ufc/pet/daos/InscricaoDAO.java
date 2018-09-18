package br.ufc.pet.daos;

import br.ufc.pet.config.PostgresMapConfig;
import br.ufc.pet.evento.Inscricao;
import br.ufc.pet.evento.Utility;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author fernando
 */
public class InscricaoDAO {

    public void insert(Inscricao inscricao) throws SQLException {
        Long id = getProxId();
        inscricao.setId(id);
        PostgresMapConfig.getSqlMapClient().insert("addInscricao", inscricao);
    }
    
    public void update(Inscricao inscricao) throws SQLException {
        PostgresMapConfig.getSqlMapClient().update("updateInscricao", inscricao);
    }

    public Inscricao getById(Long id) throws SQLException {
        return (Inscricao) PostgresMapConfig.getSqlMapClient().queryForObject("getInscricaoById", id);
    }
    
    public Inscricao getByCodigoValidacao(String codigo) throws SQLException {
        return (Inscricao) PostgresMapConfig.getSqlMapClient().queryForObject("getInscricaoByCodigoValidacao", codigo);
    }

    public Inscricao getParticipanteEvento(Utility utility) throws SQLException {
        return (Inscricao) PostgresMapConfig.getSqlMapClient().queryForObject("getInscricaoByParticipanteEvento", utility);
    }
    public ArrayList<Inscricao> getByParticipanteId(Long id) throws SQLException {
        return (ArrayList<Inscricao>) PostgresMapConfig.getSqlMapClient().queryForList("getInscricaoByParticipanteId", id);
    }

    private Long getProxId() throws SQLException {
        Long l = (Long) PostgresMapConfig.getSqlMapClient().queryForObject("getMaxIdInscricao");
        if(l == null){
            l = 0L;
        }
        return l+1;
    }

    public Long getInscritosByAtividadeId(Long atividadeId) throws SQLException{
        return (Long) PostgresMapConfig.getSqlMapClient().queryForObject("getInscritosByAtividadeId",atividadeId);
    }

    public void excluir(Long id) throws SQLException {
        PostgresMapConfig.getSqlMapClient().delete("deleteInscricao", id);
    }

    public void confirmarPagamento(Inscricao inscricao) throws SQLException {
        PostgresMapConfig.getSqlMapClient().delete("confirmarpagamento", inscricao);
    }

    public void confirmarTodosPagamentos(Long eventoId) throws SQLException {
        PostgresMapConfig.getSqlMapClient().delete("confirmarTodosPagamentos", eventoId);
    }

    public ArrayList<Inscricao> getAllInscricoesByAuxInscricao(Inscricao inscricao) throws SQLException {
        return (ArrayList<Inscricao>) PostgresMapConfig.getSqlMapClient().queryForList("getAllInscricoesByAuxInscricao", inscricao);

    }

    public ArrayList<Inscricao> getInscricoesByAtividadeId(Long id) throws SQLException {
        return (ArrayList<Inscricao>) PostgresMapConfig.getSqlMapClient().queryForList("getAllInscricoesByAtividadeId", id);
    }

    public ArrayList<Inscricao> getInscricoesByEventoId(Long id) throws SQLException {
         return (ArrayList<Inscricao>) PostgresMapConfig.getSqlMapClient().queryForList("getAllInscricoesByEventoId", id);
    }
    
    
}
