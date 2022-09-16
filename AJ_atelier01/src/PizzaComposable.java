import java.time.LocalDateTime;
import java.util.ArrayList;

public class PizzaComposable extends Pizza {
    private Client createur;
    private LocalDateTime date;

    public PizzaComposable(Client createur) {
        super("Pizza composable du client " + createur.getNumero(), "Pizza de " + createur.getPrenom() + " " + createur.getNom(), new ArrayList<>());
        this.date = LocalDateTime.now();
        this.createur = createur;
    }

    public Client getCreateur() {
        return createur;
    }
    public LocalDateTime getDate() {
        return date;
    }
}
