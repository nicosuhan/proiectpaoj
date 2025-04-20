package model.personal;

public class Livrator {
    private int id;
    private String nume;
    private String mijlocTransport; // ex: bicicletă, scuter, mașină
    private String zonaLivrare;     // ex: cartier, sector etc.

    public Livrator(int id, String nume, String mijlocTransport, String zonaLivrare) {
        this.id = id;
        this.nume = nume;
        this.mijlocTransport = mijlocTransport;
        this.zonaLivrare = zonaLivrare;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getMijlocTransport() {
        return mijlocTransport;
    }

    public String getZonaLivrare() {
        return zonaLivrare;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setMijlocTransport(String mijlocTransport) {
        this.mijlocTransport = mijlocTransport;
    }

    public void setZonaLivrare(String zonaLivrare) {
        this.zonaLivrare = zonaLivrare;
    }

    @Override
    public String toString() {
        return "Livrator: " + nume + " (ID: " + id + ", Transport: " + mijlocTransport + ", Zonă: " + zonaLivrare + ")";
    }
}
