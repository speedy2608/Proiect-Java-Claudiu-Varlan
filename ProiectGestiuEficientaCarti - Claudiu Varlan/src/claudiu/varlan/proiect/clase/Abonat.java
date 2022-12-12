package claudiu.varlan.proiect.clase;

import java.io.Serializable;
import java.util.Scanner;

import claudiu.varlan.proiect.exceptii.ExceptieGenerala;

public class Abonat {
    private int idAbonat;
    private String numeAbonat;
    private String prenumeAbonat;
    private String email;
    private String telefon;

    public String toStringFisier() {
        return this.idAbonat + ", " + this.numeAbonat + ", " + this.prenumeAbonat + ", " + this.email + ", " + this.telefon;
    }

    public Abonat() {

    }

    public Abonat(int idAbonat, String numeAbonat, String prenumeAbonat, String email, String telefon) {
        this.idAbonat = idAbonat;
        this.numeAbonat = numeAbonat;
        this.prenumeAbonat = prenumeAbonat;
        this.email = email;
        this.telefon = telefon;
    }

    public int getIdAbonat() {
        return idAbonat;
    }

    public void setIdAbonat(int idAbonat) {
        this.idAbonat = idAbonat;
    }

    public String getNumeAbonat() {
        return numeAbonat;
    }

    public void setNumeAbonat(String numeAbonat) {
        try {
            if (numeAbonat == null) {
                throw new ExceptieGenerala("Numele nu poate fi null.");
            } else if (numeAbonat.length() < 3) {
                throw new ExceptieGenerala("Numele nu poate fi mai mic de trei caractere.");
            }
        } catch (ExceptieGenerala e) {
            System.out.println("Nume incorect. Doriti sa introduceti un alt nume?");
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Da");
            System.out.println("2. Nu");
            int raspuns = scanner.nextInt();
            if (raspuns == 1) {
                System.out.println("Nume nou: ");
                String numeNou = scanner.next();
                this.setNumeAbonat(numeNou);
            }
        }
        this.numeAbonat = numeAbonat;
    }

    public String getPrenumeAbonat() {
        return prenumeAbonat;
    }

    public void setPrenumeAbonat(String prenumeAbonat) {
        this.prenumeAbonat = prenumeAbonat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        try {
            if (email == null) {
                throw new ExceptieGenerala("Email-ul nu poate fi null.");
            } else if (email.length() < 3) {
                throw new ExceptieGenerala("Adresa de email nu poate fi mai mica de 3 caractere.");
            } else {
                if (email.contains("@")) {
                    this.email = email;
                } else {
                    throw new ExceptieGenerala("Email invalid.");
                }
            }
        } catch (ExceptieGenerala e) {
            System.out.println("Adresa de email incorecta. Doriti sa introduceti o alta adresa?");
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Da");
            System.out.println("2. Nu");
            int raspuns = scanner.nextInt();
            if (raspuns == 1) {
                System.out.println("Adresa noua: ");
                String emailNou = scanner.next();
                this.setEmail(emailNou);
            }
        }
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        try {
            if (telefon == null) {
                throw new ExceptieGenerala("Telefonul nu poate fi null.");
            } else if (telefon.length() < 10 || telefon.length() > 10) {
                throw new ExceptieGenerala("Numarul de telefon trebuie sa contina exact 10 cifre.");
            } else {
                this.telefon = telefon;
            }
        } catch (ExceptieGenerala e) {
            System.out.println(telefon);
            System.out.println(telefon.length());
            System.out.println("Numarul de telefon trebuie sa contina exact 10 cifre. Doriti sa introduceti un alt numar?");
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Da");
            System.out.println("2. Nu");
            int raspuns = scanner.nextInt();
            if (raspuns == 1) {
                System.out.println("Numar nou: ");
                String numarTelefon = scanner.next();
                this.setTelefon(numarTelefon);
            }
        }
    }

    @Override
    public String toString() {
        return "Abonat{" +
                "idAbonat=" + idAbonat +
                ", numeAbonat='" + numeAbonat + '\'' +
                ", prenumeAbonat='" + prenumeAbonat + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}

