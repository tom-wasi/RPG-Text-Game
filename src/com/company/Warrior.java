package com.company;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Warrior extends Gracz {

    public Warrior(String imie, String plec) {


        klasa = "wojownik";
        setImie(imie);
        setPlec(plec);
        setZdrowie(120);
        setMana(30);
        setKondycja(100);
        setSila(9);
        setZrecznosc(5);
        setInteligencja(5);
        setSzczescie(6);
        atak = ((int)(Math.random() * sila) + 1) + ((int)(Math.random() * szczescie) + 1);
        obrona = ((int)(Math.random() * sila) + 1) + ((int)(Math.random() * szczescie) + 1);
    }

    @Override
    public String toString() {
        return "Wojownik:\n" +
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

    public static void wyborNazwyWojownika() throws IllegalArgumentException, FileNotFoundException, InterruptedException {

        String tmpimie;
        String tmpplec;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wpisz imie postaci...\n");
        tmpimie = scanner.next();

        if (tmpimie.isEmpty() || tmpimie.length() > 15) {
            System.out.println("Bledne imie!");
            wyborNazwyWojownika();
        } else
            System.out.println("""
                    Wybierz plec:
                    m - mezczyzna
                    f - kobieta
                    """);
        tmpplec = scanner.next();
        if (tmpplec.equals("m") || tmpplec.equals("f")) {
            Gracz player = new Warrior(tmpimie, tmpplec);
            System.out.println(player + "\n");
            Tawerna tawerna = new Tawerna(player);
        } else
            wyborNazwyWojownika();
    }
    public static int rabniecieToporem() {
        if (kondycja >= 15) {
            kondycja = kondycja - 15;
            return (int) (Math.random() * sila) * (int) (Math.random() * szczescie);
        } else System.out.println("Masz za malo kondycji!\n");
        return 0;
        }
    }
