package de.miran.bank;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Before;


class TestKlasse
{

    Eingabe eingabeTest = new Eingabe();
    Konto konto = new Konto();
    Kunde kunde = new Kunde();
    KontoVerwaltung kontoVerwaltungTest = new KontoVerwaltung();
    InputStream systemIn = System.in;
    PrintStream systemOut = System.out;
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


}
