package com.thenaglecode.algorithms.primes;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/13/13
 * Time: 11:58 AM
 */
public class PrimeTester {

    public static final Random rand = new SecureRandom();

    private static BigInteger getRandomFermatBase(BigInteger n){
        // Rejection method

        while (true)
        {
            final BigInteger a = new BigInteger (n.bitLength(), rand);
            // must have 1 <= a < n
            if (BigInteger.ONE.compareTo(a) <= 0 && a.compareTo(n) < 0)
            {
                return a;
            }
        }
    }

    public static boolean isPrime(BigInteger n, int maxIterations){
        if (n.equals(BigInteger.ONE))
            return false;

        for (int i = 0; i < maxIterations; i++)
        {
            BigInteger a = getRandomFermatBase(n);
            a = a.modPow(n.subtract(BigInteger.ONE), n);

            if (!a.equals(BigInteger.ONE))
                return false;
        }

        return true;
    }
}
