package com.company;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Gracz implements Opcje {

    static int enemyCounter = 0;
    static boolean pamiatka1 = false;
    static boolean pamiatka2 = false;
    static boolean pamiatka3 = false;
    static boolean isBossDead = false;

    static int score = 0; //zmienna do przechowania w rankingu
    static int round = 0; //zeby bylo wiadomo gdzie nastepnie pojsc z tawerny
    static String klasa;
    static String imie;
    static String plec;
    static int zdrowie;
    static int mana;
    static int atak;
    static int obrona;
    static int kondycja;
    static int gold = 50;
    static int sila;
    static int zrecznosc;
    static int inteligencja;
    static int szczescie;

    public Gracz() {}

    public static int getEnemyCounter() {
        return enemyCounter;
    }

    public static void setEnemyCounter(int enemyCounter) {
        Gracz.enemyCounter = enemyCounter;
    }

    public static boolean isIsBossDead() { return isBossDead; }

    public static void setIsBossDead(boolean isBossDead) { Gracz.isBossDead = isBossDead; }

    public static boolean isPamiatka1() {
        return pamiatka1;
    }

    public static void setPamiatka1(boolean pamiatka1) {
        Gracz.pamiatka1 = pamiatka1;
    }

    public static boolean isPamiatka2() {
        return pamiatka2;
    }

    public static void setPamiatka2(boolean pamiatka2) {
        Gracz.pamiatka2 = pamiatka2;
    }

    public static boolean isPamiatka3() {
        return pamiatka3;
    }

    public static void setPamiatka3(boolean pamiatka3) {
        Gracz.pamiatka3 = pamiatka3;
    }

    public static int getAtak() {
        return atak;
    }

    public static void setAtak(int atak) {
        Gracz.atak = atak;
    }

    public static int getObrona() {
        return obrona;
    }

    public static void setObrona(int obrona) {
        Gracz.obrona = obrona;
    }

    public static String getKlasa() { return klasa; }

    public static void setKlasa(String klasa) { Gracz.klasa = klasa; }

    public static String getImie() { return imie; }

    public static void setImie(String imie) {
        Gracz.imie = imie;
    }

    public static int getZdrowie() {
        return zdrowie;
    }

    public static void setZdrowie(int zdrowie) {
        Gracz.zdrowie = zdrowie;
    }

    public static int getRound() { return round; }

    public static void setRound(int round) { Gracz.round = round; }

    public static String getPlec() { return plec; }

    public static void setPlec(String plec) { Gracz.plec = plec; }

    public static int getSila() { return sila; }

    public static void setSila(int sila) { Gracz.sila = sila; }

    public static int getZrecznosc() { return zrecznosc; }

    public static void setZrecznosc(int zrecznosc) { Gracz.zrecznosc = zrecznosc; }

    public static int getInteligencja() { return inteligencja; }

    public static void setInteligencja(int inteligencja) { Gracz.inteligencja = inteligencja; }

    public static int getSzczescie() { return szczescie; }

    public static void setSzczescie(int szczescie) { Gracz.szczescie = szczescie; }

    public static int getMana() {
        return mana;
    }

    public static void setMana(int mana) {
        Gracz.mana = mana;
    }

    public static int getKondycja() {
        return kondycja;
    }

    public static void setKondycja(int kondycja) {
        Gracz.kondycja = kondycja;
    }

    public static int getGold() { return gold; }

    public static void setGold(int gold) {
        Gracz.gold = gold;
    }

    public static int getScore() { return score; }

    public static void setScore(int score) { Gracz.score = score; }

    public static boolean celGry1() {
        return isWrogowie();
    }
    public static boolean celGry2() {
        return isPamiatka1() && isPamiatka2() && isPamiatka3();
    }
    public static boolean celGry3() {
        return isBossDead;
    }

    public static void kreatorPostaci() throws InterruptedException, FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
              
                Podczas swojej podrozy bedziesz  3 misje do wykonania:
                 1. Zabic 10 przeciwnikow
                 2. Znalezc 3 pamiatki rodzinne ukryte w lokacjach, ktore napotkasz podczas gry
                 3. Pokonac ostatniego bossa gry.
                
                """);

        System.out.println("""
                
                Dostępne klasy postaci:
                1. Wojownik
                2. Mag
                3. Lowca
                4. Czlowiek z Nikad
                
                """);
        int klasaPostaci = scanner.nextInt();

        switch (klasaPostaci) {
            case 1 -> Warrior.wyborNazwyWojownika();
            case 2 -> Mage.wyborNazwyMaga();
            case 3 -> Rogue.wyborNazwyLowcy();
            case 4 -> NoLandMan.wyborNazwyCZN();
            default -> throw new IllegalStateException(klasaPostaci + "? Nie ma takiego wyboru! ");
        }
    }

    public static boolean isWrogowie() {
        return enemyCounter >= 10;
    }


    public static class Inventory {
        public static int healthPotion = 0;
        public static int manaPotion = 0;
        public static int food = 0;

        public static void addHealthPotion() {
            if(healthPotion >= 3) {
                System.out.println("Pelny ekwipunek!\n");
            } else
            if (Gracz.gold > 19) {
                Gracz.gold -= 20;
                healthPotion++;
                System.out.println("Kupiles 1 miksture zdrowia...");
            } else System.out.println("Nie masz kasy, biedaku\n");
        }

        public static void addManaPotion() {
            if(manaPotion >= 3) {
                System.out.println("Pelny ekwipunek!\n");
            } else
            if (Gracz.gold > 14) {
                Gracz.gold -= 15;
                manaPotion++;
                System.out.println("Kupiles 1 miksture many...");
            } else System.out.println("Nie masz kasy, biedaku\n");
        }

        public static void addFood() {
            if(food >= 3) {
                System.out.println("Pelny ekwipunek!\n");
            } else
            if (Gracz.gold > 14) {
                Gracz.gold -= 15;
                food++;
                System.out.println("Kupiles 1 porcje jedzenia...");
            } else System.out.println("Nie masz kasy, biedaku\n");
        }
    }
}