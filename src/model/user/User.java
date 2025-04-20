package model.user;

public abstract class User {
    protected int id;
    protected String nume;
    protected String email;

    public User(int id, String nume, String email) {
        this.id = id;
        this.nume = nume;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User: " + nume + " (" + email + ")";
    }
}
