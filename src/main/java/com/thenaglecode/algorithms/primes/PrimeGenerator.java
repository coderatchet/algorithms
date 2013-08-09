package com.thenaglecode.algorithms.primes;

import com.thenaglecode.algorithms.Configuration;
import com.thenaglecode.core.util.Named;
import com.thenaglecode.core.util.propeties.ConfigurationUtil;
import com.thenaglecode.core.util.propeties.RefreshablePropertyResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/25/13
 * Time: 3:59 PM
 */
public abstract class PrimeGenerator implements Named, Configuration {

    private static final RefreshablePropertyResourceBundle coreBundle
            = ConfigurationUtil.getRefreshablePropertyResourceBundle("core");

    private static PrimeGenerator instance;

    /**
     * gets the generator listed in the configuration properties file.
     * @return
     */
    public static PrimeGenerator getGenerator() {
        if (instance == null) {
            try {
                instance = (PrimeGenerator) Class.forName(coreBundle.getString(PRIMES_GENERATOR_CLASS)).newInstance();
            } catch (Exception e){
                instance = new PGSimple1();
            }
        }
        return instance;
    }

    /**
     * generate a list of prime numbers up until the given number (exclusive)
     * @param lim the number to go up to
     * @return a list of prime numbers up until lim
     */
    public abstract PrimeList generate(long lim);
}
