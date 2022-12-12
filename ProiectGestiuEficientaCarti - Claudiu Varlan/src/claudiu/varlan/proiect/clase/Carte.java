package claudiu.varlan.proiect.clase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import claudiu.varlan.proiect.categorii.Categorie;
import claudiu.varlan.proiect.exceptii.ExceptieGenerala;

public class Carte {
    private String titlu;
    private String autor;
    private String codISBN;
    private Categorie categorie;
    private boolean esteImprumutata;

    public Carte(String titlu, String autor, String codISBN, Categorie categorie, boolean esteImprumutata) {
        this.titlu = titlu;
        this.autor = autor;
        this.codISBN = codISBN;
        this.categorie = categorie;
        this.esteImprumutata = esteImprumutata;
    }

    public String toStringFisier() {
        return this.codISBN + ", " + this.titlu + ", " + this.autor + ", " + this.categorie.toString() + ", " + this.esteImprumutata;
    }

    public Carte() {

    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) throws ExceptieGenerala {
        if (titlu == null) {
            throw new ExceptieGenerala("Numele cartii nu poate fi null");
        } else if (titlu.length() < 3) {
            throw new ExceptieGenerala("Numele cartii nu poate fi mai mic de trei caractere");
        } else
            this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) throws ExceptieGenerala {
        if (autor == null) {
            throw new ExceptieGenerala("Numele autorului nu poate fi null");
        } else if (autor.length() < 3) {
            throw new ExceptieGenerala("Numele autorului nu poate fi mai mic de trei caractere");
        } else
            this.autor = autor;
    }

    public String getCodISBN() {
        return codISBN;
    }

    public void setCodISBN(String codISBN, ArrayList<Carte> listaCartiExistente) {
        if (listaCartiExistente != null) {
            for (Carte c : listaCartiExistente) {
                if (c.codISBN.contentEquals(codISBN)) {
                    try {
                        throw new ExceptieGenerala("Cartea cu codul ISBN " + codISBN + " deja exista: " + c.toString());
                    } catch (ExceptieGenerala e) {
                        System.out.println("Codul ISBN este deja inregistrat, doriti sa introduceti o noua valoare?");
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("1. Da");
                        System.out.println("2. Nu");
                        int raspuns = scanner.nextInt();
                        if (raspuns == 1) {
                            System.out.println("Cod nou: ");
                            String nume = scanner.next();
                            this.setCodISBN(nume, listaCartiExistente);
                        }
                    }
                } else
                    this.codISBN = codISBN;
            }
        } else
            this.codISBN = codISBN;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public static void afisareCartiCategorie(String categorie, ArrayList<Carte> carte) {
        for (Carte c : carte) {
            if (c.getCategorie().name().contains(categorie)) {
                System.out.println(c.toString());
            }
        }
    }

    public void setCategorieString(String categorieCarte) {
        if (categorieCarte.equals("ACTION")) {
            this.setCategorie(Categorie.ACTION);
        } else if (categorieCarte.equals("ADVENTURE")) {
            this.setCategorie(Categorie.ADVENTURE);
        } else if (categorieCarte.equals("SF")) {
            this.setCategorie(Categorie.SF);
        } else if (categorieCarte.equals("HORROR")) {
            this.setCategorie(Categorie.HORROR);
        } else if (categorieCarte.equals("MYSTERY")) {
            this.setCategorie(Categorie.MYSTERY);
        }
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public boolean isEsteImprumutata() {
        return esteImprumutata;
    }

    public void setEsteImprumutata(boolean esteImprumutata) {
        this.esteImprumutata = esteImprumutata;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "titlu='" + titlu + '\'' +
                ", autor='" + autor + '\'' +
                ", codISBN='" + codISBN + '\'' +
                ", categorie=" + categorie +
                "," + (esteImprumutata == true ? " esteImprumutata " : " este disponibila ") +
                '}';
    }

    public static Carte imprumutaCarte(String codISBN, ArrayList<Carte> lista) {
        for (Carte c : lista) {
            if (c.getCodISBN().contains(codISBN)) {
                c.setEsteImprumutata(true);
                return c;
            }
        }
        return null;
    }

    public static Carte returneazaCarte(String codISBN, ArrayList<Carte> lista) {
        for (Carte c : lista) {
            if (c.getCodISBN().contains(codISBN)) {
                c.setEsteImprumutata(false);
                return c;
            }
        }
        return null;
    }
}
