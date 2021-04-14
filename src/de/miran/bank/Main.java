package de.miran.bank;

public class Main
{
    public static void main(String[] args)
    {
        Datei datei = new Datei();
        datei.ladeDatei("C:\\Users\\xcg5453\\Documents\\Java\\Bank\\KundenListe.txt");
        Bank bank = new Bank();
        bank.execute();
        bank.speichereAlleDatenInDatei();
    }

}
