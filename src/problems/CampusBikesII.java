package problems;

import basics.Problem;

import static basics.Tag.Backtracking;
import static basics.Tag.DP;

/*
On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.
 */
@Problem(value = "https://leetcode.com/problems/campus-bikes-ii/", tags = {DP, Backtracking})
public class CampusBikesII {

    public static int assignBikes(int[][] workers, int[][] bikes) {
        int[] memo = new int[1 << bikes.length]; // creates memo to store all possible states
        return assignBikes(workers, 0, bikes, memo, 0);
    }

    public static int assignBikes(int[][] workers,
                                  int i,
                                  int[][] bikes,
                                  int[] memo,
                                  int state) {
        if (memo[state] > 0) return memo[state];

        if (i == workers.length) return 0;

        int res = Integer.MAX_VALUE;

        for (int j = 0; j < bikes.length; j++) {
            int mask = 1 << j; // sets jth  bit to 1
            if ((state & mask) == mask) continue;  // continue of the given bike is already taken
            state |= mask; // sets jth bit in state
            int[] p2 = bikes[j];
            int dist = dist(p2, workers[i]);
            res = Math.min(res, dist + assignBikes(workers, i + 1, bikes, memo, state));
            state ^= mask; // resets jth bit in state
        }
        memo[state] = res;
        return res;
    }

    // |p1.x - p2.x| + |p1.y - p2.y|
    static int dist(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}
