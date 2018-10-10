package br.ufc.pet.entity;

import java.util.ArrayList;
import java.util.List;

public class ModalidadeInscricao extends Bean{
	private String tipo;
	private List<PrecoAtividade> precoAtividades;
	private Long eventoId;

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public ArrayList<PrecoAtividade> getPrecoAtividades() {
        return new ArrayList<>(precoAtividades);
    }

    public void setPrecoAtividades(List<PrecoAtividade> precoAtividades) {
        this.precoAtividades = precoAtividades;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}