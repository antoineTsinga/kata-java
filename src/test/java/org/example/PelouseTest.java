package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PelouseTest {

    private Pelouse pelouse;

    @BeforeEach
    void setUp() {
        pelouse = new Pelouse(5, 5);
    }

    @Test
    void testIsWithinBounds() {
        assertTrue(pelouse.isWithinBounds(new Position(0, 0, 'N')));
        assertTrue(pelouse.isWithinBounds(new Position(5, 5, 'N')));
        assertFalse(pelouse.isWithinBounds(new Position(-1, 0, 'N')));
        assertFalse(pelouse.isWithinBounds(new Position(0, -1, 'N')));
        assertFalse(pelouse.isWithinBounds(new Position(6, 5, 'N')));
        assertFalse(pelouse.isWithinBounds(new Position(5, 6, 'N')));
    }
}