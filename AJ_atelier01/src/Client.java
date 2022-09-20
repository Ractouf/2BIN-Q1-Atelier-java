import java.util.Objects;

public class Client {
    private static int numeroSuivant = 1;
    private int numero;
    private String nom, prenom, telephone;
    private Commande commandeEnCours;

    public Client(String nom, String prenom, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;

        this.numero = numeroSuivant;
        numeroSuivant ++;
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
        if (commande.getClient().getNumero() != (numero) || commandeEnCours != null) {
            return false;
        }
        commandeEnCours = commande;
        return true;
    }

    public boolean cloturerCommandeEnCours() {
        if (commandeEnCours == null) {
            return false;
        }
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
}
