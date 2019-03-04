package com.vaadin.starter.skeleton.exporter;

public class EventOptions {
    private boolean bubbles = false;
    private boolean cancelable = false;
    private boolean composed = false;

    public EventOptions() {}

    public EventOptions(boolean bubbles) {
        this();
        this.bubbles = bubbles;
    }

    public EventOptions(boolean bubbles, boolean cancelable,
                         boolean composed) {
        this(bubbles);
        this.bubbles = bubbles;
        this.cancelable = cancelable;
        this.composed = composed;
    }

    public boolean isBubbles() {
        return bubbles;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public boolean isComposed() {
        return composed;
    }
}
