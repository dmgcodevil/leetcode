package problems;

import basics.Problem;
import basics.Tag;

import java.util.Stack;

/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.
 */
@Problem(value = "https://leetcode.com/problems/surrounded-regions/", tags = Tag.DFS)
public class SurroundedRegions {

    static int[][] MOVES = new int[][]{
            {0, -1},
            {0, 1},
            {-1, 0},
            {1, 0},
    };


    static public void solve(char[][] board) {
        if (board.length == 0) return;
        int m = board.length;
        int n = board[0].length;
        swapBorder(board);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '-') board[i][j] = 'O';
                else if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }


    static void dfs(int i, char[][] board, int j) {
        Stack<int[]> stack = new Stack<>();
        stack.add(new int[]{i, j});
        board[i][j] = '-';

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();

            for (int[] move : MOVES) {
                int x = cur[0] + move[0];
                int y = cur[1] + move[1];
                if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'O') {
                    stack.add(new int[]{x, y});
                    board[x][y] = '-';
                }
            }
        }
    }

    static char[][] swapBorder(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        // top
        for (int i = 0; i < n; i++) if (board[0][i] == 'O') dfs(0, board, i);
        //  right
        for (int i = 0; i < m; i++) if (board[i][n - 1] == 'O') dfs(i, board, n - 1);
        // bottom
        for (int i = 0; i < n; i++) if (board[m - 1][i] == 'O') dfs(m - 1, board, i);
        //  left
        for (int i = 0; i < m; i++) if (board[i][0] == 'O') dfs(i, board, 0);
        return board;
    }

}
