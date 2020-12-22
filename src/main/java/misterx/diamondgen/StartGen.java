package misterx.diamondgen;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.NetherBiomeSource;
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
    public SimDebrisGen simDebrisGen = new SimDebrisGen();
    public long currentSeed = 0L;

    StartGen(long seed) {
        this.currentSeed = seed;
    }

    public void getStartingPos(int BlockX, int BlockZ) {
        if(!chunkList.Check(BlockX,BlockZ)) {
            return;
        }
        int step;
        if(DiamondGen.ver.equals("1.16")) {
            step = 6;
        } else {
            step = 4;
        }
        long decSeed = ChunkSeeds.getDecoratorSeed(this.currentSeed, BlockX, BlockZ, 9, step, MCVersion.v1_14_4);
        Random random = new Random();
        random.setSeed(decSeed);
        int x = random.nextInt(16)+BlockX;
        int z = random.nextInt(16)+BlockZ;
        int y = random.nextInt(16);


        BlockPos blockPos = new BlockPos(x,y,z);
        simOreGen.generate(random,blockPos,DiamondGen.gen.world,8);
        ancienDebris(BlockX,BlockZ);
    }

    public void ancienDebris(int BlockX,int BlockZ){
        NetherBiomeSource netherBiomeSource = new NetherBiomeSource(MCVersion.v1_16_2,currentSeed);
        Biome biome = netherBiomeSource.getBiome(BlockX+8,0,BlockZ+8);
        int index = 15;
        if(biome.getName().equals("warped_forest")) {
            index = 13;
        } else if(biome.getName().equals("crimson_forest")) {
            index = 12;
        }
        long decSeed = ChunkSeeds.getDecoratorSeed(this.currentSeed, BlockX, BlockZ, index,7, MCVersion.v1_16_2);
        Random random = new Random();
        random.setSeed(decSeed);

        int x = random.nextInt(16)+BlockX;
        int z = random.nextInt(16)+BlockZ;
        //int y = random.nextInt(128 - 16) + 8;
        int y = random.nextInt(8) + random.nextInt(8) +8;
        BlockPos blockPos = new BlockPos(x,y,z);
        simDebrisGen.generate(random,blockPos,3);

        decSeed = ChunkSeeds.getDecoratorSeed(this.currentSeed, BlockX, BlockZ, index+1,7, MCVersion.v1_16_2);
        random.setSeed(decSeed);

        x = random.nextInt(16)+BlockX;
        z = random.nextInt(16)+BlockZ;
        y = random.nextInt(112)+8;
        BlockPos blockPos1 = new BlockPos(x,y,z);
        simDebrisGen.generate(random,blockPos1,2);
    }
}
