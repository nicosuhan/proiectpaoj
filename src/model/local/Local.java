package model.local;

public class Local {
    private int id;
    private String numeLocal;
    private String adresa;
    private Meniu meniu;

    public Local(int id, String numeLocal, String adresa, Meniu meniu) {
        this.id = id;
        this.numeLocal = numeLocal;
        this.adresa = adresa;
        this.meniu = meniu;
    }

    public int getId() {
        return id;
    }

    public String getNumeLocal() {
        return numeLocal;
    }

    public String getAdresa() {
        return adresa;
    }

    public Meniu getMeniu() {
        return meniu;
    }

    public void afiseazaDetalii() {
        System.out.println("Local: " + numeLocal + " | Adresă: " + adresa);
        meniu.afiseazaProduse();
    }
}
