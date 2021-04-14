package de.miran.bank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Eingabe
{
    Scanner scanner = new Scanner(System.in);
    /**
    * Die Methode ist nur zum Variabeln von type String geeignet
    * ueberprueft die Bedingung ob es richtig oder falsch ist
    * hier sind regulaere Ausdruecke zu beachten  
    */
    public String EingabeUniversal(String benutzerAnzeigeText, String bedingung)
    {
        System.out.println(benutzerAnzeigeText);
        String benutzerEingabe = scanner.nextLine();
        if (!Pattern.matches(bedingung, benutzerEingabe))
        {
            benutzerEingabe = "";
        }


        return benutzerEingabe;

    }
    /**
     * Die Methode ist nur zum Variabeln von type String geeignet
     *  wiederholMethode solange bis eine richtige Eingabe
     */
    public String pruefeEingabeUniversal(String benutzerAnzeigeText, String bedingung, String fehlertext)
    {
        String sEingabe = null;
        do
        {

            sEingabe = EingabeUniversal(benutzerAnzeigeText, bedingung);
            if (sEingabe.isEmpty())
            {
                System.err.println(fehlertext);

            }


        }
        while (sEingabe.isEmpty());

        return sEingabe;


    }

    /**
     * Die Methode ist nur zum Variabeln von type LocalDate geeignet(Geburstdatum in diesem Fall)
     * minimum Alter ist 18 Jahre
     * maximal Alter ist 100 minus Aktuelle Datum  
     */
    public LocalDate geburtsdatEingabemitPruefung(String benutzerAnzeigeText)
    {
        LocalDate geburstdatumEingabe;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");

        System.out.println(benutzerAnzeigeText);
        try
        {
            geburstdatumEingabe = LocalDate.parse(scanner.next(), formatter);


            LocalDate maximalDatum = LocalDate.now().minusYears(18);
            LocalDate minimalDatum = LocalDate.now().minusYears(100);

            if (geburstdatumEingabe.isAfter(maximalDatum))
            {
                System.err.println(
                    "Sie dürfen dieses Alter " + formatter.format(maximalDatum) + " nicht überschreiten");
                geburstdatumEingabe = null;

            }
            else if (geburstdatumEingabe.isBefore(minimalDatum))
            {
                System.err.println(formatter.format(minimalDatum) + "\n" + "Sie können noch kein Konto eröffnen");
                geburstdatumEingabe = null;

            }
        }

        catch (Exception e)
        {
            System.err.println("Falsche Eingabe:(Das Datum in den richtigen Format eingeben)");
            geburstdatumEingabe = null;
        }

        return geburstdatumEingabe;
    }
    /**
     * Die Methode ist nur zum Variabeln von type LocalDate geeignet
     * wiederholMethode solange bis eine richtige Eingabe
     */
    public LocalDate pruefeGeburtsdatEingabe(String benutzerAnzeigeText, String fehlerText)
    {
        LocalDate geburstdatum = null;

        do
        {
            geburstdatum = geburtsdatEingabemitPruefung(benutzerAnzeigeText);

        }
        while (geburstdatum == null);

        return geburstdatum;

    }

    public double pruefeEingabeUniversal(String benutzerAnzeigeText, double mindestBetrag, double hoechstBetrag,
        String fehlerText)
    {
        double benutzerEingabe = 0;
        do
        {
            System.out.println(benutzerAnzeigeText);
            try
            {
                benutzerEingabe = Double.parseDouble(scanner.next());
                if (benutzerEingabe < mindestBetrag || benutzerEingabe > hoechstBetrag)
                {
                    System.err.println(fehlerText);
                }

            }
            catch (NumberFormatException nfe)

            {
                System.err.println("Keine Buchstaben!");
            }

        }
        while (benutzerEingabe < mindestBetrag || benutzerEingabe > hoechstBetrag);

        return benutzerEingabe;
    }
}