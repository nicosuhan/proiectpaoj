package model.user;

import model.comanda.Comanda;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    private String adresa;
    private String telefon;
    private int varsta;
    private List<Comanda> comenzi; //  colecție nouă

    public Client(int id, String nume, String email, String adresa, String telefon, int varsta) {
        super(id, nume, email);
        this.adresa = adresa;
        this.telefon = telefon;
        this.varsta = varsta;
        this.comenzi = new ArrayList<>();
    }

    // Getter și Setter
    public String getAdresa() {
        return adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public int getVarsta() {
        return varsta;
    }

    public List<Comanda> getComenzi() {
        return comenzi;
    }

    //  Adăugăm o comandă în lista clientului
    public void adaugaComanda(Comanda comanda) {
        comenzi.add(comanda);
    }

    //  Afișăm toate comenzile clientului
    public void afiseazaComenzi() {
        System.out.println("=== Comenzi pentru clientul " + nume + " ===");
        if (comenzi.isEmpty()) {
            System.out.println("Nu există comenzi.");
        } else {
            for (Comanda comanda : comenzi) {
                comanda.afiseazaComanda();
                System.out.println();
            }
        }
    }

    @Override
    public String toString() {
        return "Client: " + nume +
                " | Email: " + email +
                " | Adresa: " + adresa +
                " | Telefon: " + telefon +
                " | Varsta: " + varsta;
    }
}
