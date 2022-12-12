package claudiu.varlan.proiect.main;

import claudiu.varlan.proiect.categorii.Categorie;
import claudiu.varlan.proiect.clase.Abonat;
import claudiu.varlan.proiect.clase.Biblioteca;
import claudiu.varlan.proiect.clase.Carte;
import claudiu.varlan.proiect.clase.Imprumut;
import claudiu.varlan.proiect.exceptii.ExceptieGenerala;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static int citireOptiune(){
        Scanner scan = new Scanner(System.in);
        int optSelectata = -1;
        boolean isANumber = true;
        while(isANumber) {
            try {
                optSelectata = scan.nextInt();
                isANumber = false;
            } catch (Exception e) {
                System.out.println("Nu este cifra");
                break;
            }
        }
        return optSelectata;
    }


    public static void meniu(Biblioteca biblioteca) {
        System.out.println("MENIU Principal");
        System.out.println("1. Vizualizare informatii despre biblioteca.");
        System.out.println("2. Informatii carti.");
        System.out.println("3. Abonati.");
        System.out.println("4. Informatii imprumuturi.");
        System.out.println("5. Statistici aplicatie.");
        System.out.println("6. Inchidere aplicatie.");
        Scanner scan = new Scanner(System.in);
        boolean repeta = true;
        int optSelectata = -1;
        boolean isANumber = true;
//        while (isANumber) {
//            try {
                optSelectata = citireOptiune();
//                isANumber = false;
//            } catch (Exception e) {
//                System.out.println("Cannot read!");
//                break;
//            }
//        }
        while (repeta) {
            switch (optSelectata) {
                case 0:
                    System.out.println("MENIU Principal");
                    System.out.println("1. Vizualizare informatii despre biblioteca.");
                    System.out.println("2. Informatii carti.");
                    System.out.println("3. Informatii abonati.");
                    System.out.println("4. Informatii imprumuturi.");
                    System.out.println("5. Statistici aplicatie.");
                    System.out.println("6. Inchidere aplicatie.");
                    optSelectata = citireOptiune();
                    break;

                case 1:
                    System.out.println(biblioteca.toString());
                    System.out.println("0. Meniu principal.");
                    optSelectata = citireOptiune();
                    break;

                case 2:
                    System.out.println("1. Vizualizare toate cartile.");
                    System.out.println("2. Carti disponibile.");
                    System.out.println("3. Carti imprumutate.");
                    System.out.println("4. Cauta carte/Localizare.");
                    System.out.println("5. Vizualizare carti pe categorii.");
                    System.out.println("6. Adaugare carte.");
                    System.out.println("7. Imprumuta carte.");
                    System.out.println("8. Restituie carte.");
                    System.out.println("9. Inapoi la meniul principal.");
                    int opt = citireOptiune();
                    switch (opt) {
                        case 1:
                            for (Carte c : biblioteca.getListaCarti())
                                System.out.println(c.toString());
                            System.out.println("0. Meniu principal");
                            System.out.println("2. Inapoi");
                            optSelectata = citireOptiune();
                            break;

                        case 2:
                            for (Carte c : biblioteca.getListaCarti()) {
                                if (c.isEsteImprumutata() == false)
                                    System.out.println(c.toString());
                            }
                            System.out.println("0. Meniu principal");
                            System.out.println("2. Inapoi");
                            optSelectata = citireOptiune();
                            break;

                        case 3:
                            for (Carte c : biblioteca.getListaCarti()) {
                                if (c.isEsteImprumutata() == true)
                                    System.out.println(c.toString());
                            }
                            System.out.println("0. Meniu principal");
                            System.out.println("2. Inapoi");
                            optSelectata = citireOptiune();
                            break;

                        case 4:
                            System.out.println("1. Cauta carte dupa titlu.");
                            System.out.println("2. Cauta carte dupa cod ISBN.");
                            int preferinta = citireOptiune();
                            switch (preferinta) {
                                case 1:
                                    System.out.println("Titlu cautat: ");
                                    scan.useDelimiter("\n");
                                    String nume = scan.next();
                                    for (Carte carte : biblioteca.getListaCarti()) {
                                        if (carte.getTitlu().contains(nume)) {
                                            System.out.println("Informatii carte: ");
                                            System.out.println(carte.toString());
                                            if (carte.isEsteImprumutata() == false) {

                                                biblioteca.getLocalizare().entrySet().forEach(entry -> {
                                                    //	System.out.println(entry.getKey() +"  "+entry.getValue()+"  "+carte.getCodISBN());
                                                    if (entry.getKey().contains(carte.getCodISBN())) {

                                                        System.out.println("Cartea este localizata la randul " + entry.getValue());
                                                    }

                                                });
                                            } else {
                                                System.out.println("Cartea nu se afla in biblioteca.");
                                            }
                                        }
                                    }
                                    System.out.println("0. Meniu principal");
                                    System.out.println("2. Inapoi");
                                    optSelectata = citireOptiune();
                                    break;

                                case 2:
                                    System.out.println("Cod ISBN: ");
                                    String codisbn = scan.next();
                                    for (Carte carte : biblioteca.getListaCarti()) {
                                        if (carte.getCodISBN().contains(codisbn)) {
                                            System.out.println("Informatii carte: ");
                                            System.out.println(carte.toString());
                                            if (carte.isEsteImprumutata() == false) {

                                                biblioteca.getLocalizare().entrySet().forEach(entry -> {
                                                    //	System.out.println(entry.getKey() +"  "+entry.getValue()+"  "+carte.getCodISBN());
                                                    if (entry.getKey().contains(carte.getCodISBN())) {

                                                        System.out.println("Cartea este localizata la randul " + entry.getValue());
                                                    }

                                                });
                                            } else {
                                                System.out.println("Cartea nu se afla in biblioteca.");
                                            }
                                        }
                                    }
                                    System.out.println("0. Meniu principal");
                                    System.out.println("2. Inapoi");
                                    optSelectata = citireOptiune();
                                    break;

                            }
                            break;
                        case 5:
                            System.out.println("Selectati categoria dorita:");
//					System.out.println("1. SF");
//					System.out.println("2. Literatura universala");
//					System.out.println("3. Beletristica");
//					System.out.println("4. Carti pentru copii");
//					System.out.println("5. Altele");
                            for (Categorie c : Categorie.values()) {
                                System.out.println(c.ordinal() + " " + c.name());
                            }
                            int cat = citireOptiune();
                            for (Categorie c : Categorie.values()) {
                                if (c.ordinal() == cat) {
                                    Carte.afisareCartiCategorie(c.name(), biblioteca.getListaCarti());
                                }
                            }
                            System.out.println("0. Meniu principal");
                            System.out.println("2. Inapoi");
                            optSelectata = citireOptiune();
                            break;
                        case 6:
                            Carte newCarte = new Carte();
                            try {
                                System.out.println("Titlu carte: ");
                                scan.useDelimiter("\n");
                                newCarte.setTitlu(scan.next());

                                System.out.println("Autor: ");
                                scan.useDelimiter("\n");
                                newCarte.setAutor(scan.next());

                                System.out.println("Cod ISBN: ");
                                scan.useDelimiter("\n");
                                newCarte.setCodISBN(scan.next(), biblioteca.getListaCarti());

                                System.out.println("Categorie: ");
                                scan.useDelimiter("\n");
                                String categorie = scan.next();
                                newCarte.setCategorieString(categorie);

                                newCarte.setEsteImprumutata(false);

                                System.out.println(newCarte.toString() + ". A fost adaugata.");
                                ArrayList<Carte> newLista = biblioteca.getListaCarti();
                                newLista.add(newCarte);
                                biblioteca.setListaCarti(newLista);
                            } catch (ExceptieGenerala e) {
                                e.printStackTrace();
                            }
                            System.out.println("0. Meniu principal");
                            System.out.println("2. Inapoi");
                            optSelectata = citireOptiune();
                            break;
                        case 7:
                            ArrayList<String> listaCoduriCartiDisponibile = new ArrayList<>();

                            System.out.println("Imprumuta carte");
                            for (Carte c : biblioteca.getListaCarti()) {
                                if (c.isEsteImprumutata() == false) {
                                    System.out.println(c.toString());
                                    listaCoduriCartiDisponibile.add(c.getCodISBN());
                                }

                            }
                            System.out.println("Introduceti codul isbn al cartii dorite:");

                            String codISBNImprumut = scan.next();
                            codISBNImprumut = codISBNImprumut.toUpperCase();
                            if (!listaCoduriCartiDisponibile.contains(codISBNImprumut)) {
                                System.out.println("Cartea este deja imprumutata!");
                                break;
                            }
                            System.out.println("Selectati abonatul care imprumuta cartea: ");
                            for (Abonat a : biblioteca.getAbonati()) {
                                System.out.println(a.toString());
                            }
                            int idAbonatImprumumut = citireOptiune();
                            Abonat abimprumut = new Abonat();
                            for (Abonat a : biblioteca.getAbonati()) {
                                if (a.getIdAbonat() == idAbonatImprumumut) {
                                    abimprumut = a;
                                }
                            }
                            Imprumut impr = new Imprumut();
                            Carte imprumut = Carte.imprumutaCarte(codISBNImprumut, biblioteca.getListaCarti());
//
//					for(int i=0;i<biblioteca.getListaCarti().size();i++) {
//						if(biblioteca.getListaCarti().get(i).getCodISBN().contains(imprumut.getCodISBN())) {
//							biblioteca.getListaCarti().get(i).setEsteImprumutata(true);
//						}
//					}


                            HashMap<String, Abonat> hash;
                            hash = biblioteca.getImprumut().getListaImprumuturi();
                            hash.put(imprumut.getCodISBN(), abimprumut);

                            impr.setListaImprumuturi(hash);
                            impr.setNrTotalImprumuturi(biblioteca.getImprumut().getNrTotalImprumuturi() + 1);

                            biblioteca.setImprumut(impr);

                            System.out.println("Cartea " + imprumut.getTitlu() + " a fost imprumutata");

                            System.out.println("0. Meniu principal");
                            System.out.println("2. Inapoi");
                            optSelectata = citireOptiune();

                            break;
                        case 8:
                            System.out.println("Carti imprumutate: ");
                            for (Carte c : biblioteca.getListaCarti()) {
                                if (c.isEsteImprumutata() == true)
                                    System.out.println(c.toString());
                            }
                            System.out.println("Introduceti codul ISBN al cartii returnate");
                            String codISBNRetur = scan.next();
                            codISBNRetur = codISBNRetur.toUpperCase();
                            Carte retur = Carte.returneazaCarte(codISBNRetur, biblioteca.getListaCarti());
                            biblioteca.getImprumut().getListaImprumuturi().remove(retur.getCodISBN());

                            System.out.println("Cartea " + retur.getTitlu() + " a fost returnata.");
                            System.out.println("0. Meniu principal");
                            System.out.println("2. Inapoi");
                            optSelectata = citireOptiune();
                            break;
                        case 9:
                            optSelectata = 0;
                            break;
                    }

                    break;

                case 3:
                    System.out.println("1. Lista abonati. ");
                    System.out.println("2. Adaugare abonat nou.");
                    System.out.println("3. Dezabonare.");

                    int optiuneAbonati = citireOptiune();
                    switch (optiuneAbonati) {
                        case 1:
                            for (Abonat a : biblioteca.getAbonati()) {
                                System.out.println(a.toString());
                            }
                            System.out.println("0. Meniu principal");
                            System.out.println("3. Inapoi");
                            optSelectata = citireOptiune();
                            break;
                        case 2:
                            Abonat abonatnou = new Abonat();
                            abonatnou.setIdAbonat(Biblioteca.idAbonat + 1);
                            Biblioteca.idAbonat ++;
                            System.out.println("Numele abonatului:");
                            abonatnou.setNumeAbonat(scan.next());
                            System.out.println("Prenumele abonatului:");
                            abonatnou.setPrenumeAbonat(scan.next());
                            System.out.println("Email abonat:");
                            abonatnou.setEmail(scan.next());
                            System.out.println("Telefon abonat: ");
                            abonatnou.setTelefon(scan.next());
                            biblioteca.getAbonati().add(abonatnou);
                            System.out.println("Abonatul a fost adaugat\n");
                            System.out.println("0. Meniu principal");
                            System.out.println("3. Inapoi");
                            optSelectata = citireOptiune();
                            break;
                        case 3:
                            ArrayList<Abonat> abnoi = new ArrayList<>();
                            System.out.println("Introduceti id-ul abonatului pentru dezabonare:");
                            for (Abonat a : biblioteca.getAbonati()) {
                                System.out.println(a.toString());
                            }
                            int abonatDeSters = citireOptiune();
                            for (Abonat a : biblioteca.getAbonati()) {
                                if (a.getIdAbonat() == abonatDeSters) {
//							abnoi.add(a);
                                    biblioteca.getAbonati().remove(a);
                                    break;
                                }
                            }
//					biblioteca.setAbonati(abnoi);
                            System.out.println("Abonat sters");
                            System.out.println("0. Meniu principal");
                            System.out.println("3. Inapoi");
                            optSelectata = citireOptiune();
                            break;
                    }

//				System.out.println("0. Meniu principal");
//				optSelectata = scan.nextInt();

                    break;
                case 4:
                    System.out.println(biblioteca.getImprumut().toStringInformatii(biblioteca.getListaCarti()));
                    System.out.println("0. Meniu principal");
                    optSelectata = citireOptiune();
                    break;
                case 5:
                    int nrCartiImprumutate = 0;
                    int nrCartiDisponibile = 0;
                    for (Carte c : biblioteca.getListaCarti()) {
                        if (c.isEsteImprumutata() == true)
                            nrCartiImprumutate++;
                        else
                            nrCartiDisponibile++;
                    }
                    System.out.println("In aplicatie exista " + biblioteca.getListaCarti().size() + " carti.");
                    System.out.println("Dintre acestea, " + nrCartiImprumutate + " sunt imprumutate si " + nrCartiDisponibile + " sunt disponibile");
                    System.out.println("Sunt inregistrati " + biblioteca.getAbonati().size() + " abonati");
                    System.out.println("Pana in prezent s-au realizat " + biblioteca.getImprumut().getNrTotalImprumuturi() + " imprumuturi.");
                    File f = new File("raportBiblioteca.txt");
                    FileWriter fw = null;
                    BufferedWriter bf = null;
                    try {
                        fw = new FileWriter(f);
                        bf = new BufferedWriter(fw);

                        bf.write("In aplicatie exista " + biblioteca.getListaCarti().size() + " carti.\n "
                                + "Dintre acestea, " + nrCartiImprumutate + " sunt imprumutate si " + nrCartiDisponibile + " sunt disponibile\n"
                                + "Sunt inregistrati " + biblioteca.getAbonati().size() + " abonati\n"
                                + "Pana in prezent s-au realizat " + biblioteca.getImprumut().getNrTotalImprumuturi() + " imprumuturi.\n"
                        );

                        bf.close();
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("0. Meniu principal");
                    optSelectata = citireOptiune();
                    break;

                case 6:
                    System.out.println("Aplicatia s-a inchis");
                    biblioteca.printFisier("date.txt");
                    repeta = false;
                    break;

                default:
                    System.out.println("Introduceti un numar in range-ul potrivit");
                    optSelectata = citireOptiune();

            }
        }
    }

    public static void main(String[] args) {

//        Carte c1 = new Carte("Don Quixote", "Miguel de Cervantes", "AIC1", Categorie.ADVENTURE, false);
//        Carte c2 = new Carte("Dracula", "Bram Stoker", "FIB1", Categorie.HORROR, false);
//        Carte c3 = new Carte("Cei Trei Muschetari", "Alexandre Dumas", "FID2", Categorie.ADVENTURE, false);
//        Carte c4 = new Carte("Dune", "Frank Herbert", "AID1", Categorie.SF, false);
//        Carte c5 = new Carte("Fahrenheit 451", "Ray Bradbury", "EID2", Categorie.SF, false);
//        Carte c6 = new Carte("Harry Potter și Camera Secretelor", "J. K. Rowling", "AIB1", Categorie.ACTION, false);
//        Carte c7 = new Carte("Game of Thrones", "J. K. Rowling", "EAM7", Categorie.ACTION, false);
//
//        ArrayList<Carte> listaCarti = new ArrayList<Carte>();
//        listaCarti.add(c1);
//        listaCarti.add(c2);
//        listaCarti.add(c3);
//        listaCarti.add(c4);
//        listaCarti.add(c5);
//        listaCarti.add(c6);
//        listaCarti.add(c7);
//
//
//        Abonat a1 = new Abonat(1, "Varlan", "Claudiu", "claudiu.varlan69@gmail.com", "0770299002");
//        Abonat a2 = new Abonat(2, "Dumitrache", "Gigel", "gigel@gmail.com", "0772024334");
//
//
//        ArrayList<Abonat> abonati = new ArrayList<>();
//        abonati.add(a1);
//        abonati.add(a2);
//
//
//        HashMap<String, Integer> map = new HashMap<>();
//        for (int i = 0; i < listaCarti.size(); i++) {
//            map.put(listaCarti.get(i).getCodISBN(), i);
//        }
//
//
//        HashMap<String, Abonat> listaImprumuturi = new HashMap<>();
//        for(int j=0;j<listaImprumuturi.size();j++) {
//            listaImprumuturi.put("FID2", a1);
//        }
//
//        Imprumut impr = new Imprumut(listaImprumuturi, 0);
////        System.out.println(impr.getListaImprumuturi());
////        System.out.println(impr.toStringFisier());
//
//
//       Biblioteca b = new Biblioteca("Biblioteca Va Urechia", "Str. Mihai Bravu Nr. 16 Galați", "vaurechia@gmail.com","0236411037",
//               10, listaCarti, map, impr, abonati);
////        //Biblioteca b2 = new Biblioteca("Bibl2", "Ramnicul Sarat", "bibl2@gmail.com", "02771", 10, listaCarti, map);
//
////        c1.setCodISBN("AIC2", listaCarti);
//
//        b.printFisier("date.txt");
////        System.out.println(b);
//
//        b.setAbonati(abonati);
////        b.printFisier("date2.txt");
////        Biblioteca bibliotecaCitita = Biblioteca.citireFisier("date.txt");
////        System.out.println(bibliotecaCitita);
////        System.out.println(bibliotecaCitita.getListaCarti());
////        System.out.println(bibliotecaCitita.getLocalizare());
////        for (Categorie c : Categorie.values()) {
////            System.out.println(c.ordinal() + " " + c.name());
////        }

        Biblioteca b =
        Biblioteca.citireFisier("date.txt");
        meniu(b);
    }
}
