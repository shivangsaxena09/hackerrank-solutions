// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/bear-and-steady-gene/problem?isFullScreen=true
// Problem     Bear and Steady Gene
// Difficulty  Medium
// Subdomain   Strings
// Platform    HackerRank
// Language    java8
// Status      Accepted
// Submitted   2026-11-02, 11:38 a.m.
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

    public static int steadyGene(String gene) {
        int n = gene.length();
        int target = n / 4;
        int[] cnt = new int[128];

        for (int i = 0; i < n; i++) cnt[gene.charAt(i)]++;

        if (cnt['A'] == target && cnt['C'] == target
                && cnt['G'] == target && cnt['T'] == target) {
            return 0;
        }

        int best = n;
        int l = 0;

        for (int r = 0; r < n; r++) {
            cnt[gene.charAt(r)]--;

            while (l <= r && cnt['A'] <= target && cnt['C'] <= target
                    && cnt['G'] <= target && cnt['T'] <= target) {
                best = Math.min(best, r - l + 1);
                cnt[gene.charAt(l)]++;
                l++;
            }
        }

        return best;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String gene = bufferedReader.readLine();

        int result = Result.steadyGene(gene);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
