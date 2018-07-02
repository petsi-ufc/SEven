package br.ufc.pet.evento;

import java.util.ArrayList;
import java.util.Date;

public class Evento extends Bean {

    private String nome;
    private String sigla;
    private String tema;
    private Date inicioPeriodoInscricao;
    private Date fimPeriodoInscricao;
    private String descricao;
    private boolean ativo;
    private ArrayList<Atividade> atividades;
    private ArrayList<Organizador> organizadores;
    private Administrador administrador;
    private ArrayList<MovimentacaoFinanceira> movimentacoesFinanceiras;
    private Date inicioPeriodoEvento;
    private Date fimPeriodoEvento;
    private int limiteAtividadePorParticipante;
    private boolean gratuito;

    public Date getFimPeriodoEvento() {
        return fimPeriodoEvento;
    }

    public void setFimPeriodoEvento(Date fimPeriodoEvento) {
        this.fimPeriodoEvento = fimPeriodoEvento;
    }

    public Date getInicioPeriodoEvento() {
        return inicioPeriodoEvento;
    }

    public void setInicioPeriodoEvento(Date inicioPeriodoEvento) {
        this.inicioPeriodoEvento = inicioPeriodoEvento;
    }

    public Evento() {
        this.administrador = new Administrador();
        this.ativo = true;
        this.organizadores = new ArrayList<Organizador>();
        this.atividades = new ArrayList<Atividade>();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public ArrayList<Atividade> getAtividades() {

        return atividades;
    }

    public ArrayList<Atividade> getAtividadeQueAceitamInscricao() {

        ArrayList<Atividade> ats = new ArrayList<Atividade>();

        for (Atividade a : atividades) {
            if (a.isAceitaInscricao()) {
                ats.add(a);
            }
        }
        return ats;
    }

    public void setAtividades(ArrayList<Atividade> atividades) {
        this.atividades = atividades;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getFimPeriodoInscricao() {
        return fimPeriodoInscricao;
    }

    public void setFimPeriodoInscricao(Date fimPerodoInscricao) {
        this.fimPeriodoInscricao = fimPerodoInscricao;
    }

    public Date getInicioPeriodoInscricao() {
        return inicioPeriodoInscricao;
    }

    public void setInicioPeriodoInscricao(Date inicioPeriodoInscricao) {
        this.inicioPeriodoInscricao = inicioPeriodoInscricao;
    }

    public ArrayList<MovimentacaoFinanceira> getMovimentacoesFinanceiras() {
        return movimentacoesFinanceiras;
    }

    public void setMovimentacoesFinanceiras(ArrayList<MovimentacaoFinanceira> movimentacoesFinanceiras) {
        this.movimentacoesFinanceiras = movimentacoesFinanceiras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Organizador> getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(ArrayList<Organizador> organizador) {
        this.organizadores = organizador;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void addOrganizador(Organizador org) {
        this.organizadores.add(org);
    }

    public boolean deleteOrganizador(Organizador org) {
        for (int i = 0; i < organizadores.size(); i++) {
            if (organizadores.get(i).getId().equals(org.getId())) {
                organizadores.remove(i);
                return true;
            }
        }
        return false;
    }

    public Atividade recuperarAtividadeCadastrada(Long id) {
        for (Atividade ativ : this.atividades) {
            if (ativ.getId().compareTo(id) == 0) {
                return ativ;
            }
        }
        return null;
    }

    public Atividade removerAtividadeCadastradaById(Long id) {
        for (int i = 0; i < this.atividades.size(); i++) {
            if (this.atividades.get(i).getId().compareTo(id) == 0) {
                return this.atividades.remove(i);
            }
        }
        return null;
    }

    public void setLimiteAtividadePorParticipante(int limiteDeAtividades) {
        this.limiteAtividadePorParticipante = limiteDeAtividades;
    }
    
    public int getLimiteAtividadePorParticipante() {
       return this.limiteAtividadePorParticipante;
    }

    public void setGratuito(boolean gratuito){
        this.gratuito = gratuito;
    }
    
    public boolean isGratuito(){
        return this.gratuito;
    }
    
    @Override
    public String toString() {
        return "Evento{" + "id=" + id + ", nome=" + nome + ", sigla=" + sigla + ", tema=" + tema + ", inicioPeriodoInscricao=" + inicioPeriodoInscricao + ", fimPeriodoInscricao=" + fimPeriodoInscricao + ", descricao=" + descricao + ", ativo=" + ativo + ", atividades=" + atividades + ", organizadores=" + organizadores + ", administrador=" + administrador + ", movimentacoesFinanceiras=" + movimentacoesFinanceiras + ", inicioPeriodoEvento=" + inicioPeriodoEvento + ", fimPeriodoEvento=" + fimPeriodoEvento + ", limiteAtividadePorParticipante=" + limiteAtividadePorParticipante + '}';
    }
}
 
