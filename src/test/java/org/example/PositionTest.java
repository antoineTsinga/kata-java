package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PositionTest {

    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(0, 0, 'N');
    }

    @Test
    void testMoveForward() {
        position.moveForward();
        assertEquals(0, position.getX());
        assertEquals(1, position.getY());

        position.setOrientation('E');
        position.moveForward();
        assertEquals(1, position.getX());
        assertEquals(1, position.getY());
    }

    @Test
    void testRotateLeft() {
        position.rotateLeft();
        assertEquals('W', position.getOrientation());

        position.rotateLeft();
        assertEquals('S', position.getOrientation());
    }

    @Test
    void testRotateRight() {
        position.rotateRight();
        assertEquals('E', position.getOrientation());

        position.rotateRight();
        assertEquals('S', position.getOrientation());
    }
}

