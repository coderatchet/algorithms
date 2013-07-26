package com.thenaglecode.algorithms;

import com.thenaglecode.algorithms.primes.PrimeGenerator;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/25/13
 * Time: 5:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args){
        long upTo = Long.valueOf(ConfigurationManager.getInstance().getValue(Configuration.PRIME_UPTO));


        DateTime now = new DateTime();
        PrimeGenerator generator = PrimeGenerator.getGenerator();
        System.out.println("Started prime generation using algorithm: " + generator.getName() + " up to " + upTo);
        List<Long> primes = generator.generate(upTo);
        DateTime after = new DateTime();

        System.out.println("primes found: " + primes.size());

        Period period = new Period(now, after);
        PeriodFormatter HHMMSSMMMFormatter = new PeriodFormatterBuilder()
                .printZeroAlways()
                .minimumPrintedDigits(2)
                .appendHours().appendSeparator(":")
                .appendMinutes().appendSeparator(":")
                .appendSeconds().appendSeparator(".")
                .appendMillis3Digit()
                .toFormatter();
        System.out.println("Took " + HHMMSSMMMFormatter.print(period) + " to generate");
    }
}
