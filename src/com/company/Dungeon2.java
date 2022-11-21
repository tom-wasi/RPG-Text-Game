package com.company;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Dungeon2 extends GenericDungeon implements Opcje {
    Gracz gracz;
    int choice;
    int choice1;
    int choice2;

    Scanner scanner = new Scanner(System.in);

    public Dungeon2(Gracz postac) throws FileNotFoundException, InterruptedException {
        this.gracz = postac;
        Stage_1();
    }

    public void Stage_1() throws FileNotFoundException, InterruptedException {
        sleep(2000);
        System.out.println("""
                Po dotarciu na miejsce wskazane przez mape widzisz przed soba grobowiec.
                Wszedzie jest cicho, a w powietrzu czuc chlod bijacy od kamieni.
                Wchodzisz do srodka.
                
                Masz przeczucie, ze znajdziesz tu kolejna pamiatke rodzinna, a mapa w obrazie to nie byl przypadek.
                Kierujesz sie wglab grobowca. Gdy przechodzisz obok pierwszych sarkofagow nagle 2 z nich sie otwieraja, a ze srodka wychodza kosciotrupy...
                """);

        Enemy kosciej = new Enemy(40, 20, 10);
        battleLoop(kosciej);
        System.out.println("Doskonale, teraz czas na drugiego.\n");
        Enemy kosciej2 = new Enemy(40,20,10);
        battleLoop(kosciej2);

        System.out.println("""
                Kosciotrupy zmienily sie w kupke kosci.
                
                Na koncu dlugiego, ciemnego korytarza stoi postac w bialej szacie. Proponuje ci przyjecie cennego naszyjnika po jej matce
                w zamian za oddanie dwoch swoich palcow. Czy sie zgadzasz?
                
                1. Tak. (-2 do ataku, +10 do zdrowia)
                2. Nie.
                """);

        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println("""
                        Twoje 2 palce znikly, a na szyi pojawil sie naszyjnik z opalizujacym kamieniem.
                        Zjawa rozplynela sie w powietrzu. Przed jej zniknieciem zauwazyles, ze na szyi miala sznur z kosci ludzkich palcow...
                        """);
                atak -= 2;
                zdrowie += 10;
                pamiatka2 = true;
                Stage_2();
            }
            case 2 -> {
                System.out.println("""
                        Zjawa usmiecha sie, pokazujac rzad dlugich, czarnych klow. Mowi: skoro nie chcesz podzielic sie palcami, podzielisz sie sznurem swych jelit.
                        """);
                Enemy zjawa = new Enemy (40, 20, 50);
                battleLoop(zjawa);
                System.out.println("Zjawa rozplynela sie w powietrzu. Pozostal po niej tylko naszyjnik i pare suchych kosci...\n");
                zdrowie += 10;
                pamiatka2 = true;
                Stage_2();
            }
            default -> throw new IllegalStateException(choice + "? Nie ma takiego wyboru!");
        }
    }

    public void Stage_2() throws FileNotFoundException, InterruptedException {
        System.out.println("""
                Idziesz dalej. Docierasz do rozstaju drog.
                Ktoredy idziesz?
                                
                1. Lewa
                2. Prawa
                """);
        choice1 = scanner.nextInt();
        switch (choice1) {
            case 1 -> {
                System.out.println("""
                        Dochodzisz do slepego zaulka. Blask pochodni obnazyl przed toba napis wyryty na scianie:
                        
                        "Cos, przed czym w świecie nic nie uciecze,
                         co gnie żelazo, przegryza miecze,
                         pozera ptaki, zwierzęta, ziele,
                         najtwardszy kamien na make miele,
                         krolow nie szczedzi, rozwala mury,
                         poniza nawet najwyższe góry.
                         
                         Czym jestem?"
                         
                         1. Odpowiedz na pytanie.
                         2. Cofnij sie i pojdz w prawa odnoge.
                        """);
                int choice2 = scanner.nextInt();
                switch (choice2) {
                    case 1 -> {
                        int counter = 0;
                        while(counter < 3) {
                        System.out.println("Jaka jest odpowiedz do zagadki?\n");
                        String zagadka = scanner.nextLine();
                        if (zagadka.equals("czas") || zagadka.equals("czasem")) {
                            System.out.println("""
                                    Przed toba otwiera sie tajemne przejscie do sekretnego pomieszczenia.
                                    Wchodzisz do niego i przecierasz oczy ze zdumienia.
                                    Jest tu mnostwo skrzyn z wartosciowymi rzeczami.
                                    """);
                            int tmpint = (int) (Math.random() * 7) + 1;
                            int tmpgold = (int) (Math.random() * 100) + 1;
                            System.out.println("W skrzyniach znajdujesz " + tmpint + " miksture(y) zdrowia i " + tmpgold + " sztuk zlota\n");
                            Inventory.healthPotion += tmpint;
                            Gracz.gold += tmpgold;
                            System.out.println("Cofasz sie do poprzedniej lokacji i tym razem idziesz w prawo");
                            Stage_3();
                        } else {
                            System.out.println("Niestety, to nie jest dobra odpowiedz.\n");
                            counter++;
                            }
                        }
                    } case 2 -> Stage_3();
                }
            } case 2 -> Stage_3();
        }
    }
    public void Stage_3() throws FileNotFoundException, InterruptedException {
        System.out.println("""
                Idziesz w glab. Masz wrazenie ze serce coraz mocniej Ci bije.
                W pewnym momencie zdajesz sobie sprawe, ze znajdujesz sie w pomieszczeniu, ktore jest bardzo przestrzenne.
                Slyszysz ze w katach tego pomieszczenia cos sie rusza i powoli kieruje sie w twoja strone.
                
                Co robisz?
                1. Idziesz dalej
                2. Uciekasz""");
        choice2 = scanner.nextInt();
        switch(choice2) {
            case 1 -> {
                System.out.println("""
                       Wchodzisz do nastepnego pomieszczenia. Przed toba ukazuje sie 3 kultystow.
                       Wyglada na to, ze przeszkodziles im w jakiegos rodzaju rytuale.
                       
                       Sa wsciekli i rzucaja sie na ciebie. Pozostaje Ci jedynie sie bronic - a najlepsza obrona jest atak.
                        """);
                Enemy kultysta = new Enemy(90, 20, 30);
                battleLoop(kultysta);
                Enemy kultysta1 = new Enemy(90, 20, 30);
                battleLoop(kultysta1);
                Enemy kultysta2 = new Enemy(90,20,30);
                battleLoop(kultysta2);

                System.out.println("""
                        Wszyscy trzej kultysci padli na ziemie, na ktorej po chwili pojawila sie kaluza krwi.
                        Jeden z nich ma na palcu pierscien, ktory nalezal niegdys do twojego ojca.
                        
                        Masz to, po co tu przyszedles.
                        Wrzucasz go do torby i kierujesz sie do wyjscia.
                        """);
                pamiatka3 = true;
                Tawerna tawerna = new Tawerna(gracz);
            }
            case 2 -> {
                if(kondycja > 15) {
                    System.out.println("""
                            Rzucasz sie do ucieczki potykajac sie o wlasne nogi.
                            """);
                    Tawerna tawerna = new Tawerna(gracz);
                } else System.out.println("Nie masz na tyle duzo kondycji, by teraz stad wybiec :(");
                choice2 = 1;
            }
        }
    }
}