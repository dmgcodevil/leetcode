package problems;

import basics.Problem;
import basics.Tag;

import java.util.Arrays;

@Problem(value = "https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/", tags = Tag.Greedy)
public class MaximizeSumOfArrayAfterKNegations {

    public int largestSumAfterKNegations(int[] A, int K) {
        int sum = 0;
        int min = 101;

        Arrays.sort(A);

        for (int i = 0; i < A.length; i++) {
            if (A[i] < 0 && K > 0) {
                A[i] = -A[i];
                K--;
            }
            min = Math.min(min, A[i]);
            sum += A[i];
        }

        if (K > 0 && min >= 0 && K % 2 != 0 || min < 0 && K % 2 != 0) sum -= 2 * min;
        return sum;
    }

}
