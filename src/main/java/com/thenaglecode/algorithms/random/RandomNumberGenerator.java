package com.thenaglecode.algorithms.random;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/30/13
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RandomNumberGenerator {
    public short nextShort();
    public short nextShort(short upto);
    public int nextInt();
    public int nextInt(int upToExcluding);
    public long nextLong();
    public long nextLong(long upToExcluding);
    public float nextFloat();
    public double nextDouble();
}
