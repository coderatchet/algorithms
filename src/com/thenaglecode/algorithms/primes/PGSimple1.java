package com.thenaglecode.algorithms.primes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/25/13
 * Time: 4:01 PM
 * Algorithm translated from http://en.wikibooks.org/wiki/Efficient_Prime_Number_Generating_Algorithms
 */
public class PGSimple1 extends PrimeGenerator {

    public static final String ALGORITHM_NAME = "PGSimple1";

    /**
     * prevent ctor
     */
    public PGSimple1() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Long> generate(long lim) {
        List<Long> ps = new ArrayList<Long>();
        long pp = 2l;
        ps.add(pp);
        while(pp < lim){
            pp++;
            boolean addMe = true;
            for(long a : ps){
                if(pp%a == 0){
                    addMe = false;
                    break;
                }
            }
            if(addMe) ps.add(pp);
        }
        return ps;
    }

    @Override
    public String getName() {
        return ALGORITHM_NAME;
    }
}
