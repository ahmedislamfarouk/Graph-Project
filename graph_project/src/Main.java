import java.util.Scanner;

public class Main {
    static int[][] Adj;
    static int[][] inc;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("1-Simple (Undirected) Graph or 2-Directed Graph");
        int choice=scn.nextInt();
        if (choice==1){
            System.out.println("Entre The Number Of Vertices");
            int ver = scn.nextInt();
            System.out.println("Enter The Number Of Edges");
            int edg = scn.nextInt();
            Adj = new int[ver][ver];
            inc = new int[ver][edg];
            System.out.println("Entre The Adjacency Matrix Rows: " + ver + " Columns: " + ver);
            for (int i = 0; i < ver; i++) {
                for (int j = 0; j < ver; j++) {
                    Adj[i][j] = scn.nextInt();
                }
            }

            System.out.println("Entre The Incidence Matrix Rows: " + ver + " Columns: " + edg);
            for (int i = 0; i < ver; i++) {
                for (int j = 0; j < edg; j++) {
                    inc[i][j] = scn.nextInt();
                }
            }

            System.out.println("Adjacency Matrix");
            for (int i = 0; i < ver; i++) {
                System.out.println();
                for (int j = 0; j < ver; j++) {
                    System.out.print(Adj[i][j] + " ");
                }
            }
            System.out.println();
            System.out.println("incidence Matrix");
            for (int i = 0; i < ver; i++) {
                System.out.println();
                for (int j = 0; j < edg; j++) {
                    System.out.print(inc[i][j] + " ");
                }
            }
            System.out.println();
            System.out.println("Reflexive a reflexive edge is not applicable since there are no directed edges." );
            System.out.println("Symmetric all edges are symmetric by definition since they connect two vertices" );
            System.out.println("Transitive the concept of transitivity is not applicable since there are no directed edges" );
            System.out.println("Acyclic cannot exist in such graphs" );
            System.out.println("Connected: " + ConnectedUndirected());
            System.out.println("Has Euler Path: " + hasEulerianPathUndirected());
        }else if (choice==2){
            System.out.println("Entre The Number Of Vertices");
            int ver = scn.nextInt();
            System.out.println("Enter The Number Of Edges");
            int edg = scn.nextInt();
            Adj = new int[ver][ver];
            inc = new int[ver][edg];
            System.out.println("Entre The Adjacency Matrix Rows: " + ver + " Columns: " + ver);
            for (int i = 0; i < ver; i++) {
                for (int j = 0; j < ver; j++) {
                    Adj[i][j] = scn.nextInt();
                }
            }

            System.out.println("Entre The Incidence Matrix Rows: " + ver + " Columns: " + edg);
            for (int i = 0; i < ver; i++) {
                for (int j = 0; j < edg; j++) {
                    inc[i][j] = scn.nextInt();
                }
            }

            System.out.println("Adjacency Matrix");
            for (int i = 0; i < ver; i++) {
                System.out.println();
                for (int j = 0; j < ver; j++) {
                    System.out.print(Adj[i][j] + " ");
                }
            }
            System.out.println();
            System.out.println("incidence Matrix");
            for (int i = 0; i < ver; i++) {
                System.out.println();
                for (int j = 0; j < edg; j++) {
                    System.out.print(inc[i][j] + " ");
                }
            }
            System.out.println();
            System.out.println("Reflexive: " + Reflexive());
            System.out.println("Symmetric: " + Symmetric());
            System.out.println("Transitive: " + Transitive());
            System.out.println("Acyclic: " +Acyclic());
            System.out.println("Strongly Connected : " + StronglyConnected());
            System.out.println("Weakly connected: "+ConnectedUndirected());
            System.out.println("Has Euler Path: " + hasEulerianPathDirected());
        }else System.out.println("invalid graph choice");




    }
    public static boolean Reflexive() {
        for (int i = 0; i < Adj.length; i++) {
            if (Adj[i][i] != 1) {
                return false;
            }
        }
        return true;
    }
    public static boolean Symmetric() {
        for (int i = 0; i < Adj.length; i++) {
            for (int j = 0; j < Adj.length; j++) {
                if (Adj[i][j] != Adj[j][i] && i != j) {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean Transitive() {
        for (int i = 0; i < Adj.length; i++) {
            for (int j = 0; j < Adj.length; j++) {
                if (Adj[i][j] == 1 && i != j) {
                    for (int k = 0; k < Adj.length; k++) {
                        if (Adj[j][k] == 1 && Adj[i][k] == 1 && i != k && j != k) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public static boolean StronglyConnected() {
        int n = Adj.length;
        for (int i = 0; i < n; i++) {
            boolean[] visit = new boolean[n];
            FirstSearch(i,Adj, visit);
            for (boolean v : visit) {
                if (!v) {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean ConnectedUndirected() {
        int n = Adj.length;
        boolean[] visit = new boolean[n];
        FirstSearch(0, Adj, visit);

        for (boolean v : visit) {
            if (!v) {
                return false;
            }
        }
        return true;
    }
    private static void FirstSearch(int vertex, int[][] adjacency, boolean[] v) {
        v[vertex] = true;
        for (int i = 0; i < adjacency.length; i++) {
            if (adjacency[vertex][i] == 1 && !v[i]) {
                FirstSearch(i,adjacency,v);
            }
        }
    }
    public static boolean hasEulerianPathUndirected() {
        int OD = 0;
        for (int i = 0; i < Adj.length; i++) {
            int d = 0;
            for (int j = 0; j < Adj.length; j++) {
                d += Adj[i][j];
            }
            if (d % 2 != 0) {
                OD++;
            }
        }
        return OD == 0 || OD == 2;
    }
    public static boolean hasEulerianPathDirected() {
        if (!StronglyConnected()) {
            return false;
        }
        int n = Adj.length;
        int OutMIn = 0;
        for (int i = 0; i < n; i++) {
            int Dout = 0;
            int Din = 0;

            for (int j = 0; j < n; j++) {
                Dout += Adj[i][j];
                Din += Adj[j][i];
            }
            int diff = Dout- Din;
            if (diff > 1 || diff < -1) {
                return false;
            }
            if (diff == 1) {
                OutMIn++;
            }
        }
        return OutMIn == 0 || OutMIn == 2;
    }
        public static boolean Acyclic() {
            int n = Adj.length;
            boolean[] v = new boolean[n];
            boolean[] r = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!v[i]) {
                    if (Cyclic(i, Adj, v, r)) {
                        return false;
                    }
                }
            }
            return true;
        }
        private static boolean Cyclic(int v, int[][] adjacency, boolean[] visit, boolean[] r) {
            visit[v] = true;
            r[v] = true;
            for (int i = 0; i < adjacency.length; i++) {
                if (adjacency[v][i] == 1) {
                    if (!visit[i]) {
                        if (Cyclic(i, adjacency, visit, r)) {
                            return true;
                        }
                    } else if (r[i]) {
                        return true;
                    }
                }
            }
            r[v] = false;
            return false;
        }
    }
