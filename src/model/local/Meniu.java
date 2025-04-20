package model.local;

import model.produs.Produs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Meniu {
    private int id;
    private String nume;
    private List<Produs> produse;

    public Meniu(int id, String nume) {
        this.id = id;
        this.nume = nume;
        this.produse = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public void adaugaProdus(Produs produs) {
        produse.add(produs);
    }

    public void afiseazaProduse() {
        System.out.println("=== Produse în " + nume + " ===");
        for (Produs produs : produse) {
            System.out.println(produs);
        }
    }
    //Metoda de sortare după preț (crescător)
    public void sorteazaProduseDupaPret() {
        produse.sort(Comparator.comparingDouble(Produs::getPret));
        System.out.println("=== Produse sortate după preț în " + nume + " ===");
        for (Produs produs : produse) {
            System.out.println(produs);
        }
    }

    //Metoda de filtrare după disponibilitate
    public void filtreazaProduseDisponibile() {
        System.out.println("=== Produse disponibile în " + nume + " ===");
        for (Produs produs : produse) {
            if (produs.isDisponibil()) {
                System.out.println(produs);
            }
        }
    }
}
