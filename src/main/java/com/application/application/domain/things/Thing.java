package com.application.application.domain.things;

import com.application.application.domain.interfaces.IInventory;

public class Thing implements IInventory {
    private int number;

    public Thing(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
