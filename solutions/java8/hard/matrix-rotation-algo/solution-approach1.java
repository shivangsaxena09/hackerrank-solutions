// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/matrix-rotation-algo/problem?isFullScreen=true
// Problem     Matrix Layer Rotation 
// Difficulty  Hard
// Subdomain   Implementation
// Platform    HackerRank
// Language    java8
// Status      Accepted
// Submitted   2026-11-03, 01:44 a.m.
// ──────────────────────────────────────────────────

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'matrixRotation' function below.
     *
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY matrix
     *  2. INTEGER r
     */

    public static void matrixRotation(List<List<Integer>> matrix, int r) {
        int m = matrix.size();
        int n = matrix.get(0).size();
        int[][] a = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = matrix.get(i).get(j);
            }
        }

        int[][] res = new int[m][n];
        int layers = Math.min(m, n) / 2;

        for (int k = 0; k < layers; k++) {
            int top = k, left = k, bottom = m - 1 - k, right = n - 1 - k;

            // Ring positions in anti-clockwise order:
            // down the left side, right along the bottom, up the right side, left along the top
            List<int[]> pos = new ArrayList<>();
            for (int i = top; i < bottom; i++) pos.add(new int[]{i, left});
            for (int j = left; j < right; j++) pos.add(new int[]{bottom, j});
            for (int i = bottom; i > top; i--) pos.add(new int[]{i, right});
            for (int j = right; j > left; j--) pos.add(new int[]{top, j});

            int len = pos.size();
            int shift = r % len;

            for (int idx = 0; idx < len; idx++) {
                int[] from = pos.get(idx);
                int[] to = pos.get((idx + shift) % len);
                res[to[0]][to[1]] = a[from[0]][from[1]];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) sb.append(' ');
                sb.append(res[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(firstMultipleInput[0]);

        int n = Integer.parseInt(firstMultipleInput[1]);

        int r = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Result.matrixRotation(matrix, r);

        bufferedReader.close();
    }
}
