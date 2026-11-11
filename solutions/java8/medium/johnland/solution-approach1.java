// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/johnland/problem?isFullScreen=true
// Problem     Roads in HackerLand
// Difficulty  Medium
// Subdomain   Graph Theory
// Platform    HackerRank
// Language    java8
// Status      Accepted
// Submitted   2026-11-11, 06:20 p.m.
// ──────────────────────────────────────────────────

import java.io.*;
import java.util.*;

public class Solution {
    static int[] parent;

    static int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedInputStream(System.in));

        st.nextToken();
        int n = (int) st.nval;
        st.nextToken();
        int m = (int) st.nval;

        int[] eu = new int[m];
        int[] ev = new int[m];
        int[] ew = new int[m];
        long[] keys = new long[m];
        int maxW = 0;

        for (int i = 0; i < m; i++) {
            st.nextToken();
            eu[i] = (int) st.nval;
            st.nextToken();
            ev[i] = (int) st.nval;
            st.nextToken();
            ew[i] = (int) st.nval;
            if (ew[i] > maxW) maxW = ew[i];
            keys[i] = ((long) ew[i] << 20) | i;
        }

        Arrays.sort(keys);

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) parent[i] = i;

        int[] head = new int[n + 1];
        Arrays.fill(head, -1);
        int[] nxt = new int[2 * n];
        int[] to = new int[2 * n];
        int[] wt = new int[2 * n];
        int cnt = 0;

        for (int k = 0; k < m; k++) {
            int idx = (int) (keys[k] & ((1 << 20) - 1));
            int a = find(eu[idx]);
            int b = find(ev[idx]);
            if (a != b) {
                parent[a] = b;
                to[cnt] = ev[idx];
                wt[cnt] = ew[idx];
                nxt[cnt] = head[eu[idx]];
                head[eu[idx]] = cnt++;
                to[cnt] = eu[idx];
                wt[cnt] = ew[idx];
                nxt[cnt] = head[ev[idx]];
                head[ev[idx]] = cnt++;
            }
        }

        int[] par = new int[n + 1];
        int[] parW = new int[n + 1];
        int[] order = new int[n];
        int[] stack = new int[n];
        boolean[] seen = new boolean[n + 1];
        int sp = 0, oc = 0;
        stack[sp++] = 1;
        seen[1] = true;
        while (sp > 0) {
            int u = stack[--sp];
            order[oc++] = u;
            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e];
                if (!seen[v]) {
                    seen[v] = true;
                    par[v] = u;
                    parW[v] = wt[e];
                    stack[sp++] = v;
                }
            }
        }

        long[] size = new long[n + 1];
        long[] bits = new long[maxW + 80];
        for (int i = oc - 1; i >= 0; i--) {
            int u = order[i];
            size[u] += 1;
            if (u != 1) {
                size[par[u]] += size[u];
                bits[parW[u]] += size[u] * ((long) n - size[u]);
            }
        }

        for (int i = 0; i < bits.length - 1; i++) {
            bits[i + 1] += bits[i] >> 1;
            bits[i] &= 1;
        }

        int top = bits.length - 1;
        while (top > 0 && bits[top] == 0) top--;

        StringBuilder sb = new StringBuilder();
        for (int i = top; i >= 0; i--) sb.append(bits[i]);
        System.out.println(sb);
    }
}
