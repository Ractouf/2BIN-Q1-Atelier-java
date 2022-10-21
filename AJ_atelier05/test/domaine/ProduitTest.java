package domaine;

import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {

    private Prix prixAucune, prixPub, prixSolde;
    private Produit produit1, produit2;


    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixSolde = new Prix(TypePromo.SOLDE, 10);
        prixPub = new Prix(TypePromo.PUB, 10);

        produit1 = new Produit("Spaghetti" , "Barilla", "Pâtes");
        produit2 = new Produit("Lasagne", "Delhaize", "Surgelés");

        prixAucune.definirPrix(1,20);
        prixAucune.definirPrix(10,10);

        prixPub.definirPrix(3,15);

        produit1.ajouterPrix(LocalDate.now(), prixAucune);
        produit1.ajouterPrix(LocalDate.of(2021, 10, 21), prixSolde);
        produit1.ajouterPrix(LocalDate.of(2021, 10, 19), prixPub);
    }

    @Test
    @DisplayName("Contructeur paramètre invalide")
    void testConstructeurParametreInvalide() {
        assertThrows(IllegalArgumentException.class, () -> new Produit(null, null, null));
        assertThrows(IllegalArgumentException.class, () -> new Produit(" ", " ", " "));
    }

    @Test
    @DisplayName("Test params = résultat get")
    void testConstructeurParams() {
        assertEquals("Spaghetti", produit1.getNom());
        assertEquals("Barilla", produit1.getMarque());
        assertEquals("Pâtes", produit1.getRayon());
    }

    @Test
    @DisplayName("ajouter prix param null -> IllégalArgument")
    void testAjouterPrixParams() {
        assertThrows(IllegalArgumentException.class, () -> produit2.ajouterPrix(null, prixPub));
        assertThrows(IllegalArgumentException.class, () -> produit2.ajouterPrix(LocalDate.of(2021, 10, 20), null));
    }

    @Test
    @DisplayName("Date déjà présente")
    void testAjouterPrixDate() {
        assertThrows(DateDejaPresenteException.class, () -> produit1.ajouterPrix(LocalDate.of(2021, 10, 21), prixPub));
    }

    @Test
    @DisplayName("Test ajouter prix")
    void testAjouterPrix() {
        LocalDate date = LocalDate.of(2021, 10, 2);
        produit1.ajouterPrix(date, prixAucune);
        assertEquals(prixAucune, produit1.getPrix(date));
    }

    @Test
    @DisplayName("test pas de prix ce jour la")
    void getPrixDate() {
        assertThrows(PrixNonDisponibleException.class, () -> produit1.getPrix(LocalDate.of(2021, 10, 19)));
    }

    @Test
    @DisplayName("test get prix sans prix")
    void getPrixSansPrix() {
        assertThrows(PrixNonDisponibleException.class, () -> produit2.getPrix(LocalDate.of(2021, 10, 19)));
    }

    @Test
    @DisplayName("prix pour une date comprise entre deux dates pour lesquelles le prix a été défini")
    void getPrixDateAnterieure() {
        assertEquals(prixPub, produit1.getPrix(LocalDate.of(2021, 10, 20)));
    }

    @Test
    void testEqualsDeuxInstances() {

    }
}