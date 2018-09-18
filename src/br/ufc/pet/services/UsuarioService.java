package br.ufc.pet.services;

import br.ufc.pet.daos.UsuarioDAO;
import br.ufc.pet.entity.Perfil;
import br.ufc.pet.entity.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author fernando
 */
public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {
        usuarioDAO = new UsuarioDAO();
    }

    public Perfil validaUsuario(Usuario usuarioEntrada, String conta) {

        Usuario user = getByEmail(usuarioEntrada.getEmail());
        //System.out.println("id do usuario:  "+user.getId());
        
        if (user != null) {
            Perfil perfil = null;
            if (conta.trim().equals("participante")) {
                ParticipanteService service = new ParticipanteService();
                perfil = service.getByUsuarioId(user.getId());
                if (perfil == null || perfil.getStatus() == false) {
                    return null;
                }
                //System.out.println("pegou o id do perfil : "+perfil.getId());
            } else if (conta.trim().equals("organizador")) {
                OrganizadorService service = new OrganizadorService();
                perfil = service.getByUsuarioId(user.getId());
                if (perfil == null || perfil.getStatus() == false) {
                    return null;
                }
                //System.out.println("pegou o id do perfil : "+perfil.getId());
            } else if (conta.trim().equals("administrador")) {
                AdministradorService service = new AdministradorService();
                perfil = service.getByUsuarioId(user.getId());
                if (perfil == null || perfil.getStatus() == false) {
                    return null;
                }
                //System.out.println(perfil==null);
            }
            if (perfil != null && perfil.getUsuario().validaSenha(usuarioEntrada.getSenha())) {
                //System.out.println("retornou o perfil");
                return perfil;
            }
        }

        return null;
    }

    public Usuario getByEmail(String email) {
        try {
            Usuario user = usuarioDAO.getByEmail(email);
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Usuario getById(Long id) {
        try {
            Usuario user = usuarioDAO.getById(id);

            //System.out.println("" + user.getNome());

            return user;
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Usuario> getByNome(String nome) {
        try {
            ArrayList<Usuario> user = usuarioDAO.getByNome(nome);
            return user;
        } catch (SQLException ex) {
            return null;
        }
    }



    public ArrayList<Usuario> getAllUsers() {
        try {
            ArrayList<Usuario> user = usuarioDAO.getAll();
            return user;
        } catch (SQLException ex) {
            return null;
        }
    }


    public ArrayList<Usuario> getResponsavelAtividade(Long ativId) {
        try {
            ArrayList<Usuario> user = usuarioDAO.geResponsavelAtividade(ativId);
            return user;
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
