package br.ufc.pet.daos;

import br.ufc.pet.config.PostgresMapConfig;
import br.ufc.pet.evento.Horario;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author Escritorio projetos
 */
public class HorarioDAO {

    public void insert(Horario horario) throws SQLException {
        Long id = getProxId();
        horario.setId(id);
        PostgresMapConfig.getSqlMapClient().insert("addHorario", horario);
    }

    public Horario getById(Long id) throws SQLException {
        return (Horario) PostgresMapConfig.getSqlMapClient().queryForObject("getHorarioById", id);
    }

    public ArrayList<Horario> getByAtividadeId(Long id) throws SQLException {
        return (ArrayList<Horario>) PostgresMapConfig.getSqlMapClient().queryForList("getHorariosByAtividadeId", id);
    }

    public ArrayList<Horario> getByEventoId(Long id) throws SQLException {
        return (ArrayList<Horario>) PostgresMapConfig.getSqlMapClient().queryForList("getHorariosByEventoId", id);
    }

    public ArrayList<Horario> getAllHorarios() throws SQLException {
        return (ArrayList<Horario>) PostgresMapConfig.getSqlMapClient().queryForList("getAllHorarios");
    }

    public void delete(Long id) throws SQLException {
        PostgresMapConfig.getSqlMapClient().delete("deleteHorario", id);
    }

    private Long getProxId() throws SQLException {
        Long l = (Long) PostgresMapConfig.getSqlMapClient().queryForObject("getMaxIdHorario");
        if (l == null) {
            l = 0L;
        }
        return l + 1;
    }

    public void update(Horario horario) throws SQLException {
        PostgresMapConfig.getSqlMapClient().update("updateHorario", horario);
    }

    
}
