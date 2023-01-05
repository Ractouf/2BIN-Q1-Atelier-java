package domaine;

import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {
    private Prix prixAucune, prixPub, prixSolde;
    private Produit produit1, produit2, equalsTest1, equalsTest2, equalsTest3, equalsTest4;

    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixPub = new Prix(TypePromo.PUB, 10);
        prixSolde = new Prix(TypePromo.SOLDE, 10);

        prixAucune.definirPrix(1, 20);
        prixAucune.definirPrix(10, 10);

        prixPub.definirPrix(3, 15);

        produit1 = new Produit("Damien", "Gucci", "Chad");
        produit2 = new Produit("Nitro", "OmegaBoss", "Mafia");
        equalsTest1 = new Produit("Nitro", "OmegaBoss", "Mafia");
        equalsTest2 = new Produit("Nitroo", "OmegaBoss", "Mafia");
        equalsTest3 = new Produit("Nitro", "OmegaBosss", "Mafia");
        equalsTest4 = new Produit("Nitro", "OmegaBoss", "Mafiaa");

        produit2.ajouterPrix(LocalDate.of(2023, 1, 1), prixAucune);
        produit2.ajouterPrix(LocalDate.of(2023, 1, 2), prixPub);
        produit2.ajouterPrix(LocalDate.of(2023, 1, 4), prixSolde);
    }

    @Test
    @DisplayName("Paramètre null ou vide - Constructeur")
    void test1() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Produit(null, null, null)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Produit("", " ", "  "))
        );
    }

    @Test
    @DisplayName("Valeurs correctes - Getteurs")
    void test3() {
        assertAll(
            () -> assertEquals("Nitro", produit2.getNom()),
            () -> assertEquals("OmegaBoss", produit2.getMarque()),
            () -> assertEquals("Mafia", produit2.getRayon()),
            () -> assertEquals(prixAucune, produit2.getPrix(LocalDate.of(2023, 1, 1)))
        );
    }

    @Test
    @DisplayName("Un des params est null - ajouterPrix")
    void test4() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> produit1.ajouterPrix(LocalDate.of(2023, 1, 1), null)),
            () -> assertThrows(IllegalArgumentException.class, () -> produit1.ajouterPrix(null, prixAucune))
        );
    }

    @Test
    @DisplayName("Lance erreur si date déjà présente - ajouterPrix")
    void test5() {
        assertThrows(DateDejaPresenteException.class, () -> produit2.ajouterPrix(LocalDate.of(2023, 1, 1), prixPub));
    }

    @Test
    @DisplayName("Verifie si ça ajouter bien - ajouterPrix")
    void test6() {
        produit1.ajouterPrix(LocalDate.of(2023, 1, 1), prixAucune);
        assertEquals(prixAucune, produit1.getPrix(LocalDate.of(2023, 1, 1)));
    }

    @Test
    @DisplayName("Lance erreur si on get un prix antérieur au prix existants - ajouterPrix")
    void test7() {
        assertThrows(PrixNonDisponibleException.class, () -> produit2.getPrix(LocalDate.of(2022, 12, 31)));
    }

    @Test
    @DisplayName("Lance erreur si on get un prix d'un produit qui n'a pas de prix - ajouterPrix")
    void test8() {
        assertThrows(PrixNonDisponibleException.class, () -> produit1.getPrix(LocalDate.of(2023, 1, 1)));
    }

    @Test
    @DisplayName("Renvoie le prix antérieur si on get in prix entre deux dates - ajouterPrix")
    void test9() {
        assertEquals(prixPub, produit2.getPrix(LocalDate.of(2023, 1, 3)));
    }

    @Test
    @DisplayName("Test equals - equals")
    void test10() {
        assertAll(
            () -> assertEquals(equalsTest1, produit2),
            () -> assertNotEquals(equalsTest1, equalsTest2),
            () -> assertNotEquals(equalsTest1, equalsTest3),
            () -> assertNotEquals(equalsTest1, equalsTest4)
        );
    }
    @Test
    @DisplayName("Test hashCode - hashCode")
    void test11() {
        assertEquals(equalsTest1.hashCode(), produit2.hashCode());
    }
}