// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/making-candies/problem?isFullScreen=true
// Problem     Making Candies
// Difficulty  Hard
// Subdomain   Search
// Platform    HackerRank
// Language    java8
// Status      Accepted
// Submitted   2026-11-11, 06:16 p.m.
// ──────────────────────────────────────────────────

import java.io.*;
import java.util.*;

class Result {

    public static long minimumPasses(long m, long w, long p, long n) {
        long candy = 0, steps = 0, ans = Long.MAX_VALUE;

        while (steps < ans) {
            if (m >= (n + w - 1) / w) {
                ans = Math.min(ans, steps + 1);
                break;
            }
            long prod = m * w;

            long finish = (n - candy + prod - 1) / prod;
            ans = Math.min(ans, steps + finish);

            if (candy < p) {
                long skip = (p - candy + prod - 1) / prod;
                if (finish <= skip) break;
                steps += skip;
                candy += skip * prod;
            }

            long buy = candy / p;
            candy %= p;

            long diff = Math.abs(m - w);
            if (buy <= diff) {
                if (m < w) m += buy; else w += buy;
            } else {
                long rest = buy - diff;
                long hi = Math.max(m, w);
                m = hi + rest / 2;
                w = hi + (rest - rest / 2);
            }
        }
        return ans;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] first = bufferedReader.readLine().trim().split(" ");
        long m = Long.parseLong(first[0]);
        long w = Long.parseLong(first[1]);
        long p = Long.parseLong(first[2]);
        long n = Long.parseLong(first[3]);

        long result = Result.minimumPasses(m, w, p, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
