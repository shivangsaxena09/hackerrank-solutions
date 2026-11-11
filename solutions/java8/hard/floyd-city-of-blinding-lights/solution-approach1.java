// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/floyd-city-of-blinding-lights/problem?isFullScreen=true
// Problem     Floyd : City of Blinding Lights
// Difficulty  Hard
// Subdomain   Graph Theory
// Platform    HackerRank
// Language    java8
// Status      Accepted
// Submitted   2026-11-11, 06:18 p.m.
// ──────────────────────────────────────────────────

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);

        st.nextToken();
        int n = (int) st.nval;
        st.nextToken();
        int m = (int) st.nval;

        final int INF = Integer.MAX_VALUE / 2;
        int[][] d = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(d[i], INF);
            d[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st.nextToken();
            int u = (int) st.nval;
            st.nextToken();
            int v = (int) st.nval;
            st.nextToken();
            int w = (int) st.nval;
            if (u != v) d[u][v] = w;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (d[i][k] == INF) continue;
                for (int j = 1; j <= n; j++) {
                    int nd = d[i][k] + d[k][j];
                    if (nd < d[i][j]) d[i][j] = nd;
                }
            }
        }

        st.nextToken();
        int q = (int) st.nval;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st.nextToken();
            int a = (int) st.nval;
            st.nextToken();
            int b = (int) st.nval;
            sb.append(d[a][b] >= INF ? -1 : d[a][b]).append('\n');
        }

        System.out.print(sb);
    }
}
