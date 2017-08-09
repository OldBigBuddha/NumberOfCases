package kyoto.freeprojects.oldbigbuddha.number_of_cases;

import android.util.Log;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Copyright 2017 Big Buddha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


public class CasesUtils {

    public static BigInteger permutation(BigInteger n, BigInteger r) {
        if (n.compareTo(r) < 0) return null;                            // (n P r) n < r -> null
        if (n.compareTo(BigInteger.ZERO) == 0) return null;             // (n P r) n = 0 -> null
        if (r.compareTo(BigInteger.ZERO) == 0) return BigInteger.ONE;   // (n P r) r = 0 -> 1
        if (r.compareTo(BigInteger.ONE) == 0) return n;                 // (n P r) r = 1 -> n
        if (n.compareTo(r) == 0) return factorial(n);                   // (n P r) n = r -> n!

        BigInteger answer = n;
        BigInteger i = n.subtract(BigInteger.ONE);
        do {
            answer = answer.multiply(i);
            i = i.subtract(BigInteger.ONE);
        } while (i.compareTo(n.subtract(r)) > 0 );                      // (n P r) = n * (n-1) * (n-2) ・・・ * (n-r+1)
        return answer;
    }

    public static BigInteger factorial(BigInteger n) {
        if(n.compareTo(BigInteger.ZERO) == 0 || n.compareTo(BigInteger.ONE) == 0) return BigInteger.ONE;    // 0! = 1, 1! = 1
        else return n.multiply( factorial(n.subtract(BigInteger.ONE)) );                                    // n! = n * (n-1) * (n-2) ・・・ * 1
    }

    public static BigInteger combination(BigInteger n, BigInteger r) {
        return permutation(n, r).divide( factorial(r) );                // (n P r) / r!
    }

    public static BigInteger homogeneousProduct(BigInteger n, BigInteger r) {
        return combination(n.add(r).subtract(BigInteger.ONE), r);       // n + r - 1 C r
    }
}
