/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import util.Queue;
import util.ArrayList;
import java.util.HashSet;
import util.Pair;

/**
 *
 * @author sami
 */
public class DungeonBFS {
    public static ArrayList<Pair<Integer, Integer>> traverseBFS(Dungeon dungeon, Pair<Integer, Integer> start) {
        HashSet<Pair<Integer, Integer>> visited = new HashSet<>();
        ArrayList<Pair<Integer, Integer>> result = new ArrayList<>();
        Queue<Pair<Integer, Integer>> openSet = new Queue<>();
        
        openSet.enqueue(start);
        
        while (!openSet.isEmpty()) {
            Pair<Integer, Integer> next;
            next = openSet.dequeue();
            
            int x = next.getFirst();
            int y = next.getSecond();
            
            for (Pair<Integer, Integer> neighbour : dungeon.getNeighbours(next)) {
                if (visited.contains(neighbour)) continue;
                if (!openSet.contains(neighbour)) {
                    openSet.enqueue(neighbour);
                }
            }
            
            visited.add(next);
            result.add(next);
        }
        
        return result;
    }
}
