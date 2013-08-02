package com.thenaglecode.algorithms;

import com.thenaglecode.algorithms.util.ConfigurationItem;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/25/13
 * Time: 4:46 PM
 */
public class Configuration {
    public static final String PRIME_GENERATOR_CLASS_ID = "primes.generator.class";
    public static final String PRIME_UPTO_ID = "primes.upto";
    public static final String FILE_PRIMES_ID = "file.primes";
    public static final String STANDARD_BASE_PACKAGE_ID = "standard.base.package";

    public static final ConfigurationItem PRIME_GENERATOR_CLASS = new ConfigurationItem(PRIME_GENERATOR_CLASS_ID, "PGSimple1");
    public static final ConfigurationItem PRIME_UPTO = new ConfigurationItem(PRIME_UPTO_ID, "1000");
    public static final ConfigurationItem FILE_PRIMES = new ConfigurationItem(FILE_PRIMES_ID, "C;/primes.obj");
    public static final ConfigurationItem STANDARD_BASE_PACKAGE = new ConfigurationItem(STANDARD_BASE_PACKAGE_ID, "com.thenaglecode");

}
