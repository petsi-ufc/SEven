package br.ufc.pet.daos;

import br.ufc.pet.config.PostgresMapConfig;
import br.ufc.pet.entity.Evento;

import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author Escritorio projetos
 */
public class EventoDAO {

    public void insert(Evento evento) throws SQLException{
        evento.setId(proxId());
        PostgresMapConfig.getSqlMapClient().insert("addEvento", evento);
    }

    public void update(Evento evento) throws SQLException{
        PostgresMapConfig.getSqlMapClient().update("updateEvento", evento);
    }
    
    public void encerrar(Long id) throws SQLException{
        PostgresMapConfig.getSqlMapClient().update("encerrarEvento", id);
    }
    
    public void ativar(Long id) throws SQLException{
        PostgresMapConfig.getSqlMapClient().update("ativarEvento", id);
    }
    
    public void delete(Long id) throws SQLException{
        PostgresMapConfig.getSqlMapClient().delete("deleteEvento", id);
    }

    public Evento getById(Long id) throws SQLException{
        return (Evento) PostgresMapConfig.getSqlMapClient().queryForObject("getEventoById", id);
    }

    public Evento getBySigla(String sigla) throws SQLException{
        return (Evento)PostgresMapConfig.getSqlMapClient().queryForObject("getEventoBySigla", sigla);
    }
    
    public Evento getByNome(String nome) throws SQLException{
        return (Evento)PostgresMapConfig.getSqlMapClient().queryForObject("getEventoByNome", nome);
    }

    public ArrayList<Evento> getAllEventosAbertos() throws SQLException{
        return (ArrayList<Evento>) PostgresMapConfig.getSqlMapClient().queryForList("getAllEventosNaoEncerrados");
    }
    
    public ArrayList<Evento> getAllEventos() throws SQLException{
        return (ArrayList<Evento>) PostgresMapConfig.getSqlMapClient().queryForList("getAllEventos");
    }

    public Evento getEventoByOrganizacaoId(Long id) throws SQLException{
        return (Evento) PostgresMapConfig.getSqlMapClient().queryForObject("getEventoByOrganizacaoId", id);
    }

    private Long proxId() throws SQLException{
        Long id= (Long) PostgresMapConfig.getSqlMapClient().queryForObject("getMaxIdEvento");
        if(id == null){
        	id = 0L;
        }
        return id + 1L;
    }

}
