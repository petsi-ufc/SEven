package br.ufc.pet.evento;

import java.util.ArrayList;

/*
 * @author ismaily
 */
public class Supervisor extends Perfil{


    private ArrayList<Organizador> organizadores;



    public Supervisor(){

    organizadores = new ArrayList<Organizador>();
    }

    public ArrayList<Organizador> getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(ArrayList<Organizador> organizadores) {
        this.organizadores = organizadores;
    }

    

}
