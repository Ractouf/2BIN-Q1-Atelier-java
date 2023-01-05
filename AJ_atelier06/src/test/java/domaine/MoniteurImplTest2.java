package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MoniteurImplTest2 {
    private Moniteur moniteur;
    private Sport sportCompetent;
    private Stage stageValide;
    @BeforeEach
    void setUp() {
        moniteur = new MoniteurImpl("Damien");
        sportCompetent = Mockito.mock(Sport.class);
        Mockito.when(sportCompetent.contientMoniteur(moniteur)).thenReturn(true);
        stageValide = Mockito.mock(Stage.class);
        Mockito.when(stageValide.getSport()).thenReturn(sportCompetent);
        Mockito.when(stageValide.getMoniteur()).thenReturn(null);
        Mockito.when(stageValide.getNumeroDeSemaine()).thenReturn(8);
    }

    private void amenerALEtat(int etat, Moniteur moniteur) {
        for (int i = 1; i <= etat; i++) {
            Stage stageAjoute = Mockito.mock(Stage.class);
            Mockito.when(stageAjoute.getSport()).thenReturn(sportCompetent);
            Mockito.when(stageAjoute.getMoniteur()).thenReturn(null);
            Mockito.when(stageAjoute.getNumeroDeSemaine()).thenReturn(i);
            moniteur.ajouterStage(stageAjoute);
        }
    }

    @Test
    @DisplayName("TC1 - ajouterStage")
    void test1() {
        assertAll("TC1",
            () -> assertEquals(0, moniteur.nombreDeStages()),
            () -> assertTrue(moniteur.ajouterStage(stageValide)),
            () -> assertTrue(moniteur.contientStage(stageValide)),
            () -> assertEquals(1, moniteur.nombreDeStages()),
            () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
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
            () -> assertEquals(2, moniteur.nombreDeStages()),
            () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
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
            () -> assertEquals(3, moniteur.nombreDeStages()),
            () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
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
            () -> assertEquals(4, moniteur.nombreDeStages()),
            () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    @DisplayName("TC5 - ajouterStage")
    void test5() {
        amenerALEtat(3, moniteur);
        moniteur.ajouterStage(stageValide);
        assertAll("TC5",
            () -> assertFalse(moniteur.ajouterStage(stageValide)),
            () -> assertEquals(4, moniteur.nombreDeStages()),
            () -> Mockito.verify(stageValide, Mockito.times(1)).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    @DisplayName("TC6 - ajouterStage")
    void test6() {
        amenerALEtat(4, moniteur);
        assertAll("TC6",
                () -> assertFalse(moniteur.ajouterStage(new StageStub(1, sportCompetent, null))),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide, Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }
    @Test
    @DisplayName("TC7 - ajouterStage")
    void test7() {
        amenerALEtat(4, moniteur);
        assertAll("TC7",
                () -> assertFalse(moniteur.ajouterStage(new StageStub(6, sportCompetent, new MoniteurImpl("KeMan")))),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide, Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    @DisplayName("TC8 - ajouterStage")
    void test8() {
        amenerALEtat(4, moniteur);
        assertAll("TC8",
                () -> assertTrue(moniteur.ajouterStage(new StageStub(6, sportCompetent, moniteur))),
                () -> assertEquals(5, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide, Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    @DisplayName("TC9 - ajouterStage")
    void test9() {
        assertAll("TC9",
                () -> assertFalse(moniteur.ajouterStage(new StageStub(6, new SportStub(false), null))),
                () -> assertEquals(0, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide, Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }
}