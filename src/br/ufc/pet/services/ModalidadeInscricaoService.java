package br.ufc.pet.services;

import br.ufc.pet.daos.ModalidadeInscricaoDAO;
import br.ufc.pet.entity.ModalidadeInscricao;

import java.sql.SQLException;
import java.util.ArrayList;

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
            return modalidadeInscricaoDAO.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<ModalidadeInscricao> getModalidadesInscricaoByEventoId(Long id) {
        try {
            return modalidadeInscricaoDAO.getModalidadesByEventoId(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
