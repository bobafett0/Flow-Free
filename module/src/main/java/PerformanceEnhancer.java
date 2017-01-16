package module.src.main.java;

import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Dictionary;
import java.util.Set;
import java.util.ArrayList;
// Calculate min and max distances for each pair
public class PerformanceEnhancer {
    private int infinity = 999999999;
    private void Dijkstra (StartingPair curGridPair,GridSpot[][] gridSpotArray, Hashtable<GridSpot,StartingPair> GridSpotIdentifier ){
        Hashtable<GridSpot,Integer> dist = new Hashtable<GridSpot,Integer>();
        Hashtable<GridSpot,GridSpot> prevs = new Hashtable<GridSpot,GridSpot>();
        PriorityQueue<PairI<Integer,GridSpot>> minQueue = new PriorityQueue<PairI<Integer,GridSpot>>();
        GridSpot source = curGridPair.getL();
        GridSpot goal = curGridPair.getR();        
        dist.put(source,0);

        Set<GridSpot> occupiedSpaces = GridSpotIdentifier.keySet();

        for(int i = 0; i < gridSpotArray.length; i++)
            for(int u = 0; u < gridSpotArray[i].length; u++)
            {
                GridSpot curSpot = gridSpotArray[i][u];
                if(!occupiedSpaces.contains(curSpot)) {
                    minQueue.add(new PairI<Integer,GridSpot>(infinity,curSpot));
                    dist.put(curSpot,infinity);
                    prevs.put(curSpot, null);
                }

            }
        
        while(minQueue.size() > 0 ){
            GridSpot cur = minQueue.poll().getR();
//            GridUtilities.getNeighbors(cur, gridSpotArray)
            ArrayList<GridSpot> neighbors = GridUtilities.getNeighbors(cur,gridSpotArray);

        }
        
    }
}