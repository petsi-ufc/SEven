package br.ufc.pet.daos;

import br.ufc.pet.config.PostgresMapConfig;
import br.ufc.pet.evento.Organizacao;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author fernando
 */
public class OrganizacaoDAO {

    public void insert(Organizacao organizacao) throws SQLException{



        organizacao.setId(proxId());
        PostgresMapConfig.getSqlMapClient().insert("addOrganizacao",organizacao);

    }

    public void update(Organizacao organizacao) throws SQLException{

    PostgresMapConfig.getSqlMapClient().update("updateOrganizacao", organizacao);
    }
    
    public void delete(Organizacao organizacao) throws SQLException{

        PostgresMapConfig.getSqlMapClient().delete("deleteOrganizacao",organizacao);

    }

    public ArrayList<Organizacao> getByOrganizacoesByOrganizadorId(Long id) throws SQLException{
        return (ArrayList<Organizacao>) PostgresMapConfig.getSqlMapClient().queryForList("getOrganizacaoByOrganizador",id);
    }

     public Organizacao getOrganizacaoByOrganizadorIdAndEventoId(Organizacao org) throws SQLException{

     return (Organizacao) PostgresMapConfig.getSqlMapClient().queryForObject("getOrganizacaoByOrganizadorEvento", org);

     }

     private Long proxId() throws SQLException{

    Long id=(Long)PostgresMapConfig.getSqlMapClient().queryForObject("getMaxIdOrganizacao");

     if(id==null){
     id=0L;
     }
     return id+1L;
     }

}
