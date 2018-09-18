package br.ufc.pet.evento;

import java.util.ArrayList;

/*
 * @author Escritorio projetos
 */
public class ResponsavelAtividade extends Perfil{
    private ArrayList<Atividade> atividades;

    public ArrayList<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(ArrayList<Atividade> atividades) {
        this.atividades = atividades;
    }

    
}
