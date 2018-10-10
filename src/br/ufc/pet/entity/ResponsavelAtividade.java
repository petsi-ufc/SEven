package br.ufc.pet.entity;

import java.util.ArrayList;
import java.util.List;

public class ResponsavelAtividade extends Perfil{
    private List<Atividade> atividades;

    public ArrayList<Atividade> getAtividades() {
        return new ArrayList<>(atividades);
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }    
}
