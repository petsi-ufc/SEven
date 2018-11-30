package br.ufc.pet.services;

import br.ufc.pet.daos.InscricaoDAO;
import br.ufc.pet.entity.Atividade;
import br.ufc.pet.entity.Evento;
import br.ufc.pet.entity.Inscricao;
import br.ufc.pet.entity.InscricaoAtividade;
import br.ufc.pet.entity.PrecoAtividade;
import br.ufc.pet.entity.Utility;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InscricaoService {

    private final InscricaoDAO inscricaoDAO;
    EventoService eS = new EventoService();
    ParticipanteService pS = new ParticipanteService();
    ModalidadeInscricaoService mS = new ModalidadeInscricaoService();
    AtividadeService aS = new AtividadeService();

    public InscricaoService() {
        inscricaoDAO = new InscricaoDAO();
    }

    public boolean adicionar(Inscricao inscricao) {
        try {
            inscricaoDAO.insert(inscricao);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean atualizar(Inscricao inscricao){
        try {
            inscricaoDAO.update(inscricao);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Inscricao getInscricaoById(long id) {
        try {
            return inscricaoDAO.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Inscricao getInscricaoByCodigoValidacao(String codigo){
        try {
            return inscricaoDAO.getByCodigoValidacao(codigo);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Inscricao getInscricaoParticipanteEvento(Utility utility) {
        try {
            return inscricaoDAO.getParticipanteEvento(utility);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    
    public ArrayList<Inscricao> getAllInscricaoByParticipanteId(Long id) {
        try {
            return inscricaoDAO.getByParticipanteId(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Long getInscritosByAtividadeId(Long atividadeId) throws SQLException {
        return inscricaoDAO.getInscritosByAtividadeId(atividadeId);
    }

    public ArrayList<Inscricao> getAllInscricoesByAtividadeId(Long id) {
        try {
            return inscricaoDAO.getInscricoesByAtividadeId(id);
        } catch (SQLException ex) {
            Logger.getLogger(InscricaoService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Inscricao> getAllInscricoesByEventoId(Long id) {
        try {
            return inscricaoDAO.getInscricoesByEventoId(id);
        } catch (SQLException ex) {
            Logger.getLogger(InscricaoService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean excluir(Long id) {
        try {
            inscricaoDAO.excluir(id);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public double getPrecoInscricao(Inscricao i) {
        double preco = 0;
        for (PrecoAtividade p : i.getModalidade().getPrecoAtividades()) {
            for (Atividade a : i.getAtividades()) {
                if (a.getTipo().getId().equals(p.getTipoAtividadeId())) {
                    preco += p.getValor(); 
                }
            }
        }
        return preco;
    }

    public ArrayList<Inscricao> getAllInscricoesByAuxInscricao(Inscricao inscricao) {
        try {
            return inscricaoDAO.getAllInscricoesByAuxInscricao(inscricao);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean confirmaPagamento(Inscricao inscricao) {
        try {
            inscricaoDAO.confirmarPagamento(inscricao);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean confirmarTodosPagamentos(Long eventoId) {
        try {
            inscricaoDAO.confirmarTodosPagamentos(eventoId);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void confirmaLiberacaoCertificadoAtividade(InscricaoAtividade a ){
    
    }
}
