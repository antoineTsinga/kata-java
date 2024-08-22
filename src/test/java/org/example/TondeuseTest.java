package org.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TondeuseTest {

    private Tondeuse tondeuse;
    private Pelouse pelouse;
    private Map<Character, MovementStrategy> strategies;

    @BeforeEach
    void setUp() {
        pelouse = new Pelouse(5, 5);
        strategies = new HashMap<>();
        strategies.put('G', Position::rotateLeft);
        strategies.put('D', Position::rotateRight);
        strategies.put('A', Position::moveForward);
        tondeuse = new Tondeuse(new Position(1, 2, 'N'), pelouse, strategies);
    }

    @Test
    void testExecuteInstructions() {
        tondeuse.executeInstructions("GAGAGAGAA");
        Position finalPosition = tondeuse.getPosition();
        assertEquals(1, finalPosition.getX());
        assertEquals(3, finalPosition.getY());
        assertEquals('N', finalPosition.getOrientation());
    }

    @Test
    void testExecuteInstructionsOutOfBounds() {
        tondeuse = new Tondeuse(new Position(5, 5, 'N'), pelouse, strategies);
        tondeuse.executeInstructions("A");
        Position finalPosition = tondeuse.getPosition();
        assertEquals(5, finalPosition.getX());
        assertEquals(5, finalPosition.getY());
        assertEquals('N', finalPosition.getOrientation());
    }
}
