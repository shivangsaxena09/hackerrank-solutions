// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/subset-component/problem?isFullScreen=true
// Problem     Subset Component
// Difficulty  Hard
// Subdomain   Graph Theory
// Platform    HackerRank
// Language    java8
// Status      Accepted
// Submitted   2026-11-03, 01:47 a.m.
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
     * Complete the 'findConnectedComponents' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts LONG_INTEGER_ARRAY d as parameter.
     */

    private static long[] vals;
    private static int n;

    public static int findConnectedComponents(List<Long> d) {
        n = d.size();
        vals = new long[n];
        for (int i = 0; i < n; i++) vals[i] = d.get(i);
        return (int) rec(0, new long[0], 0);
    }

    private static long rec(int i, long[] masks, int cnt) {
        if (i == n) {
            long comps = 64 + cnt;
            for (int j = 0; j < cnt; j++) comps -= Long.bitCount(masks[j]);
            return comps;
        }

        // Exclude vals[i]
        long total = rec(i + 1, masks, cnt);

        // Include vals[i]
        if (vals[i] == 0) {
            // Zero has no bits, so it changes nothing
            return total + rec(i + 1, masks, cnt);
        }

        long cur = vals[i];
        long[] next = new long[cnt + 1];
        int k = 0;
        for (int j = 0; j < cnt; j++) {
            if ((masks[j] & cur) != 0) {
                cur |= masks[j];
            } else {
                next[k++] = masks[j];
            }
        }
        next[k++] = cur;
        total += rec(i + 1, next, k);

        return total;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int dCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Long> d = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        int components = Result.findConnectedComponents(d);

        bufferedWriter.write(String.valueOf(components));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
