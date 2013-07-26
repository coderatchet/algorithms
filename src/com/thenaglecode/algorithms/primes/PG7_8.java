package com.thenaglecode.algorithms.primes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 7/26/13
 * Time: 11:25 AM
 * <p/>
 * This algorithm efficiently selects potential primes by eliminating multiples of previously identified primes from
 * consideration and minimizes the number of tests which must be performed to verify the primacy of each potential
 * prime. While the efficiency of selecting potential primes allows the program to sift through a greater range of
 * numbers per second the longer the program is run, the number of tests which need to be performed on each potential
 * prime does continue to rise, (but rises at a slower rate compared to other algorithms). Together, these processes
 * bring greater efficiency to generating prime numbers, making the generation of even 10 digit verified primes possible
 * within a reasonable amount of time on a PC. </br>
 * </br>
 * Further skip sets can be developed to eliminate the selection of potential primes which can be factored by each prime
 * that has already been identified. Although this process is more complex, it can be generalized and made somewhat
 * elegant. At the same time, we can continue to eliminate from the set of test primes each of the primes which the skip
 * sets eliminate multiples of, minimizing the number of tests which must be performed on each potential prime. This
 * example is fully commented, line by line, with some explanation to help the reader fully comprehend how the algorithm
 * works. A complete program including a user interface, but without the comments, can be found in Appendix B.
 * Please disregard syntactical errors which occur in the user interface such as “the 1th prime”, instead of “1st”, and
 * the inclusion of the last prime generated in the completed array even though it may be larger than the user defined
 * limit. These errors can easily be corrected at the convenience of the student programmer, but were not necessary to
 * illustrate the performance of the algorithms. I apologize for any confusion or inconvenience this may have caused the
 * reader.
 * <p/>
 * Algorithm translated from http://en.wikibooks.org/wiki/Efficient_Prime_Number_Generating_Algorithms
 */
public class PG7_8 extends PrimeGenerator {

