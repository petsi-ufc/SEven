package br.ufc.pet.services;

import java.sql.SQLException;

import br.ufc.pet.daos.AdministradorDAO;
import br.ufc.pet.evento.Administrador;
import br.ufc.pet.util.UtilSeven;


/*
 * @author fernando
 */
public class AdministradorService {

    private final AdministradorDAO administradorDAO;

    public AdministradorService() {
        administradorDAO = new AdministradorDAO();
    }

    public Administrador getByUsuarioId(Long id) {
        try {
            Administrador admin = administradorDAO.getByUsuarioId(id);
            if (admin != null) {
                UsuarioService us = new UsuarioService();
                admin.setUsuario(us.getById(admin.getUsuario().getId()));
                EventoService es = new EventoService();
                admin.setEventos(es.buscarAllEventos());
                return admin;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void alterarSenhaAdmin(Long id, String senha){
         try {
            Administrador admin = administradorDAO.getByUsuarioId(id); // Esse DAO possui apenas os admins
            if (admin != null) {
                UsuarioService us = new UsuarioService();
                admin.setUsuario(us.getById(admin.getUsuario().getId())); //Aqui ele vai pegar o admin do DAO e pegar seu id na tabela com todos os usuarios (vai saber pq fizeram assim)
                admin.getUsuario().setSenha(UtilSeven.criptografar(senha)); // Trabalha em cima da senha
                us.updateSenhaUser(admin.getUsuario()); // Joga novamente para o banco
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }
    
    public String getSenhaAdmin(Long id){
         try {
            Administrador admin = administradorDAO.getByUsuarioId(id);
            UsuarioService us = new UsuarioService();
            admin.setUsuario(us.getById(admin.getUsuario().getId())); 
            return admin.getUsuario().getSenha();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return null;
    }
}
