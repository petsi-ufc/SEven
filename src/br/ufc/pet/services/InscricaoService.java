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
            //fazer com que as atividades no array de atividades insiram uma tupla na tabela inscricao_atividade
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
            Inscricao en = inscricaoDAO.getById(id);
            if (en != null) {
                en.setEvento(eS.getEventoById(en.getEvento().getId()));
                en.setParticipante(pS.getById(en.getParticipante().getId()));
                en.setModalidade(mS.getModalidadeInscricaoById(en.getModalidade().getId()));
                en.setAtividades(aS.getAtividadeByInscricaoId(en.getId()));
            }
            return en;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Inscricao getInscricaoByCodigoValidacao(String codigo){
        try {
            Inscricao en = inscricaoDAO.getByCodigoValidacao(codigo);
            if (en != null) {
                en.setEvento(eS.getEventoById(en.getEvento().getId()));
                en.setParticipante(pS.getById(en.getParticipante().getId()));
                en.setModalidade(mS.getModalidadeInscricaoById(en.getModalidade().getId()));
                en.setAtividades(aS.getAtividadeByInscricaoId(en.getId()));
            }
            return en;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public Inscricao getInscricaoParticipanteEvento(Utility utility) {
        try {
            Inscricao en = inscricaoDAO.getParticipanteEvento(utility);
            if (en != null) {
                en.setEvento(eS.getEventoById(en.getEvento().getId()));
                en.setParticipante(pS.getById(en.getParticipante().getId()));
                en.setModalidade(mS.getModalidadeInscricaoById(en.getModalidade().getId()));
                en.setAtividades(aS.getAtividadeByInscricaoId(en.getId()));
            }
            return en;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    
    public ArrayList<Inscricao> getAllInscricaoByParticipanteId(Long id) {
        try {
            ArrayList<Inscricao> a = inscricaoDAO.getByParticipanteId(id);
            for (Inscricao en : a) {
                en.setEvento(eS.getEventoById(en.getEvento().getId()));
                en.setParticipante(pS.getById(id));
                en.setModalidade(mS.getModalidadeInscricaoById(en.getModalidade().getId()));
                en.setAtividades(aS.getAtividadeByInscricaoId(en.getId()));
            }
            return a;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Long getInscritosByAtividadeId(Long atividadeId) throws SQLException {
        return inscricaoDAO.getInscritosByAtividadeId(atividadeId);
    }

    public ArrayList<Inscricao> getAllInscricoesByAtividadeId(Long id) {
        ArrayList<Inscricao> a = null;
        try {
            a = inscricaoDAO.getInscricoesByAtividadeId(id);
        } catch (SQLException ex) {
            Logger.getLogger(InscricaoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Inscricao en : a) {
            en.setEvento(eS.getEventoById(en.getEvento().getId()));
            en.setParticipante(pS.getById(en.getParticipante().getId()));
            en.setModalidade(mS.getModalidadeInscricaoById(en.getModalidade().getId()));
            en.setAtividades(aS.getAtividadeByInscricaoId(en.getId()));
        }
        return a;
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
            ArrayList<Inscricao> a = inscricaoDAO.getAllInscricoesByAuxInscricao(inscricao);
            for (Inscricao en : a) {
                en.setEvento(eS.getEventoById(en.getEvento().getId()));
                en.setParticipante(pS.getById(en.getParticipante().getId()));
                en.setModalidade(mS.getModalidadeInscricaoById(en.getModalidade().getId()));
            }
            return a;
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
