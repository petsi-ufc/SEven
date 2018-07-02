package br.ufc.pet.evento;

import java.util.Date;

public class Perfil extends Bean{
    
    private Date dataCriacao;
    private boolean status;
    private Usuario usuario;

    public Perfil() {
        this.usuario = new Usuario();
    }
    
    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public boolean getStatus() {
        return status;
    }



}
 
