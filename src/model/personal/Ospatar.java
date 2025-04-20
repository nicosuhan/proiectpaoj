package model.personal;

public class Ospatar {
    private int id;
    private String nume;
    private int experientaAni;
    private String tura; // ex: "zi", "noapte"

    public Ospatar(int id, String nume, int experientaAni, String tura) {
        this.id = id;
        this.nume = nume;
        this.experientaAni = experientaAni;
        this.tura = tura;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public int getExperientaAni() {
        return experientaAni;
    }

    public String getTura() {
        return tura;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setExperientaAni(int experientaAni) {
        this.experientaAni = experientaAni;
    }

    public void setTura(String tura) {
        this.tura = tura;
    }

    @Override
    public String toString() {
        return "Ospătar: " + nume + " (ID: " + id + ", Experiență: " + experientaAni + " ani, Tura: " + tura + ")";
    }
}
