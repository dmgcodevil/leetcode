package problems;

import basics.Problem;
import basics.Tag;

import java.util.LinkedList;
import java.util.Queue;
/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.
 */
@Problem(value = "https://leetcode.com/problems/jump-game-ii/", tags = {Tag.Greedy, Tag.BFS})
public class JumpGameII {


    static public int jump(int[] A) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < A.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + A[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }


    // bfs
    static public int jump2(int[] A) {
        if (A.length <= 1) return 0;
        boolean[] visited = new boolean[A.length];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                for (int i = A[curr[0]]; i > 0; i--) {
                    int next = curr[0] + i;
                    if (next >= A.length - 1) return curr[1] + 1;
                    if (!visited[next]) {
                        queue.add(new int[]{next, curr[1] + 1});
                        visited[next] = true;
                    }
                }
            }
        }
        return -1;
    }

}
