package problems;

import basics.Problem;
import basics.Tag;

/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally
or vertically neighboring. The same letter cell may not be used more than once.
 */
@Problem(value = "https://leetcode.com/problems/word-search/", tags = {Tag.Backtracking})
public class WordSearch {

    static int[][] MOVES = new int[][]{
            {0, -1},
            {0, 1},
            {-1, 0},
            {1, 0},
    };

    static public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (search(i, j, 0, board, word)) return true;

            }
        }
        return false;
    }

    static boolean search(int i, int j, int size, char[][] board, String word) {
        if (size == word.length() - 1) return board[i][j] == word.charAt(size);
        if (board[i][j] != word.charAt(size)) return false;
        char tmp = board[i][j];
        board[i][j] = '-';
        size++;
        for (int[] move : MOVES) {
            int x = i + move[0];
            int y = j + move[1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == word.charAt(size)) {
                if (search(x, y, size, board, word)) return true;
            }
        }
        board[i][j] = tmp;
        return false;
    }

}
