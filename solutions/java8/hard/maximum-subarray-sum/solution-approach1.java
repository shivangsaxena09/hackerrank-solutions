// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/maximum-subarray-sum/problem?isFullScreen=true
// Problem     Maximum Subarray Sum
// Difficulty  Hard
// Subdomain   Search
// Platform    HackerRank
// Language    java8
// Status      Accepted
// Submitted   2026-11-11, 06:22 p.m.
// ──────────────────────────────────────────────────

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedInputStream(System.in));

        st.nextToken();
        int q = (int) st.nval;
        StringBuilder sb = new StringBuilder();

        while (q-- > 0) {
            st.nextToken();
            int n = (int) st.nval;
            st.nextToken();
            long m = (long) st.nval;

            TreeSet<Long> set = new TreeSet<>();
            set.add(0L);
            long pre = 0;
            long best = 0;

            for (int i = 0; i < n; i++) {
                st.nextToken();
                long x = (long) st.nval;
                pre = (pre + x % m) % m;
                best = Math.max(best, pre);
                Long h = set.higher(pre);
                if (h != null) {
                    best = Math.max(best, pre - h + m);
                }
                set.add(pre);
            }

            sb.append(best).append('\n');
        }

        System.out.print(sb);
    }
}
