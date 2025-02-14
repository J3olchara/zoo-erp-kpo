package com.application.application.domain.animals;

import com.application.application.domain.interfaces.*;


public abstract class Animal implements IAlive, IInventory {
    private int number;
    private int food;

    public Animal(int number, int food) {
        this.number = number;
        this.food = food;
    }

    @Override
    public int getFood() {
        return food;
    }

    @Override
    public int getNumber() {
        return number;
    }
}