package com.soprasteria;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Vacanza {
	/*
	 * 
Nel progetto java-travel-agency, creare la classe Vacanza caratterizzata da:
- destinazione
- data inizio
- data fine
Quando viene creata una nuova Vacanza vanno effettuati dei controlli:
- destinazione, data inizio e data fine non possono essere null
- la data inizio non può essere già passata
- la data fine non può essere prima della data inizio
Se fallisce la validazione vanno sollevate opportune eccezioni
La classe Vacanza espone un metodo per calcolare la durata in giorni della vacanza.

Aggiungere una classe Agenzia con metodo main, dove chiediamo all’operatore se vuole 
inserire una nuova vacanza o uscire. Se vuole proseguire con l’inserimento 
va chiesta la destinazione, il giorno, mese e anno di partenza e 
il giorno, mese anno di ritorno. Con questi dati va generata una 
nuova vacanza e in console verrà stampato un messaggio del tipo: 
“Hai prenotato una vacanza di [numero di giorni] giorni a 
[destinazione] dal [data inizio formattata] al [data fine formattata]”. 
Se la creazione della vacanza genera un errore, il programma non deve 
interrompersi ma va gestito con dei messaggi di errore che permettono 
all’utente di capire cosa è andato storto e di ripetere l’inserimento.
	 * 
	 * */
	
    private String destinazione;
    private String dataInizio;
    private String dataFine;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate inizio;
    private LocalDate fine;
	
	//costruttori
    public Vacanza(String destinazione, String dataInizio, String dataFine)
            throws IllegalArgumentException,DateTimeParseException {
        validDest(destinazione);
        validInizio(dataInizio);
        validFine(dataFine , dataInizio);
        this.destinazione = destinazione;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.inizio = LocalDate.parse(dataInizio, format);
        this.fine = LocalDate.parse(dataFine, format);
    }

    public Vacanza(Vacanza vacanza) {
        this.destinazione = vacanza.getDestinazione();
        this.dataInizio = vacanza.dataInizio;
        this.dataFine = vacanza.dataFine;
    }

    //getter e setter

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) throws IllegalArgumentException {
        validDest(destinazione);
        this.destinazione = destinazione;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) throws IllegalArgumentException, DateTimeParseException {
        validInizio(dataInizio);
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public void setDataFine(String dataFine) throws IllegalArgumentException,DateTimeParseException {
        validFine(dataFine,dataInizio);
        this.dataFine = dataFine;
    }

    
    
    
    private void validInizio (String dataInizio)
            throws IllegalArgumentException, DateTimeParseException {
        LocalDate today = LocalDate.now();
        
        if (dataInizio==null)
            throw new IllegalArgumentException("Il campo data inizio non può essere vuoto");
        if (LocalDate.parse(dataInizio, format).isBefore(today))
            throw new IllegalArgumentException("La data di partenza non può essere nel passato");
    }
    
    
    private void validFine (String dataFine , String dataInizio)
            throws IllegalArgumentException,DateTimeParseException {
        if (dataFine==null)
            throw new IllegalArgumentException("Il campo data fine non può essere vuoto");
        if (LocalDate.parse(dataFine, format).isBefore(LocalDate.parse(dataInizio, format)))
            throw new IllegalArgumentException("La data di ritorno non può essere prima di quella di partenza");
    }
    
    private void validDest (String destinazione) throws IllegalArgumentException {
        if (destinazione.equals("")) 
            throw new IllegalArgumentException("Il campo destinazione non può essere vuoto");
    }

    
    
    public String durata(String dataInizio, String dataFine) {
        Period period = Period.between(inizio, fine);
        return period.getDays()+" giorni.";
    }
    
   
    
    @Override
    public String toString() {
        return "Hai prenotato una vacanza a "+destinazione+" con partenza il "+dataInizio+" e ritorno il "+dataFine+". Durata: "+durata(dataInizio , dataFine);
    }
}
	
