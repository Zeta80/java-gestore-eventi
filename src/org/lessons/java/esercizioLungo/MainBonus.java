package org.lessons.java.esercizioLungo;


    import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
    import java.time.format.DateTimeFormatter;
    import java.util.List;

public class MainBonus {
    public static void main(String[] args) {
        ProgrammEventi program = new ProgrammEventi("Programma Eventi 2023");

        // Creazione di un nuovo concerto e aggiunta al programma di eventi
        LocalDate dataConcerto = LocalDate.of(2023, 6, 10);
        LocalTime oraConcerto = LocalTime.of(21, 0);
        Concerto concerto = new Concerto("Concerto di primavera", dataConcerto, 30, oraConcerto, new BigDecimal("50"));
        program.aggiungiEvento(concerto);

        LocalDate dataConcerto2 = LocalDate.of(2025, 12, 10);
        LocalTime oraConcerto2 = LocalTime.of(20, 0);
        Concerto concerto2 = new Concerto("Concertone", dataConcerto2, 30, oraConcerto2, new BigDecimal("60"));
        program.aggiungiEvento(concerto2);

        // Stampa la lista dei concerti
        List<Evento> concerti = program.eventiInData(dataConcerto);
        System.out.println("Concerti del " + dataConcerto.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ":");
        for (Evento evento : concerti) {
            System.out.println(evento.getData() + " - " + evento.getTitolo());
        }

        List<Evento> concerti2 = program.eventiInData(dataConcerto2);
        System.out.println("Concerti del " + dataConcerto.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ":");
        for (Evento evento : concerti2) {
            System.out.println(evento.getData() + " - " + evento.getTitolo());
        }
    }

    }

