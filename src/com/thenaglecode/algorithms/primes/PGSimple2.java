package com.thenaglecode.algorithms.primes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/26/13
 * Time: 9:06 AM
 * Algorithm translated from http://en.wikibooks.org/wiki/Efficient_Prime_Number_Generating_Algorithms
 */
public class PGSimple2 extends PrimeGenerator {

    private static final String ALGORITHM_NAME = "PGSimple2";

    @Override
    public List<Long> generate(long lim) {
        long pp = 2;
        List<Long> ps = new ArrayList<Long>();
        ps.add(pp);
        pp++;
        ps.add(pp);
        while (pp < lim){
            pp += 2;
            boolean test = true;
            for(long a : ps){
                if (pp % a == 0 ) test = false;
                break;
            }
            if(test) ps.add(pp);
        }
        return ps;
    }

    @Override
    public String getName() {
        return ALGORITHM_NAME;
    }
}
