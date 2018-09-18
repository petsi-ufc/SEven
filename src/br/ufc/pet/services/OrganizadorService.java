package br.ufc.pet.services;

import br.ufc.pet.daos.OrganizadorDAO;
import br.ufc.pet.evento.Organizador;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author fernando
 */
public class OrganizadorService {

    private final OrganizadorDAO organizadorDAO;

    public OrganizadorService() {
        organizadorDAO = new OrganizadorDAO();
    }

    public Organizador getByUsuarioId(Long id) {
        try {
            Organizador org = organizadorDAO.getByUsuarioId(id);
            if (org != null) {
                UsuarioService us = new UsuarioService();
                org.setUsuario(us.getById(id));
                OrganizacaoService os = new OrganizacaoService();
                org.setOrganizacoes(os.getAllOrganizacoesByOrganizadorId(org.getId()));
                return org;
            }
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
            UsuarioService u = new UsuarioService();
            ArrayList<Organizador> orgs = organizadorDAO.getOrganizadoresByEvento(idEvento);
            if (orgs != null) {
                for (Organizador o : orgs) {
                    o.setUsuario(u.getById(o.getUsuario().getId()));
                }
            }
            return orgs;
        } catch (SQLException ex) {
            return null;
        }
    }
}
