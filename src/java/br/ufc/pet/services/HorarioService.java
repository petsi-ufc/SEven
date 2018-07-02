package br.ufc.pet.services;

import br.ufc.pet.daos.HorarioDAO;
import br.ufc.pet.evento.Horario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/*
 * @author Caio
 */
public class HorarioService {

    private final HorarioDAO horarioDAO;
    
    //Atributos de data usados para conferir se os horários cadastrados estão entre
    //os dias de evento.
    private Date dataEventoFim;
    private Date dataEventoInicio;
    
    public HorarioService() {
        horarioDAO = new HorarioDAO();
    }
    
    public HorarioService(Date dataEventoInicio, Date dataEventoFim) {
        horarioDAO = new HorarioDAO();
        this.dataEventoFim = dataEventoFim;
        this.dataEventoInicio = dataEventoInicio;
    }

    public Horario getHorarioById(long id) {
        try {
            Horario en = horarioDAO.getById(id);
            return en;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Horario> getHorariosByAtivideId(long id) {
        try {
            ArrayList<Horario> horarios = horarioDAO.getByAtividadeId(id);
            return horarios;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Horario> getHorariosByEventoId(long id) {
        try {
            ArrayList<Horario> horarios = horarioDAO.getByEventoId(id);
            return horarios;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Horario> getAllHorarios() {
        try {
            ArrayList<Horario> horarios = horarioDAO.getAllHorarios();
            return horarios;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean adicionar(Horario horario) {
        try {
            if(conferirHorario(horario)){
                horarioDAO.insert(horario);
                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean excluir(long horId) {
        try {
            horarioDAO.delete(horId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean atualizar(Horario horario) {
        try {
            if(conferirHorario(horario)){
                horarioDAO.update(horario);
                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    //Esse método confere se a data do horário informado pelo organizador está
    //entre a data de inicio e de fim do evento.
    public boolean conferirHorario(Horario horario){
        if(dataEventoFim != null && dataEventoInicio != null){
           if(horario.getDia().before(dataEventoFim) && 
                   horario.getDia().after(dataEventoInicio)
               ||  horario.getDia().equals(dataEventoInicio)
               ||  horario.getDia().equals(dataEventoFim))
                     return true;
          return false;
        }
        return false;
    }
    
}
