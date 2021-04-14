package de.miran.bank;

import java.util.HashMap;

public class Bank
{
    KundenVerwaltung kundeVerwaltung;//nur variablen deklaration ,kundeVerwalten ist noch leer
    KontoVerwaltung kontoVerwaltung;
    ZuordnungKundeZuKonto zuordnungKundeZuKonto;
    Buchungsverwaltung buchungsverwaltung;
    Buchung buchung;
    Bank()//alle deklarierten Variablen werden bei erzeugen der Klasse mit Leben gefaellt
    {
        kundeVerwaltung = new KundenVerwaltung();//da wird der Speicher fuer kundenVerwaltung instanziiert 
        kontoVerwaltung = new KontoVerwaltung();
        zuordnungKundeZuKonto = new ZuordnungKundeZuKonto();
        buchungsverwaltung = new Buchungsverwaltung();
        buchung = new Buchung();
    }
    private String menueAuswahl()
    {
        System.out.println("1-Ende" + "\n" + "2-Kunde anlegen" + "\n" + "3-Konto anlegen" + "\n"
            + "4-Konto einem Kunden zuordnen" + "\n" + "5-Geld einzahlen " + "\n" + "6-Geld auszahlen" + "\n"
            + "7-Ueberweisen" + "\n" + "8-Kontostand pruefen" + "\n" + "9-Monatsabrechnung" + "\n"
            + "10-Speichern (Kunden, Konten, Buchungen)" + "\n" + "11-Kunden informationen aendern");
        Eingabe eingabe = new Eingabe();
        String Eingabe = eingabe.pruefeEingabeUniversal("Waehlen Sie aus:", "[1-2-3-4-5-6-7-9-10-11]{0,2}",
            "Waehlen Sie  eine Option von 0 bis 12 aus:");
        return Eingabe;
    }
    public void execute()
    {
        boolean endeProgrammAblauf = false;

        do
        {

            String auswahl = menueAuswahl();
            switch (auswahl)
            {
                case "1" :
                    endeProgrammAblauf = true;
                    break;
                case "2" :
                    kundeVerwaltung.kundenAnlegen();
                    break;
                case "3" :
                    kontoVerwaltung.kontenAnlegen();
                    break;
                case "4" :
                	// TODO würde ich tatsächlich eine eigene Methode machen, da recht kompakt
                    zuordnungKundeZuKonto.zuordnungKundeZuKonto(
                        kundeVerwaltung.pruefeKundenNummerUndErmittleKunde(
                            "Geben Sie Ihre Kundenummer ein:").getKundenNummer(),
                        kontoVerwaltung.pruefeKontoNummerUndErmittleKonto(
                            "Geben Sie Ihre Kontonummer ein:").getKontoNummer());
                    break;
                case "5" :
                    buchung.geldEinzahlen(
                        kontoVerwaltung.pruefeKontoNummerUndErmittleKonto("Geben Sie Ihre Kontonummer ein:"));
                    //            kontoVerwaltung.geldEinzahlen(kontoVerwaltung.ermittleKonto("Geben Sie Ihre Kontonummer ein:"));
                    break;
                case "6" :
                    buchung.geldAuszahlen(
                        kontoVerwaltung.pruefeKontoNummerUndErmittleKonto("Geben Sie Ihre Kontonummer ein:"));
                    // kontoVerwaltung.geldAuszahlen(kontoVerwaltung.ermittleKonto("Geben Sie Ihre Kontonummer ein:"));
                    break;
                case "7" :

                    Konto quellKonto = kontoVerwaltung.pruefeKontoNummerUndErmittleKonto(
                        "Geben Sie Ihre quell Kontonummer ein:");
                    Konto zielKonto = kontoVerwaltung.pruefeKontoNummerUndErmittleKonto(
                        "Geben Sie die ziel Kontonummer ein:");
                    buchungsverwaltung.geldUeberweisen(quellKonto, zielKonto);
                    break;
                case "8" :
                    kontoVerwaltung.saldoErmitteln(
                        kontoVerwaltung.pruefeKontoNummerUndErmittleKonto("Geben Sie Ihre Kontonummer ein:"));
                    break;
                case "9" :
                    break;
                case "10" :
                    speichereAlleDatenInDatei();
                    break;
                case "11" :
                    kundeVerwaltung.aendereKundenAngaben();
                    break;
                default :
                    endeProgrammAblauf = false;
                    break;
            }
        }
        while (endeProgrammAblauf == false);
    }


    //hiere  ist eine allgemeine Speicher Methode
    public void speichereAlleDatenInDatei()
    {
        HashMap<Integer, Kunde> kundenListe = kundeVerwaltung.speichereAlleKunden();
        HashMap<Integer, Konto> kontenListe = kontoVerwaltung.speichereAlleKonten();
        zuordnungKundeZuKonto.speichereKontenUndKunde(kundenListe, kontenListe);
        buchungsverwaltung.speichereBuchungen();
    }
}
