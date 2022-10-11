package com.soprasteria;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Agenzia {
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Scegli: 1 per prenotare una nuova vacanza");
        System.out.println("Scegli 2 per uscire");
        String paginaPrenotazione = scan.nextLine();

        switch (paginaPrenotazione) {
        
	        case "2":
	            System.out.println("Grazie e alla prossima!");
	            break;
            
            case "1":
                System.out.println("Inserisci destinazione: ");
                String destinazione = scan.nextLine();
                System.out.println("Inserisci data di partenza: (dd/mm/yyyy) ");
                String dataInizio = scan.nextLine();
                System.out.println("Inserisci data di ritorno:  (dd/mm/yyyy) ");
                String dataFine = scan.nextLine();

                try {
                    Vacanza vacanza1 =   new Vacanza(destinazione, dataInizio , dataFine);
                    System.out.println(vacanza1);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (DateTimeParseException e) {
                    System.out.println("Le date non sono state inserite correttamente");
                    
                } finally {
                	 scan.close();
                }
   
        }
       
    }
}