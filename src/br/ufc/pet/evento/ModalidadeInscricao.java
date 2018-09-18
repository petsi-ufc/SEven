package br.ufc.pet.evento;

import java.util.ArrayList;

/*
 * @author Escritorio projetos
 */
public class ModalidadeInscricao extends Bean{
private String tipo;
private ArrayList<PrecoAtividade> precoAtividades;
private Long eventoId;

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public ArrayList<PrecoAtividade> getPrecoAtividades() {
        return precoAtividades;
    }

    public void setPrecoAtividades(ArrayList<PrecoAtividade> precoAtividades) {
        this.precoAtividades = precoAtividades;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
