// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/beadornaments/problem?isFullScreen=true
// Problem     Bead Ornaments
// Difficulty  Advanced
// Subdomain   Graph Theory
// Platform    HackerRank
// Language    java8
// Status      Accepted
// Submitted   2026-11-11, 06:31 p.m.
// ──────────────────────────────────────────────────

import java.io.*;
import java.util.*;

public class Solution {
    static final long MOD = 1000000007L;

    static long power(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedInputStream(System.in));

        st.nextToken();
        int t = (int) st.nval;
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            st.nextToken();
            int n = (int) st.nval;
            long[] b = new long[n];
            long s = 0;
            for (int i = 0; i < n; i++) {
                st.nextToken();
                b[i] = (long) st.nval;
                s += b[i];
            }

            long ans;
            if (n == 1) {
                ans = b[0] <= 2 ? 1 : power(b[0], b[0] - 2);
            } else {
                ans = power(s, n - 2);
                for (int i = 0; i < n; i++) {
                    ans = ans * power(b[i], b[i] - 1) % MOD;
                }
            }
            sb.append(ans).append('\n');
        }

        System.out.print(sb);
    }
}
