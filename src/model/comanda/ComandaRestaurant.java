package model.comanda;

import model.personal.Ospatar;
import model.produs.Produs;

public class ComandaRestaurant extends Comanda {
    private int numarMasa;
    private boolean rezervare;
    private Ospatar ospatar;

    public ComandaRestaurant(int id, int utilizatorId, int numarMasa, boolean rezervare, Ospatar ospatar) {
        super(id, utilizatorId);  // apelăm constructorul din clasa de bază Comanda
        this.numarMasa = numarMasa;
        this.rezervare = rezervare;
        this.ospatar = ospatar;
    }

    public int getNumarMasa() {
        return numarMasa;
    }

    public boolean isRezervare() {
        return rezervare;
    }

    public Ospatar getOspatar() {
        return ospatar;
    }

    @Override
    public void afiseazaComanda() {
        System.out.println("=== Comandă în Restaurant ===");
        System.out.println("ID Comandă: " + id);
        System.out.println("ID Client: " + utilizatorId); // afișăm ID-ul clientului
        System.out.println("Masă: " + numarMasa + (rezervare ? " (rezervată)" : ""));
        System.out.println("Ospătar: " + ospatar.getNume());
        System.out.println("Produse comandate:");
        for (Produs p : produse) {
            System.out.println(" - " + p);
        }
        System.out.println("Total: " + total + " lei");
        System.out.println("Status: " + status);
    }
}
