package com.elbraulio.neuralnet.utils;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class Add {
    private double count;
    public Add(){
        this.count = 0d;
    }

    public void add(double add) {
        this.count = this.count + add;
    }

    public double count(){
        return this.count;
    }

    @Override
    public String toString(){
        return String.valueOf(this.count);
    }
}
