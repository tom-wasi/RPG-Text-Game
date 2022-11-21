package com.company;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mage extends Gracz implements Opcje {

    public Mage(String imie, String plec) {

        klasa = "mag";
        setImie(imie);
        setPlec(plec);
        setZdrowie(80);
        setMana(90);
        setKondycja(60);
        setSila(4);
        setZrecznosc(6);
        setInteligencja(9);
        setSzczescie(7);
        atak = ((int)(Math.random() * inteligencja) + 1) + ((int)(Math.random() * szczescie) + 1);
        obrona = ((int)(Math.random() * inteligencja) + 1) + ((int)(Math.random() * szczescie) + 1);
    }

    @Override
    public String toString() {
        return "Mag:\n" +
                "Imie - " + getImie() + '\n' +
                "Plec - " + getPlec() + '\n' +
                "Zdrowie - " + getZdrowie() + '\n' +
                "Mana - " + getMana() + '\n' +
                "Kondycja - " + getKondycja() + '\n' +
                "Epa - " + getSila() + '\n' +
                "Zrecznosc - " + getZrecznosc() + '\n' +
                "Inteligencja - " + getInteligencja() + '\n' +
                "Szczescie - " + getSzczescie();
    }

    public static void wyborNazwyMaga() throws InterruptedException, FileNotFoundException {

        String tmpimie;
        String tmpplec;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wpisz imie postaci...\n");
        tmpimie = scanner.nextLine();
        if (tmpimie.isEmpty() || tmpimie.length() > 12) {
            System.out.println("Bledne imie!");
            wyborNazwyMaga();
        } else
            System.out.println("""
                    Wybierz plec:
                    m - mezczyzna
                    f - kobieta
                    """);
        tmpplec = scanner.nextLine();

        if (tmpplec.equals("m") || tmpplec.equals("f")) {
            Gracz player = new Mage(tmpimie, tmpplec);
            System.out.println(player + "\n");
            Tawerna tawerna = new Tawerna(player);
        } else
            wyborNazwyMaga();
    }

    public static int kulaOgnia() {
        if (mana >= 20) {
            mana = mana - 20;
            return ((int)(Math.random() * inteligencja) + 1) * (int)(Math.random() * szczescie);
        } else System.out.println("Masz za malo many!\n");
        return 0;
    }

    public static void kojacyDotyk() {
        if (mana > 20) {
            mana = mana - 20;
            zdrowie = zdrowie + 25;
        } else System.out.println("Masz za malo many!\n");
    }
}


