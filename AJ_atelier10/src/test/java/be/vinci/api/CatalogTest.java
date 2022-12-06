package be.vinci.api;

import be.vinci.domain.CompactDisc;
import be.vinci.services.Catalog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatalogTest {

    @Test
    void cdIsFound() {
        Catalog catalog = new Catalog();
        CompactDisc cd1 = new CompactDisc("Young Mystic", "Bob Marley", 3, 12, "666-777");
        CompactDisc cd2 = new CompactDisc("Time Out", "Dave Brubeck Quartet", 10, 111.1, "123-456");
        catalog.initCatalog(cd1, cd2);
        assertAll(
                () -> assertEquals(1, catalog.findCds("young mystic", "").size()),
                () -> assertEquals(cd1, catalog.findCds("young mystic", "").get(0))
        );
    }
}