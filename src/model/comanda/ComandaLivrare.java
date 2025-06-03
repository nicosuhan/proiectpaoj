package model.comanda;

import model.personal.Livrator;
import model.produs.Produs;

public class ComandaLivrare extends Comanda {
    private String adresaLivrare;
    private int durataEstimataLivrare; // în minute
    private Livrator livrator;

    public ComandaLivrare(int id, int utilizatorId, String adresaLivrare, int durataEstimataLivrare, Livrator livrator) {
        super(id, utilizatorId);
        this.adresaLivrare = adresaLivrare;
        this.durataEstimataLivrare = durataEstimataLivrare;
        this.livrator = livrator;
    }

    public String getAdresaLivrare() {
        return adresaLivrare;
    }

    public void setAdresaLivrare(String adresaLivrare) {
        this.adresaLivrare = adresaLivrare;
    }

    public int getDurataEstimataLivrare() {
        return durataEstimataLivrare;
    }

    public void setDurataEstimataLivrare(int durataEstimataLivrare) {
        this.durataEstimataLivrare = durataEstimataLivrare;
    }

    public Livrator getLivrator() {
        return livrator;
    }

    public void setLivrator(Livrator livrator) {
        this.livrator = livrator;
    }

    @Override
    public void afiseazaComanda() {
        System.out.println("=== Comandă cu Livrare ===");
        System.out.println("ID Comandă: " + id);
        System.out.println("ID Utilizator (Client): " + utilizatorId);
        System.out.println("Adresă livrare: " + adresaLivrare);
        System.out.println("Livrator: " + livrator.getNume());
        System.out.println("Timp estimat: " + durataEstimataLivrare + " min");
        System.out.println("Produse comandate:");
        for (Produs p : produse) {
            System.out.println(" - " + p);
        }
        System.out.println("Total: " + total + " lei");
        System.out.println("Status: " + status);
    }

}
