package br.ufc.pet.entity;

import java.util.ArrayList;

public class Administrador extends Perfil {

    private ArrayList<Evento> eventos;

    public ArrayList<Evento> getEventos() {
        ArrayList<Evento> eventos_abertos = new ArrayList<Evento>();
        for(Evento e : eventos){
            if(e.isAtivo()){
                eventos_abertos.add(e);
            }
        }
        return eventos_abertos;
    }
    
    public ArrayList<Evento> getAllEventos() {
        return eventos;
    }
    
    public ArrayList<Evento> getEventosEncerrados() {
        ArrayList<Evento> eventos_encerrados = new ArrayList<Evento>();
        for(Evento e : eventos){
            if(!e.isAtivo()){
                eventos_encerrados.add(e);
            }
        }
        return eventos_encerrados;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    public void removerEventoById(Long id){
	    for(int i=0;i<eventos.size();i++){
		    if(eventos.get(i).getId().equals(id)){
		    	eventos.remove(i);
		    }
	    }
    }
    
    public void addEvento(Evento en){
    	eventos.add(en);
    }   
}