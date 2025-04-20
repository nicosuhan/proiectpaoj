package main;

import model.user.Admin;
import model.user.Client;

import model.produs.*;
import model.local.*;

import java.util.ArrayList;
import java.util.List;
import model.comanda.ComandaLivrare;
import model.comanda.ComandaRestaurant;
import model.personal.Livrator;
import model.personal.Ospatar;
import model.produs.*;
import model.comanda.Comanda;

public class Main {
    public static void main(String[] args) {
        // ACTIUNEA 1: Creăm userii (client și admin)
        System.out.println("=== LISTA USERI ===");
        Client client1 = new Client(1, "Ana Popescu", "ana@email.com", "Strada Florilor nr. 10", "0741234567", 28);
        Client client2 = new Client(2, "George Iacob", "george@email.com", "Strada Trandafirilor nr. 7", "0758765432", 34);
        Admin admin1 = new Admin(99, "Mihai Admin", "mihai@admin.com");
        System.out.println(client1);
        System.out.println(admin1);

// ACTIUNEA 2: Creăm categoriile de produse
        CategorieProdus fastFood = new FastFood();
        CategorieProdus japonez = new Japonez();
        CategorieProdus traditional = new Traditional();
        CategorieProdus vegetarian = new Vegetarian();

// ACTIUNEA 3: Creăm produsele
        Produs burger = new Produs("Burger", 24.99, 250, true, fastFood);
        Produs pizza = new Produs("Pizza", 29.99, 400, true, fastFood);
        Produs sushi = new Produs("Sushi cu somon", 42.50, 200, true, japonez);
        Produs ramen = new Produs("Ramen clasic", 38.00, 350, false, japonez);
        Produs sarmale = new Produs("Sarmale", 32.00, 300, true, traditional);
        Produs ciorba = new Produs("Ciorbă de burtă", 28.00, 350, true, traditional);
        Produs salata = new Produs("Salată cu tofu", 21.00, 250, true, vegetarian);
        Produs hummus = new Produs("Hummus cu legume", 18.50, 200, true, vegetarian);

// ACTIUNEA 4: Creăm meniurile și adăugăm produse în ele
        Meniu meniu1 = new Meniu(1, "Meniu Fast & Japonez");
        meniu1.adaugaProdus(burger);
        meniu1.adaugaProdus(pizza);
        meniu1.adaugaProdus(sushi);
        meniu1.adaugaProdus(ramen);

        Meniu meniu2 = new Meniu(2, "Meniu Traditional & Vegetarian");
        meniu2.adaugaProdus(sarmale);
        meniu2.adaugaProdus(ciorba);
        meniu2.adaugaProdus(salata);
        meniu2.adaugaProdus(hummus);

// COLECTIE 1: List<Produs> în fiecare meniu → sortabilă

// ACTIUNEA 5: Creăm localurile și asociem meniurile
        Local local1 = new Local(1, "Bistro Urban", "Strada Libertății 10", meniu1);
        Local local2 = new Local(2, "Casa Bunicii", "Bulevardul Revoluției 45", meniu2);

// ACTIUNEA 6: Afișăm detalii despre localuri și meniuri
        System.out.println("=== LOCALURI ȘI MENIURI ===\n");
        local1.afiseazaDetalii();
        System.out.println();
        local2.afiseazaDetalii();

// ACTIUNEA 7: Sortăm produsele din meniu după preț
        System.out.println("\n=== TEST SORTARE PRODUSE DUPĂ PREȚ ===");
        local1.getMeniu().sorteazaProduseDupaPret();

// ACTIUNEA 8: Filtrăm produsele disponibile
        System.out.println("\n=== TEST FILTRARE PRODUSE DISPONIBILE ===");
        local2.getMeniu().filtreazaProduseDisponibile();

// ACTIUNEA 9: Creăm personalul – ospătar și livrator
        Ospatar ospatar = new Ospatar(1, "Mihai Stancu", 4, "zi");
        Livrator livrator = new Livrator(2, "Alina Dobre", "bicicletă", "Sector 2");

// ACTIUNEA 10: Creăm comenzi (restaurant și livrare)
        ComandaRestaurant comandaRestaurant = new ComandaRestaurant(
                1001, client1.getNume(), 5, true, ospatar
        );
        comandaRestaurant.adaugaProdus(burger);
        comandaRestaurant.adaugaProdus(sushi);

        ComandaLivrare comandaLivrare = new ComandaLivrare(
                1002, client1.getNume(), client2.getAdresa(), 35, livrator
        );
        comandaLivrare.adaugaProdus(sarmale);
        comandaLivrare.adaugaProdus(hummus);

// ACTIUNEA 11: Afișăm comenzile create
        System.out.println("=== COMENZI ===\n");
        comandaRestaurant.afiseazaComanda();
        comandaLivrare.afiseazaComanda();

// COLECTIE 2: List<Comanda> în clasa Client → nesortată, dar gestionabilă

// ACTIUNEA 12: Adăugăm comenzile în lista clientului și afișăm istoricul
        client1.adaugaComanda(comandaRestaurant);
        client1.adaugaComanda(comandaLivrare);
        System.out.println("\n=== ISTORIC COMENZI CLIENT1 ===");
        client1.afiseazaComenzi();

// ACTIUNEA 13: Adăugăm încă două comenzi și le afișăm din nou
        ComandaRestaurant comanda2 = new ComandaRestaurant(
                1003, client1.getNume(), 7, false, ospatar
        );
        comanda2.adaugaProdus(pizza);
        comanda2.adaugaProdus(ciorba);

        ComandaLivrare comanda3 = new ComandaLivrare(
                1004, client1.getNume(), "Strada Florilor nr. 10", 25, livrator
        );
        comanda3.adaugaProdus(ramen);

        client1.adaugaComanda(comanda2);
        client1.adaugaComanda(comanda3);
        System.out.println("\n=== ISTORIC COMENZI CLIENT1 DUPĂ COMENZI NOI ===");
        client1.afiseazaComenzi();

//ACTIUNEA 14: Schimbăm mijlocul de transport al livratorului
        livrator.setMijlocTransport("mașină");
        System.out.println("Transport actualizat: " + livrator.getMijlocTransport());

// ACTIUNEA 15: Facem produsul Ramen disponibil și verificăm
        ramen.setDisponibil(true);
        System.out.println("Produsul Ramen este acum disponibil? " + ramen.isDisponibil());

// ACTIUNEA 16: Schimbăm statusul ultimei comenzi în "livrată"
        System.out.println("\n=== ACTUALIZARE STATUS COMANDĂ ===");
        comanda3.setStatus("livrată");
        comanda3.afiseazaComanda();

// ACTIUNEA 17: Afișăm descriere detaliată pentru categoria FastFood
        System.out.println("\n=== DESCRIERE DETALIATĂ CATEGORIE ===");
        System.out.println(((FastFood) fastFood).descriereDetaliata());

// ACTIUNEA 18: Afișăm informații despre livrator
        System.out.println("\n=== INFORMAȚII LIVRATOR ===");
        System.out.println(livrator);

// ACTIUNEA 19: Afișăm informații despre ospătar
        System.out.println("\n=== INFORMAȚII OSPĂTAR ===");
        System.out.println(ospatar);

//ACTIUNEA 20: Schimbăm tura ospătarului și o afișăm
        ospatar.setTura("seară");
        System.out.println("Tura actualizată pentru ospătar: " + ospatar.getTura());










    }
}
