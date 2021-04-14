package de.miran.bank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


public class KontoVerwaltung
{
    HashMap<Integer, Konto> kontenListe = new HashMap<>();

    public Konto kontoAnlegen()
    {
        Konto neuenKontoAnlegen = new Konto();

        Eingabe eingabe = new Eingabe();
        String bankLeitZahl = eingabe.pruefeEingabeUniversal(
            "Geben Sie Ihre Bankleitzahl Ein:(die sollte aus 8 Ziffern bestehen)", "[0-9]{8}",
            "Versuchen Sie nochmal,die Bankleitzahl sollte nur aus 8 Ziffern bestehen");
        eingabe = new Eingabe();
        String kontoNummer = eingabe.pruefeEingabeUniversal(
            "Geben Sie Ihre KontoNummer Ein:(die sollte aus maximal 10 Ziffern bestehen)", "[0-9]{0,10}",
            "Versuchen Sie nochmal,die Kontonummer sollte nur aus maximal 10 Ziffern bestehen");
        neuenKontoAnlegen.ibanErzeugen(bankLeitZahl, kontoNummer);
        neuenKontoAnlegen.bestimmeKontoArt();
        neuenKontoAnlegen.bestimmeKontostand();
        neuenKontoAnlegen.isNeuerKonto();
        kontenListe.put(neuenKontoAnlegen.erzuegeKontoNummer(200, 400), neuenKontoAnlegen);
        return neuenKontoAnlegen;
    }
    public Konto kontenAnlegen()
    {
        Konto kontoAnlegen = new Konto();
        Konto konto = kontoAnlegen.kontoAnlegen();
        return kontenListe.put(konto.getKontoNummer(), konto);
    }

    public double geldEinzahlen(Konto konto)
    {
        Eingabe eingabe = new Eingabe();
        double betragEinzahlen = eingabe.pruefeEingabeUniversal("Bargeld einzahlen:", 0, 9999, "Bargeld einzahlen:");
        double saldo = konto.getSaldo();
        double betragnachEinzahlen = saldo += betragEinzahlen;
        System.out.println(betragnachEinzahlen);
        konto.setSaldo(betragnachEinzahlen);
        return betragnachEinzahlen;
    }
    public double geldAuszahlen(Konto konto)
    {
        Eingabe eingabe = new Eingabe();
        double betragnachAuszahlen = 0;
        double betragAusgabe;
        double saldo;
        boolean eingabeboolean = false;
        do
        {
            betragAusgabe = eingabe.pruefeEingabeUniversal("Bargeld abheben:", 0, 9999, "Bargeld abheben:");
            saldo = konto.getSaldo();
            if (betragAusgabe > saldo)
            {
                System.err.println("Sie duerfen nicht auf Minus Kommen");
            }
            else
            {
                betragnachAuszahlen = saldo -= betragAusgabe;
                eingabeboolean = true;
            }
        }
        while (eingabeboolean == false);
        System.out.println(betragnachAuszahlen);
        konto.setSaldo(betragnachAuszahlen);
        return betragnachAuszahlen;
    }


    public void geldUeberweisen(Konto quellKonto, double betrag, String verwendungszweck, Konto zielKonto)
    {

        if (quellKonto != zielKonto)
        {
            quellKonto.saldoReduzieren(betrag);
            zielKonto.saldoErhoehen(betrag);
            System.out.println("Sie haben " + betrag + " \u20ac von dem Konto " + quellKonto + " erhalten"
                + " der Verwendungszweck lautet: " + verwendungszweck);
            System.out.println("Ihr aktueller Saldo ist: " + quellKonto.saldo + "\u20ac");
        }
        else
        {
            System.err.println("Quellkonto und Zielkonto duerfen nicht gleich sein");
        }
    }

    //hier werden alle Konten über eine Hashmap gespeichert

    public Konto pruefeKontoNummerUndErmittleKonto(String anzeigeText)
    {
        Konto konto;
        do
        {
            Eingabe eingabe = new Eingabe();

            String kontoNummerLokal = eingabe.pruefeEingabeUniversal(anzeigeText, "[0-9]*",
                "Kontonummer sollte nur numerisch sein, Versuchen Sie nochmal!");
            konto = kontenListe.get(Integer.valueOf(kontoNummerLokal));
            if (konto == null)
            {
                System.err.println("Kontonummer wurde nicht gefunden,Versuchen Sie nochmal!!");
            }

        }
        while (konto == null);

        return konto;
    }
    public HashMap<Integer, Konto> getKontenListe()
    {
        return kontenListe;
    }
    public void setKontenListe(HashMap<Integer, Konto> kontenListe)
    {
        this.kontenListe = kontenListe;
    }
    public double saldoErmitteln(Konto konto)
    {
        return konto.saldoErmitteln();
    }

    public HashMap<Integer, Konto> speichereKonto(File file)

    {
        try
        {
            PrintWriter pW = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            pW.println("[kontoNummer;iban;saldo;kontoArt]");
            for (Konto konto : kontenListe.values())
            {
                if (konto.isNeuerKonto())
                {
                    pW.println(konto.toString());
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
        return kontenListe;

    }
    //hier ist eine wrapperMethode(die ist wie ein cover zu einen weiteren Methode)
    public HashMap<Integer, Konto> speichereAlleKonten()
    {
        return speichereKonto(new File("C:\\Users\\xcg5453\\Documents\\Java\\Bank\\KontenListe.txt"));

    }


}