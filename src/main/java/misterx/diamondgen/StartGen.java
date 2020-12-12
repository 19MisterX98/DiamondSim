package misterx.diamondgen;

import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.seed.ChunkSeeds;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class StartGen {
    public SimOreGen simOreGen = new SimOreGen();
    public ChunkGenerated chunkList = new ChunkGenerated();

    private long currentSeed = 0L;

    StartGen(long seed) {
        this.currentSeed = seed;
    }
    public void setCurrentSeed(long seed) {this.currentSeed = seed;}

    public void getStartingPos(int BlockX, int BlockZ, ClientWorld world) {
        if(!chunkList.Check(BlockX,BlockZ)) {
            return;
        }
        long decSeed = ChunkSeeds.getDecoratorSeed(this.currentSeed,BlockX,BlockZ,9,6, MCVersion.v1_16_2);
        Random random = new Random();
        random.setSeed(decSeed);
        int x = random.nextInt(16)+BlockX;
        int z = random.nextInt(16)+BlockZ;
        int y = random.nextInt(16);


        BlockPos blockPos = new BlockPos(x,y,z);
        //System.out.println(blockPos.toShortString());
        simOreGen.generate(random,blockPos,world);
    }
}
