package br.ufc.pet.services;

import br.ufc.pet.daos.PrecoAtividadeDAO;
import br.ufc.pet.entity.PrecoAtividade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrecoAtividadeService {

    private final PrecoAtividadeDAO PrecoAtividadeDAO;

    public PrecoAtividadeService() {
        this.PrecoAtividadeDAO = new PrecoAtividadeDAO();
    }

    public boolean adicionar(PrecoAtividade precoAtividade) {
        try {
            PrecoAtividadeDAO.insert(precoAtividade);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(PrecoAtividade precoAtividade){
        try {
            PrecoAtividadeDAO.update(precoAtividade);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public boolean excluir(PrecoAtividade precoAtividade){
        try {
            PrecoAtividadeDAO.delete(precoAtividade);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public ArrayList<PrecoAtividade> getPrecosByModalidadeId(Long id) {
        try {
            return PrecoAtividadeDAO.getAllPrecosByModalidadeId(id);
        } catch (SQLException ex) {
            Logger.getLogger(PrecoAtividadeService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
