package br.ufc.pet.services;

import br.ufc.pet.daos.AtividadeDAO;
import br.ufc.pet.entity.Atividade;
import br.ufc.pet.entity.Horario;
import br.ufc.pet.entity.InscricaoAtividade;

import java.sql.SQLException;
import java.util.ArrayList;

public class AtividadeService {
	
    private final AtividadeDAO atividadeDAO;

    public AtividadeService() {
        atividadeDAO = new AtividadeDAO();
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
            return atividadeDAO.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Atividade> getAtividadesByEventoId(Long id) {
        try {
            return atividadeDAO.getByEventoId(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Atividade> getAtividadeByInscricaoId(Long id) {
        try {
            return atividadeDAO.getByInscricaoId(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Atividade> getAtividadeByOrganizadorId(Long id) {
        try {
            return atividadeDAO.getByOrganizadorId(id);
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
            return atividadeDAO.getIncricaoAtividadeByInscricao(idInscricao);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
   }
}
