package com.company;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.Thread.sleep;

public class Main implements Opcje{
    public static void powitanie() throws InterruptedException, FileNotFoundException {
        Scanner scan = new Scanner(System.in);

        System.out.println("""
                1. Nowa gra
                2. Wczytaj gre
                3. Wyjdz
                4. Ranking
                5. Tworcy
                
                """);
        int akcja = scan.nextInt();

        switch (akcja) {
            case 1 -> Gracz.kreatorPostaci();

            case 2 -> {
                Opcje.wczytajGre();
                Tawerna.FazaPrzygotowan();
            }

            case 3 -> System.out.println("Do nastepnego!\n");

            case 4 -> Opcje.wczytywanieRankingu();

            case 5 -> {
                System.out.println("Muzyka - Tomasz Wasielewski\n");
                sleep(1000);
                System.out.println("Scenariusz - Tomasz Wasielewski\n");
                sleep(1000);
                System.out.println("Rezyseria - Tomasz Wasielewski\n");
                sleep(1000);
            }
            default -> throw new IllegalStateException(akcja + "? Nie ma takiego wyboru!");
        }
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        System.out.println("Witaj w Klatwie.\n");
        powitanie();
    }
}

