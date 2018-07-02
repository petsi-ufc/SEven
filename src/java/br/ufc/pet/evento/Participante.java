package br.ufc.pet.evento;

import java.util.ArrayList;

public class Participante extends Perfil implements Comparable<Participante> {

    private ArrayList<Inscricao> inscricoes;

    public Participante() {
        this.inscricoes = new ArrayList<Inscricao>();
    }

    public ArrayList<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(ArrayList<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }

    public Inscricao getInscricaoByEvento(Long eveId) {
        if (this.inscricoes == null) {
            return null;
        }
        for (Inscricao inscricao : this.inscricoes) {
            if (inscricao.getEvento().getId().compareTo(eveId) == 0) {
                return inscricao;
            }
        }
        return null;
    }

    @Override
    public int compareTo(Participante p) {
        return this.getUsuario().getNome().compareToIgnoreCase(p.getUsuario().getNome());
    }
}
 
