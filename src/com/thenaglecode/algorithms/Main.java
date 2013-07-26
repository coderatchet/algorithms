package com.thenaglecode.algorithms;

import com.thenaglecode.algorithms.primes.PrimeGenerator;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

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


        DateTime now = new DateTime();
        for(long number : PrimeGenerator.getGenerator().generate(upTo)){
            System.out.print(number + ",");
        }
        DateTime after = new DateTime();
        Period period = new Period(now, after);
        PeriodFormatter HHMMSSMMMFormatter = new PeriodFormatterBuilder()
                .printZeroAlways()
                .minimumPrintedDigits(2)
                .appendHours().appendSeparator("-")
                .appendMinutes().appendSeparator("-")
                .appendSeconds().appendSeparator(".")
                .appendMillis()
                .toFormatter();

        System.out.println("\nUsing algorithm: " + PrimeGenerator.getGenerator().getName());
        System.out.println("Primes up to " + upTo);
        System.out.println("Took " + HHMMSSMMMFormatter.print(period) + " to generate");
    }
}
