package org.kurodev.progfrog.script.stack;

import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.game.entity.Frog;
import org.kurodev.progfrog.game.entity.FrogEntity;

import java.util.List;

public class InterceptedFrog extends FrogEntity implements Frog {
    private final ProgFrogGame game;
    private final List<MethodCall> callstack;

    /**
     * Just so if someone tries to get a sneak peek into the code structure by using the class instance.
     */

    @SuppressWarnings("unused")
    private final String stopTryingToBreakThingsYouCheater = "Please :(";

    /**
     * Creates a proxy object of the frog.
     */
    public InterceptedFrog(ProgFrogGame game, List<MethodCall> callstack) {
        super(game.getFrog());
        this.game = game;
        this.callstack = callstack;
    }

    @Override
    public void move() {
        callstack.add(MethodCall.of("move", game.getFrog()::move));
        super.move();
    }

    @Override
    public void turn() {
        callstack.add(MethodCall.of("turn", game.getFrog()::turn));
        super.turn();
    }

    @Override
    public boolean peek() {
        callstack.add(MethodCall.of("peek", game.getFrog()::peek));
        return super.peek();
    }

    @Override
    public boolean checkFood() {
        callstack.add(MethodCall.of("checkFood", game.getFrog()::checkFood));
        return super.checkFood();
    }

    @Override
    public void eat() {
        callstack.add(MethodCall.of("eat", game.getFrog()::eat));
        super.eat();
    }

    @Override
    public void drop() {
        callstack.add(MethodCall.of("drop", game.getFrog()::drop));
        super.drop();
    }
}
