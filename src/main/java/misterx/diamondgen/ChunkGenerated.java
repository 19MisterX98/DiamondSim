package misterx.diamondgen;

import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ChunkGenerated {

    private ArrayList<ArrayList<Integer>> chunks = new ArrayList<>();

    public boolean Check(int x, int z){
        ArrayList<Integer> searchChunk = new ArrayList<>();
        searchChunk.add(x);
        searchChunk.add(z);
        if(chunks.contains(searchChunk)) {
            return false;
        } else  {
            chunks.add(searchChunk);
            return true;
        }
    }
}
