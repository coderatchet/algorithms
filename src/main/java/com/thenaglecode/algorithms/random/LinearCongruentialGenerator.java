package com.thenaglecode.algorithms.random;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/29/13
 * Time: 5:10 PM
 */
public class LinearCongruentialGenerator implements RandomNumberGenerator {

    private static final Long M = (long) Math.pow(2, 48);
    private static final Long MASK = M-1; // we just and the last 48 bits of the next state.
    private static final Long A = 25214903917l;
    private static final Long C = 11l;
    private long state;

    public LinearCongruentialGenerator(){
        this(System.currentTimeMillis());
    }

    public LinearCongruentialGenerator(long seed){
        state = seed;
    }

    public void setSeed(long seed){
        state = seed;
    }

    private long nextState(){
       return state = (A * state + C) & MASK;
    }

    @Override
    public synchronized short nextShort() {
        return (short) nextState();
    }

    @Override
    public short nextShort(short upToExcluding) {
        return (short) (nextState() % upToExcluding);
    }

    @Override
    public synchronized int nextInt() {
        return (int) nextState();
    }

    @Override
    public synchronized int nextInt(int upToExcluding) {
        return (int) (nextState() % upToExcluding);
    }

    @Override
    public synchronized long nextLong() {
        return nextState();
    }

    @Override
    public synchronized long nextLong(long upToExcluding) {
        return nextState() % upToExcluding;
    }

    @Override
    public float nextFloat() {
        return (float) nextState() / M;
    }

    @Override
    public double nextDouble() {
        return (double) nextState() / M;
    }
}
