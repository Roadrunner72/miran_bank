package de.miran.bank;

import java.math.BigInteger;
import java.util.Random;

public class Konto
{
    String iban;
    double saldo, geldEinzahlen, geldAuszahlen;
    int kontoNummer;
    KontoArt kontoArt;
    private boolean neuerKonto = true;
    String delimiter = ";";

    public String toString()
    {
        return kontoNummer + delimiter + iban + delimiter + saldo + delimiter + kontoArt;
    }
    public Konto()
    {

    }
    public Konto(String iban, double saldo, int kontoNummer, KontoArt kontoArt, boolean neuerKonto)
    {
        this.iban = iban;
        this.saldo = saldo;
        this.kontoNummer = kontoNummer;
        this.kontoArt = kontoArt;
        this.neuerKonto = neuerKonto;
    }

    public Konto(String iban, double saldo, double geldEinzahlen, double geldAuszahlen)
    {
        this.iban = iban;
        this.saldo = saldo;
    }

    public String getIban()
    {
        return iban;
    }
    public void setIban(String iban)
    {
        this.iban = iban;
    }
    public double getSaldo()
    {
        return saldo;
    }
    public void setSaldo(double saldo)
    {
        this.saldo = saldo;
    }
    public double getGeldEinzahlen()
    {
        return geldEinzahlen;
    }
    public void setGeldEinzahlen(double geldEinzahlen)
    {
        this.geldEinzahlen = geldEinzahlen;
    }
    public double getGeldAuszahlen()
    {
        return geldAuszahlen;
    }
    public void setGeldAuszahlen(double geldAuszahlen)
    {
        this.geldAuszahlen = geldAuszahlen;
    }
    public int getKontoNummer()
    {
        return kontoNummer;
    }
    public void setKontoNummer(int kontoNummer)
    {
        this.kontoNummer = kontoNummer;
    }
    public Konto(int kontoNummer)
    {
        this.kontoNummer = kontoNummer;
    }
    public KontoArt getKontoArt()
    {
        return kontoArt;
    }

    public void setKontoArt(KontoArt kontoArt)
    {
        this.kontoArt = kontoArt;
    }

    public boolean isNeuerKonto()
    {
        return neuerKonto;
    }
    public void setNeuerKonto(boolean neuerKonto)
    {
        this.neuerKonto = neuerKonto;
    }
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
        neuenKontoAnlegen.erzuegeKontoNummer(0, 999);
        neuenKontoAnlegen.ibanErzeugen(bankLeitZahl, kontoNummer);
        neuenKontoAnlegen.bestimmeKontoArt();
        neuenKontoAnlegen.bestimmeKontostand();
        neuenKontoAnlegen.isNeuerKonto();
        return neuenKontoAnlegen;
    }
    public int erzuegeKontoNummer(int unterGrenze, int maximalZahl)
    {
        Random zufall = new Random();

        kontoNummer = zufall.nextInt(maximalZahl);
        if (kontoNummer == 0)
        {
            return 0;

        }
        while (kontoNummer < unterGrenze)
        {
            if (unterGrenze > maximalZahl || maximalZahl == unterGrenze)
            {
                System.err.println("Zufallzahl sollte gr��er als Untergrenze Zahl sein");
                kontoNummer = 0;
                break;
            }
            kontoNummer = zufall.nextInt(maximalZahl);
        }

        System.out.println("Ihre Kontonummer lautet:" + kontoNummer);
        return kontoNummer;
    }
    public String ibanErzeugen(String bankLeitZahl, String kontoNummer)
    {

        // zehnstellige Kontonummer   
        //        String zumTest = null;
        if (kontoNummer.length() < 10)
        {
            int differenz = 10 - kontoNummer.length();
            for (int i = 0; i < differenz; i++)
            {
                kontoNummer = "0" + kontoNummer;
            }
            //zum Test return null;
        }
        // Pruefziffer
        // die 1314 steht fuer DE und die 00 fuer die fehlenden Prueffziffern
        String checkIBAN = bankLeitZahl + kontoNummer + "131400";

        // String in eine Zahl konvertieren
        BigInteger checkIBANSum;
        try
        {
            checkIBANSum = new BigInteger(checkIBAN);
        }
        catch (Exception e)
        {
            // TODO:FEHLER RICHTIG behandeln!!!!
            return "Fehler";
        }
        //??????????????????????????????????????????????????????????????????????????????
        // Modulo rechnen 
        BigInteger faktor = new BigInteger("97");
        long div = checkIBANSum.remainder(faktor).longValue();
        // Differenz zu 98
        long pZiffer = 98 - div;

        // IBAN Regeln einhalten (22 Stellen)
        iban = "";
        if (pZiffer < 10)
        {
            iban = "DE0" + pZiffer + bankLeitZahl + kontoNummer;
        }
        else
        {
            iban = "DE" + pZiffer + bankLeitZahl + kontoNummer;
        }
        System.out.println("Ihre Iban lautet:" + "\n" + iban);
        return iban;

    }

    public KontoArt bestimmeKontoArt()
    {
        Eingabe eingabe = new Eingabe();
        String aussuchenWelchenKontoArt = eingabe.pruefeEingabeUniversal(
            "Moechten Sie ein 1-Girokonto oder 2-Sparkonto", "[1-2]{1}",
            "Waehlen Sie (1), wenn Sie Girokonto moechten \nWaehlen Sie (2) ,wenn Sie Sparkonto moechten ");;
        switch (aussuchenWelchenKontoArt)
        {
            case "1" :
                kontoArt = KontoArt.GIROKONTO;
                break;
            case "2" :
                kontoArt = KontoArt.SPARKONTO;
                break;
            default :
                System.err.println("Error");
                break;
        }
        System.out.println("Kontoart: " + kontoArt);
        return kontoArt;
    }
    public double bestimmeKontostand()
    {
        Eingabe eingabe = new Eingabe();
        saldo = eingabe.pruefeEingabeUniversal(
            "Ihre Saldobertag Eingeben(Einer Konto Eroeffnung ist nur M�glich bei einem MindestBetrag von 500 euro):",
            500, 9999, "Einer Konto Er�ffnung ist nur Moeglich bei einem MindestBetrag von 500 euro");
        System.out.println("Ihr aktueller Saldo betraegt:" + saldo);
        return saldo;
    }
    public double saldoReduzieren(double betrag)
    {
        if (saldo < betrag)
        {
            System.err.println("Guthaben ist nicht genug");
            return saldo;
        }
        return saldo - betrag;
    }
    public double saldoErhoehen(double betrag)
    {
        return betrag + saldo;
    }
    public double saldoErmitteln()
    {
        return saldo;
    }

}