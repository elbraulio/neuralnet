package com.elbraulio.neuralnet.utils;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class Normalize extends Number {

    private final Number x;
    private final Number dl;
    private final Number dh;
    private final Number nl;
    private final Number nh;

    public Normalize(Number x, Number dl, Number dh, Number nl, Number nh) {

        this.x = x;
        this.dl = dl;
        this.dh = dh;
        this.nl = nl;
        this.nh = nh;
    }

    @Override
    public int intValue() {
        return (this.x.intValue() - this.dl.intValue())*
                (nh.intValue()-nl.intValue())/
                (dh.intValue()-dl.intValue()) + nl.intValue();
    }

    @Override
    public long longValue() {
        return (this.x.longValue() - this.dl.longValue())*
                (nh.longValue()-nl.longValue())/
                (dh.longValue()-dl.longValue()) + nl.longValue();
    }

    @Override
    public float floatValue() {
        return (this.x.floatValue() - this.dl.floatValue())*
                (nh.floatValue()-nl.floatValue())/
                (dh.floatValue()-dl.floatValue()) + nl.floatValue();
    }

    @Override
    public double doubleValue() {
        return (this.x.doubleValue() - this.dl.doubleValue())*
                (nh.doubleValue()-nl.doubleValue())/
                (dh.doubleValue()-dl.doubleValue()) + nl.doubleValue();
    }
}
