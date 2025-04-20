package model.produs;

public class FastFood extends CategorieProdus {
    private String origineCulturala;
    private int timpMediuPreparare; // în minute
    private String nivelCalorii;

    public FastFood() {
        super(1, "Fast Food", "Mâncare rapidă, gustoasă și sățioasă.");
        this.origineCulturala = "Americană";
        this.timpMediuPreparare = 10;
        this.nivelCalorii = "Mare";
    }

    public String getOrigineCulturala() {
        return origineCulturala;
    }

    public int getTimpMediuPreparare() {
        return timpMediuPreparare;
    }

    public String getNivelCalorii() {
        return nivelCalorii;
    }

    public String descriereDetaliata() {
        return getNumeCategorie() + " (" + origineCulturala + ") - Timp mediu: " +
                timpMediuPreparare + " min - Calorii: " + nivelCalorii;
    }
}
