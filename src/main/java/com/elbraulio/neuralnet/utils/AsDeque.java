package com.elbraulio.neuralnet.utils;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class AsDeque <T> {

    private final T initial;

    public AsDeque(T initial) {
        this.initial = initial;
    }

    public Deque<T> deque() {
        final Deque<T> deque = new ArrayDeque<>();
        deque.add(initial);
        return deque;
    }
}
