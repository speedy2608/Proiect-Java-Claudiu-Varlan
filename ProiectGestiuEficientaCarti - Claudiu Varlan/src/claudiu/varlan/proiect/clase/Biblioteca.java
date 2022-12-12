package claudiu.varlan.proiect.clase;

import claudiu.varlan.proiect.categorii.Categorie;
import claudiu.varlan.proiect.exceptii.ExceptieGenerala;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Biblioteca  {
    public static int idAbonat = 0;
    private String nume;
    private String adresa;
    private String email;
    private String telefon;
    private int nrAbonati;
    private ArrayList<Carte> listaCarti;
    private HashMap<String, Integer> localizare;
    private Imprumut imprumut;
    private ArrayList<Abonat> abonati;

    public Biblioteca(String nume, String adresa, String email, String telefon, int nrAbonati, ArrayList<Carte> listaCarti, HashMap<String, Integer> localizare, Imprumut imprumut, ArrayList<Abonat> abonati) {
        this.nume = nume;
        this.adresa = adresa;
        this.email = email;
        this.telefon = telefon;
        this.nrAbonati = nrAbonati;
        this.listaCarti = listaCarti;
        this.localizare = localizare;
        this.imprumut = imprumut;
        this.abonati = abonati;
    }

    public Biblioteca() {

    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) throws ExceptieGenerala {
        if (nume == null) {
            throw new ExceptieGenerala("Numele bibliotecii nu poate fi null");
        } else {
            if (nume.length() < 3) {
                throw new ExceptieGenerala("Denumirea nu poate fi mai mica de trei caractere");
            } else
                this.nume = nume;
        }
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) throws ExceptieGenerala {
        if (adresa == null) {
            throw new ExceptieGenerala("Adresa bibliotecii nu poate fi null");
        } else {
            if (adresa.length() < 3) {
                throw new ExceptieGenerala("Adresa nu poate fi mai mica de trei caractere");
            } else
                this.adresa = adresa;
        }

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ExceptieGenerala {
        if (email == null) {
            throw new ExceptieGenerala("Adresa de email nu poate fi null");
        } else {
            if (email.length() < 3) {
                throw new ExceptieGenerala("Adresa de email nu poate fi mai mica de trei caractere");
            } else {
                if (email.contains("@")) {
                    this.email = email;
                } else {
                    throw new ExceptieGenerala("Email invalid.");
                }
            }
        }
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) throws ExceptieGenerala {
        if (telefon == null) {
            throw new ExceptieGenerala("Numarul de telefon nu poate fi null");
        } else {
            if (telefon.length() < 10 || telefon.length() > 10) {
                System.out.println(telefon);
                System.out.println(telefon.length());
                throw new ExceptieGenerala("Numarul de telefon trebuie sa contina exact 10 cifre");
            } else
                this.telefon = telefon;
        }
    }

    public int getNrAbonati() {
        return nrAbonati;
    }

    public void setNrAbonati(int nrAbonati) throws ExceptieGenerala {
        if (nrAbonati < 0) {
            throw new ExceptieGenerala("Nu puteti seta un nr negativ de abonati");
        }
        this.nrAbonati = nrAbonati;
    }

    public ArrayList<Carte> getListaCarti() {
        return listaCarti;
    }

    public void setListaCarti(ArrayList<Carte> listaCarti) {
        this.listaCarti = listaCarti;
    }

    public int nrCartiDisponibile() {
        int nrCarti = 0;
        for (Carte c : listaCarti) {
            if (c.isEsteImprumutata() == false) {
                nrCarti++;
            }
        }
        return nrCarti;
    }

    public HashMap<String, Integer> getLocalizare() {
        return localizare;
    }

    public void setLocalizare(HashMap<String, Integer> localizare) {
        this.localizare = localizare;
    }

    public Imprumut getImprumut() {
        return imprumut;
    }

    public void setImprumut(Imprumut imprumut) {
        this.imprumut = imprumut;
    }

    public ArrayList<Abonat> getAbonati() {
        return abonati;
    }

    public void setAbonati(ArrayList<Abonat> abonati) {
        this.abonati = abonati;
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", nrAbonati=" + nrAbonati +
                ", listaCarti= " + listaCarti.size() +
                " carti, dintre care " + this.nrCartiDisponibile() +
                " sunt disponibile." +
                '}';
    }

    public void printFisier(String numeFisier) {
        File f = new File(numeFisier);

        FileWriter fw = null;
        BufferedWriter bf = null;
        try {
            fw = new FileWriter(f);
            bf = new BufferedWriter(fw);

            bf.write(this.toStringScriereFisier());

            bf.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Biblioteca citireFisier(String numeFisier) {
        Biblioteca b = new Biblioteca();

        try {
            File f = new File(numeFisier);

            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            String[] v = line.split(", ");
            b.setNume(v[0]);
            b.setAdresa(v[1]);
            b.setEmail(v[2]);
            b.setTelefon(v[3]);
            b.setNrAbonati(Integer.parseInt(v[4]));

            ArrayList<Carte> listaCarti = new ArrayList<Carte>();
            int nrCarti = Integer.parseInt(br.readLine());
            for (int i = 0; i < nrCarti; i++) {
                if ((line = br.readLine()) != null) {
                    String[] a = line.split(", ");
                    Carte carte = new Carte();
                    carte.setCodISBN(a[0], null);
                    carte.setTitlu(a[1]);
                    carte.setAutor(a[2]);
                    String categorieCarte = a[3];
//                    Categorie c = Categorie.valueOf(categorieCarte);
//                    carte.setCategorie(c);
                    if (categorieCarte.equalsIgnoreCase("ACTION")) {
                        carte.setCategorie(Categorie.ACTION);
                    } else if (categorieCarte.equalsIgnoreCase("ADVENTURE")) {
                        carte.setCategorie(Categorie.ADVENTURE);
                    } else if (categorieCarte.equalsIgnoreCase("SF")) {
                        carte.setCategorie(Categorie.SF);
                    } else if (categorieCarte.equalsIgnoreCase("HORROR")) {
                        carte.setCategorie(Categorie.HORROR);
                    } else {
                        carte.setCategorie(Categorie.MYSTERY);
                    }
                    carte.setEsteImprumutata(Boolean.parseBoolean(a[4]));
                    listaCarti.add(carte);
                }
            }
            int nrLocalizari = Integer.parseInt(br.readLine());
            HashMap<String, Integer> listaLocalizari = new HashMap<String, Integer>();
            for (int j = 0; j < nrLocalizari; j++) {
                if ((line = br.readLine()) != null) {
                    String[] c = line.split(", ");
                    String key = c[0];
                    if (Arrays.stream(c).count() > 1) {
                    int value = Integer.parseInt(c[1]);
                    listaLocalizari.put(key, value);
                    }
                }
            }
            int nrImprumuturi = Integer.parseInt(br.readLine());
            HashMap<String, Abonat> listaImprumuturi = new HashMap<String, Abonat>();
            for (int k = 0; k < nrImprumuturi; k++) {
                if ((line = br.readLine()) != null) {
                    String[] d = line.split(", ");
                    String codIsbn = d[0];
                    Abonat a = new Abonat();
                    a.setIdAbonat(Integer.parseInt(d[1]));
                    a.setNumeAbonat(d[2]);
                    a.setPrenumeAbonat(d[3]);
                    a.setEmail(d[4]);
                    a.setTelefon(d[5]);
                    listaImprumuturi.put(codIsbn, a);
                }
            }
            int nrImprumuturiTotale = Integer.parseInt(br.readLine());

            int nrAbonati = Integer.parseInt(br.readLine());
            ArrayList<Abonat> abonati = new ArrayList<>();
            for (int l = 0; l < nrAbonati; l++) {
                if ((line = br.readLine()) != null) {
                    String[] d = line.split(", ");
                    Abonat a = new Abonat();
                    a.setIdAbonat(Integer.parseInt(d[0]));
                    a.setNumeAbonat(d[1]);
                    a.setPrenumeAbonat(d[2]);
                    a.setEmail(d[3]);
                    a.setTelefon(d[4]);
                    abonati.add(a);
                }
            }
            Biblioteca.idAbonat = abonati.get(abonati.size() - 1).getIdAbonat();
            b.setImprumut(new Imprumut(listaImprumuturi, nrImprumuturiTotale));
            b.setListaCarti(listaCarti);
            b.setLocalizare(listaLocalizari);
            b.setAbonati(abonati);

            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExceptieGenerala e) {
            e.printStackTrace();
        }
        return b;
    }


    public String toStringScriereFisier() {
        StringBuffer lista = new StringBuffer();
        lista.append("\n" + listaCarti.size() + "\n");
        int i = 0;
        for (Carte c : listaCarti) {
            lista.append(c.toStringFisier());
            if (i < listaCarti.size() - 1) {
                lista.append("\n");
            }
            i++;
        }
        StringBuffer hashList = new StringBuffer();
        hashList.append("\n" + localizare.size() + "\n");
        i = 0;
        for (Map.Entry<String, Integer> entry : localizare.entrySet()) {
            hashList.append(entry.getKey() + ", " + entry.getValue());
            if (i < localizare.size() - 1) {
                hashList.append("\n");
            }
            i++;
        }

        StringBuffer listaAbonati = new StringBuffer();
        listaAbonati.append("\n" + this.abonati.size() + "\n");
        i = 0;
        for (Abonat a : this.abonati) {
            listaAbonati.append(a.toStringFisier());
            if (i < this.abonati.size() - 1) {
                listaAbonati.append("\n");
            }
            i++;
        }
        return this.nume + ", " + this.adresa + ", " + this.email + ", " + this.telefon + ", " +
                this.nrAbonati + ", " + lista.toString() + hashList.toString() + imprumut.toStringFisier() + listaAbonati.toString();
    }


}
