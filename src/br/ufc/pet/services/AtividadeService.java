package br.ufc.pet.services;

import br.ufc.pet.daos.AtividadeDAO;
import br.ufc.pet.evento.Atividade;
import br.ufc.pet.evento.Horario;
import br.ufc.pet.evento.InscricaoAtividade;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * @author caio
 */
public class AtividadeService {

    private final HorarioService hs = new HorarioService();
    private final AtividadeDAO atividadeDAO;
    private final EventoService es;
    private final ResponsavelAtividadeService responsavelService;

    public AtividadeService() {
        atividadeDAO = new AtividadeDAO();
        es = new EventoService();
        responsavelService = new ResponsavelAtividadeService();
    }

    public boolean adicionar(Atividade atividade) {
        try {
            atividadeDAO.insert(atividade);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Atividade atividade) {
        try {
            atividadeDAO.update(atividade);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean excluir(Long id) {
        try {
            atividadeDAO.delete(id);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Atividade getAtividadeById(Long id) {
        try {
            Atividade at = atividadeDAO.getById(id);
            at.setHorarios(hs.getHorariosByAtivideId(id));
            at.setEvento(es.getEventoById(at.getEvento().getId()));
            at.setResponsaveis(responsavelService.getResponsavelAtividade(at.getId()));
            return at;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Atividade> getAtividadeByEventoId(Long id) {
        try {
            ArrayList<Atividade> aa = atividadeDAO.getByEventoId(id);
            for (Atividade a : aa) {
                a.setHorarios(hs.getHorariosByAtivideId(a.getId()));
                a.setResponsaveis(responsavelService.getResponsavelAtividade(a.getId()));
            }
            return aa;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Atividade> getAtividadeByInscricaoId(Long id) {
        try {
            ArrayList<Atividade> aa = atividadeDAO.getByInscricaoId(id);
            for (Atividade a : aa) {
                a.setHorarios(hs.getHorariosByAtivideId(a.getId()));
                a.setResponsaveis(responsavelService.getResponsavelAtividade(a.getId()));
            }
            return aa;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Atividade> getAtividadeByOrganizadorId(Long id) {
        try {
            ArrayList<Atividade> aa = atividadeDAO.getByOrganizadorId(id);
            for (Atividade a : aa) {
                a.setHorarios(hs.getHorariosByAtivideId(a.getId()));
                a.setResponsaveis(responsavelService.getResponsavelAtividade(a.getId()));
            }
            return aa;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean conflitam(Atividade A, Atividade B) {
        boolean conf = false;
        for (Horario i : A.getHorarios()) {
            for (Horario j : B.getHorarios()) {
                if (i.getDia().equals(j.getDia())) {
                    if (i.getHoraInicial() < j.getHoraFinal() && j.getHoraFinal() <= i.getHoraFinal()) {
                        conf = true;
                    } //caso 1
                    else if (i.getHoraInicial() <= j.getHoraInicial() && j.getHoraInicial() < i.getHoraFinal()) {
                        conf = true;
                    } //caso 2
                    else if (j.getHoraInicial() < i.getHoraFinal() && i.getHoraFinal() <= j.getHoraFinal()) {
                        conf = true;
                    } //caso 3
                    else if (j.getHoraInicial() <= i.getHoraInicial() && i.getHoraInicial() < j.getHoraFinal()) {
                        conf = true;
                    } //caso 4
                }
            }
        }
        return conf;
    }
    
    
    public boolean confirmaLiberacaoCertificadoAtividade(InscricaoAtividade utility ){
    
        
        if (utility==null || utility.getAtividadeId()==null)
            return false;
        
        try {
            atividadeDAO.confirmaLiberacaoCertificadoAtividade(utility);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
           
    }
    
    public ArrayList<InscricaoAtividade> getIncricaoAtividadeByInscricao(Long idInscricao){
        try {
            ArrayList<InscricaoAtividade> ia = atividadeDAO.getIncricaoAtividadeByInscricao(idInscricao);
            return ia;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
   }
}
