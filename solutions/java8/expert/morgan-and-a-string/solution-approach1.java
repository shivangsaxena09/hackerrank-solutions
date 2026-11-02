// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/morgan-and-a-string/problem?isFullScreen=true
// Problem     Morgan and a String
// Difficulty  Expert
// Subdomain   Strings
// Platform    HackerRank
// Language    java8
// Status      Accepted
// Submitted   2026-11-02, 11:40 a.m.
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

    private static final long M1 = 1_000_000_007L, M2 = 998_244_353L;
    private static final long B1 = 911_382_323L, B2 = 972_663_749L;

    private static long[] prefix(String s, long base, long mod) {
        long[] h = new long[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            h[i + 1] = (h[i] * base + s.charAt(i)) % mod;
        }
        return h;
    }

    private static long get(long[] h, long[] pw, int l, int len, long mod) {
        long v = (h[l + len] - h[l] * pw[len]) % mod;
        return v < 0 ? v + mod : v;
    }

    public static String morganAndString(String a, String b) {
        int la = a.length(), lb = b.length();
        String A = a + "z";
        String B = b + "z";

        int mx = Math.max(la, lb) + 2;
        long[] p1 = new long[mx], p2 = new long[mx];
        p1[0] = 1;
        p2[0] = 1;
        for (int i = 1; i < mx; i++) {
            p1[i] = p1[i - 1] * B1 % M1;
            p2[i] = p2[i - 1] * B2 % M2;
        }

        long[] ha1 = prefix(A, B1, M1), ha2 = prefix(A, B2, M2);
        long[] hb1 = prefix(B, B1, M1), hb2 = prefix(B, B2, M2);

        StringBuilder sb = new StringBuilder(la + lb);
        int i = 0, j = 0;

        while (i < la || j < lb) {
            if (i == la) {
                sb.append(B.charAt(j++));
                continue;
            }
            if (j == lb) {
                sb.append(A.charAt(i++));
                continue;
            }

            char ca = A.charAt(i), cb = B.charAt(j);
            boolean takeA;
            if (ca != cb) {
                takeA = ca < cb;
            } else {
                int lo = 0, hi = Math.min(la + 1 - i, lb + 1 - j);
                int max = hi;
                while (lo < hi) {
                    int mid = (lo + hi + 1) >>> 1;
                    if (get(ha1, p1, i, mid, M1) == get(hb1, p1, j, mid, M1)
                            && get(ha2, p2, i, mid, M2) == get(hb2, p2, j, mid, M2)) {
                        lo = mid;
                    } else {
                        hi = mid - 1;
                    }
                }
                if (lo == max) {
                    takeA = true;
                } else {
                    takeA = A.charAt(i + lo) < B.charAt(j + lo);
                }
            }

            if (takeA) sb.append(A.charAt(i++));
            else sb.append(B.charAt(j++));
        }

        return sb.toString();
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String a = bufferedReader.readLine();

                String b = bufferedReader.readLine();

                String result = Result.morganAndString(a, b);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
