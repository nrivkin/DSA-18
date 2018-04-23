import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
//            PriorityQueue<Edge> edges = new PriorityQueue<>();
//            Set<Edge> seen = new HashSet<>();
//            for(Edge e : G.edges(0)) {
//                edges.add(e);
//                seen.add(e);
//            }
//            int totW = 0;
//            Set<Integer> visited = new HashSet<>();
//            visited.add(0);
//            while(!edges.isEmpty()){
//                Edge e = edges.poll();
//                int v = e.either();
//                int w = e.other(v);
//                if(!visited.contains(w) || !visited.contains(v)){ // does not create cycle
//                    visited.add(w);
//                    for(Edge e2 : G.edges(w)){
//                        if(!seen.contains(e2)) {
//                            edges.add(e2);
//                            seen.add(e2);
//                        }
//                    }
//                    totW += e.weight();
//                }
//            }
            IndexPQ<Edge> pq = new IndexPQ<>(G.numberOfV());
            HashMap<Integer, Edge> least = new HashMap<>();
            Set<Integer> visited = new HashSet<>();
            int totW = 0;
            int curV = 0;
//            visited.add(curV);
//            Edge least = null;
//            for(Edge e : G.edges(curV)){
//                if(pq.isEmpty()) {
//                    pq.insert(e.other(curV), e);
//                    pq.insert(curV, e);
//                    least = e;
//                } else{
//                    pq.insert(e.other(curV), e);
//                    if(least != null) {
//                        if (e.weight() < least.weight()) {
//                            pq.decreaseKey(curV, e);
//                            least = e;
//                        }
//                    }
//                }
//            }
//            if(least != null) totW += least.weight();
//            while (!pq.isEmpty()){
//                curV = pq.delMin();
//                if(!visited.contains(curV)) {
//                    visited.add(curV);
//                    least = null;
//                    for (Edge e : G.edges(curV)) {
//                        if (least == null) least = e;
//                        if (!pq.contains(e.other(curV))) pq.insert(e.other(curV), e);
//                        else {
//                            try {
//                                pq.decreaseKey(e.other(curV), e);
//                                least = e;
//                            } catch (IllegalArgumentException ignore) {
//
//                            }
//                        }
//                    }
//                    if (least != null) totW += least.weight();
//                }
//            }
            for(Edge e : G.edges(curV)){
                pq.insert(e.other(curV), e);
                least.put(e.other(curV), e);
            }
            visited.add(curV);
            while(!pq.isEmpty()){
                curV = pq.delMin();
                Edge toAdd = least.get(curV);
                totW += toAdd.weight();
                for(Edge e : G.edges(curV)){
                    int w = e.other(curV);
                    if(!visited.contains(w)){
                        if(!pq.contains(w)){
                            pq.insert(w, e);
                            least.put(w, e);
                        } else {
                            if(least.get(w).weight() > e.weight()){
                                pq.decreaseKey(w, e);
                                least.put(w, e);
                            }
                        }
                    }
                }
                visited.add(curV);
            }
            return totW;
        }
    }

