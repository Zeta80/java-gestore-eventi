package org.lessons.java.esercizioLungo;

import java.time.LocalDate;

public abstract class Evento {
    private String titolo;
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati;

    public Evento(String titolo, LocalDate data, int postiTotali) throws IllegalArgumentException {

        setTitolo(titolo);
        setData(data);
        setPostiTotali(postiTotali);
        this.postiPrenotati = 0;
    }

    //getter e setter
    //titolo
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    //data
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) throws IllegalArgumentException {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data non valida: non è possibile spostare un evento nel passato.");
        }
        this.data = data;
    }
    //posti totali
    public int getPostiTotali() {
        return postiTotali;
    }

    public void setPostiTotali(int postiTotali) {
        this.postiTotali = postiTotali;
    }
    //posti prenotati
    public int getPostiPrenotati() {
        return postiPrenotati;
    }
    // METODI
    public void prenota(int numPosto) throws IllegalArgumentException {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("cosa sei un viaggiatore del tempo? cambia data.");
        }
        if (postiPrenotati >= postiTotali) {
            throw new IllegalArgumentException("troppo lento bambol, descantabauchi i posti son finiti.");
        }
        postiPrenotati += numPosto;
    }

    public void disdici(int numPosto) throws IllegalArgumentException {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("non posso cancellare il passato");
        }
        if (postiPrenotati <= 0) {
            throw new IllegalArgumentException("sicuro? non mi risultano posti prenotati");
        }
        postiPrenotati -= numPosto;
    }

    @Override
    public String toString() {
        return getData() + " - " + getTitolo();
    }
}
