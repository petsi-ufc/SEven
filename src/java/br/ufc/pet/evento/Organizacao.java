package br.ufc.pet.evento;

/*
 * @author Escritorio projetos
 */
public class Organizacao extends Bean {

    private Organizador organizador;
    private Evento evento;
    private boolean manterAtividade;
    private boolean manterModuloFinanceiro;

    public Organizacao() {
    }
   
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    public boolean isManterAtividade() {
        return manterAtividade;
    }

    public void setManterAtividade(boolean manterAtividade) {
        this.manterAtividade = manterAtividade;
    }

    public boolean isManterModuloFinanceiro() {
        return manterModuloFinanceiro;
    }

    public void setManterModuloFinanceiro(boolean manterModuloFinanceiro) {
        this.manterModuloFinanceiro = manterModuloFinanceiro;
    }
    public boolean getManterAtividade() {
        return manterAtividade;
    }
    public boolean getManterModuloFinanceiro() {
        return manterModuloFinanceiro;
    }

    
}
