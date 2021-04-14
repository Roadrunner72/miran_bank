package de.miran.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ZuordnungKundeZuKontoTest
{
    ZuordnungKundeZuKonto kundenUndKontoBestand = new ZuordnungKundeZuKonto();

    //    @Test
    //    void holeEinKontonummerMitHashmapTest()
    //    {
    //        kundenUndKontoBestand.kundeZuEinemKontoZuordnen(5, 19);
    //        assertEquals(19, kundenUndKontoBestand.holeKontenZuKunde(5));
    //    }
    //    @Test
    //    void holeMehrereKontenNummerMitHashmapTest()
    //    {
    //        kundenUndKontoBestand.kundeZuEinemKontoZuordnen(5, 19);
    //        kundenUndKontoBestand.kundeZuEinemKontoZuordnen(5, 18);
    //        kundenUndKontoBestand.kundeZuEinemKontoZuordnen(5, 14);
    //        assertEquals(14, kundenUndKontoBestand.holeKontenZuKunde(5));
    //    }
    //    @Test
    //    void holeMehrereKontenNummerMitHashtableTest()
    //    {
    //        int[] kontoNummer = {1, 2, 3, 4, 5};
    //        int kundenNummer = 1;
    //        kundenUndKontoBestand.kundeZuEinemKontoZuordnen(kundenNummer, kontoNummer);
    //        int[] expected = {1, 2, 3, 4, 5};
    //        assertEquals(expected, kundenUndKontoBestand.holeKontenZuKunde(kundenNummer));
    //    }
    //    @Test
    //    void holeMehrereKontenNummerMitArrayListTest()
    //    {
    //        int kundenNummer = 1;
    //        
    //        ArrayList<Integer> konten = new ArrayList<>();
    //        konten.add(1);
    //        konten.add(2);
    //        konten.add(3);
    //
    //        kundenUndKontoBestand.kundeZuEinemKontoZuordnen(kundenNummer, konten);
    //        //        ArrayList<Integer> expected = konten.toString();
    //        assertEquals(konten, kundenUndKontoBestand.holeKontenZuKunde(kundenNummer));
    //    }
    @Test
    void holeMehrereKontenNummerMitCollationKeyTest()
    {
        Integer kundenNummer = 1;

        Integer kontoNummer = 2;
        kundenUndKontoBestand.zuordnungKundeZuKonto(kundenNummer, kontoNummer);
        kontoNummer = 3;
        kundenUndKontoBestand.zuordnungKundeZuKonto(kundenNummer, kontoNummer);
        kontoNummer = 4;
        kundenUndKontoBestand.zuordnungKundeZuKonto(kundenNummer, kontoNummer);
        kontoNummer = 5;
        kundenUndKontoBestand.zuordnungKundeZuKonto(kundenNummer, kontoNummer);
        java.util.List<Integer> list = Arrays.asList(2, 3, 4, 5);
        assertEquals(list, kundenUndKontoBestand.holeKonten(kundenNummer));
    }


}
