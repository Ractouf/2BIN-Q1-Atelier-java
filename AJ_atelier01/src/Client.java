import java.util.Objects;

public class Client {
    private static int numeroSuivant = 1;
    private int numero;
    private String nom, prenom, telephone;

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
}
