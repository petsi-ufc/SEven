package br.ufc.pet.evento;

import java.util.Date;

/**
 * Regra de Negocio 1 - Uma movimenta��o financeira s� poder� ser criada por um organizador 
 * com permiss�o para manipular o m�culo financeiro.
 * Regra de Negocio 2 - As atividades s� poder�o ser manipuladas por organizadores com
 * permiss�o para isso.
 * Regra de Negocio 3 - Uma movimenta��o financeira pode ser um d�bito ou um credito
 * 
 */
public class MovimentacaoFinanceira extends Bean {

    private Date data;
    private double valor;
    private String tipo;
    private String descricao;
    private Evento evento;
    private Organizador organizador;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    private MovimentacaoFinanceira AlterarMovimentacaoFinanceira(String descricao,Double valor, Date data, String tipo, Evento evento, Organizador organizador){
    MovimentacaoFinanceira movfinan = new MovimentacaoFinanceira();

    movfinan.setDescricao(descricao);
    movfinan.setValor(valor);
    movfinan.setData(data);
    movfinan.setTipo(tipo);
    movfinan.setEvento(evento);
    movfinan.setOrganizador(organizador);

    return movfinan;
    }
}
 
