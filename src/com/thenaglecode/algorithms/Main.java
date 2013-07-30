package com.thenaglecode.algorithms;

import com.thenaglecode.algorithms.primes.PrimeGenerator;
import com.thenaglecode.algorithms.primes.PrimeList;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/25/13
 * Time: 5:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        long upTo = Long.valueOf(ConfigurationManager.getInstance().getValue(Configuration.PRIME_UPTO));

        if (args.length > 0) {
            //gen upto number and save.
            DateTime now = new DateTime();
            PrimeGenerator generator = PrimeGenerator.getGenerator();
            Long max = Long.valueOf(args[0]);
            System.out.println("Started prime generation using algorithm: " + generator.getName() + " up to " + max);
            PrimeList pList = generator.generate(max);
            try {
                File primes = new File(ConfigurationManager.getInstance().getValue(Configuration.FILE_PRIMES));
                if(primes.exists()) primes.delete();
                FileOutputStream fileOut = new FileOutputStream(ConfigurationManager.getInstance().getValue(Configuration.FILE_PRIMES));
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(pList);
                out.close();
                fileOut.close();
                System.out.println("Saved the primes");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            DateTime now = new DateTime();
            PrimeGenerator generator = PrimeGenerator.getGenerator();
            System.out.println("Started prime generation using algorithm: " + generator.getName() + " up to " + upTo);
            PrimeList primes = generator.generate(upTo);
            DateTime after = new DateTime();

            System.out.println("primes found: " + primes.getPrimes().size());

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
}
