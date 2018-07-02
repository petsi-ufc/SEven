package br.ufc.pet.services;

import br.ufc.pet.daos.ResponsavelAtividadeDAO;
import br.ufc.pet.evento.ResponsavelAtividade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author fernando
 */
public class ResponsavelAtividadeService {

    private final ResponsavelAtividadeDAO responsavelAtividadeDAO;

    public ResponsavelAtividadeService() {
        responsavelAtividadeDAO = new ResponsavelAtividadeDAO();
    }

    public ResponsavelAtividade getByUsuarioId(Long id) {
        try {
            ResponsavelAtividade res = responsavelAtividadeDAO.getByUsuarioId(id);
            if (res != null) {
                return res;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipanteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<ResponsavelAtividade> getResponsavelAtividade(Long ativId) {
        try {
            ArrayList<ResponsavelAtividade> responsaveis = responsavelAtividadeDAO.geResponsaveisByAtividade(ativId);
            return responsaveis;
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
