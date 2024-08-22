package org.example;


import java.util.Map;

public class Tondeuse {
    private Position position;
    private final Pelouse pelouse;
    private final Map<Character, MovementStrategy> strategies;

    public Tondeuse(Position position, Pelouse pelouse,Map<Character, MovementStrategy> strategies) {
        this.position = position;
        this.pelouse = pelouse;
        this.strategies = strategies;
    }

    public void executeInstructions(String instructions) {
        for (char instruction : instructions.toCharArray()) {
            MovementStrategy strategy = strategies.get(instruction);

            if (strategy == null) continue;

            Position potentialPosition = position.copy();
            strategy.execute(potentialPosition);
            if (pelouse.isWithinBounds(potentialPosition)) {
                this.position = potentialPosition;
            }

        }
    }

    public Position getPosition() {
        return position;
    }
}
