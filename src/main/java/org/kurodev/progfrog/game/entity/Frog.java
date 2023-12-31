package org.kurodev.progfrog.game.entity;

public interface Frog {

    void move();

    void turn();

    boolean peek();

    boolean checkFood();

    void eat();

    void drop();
}
