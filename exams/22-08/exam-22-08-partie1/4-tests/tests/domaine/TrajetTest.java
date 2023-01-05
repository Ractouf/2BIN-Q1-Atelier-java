package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TrajetTest {

    Trajet trajet;
    @BeforeEach
    void setUp() {
        trajet = new Trajet("1", LocalDate.now().plusDays(10), "SFO", "LAX");
    }

    @Test
    @DisplayName("Lance erreur si null en paramètre - peutAjouter")
    void peutAjouterNull() {
        assertThrows(IllegalArgumentException.class, () -> trajet.peutAjouter(null));
    }

    @Test
    @DisplayName("Retourne false si on ajoute une caisse qui n'a pas la même ville de départ - peutAjouter")
    void peutAjouterVilleDepart() {
        assertFalse(trajet.peutAjouter(new Caisse("LAX2", LocalDate.now().plusDays(20), "LAX", "NYC", 20)));
    }

    @Test
    @DisplayName("Retourne true si on ajoute valable - peutAjouter")
    void peutAjouter() {
        assertTrue(trajet.peutAjouter(new Caisse("LAX2", LocalDate.now().plusDays(20), "SFO", "LAX", 20)));
    }
}