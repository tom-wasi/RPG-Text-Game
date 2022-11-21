package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public interface Opcje {
    static void zapiszGre() {
        try {
            PrintWriter zapis = new PrintWriter("save.txt");
            zapis.println(Gracz.enemyCounter);
            zapis.println(Gracz.pamiatka1);
            zapis.println(Gracz.pamiatka2);
            zapis.println(Gracz.pamiatka3);
            zapis.println(Gracz.isBossDead);
            zapis.println(Gracz.score);
            zapis.println(Gracz.round);
            zapis.println(Gracz.klasa);
            zapis.println(Gracz.imie);
            zapis.println(Gracz.plec);
            zapis.println(Gracz.zdrowie);
            zapis.println(Gracz.mana);
            zapis.println(Gracz.atak);
            zapis.println(Gracz.obrona);
            zapis.println(Gracz.kondycja);
            zapis.println(Gracz.gold);
            zapis.println(Gracz.sila);
            zapis.println(Gracz.zrecznosc);
            zapis.println(Gracz.inteligencja);
            zapis.println(Gracz.szczescie);
            zapis.println(Gracz.Inventory.healthPotion);
            zapis.println(Gracz.Inventory.manaPotion);
            zapis.println(Gracz.Inventory.food);

            zapis.close();
        } catch (FileNotFoundException E) {
            System.out.println("W katalogu gry nie ma pliku save.txt!");
        }
    }

    static void wczytajGre() {
        try {
            File wczytywanie = new File("save.txt");
            Scanner input = new Scanner(wczytywanie);
            Gracz.setEnemyCounter(input.nextInt());
            Gracz.setPamiatka1(input.nextBoolean());
            Gracz.setPamiatka2(input.nextBoolean());
            Gracz.setPamiatka3(input.nextBoolean());
            Gracz.setIsBossDead(input.nextBoolean());
            Gracz.setScore(input.nextInt());
            Gracz.setRound(input.nextInt());
            Gracz.setKlasa(input.next());
            Gracz.setImie(input.next());
            Gracz.setPlec(input.next());
            Gracz.setZdrowie(input.nextInt());
            Gracz.setMana(input.nextInt());
            Gracz.setAtak(input.nextInt());
            Gracz.setObrona(input.nextInt());
            Gracz.setKondycja(input.nextInt());
            Gracz.setGold(input.nextInt());
            Gracz.setSila(input.nextInt());
            Gracz.setZrecznosc(input.nextInt());
            Gracz.setInteligencja(input.nextInt());
            Gracz.setSzczescie(input.nextInt());
            Gracz.Inventory.healthPotion = input.nextInt();
            Gracz.Inventory.manaPotion = input.nextInt();
            Gracz.Inventory.food = input.nextInt();

            input.close();
        } catch (FileNotFoundException E) {
            System.out.println("W katalogu gry nie ma pliku save.txt!");
        }
        finally {
            System.out.println("Zawsze to wypisze");
        }
    }
    static void wczytywanieRankingu()  {
            File rankingOUT = new File("ranking.txt");
            ArrayList<Integer> lista = new ArrayList<>();
            try {
                Scanner scanner = new Scanner(rankingOUT);
                while(scanner.hasNext()) {
                    lista.add(scanner.nextInt());
                    Collections.sort(lista);
                }
                System.out.println("Najwyzsze wyniki - " + lista + "\n");
            } catch (FileNotFoundException e) {
                System.out.println("W katalogu gry nie ma pliku ranking.txt!");
            }
    }
    static void zapisywanieRankingu() {
        try {
            PrintWriter zapis = new PrintWriter("ranking.txt");
            zapis.println(Gracz.score);
            zapis.close();
        } catch (FileNotFoundException e) {
            System.out.println("W katalogu gry nie ma pliku ranking.txt!");
        }
    }
    static void consoleCleaner() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
