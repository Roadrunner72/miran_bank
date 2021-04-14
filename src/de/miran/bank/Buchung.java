package de.miran.bank;

import java.time.LocalDate;

public class Buchung
{
    double barkonto = 0;
    LocalDate datum;
    Konto quellKonto;
    Konto zielKonto;
    double saldo;


    String verwendungszweck;
    double betrag;
    String delimiter = ";";
    boolean isNeuerBuchung = true;

    public String toString()
    {
        return quellKonto + delimiter + zielKonto + delimiter + verwendungszweck + delimiter + betrag + delimiter
            + saldo;
    }

    public double getBarkonto()
    {
        return barkonto;
    }
    public void setBarkonto(double barkonto)
    {
        this.barkonto = barkonto;
    }
    public LocalDate getDatum()
    {
        return datum;
    }
    public void setDatum(LocalDate datum)
    {
        this.datum = datum;
    }
    public Konto getQuellKonto()
    {
        return quellKonto;
    }
    public void setQuellKonto(Konto quellKonto)
    {
        this.quellKonto = quellKonto;
    }
    public Konto getZielKonto()
    {
        return zielKonto;
    }
    public void setZielKonto(Konto zielKonto)
    {
        this.zielKonto = zielKonto;
    }
    public double getSaldo()
    {
        return saldo;
    }
    public void setSaldo(double saldo)
    {
        this.saldo = saldo;
    }
    public String getVerwendungszweck()
    {
        return verwendungszweck;
    }
    public void setVerwendungszweck(String verwendungszweck)
    {
        this.verwendungszweck = verwendungszweck;
    }
    public double getBetrag()
    {
        return betrag;
    }
    public void setBetrag(double betrag)
    {

        this.betrag = betrag;
    }
    public boolean isNeuerBuchung()
    {
        return isNeuerBuchung;
    }
    public void setNeuerBuchung(boolean isNeuerBuchung)
    {
        this.isNeuerBuchung = isNeuerBuchung;
    }
    public LocalDate legeDatum()
    {
        datum = LocalDate.now();
        return datum;
    }
    public double barkontoErhoehen(Double betrag)
    {
        return barkonto += betrag;
    }
    public double barkontoAufNullSetzen(Double betrag)
    {
        return barkonto =  barkonto - betrag;
    }

    public double giroKontoErhoehen(Konto konto)
    {
        saldo = konto.saldo + barkonto;
        return saldo;
    }
    public double giroKontoReduzieren(Konto konto)
    {

        saldo = konto.saldo - barkonto;
        return saldo;
    }

    public double geldEinzahlen(Konto konto)
    {
        Eingabe eingabe = new Eingabe();
        double betrag = eingabe.pruefeEingabeUniversal("Betrag:", 10, 9999, "Error");
        barkontoErhoehen(betrag);
        System.out.println(barkonto);
        double saldo = giroKontoErhoehen(konto);
        barkontoAufNullSetzen(betrag);
        konto.setSaldo(saldo);
        System.out.println(barkonto);
        System.out.println(saldo);
        return saldo;
    }
    public Double geldAuszahlen(Konto konto)
    {
        boolean eingabeboolean = false;
        do
        {
            Eingabe eingabe = new Eingabe();
            double betrag = eingabe.pruefeEingabeUniversal("Bargeld abheben:", 10, 9999, "Error");
            saldo = konto.getSaldo();
            if (betrag > saldo)
            {
                System.err.println("Sie duerfen nicht auf Minus Kommen");
                //nur zum Testen return 0.0;
            }
            else
            {
                barkontoErhoehen(betrag);
                Double saldo = giroKontoReduzieren(konto);
                konto.setSaldo(saldo);
                eingabeboolean = true;
            }
        }
        while (eingabeboolean == false);
        System.out.println(saldo);
        return saldo;
    }
    public void geldUberweisen(Konto quellKonto, Konto zielKonto)
    {
        if (quellKonto != zielKonto)
        {
            Eingabe eingabe = new Eingabe();
            double betrag = eingabe.pruefeEingabeUniversal("Wie viel moechten Sie ueberweisen", 10, 9000,
                "Versuchen Sie nochmal mit mindestens Bertag von 10 und hoschsten Betrag von 9000");
            eingabe = new Eingabe();
            String verwendungszweck = eingabe.pruefeEingabeUniversal("Verwenudungszweck Eingeben", "\\S*",
                "Versuchen Sie nochmal");

            barkontoErhoehen(betrag);
            giroKontoReduzieren(quellKonto);
            giroKontoErhoehen(zielKonto);

            System.out.println("Sie haben " + betrag + "€ von dem Konto " + quellKonto + " erhalten"
                + " der Verwendungszweck lautet: " + verwendungszweck);
            System.out.println("Ihr aktueller Saldo ist: " + quellKonto.saldo + "€");
        }
        else
        {
            System.err.println("Quellkonto und Zielkonto durfen nicht gleich sein");
        }
    }
}
