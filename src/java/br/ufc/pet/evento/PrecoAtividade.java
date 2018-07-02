package br.ufc.pet.evento;

public class PrecoAtividade extends Bean{

    private double valor;
    private long tipoAtividadeId;
    private long modalidadeId;

    public long getModalidadeId() {
        return modalidadeId;
    }

    public void setModalidadeId(long modalidadeId) {
        this.modalidadeId = modalidadeId;
    }

    public long getTipoAtividadeId() {
        return tipoAtividadeId;
    }

    public void setTipoAtividadeId(long tipoAtividadeId) {
        this.tipoAtividadeId = tipoAtividadeId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
 
