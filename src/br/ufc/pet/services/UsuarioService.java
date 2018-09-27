package br.ufc.pet.services;

import br.ufc.pet.daos.UsuarioDAO;
import br.ufc.pet.entity.Perfil;
import br.ufc.pet.entity.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {
        usuarioDAO = new UsuarioDAO();
    }

    public Perfil validaUsuario(Usuario usuarioEntrada, String conta) {

        Usuario user = getByEmail(usuarioEntrada.getEmail());
        
        if (user != null) {
            Perfil perfil = null;
            if (conta.trim().equals("participante")) {
                ParticipanteService service = new ParticipanteService();
                perfil = service.getByUsuarioId(user.getId());
            } else if (conta.trim().equals("organizador")) {
                OrganizadorService service = new OrganizadorService();
                perfil = service.getByUsuarioId(user.getId());
            } else if (conta.trim().equals("administrador")) {
                AdministradorService service = new AdministradorService();
                perfil = service.getByUsuarioId(user.getId());
            }
            
            if (perfil != null && perfil.getUsuario().validaSenha(usuarioEntrada.getSenha())) {
                return perfil;
            }else if (perfil == null || perfil.isStatus() == false) {
                return null;
            }
        }

        return null;
    }

    public Usuario getByEmail(String email) {
        try {
            return usuarioDAO.getByEmail(email);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Usuario getById(Long id) {
        try {
            return usuarioDAO.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Usuario> getByNome(String nome) {
        try {
            return usuarioDAO.getByNome(nome);
        } catch (SQLException ex) {
            return null;
        }
    }

    public ArrayList<Usuario> getAllUsers() {
        try {
            return usuarioDAO.getAll();
        } catch (SQLException ex) {
            return null;
        }
    }

    public ArrayList<Usuario> getResponsavelAtividade(Long ativId) {
        try {
            return usuarioDAO.geResponsavelAtividade(ativId);
        } catch (SQLException ex) {
            return null;
        }
    }

    public boolean inserir(Usuario u) {
        try {
            usuarioDAO.inserir(u);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean update(Usuario u) {
        try {
            usuarioDAO.editar(u);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean updateSemSenha(Usuario u) {
        try {
            usuarioDAO.editarSemSenha(u);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateSenhaUser(Usuario u) {
        try {
            usuarioDAO.editarSenha(u);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean excluir(Long id) {
        try {
            usuarioDAO.excluir(id);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
