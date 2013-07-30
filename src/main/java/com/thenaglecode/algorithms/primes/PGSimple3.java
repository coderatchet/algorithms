package com.thenaglecode.algorithms.primes;

import com.thenaglecode.algorithms.primes.PrimeGenerator;
import com.thenaglecode.algorithms.primes.PrimeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Algorithm translated from http://en.wikibooks.org/wiki/Efficient_Prime_Number_Generating_Algorithms
 */
public class PGSimple3 extends PrimeGenerator {

    private static final String ALGORITHM_NAME = "PGSimple3";

    /**
     * {@inheritDoc}
     */
    @Override
    public PrimeList generate(long lim) {
        long pp = 2;
        List<Long> ps = new ArrayList<Long>();
        pp+=1;
        ps.add(pp);
        while (pp < lim){
            pp+=2;
            boolean test = true;
            Double sqrtpp = Math.sqrt(pp);
            for (long a : ps){
                if (a > sqrtpp) break;
                if (pp%a==0){
                    test = false;
                    break;
                }
            }
            if (test) ps.add(pp);
        }
        return new PrimeList(ps);
    }

    @Override
    public String getName() {
        return ALGORITHM_NAME;
    }
}
