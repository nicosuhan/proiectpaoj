package model.comanda;

import model.personal.Ospatar;
import model.produs.Produs;


import model.personal.Ospatar;
import model.produs.Produs;

public class ComandaRestaurant extends Comanda {
    private int numarMasa;
    private boolean rezervare;
    private Ospatar ospatar;

    public ComandaRestaurant(int id, String numeClient, int numarMasa, boolean rezervare, Ospatar ospatar) {
        super(id, numeClient);
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
        System.out.println("Client: " + numeClient);
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
