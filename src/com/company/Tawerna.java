package com.company;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static com.company.Gracz.Inventory.*;
import static java.lang.Thread.sleep;

public class Tawerna implements Opcje {
    static Gracz gracz;

    public Tawerna(Gracz postac) throws FileNotFoundException, InterruptedException {
        sleep(2000);
        System.out.println("""
                    Witaj w tawernie!
                    """);
        gracz = postac;
        FazaPrzygotowan();
    }

    public static void FazaPrzygotowan() throws InterruptedException, FileNotFoundException {
        sleep(2000);
        System.out.println("""
                    
                    Poki jeszcze nie jestes w podrozy, czy chcesz sie przygotowac?
                    1. Kup mikstury.
                    2. Odpocznij.
                    3. Zapisz gre.
                    4. Nie. (wyrusz w podroz)
                    """);

            Scanner action = new Scanner(System.in);
            int wybor = action.nextInt();

            switch (wybor) {
            case 1 -> {
                Trade();
                FazaPrzygotowan();
            }
            case 2 -> {
                Rest();
                FazaPrzygotowan();
            }
            case 3 -> {
                Opcje.zapiszGre();
                FazaPrzygotowan();
            }
            case 4 -> {
                if(Gracz.round == 0) {
                    sleep(2000);
                    System.out.println("""
                            To byla wina lesnika. To on zawsze pil w pracy.
                            I wlasnie w tym stanie splonal w swojej chacie razem z reszta lasu. Razem z reszta wioski.
                            Razem z moja matka. Razem z patriarcha. Razem ze wszystim co kiedys bylo mi znane.
                            Dzis znam tylko upadlajacy zapach ubostwa, ktory podaza za moja nedzna osoba osiadajac w scianach tawern takich jak ta.
                            
                            Kelnerka razem z zamowionych piwem przynosi Ci plakat informujacy o ekspedycji, do ktorej trwa nabor.
                            Postanawiasz skorzystac z zaproszenia i stawic sie w Polnocnym Lesie o swicie zgodnie z dolaczona informacja.""");
                    Gracz.round++;
                    GenericDungeon kopalnia = new Dungeon1(gracz);


                } else if(Gracz.round == 1) {
                    sleep(2000);
                    System.out.println("""
                            Przez ostatnia godzine wpatrujesz sie w znaleziony obraz.
                            W koncu postanawiasz wyjac go z ramki.
                            Spod plotna wypada maly papierowy prostakat.
                            Po dokladniejszych ogledzinach stwierdzasz, ze jest to mapa, ktora malym, czarnym 'X' wskazuje na miejsce,
                            ktore znajduje sie niedaleko opuszczonej kopalni, z ktorej dopiero przyszlo Ci wrocic.
                            
                            Postanawiasz, ze z rana tam wyruszysz.
                            """);
                    Gracz.round++;
                    GenericDungeon grobowiec = new Dungeon2(gracz);

                } else if(Gracz.round == 2) {
                    sleep(2000);
                    System.out.println("""
                            Wszystko uklada sie w jedna, wieksza calosc. Jest tylko jedno miejsce, w ktore musze sie teraz udac.
                            Musze pokonac ducha zlego lesniczego. Pora cofnac sie w rodzinne strony...
                            """);
                    Gracz.round++;
                    GenericDungeon sejmNaWiejskiej = new FinalDungeon(gracz);

                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + wybor);
        }
    }

    public static void Trade() throws InterruptedException, FileNotFoundException {
        System.out.println("Posiadasz " + Gracz.getGold() + " sztuk zlota.\n");
        System.out.println("""
                Co chcesz kupic?
                1. Mikstura zycia - 20 gold
                2. Mikstura many - 15 gold
                3. Pozywna racja podroznicza - 15 gold
                4. Nic, wyjdz...
                """);

        Scanner buy = new Scanner(System.in);
        int wybor = buy.nextInt();

        switch (wybor) {
            case 1 -> addHealthPotion();
            case 2 -> addManaPotion();
            case 3 -> addFood();
            case 4 -> FazaPrzygotowan();
            default -> throw new IllegalStateException(wybor + "? Nie ma takiego wyboru!");
        }
    }

    public static void Rest() throws InterruptedException, FileNotFoundException {
        System.out.println("""
                Podchodzisz do karczmarza i prosisz go o klucz do pokoju.
                Ta przyjemnosc kosztuje cie 5 sztuk zlota. Czy sie zgadzasz?
                1. Tak
                2. Nie
                """);
        Scanner scanner = new Scanner(System.in);
        int wybor = scanner.nextInt();

        switch (wybor) {
            case 1 -> {
                if (Gracz.gold > 4) {
                    System.out.println("""
                            Wyciagasz z sakiewki 5 zlotych monet i podajesz je karczmarzowi.
                            Wchodzisz po schodach na wyzsze pietro, po czym kierujesz sie do pokoju.
                            Kladziesz sie na lozku i zasypiasz.
                            Drecza cie koszmary...""");
                    sleep(2000);
                    Gracz.gold -= 5;
                    Gracz.kondycja += 15;
                    Gracz.zdrowie += 20;

                } else
                    System.out.println("Nie masz kasy, biedaku.");
                FazaPrzygotowan();
            }
            case 2 -> FazaPrzygotowan();
            default -> throw new IllegalStateException(wybor + "? Nie ma takiego wyboru!");
        }
    }
}
