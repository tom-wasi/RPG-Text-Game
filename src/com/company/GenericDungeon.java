package com.company;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public abstract class GenericDungeon extends Gracz implements Opcje {
    //wspolny mianownik wszystkich lokacji, w ktorych gracz bedzie przeprowadzal walki
    //
    //

    public GenericDungeon() {}

    public void Death() throws FileNotFoundException, InterruptedException {
        //metoda do wywolania smierci. Odsyla do pierwszego menu
        //
        //

        sleep(2000);
        System.out.println("""
                Byc moze masz zbyt wiele na swoich barkach.
                Byc moze ta misja cie przerosla.
                Tak czy inaczej zdajesz sobie sprawe, ze to koniec.
                Padasz na podloge i przyzwyczajasz sie do piachu... do momentu, gdy Twoj przeciwnik Cie dobija.""");
        System.out.println("Twoj wynik to: " + Gracz.getScore() + "\n");
        Opcje.consoleCleaner();
        Main.powitanie();
    }

    public void Flee() throws FileNotFoundException, InterruptedException { //ucieczka z pola walki
        if (Gracz.kondycja > 15) {
            sleep(2000);
            System.out.println("""
                    Stwierdzasz, ze nie dasz rady wygrac tej walki i biegniesz co sil w nogach do wyjscia.
                    Nastepnym razem powrocisz z tarcza!
                    """);
            Gracz.round -= 1;
            Gracz.kondycja -= 15;
            Opcje.consoleCleaner();
            Tawerna.FazaPrzygotowan();
        } else {
            Death();

        }
    }

    public void useSpecialAbility(Enemy enemy) {
        //metoda do wywolania specjalnych umiejetnosci poszczegolnych klas postaci
        //
        //

        Scanner scanner = new Scanner(System.in);
        int choice;
        switch (klasa) {
            case "mag" -> {
                System.out.println("1. Kula Ognia\n2. Kojacy Dotyk");

                choice = scanner.nextInt();
                if (choice == 1) {
                    int obrazenia = Mage.kulaOgnia() + ((int)(Math.random() * szczescie) / (((int)(Math.random() * enemy.obrona) + 1)));
                    enemy.zdrowie = enemy.zdrowie - obrazenia;
                    System.out.println("Zadales:" + obrazenia + " obrazen.");

                } else if(choice == 2) {
                    Mage.kojacyDotyk();
                }
                System.out.println("Twoje zdrowie:" + getZdrowie());
            }

            case "wojownik" -> {
                System.out.println("1. Rabniecie toporem\n");
                choice = scanner.nextInt();
                if(choice == 1) {
                    int obrazenia = Warrior.rabniecieToporem() * ((int)(Math.random() * szczescie) / (((int)(Math.random() * enemy.obrona) + 1)));
                    enemy.zdrowie = enemy.zdrowie - obrazenia;
                    System.out.println("Zadales:" + obrazenia + " obrazen.");
                }

            }
            case "lowca" -> {
                System.out.println("1. Kosa Pod Zebro\n");
                choice = scanner.nextInt();
                if(choice == 1) {
                    int obrazenia = Rogue.kosaPodZebro() + ((int)(Math.random() * szczescie) / (((int)(Math.random() * enemy.obrona) + 1)));
                    enemy.zdrowie = enemy.zdrowie - obrazenia;
                    System.out.println("Zadales:" + obrazenia + " obrazen.");
                }
            }
            case "czlowiek z nikad" -> {
                System.out.println("1. Uderzenie z Piesci\n");
                choice = scanner.nextInt();
                if(choice == 1) {
                    int obrazenia = NoLandMan.uderzenieZPiesci() + ((int)(Math.random() * szczescie) / (((int)(Math.random() * enemy.obrona) + 1 )));
                    enemy.zdrowie = enemy.zdrowie - obrazenia;
                    System.out.println("Zadales:" + obrazenia + " obrazen.");
                }
        }
    }

}

    public void battleLoop(Enemy atakowany) throws FileNotFoundException, InterruptedException {
        //petla do przeprowadzania bitwy. GenericDungeon jest podklasa Gracza, wiec domyslnie wszystkie zmienne sa zasysane
        //
        //

        while(atakowany.zdrowie > 0 && zdrowie > 0) {
            sleep(2000);
            System.out.println("1. Atak\n2. Ucieczka.\n3. Skorzystaj z inwentarza\n4. Skorzystaj ze specjalnych umiejetnosci klasy\n");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch(choice) {
                case 1 -> {
                    attack(atakowany);
                    counterAttack(atakowany);
                    System.out.println("Twoje zdrowie: " + zdrowie + "\nZdrowie przeciwnika: " + atakowany.zdrowie);
                    sleep(2000);
                }
                case 2 -> Flee();

                case 3 -> useInventory();

                case 4 -> useSpecialAbility(atakowany);

                default -> throw new IllegalStateException(choice+"? Nie ma takiego wyboru!\n");
            }
            if (atakowany.zdrowie <= 0) {
                System.out.println("Gratulacje, wygrywasz\n");
                score += 5;
                enemyCounter++;
            } else if(zdrowie <= 0) {
                Death();
            }
        }
    }

    public void attack(Enemy atakowany) throws InterruptedException {
        //metoda z najprostszym atakiem
        //
        //
        sleep(2000);
        int obrazenia = atak;
        atakowany.zdrowie = atakowany.zdrowie - obrazenia;
        System.out.println("Zadajesz przeciwnikowi " + obrazenia + " obrazen.\n");
    }

    public void counterAttack(Enemy atakujacy) {
        //metoda do kontrataku przeciwnikow
        //
        //

        int obrazenia = (int)(Math.random() * atakujacy.atak) + 1;
        zdrowie = zdrowie - obrazenia;
        System.out.println("Przeciwnik zadaje Ci " + obrazenia + " obrazen.\n");
    }

    public void useInventory() throws InterruptedException {
        //rozszerzenie opcji walki. Zasysane z wewnetrznej klasy Gracza.
        //
        //

        System.out.println("Masz: \n" +
                + Inventory.healthPotion + " mikstur zdrowia\n" +
                + Inventory.manaPotion + " mikstur many\n" +
                + Inventory.food + " racji zywnosciowych.\n" +
                "Czego chcesz uzyc?\n");
        sleep(2000);
        System.out.println("1.Miksture zdrowia\n2.Miksture many\n3.Racje zywnosciowa\n4.Nic, wyjdz");

        Scanner scanner = new Scanner(System.in);
        int wybor = scanner.nextInt();
        switch (wybor) {
            case 1 -> {
                if(Inventory.healthPotion > 0) {
                zdrowie += 25;
                Inventory.healthPotion -= 1;
                } else System.out.println("Nie masz mikstur zdrowia!");
                useInventory();
            }
            case 2 -> {
                if(Inventory.manaPotion > 0) {
                    Gracz.mana += 25;
                    Inventory.manaPotion -= 1;
                } else System.out.println("Nie masz mikstur many!");
                useInventory();
            }
            case 3 -> {
                if(Inventory.food > 0) {
                    Gracz.kondycja += 15;
                    Inventory.food -= 1;
                } else System.out.println("Nie masz racji zywnosciowych!");
                useInventory();
            }
        }
    }
    public void endGame() throws FileNotFoundException, InterruptedException {
        if(celGry1()) {
            if(celGry2()) {
                if(celGry3()) {
                    System.out.println("Gratulacje! Udalo Ci sie zdjac klatwe!\n Twoj wynik to: " + getScore());
                    Opcje.zapisywanieRankingu();
                    Opcje.consoleCleaner();
                    Main.powitanie();
                } else System.out.println("Niestety, nie udalo Ci sie zdjac klatwy...");
                Opcje.consoleCleaner();
                Main.powitanie();
            } else System.out.println("Niestety, nie udalo Ci sie zdjac klatwy...");
            Opcje.consoleCleaner();
            Main.powitanie();
        } else System.out.println("Niestety, nie udalo Ci sie zdjac klatwy...");
        Opcje.consoleCleaner();
        Main.powitanie();
    }
}
