package model;

import java.util.Random;

class Dado {
    private Random rand = new Random();

    public int rolar() {
        return rand.nextInt(6) + 1;
    }
}