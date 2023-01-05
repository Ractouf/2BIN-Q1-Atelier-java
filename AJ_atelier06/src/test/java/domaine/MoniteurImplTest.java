package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoniteurImplTest {
    private Moniteur moniteur;
    private Sport sportCompetent;
    private Stage stageValide;
    @BeforeEach
    void setUp() {
        moniteur = new MoniteurImpl("Damien");
        sportCompetent = new SportStub(true);
        stageValide = new StageStub(8, sportCompetent, null);
    }

    private void amenerALEtat(int etat, Moniteur moniteur) {
        for (int i = 1; i <= etat; i++) {
            moniteur.ajouterStage(new StageStub(i, sportCompetent, null));
        }
    }

    @Test
    @DisplayName("TC1 - ajouterStage")
    void test1() {
        assertAll("TC1",
            () -> assertEquals(0, moniteur.nombreDeStages()),
            () -> assertTrue(moniteur.ajouterStage(stageValide)),
            () -> assertTrue(moniteur.contientStage(stageValide)),
            () -> assertEquals(1, moniteur.nombreDeStages())
        );
    }

    @Test
    @DisplayName("TC2 - ajouterStage")
    void test2() {
        amenerALEtat(1, moniteur);
        assertAll("TC2",
            () -> assertEquals(1, moniteur.nombreDeStages()),
            () -> assertTrue(moniteur.ajouterStage(stageValide)),
            () -> assertTrue(moniteur.contientStage(stageValide)),
            () -> assertEquals(2, moniteur.nombreDeStages())
        );
    }

    @Test
    @DisplayName("TC3 - ajouterStage")
    void test3() {
        amenerALEtat(2, moniteur);
        assertAll("TC3",
            () -> assertEquals(2, moniteur.nombreDeStages()),
            () -> assertTrue(moniteur.ajouterStage(stageValide)),
            () -> assertTrue(moniteur.contientStage(stageValide)),
            () -> assertEquals(3, moniteur.nombreDeStages())
        );
    }

    @Test
    @DisplayName("TC4 - ajouterStage")
    void test4() {
        amenerALEtat(3, moniteur);
        assertAll("TC4",
                () -> assertEquals(3, moniteur.nombreDeStages()),
                () -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(4, moniteur.nombreDeStages())
        );
    }

    @Test
    @DisplayName("TC5 - supprimerStage")
    void test5() {
        amenerALEtat(3, moniteur);
        moniteur.ajouterStage(stageValide);

        assertAll("TC5",
                () -> assertTrue(moniteur.supprimerStage(stageValide)),
                () -> assertEquals(3, moniteur.nombreDeStages())
        );
    }

    @Test
    @DisplayName("TC6 - supprimerStage")
    void test6() {
        amenerALEtat(2, moniteur);
        moniteur.ajouterStage(stageValide);

        assertAll("TC6",
                () -> assertTrue(moniteur.supprimerStage(stageValide)),
                () -> assertEquals(2, moniteur.nombreDeStages())
        );
    }

    @Test
    @DisplayName("TC7 - supprimerStage")
    void test7() {
        amenerALEtat(1, moniteur);
        moniteur.ajouterStage(stageValide);

        assertAll("TC7",
                () -> assertTrue(moniteur.supprimerStage(stageValide)),
                () -> assertEquals(1, moniteur.nombreDeStages())
        );
    }

    @Test
    @DisplayName("TC8 - supprimerStage")
    void test8() {
        moniteur.ajouterStage(stageValide);

        assertAll("TC8",
                () -> assertTrue(moniteur.supprimerStage(stageValide)),
                () -> assertEquals(0, moniteur.nombreDeStages())
        );
    }

    @Test
    @DisplayName("TC9 - ajouterStage")
    void test9() {
        amenerALEtat(3, moniteur);
        moniteur.ajouterStage(stageValide);

        assertAll("TC9",
                () -> assertFalse(moniteur.ajouterStage(stageValide)),
                () -> assertEquals(4, moniteur.nombreDeStages())
        );
    }

    @Test
    @DisplayName("TC10 - ajouterStage")
    void test10() {
        amenerALEtat(3, moniteur);
        moniteur.ajouterStage(stageValide);

        assertAll("TC10",
                () -> assertFalse(moniteur.ajouterStage(new StageStub(8, sportCompetent, null))),
                () -> assertEquals(4, moniteur.nombreDeStages())
        );
    }

    @Test
    @DisplayName("TC11 - supprimerStage")
    void test11() {
        amenerALEtat(4, moniteur);

        assertAll("TC11",
                () -> assertFalse(moniteur.supprimerStage(new StageStub(8, sportCompetent, null))),
                () -> assertEquals(4, moniteur.nombreDeStages())
        );
    }

    @Test
    @DisplayName("TC12 - ajouterStage")
    void test12() {
        amenerALEtat(4, moniteur);
        assertAll("TC12",
                () -> assertFalse(moniteur.ajouterStage(new StageStub(7, sportCompetent, new MoniteurImpl("KeMan")))),
                () -> assertEquals(4, moniteur.nombreDeStages())
        );
    }

    @Test
    @DisplayName("TC13 - ajouterStage")
    void test13() {
        amenerALEtat(4, moniteur);
        assertAll("TC13",
                () -> assertTrue(moniteur.ajouterStage(new StageStub(7, sportCompetent, moniteur))),
                () -> assertEquals(5, moniteur.nombreDeStages())
        );
    }

    @Test
    @DisplayName("TC14 - ajouterStage")
    void test14() {
        amenerALEtat(4, moniteur);
        assertAll("TC14",
                () -> assertFalse(moniteur.ajouterStage(new StageStub(7, new SportStub(false), null))),
                () -> assertEquals(4, moniteur.nombreDeStages())
        );
    }
}