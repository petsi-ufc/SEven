package br.ufc.pet.entity;

import java.util.ArrayList;
import java.util.List;

public class Organizador extends Perfil {

    private List<Organizacao> organizacoes;

    public Organizador() {
        
    }

    public ArrayList<Organizacao> getOrganizacoes() {
        return new ArrayList<>(organizacoes);
    }

    public void setOrganizacoes(List<Organizacao> organizacoes) {
        this.organizacoes = new ArrayList<>(organizacoes);
    }

    public void setOrganizacaoAdd(Organizacao organizacao) {
        this.organizacoes.add(organizacao);
    }

    public boolean deleteOrganizacao(Evento en) {
        for (int i = 0; i < organizacoes.size(); i++) {
            if (organizacoes.get(i).getEvento().getId().equals(en.getId())) {
                organizacoes.remove(i);
                return true;
            }
        }
        return false;
    }

    public Organizacao recuperarOrganizacaoByEvendoId(Long id) {
        for (int i = 0; i < this.organizacoes.size(); i++) {
            if (this.organizacoes.get(i).getEvento().getId().compareTo(id)== 0) {
                return this.organizacoes.get(i);
            }
        }
        return null;
    }
}
 
