package br.ufc.pet.daos;

import br.ufc.pet.config.PostgresMapConfig;
import br.ufc.pet.entity.Administrador;

import java.sql.SQLException;

public class AdministradorDAO {

    public Administrador getByUsuarioId(Long id) throws SQLException {
        return (Administrador) PostgresMapConfig.getSqlMapClient().queryForObject("getAdministradorByUsuarioId", id);
    }
}
