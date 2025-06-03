package model.comanda;

import model.produs.Produs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Comanda {
    protected int id;
    protected int utilizatorId;
    public String numeClient;
    protected List<Produs> produse;
    protected LocalDateTime dataComanda;
    protected double total;
    protected String status; // ex: plasata, in procesare, livrata

    public Comanda(int id, int utilizatorId) {
        this.id = id;
        this.utilizatorId = utilizatorId;
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

    public int getUtilizatorId() {
        return utilizatorId;
    }
    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public void setTotal(double recalculat) {
        this.total = recalculat;
    }
}
