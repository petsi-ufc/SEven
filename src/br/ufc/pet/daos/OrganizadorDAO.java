package br.ufc.pet.daos;

import br.ufc.pet.config.PostgresMapConfig;
import br.ufc.pet.evento.Organizador;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author Escritorio projetos
 */
public class OrganizadorDAO {

    public void insert(Organizador org) throws SQLException{
        org.setId(proxId());
        PostgresMapConfig.getSqlMapClient().insert("addOrganizador",org);

    }

    public Organizador getByUsuarioId(Long id) throws SQLException {
        
            return (Organizador) PostgresMapConfig.getSqlMapClient().queryForObject("getOrganizadorByUsuarioId", id);
        
    }

    public ArrayList<Organizador> getByEventoId(Long id){
        return null;
    }

    private Long proxId() throws SQLException{
        
         
        Long id= (Long) PostgresMapConfig.getSqlMapClient().queryForObject("getMaxIdOrganizador");
        if(id==null){

        id=0L;
        }
        return id+1L;
    }


    public ArrayList<Organizador> getOrganizadoresByEvento(Long idEvento) throws SQLException{


    return (ArrayList<Organizador>)PostgresMapConfig.getSqlMapClient().queryForList("getAllOrganizadorByEvento", idEvento);
    }

}
