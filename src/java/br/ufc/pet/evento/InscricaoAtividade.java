package br.ufc.pet.evento;


/*
 * @author anderson
 */

public class InscricaoAtividade extends Bean{

    private Long atividadeId;
    private Long inscricaoId;
    private boolean confirmaCertificado;

    
    
    public InscricaoAtividade(){
    
    }
    
    
    public Long getAtividadeId() {
        return atividadeId;
    }

    public void setAtividadeId(Long atividadeId) {
        this.atividadeId = atividadeId;
    }

    public Long getInscricaoId() {
        return inscricaoId;
    }

    public void setInscricaoId(Long inscricaoId) {
        this.inscricaoId = inscricaoId;
    }

    public boolean isConfirmaCertificado() {
        return this.confirmaCertificado;
    }
    
    public void setConfirmaCertificado(boolean confirmaCertificado) {
        this.confirmaCertificado = confirmaCertificado;
    }
    
}
