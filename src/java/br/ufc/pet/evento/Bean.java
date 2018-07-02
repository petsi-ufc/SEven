package br.ufc.pet.evento;

import java.io.Serializable;

/*
 * @author Escritorio projetos
 */
public class Bean implements Serializable{
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

}
