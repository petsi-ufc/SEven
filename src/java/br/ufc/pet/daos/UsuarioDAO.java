package br.ufc.pet.daos;

import br.ufc.pet.config.PostgresMapConfig;
import br.ufc.pet.evento.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author Escritorio projetos
 */
public class UsuarioDAO {

    public void inserir(Usuario usuario) throws SQLException {
        usuario.setId(proxId());
        PostgresMapConfig.getSqlMapClient().insert("addUsuario", usuario);
    }

    public void excluir(Long id) throws SQLException {
        PostgresMapConfig.getSqlMapClient().delete("deleteUsuario", id);
    }

    public void editar(Usuario user) throws SQLException {
        PostgresMapConfig.getSqlMapClient().update("updateUsuario", user);
    }
    
    public void editarSemSenha(Usuario user) throws SQLException {
        PostgresMapConfig.getSqlMapClient().update("updateUsuarioSemSenha", user);
    }

    public void editarSenha(Usuario user) throws SQLException {
        PostgresMapConfig.getSqlMapClient().update("updateSenhaUsuario", user);
    }

    public Usuario getById(Long id) throws SQLException {
        return (Usuario) PostgresMapConfig.getSqlMapClient().queryForObject("getUsuarioById", id);
    }

    public Usuario getByEmail(String email) throws SQLException {
        return (Usuario) PostgresMapConfig.getSqlMapClient().queryForObject("getUsuarioByEmail", email);
    }

    public ArrayList<Usuario> getByNome(String nome) throws SQLException {
        return (ArrayList<Usuario>) PostgresMapConfig.getSqlMapClient().queryForList("getUsuarioByNome", nome);
    }

    public ArrayList<Usuario> getAll() throws SQLException {
        return (ArrayList<Usuario>) PostgresMapConfig.getSqlMapClient().queryForList("getAllUsuarios");
    }

    public ArrayList<Usuario> geResponsavelAtividade(Long id) throws SQLException {
        return (ArrayList<Usuario>) PostgresMapConfig.getSqlMapClient().queryForList("getUsuariosResponsaveisByAtividadeId", id);
    }

    private Long proxId() throws SQLException {
        Long id = (Long) PostgresMapConfig.getSqlMapClient().queryForObject("getMaxIdUsuario");
        if (id == null) {
            id = 0L;
        }
        return id + 1L;
    }
    
}
