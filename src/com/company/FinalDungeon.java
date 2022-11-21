package com.company;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class FinalDungeon extends GenericDungeon implements Opcje {
    Gracz gracz;
    int choice;
    int choice1;

    Scanner scanner = new Scanner(System.in);

    public FinalDungeon(Gracz postac) throws FileNotFoundException, InterruptedException {
        this.gracz = postac;
        Stage_1();
    }

    public void Stage_1() throws FileNotFoundException, InterruptedException {
        sleep(2000);
        System.out.println("""
                Wszystko wyglada dokladnie tak, jak tego dnia gdy mysliwy wynosil Cie stad na rekach.
                Na srodku placu widac studnie; kiedys dawala tej wsi zycie, dzis calkowicie wyschla...
                Powietrze przeszywa dziwny dzwiek przypominajacy skrzek mewy. Powietrze staje sie zimne.
                Zza sciany studni wysuwaja sie dwie drobne biale dlonie, a za nimi kawalek dzieciej, przemoczonej glowy.
                Male czarne oczy patrza na Ciebie - zjawa wyglada ciekawsko, ale ostroznie zza omszonych kamieni. 
                
                "Czego tu szukasz? Daj mi spokoj... Odejdz" -mowi.
                
                Co odpowiesz?
                1. Przybylem, bo moi rodzice jak i cala wioska nie powinni byli umrzec.
                2. Dobrze, juz ide...
                """);
        choice = scanner.nextInt();
        switch(choice) {
            case 1 -> {
                System.out.println("""
                        Aparycja ducha zmienia sie; dzieciecie oczy przeobrazaja sie w rozmyty obraz meskiej sylwetki:
                        "Myslisz ze tego chcialem?
                        Od kiedy umarlem jestem skazany na wieczna tulaczke przez poczucie winy.
                        Jedyne, co mogloby mnie uwolnic, to gdyby ktos dobil mnie tym mieczem..."
                        
                        Duch wskazal koscistym palcem na miecz, ktory lezy nieopodal wejscia do jednej z chat. Obok niego lezy trup w zbroi.
                        
                        "Blagam, zabij mnie nim, a wreszcie bede mogl zaznac spokoju..."
                        
                        
                        Co odpowiesz?
                        1. Zgoda, zrobie to.
                        2. Nie zaslugujesz na spokoj.
                        """);
                choice1 = scanner.nextInt();
                switch(choice1) {
                    case 1 -> {
                        System.out.println("""
                                Duch Ci odpowiada:
                                "Dziekuje. Niestety - musisz mnie pokonac w rownej walce. Takie sa zasady."
                                
                                Duch przybiera agresywna postawe i rzuca sie na Ciebie zaraz po tym, jak dobywasz miecza.
                                """);
                        Enemy duchLesnika = new Enemy(120,40,50);
                        battleLoop(duchLesnika);
                        System.out.println("Duch wydaje sie byc bardziej rozwscieczony. Zdaje sie, ze nieco urosl...\n");

                        Enemy duchLesnika1 = new Enemy(120, 50, 60);
                        battleLoop(duchLesnika1);
                        System.out.println("""
                                Zdaje sie, ze to koniec.
                                Zdawalo Ci sie, ze gdy otrzymasz swoja zemste, to poczujesz sie lepiej.
                                Niestety raz zadane rany nigdy nie znikaja. Zemsta nie jest droga do ukojenia. 
                                """);
                        isBossDead = true;
                        endGame();
                    }
                    case 2 -> {
                        System.out.println("""
                                Duch Ci odpowiada:
                                "Ludzie tacy jak Ty, ktorzy nie potrafia okazac milosierdzia zasluguja tylko na jedno."
                                Duch przybiera agresywna postawe i rzuca sie na Ciebie zaraz po tym, jak dobywasz miecza.
                                """);
                        Enemy duchLesnika = new Enemy(120,40,50);
                        battleLoop(duchLesnika);
                        System.out.println("Duch wydaje sie byc bardziej rozwscieczony. Zdaje sie, ze nieco urosl...\n");

                        Enemy duchLesnika1 = new Enemy(120, 50, 60);
                        battleLoop(duchLesnika1);
                        System.out.println("""
                                Duch zmienia sie nie do poznania - w swego rodzaju widmowe monstrum.
                                Chyba bedzie jeszcze trudniej...
                                """);
                        Enemy duchLesnika2 = new Enemy(150,60,80);
                        battleLoop(duchLesnika2);
                        System.out.println("""
                                Zdaje sie ze to koniec.
                                Zdawalo Ci sie, ze gdy otrzymasz swoja zemste, to poczujesz sie lepiej.
                                Niestety - zdaje sie ze nie miales racji...
                                """);
                        isBossDead = true;
                        endGame();
                    }
                }
            }
            case 2 -> endGame();
        }
    }
}
