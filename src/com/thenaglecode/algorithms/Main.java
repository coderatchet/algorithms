package com.thenaglecode.algorithms;

import com.thenaglecode.algorithms.primes.PrimeGenerator;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/25/13
 * Time: 5:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args){
        long upTo = 10000000;


        for(long number : PrimeGenerator.getGenerator().generate(upTo)){
            System.out.print(number + ",");
        }
        System.out.println("\nUsing algorithm: " + PrimeGenerator.getGenerator().getName());
        System.out.println("Primes up to " + upTo);
    }
}
