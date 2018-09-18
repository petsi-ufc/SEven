package br.ufc.pet.services;

import br.ufc.pet.daos.TipoAtividadeDAO;
import br.ufc.pet.evento.TipoAtividade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Escritorio projetos
 */
public class TipoAtividadeService {

    private final TipoAtividadeDAO tipoAtividadeDAO;

    public TipoAtividadeService() {
        this.tipoAtividadeDAO = new TipoAtividadeDAO();
    }

    public TipoAtividade getTipoAtividadeById(long id) {
        try {
            return tipoAtividadeDAO.getById(id);
        } catch (SQLException ex) {
            Logger.getLogger(TipoAtividadeService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<TipoAtividade> getTiposDeAtividades() {
        try {
            return tipoAtividadeDAO.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(TipoAtividadeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public TipoAtividade getTipoDeAtividadeById(long id) {
        try {
            return tipoAtividadeDAO.getById(id);
        } catch (SQLException ex) {
            Logger.getLogger(TipoAtividadeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<TipoAtividade> getTiposDeAtividadesByEventoId(long id) {
        try {
            return tipoAtividadeDAO.getByEventoId(id);
        } catch (SQLException ex) {
            Logger.getLogger(TipoAtividadeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean adicionar(TipoAtividade tipo) {
        try {
            tipoAtividadeDAO.insert(tipo);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean excluir(long tipoId) {
        try {
            tipoAtividadeDAO.delete(tipoId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean atualizar(TipoAtividade taEdit) {
        try {
            tipoAtividadeDAO.update(taEdit);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
