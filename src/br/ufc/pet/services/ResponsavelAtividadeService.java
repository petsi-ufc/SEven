package br.ufc.pet.services;

import br.ufc.pet.daos.ResponsavelAtividadeDAO;
import br.ufc.pet.entity.ResponsavelAtividade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResponsavelAtividadeService {

    private final ResponsavelAtividadeDAO responsavelAtividadeDAO;

    public ResponsavelAtividadeService() {
        responsavelAtividadeDAO = new ResponsavelAtividadeDAO();
    }

    public ResponsavelAtividade getByUsuarioId(Long id) {
        try {
            return responsavelAtividadeDAO.getByUsuarioId(id);
        } catch (SQLException ex) {
            Logger.getLogger(ParticipanteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<ResponsavelAtividade> getResponsavelAtividade(Long ativId) {
        try {
            return responsavelAtividadeDAO.geResponsaveisByAtividade(ativId);
        } catch (SQLException ex) {
            return null;
        }
    }

    public boolean insertPerfilResponsavelAtividade(ResponsavelAtividade responsavel) {
        try {
            responsavelAtividadeDAO.insert(responsavel);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ResponsavelAtividadeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
}
