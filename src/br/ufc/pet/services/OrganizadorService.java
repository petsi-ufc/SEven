package br.ufc.pet.services;

import br.ufc.pet.daos.OrganizadorDAO;
import br.ufc.pet.entity.Organizador;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrganizadorService {

    private final OrganizadorDAO organizadorDAO;

    public OrganizadorService() {
        organizadorDAO = new OrganizadorDAO();
    }

    public Organizador getByUsuarioId(Long id) {
        try {
            return organizadorDAO.getByUsuarioId(id);            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean adicionar(Organizador org) {
        try {
            if (org != null) {
                organizadorDAO.insert(org);
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<Organizador> getOrganizadoresByEventoId(Long idEvento) {
        try {
            return organizadorDAO.getOrganizadoresByEvento(idEvento);
        } catch (SQLException ex) {
            return null;
        }
    }
}
