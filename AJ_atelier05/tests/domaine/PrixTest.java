package domaine;

import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PrixTest {
    private Prix prixAucune, prixPub, prixSolde;

    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixPub = new Prix(TypePromo.PUB, 10);
        prixSolde = new Prix(TypePromo.SOLDE, 10);

        prixAucune.definirPrix(1, 20);
        prixAucune.definirPrix(10, 10);

        prixPub.definirPrix(3, 15);
    }

    @Test
    @DisplayName("Paramètre typePromo null - Constructeur")
    void test1() {
        assertThrows(IllegalArgumentException.class, () -> new Prix(null, 10));
    }

    @ParameterizedTest
    @DisplayName("Paramètre valeur <= 0 - Constructeur")
    @ValueSource(ints = {0, -10})
    void test2(int valeur) {
        assertThrows(IllegalArgumentException.class, () -> new Prix(TypePromo.PUB, valeur));
    }

    @Test
    @DisplayName("Initialisée a 0 avec constructeur sans params - GetValeurPromo")
    void test3() {
        assertEquals(0, prixAucune.getValeurPromo());
    }

    @Test
    @DisplayName("Valeur promo est la bonne - GetValeurPromo")
    void test4() {
        assertEquals(10, prixSolde.getValeurPromo());
    }

    @Test
    @DisplayName("Type promo null avec constructeur sans params - GetTypePromo")
    void test5() {
        assertNull(prixAucune.getTypePromo());
    }

    @Test
    @DisplayName("Type promo est la bonne - GetTypePromo")
    void test6() {
        assertEquals(TypePromo.PUB, prixPub.getTypePromo());
    }

    @ParameterizedTest
    @DisplayName("Lance erreur si quantité <= 0 - definirPrix")
    @ValueSource(ints = {0, -10})
    void test7(int quantite) {
        assertThrows(IllegalArgumentException.class, () -> prixAucune.definirPrix(quantite,10));
    }

    @ParameterizedTest
    @DisplayName("Lance erreur si valeur <= 0 - definirPrix")
    @ValueSource(ints = {0, -10})
    void test8(int valeur) {
        assertThrows(IllegalArgumentException.class, () -> prixAucune.definirPrix(10,valeur));
    }

    @Test
    @DisplayName("verifier definirPrix change le prix - definirPrix")
    void test9() {
        assertEquals(10, prixAucune.getPrix(10));
        prixAucune.definirPrix(10, 6);
        assertEquals(6, prixAucune.getPrix(10));
    }

    @ParameterizedTest
    @DisplayName("Lance erreur si quantité <= 0 - getPrix")
    @ValueSource(ints = {0, -10})
    void test10(int quantite) {
        assertThrows(IllegalArgumentException.class, () -> prixAucune.getPrix(quantite));
    }

    @ParameterizedTest
    @DisplayName("Tester les prix - getPrix")
    @ValueSource(ints = {1, 5, 9})
    void test11(int quantite) {
        assertEquals(20, prixAucune.getPrix(quantite));
    }

    @ParameterizedTest
    @DisplayName("Tester les prix - getPrix")
    @ValueSource(ints = {10, 15, 20, 25})
    void test12(int quantite) {
        assertEquals(10, prixAucune.getPrix(quantite));
    }

    @Test
    @DisplayName("Lance erreur si quantité en dessous de autorisé - getPrix")
    void test13() {
        assertThrows(QuantiteNonAutoriseeException.class, () -> prixPub.getPrix(2));
    }

    @Test
    @DisplayName("Lance erreur si quantité en dessous de autorisé - getPrix")
    void test14() {
        assertThrows(QuantiteNonAutoriseeException.class, () -> prixSolde.getPrix(1));
    }
}