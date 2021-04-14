package de.miran.bank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


public class KundenVerwaltung
{
    HashMap<Integer, Kunde> kundenListe = new HashMap<>();
    public Kunde kundenAnlegen()
    {
//        Kunde kundeAnlegen = new Kunde();
        Kunde kunde = Kunde.kundeAnlegen(); //TODO static
        return kundenListe.put(kunde.getKundenNummer(), kunde);
    }

    //hier werden Kunden in einer Text Datei gespeichert über eine Hashmap
    public Kunde pruefeKundenNummerUndErmittleKunde(String anzeigeText)
    {
        Kunde kunde = null; // TODO Vorsicht! Nicht unbedingt NULL
        do
        {
            Eingabe eingabe = new Eingabe();
            String kundenNummerLockal = eingabe.pruefeEingabeUniversal(anzeigeText, "[0-9]*",
                "Kunden Nummer sollte nur nummierisch sein, Versuchen Sie nochmal");
            kunde = kundenListe.get(Integer.valueOf(kundenNummerLockal));

        }
        while (kunde == null);
        return kunde;
    }
    public void aendereKundenAngaben()
    {
        Eingabe eingabe = new Eingabe();
        String aussuchen = eingabe.pruefeEingabeUniversal(
            "Waehlen Sie aus:" + "\n" + "1-Kundenname aendern" + "\n" + "2-Kundenart aendern" + "\n" + "3-back",
            "[1-2-3]{0,2}", "Waehlen Sie  eine Option von 1 bis 3 aus:");
        switch (aussuchen)
        {
            case "1" :
                Kunde kunde = pruefeKundenNummerUndErmittleKunde("Geben Sie Ihre Kundenummer ein:"); // TODO Aufruf doppelt. Sinnvoll?
                kunde.kundenNameAendern();
                break;
            case "2" :
                kunde = pruefeKundenNummerUndErmittleKunde("Geben Sie Ihre Kundenummer ein:");
                kunde.kundenArtAendern();
            case "3" :
                break;
            default :
                break;
        }
    }
    // TODO ReturnValue korrekt?
    private HashMap<Integer, Kunde> kundenSpeichern(File file)
    {
        try
        {
            PrintWriter pW = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));

            //values() Methode liefert ein Set(alle Objekte ,die bei dem Kunde sind) von Values bzw Kunden
            pW.println("[kundenNummer;kundenName;geburtstag;kundenArt]");
            for (Kunde kundeSchreiben : kundenListe.values())
            {
                if (kundeSchreiben.isNeuerKunde())
                {
                    pW.println(kundeSchreiben.toString());
                    // durch flush Methode werden die Datei sofo rt auf die Festplatte direkt gespeichert 
                    pW.flush();
                }
            }
            pW.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Keine Kunden wurden gespeichert");
        }
        return kundenListe;
    }
    //hier ist eine wrapperMethode(die ist wie ein cover zu einen weiteren Methode)
    public HashMap<Integer, Kunde> speichereAlleKunden()
    {

        return kundenSpeichern(new File("C:\\Users\\xcg5453\\Documents\\Java\\Bank\\KundenListe.txt"));

    }
}
