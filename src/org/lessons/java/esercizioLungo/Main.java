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

        boolean oraValida = false;
        LocalTime ora = null;
        while (!oraValida) {
            System.out.print("Inserisci l'ora dell'evento (formato HH:mm): ");
            try {
                LocalTime oraInserita = LocalTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("HH:mm"));
                ora = oraInserita;
                oraValida = true;

            } catch (DateTimeParseException e) {
                System.out.println("Formato ora non valido. Inserisci l'ora nel formato HH:mm.");
            }
        }

        //System.out.print("Inserisci l'ora del concerto (formato HH:mm): ");
        //LocalTime ora = LocalTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("HH:mm"));
        //System.out.print("Inserisci il prezzo del biglietto: ");
        //BigDecimal prezzo = scanner.nextBigDecimal();
        //System.out.print("Inserisci il prezzo del biglietto: ");
        BigDecimal prezzo = null;
        boolean prezzoValido = false;
        while (!prezzoValido) {
            System.out.print("Inserisci il prezzo del biglietto: ");
            try {
                prezzo = scanner.nextBigDecimal();
                prezzoValido = true;
            } catch (Exception e) {
                System.out.println("Errore: devi inserire un numero decimale valido come prezzo.");
                scanner.nextLine();
            }
        }


        int capienza = 0;
        boolean capienzaValida = false;
        while (!capienzaValida) {
            System.out.print("Inserisci la capienza della location: ");
            try {
                capienza = scanner.nextInt();
                capienzaValida = true;
            } catch (Exception e) {
                System.out.println("Errore: devi inserire un numero intero.");
            }
        }

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
        //System.out.print("Quanti posti vuoi prenotare? ");

        while (true) {
            System.out.println("(p) per Prenotare posti, (d) per Disdire prenotazioni, (e) per uscire");
            scanner.nextLine();
            String choice = scanner.nextLine();
            if (choice.equals("e")) {
                System.out.println("Grazie e arrivederci!!");
                break;
            }

            switch (choice) {
                case "p" -> {
                    int postiDaPrenotare = 0;
                    boolean postiValidi = false;
                    while (!postiValidi) {
                        System.out.print("Quanti posti vuoi prenotare? ");
                        try {
                            postiDaPrenotare = scanner.nextInt();
                            if (postiDaPrenotare <= concerto.getPostiDisponibili() && postiDaPrenotare >= 0) {
                                postiValidi = true;
                            } else {
                                System.out.println("il numero di posti da prenotare è maggiore o minore dei posti disponibili.");
                            }
                        } catch (Exception e) {
                            System.out.println("devi inserire un numero intero valido come numero di posti.");
                            scanner.nextLine(); // Consuma l'input non valido dal buffer di input

                        }

                    }
                    try {
                        concerto.prenota(postiDaPrenotare);
                        System.out.println("Prenotazione effettuata con successo!");
                    } catch (Exception e) {
                        System.out.println("Errore durante la prenotazione: " + e.getMessage());
                    }
                    System.out.println("Posti prenotati: " + concerto.getPostiPrenotati());
                    System.out.println("Posti disponibili: " + concerto.getPostiDisponibili());
                }
                case "d" -> {
                    System.out.print("Quanti posti vuoi disdire? ");
                    int postiDaDisdire = scanner.nextInt();
                    try {
                        concerto.disdici(postiDaDisdire);
                        System.out.println("Disdetta effettuata con successo!");
                    } catch (Exception e) {
                        System.out.println("Errore durante la disdetta: " + e.getMessage());
                    }
                    System.out.println("Posti prenotati: " + concerto.getPostiPrenotati());
                    System.out.println("Posti disponibili: " + concerto.getPostiDisponibili());
                }
                default -> System.out.println("opzione non disponibile");
            }
        }

    }
}