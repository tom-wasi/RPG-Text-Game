package com.company;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Rogue extends Gracz implements Opcje {

    public Rogue(String imie, String plec) {

        klasa = "lowca";
        setImie(imie);
        setPlec(plec);
        setZdrowie(90);
        setMana(50);
        setKondycja(100);
        setSila(6);
        setZrecznosc(9);
        setInteligencja(7);
        setSzczescie(7);
        atak = ((int)(Math.random() * zrecznosc) + 1) + ((int)(Math.random() * szczescie) + 1);
        obrona = ((int)(Math.random() * zrecznosc) + 1);
    }
    @Override
    public String toString() {
        return "Lotrzyk:\n" +
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
    public static void wyborNazwyLowcy() throws InterruptedException, FileNotFoundException {

        String tmpimie;
        String tmpplec;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz imie postaci...\n");
        tmpimie = scanner.next();
        if (tmpimie.equals("") || tmpimie.length() > 12) {
            System.out.println("Bledne imie!");
            wyborNazwyLowcy();
        } else {
            System.out.println("""
                    Wybierz plec:
                    m - mezczyzna
                    f - kobieta
                    """);
            tmpplec = scanner.nextLine();

            if (tmpplec.equals("m") || tmpplec.equals("f")) {
                Gracz player = new Rogue(tmpimie, tmpplec);
                System.out.println(player + "\n");
                Tawerna tawerna = new Tawerna(player);
            } else wyborNazwyLowcy();
        }
    }
    public static int kosaPodZebro() {
        if(kondycja>10) {
            kondycja = kondycja - 10;
            return ((int)(Math.random() * zrecznosc) + 1) * ((int)(Math.random() * szczescie) + 6);
        } else System.out.println("Masz za malo kondycji!\n");
        return 0;
    }
}
