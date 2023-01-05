package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CamionImplTest {
    CamionImpl camion;
    Trajet trajet;
    Caisse caisse;
    @BeforeEach
    void setUp() {
        camion = Mockito.mock(CamionImpl.class);
        trajet = Mockito.mock(Trajet.class);
        Mockito.when(trajet.getDate()).thenReturn(LocalDate.now().plusDays(2));
        Mockito.when(trajet.calculerPoidsTotal()).thenReturn(99999.0);
        caisse = Mockito.mock(Caisse.class);
    }

    @Test
    void ajouterTrajet() {
        assertFalse(camion.ajouterTrajet(trajet));
    }
}