package br.ufc.pet.entity;

import java.io.Serializable;

public class Bean implements Serializable{
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
