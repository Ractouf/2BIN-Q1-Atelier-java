package domaine;

import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

import static org.junit.jupiter.api.Assertions.*;

class PrixTest {
    private Prix prixAucune, prixPub, prixSolde;
    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixSolde = new Prix(TypePromo.SOLDE, 10);
        prixPub = new Prix(TypePromo.PUB, 10);

        prixAucune.definirPrix(1,20);
        prixAucune.definirPrix(10,10);

        prixPub.definirPrix(3,15);
    }

    @Test
    @DisplayName("Test constructeur promo null")
    void testConstructeurPromoNull() {
        assertThrows(IllegalArgumentException.class, () -> new Prix(null, 10));
    }

    @Test
    @DisplayName("Test constructeur valeur promo négative")
    void testConstructeurPromoNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Prix(TypePromo.PUB, -2));
    }

    @Test
    @DisplayName("Test get type promo")
    void getTypePromo() {
        //Vérifier que le type de la promo est null lors de l’instanciation d’un prix au moyen du
        //constructeur sans paramètre
        assertNull(prixAucune.getTypePromo());
    }
    @Test
    @DisplayName("Test type correspond de la méthode get type promo")
    void getTypePromoVerifierType() {
        //Vérifier que le type de la promo correspond bien à celle passée en paramètre du
        //constructeur
        assertEquals(TypePromo.PUB, prixPub.getTypePromo());
        assertEquals(TypePromo.SOLDE, prixSolde.getTypePromo());
    }

    @Test
    @DisplayName("Test get valeur promo")
    void getValeurPromo() {
        //Vérifier que valeur de la promo est initialisée à 0 lors de l’instanciation d’un prix au
        //moyen du constructeur sans paramètre
        assertEquals(0, prixAucune.getValeurPromo());
    }

    @Test
    @DisplayName("Test get valeur promo")
    void getValeurPromoValeur() {
        //Vérifier que la valeur de la promo correspond bien à celle passée en paramètre du
        //constructeur
        assertEquals(10, prixSolde.getValeurPromo());
        assertEquals(10, prixPub.getValeurPromo());
    }

    @Test
    @DisplayName("Test definir prix")
    void definirPrix() {
        //Définir un prix de 6 euros à partir de 10 unités pour l’attribut prixAucune et vérifier que
        //l’ancien prix a été remplacé.
        prixAucune.definirPrix(10,6);
        assertEquals(6, prixAucune.getPrix(10));
    }

    @ParameterizedTest
    @DisplayName("Test paramètre valeur pour la méthode définir prix")
    @ValueSource(ints = {0, -10})
    void definirPrixValeur(int valeur) {
        //Vérifier que la méthode definirPrix lance une IllegalArgumentException si le
        //paramètre valeur est 0 ou négatif
        assertThrows(IllegalArgumentException.class, () -> prixAucune.definirPrix(10, valeur));
    }

    @ParameterizedTest
    @DisplayName("Test paramètre quantité pour la méthode definir prix")
    @ValueSource(ints = {0, -10})
    void definirPrixQuantite(int quantite) {
        //Vérifier que la méthode definirPrix lance une IllegalArgumentException si le
        //paramètre quantité est 0 ou négatif
        assertThrows(IllegalArgumentException.class, () -> prixAucune.definirPrix(quantite, 10));
    }

    @ParameterizedTest
    @DisplayName("Test get prix")
    @ValueSource(ints = {0, -10})
    void getPrix(int quantite) {
        // Vérifier que la méthode lance une IllegalArgumentException si le paramètre
        // quantité est négatif ou nul
        assertThrows(IllegalArgumentException.class, () -> prixAucune.getPrix(quantite));
    }

    @ParameterizedTest
    @DisplayName("Test get prix")
    @ValueSource(ints = {1, 5, 9})
    void getPrixTestPetit(int quantite) {
        // Testez les prix renvoyés par la méthode getPrix pour l’attribut prixAucune
        assertEquals(20, prixAucune.getPrix(quantite));
    }

    @ParameterizedTest
    @DisplayName("Test get prix")
    @ValueSource(ints = {10, 15, 20, 25})
    void getPrixTestGrand(int quantite) {
        // Testez les prix renvoyés par la méthode getPrix pour l’attribut prixAucune
        assertEquals(10, prixAucune.getPrix(quantite));
    }

    @Test
    @DisplayName("prix pour 2 unités pub")
    void testQuantiteNonAutoriseePub() {
        assertThrows(QuantiteNonAutoriseeException.class, () -> prixPub.getPrix(2));
    }

    @Test
    @DisplayName("prix pour 2 unités solde")
    void testQuantiteNonAutoriseeSolde() {
        assertThrows(QuantiteNonAutoriseeException.class, () -> prixSolde.getPrix(1));
    }
}