package model.produs;

public class Japonez extends CategorieProdus {
    private String origineCulturala;
    private int timpMediuPreparare;
    private String nivelCalorii;

    public Japonez() {
        super(2, "Japonez", "Preparatele rafinate din bucătăria japoneză.");
        this.origineCulturala = "Japoneză";
        this.timpMediuPreparare = 20;
        this.nivelCalorii = "Scăzut";
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
