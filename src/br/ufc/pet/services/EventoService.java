package br.ufc.pet.services;

import br.ufc.pet.daos.EventoDAO;
import br.ufc.pet.entity.Evento;
import br.ufc.pet.entity.Organizador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/*
 * @author fernando
 */
public class EventoService {

    private final EventoDAO eventoDAO;

    public EventoService() {
        eventoDAO = new EventoDAO();
    }

    public boolean adicionar(Evento evento) {
        try {
            eventoDAO.insert(evento);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Evento evento) {
        try {
            eventoDAO.update(evento);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean encerrar(Long id) {
        try {
            eventoDAO.encerrar(id);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean ativar(Long id) {
        try {
            eventoDAO.ativar(id);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean excluir(Evento evento) {
        try {
            eventoDAO.delete(evento.getId());
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Evento> buscarEventosAbertos() {
        try {
            return eventoDAO.getAllEventosAbertos();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Evento> buscarAllEventos() {
        try {
            return eventoDAO.getAllEventos();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Evento> buscarEventosComInscricoesAbertas() {
        try {
            ArrayList<Evento> eventos = eventoDAO.getAllEventosAbertos();
            ArrayList<Evento> events = new ArrayList<Evento>();
            Date hoje = new Date();
            if (eventos != null) {
                for (Evento e : eventos) {
                    Date ini = e.getInicioPeriodoInscricao();
                    Date fim = e.getFimPeriodoInscricao();
                    if ((ini.before(hoje) && fim.after(hoje))) {
                        events.add(e);
                    }
                }
            }
            return events;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Evento getEventoById(long id) {
        try {
            return eventoDAO.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Evento getEventoByOrganizacaoId(Long id) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Evento getEventoBySigla(String sigla) {
        try {
            return eventoDAO.getBySigla(sigla);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }
    
    public Evento getEventoByNome(String nome) {
        try {

            return eventoDAO.getByNome(nome);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }
}
