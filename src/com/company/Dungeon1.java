package com.company;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Dungeon1 extends GenericDungeon implements Opcje {
    boolean klucz = false;

    Gracz gracz;
    int choice;
    int choice1;
    Scanner scanner = new Scanner(System.in);

    public Dungeon1(Gracz postac) throws FileNotFoundException, InterruptedException {
        this.gracz = postac;
        Stage_1();
        }

    public void Stage_1() throws FileNotFoundException, InterruptedException {
        sleep(2000);
        System.out.println("""
                Przybywasz na miejsce, ale nie mozesz wzrokiem odnalezc swojego mocodawcy.
                Zauwazasz porozrzucane w pospiechu pakunki. W lesie panuje niecodzienna cisza.
                
                Nagle Twoich uszu dobiega rubaszny smiech, ktorego zrodlo jest gdzies w okolicy.
                Wyglada na to, ze wpadles w zasadzke. Atakuje cie zboj.
                """);
        Enemy zboj1 = new Enemy(10, 5, 10);

        battleLoop(zboj1);

        System.out.println("""
                Zanim dobiles napastnika ten blagal Cie o litosc w zamian za informacje o tym, gdzie on i jego kompani
                przetrzymuja skradzione kamienie szlachetne: lezaca nieopodal opuszczona kopalnia.
                Udales sie w kierunku wskazanym przez zboja. Niestety wejscie jest zablokowane.
                
                Co chcesz zrobic?
                1. Wejsc do kopalni
                2. Rozejrzec sie po okolicy
                """);

        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> Stage_2();
            case 2 -> {
                System.out.println("""
                        Posrod lezacych zwlok przy wejsciu znalazles stary klucz.
                        Chyba nic juz tu nie znajdziesz, wiec kierujesz sie do srodka
                        """);
                klucz = true;
                Stage_2();
            }
            default -> throw new IllegalStateException(choice + "? Nie ma takiego wyboru!");
        }
    }

    public void Stage_2() throws FileNotFoundException, InterruptedException {
        System.out.println("""
                Wchodzisz przez zawalisko do srodka. Uderza Cie smrod gnijacego miesa.
                Z ciemnosci jaskini wychodzi niski, zielony stwor - goblin.
                Warczy na Cebie groznie, po czym rzuca sie do ataku.""");

        Enemy goblin1 = new Enemy(15, 10, 15);

        battleLoop(goblin1);

        System.out.println("""
                Po uporaniu sie z goblinem dostrzegasz przed soba dwie drogi.
                Ktora idziesz?
                1. Lewa
                2. Prawa""");
        choice = scanner.nextInt();
        switch(choice) {
            case 1 -> {
                System.out.println("""
                        W mroku dochodzisz do sciany o malo niepotykajac sie o spora drewniana skrzynie.
                        Czy chcesz ja otworzyc?
                        1. Tak
                        2. Nie
                        """);
                choice1 = scanner.nextInt();
                switch(choice1) {
                    case 1 -> {
                        if(klucz || Gracz.klasa.equals("lowca")) {
                            int tmpint = (int)(Math.random() * 3) + 1;
                            int tmpgold = (int)(Math.random() * 20) + 1;
                            System.out.println("Zamek otworzyl sie z cichym trzaskiem.\n" +
                                    "W srodku znajdujesz " + tmpint + " miksture(y) zdrowia i " + tmpgold + " sztuk zlota\n");
                            Inventory.healthPotion += tmpint;
                            Gracz.gold += tmpgold;
                            System.out.println("Cofasz sie do poprzedniej lokacji i tym razem idziesz w prawo");
                        } else {
                            System.out.println("""
                                    Nie masz odpowiedniego klucza.
                                    Cofasz sie do poprzedniej lokacji i tym razem idziesz w prawo.
                                    """);
                        }
                        Stage_3();
                    }
                    case 2 -> {
                        System.out.println("Odchodzisz od skrzyni i cofasz sie do poprzedniej lokacji. Tym razem idziesz w prawo.");
                        Stage_3();
                    }
                }
            }
            case 2 -> Stage_3();
        }
    }
    public void Stage_3() throws FileNotFoundException, InterruptedException {
        System.out.println("""
                Idziesz w glab odnogi. Po chwili slyszysz przerazajacy ryk - taki jaki wydawal z siebie ten maly goblin
                tylko 100 razy glosniejszy i przerazajacy.
                Co robisz?
                1. Idziesz dalej
                2. Uciekasz""");
        choice = scanner.nextInt();
        switch(choice) {
            case 1 -> {
                System.out.println("""
                        Kierujesz sie dalej, wglab jaskini. Po chwili dostrzegasz jasny plomien ogniska, a potem ogromnego, zielonego hobogoblina.
                        Bezmyslne stworzenie rzuca sie na ciebie lecz ty robisz zreczny unik.
                        Pozostaje Ci jedynie sie bronic - a najlepsza obrona jest atak.
                        """);
                Enemy hobogoblin = new Enemy(50, 20, 50);
                battleLoop(hobogoblin);
                System.out.println("""
                        Wielkie zielone cielsko pada na ziemie.
                        Nie bylo latwo ale teraz w spokoju mozesz przeszukac pomieszczenie.
                        Znajdujesz 30 sztuk zlota i stary, dziwnie znajomy obraz.
                        
                        Zdaje sie, ze pamietasz go z dziecinstwa - to portret kobiety w ogniu.
                        Wrzucasz go do torby i kierujesz sie do wyjscia.
                        """);
                pamiatka1 = true;
                gold += 30;
                Tawerna tawerna = new Tawerna(gracz);
            }
            case 2 -> {
                System.out.println("""
                        Rzucasz sie do ucieczki potykajac sie o wlasne nogi.
                        Pech chcial, ze w ciemnosci nie zostal przez ciebie zauwazony wystajacy korzen.
                        Upadasz, uderzajac skronia o kamien.""");
                Death();
            }
        }
    }
}