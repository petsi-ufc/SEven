package br.ufc.pet.daos;

import br.ufc.pet.config.PostgresMapConfig;
import br.ufc.pet.evento.Participante;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author Escritorio projetos
 */
public class ParticipanteDAO {

    public void insertParticipante(Participante participante) throws SQLException{
        participante.setId(proxId());
        PostgresMapConfig.getSqlMapClient().insert("addParticipante", participante);
    }
        public void deleteParticipante(Participante participante) throws SQLException{
        PostgresMapConfig.getSqlMapClient().delete("deleteParticipante", participante);
    }

    public Participante getByUsuarioId(Long id) throws SQLException {
        Participante p = (Participante) PostgresMapConfig.getSqlMapClient().queryForObject("getParticipanteByUsuarioId", id);
        return p;
    }
    public Participante getById(Long id) throws SQLException {
        Participante p = (Participante) PostgresMapConfig.getSqlMapClient().queryForObject("getParticipanteById", id);
        return p;
    }
    private Long proxId() throws SQLException{
        Long l = (Long) PostgresMapConfig.getSqlMapClient().queryForObject("getMaxIdPerfil");
        if(l == null)
            l = 0L;
        return l + 1;
    }

    public ArrayList<Participante> getParticipantesByAtividadeId(Long id) throws SQLException{
        return (ArrayList<Participante>)PostgresMapConfig.getSqlMapClient().queryForList("getParticipanteByAtividadeId",id);
    }
    
    public ArrayList<Participante> getParticipanteByAtividadeIdQuites(Long id) throws SQLException{
        return (ArrayList<Participante>)PostgresMapConfig.getSqlMapClient().queryForList("getParticipanteByAtividadeIdQuites",id);
    }
    
    public ArrayList<Participante> getParticipantesQuistesByEventoID(Long id) throws SQLException{
        return (ArrayList<Participante>)PostgresMapConfig.getSqlMapClient().queryForList("getParticipantesQuitesByEventoId",id);
    }

    public ArrayList<Participante> getParticipantesByEventoID(Long id) throws SQLException {
        return (ArrayList<Participante>) PostgresMapConfig.getSqlMapClient().queryForList("getParticipantesByEventoId", id);
    }
    
    
}