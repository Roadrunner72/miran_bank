package de.miran.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class TestBuchung
{
    Eingabe eingabe = new Eingabe();
    Buchung buchung = new Buchung();
    Konto konto = new Konto();
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
    public void TestgeldEinzahlen()
    {
        String pruefeDas = "200";
        setInputStream(pruefeDas);
        konto.setSaldo(500);
        eingabe = new Eingabe();
        assertEquals(700, buchung.geldEinzahlen(konto));
    }
    @Test
    public void TestgeldAuszahlen()
    {
        String pruefeDas = "200";
        setInputStream(pruefeDas);
        konto.setSaldo(100);
        eingabe = new Eingabe();
        assertEquals(0.0, buchung.geldAuszahlen(konto));
    }

}
