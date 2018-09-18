package br.ufc.pet.evento;

/*
 * @author Escritorio projetos
 */
public class TipoAtividade extends Bean {

    private String nome;
    private Long eventoId;

    public TipoAtividade() {
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }
    


}
