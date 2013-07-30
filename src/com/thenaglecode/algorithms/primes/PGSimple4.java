package com.thenaglecode.algorithms.primes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/26/13
 * Time: 11:13 AM
 * Algorithm translated from http://en.wikibooks.org/wiki/Efficient_Prime_Number_Generating_Algorithms
 */
public class PGSimple4 extends PrimeGenerator {

    private static final String ALGORITHM_NAME = "PGSimple4";

    /**
     * {@inheritDoc}
     */
    @Override
    public PrimeList generate(long lim) {
        long pp = 2;
        List<Long> ep = new LinkedList<Long>();
        ep.add(pp);
        pp++;
        List<Long> tp = new LinkedList<Long>();
        tp.add(pp);
        List<Long> ss = new ArrayList<Long>();
        ss.add(2l);
        while(pp < lim){
            pp += ss.get(0);
            boolean test = true;
            double sqrtpp = Math.sqrt(pp);
            for (long a : tp){
                if(a < sqrtpp) break;
                if (pp % a == 0){
                    test = false;
                    break;
                }
            }
            if(test) tp.add(pp);
        }
        Collections.reverse(ep);
        for(long a : ep){
            tp.add(0, a);
        }
        return new PrimeList(tp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return ALGORITHM_NAME;
    }
}
