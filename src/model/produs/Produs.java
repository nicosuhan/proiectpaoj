package model.produs;

public class Produs {
    private String nume;
    private double pret;
    private int gramaj; // în grame
    private boolean disponibil;
    private CategorieProdus categorie;

    public Produs(String nume, double pret, int gramaj, boolean disponibil, CategorieProdus categorie) {
        this.nume = nume;
        this.pret = pret;
        this.gramaj = gramaj;
        this.disponibil = disponibil;
        this.categorie = categorie;
    }

    // Getteri
    public String getNume() {
        return nume;
    }

    public double getPret() {
        return pret;
    }

    public int getGramaj() {
        return gramaj;
    }

    public boolean isDisponibil() {
        return disponibil;
    }

    public CategorieProdus getCategorie() {
        return categorie;
    }

    // Setteri
    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public void setGramaj(int gramaj) {
        this.gramaj = gramaj;
    }

    public void setDisponibil(boolean disponibil) {
        this.disponibil = disponibil;
    }

    public void setCategorie(CategorieProdus categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return nume + " - " + pret + " lei - " + gramaj + "g - " +
                (disponibil ? "Disponibil" : "Indisponibil") +
                " [" + categorie.getNumeCategorie() + "]";
    }
}
