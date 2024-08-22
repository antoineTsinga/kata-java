package org.example;

public class Position {
    private int x;
    private int y;
    private char orientation;

    public Position(int x, int y, char orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public void moveForward() {
        switch (orientation) {
            case 'N': y += 1; break;
            case 'E': x += 1; break;
            case 'S': y -= 1; break;
            case 'W': x -= 1; break;
        }
    }

    public void rotateLeft() {
        switch (orientation) {
            case 'N': orientation = 'W'; break;
            case 'E': orientation = 'N'; break;
            case 'S': orientation = 'E'; break;
            case 'W': orientation = 'S'; break;
        }
    }

    public void rotateRight() {
        switch (orientation) {
            case 'N': orientation = 'E'; break;
            case 'E': orientation = 'S'; break;
            case 'S': orientation = 'W'; break;
            case 'W': orientation = 'N'; break;
        }
    }

    public Position copy(){
        return new Position(this.x, this.y, this.orientation);
    }

    @Override
    public String toString() {
        return '(' +
                "x=" + x +
                ", y=" + y +
                ", orientation=" + orientation +
                ')';
    }
}
