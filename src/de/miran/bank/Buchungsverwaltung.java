package de.miran.bank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;

public class Buchungsverwaltung
{
    HashMap<LocalDate, Buchung> buchungsListe = new HashMap<LocalDate, Buchung>();
    public Buchung geldUeberweisen(Konto quellKonto, Konto zielKonto)
    {
        Buchung buchungAnlegen = new Buchung();
        buchungAnlegen.geldUberweisen(quellKonto, zielKonto);
        LocalDate datum = buchungAnlegen.legeDatum();
        return buchungsListe.put(datum, buchungAnlegen);
    }
    //hier werden alle Buchungen über eine hashmap gespeichert


    private HashMap<LocalDate, Buchung> speichereBuchung(File file)
    {

        {
            try
            {
                PrintWriter pW = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));

                for (Buchung buchung : buchungsListe.values())
                {
                    if (buchung.isNeuerBuchung())
                    {
                        pW.println(buchung.getDatum());
                        pW.println(buchung.toString());
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
            return buchungsListe;

        }
    }
    public HashMap<LocalDate, Buchung> speichereBuchungen()
    {
        return speichereBuchung(new File("C:\\Users\\xcg5453\\Documents\\Java\\Bank\\BuchungsListe.txt"));

    }
}
