package main;

import model.comanda.*;
import model.personal.Livrator;
import model.personal.Ospatar;
import model.produs.*;
import model.service.ComandaService;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ComandaService comandaService = new ComandaService();
        CategorieProdus categorie = new FastFood();

        List<Comanda> listaComenzi = new ArrayList<>();
        Set<Integer> idComenzi = new HashSet<>();

        // === Produse existente ===
        Produs p1 = new Produs(1, "Cheeseburger", 15.0, 250, true, categorie);
        Produs p2 = new Produs(2, "Cartofi prăjiți", 8.0, 150, true, categorie);
        Produs p3 = new Produs(3, "Sushi Nigiri", 22.0, 180, true, new Japonez());
        Produs p4 = new Produs(4, "Ramen", 25.0, 350, true, new Japonez());
        Produs p5 = new Produs(5, "Cola 0.5L", 6.0, 500, true, categorie);

        // === Personal ===
        Livrator livrator = new Livrator(3, "Alin Varga", "masina", "Sector 3");
        Ospatar ospatar = new Ospatar(3, "Simona Crețu", 4, "seara");

        // === Comenzi ID 10–17 ===
        ComandaLivrare c1 = new ComandaLivrare(10, 1, "Strada 1", 30, livrator);
        c1.adaugaProdus(p1);
        c1.adaugaProdus(p2);

        ComandaRestaurant c2 = new ComandaRestaurant(11, 2, 2, true, ospatar);
        c2.adaugaProdus(p3);

        ComandaRestaurant c3 = new ComandaRestaurant(12, 3, 4, false, ospatar);
        c3.adaugaProdus(p4);
        c3.adaugaProdus(p5);

        ComandaLivrare c4 = new ComandaLivrare(13, 4, "Str. Zambilei 7", 20, livrator);
        c4.adaugaProdus(p2);
        c4.adaugaProdus(p5);

        ComandaRestaurant c5 = new ComandaRestaurant(14, 5, 1, false, ospatar);
        c5.adaugaProdus(p3);
        c5.adaugaProdus(p5);

        ComandaLivrare c6 = new ComandaLivrare(15, 2, "Str. Unirii 45", 25, livrator);
        c6.adaugaProdus(p4);
        c6.adaugaProdus(p1);

        ComandaLivrare c7 = new ComandaLivrare(16, 3, "Str. Viitorului 99", 28, livrator);
        c7.adaugaProdus(p1);
        c7.adaugaProdus(p5);

        ComandaRestaurant c8 = new ComandaRestaurant(17, 1, 6, true, ospatar);
        c8.adaugaProdus(p2);
        c8.adaugaProdus(p3);

        // === Salvare în DB și colectii
        List<Comanda> toateComenzile = List.of(c1, c2, c3, c4, c5, c6, c7, c8);
        for (Comanda c : toateComenzile) {
            comandaService.addComanda(c);
            listaComenzi.add(c);
            idComenzi.add(c.getId());
        }

        // === Afișare comenzi
        System.out.println("\n📦 Toate comenzile:");
        listaComenzi.forEach(Comanda::afiseazaComanda);

        System.out.println("\n🔎 Comenzi cu livrator:");
        listaComenzi.stream()
                .filter(c -> c instanceof ComandaLivrare)
                .forEach(Comanda::afiseazaComanda);

        System.out.println("\n📊 Comenzi sortate după total:");
        listaComenzi.stream()
                .sorted(Comparator.comparingDouble(Comanda::getTotal))
                .forEach(c -> System.out.println("ID: " + c.getId() + " | Total: " + c.getTotal()));

        double totalGlobal = listaComenzi.stream().mapToDouble(Comanda::getTotal).sum();
        System.out.println("\n💰 Total global: " + totalGlobal + " lei");

        System.out.println("\n🧾 Produse din comanda 10:");
        c1.getProduse().forEach(p -> System.out.println(p.getNume()));

        System.out.println("\n🔍 Comenzi care conțin Sushi Nigiri:");
        listaComenzi.stream()
                .filter(c -> c.getProduse().stream().anyMatch(p -> p.getNume().equals("Sushi Nigiri")))
                .forEach(c -> System.out.println("Comandă ID: " + c.getId()));

        // === Șterge comenzile 16 și 17 pentru a permite rerulare
        comandaService.deleteComandaById(16);
        comandaService.deleteComandaById(17);
        listaComenzi.removeIf(c -> c.getId() == 16 || c.getId() == 17);
        idComenzi.remove(16);
        idComenzi.remove(17);

        System.out.println("\n🗑️ Comenzile 16 și 17 au fost șterse.");

        System.out.println("\n📋 Comenzi rămase:");
        listaComenzi.forEach(c -> System.out.println("Comandă ID: " + c.getId()));
    }
}
