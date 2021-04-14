package de.miran.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class TestKontoverwaltung
{
    Eingabe eingabe = new Eingabe();
    Konto konto = new Konto();
    KontoVerwaltung kontoVerwaltung = new KontoVerwaltung();

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
    @Test
    public void ibanErzeugen()
    {

        String iban = "DE58123456780123456789";
        assertEquals(iban, konto.ibanErzeugen("12345678", "123456789"));
    }
    @Test
    public void bestimmeKontoArt()
    {
        String pruefeDas = "1";
        setInputStream(pruefeDas);
        assertEquals(KontoArt.GIROKONTO, konto.bestimmeKontoArt());
    }
    @Test
    public void isNeuerKonto()
    {
        String pruefeDas = Boolean.toString(true);
        setInputStream(pruefeDas);
        boolean neuerKonto = konto.isNeuerKonto();
        neuerKonto = false;
        assertEquals(true, neuerKonto);
    }
    @Test
    public void KontonummerAusMaximal10ZeichenbestehenTest()
    {

        String scannerEingabe = "12345891";
        setInputStream(scannerEingabe);
        eingabe = new Eingabe();
        assertEquals(scannerEingabe, eingabe.EingabeUniversal(
            "Geben Sie Ihre KontoNummer Ein:(die sollte aus maximal 10 Zeichen bestehen)", "[0-9]{0,10}"));

    }

    @Test
    void KontoEroeffnungUeberpruefen()
    {
        String scannerEingabe = "523.0";
        setInputStream(scannerEingabe);
        eingabe = new Eingabe();
        assertEquals(Double.valueOf(scannerEingabe), eingabe.pruefeEingabeUniversal(
            "Ihre Saldobertag Eingeben(Einer Konto Eroeffnung ist nur M�glich bei einem MindestBetrag von 500 euro):",
            500, 9999, "Einer Konto Eroeffnung ist nur Moeglich bei einem MindestBetrag von 500 euro"));
    }

    @Test
    void IbanEingabeTest()
    {
        String scannerEingabe = "DE1234567891234567891234";
        setInputStream(scannerEingabe);
        eingabe = new Eingabe();
        assertEquals(scannerEingabe,
            eingabe.pruefeEingabeUniversal("IBAN", "[A-Za-z]{2}[0-9]{2}[0-9s]{11,30}", "error"));
    }
    @Test
    void bestimmeKontostand()
    {

        String scannerEingabe = "600";
        setInputStream(scannerEingabe);
        eingabe = new Eingabe();
        assertEquals(Double.valueOf(scannerEingabe), konto.bestimmeKontostand());
    }

    @Test
    void saldoErhoehen()
    {
        double betrag = 350;
        konto.setSaldo(400);
        assertEquals(750, konto.saldoErhoehen(betrag));
    }

    @Test
    void saldoReduzieren()
    {
        double betrag = 200;
        konto.setSaldo(900);
        assertEquals(700, konto.saldoReduzieren(betrag));
    }
    @Test
    void saldoErmitteln()
    {
        konto.setSaldo(900);
        assertEquals(900, kontoVerwaltung.saldoErmitteln(konto));
    }
    @Test
    void GeldAuzzahlenWenigerAlsSaldo()
    {
        double betrag = 900;
        konto.setSaldo(500);
        assertEquals(konto.getSaldo(), konto.saldoReduzieren(betrag));
    }
    @Test
    void GeldAuzzahlen()
    {
        String scannerEingabe = "600";
        setInputStream(scannerEingabe);
        konto.setSaldo(600);
        eingabe = new Eingabe();
        assertEquals(0, kontoVerwaltung.geldAuszahlen(konto));
    }
    @Test
    void GeldEinzahlen()
    {
        String scannerEingabe = "600";
        setInputStream(scannerEingabe);
        konto.setSaldo(600);
        eingabe = new Eingabe();
        assertEquals(1200, kontoVerwaltung.geldEinzahlen(konto));
    }
    @Test
    void geldUeberweisen()
    {

    }
    @Test
    void PruefeKontoNummerUndErmittleKonto()
    {
        String pruefeDas = "123";
        setInputStream(pruefeDas);
        HashMap<Integer, Konto> kontenListe = new HashMap<>();
        konto.setKontoNummer(123);
        kontoVerwaltung.setKontenListe(kontenListe);
        kontenListe.put(Integer.valueOf(pruefeDas), konto);
        assertEquals(konto, kontoVerwaltung.pruefeKontoNummerUndErmittleKonto("Konto"));
    }

}
