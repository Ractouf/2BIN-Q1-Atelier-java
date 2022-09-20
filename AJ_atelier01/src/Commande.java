import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Commande implements Iterable<LigneDeCommande> {
    private static int numeroSuivant = 1;
    private int numero;
    private Client client;
    private LocalDateTime date;
    private ArrayList<LigneDeCommande> lignesDeCommande;

    public Commande(Client client) {
        if (client.getCommandeEnCours() != null) {
            throw new IllegalArgumentException("impossible de créer une commande pour un client ayant encore une commande en cours");
        }

        this.client = client;
        this.date = LocalDateTime.now();
        this.numero = numeroSuivant;
        numeroSuivant ++;

        lignesDeCommande = new ArrayList<>();

        client.enregistrer(this);
    }

    public int getNumero() {
        return numero;
    }
    public Client getClient() {
        return client;
    }
    public LocalDateTime getDate() {
        return date;
    }

    public boolean ajouter(Pizza pizza, int quantite) {
        if (client.getCommandeEnCours() != this) {
            return false;
        }

        for (LigneDeCommande ligne : lignesDeCommande) {
            if (ligne.getPizza() == pizza) {
                ligne.setQuantite(ligne.getQuantite() + quantite);
                return true;
            }
        }

        lignesDeCommande.add(new LigneDeCommande(pizza, quantite));
        return true;
    }

    public boolean ajouter(Pizza pizza) {
        return ajouter(pizza, 1);
    }

    public double calculerMontantTotal() {
        double total = 0;
        for (LigneDeCommande ligne : lignesDeCommande) {
            total += ligne.calculerPrixTotal();
        }
        return total;
    }

    public String detailler() {
        String details = "";
        for (LigneDeCommande ligne : lignesDeCommande) {
            details += ligne.toString();
        }
        return details;
    }

    @Override
    public Iterator<LigneDeCommande> iterator() {
        return lignesDeCommande.iterator();
    }

    @Override
    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String encours = "";
        if (client.getCommandeEnCours() == this)
            encours = " (en cours)";
        return "Commande n° " + numero + encours + " du " + client + "\ndate : " + formater.format(date);
    }
}
