package de.miran.bank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ZuordnungKundeZuKonto
{

    //    public HashMap<Integer, Integer> zuordnungKundeZuKonto = new HashMap<>();
    //    public Hashtable<Integer, int[]> zuordnungKundeZuKonto = new Hashtable<Integer, int[]>();
    //    public HashMap<Integer, ArrayList<Integer>> zuordnungKundeZuKonto = new HashMap<Integer, ArrayList<Integer>>();
    //?
    //MultiKeyMap multiKeyMap = new MultiKeyMap();
    public Map<Integer, Collection<Integer>> zuordnungKundeZuKonto = new HashMap<Integer, Collection<Integer>>();
    /*
     * kundeZuEinemKontoZuordnen
     * int ,int
     */
    //    public void kundeZuEinemKontoZuordnen(int kundenNummer, int kontoNummer)
    //    {
    //        zuordnungKundeZuKonto.put(kundenNummer, kontoNummer);
    //    }
    //    public int holeKontenZuKunde(int kundenNummer)
    //    {
    //        int konten = zuordnungKundeZuKonto.get(kundenNummer);
    //        return konten;
    //
    //
    //    }


    /*
     * kundeZuEinemKontoZuordnen
     * int[]
     */
    //    public void kundeZuEinemKontoZuordnen(int kundenNummer, int[] kontoNummer)
    //    {
    //        zuordnungKundeZuKonto.put(kundenNummer, kontoNummer);
    //
    //    }
    //    public int[] holeKontenZuKunde(int kundenNummer)
    //    {
    //        int[] konten = zuordnungKundeZuKonto.get(kundenNummer);
    //        return konten;
    //    }


    /*
     * kundeZuEinemKontoZuordnen
     * ArrayList<Integer> 
     */
    //    public void kundeZuEinemKontoZuordnen(int kundenNummer, ArrayList<Integer> kontoNummer)
    //    {
    //
    //        zuordnungKundeZuKonto.put(kundenNummer, kontoNummer);
    //
    //    }
    //    public ArrayList<Integer> holeKontenZuKunde(int kundenNummer)
    //    {
    //        ArrayList<Integer> konten = zuordnungKundeZuKonto.get(kundenNummer);
    //        return konten;
    //    }
    public void zuordnungKundeZuKonto(Integer kundenNummer, Integer kontoNummer)
    {
        zuordnungKundeZuKonto.computeIfAbsent(kundenNummer, Integer -> new ArrayList<>()).add(kontoNummer);
    }
    public Collection<Integer> holeKonten(Integer kundenNummer)
    {

        return zuordnungKundeZuKonto.getOrDefault(kundenNummer, Collections.<Integer> emptyList());

    }
    public void kontoZuEinemKundeZuordnen()
    {

    }
    private void speichereKundeUndKonten(File file, HashMap<Integer, Kunde> kundenListe, HashMap<Integer, Konto> kontenListe)
    {

        try
        {
            PrintWriter pW = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            pW.println("[kundenNummer;kundenName;geburtstag;kundenArt");
            for (Kunde kunde : kundenListe.values())
            {
                if (kunde.isNeuerKunde())
                {
                    pW.println(kunde.toString());
                    pW.flush();
                    pW.println("kontoNummer;iban;saldo;kontoArt");
                    for (Konto konto : kontenListe.values())
                    {
                        if (konto.isNeuerKonto())
                        {
                            pW.println(konto.toString());
                            pW.flush();
                        }
                    }
                }
            }
            pW.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Keine Kunden wurden gespeichert");
        }
    }
    public void speichereKontenUndKunde(HashMap<Integer, Kunde> kundenListe, HashMap<Integer, Konto> kontenListe)
    {
        speichereKundeUndKonten(new File("C:\\Users\\xcg5453\\Documents\\Java\\Bank\\KontenUndKundenListe.txt"), kundenListe,
            kontenListe);
    }
}
