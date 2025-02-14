package com.application.application.domain.animals;

public class Herbo extends Animal {
    private int kindnessLevel;

    public Herbo(int number, int food, int kindnessLevel) {
        super(number, food);
        this.kindnessLevel = kindnessLevel;
    }

    public int getKindnessLevel() {
        return kindnessLevel;
    }
}