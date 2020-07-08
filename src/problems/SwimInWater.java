package problems;

import basics.Problem;

import java.util.PriorityQueue;
import java.util.Stack;

import static basics.Tag.DFS;
import static basics.Tag.DIJKSTRA;

/**
 * On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).
 *
 * Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 *
 * You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?
 */
@Problem(value = "https://leetcode.com/problems/swim-in-rising-water", tags = {DFS, DIJKSTRA})
public class SwimInWater {

    static int[][] MOVES = new int[][]{
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    public static void main(String[] args) {
        System.out.println(swimInWater(new int[][]{
                {10, 12, 4, 6},
                {9, 11, 3, 5},
                {1, 7, 13, 8},
                {2, 0, 15, 14}
        }));
    }

    static public int swimInWater(int[][] grid) {
        return solveQueue(grid);
    }

    //    time complexity: O(n^2*logn)
//    1) pq contains at most n^2 elements, pop time complexity each time is is O(logn^2) = O(2*logn)
//    2) At most we will pop n^2 times
//    O(n^2*2*logn) = O(n^2*logn)
    static int solveQueue(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid.length];
        visited[0][0] = true;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.add(new int[]{0, 0, grid[0][0]});

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            if (node[0] == grid.length - 1 && node[1] == grid[0].length - 1) return node[2];
            int time = node[2];
            for (int[] move : MOVES) {
                int x = node[0] + move[0];
                int y = node[1] + move[1];
                if (x >= 0 && y >= 0 && x < grid.length
                        && y < grid[0].length && !visited[x][y]) {
                    queue.add(new int[]{x, y, time >= grid[x][y] ? time : time + (grid[x][y] - time)});
                    visited[x][y] = true;
                }
            }
        }
        return -1;
    }


    // time: for DFS is N^2 , search logN => N^2 *  LogN
    // space: N^2
    static int solveBinarySearchDFS(int[][] grid) {
        int left = grid[0][0];
        int right = grid.length * grid.length;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isPossible(mid, grid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    static boolean isPossible(int t, int[][] grid) {
        int N = grid.length;
        boolean[][] visited = new boolean[grid.length][grid.length];
        visited[0][0] = true;
        Stack<Integer> stack = new Stack<>();
        stack.add(0);

        while (!stack.isEmpty()) {
            int n = stack.pop();
            int r = n / N;
            int c = n % N;
            if (r == N - 1 && c == N - 1) return true;
            for (int[] move : MOVES) {
                int x = move[0] + r;
                int y = move[1] + c;

                if (x >= 0 && y >= 0 && x < N && y < N && !visited[x][y] && t >= grid[x][y]) {
                    stack.add(x * N + y);
                    visited[x][y] = true;
                }
            }
        }

        return false;
    }
}