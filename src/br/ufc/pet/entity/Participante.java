package br.ufc.pet.entity;

import java.util.ArrayList;
import java.util.List;

public class Participante extends Perfil implements Comparable<Participante> {

    private List<Inscricao> inscricoes;

    public Participante() {
        this.inscricoes = new ArrayList<Inscricao>();
    }

    public ArrayList<Inscricao> getInscricoes() {
        return new ArrayList<>(inscricoes);
    }

    public void setInscricoes(List<Inscricao> inscricoes) {
        this.inscricoes = new ArrayList<>(inscricoes);
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