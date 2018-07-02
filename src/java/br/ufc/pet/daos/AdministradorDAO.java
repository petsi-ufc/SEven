package br.ufc.pet.daos;

import br.ufc.pet.config.PostgresMapConfig;
import br.ufc.pet.evento.Administrador;
import java.sql.SQLException;

/*
 * @author Escritorio projetos
 */
public class AdministradorDAO {

    public Administrador getByUsuarioId(Long id) throws SQLException {
        Administrador admin = (Administrador) PostgresMapConfig.getSqlMapClient().queryForObject("getAdministradorByUsuarioId", id);
        return admin;
    }
}
