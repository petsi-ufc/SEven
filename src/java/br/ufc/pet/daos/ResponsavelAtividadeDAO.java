package br.ufc.pet.daos;

import br.ufc.pet.config.PostgresMapConfig;
import br.ufc.pet.evento.ResponsavelAtividade;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author fernando
 */
public class ResponsavelAtividadeDAO {
      public ResponsavelAtividade getByUsuarioId(Long id) throws SQLException {
        ResponsavelAtividade responsavel = (ResponsavelAtividade) PostgresMapConfig.getSqlMapClient().queryForObject("getResponsavelAtividadeByUsuarioId", id);
        return responsavel;
    }
      public ArrayList<ResponsavelAtividade> geResponsaveisByAtividade(Long id) throws SQLException {
        return (ArrayList<ResponsavelAtividade>) PostgresMapConfig.getSqlMapClient().queryForList("getResponsaveisAtividadeByAtividadeId", id);
    }

    public void insert(ResponsavelAtividade responsavelAtividade) throws SQLException{
       responsavelAtividade.setId(proxId());
       PostgresMapConfig.getSqlMapClient().insert("addResponsavelAtividade",responsavelAtividade);
        
    }
    
    public void update(ResponsavelAtividade responsavelAtividade){
        
    }
    
    public void delete(ResponsavelAtividade responsavelAtividade){
        
    }
    
    public ArrayList<ResponsavelAtividade> getByEventoId(Long id){
        return null;
    }
    private Long proxId() throws SQLException{
        Long l = (Long) PostgresMapConfig.getSqlMapClient().queryForObject("getMaxIdPerfil");
        if(l == null)
            l = 0L;
        return l + 1;
    }

}
