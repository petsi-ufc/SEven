package br.ufc.pet.services;

import br.ufc.pet.daos.ModalidadeInscricaoDAO;
import br.ufc.pet.evento.ModalidadeInscricao;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author Caio
 */
public class ModalidadeInscricaoService {

    private final ModalidadeInscricaoDAO modalidadeInscricaoDAO;

    public ModalidadeInscricaoService() {
        modalidadeInscricaoDAO = new ModalidadeInscricaoDAO();
    }

    public boolean adicionar(ModalidadeInscricao modalidadeInscricao) {
        try {
            modalidadeInscricaoDAO.insert(modalidadeInscricao);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(ModalidadeInscricao modalidadeInscricao){
        try {
            modalidadeInscricaoDAO.update(modalidadeInscricao);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public boolean excluir(ModalidadeInscricao modalidadeInscricao){
        try {
            modalidadeInscricaoDAO.delete(modalidadeInscricao.getId());
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public ModalidadeInscricao getModalidadeInscricaoById(long id) {
        try {
            ModalidadeInscricao en = modalidadeInscricaoDAO.getById(id);
            PrecoAtividadeService PS = new PrecoAtividadeService();
            en.setPrecoAtividades(PS.getPrecosByModalidadeId(en.getId()));
            return en;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<ModalidadeInscricao> getModalidadesInscricaoByEventoId(Long id) {
        try {
            ArrayList<ModalidadeInscricao> modalidades = modalidadeInscricaoDAO.getModalidadesByEventoId(id);
            PrecoAtividadeService PS = new PrecoAtividadeService();
            for(ModalidadeInscricao en : modalidades) {
                en.setPrecoAtividades(PS.getPrecosByModalidadeId(en.getId()));
            }
            return modalidades;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
