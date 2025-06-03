package model.produs;

public class Produs {
    private int id;
    private String nume;
    private double pret;
    private int gramaj; // în grame
    private boolean disponibil;
    private CategorieProdus categorie;

    // Constructor cu ID (pentru când luăm produsul din DB sau îl folosim într-o comandă)
    public Produs(int id, String nume, double pret, int gramaj, boolean disponibil, CategorieProdus categorie) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
        this.gramaj = gramaj;
        this.disponibil = disponibil;
        this.categorie = categorie;
    }

    // Constructor fără ID (opțional)
    public Produs(String nume, double pret, int gramaj, boolean disponibil, CategorieProdus categorie) {
        this.nume = nume;
        this.pret = pret;
        this.gramaj = gramaj;
        this.disponibil = disponibil;
        this.categorie = categorie;
    }

    public static Produs getProdusById(int idDeTest) {
        return Produs.getProdusById(idDeTest);
    }

    // Getteri
    public int getId() {
        return id;
    }

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
    public void setId(int id) {
        this.id = id;
    }

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
