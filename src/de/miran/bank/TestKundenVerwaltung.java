package de.miran.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.HashMap;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class TestKundenVerwaltung
{
    Eingabe eingabe = new Eingabe();
    Kunde kunde = new Kunde();
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    @Before
    public void setPrintStream()
    {
        System.setOut(new PrintStream(testOut));
    }
    public void setInputStream(String pruefeDas)
    {

        testIn = new ByteArrayInputStream(pruefeDas.getBytes());
        System.setIn(testIn);
    }
    //Test fure KundenName
    @Test
    public void testKundenNameNurBuchstaben()
    {
        String scannerEingabe = "nurBUchstaben";
        setInputStream(scannerEingabe);
        eingabe = new Eingabe();
        assertEquals(scannerEingabe, eingabe.EingabeUniversal("Vorname", "[a-z_A-Z]*"));

    }
    @Test
    public void testKundenMitZiffer()
    {
        String scannerEingabe = "123456";
        setInputStream(scannerEingabe);
        eingabe = new Eingabe();
        assertEquals("", eingabe.EingabeUniversal("Vorname", "[a-z_A-Z]*"));

    }
    @Test
    public void testKundenMitZifferUndBuchstaben()
    {

        String scannerEingabe = "Buchstabe1";
        setInputStream(scannerEingabe);
        eingabe = new Eingabe();
        assertEquals("", eingabe.EingabeUniversal("Vorname", "[a-z_A-Z]*"));

    }
    @Test
    public void testKundenMitLeerZeichen()
    {

        String scannerEingabe = "   ";
        setInputStream(scannerEingabe);
        eingabe = new Eingabe();
        assertEquals("", eingabe.EingabeUniversal("Vorname", "[a-z_A-Z]*"));

    }
    //Kundenart bestimmen
    @Test
    public void testbestimmeKundenArt()
    {

        String scannerEingabe = "2";
        setInputStream(scannerEingabe);
        assertEquals(KundenArt.PRIVATKUNDE, kunde.bestimmeKundenArt());
    }
    //Kundennummer bestimmen
    @Test
    public void testErzuegeKundenummerMitMinimumWert200UndMaximalWert999()
    {
        Kunde kunde = new Kunde();
        int kundenNummer = kunde.erzuegeNummer(200, 999);
        assertEquals(kundenNummer, kundenNummer);
    }
    @Test
    void erzuegeKundenNummerMaximalgroeserAlsUntergrenzeTest()
    {
        //maximal Eingabe und Minimum Eingabe 

        int unterGrenze = 100;
        int maximalZahl = 110;

        int kundenNummer = kunde.erzuegeNummer(unterGrenze, maximalZahl);
        kunde.setKundenNummer(kundenNummer);
        assertEquals(kunde.getKundenNummer(), kundenNummer);
    }
    @Test
    void erzuegeKundenNummerUntergrenzegroeserAlsMaximalTest()
    {
        //maximal Eingabe und Minimum Eingabe

        int unterGrenze = 200;
        int maximalZahl = 110;
        Kunde kundeTest = new Kunde();
        int kundenNummer = kunde.erzuegeNummer(unterGrenze, maximalZahl);
        kundeTest.setKundenNummer(kundenNummer);
        assertEquals(kundenNummer = 0, kundenNummer, "");

    }
    @Test
    void erzuegeKundenNummerUntergrenzeGleichgroesWieMaximalTest()
    {
        //maximal Eingabe und Minimum Eingabe

        int unterGrenze = 100;
        int maximalZahl = 100;
        Kunde kundeTest = new Kunde();
        int kundenNummer = kunde.erzuegeNummer(unterGrenze, maximalZahl);
        kundeTest.setKundenNummer(kundenNummer);
        assertEquals(kundenNummer = 0, kundenNummer);
    }

    //Geburtsdatum bestimmen
    @Test
    public void testErstelleGeburtsdatum()
    {
        String scannerEingabe = "12.12.1998";
        setInputStream(scannerEingabe);
        eingabe = new Eingabe();
        assertEquals(LocalDate.of(1998, 12, 12), eingabe.pruefeGeburtsdatEingabe(
            "Geben Sie Ihr Geburstag ein in diesem Format(22.12.1997):", "Falsche Eingabe, bitte nochmal versuchen"));
    }
    //Test Kunden Angaben aendern
    @Test
    public void testKundenNameAendernTest()
    {
        String scannerEingabe = "-";
        setInputStream(scannerEingabe);
        assertEquals(kunde.kundenNameAendern(), kunde.erstelleKundenName());
    }
    @Test
    public void testKundenArtAendern()
    {

        kunde.setKundenArt(KundenArt.PRIVATKUNDE);
        String scannerEingabe = "1";
        setInputStream(scannerEingabe);
        eingabe = new Eingabe();
        assertEquals(KundenArt.GESCHAEFTSKUNDE, kunde.kundenArtAendern());
    }
    //Test ob Kunde in KundenListe gespeichert 
    @Test
    public void testErmittleKundeNummer()
    {
        HashMap<Integer, Kunde> kundenListe = new HashMap<>();
        String scannerEingabe = "123";
        setInputStream(scannerEingabe);
        eingabe = new Eingabe();
        kunde.setKundenNummer(Integer.valueOf(scannerEingabe));
        kundenListe.put(Integer.valueOf(scannerEingabe), kunde);
        assertEquals(scannerEingabe, eingabe.pruefeEingabeUniversal("KundenNummer", "[0-9]*",
            "Kunden Nummer sollte nur nummierisch sein, Versuchen Sie nochmal"));
    }
}
