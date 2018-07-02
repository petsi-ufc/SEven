package br.ufc.pet.evento;

import java.util.ArrayList;
import java.util.Date;

public class Inscricao extends Bean {

    private boolean confirmada;
    private Date dataRealizada;
    private Date dataPagamento;
    private Evento evento;
    private ArrayList<Atividade> atividades;
    private Participante participante;
    private ModalidadeInscricao modalidade;
    private String codigoValidacaoCertificado;
    private String emailP;

    public Inscricao() {
        this.confirmada = false;
        this.evento = new Evento();
        this.modalidade = new ModalidadeInscricao();
        this.participante = new Participante();
        this.atividades=new ArrayList<Atividade>();
        this.emailP = participante.getUsuario().getEmail();

    }

    public ArrayList<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(ArrayList<Atividade> atividades) {
        this.atividades = atividades;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataRealizada() {
        return dataRealizada;
    }

    public void setDataRealizada(Date dataRealizada) {
        this.dataRealizada = dataRealizada;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public ModalidadeInscricao getModalidade() {
        return modalidade;
    }

    public void setModalidade(ModalidadeInscricao modalidade) {
        this.modalidade = modalidade;
    }
    
    public String getCodigoValidacaoCertificado(){
        return this.codigoValidacaoCertificado;
    }
    
    public void setCodigoValidacaoCertificado(String codigoValidacaoCertificado){
        this.codigoValidacaoCertificado = codigoValidacaoCertificado;
    }

    public String confimadaToString() {
        if (isConfirmada()) {
            return "SIM";
        } else {
            return "NAO";
        }
    }

    @Override
    public String toString() {
        return "Inscricao{" + "confirmada=" + confirmada + ", dataRealizada=" + dataRealizada + ", dataPagamento=" + dataPagamento + ", evento=" + evento + ", atividades=" + atividades + ", participante=" + participante + ", modalidade=" + modalidade + ", codigoValidacaoCertificado=" + codigoValidacaoCertificado + '}';
    }
    
}
 
