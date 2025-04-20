package model.comanda;

import model.produs.Produs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Comanda {
    protected int id;
    protected String numeClient;
    protected List<Produs> produse;
    protected LocalDateTime dataComanda;
    protected double total;
    protected String status; // ex: plasata, in procesare, livrata

    public Comanda(int id, String numeClient) {
        this.id = id;
        this.numeClient = numeClient;
        this.produse = new ArrayList<>();
        this.dataComanda = LocalDateTime.now();
        this.total = 0;
        this.status = "plasata";
    }

    public void adaugaProdus(Produs produs) {
        produse.add(produs);
        total += produs.getPret();
    }

    public int getId() {
        return id;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public LocalDateTime getDataComanda() {
        return dataComanda;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract void afiseazaComanda();
}
