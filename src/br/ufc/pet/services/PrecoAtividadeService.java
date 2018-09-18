package br.ufc.pet.services;

import br.ufc.pet.daos.PrecoAtividadeDAO;
import br.ufc.pet.evento.PrecoAtividade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Caio
 */
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
            ArrayList<PrecoAtividade> precos = PrecoAtividadeDAO.getAllPrecosByModalidadeId(id);
            return precos;
        } catch (SQLException ex) {
            Logger.getLogger(PrecoAtividadeService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
