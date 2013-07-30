package com.thenaglecode.algorithms.primes;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/29/13
 * Time: 4:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class PrimeList implements Serializable, Iterable<Long> {

    List<Long> primes;

    public PrimeList(List<Long> primes){
        setPrimes(primes);
    }

    private void setPrimes(List<Long> primes) {
        this.primes = primes;
    }

    public List<Long> getPrimes(){
        return primes;
    }

    @Override
    public Iterator<Long> iterator() {
        return primes == null ? null : primes.iterator();
    }
}
