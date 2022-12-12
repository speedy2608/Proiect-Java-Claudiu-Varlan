package claudiu.varlan.proiect.clase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Imprumut {
    private HashMap<String, Abonat> listaImprumuturi;
    private int nrTotalImprumuturi;

    public Imprumut(HashMap<String, Abonat> listaImprumuturi, int nrTotalImprumuturi) {
        this.listaImprumuturi = listaImprumuturi;
        this.nrTotalImprumuturi = nrTotalImprumuturi;
    }

    public Imprumut() {

    }

    public HashMap<String, Abonat> getListaImprumuturi() {
        return listaImprumuturi;
    }

    public void setListaImprumuturi(HashMap<String, Abonat> listaImprumuturi) {
        this.listaImprumuturi = listaImprumuturi;
    }

    public int getNrTotalImprumuturi() {
        return nrTotalImprumuturi;
    }

    public void setNrTotalImprumuturi(int nrTotalImprumuturi) {
        this.nrTotalImprumuturi = nrTotalImprumuturi;
    }

    public String toStringFisier() {
        int i = 0;
        StringBuffer lista = new StringBuffer();
        lista.append("\n" + listaImprumuturi.size() + "\n");
        for (Map.Entry<String, Abonat> entry : listaImprumuturi.entrySet()) {
            lista.append(entry.getKey() + ", " + entry.getValue().toStringFisier());
            if (i < listaImprumuturi.size()) {
                lista.append("\n");
            }
            i++;
        }
        return lista.toString() + this.nrTotalImprumuturi;
    }

    public String toStringInformatii(ArrayList<Carte> listaCarti) {
        int i = 0;
        StringBuffer lista = new StringBuffer();
        lista.append("\n Sunt imprumutate " + listaImprumuturi.size() + " carti. \n");
        lista.append("\n Imprumuturi: \n");
        for (Map.Entry<String, Abonat> entry : listaImprumuturi.entrySet()) {
            for (Carte c : listaCarti) {
                if (c.getCodISBN().contains(entry.getKey())) {
                    lista.append("Carte: " + c.toString() + "\n");
                }
            }
            lista.append("Abonat: " + entry.getValue().toStringFisier());
            if (i < listaImprumuturi.size()) {
                lista.append("\n");
            }
            i++;
        }
        return lista.toString();
    }


}
