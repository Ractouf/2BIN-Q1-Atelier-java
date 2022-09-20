import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PizzaComposable that)) return false;
        if (!super.equals(o)) return false;
        return createur.equals(that.createur) && date.equals(that.date);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), createur, date);
    }

    @Override
    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return super.toString() + "\nPizza créée le " + formater.format(date);
    }
}
