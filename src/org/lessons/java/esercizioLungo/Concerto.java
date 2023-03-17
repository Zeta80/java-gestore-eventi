package org.lessons.java.esercizioLungo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento {

    private LocalTime ora;
    private BigDecimal prezzo;

    public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime ora, BigDecimal prezzo) throws IllegalArgumentException {
        super(titolo, data, postiTotali);
        setOra(data, ora);
        setPrezzo(prezzo);
    }

    // getter e setter
    //data e ora
    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalDate data, LocalTime ora) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data già passata");
        }
        this.ora = ora;
    }
    //prezzo
    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        if (prezzo.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Prezzo non valido");
        }
        this.prezzo = prezzo;
    }
    //METODI
    public String getDataOraFormattata() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.of(getData(), getOra()).format(formatter);
    }

    public String getPrezzoFormattato() {
        return prezzo.setScale(2).toString() + "€";
    }

    @Override
    public String toString() {
        return getDataOraFormattata() + " - " + getTitolo() + " - " + getPrezzoFormattato();
    }
}
