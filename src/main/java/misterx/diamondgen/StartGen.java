package misterx.diamondgen;

import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.seed.ChunkSeeds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class StartGen {
    public SimOreGen simOreGen = new SimOreGen();
    public ClientWorld world = MinecraftClient.getInstance().world;
    public PlayerEntity player = MinecraftClient.getInstance().player;
    public ChunkGenerated chunkList = new ChunkGenerated();
    public long currentSeed = 0L;

    StartGen(long seed) {
        this.currentSeed = seed;
    }

    public void getStartingPos(int BlockX, int BlockZ) {
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
        simOreGen.generate(random,blockPos,DiamondGen.gen.world);
    }
}
