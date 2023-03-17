package org.lessons.java.esercizioLungo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProgrammEventi {
    private String titolo;
    private List<Evento> eventi;

    public ProgrammEventi(String titolo) {
        this.titolo = titolo;
        this.eventi = new ArrayList<>();
    }

    public void aggiungiEvento(Evento evento) {
        this.eventi.add(evento);
    }

    public List<Evento> eventiInData(LocalDate data) {
        return eventi.stream()
                .filter(evento -> evento.getData().equals(data))
                .collect(Collectors.toList());
    }

    public int numeroEventi() {
        return eventi.size();
    }

    public void cancellaEventi() {
        eventi.clear();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(titolo).append("\n");
        eventi.stream()
                .sorted(Comparator.comparing(Evento::getData))
                .forEach(evento -> sb.append(evento.getData()).append(" - ").append(evento.getTitolo()).append("\n"));
        return sb.toString();
    }
}
