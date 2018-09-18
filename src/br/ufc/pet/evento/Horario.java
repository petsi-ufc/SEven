package br.ufc.pet.evento;

import br.ufc.pet.util.UtilSeven;
import java.util.Date;

public class Horario extends Bean implements Comparable<Horario> {
    
    public static int DIA_EM_MINUTOS = 1440;

    private Date dia;
    private int horaInicial;
    private int minutoInicial;
    private int horaFinal;
    private int minutoFinal;
    private Long eventoId;

    public Horario() {
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public int getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }

    public int getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(int horaInicial) {
        this.horaInicial = horaInicial;
    }

    public int getMinutoFinal() {
        return minutoFinal;
    }

    public void setMinutoFinal(int minutoFinal) {
        this.minutoFinal = minutoFinal;
    }

    public int getMinutoInicial() {
        return minutoInicial;
    }

    public void setMinutoInicial(int minutoInicial) {
        this.minutoInicial = minutoInicial;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public String printHorario() {
        String data = UtilSeven.treatToString(this.getDia());

        data = data + ", de " + String.format("%02d", this.getHoraInicial()) + ":" + String.format("%02d", this.getMinutoInicial()) + " às " + String.format("%02d", this.getHoraFinal()) + ":" + String.format("%02d", this.getMinutoFinal());
        return data;
    }

    public boolean isEqual(Horario horario) {
        if (this.id.compareTo(horario.getId()) == 0) {
            return true;
        }
        return false;
    }

    public boolean conflitaComHorario(Horario other) {
        if (!this.getDia().equals(other.getDia())) {
            return false;
        } else if (this.getHoraFinal() < other.getHoraInicial() || other.getHoraFinal() < this.getHoraInicial()) {
            return false;
        } else if (this.getHoraFinal() == other.getHoraInicial() && this.getMinutoFinal() <= other.getMinutoInicial()) {
            return false;
        } else if (other.getHoraFinal() == this.getHoraInicial() && other.getMinutoFinal() <= this.getMinutoInicial()) {
            return false;
        }
        return true;
    }

    public String exibirFormatoSimples(){
    return String.format("%02d", this.getHoraInicial()) + ":" + String.format("%02d", this.getMinutoInicial())+ "</br>" +
            String.format("%02d", this.getHoraFinal()) + ":" + String.format("%02d", this.getMinutoFinal());
    }

    @Override
    public int compareTo(Horario otherHorario) {
        int dia = this.dia.compareTo(otherHorario.getDia());
        if (dia == 0) {
            //compara horario final
            if (this.horaFinal < otherHorario.horaFinal) {
                return -1;
            }
            if (otherHorario.horaFinal < this.horaFinal) {
                return 1;
            }
            //desempata com horario inicial
            if (this.horaInicial < otherHorario.horaInicial ) {
                return -1;
            }
            if (otherHorario.horaInicial < this.horaInicial) {
                return 1;
            }
            //desempata com minuto final
            if (this.minutoFinal < otherHorario.minutoFinal) {
                return -1;
            }
            if (otherHorario.minutoFinal < this.minutoFinal) {
                return 1;
            }
            //desempata com minuto inicial
            if (this.minutoInicial < otherHorario.minutoInicial) {
                return -1;
            }
            if (otherHorario.minutoInicial < this.minutoInicial) {
                return 1;
            }
            return 0;
        }

        return dia;

    }    
}
