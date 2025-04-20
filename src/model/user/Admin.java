package model.user;

public class Admin extends User {

    public Admin(int id, String nume, String email) {
        super(id, nume, email);
    }

    @Override
    public String toString() {
        return "Admin: " + nume + " | Email: " + email;
    }
}
