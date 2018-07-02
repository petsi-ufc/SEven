package br.ufc.pet.services;

import br.ufc.pet.daos.OrganizacaoDAO;
import br.ufc.pet.evento.Organizacao;
import java.util.ArrayList;
import java.sql.SQLException;

/*
 * @author fernando
 */
public class OrganizacaoService {

    private final OrganizacaoDAO organizacaoDAO;

    public OrganizacaoService() {
        organizacaoDAO = new OrganizacaoDAO();
    }

    public ArrayList<Organizacao> getAllOrganizacoesByOrganizadorId(Long id) {
        try {
            ArrayList<Organizacao> orgs = organizacaoDAO.getByOrganizacoesByOrganizadorId(id);
            EventoService es = new EventoService();
            OrganizadorService os = new OrganizadorService();
            for (Organizacao o : orgs) {
                o.setEvento(es.getEventoById(o.getEvento().getId()));
            }
            return orgs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean adicionar(Organizacao org) {
        try {
            organizacaoDAO.insert(org);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;

        }
    }

    public boolean update(Organizacao organizacao) {
        try {
            organizacaoDAO.update(organizacao);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public Organizacao getOrganizacaoByOrganizadorIdAndEventoId(Organizacao org) {
        try {
            return organizacaoDAO.getOrganizacaoByOrganizadorIdAndEventoId(org);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public boolean delete(Organizacao organizacao) {
        try {
            organizacaoDAO.delete(organizacao);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }
}
