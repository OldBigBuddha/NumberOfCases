package kyoto.freeprojects.oldbigbuddha.number_of_cases;

import android.util.Log;

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

    public static int permutation(int n, int r) {
        if (n < r) return 0;
        if (n == 0) return 1;
        if (r == 0) return 1;
        if (r == 1) return n;
        if (n == r) return factorial(n);
        int answer = n;
        int i = n - 1;
        do {
            Log.i("Count", "i = " + i);
            Log.d("Calc", answer + " * " + i);
            answer *= i;
            i--;
        } while (i > n - r);
        return answer;
    }

    public static Integer factorial(int n) {
        if(n==1) return 1;
        else return n * factorial(n-1);
    }

    public static Integer combination(int n, int r) {
        return permutation(n, r) / factorial(r);
    }

    public static int homogeneousProduct(int n, int r) {
        return combination(n + r - 1, r);
    }
}
