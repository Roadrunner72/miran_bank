package de.miran.bank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Datei
{
    public void ladeDatei(String dateiName)
    {

        File file = new File(dateiName);

        if (!file.canRead() || !file.isFile())
            System.exit(0);
        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new FileReader(dateiName));
            String zeile = null;
            //            in.skip(48);

            while ((zeile = in.readLine()) != null)
            {
                String geleseneZeile = zeile.trim();

                System.out.println(geleseneZeile);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                }
        }
    }

    public void ladeDateiDurchScanner(String dateiName)
    {
        Scanner scan = null;
        try
        {
            scan = new Scanner(new File("test.txt"));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        while (scan.hasNext())
        {
            System.out.println(scan.nextLine());
        }
        scan.close();

    }
}