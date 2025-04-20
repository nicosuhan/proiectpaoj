package model.produs;

public class Vegetarian extends CategorieProdus {
    private String origineCulturala;
    private int timpMediuPreparare;
    private String nivelCalorii;

    public Vegetarian() {
        super(3, "Vegetarian", "Mâncare sănătoasă fără carne.");
        this.origineCulturala = "Internațională";
        this.timpMediuPreparare = 15;
        this.nivelCalorii = "Mediu";
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