    private static final String ALGORITHM_NAME = "PG7.8";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Long> generate(long lim) {
        /*Get the square root of the upper limit. This will be the upper limit of the test prime array
        for primes used to verify the primacy of any potential primes up to (lim). Primes greater than
        (sqrtlim) will be placed in an array for extended primes, (xp), not needed for the verification
        test. The use of an extended primes array is technically unnecessary, but helps to clarify that we
        have minimized the size of the test prime array.*/
        double sqrtlim = Math.sqrt(lim);
        /* Initialize the variable for the potential prime, setting it to begin with the first prime
        number, (2). */
        long pp = 2;
        /*Initialize the array for the skip set, setting it at a single member, being (pp=2). Although
        the value of the quantity of members in the skip set is never needed in the program, it may be
        useful to understand that future skip sets will contain more than one member, the quantity of which
        can be calculated, and is the quantity of members of the previous skip set multiplied by one less
        than the value of the prime which the new skip set will exclude multiples of. Example - the skip
        set which eliminates multiples of primes up through 3 will have (3-1)*1=2 members, since the
        previous skip set had 1 member. The skip set which eliminates multiples of primes up through 5 will
        have (5-1)*2=8 members, since the previous skip set had 2 members, etc.*/
        List<Long> ss = new LinkedList<Long>();
        ss.add(pp);

        /*Initialize the array for primes which the skip set eliminate multiples of, setting the first
        member as (pp=2) since the first skip set will eliminate multiples of 2 as potential primes.*/
        List<Long> ep = new LinkedList<Long>();
        ep.add(pp);

        /*Advance to the first potential prime, which is 3.*/
        pp++;

        /*Initialize an array for the ranges of each skip set, setting the first member to be the range
        of the first skip set, which is (ss[0]=2). Future skip sets will have ranges which can be
        calculated, and are the sum of the members of the skip set. Another method of calculating the range
        will also be shown below.*/
        List<Long> rss = new ArrayList<Long>();
        rss.add(ss.get(0));

        /*Initialize an array for primes which are needed to verify potential primes against, setting the
        first member as (pp=3), since we do not yet have a skip set that excludes multiples of 3. Also note
        that 3 is a verified prime, without testing, since there are no primes less than the square root of
        3.*/
        List<Long> tp = new LinkedList<Long>();
        tp.add(pp);

        /*Initialize a variable for keeping track of which skip set range is current.*/
        int i = 0;

        /*Add a member to the array of skip set ranges, the value being the value of the previous skip
        set range, (rss[0]=2), multiplied by the value of the largest prime which the new skip set will
        exclude multiples of, (tp[0]=3), so 2*3=6. This value is needed to define when to begin
        constructing the next skip set.*/
        rss.add(rss.get(i) * tp.get(0));

        /*Initialize an array for extended primes which are larger than the square root of the user
        defined limit (lim) and not needed to verify potential primes against, leaving it empty for now.
                Again, the use of an extended primes array is technically unnecessary, but helps to clarify that we
        have minimized the size of the test prime */
        List<Long> xp = new LinkedList<Long>();

        /*Advance to the next potential prime, which is the previous potential prime, (pp=3), plus the
        value of the next member of the skip set, which has only one member at this time and whose value is
                (ss[0]=2), so 3+2=5.*/
        pp += ss.get(0);

        /*Initialize a variable for the next potential prime, setting its value as (pp=5).*/
        long npp = pp;

        /*Add a member to the array of test primes, the member being the most recently identified prime,
                (npp=5). Note that 5 is a verified prime without testing, since there are no TEST primes less than
        the square root of 5.*/
        tp.add(npp);

        /*Loop until the user defined upper limit is reached.*/
        while (npp < lim) {
            /*Increment the skip set range identifier.*/
            i++;

            /*Loop until the next skip set range is surpassed, since data through that range is
            needed before constructing the next skip set.*/
            while (npp < rss.get(i) + 1) {

                /*Loop through the current skip set array, assigning the variable (n) the value
                of the next member of the skip set.*/
                for (long n : ss) {

                    /*Assign the next potential prime the value of the potential prime plus
                    the value of the current member of the skip set.*/
                    npp = pp + n;

                    /*If the next potential prime is greater than the user defined limit,
                    then end the 'for n' loop.*/
                    if (npp > lim) break;

                    /*If the next potential prime is still within the range of the next skip
                    set, then assign the potential prime variable the value of the next
                    potential prime. Otherwise, the potential prime variable is not changed
                    and the current value remains the starting point for constructing the next
                    skip set.*/
                    if (npp <= rss.get(i)) pp = npp;

                    /*Get the square root of the next potential prime, which will be the
                    limit for the verification process.*/
                    double sqrtnpp = Math.sqrt(npp);

                    /* Set the verification flag to True */
                    boolean test = true;

                    /*Loop through the array of the primes necessary for verification of the
                    next potential prime.*/
                    for (long q : tp) {

                        /*If the test prime is greater than the square root of the next
                        potential prime, then end testing through the 'for q' loop.*/
                        if (sqrtnpp < q) {
                            break;
                        /*If the test prime IS a factor of the next potential prime.*/
                        } else if (npp % q == 0) {

                            /*Then set the verification flag to False since the next
                            potential prime is not a prime number.*/
                            test = false;

                            /*And end testing through the 'for q' loop.*/
                            break;
                        }
                    }

                    /*If the next potential prime has been verified as a prime number.*/
                    if (test) {

                        /*And if the next potential prime is less than or equal to the
                        square root of the user defined limit, then add it to the array of
                        primes which potential primes must be tested against.*/
                        if (npp <= sqrtlim) tp.add(npp);

                        /*Otherwise, add it to the array of primes not needed to verify
                        potential primes against.*/
                        else xp.add(npp);

                    }
                    /*Then continue through the 'for n' loop.*/
                }

                /*If the next potential prime is greater than the user defined limit, then end
                the 'while npp<rss[i]+1' loop.*/
                if (npp > lim) break;

                /*Otherwise, continue the 'while npp<rss[i]+1' loop.*/
            }

            /*If the next potential prime is greater than the user defined limit, then end the 'while
            npp<int(lim)' loop.*/
            if (npp > lim) break;
        /*At this point, the range of the next skip set has been reached, so we may begin
        constructing a new skip set which will exclude multiples of primes up through the value of
        the first member of the test prime set, (tp[0]), from being selected as potential
        primes.*/

        /*Initialize a variable for the last relevant potential prime and set its value to the
        value of the potential prime.*/
            long lrpp = pp;

        /*Initialize an array for the next skip set, leaving it empty for now.*/
            List<Long> nss = new LinkedList<Long>();

        /*Loop until the construction of the new skip set has gone through the range of the new
                skip set.*/
            while (pp < (rss.get(i) + 1) * 2 - 1) {

            /*Loop through the current skip set array.*/
                for (long n : ss) {

                /*Assign the next potential prime the value of the potential prime plus
                the value of the current member of the skip set.*/
                    npp = pp + n;

                /*If the next potential prime is greater than the user defined limit,
                then end the 'for n' loop.*/
                    if (npp > lim) break;

                /*Get the square root of the next potential prime, which will be the
                limit for the verification process.*/
                    double sqrtnpp = Math.sqrt(npp);

                /*Set the verification flag to True.*/
                    boolean test = true;

                /*Loop through the array of the primes necessary for verification of the
                next potential prime.*/
                    for (long q : tp) {

                    /*If the test prime is greater than the square root of the next
                    potential prime, then end testing through the 'for q' loop.*/
                        if (sqrtnpp < q) {
                            break;
                        }
                    /*If the test prime IS a factor of the next potential prime.*/
                        else if (npp % q == 0) {

                        /*Then set the verification flag to False since the next
                        potential prime is not a prime number.*/
                            test = false;

                        /*And end testing through the 'for q' loop.*/
                            break;
                        }
                    /*Otherwise, continue testing through the 'for q' loop.*/
                    }

                /*If the next potential prime has been verified as a prime number.*/
                    if (test) {

                    /*And if the next potential prime is less than or equal to the
                    square root of the user defined limit, then add it to the array of
                    primes which potential primes must be tested against.*/
                        if (npp <= sqrtlim) tp.add(npp);

                    /*Otherwise, add it to the array of primes not needed to verify
                    potential primes against.*/
                        else xp.add(npp);
                    }

                /*If the next potential prime was NOT factorable by the first member of
                the test array, then it is relevant to the construction of the new skip set
                and a member must be included in the new skip set for a potential prime to
                be selected. Note that this is the case regardless of whether the next
                    potential prime was verified as a prime, or not.*/
                    if (npp % tp.get(0) != 0) {

                    /*Add a member to the next skip set, the value of which is the
                    difference between the last relevant potential prime and the next
                    potential prime.*/
                        nss.add(npp - lrpp);

                    /*Assign the variable for the last relevant potential prime the
                    value of the next potential prime.*/
                        lrpp = npp;
                    }

                /*Assign the variable for the potential prime the value of the next
                potential prime.*/
                    pp = npp;

                /*Then continue through the 'for n' loop.*/
                }

            /*If the next potential prime is greater than the user defined limit, then end
            the 'while npp<(rss[i]+1)*2-1' loop.*/
                if (npp > lim) break;

            /*Otherwise, continue the 'while npp<(rss[i]+1)*2-1' loop.*/
            }

            /*If the next potential prime is greater than the user defined limit, then end the 'while
            npp<int(lim)' loop.*/
            if(npp > lim) break;

            /*Assign the skip set array the value of the new skip set array.*/
            ss = nss;

            /*Add a new member to the excluded primes array, since the newly constructed skip set
            will exclude all multiples of primes through the first member of the test prime array.*/
            ep.add(tp.get(0));

            /*Delete the first member from the test prime array since future potential primes will
            not have to be tested against this prime.*/
            tp.remove(0);

            /*Add a member to the skip set range array with the value of the range of the next skip
            set.*/
            rss.add(rss.get(i)*tp.get(0));

            /*Assign the next potential prime the value of the last relevant potential prime.*/
            npp = lrpp;

            /*Then continue through the 'while npp<int(lim)' loop.*/
        }

        /*At this point the user defined upper limit has been reached and the generator has completed
        finding all of the prime numbers up to the user defined limit.*/

        /*Flip the array of excluded primes.*/
        Collections.reverse(ep);

        for(long a : ep){
            tp.add(0, a);
        }

        Collections.reverse(tp);

        for(long a : tp){
            xp.add(0, a);
        }

        return xp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return ALGORITHM_NAME;
    }
}
