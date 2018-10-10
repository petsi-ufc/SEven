package br.ufc.pet.services;

import br.ufc.pet.daos.HorarioDAO;
import br.ufc.pet.entity.Horario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class HorarioService {

    private final HorarioDAO horarioDAO;
    
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
            return horarioDAO.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Horario> getHorariosByAtivideId(long id) {
        try {
            return horarioDAO.getByAtividadeId(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Horario> getHorariosByEventoId(long id) {
        try {
            return horarioDAO.getByEventoId(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Horario> getAllHorarios() {
        try {
            return horarioDAO.getAllHorarios();
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
    
    //Esse método confere se a data do horário informado pelo organizador está entre a data de inicio e de fim do evento.
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
