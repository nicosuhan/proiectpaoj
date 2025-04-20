package model.produs;

public class Traditional extends CategorieProdus {
    private String origineCulturala;
    private int timpMediuPreparare;
    private String nivelCalorii;

    public Traditional() {
        super(4, "Traditional", "Mâncare tradițională românească.");
        this.origineCulturala = "Românească";
        this.timpMediuPreparare = 25;
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
