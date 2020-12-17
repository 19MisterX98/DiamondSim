package misterx.diamondgen;

import misterx.diamondgen.render.Color;
import misterx.diamondgen.render.Cube;
import misterx.diamondgen.render.Renderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimDebrisGen {

    public List<Renderer> renderers = new ArrayList<>();

    public boolean generate(Random random, BlockPos blockPos, int size) {
        int i = random.nextInt(size + 1);
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int j = 0; j < i; ++j) {
            this.getStartPos(mutable, random, blockPos, Math.min(j, 7));
            renderers.add(new Cube(mutable, new Color(255, 255, 0)));
        }

        return true;
    }

    private void getStartPos(BlockPos.Mutable mutable, Random random, BlockPos pos, int size) {
        int i = this.randomCoord(random, size);
        int j = this.randomCoord(random, size);
        int k = this.randomCoord(random, size);
        mutable.set((Vec3i)pos, i, j, k);
    }

    private int randomCoord(Random random, int size) {
        return Math.round((random.nextFloat() - random.nextFloat()) * (float)size);
    }

}
