package de.miran.bank;

import java.time.LocalDate;
import java.util.Random;

public class Kunde
{

    String kundenName;
    int kundenNummer;
    LocalDate geburtstag;
    private boolean neuerKunde = true;
    KundenArt kundenArt;
    String delimiter = ";";
    Eingabe eingabe = new Eingabe();
    public String toString()
    {
        return kundenNummer + delimiter + kundenName + delimiter + geburtstag + delimiter + kundenArt;
    }

    public Kunde()
    {

    }
    public Kunde(String kundenName, int kundenNummer, LocalDate geburtstag, KundenArt kundenart, boolean neuerKunde)
    {
        this.kundenName = kundenName;
        this.kundenNummer = kundenNummer;
        this.geburtstag = geburtstag;
        this.kundenArt = kundenart;
        this.neuerKunde = neuerKunde;
    }
    public Kunde(String kundenName)
    {
        this.kundenName = kundenName;
    }
    public String getKundenName()
    {
        return kundenName;
    }
    public void setKundenName(String kundenName)
    {
        this.kundenName = kundenName;
    }

    public int getKundenNummer()
    {
        return kundenNummer;
    }
    public void setKundenNummer(int kundenNummer)
    {
        this.kundenNummer = kundenNummer;
    }
    public LocalDate getGeburstag()
    {
        return geburtstag;
    }
    public void setGeburstag(LocalDate geburstag)
    {
        this.geburtstag = geburstag;
    }
    public boolean isNeuerKunde()
    {
        return neuerKunde;
    }
    public void setNeuerKunde(boolean neuerKunde)
    {
        this.neuerKunde = neuerKunde;
    }
    public KundenArt getKundenArt()
    {
        return kundenArt;
    }
    public void setKundenArt(KundenArt kundenArt)
    {
        this.kundenArt = kundenArt;
    }
    public static Kunde kundeAnlegen()
    {
        Kunde kundeAnlegen = new Kunde();
        kundeAnlegen.erzuegeNummer(200, 999);
        kundeAnlegen.erstelleKundenName();
        kundeAnlegen.erstelleGeburtsdatum();
        kundeAnlegen.bestimmeKundenArt();
        kundeAnlegen.isNeuerKunde();
        return kundeAnlegen;
    }

    public String erstelleKundenName()
    {
        eingabe = new Eingabe();
        kundenName = eingabe.pruefeEingabeUniversal("Geben Sie Ihre Name Ein:", "[a-z_A-Z]*",
            "Ihre Name sollte Nur aus Buchstaben bestehen");
        return kundenName;
    }
    public int erzuegeNummer(int unterGrenze, int maximalZahl)
    {

        Random zufall = new Random();

        kundenNummer = zufall.nextInt(maximalZahl);
        while (kundenNummer < unterGrenze)
        {
            if (unterGrenze > maximalZahl || maximalZahl == unterGrenze)
            {
                System.err.println("Zufallzahl sollte groessr als Untergrenze Zahl sein");
                kundenNummer = 0;
                break;
            }
            kundenNummer = zufall.nextInt(maximalZahl);
        }
        System.out.println("Ihre Kunden Nummer lautet:" + "\n" + kundenNummer);

        return kundenNummer;
    }
    public LocalDate erstelleGeburtsdatum()
    {
        eingabe = new Eingabe();
        geburtstag = eingabe.pruefeGeburtsdatEingabe("Geben Sie Ihr Geburstag ein in diesem Format(22.12.1997):",
            "Falsche Eingabe, bitte nochmal versuchen");
        return geburtstag;
    }
    public KundenArt bestimmeKundenArt()
    {
        Eingabe eingabe = new Eingabe();
        String aussuchen = eingabe.pruefeEingabeUniversal("Sind Sie 1-Geschaeftskunde oder 2-PrivatKunde", "[1-2]{1}",
            "W�hlen Sie (1) ,wenn Sie Gesch�ftskunde sind \nW�hlen Sie (2) ,wenn Sie PrivatKunde sind ");
        switch (aussuchen)
        {
            case "1" :
                kundenArt = KundenArt.GESCHAEFTSKUNDE;
                break;
            case "2" :
                kundenArt = KundenArt.PRIVATKUNDE;
                break;
            default :
                System.err.println("Error");
                break;
        }
        System.out.println("Ihre Kundenart ist: " + kundenArt);
        return kundenArt;
    }
    public String kundenNameAendern()
    {
        System.out.println("Ihre Kundenname lautet: " + kundenName);
        eingabe = new Eingabe();
        String eingabeAndern = eingabe.pruefeEingabeUniversal("Geben Sie(-),wenn Sie Ihre Name aendern wollen:", "\\D*",
            "Ihre Name sollte Nur aus Buchstaben bestehen");
        if (eingabeAndern.equals("-"))
        {
            erstelleKundenName();
        }
        return kundenName;
    }
    public KundenArt kundenArtAendern()
    {
        System.out.println("Ihre Kundenart lautet: " + kundenArt);
        eingabe = new Eingabe();
        String aussuchen = eingabe.pruefeEingabeUniversal(
            "Wenn Sie zu Geschaeftskunde aendern wollen Waehlen Sie: 1 \nWenn Sie zu PrivatKunde anndern wollen Waehlen Sie: 2",
            "[1-2]{1}", "Waehlen Sie (1) oder (2)");
        if (aussuchen.contains("1"))
        {
            kundenArt = KundenArt.GESCHAEFTSKUNDE;
        }
        else
        {
            kundenArt = KundenArt.PRIVATKUNDE;
        }
        return kundenArt;
    }
}
