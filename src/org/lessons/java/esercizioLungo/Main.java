package org.lessons.java.esercizioLungo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Chiedi all'utente di inserire i dati del concerto
        System.out.print("Inserisci il titolo del concerto: ");
        String titolo = scanner.nextLine();

        LocalDate oggi = LocalDate.now();
        //controllo che la data non sia prima della data odierna
        boolean dataValida = false;
        LocalDate giornata = null;
        while (!dataValida) {
            System.out.println("Inserisci la data dell'evento (formato: dd/MM/yyyy): ");
            String dataString = scanner.nextLine();
            try {
                LocalDate data = LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (data.isBefore(oggi)) {
                    System.out.println("La data inserita è precedente a quella odierna. Inserire una data valida.");
                } else {
                    giornata = data;
                    dataValida = true;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato data non valido. Inserire una data nel formato gg/mm/aaaa.");
            }
        }
        System.out.print("Inserisci l'ora del concerto (formato HH:mm): ");
        LocalTime ora = LocalTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("HH:mm"));
        System.out.print("Inserisci il prezzo del biglietto: ");
        BigDecimal prezzo = scanner.nextBigDecimal();
        System.out.print("Inserisci la capienza della location: ");
        int capienza = scanner.nextInt();

        // Crea un oggetto Concerto
        Concerto concerto = new Concerto(titolo, giornata, capienza, ora, prezzo);

        // Stampa le informazioni del concerto
        System.out.println("*********+++**********");
        System.out.println("Informazioni del concerto:");
        System.out.println("- Titolo: " + concerto.getTitolo());
        System.out.println("- Data e ora: " + concerto.getDataOraFormattata());
        System.out.println("- Prezzo: " + concerto.getPrezzoFormattato());
        System.out.println("- Capienza: " + concerto.getPostiTotali());
        System.out.println("- Posti prenotati: " + concerto.getPostiPrenotati());
        System.out.println("- Posti disponibili: " + concerto.getPostiDisponibili());
        System.out.println("*********+++**********");

        // Chiedi all'utente di effettuare delle prenotazioni
        System.out.print("Quanti posti vuoi prenotare? ");
        int postiDaPrenotare = scanner.nextInt();
        try {
            concerto.prenota(postiDaPrenotare);
            System.out.println("Prenotazione effettuata con successo!");
        } catch (Exception e) {
            System.out.println("Errore durante la prenotazione: " + e.getMessage());
        }

        // Stampa il numero di posti prenotati e disponibili
        System.out.println("Posti prenotati: " + concerto.getPostiPrenotati());
        System.out.println("Posti disponibili: " + concerto.getPostiDisponibili());

        // Chiedi all'utente di effettuare delle disdette
        System.out.print("Quanti posti vuoi disdire? ");
        int postiDaDisdire = scanner.nextInt();
        try {
            concerto.disdici(postiDaDisdire);
            System.out.println("Disdetta effettuata con successo!");
        } catch (Exception e) {
            System.out.println("Errore durante la disdetta: " + e.getMessage());
        }

        // Stampa il numero di posti prenotati e disponibili
        System.out.println("Posti disponibili: " + concerto.getPostiDisponibili());
        System.out.println("Posti prenotati: " + concerto.getPostiPrenotati());
    }
}