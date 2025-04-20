package model.produs;

public abstract class CategorieProdus {
    protected int id;
    protected String numeCategorie;
    protected String descriere;

    public CategorieProdus(int id, String numeCategorie, String descriere) {
        this.id = id;
        this.numeCategorie = numeCategorie;
        this.descriere = descriere;
    }

    public int getId() {
        return id;
    }

    public String getNumeCategorie() {
        return numeCategorie;
    }

    public String getDescriere() {
        return descriere;
    }

    @Override
    public String toString() {
        return numeCategorie + ": " + descriere;
    }
}
