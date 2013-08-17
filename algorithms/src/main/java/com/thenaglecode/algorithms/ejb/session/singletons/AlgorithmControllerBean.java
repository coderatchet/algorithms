package com.thenaglecode.algorithms.ejb.session.singletons;

import com.thenaglecode.algorithms.random.LinearCongruentialGenerator;
import com.thenaglecode.algorithms.random.RandomNumberGenerator;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/1/13
 * Time: 3:54 PM
 */
@Singleton
public class AlgorithmControllerBean {

    private static final RandomNumberGenerator generator = new LinearCongruentialGenerator();

    public AlgorithmControllerBean() {
    }

    public String getRandomNumber(final String type, final long upTo) {
        switch (StringUtils.lowerCase(type)) {
            case "short":
                return String.valueOf(generator.nextShort((short) upTo));
            case "int":
                return String.valueOf(generator.nextInt((int) upTo));
            case "float":
                return String.valueOf(generator.nextFloat());
            case "double":
                return String.valueOf(generator.nextDouble());
            case "long":
            default:
                return String.valueOf(generator.nextLong(upTo));
        }
    }

    public void setSeed(final long seed) {
        generator.setSeed(seed);
    }
}
