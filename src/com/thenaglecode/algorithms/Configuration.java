package com.thenaglecode.algorithms;

import com.thenaglecode.algorithms.util.ConfigurationItem;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/25/13
 * Time: 4:46 PM
 */
public class Configuration {
    public static final ConfigurationItem PRIME_GENERATOR_CLASS = new ConfigurationItem("primes.generator.class", "com.thenaglecode.algorithms.primes.PGSimple1");
    public static final ConfigurationItem PRIME_UPTO = new ConfigurationItem("primes.upto", "1000");
    public static final ConfigurationItem FILE_PRIMES = new ConfigurationItem("file.primes", "C;/primes.obj");


}
