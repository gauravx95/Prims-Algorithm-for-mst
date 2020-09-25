package graapphhss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Graph {
    HashMap<String, HashMap<String, Integer>> vres = new HashMap<>();

    public boolean containsVertex(String vname) {
        return vres.containsKey(vname);
    }

    public void addVertex(String vname) {
        if (containsVertex(vname) == false) {
            vres.put(vname, new HashMap<>());
        }
    }

    public void addEdge(String v1name, String v2name, int wt) {
        if (containsVertex(v1name) == true && containsVertex(v2name) == true) {
            vres.get(v1name).put(v2name, wt);
            vres.get(v2name).put(v1name, wt);
        }
    }

    public void display() {
        System.out.println("-------------G R A P H-------------------");
        ArrayList<String> disp = new ArrayList<>(vres.keySet());
        for (String val : disp) {
            System.out.println(val + "->" + vres.get(val));
        }
    }

    private class Pair implements Comparable<Pair> {
        String v;
        String av;
        int avw;

        Pair(String v, String av, int avw) {
            this.v = v;
            this.av = av;
            this.avw = avw;
        }

        @Override

        public int compareTo(Pair o) {
            return this.avw - o.avw;
        }

    }

    public Graph prims() {
        Graph mst = new Graph();
        ArrayList<String> allvces = new ArrayList<>(vres.keySet());
        String s = allvces.get(0);
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        HashSet<String> visited = new HashSet<>();
        pq.add(new Pair(s, null, 0));

        while (pq.size() > 0) {
            //REM
            Pair rem = pq.remove();
            if (visited.contains(rem.v))
                continue;


            //MARK
            visited.add(rem.v);
            //WORK
            mst.addVertex(rem.v);
            if (rem.av != null) {
                mst.addEdge(rem.v, rem.av, rem.avw);
            }

            //ADD
            for (String nbr : vres.get(rem.v).keySet()) {
                if (visited.contains(nbr) == false) {
                    Pair p = new Pair(nbr, rem.v, vres.get(rem.v).get(nbr));
                    pq.add(p);
                }
            }
        }

        return mst;
    }


    public static void main(String[] args) {
        Graph g = new Graph();


        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addVertex("E");
        g.addVertex("F");
        g.addVertex("G");

        g.addEdge("A", "D", 40);
        g.addEdge("A", "B", 10);
        g.addEdge("B", "C", 10);
        g.addEdge("C", "D", 10);
        g.addEdge("D", "E", 2);
        g.addEdge("E", "F", 3);
        g.addEdge("E", "G", 8);
        g.addEdge("F", "G", 3);

        Graph g1 = g.prims();
        g1.display();
    }
}
