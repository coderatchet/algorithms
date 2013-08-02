package com.thenaglecode.algorithms.ejb;

import com.thenaglecode.algorithms.random.LinearCongruentialGenerator;
import com.thenaglecode.algorithms.random.RandomNumberGenerator;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/1/13
 * Time: 3:54 PM
 */
@Stateless(name = "AlgorithmControllerEJB")
@LocalBean
public class AlgorithmControllerBean {

    private static RandomNumberGenerator generator = new LinearCongruentialGenerator();

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
        generator = new LinearCongruentialGenerator(seed);
    }
}
