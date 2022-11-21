package com.company;
import java.io.FileNotFoundException;
import java.util.Scanner;

class NoLandMan extends Gracz implements Opcje {

    public NoLandMan(String imie, String plec) {
        System.out.println("Tak wlasciwie to nie wiesz do konca kim jestes ani co tu robisz.");

        klasa = "czlowiek z nikad";
        setImie(imie);
        setPlec(plec);
        setZdrowie(10);
        setMana(10);
        setKondycja(40);
        setSila(4);
        setZrecznosc(5);
        setInteligencja(5);
        setSzczescie(5);
        atak = ((int)(Math.random() * (sila + zrecznosc + inteligencja)/3) + 1);
        obrona = ((int)(Math.random() * (sila + zrecznosc + inteligencja)/3) + 1);
    }

    @Override
    public String toString() {
        return "Czlowiek z Nikad:\n" +
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
    public static void wyborNazwyCZN() throws InterruptedException, FileNotFoundException {

        String tmpimie;
        String tmpplec;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wpisz imie postaci...\n");
        tmpimie = scanner.next();
        if (tmpimie.equals("") || tmpimie.length() > 15) {
            System.out.println("Bledne imie!");
            wyborNazwyCZN();
        } else
            System.out.println("""
                    Wybierz plec:
                    m - mezczyzna
                    f - kobieta
                    """);
        tmpplec = scanner.next();

        if (tmpplec.equals("m")|| tmpplec.equals("f")) {
            Gracz player = new NoLandMan(tmpimie, tmpplec);
            System.out.println(player + "\n");
            Tawerna tawerna = new Tawerna(player);
        }
        else
            wyborNazwyCZN();
    }
    public static int uderzenieZPiesci() {
        if (kondycja >= 20) {
            kondycja = kondycja - 15;
            return ((int)(Math.random() * (sila+inteligencja+zrecznosc)/3)+1) * ((int)(Math.random() * szczescie) + 1);
        } else System.out.println("Masz za malo kondycji!\n");
        return 0;
    }
}

