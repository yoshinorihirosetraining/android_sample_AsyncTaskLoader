package com.gmail.yoshinorihirosetraining.asynctaskloadersample;

/**
 * Immutable State/Document Container class.
 */

public final class Model {
    private State state;
    private String result;

    public Model(State state, String result) {
        this.state = state;
        this.result = result;
    }

    public State getState() {
        return state;
    }

    public String getResult() {
        return result;
    }
}
