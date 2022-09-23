import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Client implements Iterable<Commande> {
    private static int numeroSuivant = 1;
    private int numero;
    private String nom, prenom, telephone;
    private Commande commandeEnCours;
    private ArrayList<Commande> commandesPassees;

    public Client(String nom, String prenom, String telephone) {
        if (nom == null || prenom == null || telephone == null)
            throw new IllegalArgumentException();

        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;

        this.numero = numeroSuivant;
        numeroSuivant ++;
        commandesPassees = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getTelephone() {
        return telephone;
    }
    public Commande getCommandeEnCours() {
        return commandeEnCours;
    }

    public boolean enregistrer(Commande commande) {
        if (commande == null)
            throw new IllegalArgumentException();

        if (commande.getClient().getNumero() != (numero) || commandeEnCours != null || commandesPassees.contains(commande))
            return false;

        commandeEnCours = commande;
        return true;
    }

    public boolean cloturerCommandeEnCours() {
        if (commandeEnCours == null)
            return false;

        commandesPassees.add(commandeEnCours);
        commandeEnCours = null;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return numero == client.numero;
    }
    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        String toString = null;
        return "client n° " + numero + " (" + prenom + " " + nom + ", telephone : " + telephone + ")";
    }

    @Override
    public Iterator<Commande> iterator() {
        return commandesPassees.iterator();
    }
}
