package com.thenaglecode.algorithms;

import com.thenaglecode.core.util.propeties.ConfigurationItem;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/8/13
 * Time: 3:41 PM
 */
public interface Configuration {
    public static final String PRIMES_GENERATOR_CLASS_ID = "primes.generator.class";

    public static final ConfigurationItem PRIMES_GENERATOR_CLASS = new ConfigurationItem(PRIMES_GENERATOR_CLASS_ID, "PG7_8");
}
