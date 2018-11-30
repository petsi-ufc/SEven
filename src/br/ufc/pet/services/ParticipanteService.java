package br.ufc.pet.services;

import br.ufc.pet.daos.ParticipanteDAO;
import br.ufc.pet.entity.Participante;
import br.ufc.pet.util.UtilSeven;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParticipanteService {

    private final ParticipanteDAO participanteDAO;
    private final UsuarioService us;

    public ParticipanteService() {
        participanteDAO = new ParticipanteDAO();
        us = new UsuarioService();
    }

    public boolean insertParticipanteUsuario(Participante participante) {
        try {
            if (us.inserir(participante.getUsuario())) {
                participanteDAO.insertParticipante(participante);
                return true;
            }
            return false;
        } catch (SQLException ex) {
            us.excluir(participante.getUsuario().getId());
            Logger.getLogger(ParticipanteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteParticipanteUsuario(Participante participante) {
        try {
            participanteDAO.deleteParticipante(participante);
            return true;

        } catch (SQLException ex) {

            Logger.getLogger(ParticipanteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Participante getByUsuarioId(Long id) {
        try {
            Participante part = participanteDAO.getByUsuarioId(id);
            if (part != null) {
                return part;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipanteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Participante getById(Long id) {
        try {
            Participante part = participanteDAO.getById(id);
            if (part != null) {
                return part;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipanteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Participante> getParticipanteByAtividadeId(Long id) {
        try {
            ArrayList<Participante> parts = participanteDAO.getParticipantesByAtividadeId(id);
            Collections.sort(parts);
            return parts;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public ArrayList<Participante> getParticipanteByAtividadeIdQuites(Long id) {
        try {
            ArrayList<Participante> parts = participanteDAO.getParticipanteByAtividadeIdQuites(id);
            Collections.sort(parts);
            return parts;
        } catch (SQLException ex) {
            return null;
        }
    }

    public ArrayList<Participante> getParticipantesQuitesByEventoId(Long id) {
        try {
            ArrayList<Participante> parts = participanteDAO.getParticipantesQuistesByEventoID(id);
            return parts;
        } catch (SQLException ex) {
            return null;
        }
    }

    public ArrayList<Participante> getParticipantesByEventoId(Long id) {
        try {
            ArrayList<Participante> parts = participanteDAO.getParticipantesByEventoID(id);
            return parts;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public String getSenhaPart(Long id){
         try {
            Participante part = participanteDAO.getByUsuarioId(id);
            return part.getUsuario().getSenha();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return null;
    }
    
    public void alterarSenhaPart(Long id, String senha){
         try {
            Participante part = participanteDAO.getByUsuarioId(id);
            if (part != null) {
                part.getUsuario().setSenha(UtilSeven.criptografar(senha));
                us.updateSenhaUser(part.getUsuario());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }
}
