package br.ufc.pet.evento;

import java.util.ArrayList;

public class Organizador extends Perfil {

    private ArrayList<Organizacao> organizacoes;

    public Organizador() {

        this.organizacoes = new ArrayList<Organizacao>();
    }

    public ArrayList<Organizacao> getOrganizacoes() {
        return organizacoes;
    }

    public void setOrganizacoes(ArrayList<Organizacao> organizacoes) {
        this.organizacoes = organizacoes;
    }

    public void setOrganizacoes(Organizacao organizacao) {
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

    public Organizacao recuperarOrganizaçãoByEvendoId(Long id) {
        for (int i = 0; i < this.organizacoes.size(); i++) {
            if (this.organizacoes.get(i).getEvento().getId().compareTo(id)== 0) {
                return this.organizacoes.get(i);
            }
        }
        return null;
    }
}
 
